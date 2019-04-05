#!/usr/bin/env python3
import rospy
from std_msgs.msg import String
from websocket import create_connection
from my_companion.msg import Mycroft

rospy.init_node('Mycroft_tts_output_client', anonymous = True)
pub = rospy.Publisher('/mycroft/skill', Mycroft, queue_size=10)

def get_response(data):
    rospy.loginfo(data.data)
    # uri = 'ws://localhost:8181/core'
    # ws = create_connection(uri)
    # message = '{"type": "speak", "data": {"utterance": "' + data.data + '"}}'
    # rospy.loginfo(message)
    # ws.send(message)
    # ws.close()
    mycroft = Mycroft()
    mycroft.type = "speak"
    mycroft.data = "{\"utterance\": \"" + data.data + "\" }" 
    pub.publish(mycroft)



def listener():
  rospy.loginfo("Start Listening to Input")
  rospy.Subscriber("response", String, get_response, queue_size=1000)
  rospy.spin()

if __name__ == '__main__':
  listener()
