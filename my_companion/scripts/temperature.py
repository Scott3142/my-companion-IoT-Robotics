#!/usr/bin/env python
import os
import glob
import time
import datetime

# Initialize the GPIO Pins
os.system('modprobe w1-gpio')
os.system('modprobe w1-therm')

# Finds the correct device file that holds the temperature data
base_dir = '/sys/bus/w1/devices/'
device_folder = glob.glob(base_dir + '28*')[0]
device_file = device_folder + '/w1_slave'

# Function that reads the sensor data
def read_temp_raw():
    f = open(device_file, 'r') # opens the temperature data file
    lines = f.readlines() # returns the text
    f.close
    return lines
