#!/usr/bin/env python3
import rospy
from my_companion.msg import UserResponse

def callback(data):
    rospy.loginfo('\n Recieved User Response of Type: ' + data.type + "\n At: " + data.timestamp + "\n Data Recieved: " + data.data)

def listener():
    rospy.init_node('user_response_node')
    rospy.loginfo(rospy.get_caller_id() + " started")
    rospy.Subscriber('/user/response', UserResponse, callback)
    rospy.spin()

if __name__ == '__main__':
    listener()
