import SUNXI_GPIO as GPIO
import time

def NOK_ON():
  GPIO.output(RED_LED, GPIO.HIGH)

def NOK_OFF():
  GPIO.output(RED_LED, GPIO.LOW)  

def OK_ON():
  GPIO.output(GREEN_LED, GPIO.HIGH)

def OK_OFF():
  GPIO.output(GREEN_LED, GPIO.LOW)    

def STATUS_ON():
  GPIO.output(YELLOW_LED, GPIO.HIGH)

def STATUS_OFF():
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
  
  NOK_ON()
  OK_ON()
  STATUS_ON()
  time.sleep(1)
  NOK_OFF()
  OK_OFF()

