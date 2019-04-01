#!/usr/bin/env python3
import rospy
from std_msgs.msg import String
from my_companion.msg import Twitter
from helpers.twitter_scraper import fetch_tweets, Tweet
from helpers.mycroft_helpers import publish_mycroft_message

class TweetList:
    at = ""
    tweets = []

    def __init__(self, at, tweets):
        self.at = at
        self.tweets = tweets

    def json_string(self):
        seperator = ', '
        return "{" + "\"at\":\"{at}\",\"tweets\":[".format(at=self.at) + seperator.join([x.json_string() for x in self.tweets]) + "]}"

tweets = []
pub = rospy.Publisher('/twitter/tweets', Twitter, queue_size=100)

def new_tweets_json(new_tweets):
    return "{\"newTweets\":[" + ', '.join([x.json_string() for x in new_tweets]) + "]}"

# def mycroft_json_string(new_tweets):
#     return "{\"type\":\"skill.ros-twitter.handle.new_tweets\", \"data\":

def handle_tweets(event):
    rospy.loginfo('Running handle_tweets')
    new_tweets = []
    for tweet in tweets:
        temp = TweetList(tweet.at, [])
        for x in fetch_tweets(tweet.at):
            if x in tweet.tweets:
                rospy.loginfo('Tweet already in list')
                continue
            if (len(tweet.tweets) >= 5):
                tweet.tweets.pop()
            tweet.tweets.insert(0, x)
            temp.tweets.append(x)
        if len(temp.tweets) > 0:
            new_tweets.append(temp)
    if len(new_tweets) > 0:
        rospy.loginfo('There are new Tweets available to read')
        # new_tweets[0].tweets = [new_tweets[0].tweets[0]]
        json_tweets = new_tweets_json(new_tweets)
        # ros_tweet_mycroft = Mycroft()
        # ros_tweet_mycroft.type = "skill.ros-twitter.handle.new_tweets"
        # ros_tweet_mycroft.data = json
        rospy.loginfo(json_tweets)
        # publish_mycroft_message("skill.ros-twitter.handle.new_tweets", "{\"newTweets\":\"hello\"}")
        publish_mycroft_message("skill.ros-twitter.handle.new_tweets", json_tweets)


def listener():
    rospy.init_node('twitter_node')
    rospy.loginfo(rospy.get_caller_id() + " started")
    rospy.set_param('twitter/ats', ['cusoftacademy', 'steam_games'])
    ats = rospy.get_param('twitter/ats')
    for x in ats:
        tweets.append(TweetList(x, []))
    timer = rospy.Timer(rospy.Duration(60), handle_tweets)
    rospy.spin()
    timer.shutdown()
    # while not rospy.is_shutdown():
    #     handle_tweets()
    #     rospy.sleep(60)

if __name__ == '__main__':
    listener()