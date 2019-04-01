#!/usr/bin/env python3
import rospy;
from std_msgs.msg import String;
import pyttsx3;

rospy.init_node('tts_output_client', anonymous = True)
engine = pyttsx3.init(driverName='espeak')
engine.setProperty('voice', 'english+m2')
engine.setProperty('rate', 150)
rospy.sleep(1)

def get_response(data):
  response = data.data
  rospy.loginfo("Response :: %s", response)
  engine.say(response)
  engine.runAndWait()

def listener():
  rospy.loginfo("Start Listening to Input")
  rospy.Subscriber("response", String, get_response, queue_size=10)
  rospy.spin()

if __name__ == '__main__':
  listener()
