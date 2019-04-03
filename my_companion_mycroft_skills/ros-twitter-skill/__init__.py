from mycroft import MycroftSkill, intent_file_handler, intent_handler
from adapt.intent import IntentBuilder
import requests
import time
import datetime


class UserResponse:
    uuid = 1
    type = ""
    data = ""
    context = ""
    timestamp = ""

    def __init__(self, uuid, type, data, timestamp, context):
        self.uuid = uuid
        self.type = type
        self.data = data
        self.timestamp = timestamp
        self.context = context


class RosTwitter(MycroftSkill):
    def __init__(self):
        MycroftSkill.__init__(self)

    def initialize(self):
        self.add_event('skill.ros-twitter.handle.new_tweets', self.handle_new_tweets)

    @intent_file_handler('twitter.ros.intent')
    def handle_twitter_ros(self, message):
        self.speak_dialog('twitter.ros')

    """
        Method to handle list of new tweets from different Twitter accounts
    """
    def handle_new_tweets(self, message):
        phrase = message.data.get("newTweets")
        if len(phrase) > 1:
            ats = [tweet.get("at") for tweet in phrase]
            question = "There are new tweets from " + ', '.join([x.get("at") for x in phrase]) + " would you like to hear them?"
            user_response = self.ask_yesno(question)
            self._post_user_response(UserResponse(1, "SPEECH", user_response, datetime.datetime.now().strftime("%Y-%m-%dT%H:%M:%S.%f"), question))
            if user_response == 'yes':
                user_selection = self.get_response(dialog="Who's tweets would you like to hear?")
                self._post_user_response(UserResponse(1, "SPEECH", user_selection, datetime.datetime.now().strftime("%Y-%m-%dT%H:%M:%S.%f"), "Who's tweets would you like to hear?"))
                if "all" in user_selection:
                    #requests.post('http://localhost:5000/tweets', json=phrase)
                    self._post_tweets(phrase)
                elif any(at in ats for at in user_selection.lower().split()):
                    ats_tweets = list(filter(lambda x: x.get("at") in user_selection.lower(), phrase))[0]
                    read_new_tweets = ', '.join([ats_tweets.get("at") + " tweeted " + x.get("content") + " at " + x.get("time") for x in ats_tweets.get("tweets")])
                    self.speak(read_new_tweets)
                    #requests.post('http://localhost:5000/tweets', json=list(filter(lambda x: x.get("at") in user_selection.lower(), phrase)))
                    self._post_tweets(list(filter(lambda x: x.get("at") in user_selection.lower(), phrase)))
                else:
                    self.speak("I'm sorry I don't understand")
        else:
            ats_new_tweets = phrase[0].get("tweets")
            if len(ats_new_tweets) > 1:
                question = phrase[0].get("at") + " has " + str(len(ats_new_tweets)) + " new tweets, would you like to hear them?"
                user_response = self.ask_yesno(question)
                self._post_user_response(UserResponse(1, "SPEECH", user_response, datetime.datetime.now().strftime("%Y-%m-%dT%H:%M:%S.%f"), question))
                if user_response == 'yes':
                    read_new_tweets = ', '.join([phrase[0].get("at") + " tweeted " + x.get("content") + " at " + x.get("time") for x in ats_new_tweets])
                    #requests.post('http://localhost:5000/tweets', json=ats_new_tweets)
                    self._post_tweets(ats_new_tweets)
                    self.speak(read_new_tweets)
            else:
                question = phrase[0].get("at") + " has tweeted, would you like to hear it?"
                user_response = self.ask_yesno(question)
                self._post_user_response(UserResponse(1, "SPEECH", user_response, datetime.datetime.now().strftime("%Y-%m-%dT%H:%M:%S.%f"), question))
                if user_response == 'yes':
                    read_new_tweet = phrase[0].get("at") + " tweeted " +  ats_new_tweets[0].get("content") + " at " + ats_new_tweets[0].get("time")
                    #requests.post('http://localhost:5000/tweets', json=ats_new_tweets)
                    self._post_tweets(ats_new_tweets)
                    self.speak(read_new_tweet)

    @intent_handler(IntentBuilder("").require("Show").require("Tweets"))
    def handle_show_tweets(self, message):
        self.speak("Handling tweets")

    def _post_tweets(self, tweets):
        requests.post('http://10.247.39.148:5000/tweets', json=tweets)

    def _post_user_response(self, response):
        requests.post('http://10.72.97.47:8080/api/users/response', json=response.__dict__)
                


def create_skill():
    return RosTwitter()

