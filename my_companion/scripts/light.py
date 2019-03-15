#!/usr/bin/env python
import time
import RPi.GPIO as GPIO

#Set the GPIO mode
GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)

#A variable with the LDR reading pin number
pinldr = 27

def readldr():
    ldrcount = 0 # Sets the count to 0
    GPIO.setup(pinldr, GPIO.OUT)
    GPIO.output(pinldr, GPIO.LOW)
    time.sleep(0.1) # Drains all charge from capacitor
    GPIO.setup(pinldr, GPIO.IN) # Sets the pin to input
