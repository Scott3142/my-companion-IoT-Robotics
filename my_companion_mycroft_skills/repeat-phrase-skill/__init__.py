from mycroft import MycroftSkill, intent_file_handler


class RepeatPhrase(MycroftSkill):
    def __init__(self):
        MycroftSkill.__init__(self)

    def initialize(self):
        self.add_event('skill.repeatphrase.handle.start', self.handle_phrase_repeat)

    @intent_file_handler('phrase.repeat.intent')
    def handle_phrase_repeat(self, message):
        phrase = message.data.get("message")
        repeat_phrase = "Please repeat the phrase " + phrase + " back to me"
        response = self.get_response(dialog=repeat_phrase)
        if phrase.lower() not in response.lower():
            self.speak("That is incorrect you were supposed to say " + phrase + " but you said " + response)
        else:
            self.speak("Thank you!")

    def handle_phrase_repeat_start(self, message):
        phrase = message.data.get("message")
        repeat_phrase = "Please repeat the phrase " + phrase + " back to me"
        response = self.get_response(dialog=repeat_phrase)
        if phrase.lower() not in response.lower():
            self.speak("That is incorrect you were supposed to say " + phrase + " but you said " + response)
        else:
            self.speak("Thank you!")


def create_skill():
    return RepeatPhrase()

