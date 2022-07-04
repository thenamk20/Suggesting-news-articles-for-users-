import './App.css'

import  {BrowserRouter, Routes, Route } from 'react-router-dom'
import MainHeader from './Components/MainHeader'

import  { HomePage, LogIn, SignUp, UploadNewsList
} from './Pages'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element = {<HomePage />}></Route>
        <Route path = '/login' element =  {<LogIn/>}></Route>
        <Route path = '/signup' element =  {<SignUp/>}></Route>
        <Route path='/admin/uploadNewsList' element= {<UploadNewsList></UploadNewsList>}></Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App;

