from mycroft import MycroftSkill, intent_file_handler
from random import choice
import time


class Knockknock(MycroftSkill):
    def __init__(self):
        MycroftSkill.__init__(self)
        self.handle_who=False

    @intent_file_handler('knockknock.intent')
    def handle_knockknock(self, message):
        #self.speak_dialog('knockknock')
        name,punchline = self.pick_joke()
        response = self.get_response(dialog="knock, knock")
        response = self.get_response(dialog=name)
        if "who" not in response:
                prompt = "You are supposed to say " + name + " who"
                response = self.get_response(dialog=prompt)
        self.speak(punchline)


    @intent_file_handler('recieveknock.intent')
    def handle_recieveknock(self, message):
        #self.speak_dialog('recieveknock')
        response = self.get_response('who.is.there')
        print("knock knock", response)
        response2 = response + " who?"
        response3 = self.get_response(dialog=response2)
        self.speak_dialog('veryfunny')
        time.sleep(12)

    def pick_joke(self):
        jokeFile = "/opt/mycroft/skills/knockknock-skill/jokes.txt"
        jfile = open(jokeFile, "r")
        jokes = []
        for jokeline in jfile:
                jokes.append(jokeline)
        joke = choice(jokes)
        jokeParts = joke.split("/")
        name = jokeParts[0]
        punchline = jokeParts[1]
        return name, punchline


def create_skill():
    return Knockknock()

