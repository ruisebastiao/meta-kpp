import serial
import urllib2
import urllib
import signal
import sys
from ConfigParser import SafeConfigParser
import os
import os.path
import functools
import os.path
import pyudev
import subprocess
from time import sleep
import SUNXI_GPIO as GPIO
import time
from threading import Thread
import collections

class BSinfo():
    "Stores BS serial and status pair"
    def __init__(self, serial, status):
        self.serial = serial
        self.status = status
    def __repr__(self):
      return '%s:%s' % (self.serial, self.status)
    def __str__(self):
      return '%s:%s' % (self.serial, self.status)
        
apppath=os.path.dirname(os.path.abspath(__file__))
#configFile=os.path.join(os.getcwd(),'App.cfg')
configFile='/home/root/App.cfg'

def process_response(resp):
  global RED_LED
  global GREEN_LED
  global YELLOW_LED
  global last_readings
  
  #print "Teste:"+str(len(last_readings))
  lednum=0
  responses=resp.split('\n');
  responses_count=len(responses)
  if responses_count>=6:
    result=responses[0];
    uses_left=responses[1];
    serial=responses[3];
    
    #readed_bsinfo=BSinfo(serial,result)
    bsinfos=filter(lambda info: info.serial== serial, last_readings)
    if len(bsinfos)==0 :
      if result=="IGNORE":
	result='NOTOK'
	
      readed_bsinfo=BSinfo(serial,result);      
      last_readings.appendleft(readed_bsinfo)
    else:      
      readed_bsinfo=last_readings[0]
      result=readed_bsinfo.status
      
    if result=="NOTOK":
      lednum=1
    elif result=="OK":
      lednum=2
    
    
    thread_blink = Thread(target = blink_led, args = (lednum, 0.1,3))
    thread_blink.daemon = True
    blink_exit=False
    thread_blink.start()
    
    
  sleep(1)
  GPIO.output(YELLOW_LED, GPIO.LOW)
  
def setup_leds():
  global RED_LED
  global GREEN_LED
  global YELLOW_LED
  
  RED_LED = GPIO.PG5
  GREEN_LED = GPIO.PG1
  YELLOW_LED = GPIO.PG2  
  
  GPIO.init()
  GPIO.setcfg(RED_LED, GPIO.OUT)
  GPIO.setcfg(GREEN_LED, GPIO.OUT)
  GPIO.setcfg(YELLOW_LED, GPIO.OUT)
  
  GPIO.output(RED_LED, GPIO.HIGH)
  GPIO.output(GREEN_LED, GPIO.HIGH)
  GPIO.output(YELLOW_LED, GPIO.HIGH)
  time.sleep(1)
  GPIO.output(RED_LED, GPIO.LOW)
  GPIO.output(GREEN_LED, GPIO.LOW)
  #GPIO.output(YELLOW_LED, GPIO.LOW)
  


def signal_handler(signal, frame):
        print('Exiting...')
        global ser
        if ser!=None:
          ser.close()
        
        global RED_LED
	global GREEN_LED
	global YELLOW_LED
        GPIO.output(RED_LED, GPIO.LOW)
	GPIO.output(GREEN_LED, GPIO.LOW)
	GPIO.output(YELLOW_LED, GPIO.LOW)
	global thread_blink
	global blink_exit
	blink_exit=False
	thread_blink.join(2)
	sys.exit(0)
  
def printText(txt):
    lines = txt.split('\n')
    for line in lines:
        print line.strip()

global configparser
global scriptloc
global scriptport
global stationid


def LoadSettings():
  global configparser
  global scriptloc
  global scriptport
  global stationid
  global serialport

  print "Loading configurations"
  defaults = {'scriptloc': '192.168.131.30',
                'port':'8080',
                'stationid':'bskpp40',
                'serialport':'/dev/ttyACM1'
                  }

  configparser = SafeConfigParser(defaults)
  if os.path.isfile(configFile)==False:
    with open(configFile, 'a'):
        os.utime(configFile, None)

  configparser.read(configFile)
  if not configparser.has_section('SCRIPT'):
        configparser.add_section('SCRIPT')
  if not configparser.has_section('INPUT'):
        configparser.add_section('INPUT')

  scriptloc=configparser.get('SCRIPT','scriptloc')
  scriptport=configparser.get('SCRIPT','port')
  stationid=configparser.get('SCRIPT','stationid')

  serialport=configparser.get('INPUT','serialport')

  print "Script adress : "+ scriptloc
  print "Script port : "+ scriptport
  print "Input : "+ serialport

  configparser.set('SCRIPT','scriptloc',scriptloc)
  configparser.set('SCRIPT','port',scriptport)
  configparser.set('SCRIPT','stationid',stationid)

  configparser.set('INPUT','serialport',serialport)

  with open(configFile, 'wb') as configfile:
    configparser.write(configfile)




