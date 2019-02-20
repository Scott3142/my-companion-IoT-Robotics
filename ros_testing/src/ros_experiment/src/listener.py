import rospy
from std_msgs.msg import String

def callback(message):
    #get caller id(): Get fully resolved name of local node
    rospy.loginfo(rospy.get_caller_id() + "I heard %s", message.data)

def listener():
    rospy.init_node('listener', anonymous=True)

    rospy.Subscriber("chatter", String, callback)

    #spin() simple keeps python from exiting until this node is stopped
    rospy.spin()

if __name__ == '__main__':
    listener()