import re
from urllib import robotparser
import requests
from lxml.html import fromstring, tostring

# ref: https://github.com/kjam/wswp/blob/master/code/chp1/advanced_link_crawler_using_requests.py

def get_robots_parser(robots_url):
    " Return the robots parser object using the robots_url "
    rp = robotparser.RobotFileParser()
    rp.set_url(robots_url)
    rp.read()
    return rp

def download(url, num_retries=2, user_agent='wswp', proxies=None):
    """ Download a given URL and return the page content
        args:
            url (str): URL
        kwargs:
            user_agent (str): user agent (default: wswp)
            proxies (dict): proxy dict w/ keys 'http' and 'https', values
                            are strs (i.e. 'http(s)://IP') (default: None)
            num_retries (int): # of retries if a 5xx error is seen (default: 2)
    """
    print('Downloading:', url)
    headers = {'User-Agent': user_agent}
    rp = get_robots_parser(url)
    if rp.can_fetch(user_agent, url):
        try:
            resp = requests.get(url, headers=headers, proxies=proxies)
            html = resp.text
            if resp.status_code >= 400:
                print('Download error:', resp.text)
                html = None
                if num_retries and 500 <= resp.status_code < 600:
                    # recursively retry 5xx HTTP errors
                    return download(url, num_retries - 1)
            return html
        except requests.exceptions.RequestException as e:
            print('Download error:', e)
            html = None
            return html
    else:
	    print('Blocked by robots.txt:', url)

class Tweet:
    permalink = ""
    time = ""
    content = ""

url = 'https://twitter.com/steam_games'
html = download(url)
tree = fromstring(html)
tweets = tree.cssselect('ol#stream-items-id > li > div.tweet')
for tweet in tweets[0:6]:
    data_link = tweet.get('data-permalink-path')
    time_element = tweet.cssselect('small.time > .tweet-timestamp')[0]
    time = time_element.get('title')
    time2 = time_element.cssselect('._timestamp')[0].get('data-time')
    content_element = tweet.cssselect('div.js-tweet-text-container > p')[0]
    content = content_element.text_content()
    response = Tweet()
    response.permalink = data_link
    response.time = time
    response.content = content
    print(time2)


# td = tree.cssselect('ol#stream-items-id > li > div.tweet')[0]
# tweets = tostring(td, pretty_print=True)
# data_link = td.get('data-permalink-path')
# time_element = td.cssselect('small.time > .tweet-timestamp')[0]
# time = time_element.get('title')
# content_element = td.cssselect('div.js-tweet-text-container > p')[0]
# content = content_element.text_content()
# print(data_link)
# print(content)
# print(time)



