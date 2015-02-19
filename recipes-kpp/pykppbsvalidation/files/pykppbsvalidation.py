import serial
import httplib
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
  


apppath=os.path.dirname(os.path.abspath(__file__))
configFile=os.path.join(os.getcwd(),'App.cfg')

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


def main():
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

                httpServ = httplib.HTTPConnection(scriptloc, int(scriptport))

                httpServ.connect()

                ser=None
                while(True):
                  try:
                    if waitfor_port==False:
                      if ser==None:
                        ser = serial.Serial(serialport,9600,timeout=0)
                        print "Connected to Barcode Reader at : "+ serialport

                      code = ser.readline()   # read a '\n' terminated line
                      if code != "":
                        print "\nCode:"+code
                        request='/kpp/bsquery.pl?bsid='+code.rstrip().translate(None, '*$')+'&stationid='+stationid
                        httpServ.request('GET', request)
                        response = httpServ.getresponse()
                        if response.status == httplib.OK:
                          print "Output from HTML request"
                          printText (response.read())

                    else:
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
