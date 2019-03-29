#!/usr/bin/env python
import rospy
from std_msgs.msg import String
import aiml
import os
import sys

rospy.init_node('aiml_server_client')
mybot = aiml.Kernel()
response_publisher = rospy.Publisher('response', String, queue_size=10)

def load_aiml(xml_file):
  data_path = rospy.get_param("aiml_path")
  print data_path
  os.chdir(data_path)

  if os.path.isfile("standard.brn"):
    mybot.bootstrap(brainFile = "standard.brn")
  else:
    mybot.bootstrap(learnFiles = xml_file, commands = "load aiml")
    mybot.saveBrain("standard.brn")

def callback(data):
  input = data.data
  response = mybot.respond(input)
  rospy.loginfo("I heard :: %s", data.data)
  rospy.loginfo("I spoke :: %s", response)
  response_publisher.publish(response)

def listener():
  rospy.loginfo("Starting ROS AIML Server Example")
  rospy.Subscriber("/speech/input", String, callback)
  rospy.spin()

if __name__ == '__main__':
  load_aiml('startup.xml')
  listener()
