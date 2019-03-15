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

# Convert the value of the sensor into a temperature
def read_temp():
    lines = read_temp_raw() # read the temperature device file

    # While the first line does not contain 'YES' then wait 0.2s and then read the device file again
    while lines[0].strip()[-3:] != 'YES':
        time.sleep(0.2)
        lines = read_temp_raw
    # Look for the position of the '=' in the second line of the device file
    equals_pos = lines[1].find('t=')

    # If the '=' is found then convert the rest of the line into degrees Celsius and take the current time stamp
    if equals_pos != -1:
        temp_string = lines[1][equals_pos+2:]
        temp_c = float(temp_string) / 1000.0
        time = datetime.datetime.now()
        return temp_c, time.strftime("%Y-%m-%d %H:%M:%S") # Format date time into readable form

# Print temperature until program stops
while True:
    print(read_temp())
    time.sleep(1)