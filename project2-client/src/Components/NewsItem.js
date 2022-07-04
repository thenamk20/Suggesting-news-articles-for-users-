import React from 'react'

function NewsItem({ article }) {
  return (
    <article className='w-4/5 max-w-6xl mx-auto my-4 border border-cyan-400 shadow-md shadow-cyan-200'>
      <a className='block' href= {article.link} target = "_blank" rel='noreferrer'>
            <div className='w-full flex p-4'>
              <div className='w-3/4 mr-2'>
                <h2 className='text-xl font-semibold'>{article.title}</h2>
                <p className='line-clamp-3'>{article.subContent}</p>
              </div>
              <div className='w-1/4'>
                <img className='object-contain max-h-48' src={article.srcImage} alt= {`${article.title}`} />
              </div>
            </div>
        </a>
    </article>
  )
}

export default NewsItem