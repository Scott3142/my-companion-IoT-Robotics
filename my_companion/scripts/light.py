#!/usr/bin/env python
import time
import RPi.GPIO as GPIO
import requests
import datetime

class Light:
        uuid = 0
        sensorName = ""
        light = 0
        timestamp = ""

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

    # While the input pin reads 'off' or LOW count
    while (GPIO.input(pinldr) == GPIO.LOW):
        ldrcount += 1 # Add one to the counter

    time = datetime.datetime.now()
    light = Light()

    light.uuid = 1
    light.sensorName = "Kitchen"
    light.light = ldrcount
    light.timestamp = time.strftime("%Y-%m-%dT%H:%M:%S.%f")
    requests.post('http://10.72.97.47:8080/api/lights/', json=temp.__dict__)

    return ldrcount, time.strftime("%Y-%m-%dT%H:%M:%S.%f")

while True:
    print(readldr())
    time.sleep(5) # Wait 1 second
