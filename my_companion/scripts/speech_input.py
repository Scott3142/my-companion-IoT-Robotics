#!/usr/bin/env python3
import rospy
from std_msgs.msg import String
rospy.init_node('speech_input_client')
pub = rospy.Publisher('/speech/input', String, queue_size=10)
r = rospy.Rate(1)

def get_speech(data):
  speech_text = data.data
  rospy.loginfo("I said :: %s", speech_text)
  pub.publish(speech_text)

def listener():
  rospy.loginfo("Starting Speech Input Client")
  rospy.Subscriber("/speech/raw", String, get_speech)
  rospy.spin()

while not rospy.is_shutdown():
  listener()
