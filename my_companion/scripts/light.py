#!/usr/bin/env python
import time
import RPi.GPIO as GPIO

#Set the GPIO mode
GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)

#A variable with the LDR reading pin number
pinldr = 27
