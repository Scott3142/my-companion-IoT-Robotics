#!/usr/bin/env python
import RPi.GPIO as GPIO
import time
import requests
import datetime
import rospy

# Set Broadcom mode so we can address GPIO pins by number.
GPIO.setmode(GPIO.BCM)
# This is the GPIO pin number we have one of the door sensor
# wires attached to, the other should be attached to a ground
DOOR_SENSOR_PIN = 18
# Initially we don't know if the door sensor is open or closed
isOpen = None
oldIsOpen = None

# Set up the door sensor pin.
GPIO.setup(DOOR_SENSOR_PIN, GPIO.IN, pull_up_down = GPIO.PUD_UP)

class Door:
    uuid = 0
    sensorName = ""
    door = 0
    timestamp = ""

if __name__ == '__main__':
    rospy.init_node('door')
    while True:
        oldIsOpen = isOpen
        isOpen = GPIO.input(DOOR_SENSOR_PIN)
        if (isOpen and (isOpen != oldIsOpen)):
            print("Door is opened!")
            print(isOpen)

            time1 = datetime.datetime.now()
            door = Door()

            door.uuid = 1
            door.sensorName = "Fridge"
            door.door = isOpen
            door.timestamp = time1.strftime("%Y-%m-%dT%H:%M:%S.%f")
            requests.post('http://10.72.97.47:8080/api/doors/', json=door .__dict__)

            
        elif (isOpen != oldIsOpen):
            print("door is closed!")
            print(isOpen)
        time.sleep(0.1)
    rospy.spin()

