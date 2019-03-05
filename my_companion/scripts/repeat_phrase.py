#!/usr/bin/env python3
import rospy
from std_msgs.msg import String
from websocket import create_connection

def callback(data):
    rospy.loginfo(data.data)
    uri = 'ws://localhost:8181/core'
    ws = create_connection(uri)
    message = '{"type": "skill.repeatphrase.handle.start", "data": {"message": "' + data.data + '"}}'
    rospy.loginfo(message)
    ws.send(message)
    ws.close()

def listener():
    rospy.init_node('repeat_phrase_node')
    rospy.loginfo(rospy.get_caller_id() + " started")
    rospy.Subscriber('/repeat/phrase', String, callback)
    rospy.spin()

if __name__ == '__main__':
    listener()
