#!/usr/bin/env python3
import rospy
from my_companion.msg import Mycroft
from websocket import create_connection

def callback(data):
    rospy.loginfo(data.type)
    uri = 'ws://localhost:8181/core'
    ws = create_connection(uri)
    message = '{"type": "' + data.type + '", "data": ' + data.data + '}'
    rospy.loginfo(message)
    ws.send(message)
    ws.close()


def listener():
    rospy.init_node('mycroft_node')
    rospy.loginfo(rospy.get_caller_id() + " started")
    rospy.Subscriber('/mycroft/skill', Mycroft, callback)
    rospy.spin()

if __name__ == '__main__':
    listener()