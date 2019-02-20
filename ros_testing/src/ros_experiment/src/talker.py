import rospy
from std_msgs.msg import String

def talker():
    #create a new publisher. we specifu the topic name, then type of message then the queue size
    pub = rospy.Publisher('chatter', String, queue_size=10)

    #we need to initialise the node
    rospy.init_node('talker', anonymous=True)

    #set the loop rate
    rate = rospy.Rate(1)

    #keep publishing until a ctrl-c is pressed
    while not rospy.is_shutdown():
        hello_str = "hello world %s" % rospy.get_time()
        rospy.loginfo(hello_str)
        pub.publish(hello_str)
        rate.sleep()


    if __name__ == '__main__':
        try:
            talker()
        except rospy.ROSInterruptException:
            pass