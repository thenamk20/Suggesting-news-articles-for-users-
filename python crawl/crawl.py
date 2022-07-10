import requests
from bs4 import BeautifulSoup
import codecs
from utils import getCurrentNumberOfArticle

headers = {
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods': 'GET',
    'Access-Control-Allow-Headers': 'Content-Type',
    'Access-Control-Max-Age': '3600',
    'User-Agent': 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:52.0) Gecko/20100101 Firefox/52.0'
    }

url24h = "https://www.24h.com.vn"
urlVnexpress = "https://vnexpress.net"
urlDantri = "https://dantri.com.vn"
urlYoutube = "https://www.youtube.com"

maxLevelOfRecursion = 3

listUrl = [] #all urls from news website
articleUrls = [] ## urls from articles only
currentArticleNumber = getCurrentNumberOfArticle()

## get text in a article
def getArticleText(url, className):
    
    req = requests.get(url, headers)
    soup = BeautifulSoup(req.content, 'html.parser')
    pElements = soup.find_all("p")
    h1Elements = soup.find_all("h1")
    h2Elements = soup.find_all("h2")

    titleEles = soup.find_all("h1", id = className)
    
    if(len(titleEles) >= 1):
       
        print("save file:" + titleEles[0].text)
        fileName = "./24hData/post%s.txt" % str(len(articleUrls) + currentArticleNumber)
        f = codecs.open(fileName, "a","utf-8")
        for e in h1Elements:
            f.write(e.text + " ")

        for e in h2Elements:
            f.write(e.text + " ")

        for e in pElements:
            f.write(e.text + " ")

        f.close() 

        articleUrls.append(url)
        
        urlsFile = codecs.open("./24hUrls.txt", "a","utf-8")
        urlsFile.write(url + '\n')
        urlsFile.close()
 

## traversal urls in news website using recursion
def getNewsUrlFromUrl(url, mainUrl, maxUrlLevel):
    if(maxUrlLevel > 0):
        req = requests.get(url, headers)
        soup = BeautifulSoup(req.content, 'html.parser')

        ## save text data of this article
        getArticleText(url, "article_title")

        ## find all link in this article
        urls = soup.find_all('a')
        for item in urls:
            if(item.has_attr("href")):
                href = item['href']

                # is a local url?
                if(href.find(mainUrl) == -1 and (len(href) > 5) and href[0] == "/" and href.find("html") != -1):
                    item['href'] = url + item['href']

                if(item['href'].find(mainUrl) != -1):
                    
                    if(listUrl.count(item['href']) == 0):
                        # print(item['href'])
                        listUrl.append(item['href'])
                        # linksFile.write(item['href'] + '\n')
                        getNewsUrlFromUrl(item['href'], mainUrl ,maxUrlLevel-1)
              
    else:
        return

getNewsUrlFromUrl(url24h, url24h, maxLevelOfRecursion)

