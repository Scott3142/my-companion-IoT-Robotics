from helpers.download_helpers import download
from lxml.html import fromstring, tostring

class Tweet:
    permalink = ""
    time = ""
    content = ""

    def __init__(self, permalink, time, content):
        self.permalink = permalink
        self.time = time
        self.content = content
    
    def __eq__(self, other):
        return self.permalink == other.permalink

    def json_string(self):
        return "{" + "\"permalink\":\"{permalink}\", \"time\":\"{time}\",\"content\":\"{content}\"".format(
            permalink=self.permalink, time=self.time, content=self.content) + "}"

twitter_host = 'https://twitter.com/'

def fetch_tweets(at, reversed=True):
    print('Fetching @' + at + '\'s latest 5 Tweets')
    url = twitter_host + at
    tweets = []
    html = download(url)
    tree = fromstring(html)
    tweets_element = tree.cssselect('ol#stream-items-id > li > div.tweet')
    for tweet in tweets_element[0:5]:
        data_link = tweet.get('data-permalink-path')
        time_element = tweet.cssselect('small.time > .tweet-timestamp')[0]
        content_element = tweet.cssselect('div.js-tweet-text-container > p')[0]
        tweets.append(Tweet(permalink=data_link,
         time=time_element.get('title'), content=content_element.text_content()))
    if (reversed):
        tweets.reverse()
    return tweets

# twit = Tweet("link", "time", "content")
# print(twit.json_string())