signal.signal(signal.SIGINT, signal_handler)
signal.signal(signal.SIGTERM, signal_handler)

def log_event(action, device):
  global waitfor_port
  global ser
  print "Event device :"+device.device_node+" - "+action

  if serialport==device.device_node:
    if action=="remove":
      waitfor_port=True
      sleep(0.2)
      print "Waiting for Barcode Reader at :" +serialport
      if ser!=None:
        ser.close()
        ser=None

    else:
      print "Barcode reader Connected..."

      waitfor_port=False


def blink_led(lednum,blink_speed,blink_num):
  global RED_LED
  global GREEN_LED
  global YELLOW_LED
  
  global blink_exit
  blink_exit=False
  blinkcount=0
  while blink_exit==False:
    if lednum==1:
      GPIO.output(RED_LED, GPIO.LOW)
      sleep(blink_speed)
      GPIO.output(RED_LED, GPIO.HIGH)
      sleep(blink_speed)
    elif lednum==2:      
      GPIO.output(GREEN_LED, GPIO.LOW)
      sleep(blink_speed)
      GPIO.output(GREEN_LED, GPIO.HIGH)
      sleep(blink_speed)
    elif lednum==3:
      GPIO.output(YELLOW_LED, GPIO.LOW)
      sleep(blink_speed)
      GPIO.output(YELLOW_LED, GPIO.HIGH)
      sleep(blink_speed)
    if blink_num>0:
      blinkcount=blinkcount+1
      if blinkcount==blink_num:
	break;
      


def main():
	global last_readings
	last_readings = collections.deque([BSinfo("",""),BSinfo("",""),BSinfo("",""),BSinfo("",""),BSinfo("","")])
	global blink_exit
	global RED_LED
	global GREEN_LED
	global YELLOW_LED
        setup_leds()
        global ser
        global waitfor_port
        waitfor_port=False
        context = pyudev.Context()
        monitor = pyudev.Monitor.from_netlink(context)
        monitor.filter_by('tty')
        observer = pyudev.MonitorObserver(monitor, log_event)
        observer.start()
        try:

                LoadSettings();

                
		errorretry=0
                ser=None
                global thread_blink
                while True :
                  try:
                    if waitfor_port==False:
                      if ser==None:
                        ser = serial.Serial(serialport,9600,timeout=0)
                        sleep(0.1)
                        ser.write('\x45')
                        print "Connected to Barcode Reader at : "+ serialport
		      if errorretry==0:
			code = ser.readline()   # read a '\n' terminated line			
		      if code != "":
			ser.write('\x44')
			GPIO.output(RED_LED, GPIO.LOW)
			GPIO.output(GREEN_LED, GPIO.LOW)
			GPIO.output(YELLOW_LED, GPIO.LOW)
			thread_blink = Thread(target = blink_led, args = (3,0.5,0 ))
			thread_blink.daemon = True
			thread_blink.start()
                        print "\nCode:"+code
                        request='/kpp/bsquery.pl?bsid='+code.rstrip().translate(None, '*$')+'&stationid='+stationid
                        
                        try:
			  response = urllib2.urlopen('http://'+scriptloc+':'+scriptport+request)
			  resp=response.read()			  
			  printText (resp)
			  process_response(resp)
			  blink_exit=True
			  thread_blink.join()			  
			except Exception,err:
			  print "Error processing HTML request - "+str(err)
			  blink_exit=True
			  thread_blink.join()
			
			sleep(1)
			ser.write('\x45')
			#ser.flushInput()

                    #else:
		    sleep(0.1)

                  except OSError:
                    print "Read Error"
                    waitfor_port=True
                    if ser!=None:
                        ser.close()
                        ser=None
                  except serial.serialutil.SerialException,e:
                    print "Serial Error : "+e.message
                    if "could not open port" in e.message:
                      print "Waiting for Barcode Reader at :" +serialport
                      waitfor_port=True
                      if ser!=None:
                        ser.close()
                        ser=None


        except KeyboardInterrupt:
                print('You pressed Ctrl+C!')
                ser.close()
                sys.exit(0)

        ser.close()

if __name__ == '__main__':
    main()
