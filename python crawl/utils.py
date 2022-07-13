def getCurrentNumberOfArticle():
    urlsFile = open('./24hUrls.txt','r',encoding='utf-8')
    urls = urlsFile.readlines()

    return len(urls)
