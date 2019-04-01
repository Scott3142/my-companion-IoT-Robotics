#!/usr/bin/env python
import RPi.GPIO as GPIO
import time
import datetime
import requests

class Motion:
        uuid = 0
        sensorName = ""
        motion = 0
        timestamp = ""

# Set the GPIO naming convention
GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)

# Set a variable to hold the GPIO pin identity
pinpir = 17
pinredled = 26
pinblueled = 24
pinbuzzer = 22

print("PIR Motion Module Test (CTRL-C to Exit)")

# Set pin as input/output
GPIO.setup(pinpir, GPIO.IN)
GPIO.setup(pinredled, GPIO.OUT)
GPIO.setup(pinblueled, GPIO.OUT)
GPIO.setup(pinbuzzer, GPIO.OUT)

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
            # Flash light and sound buzzer three times
            for x in range(0, 3):
                GPIO.output(pinbuzzer, GPIO.HIGH)
                GPIO.output(pinredled, GPIO.HIGH)
                time.sleep(0.2)
                GPIO.output(pinredled, GPIO.LOW)
                GPIO.output(pinblueled, GPIO.HIGH)
                time.sleep(0.2)
                GPIO.output(pinblueled, GPIO.LOW)
                GPIO.output(pinbuzzer, GPIO.LOW)
                time.sleep(0.2)

            time1 = datetime.datetime.now()
            motion = Motion()

            motion.uuid = 1
            motion.sensorName = "Kitchen"
            motion.motion = currentstate
            motion.timestamp = time1.strftime("%Y-%m-%dT%H:%M:%S.%f")
            requests.post('http://10.72.97.47:8080/api/motion/', json=motion.__dict__)

            # Record previous state
            previousstate = 1

        # IF the PIR has returned to the Ready State
        elif currentstate == 0 and previousstate == 1:
            print("Ready")
            previousstate = 0
        # Wait 10 milliseconds
        time.sleep(0.01)

except KeyboardInterrupt:
    print("Quit!")

    # Reset GPIO settings
    GPIO.cleanup()
