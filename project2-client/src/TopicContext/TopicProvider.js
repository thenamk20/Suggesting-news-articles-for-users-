import React from 'react'
import { useReducer } from 'react'
import TopicContext from './TopicContext' 
import TopicReducer, {initialState} from './TopicReducer'

function TopicProvider({children}) {

    const [state, dispatch] = useReducer(TopicReducer, initialState)

    return (
        <TopicContext.Provider value = {[state, dispatch]}>
            {children}
        </TopicContext.Provider>
    )
}

export default TopicProvider
