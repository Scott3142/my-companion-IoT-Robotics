#!/usr/bin/env python
import RPi.GPIO as GPIO
import time

# Set the GPIO naming convention
GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)

# Set a variable to hold the GPIO pin identity
pinpir = 17

print("PIR Motion Module Test (CTRL-C to Exit)")

# Set pin as input
GPIO.setup(pinpir, GPIO.IN)

currentstate = 0
previousstate = 0
