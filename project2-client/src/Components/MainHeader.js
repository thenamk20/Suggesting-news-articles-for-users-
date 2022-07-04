import React, {useContext, useState, useEffect} from 'react'
import { Link, Outlet } from 'react-router-dom'

import {TopicContext} from '../TopicContext'
import ListTopics from '../common/topics'

function MainHeader() {

  const [state, dispatch] = useContext(TopicContext)

  const [topics, setTopics] = useState([])

  const url = "http://localhost:8082/api/topics/all"

  useEffect(() => {

    fetch(url)
      .then(res => res.json())
      .then(data => {
        if(!data.errors){
          console.log('set header ')
          setTopics(data)
        }
        else {
          console.log("failed");
        }
      })
  }, [])

  return (
    <header className='bg-cyan-400  text-white'>
      <nav className='flex max-w-6xl w-4/5 mx-auto'>

        <div className='inline-block w-1/12'>thenam</div>

        <ul className='flex h-12 bg-cyan-400 w-11/12'>
          
          <li className='my-auto px-4' 
            onClick={() => {dispatch('all')}}
          ><Link to = '/'>Home</Link></li>
         
          {topics.map((topicItem) => (
            <li 
              className='my-auto px-4 hover:cursor-pointer' key={topicItem.id}
              onClick={()=>{dispatch(topicItem.id)}}
            >
              {ListTopics[topicItem.id]}
            </li>
          ))}

          <li className='my-auto px-4'><Link to = '/login'>Login</Link></li>
          <li className='my-auto px-4'><Link to = '/admin/uploadNewsList'>Upload</Link></li>
        </ul>
      </nav>
      <Outlet></Outlet>
    </header>
  )
}

export default MainHeader