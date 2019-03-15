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

try:
    print("Waiting for PIR to Settle ...")
    # Loop until PIR output is 0
    while GPIO.input(pinpir) == 1:
        currentstate = 0

    print("Ready")
    # Loop until user quits using CTRL-C
    while True:
        # Read PIR state
        currentstate = GPIO.input(pinpir)

        # If the PIR is triggered
        if currentstate == 1 and previousstate == 0:
            print("Motion Detected!")
            # Record previous state
            previousstate = 1
        # IF the PIR has returned to the Ready State
        elif currentstate = 0 and previousstate == 1:
            print("Ready")
            previousstate = 0

        time.sleep(0.01)
