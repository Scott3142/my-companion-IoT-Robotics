#!/usr/bin/env python
import rospy, os, sys
from sound_play.msg import SoundRequest
from sound_play.libsoundplay import SoundClient
from std_msgs.msg import String

rospy.init_node('sound_output_client', anonymous = True)
soundhandle = SoundClient(blocking=True)
rospy.sleep(1)
#soundhandle.stopAll()
print 'Starting TTS Output Client'

def get_response(data):
  response = data.data
  rospy.loginfo("Response :: %s", response)
  soundhandle.say(response)

def listener():
  rospy.loginfo("Start Listening to Input")
  rospy.Subscriber("response", String, get_response, queue_size=10)
  rospy.spin()

if __name__ == '__main__':
  listener()