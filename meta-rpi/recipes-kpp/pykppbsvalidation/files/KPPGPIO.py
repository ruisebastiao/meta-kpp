import RPi.GPIO as GPIO
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
  
  GPIO.setmode(GPIO.BCM)
  
  
  RED_LED = 24
  GREEN_LED = 23
  YELLOW_LED = 25 
  
  GPIO.setup(RED_LED , GPIO.OUT)
  GPIO.setup(GREEN_LED , GPIO.OUT)
  GPIO.setup(YELLOW_LED , GPIO.OUT)
  
  NOK_ON()
  OK_ON()
  STATUS_ON()
  time.sleep(1)
  NOK_OFF()
  OK_OFF()

