
export const initialState = {
    topicID : 'all'
}

function TopicReducer(state, topic){
    return {
        topicID: topic
    }
}

export default TopicReducer