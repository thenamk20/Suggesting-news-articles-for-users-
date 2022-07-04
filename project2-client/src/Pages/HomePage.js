
import React, { useEffect, useState, useContext } from 'react'
import { TopicContext } from '../TopicContext'
import  NewsItem  from '../Components/NewsItem'
import MainHeader from '../Components/MainHeader'

function HomePage() {

  const [articles, setArticles] = useState([])
  const [state, dispatch] = useContext(TopicContext)
  
  var url = ''

  if(Number.isInteger(state.topicID)){
    url = `http://localhost:8082/api/articles/topic/${state.topicID}`
  }else{
    url = `http://localhost:8082/api/articles/${state.topicID}`
  }

  useEffect(() => {
    fetch(url)
      .then(res => res.json())
      .then(data => {
        if(!data.errors){
          setArticles(data);
        }
        else {
          console.log("failed");
        }
      })
  }, [state])

  console.log('re-render')

  return (
    <>
      <MainHeader></MainHeader>
      <div>
        {articles.map((article) => (
          <NewsItem article = {article} key = {article.id}></NewsItem>
        ))}
      </div>
    </>
  )
}

export default HomePage