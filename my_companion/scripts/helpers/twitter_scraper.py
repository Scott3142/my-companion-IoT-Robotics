from helpers.download_helpers import download
from lxml.html import fromstring, tostring
import re

class Tweet:
    permalink = ""
    time = ""
    content = ""
    display_name = ""
    photos = []
    video = ""

    def __init__(self, permalink, time, content, display_name, photos, video):
        self.permalink = permalink
        self.time = time
        self.content = re.sub(r'\n', "", str(content))
        self.display_name = display_name
        self.photos = photos
        self.video = video
    
    def __eq__(self, other):
        return self.permalink == other.permalink

    def json_string(self):
        return "{" + "\"permalink\":\"{permalink}\", \"time\":\"{time}\",\"content\":\"{content}\", \"displayName\":\"{display}\", \"video\":\"{video}\"".format(
            permalink=self.permalink, time=self.time, content=self.content, display=self.display_name, video=self.video) + ", \"photos\":[\"" + '", "'.join([x for x in self.photos]) + "\"]}"

twitter_host = 'https://twitter.com/'

def fetch_tweets(at, reversed=True, amount=5):
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
        display_name_element = tweet.cssselect('span.FullNameGroup > .fullname')[0]
        video_element = tweet.cssselect('div.AdaptiveMedia-container video')
        video = video_element.get("src") if len(video_element) > 0 else ""
        photos_element = tweet.cssselect('div.AdaptiveMedia-container img')
        photos = [str(x.get("src")) for x in photos_element] 
        print(video_element)
        print(photos[0]) if len(photos) > 0 else print("No image")
        tweets.append(Tweet(permalink=data_link,
         time=time_element.get('title'), content=content_element.text_content(), display_name=display_name_element.text_content(),
         photos=photos, video=video))
    if (reversed):
        tweets.reverse()
    return tweets

# twit = Tweet("link", "time", "content", "display name")
# obj = json.dumps(twit.__dict__)
# print(twit.json_string())