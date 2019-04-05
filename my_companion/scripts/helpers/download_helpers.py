import re
from urllib import robotparser
import requests

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
