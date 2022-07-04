import React, {useState} from 'react'
import axios from 'axios';
import {useNavigate} from "react-router-dom"

function LogIn() {

  const navigate = useNavigate();
  const [userName, setUserName] = useState("")
  const [password, setPassword] = useState("")

  var handleSignUp = function() {

  }

  const handleLogin = () => {
    // Create an object of formData
    const formData = new FormData();
  
    // Update the formData object
    formData.append("userName", userName);

    formData.append("password", password)

    // Request made to the backend api
    // Send formData object
    axios.post("http://localhost:8082/api/login", formData)
    .then(reponse => handleLoginResponse(reponse.data))
    .catch(err => {
        console.log(err)
    })
    ;
}

    const handleLoginResponse = (code) => {
      console.log(code);
      if(code === 0){
        navigate("/")
      }else{
        console.log(false);
      }
    }

    const OnGoBack = () => {
      navigate("/")
    }

    

  return (
      <div className="w-[480px] bg-white mx-auto pt-32">
        <div className='w-full px-6 border-solid border-2 border-stone-400 rounded mx-auto flex flex-col items-center'>

          <div className='text-center font-extrabold my-6'>Đăng nhập thành viên</div>

          <div className='flex flex-col py-4 w-4/5'>
            <label htmlFor='username' className='w-full font-medium pb-2'>Username</label>
            <input value={userName} onChange={(e) => setUserName(e.target.value)} id="username" type="text" className='w-full h-10 outline-0' name='username' />
            <div className='h-0.5 bg-sky-400'></div>
          </div>

          <div className='flex flex-col py-4 w-4/5'>
            <label htmlFor='password' className='w-full font-medium pb-2 '>Password</label>
            <input onChange={(e) => setPassword(e.target.value)} id="password" type="password" className='w-full h-10 outline-0 ' name='password' />
            <div className='h-0.5 bg-sky-400'></div>
          </div>

          <div className='flex justify-around w-full'>
            <button
              onClick={() => handleLogin()}
              className='text-xl w-32 bg-sky-400 my-4 h-8 text-zinc-50  rounded 
            hover:border-sky-400 hover:border  hover:bg-white hover:text-sky-400'
            >
              Log in
            </button>

            <button
            onClick={() => handleSignUp()}
              className='text-xl w-32 bg-amber-400 my-4 h-8 text-zinc-50  rounded 
            hover:border-sky-400 hover:border  hover:bg-white hover:text-sky-400'
            >
              Sign up
            </button>
          </div>

          <button onClick={() => OnGoBack()} className='my-4 text-xl w-28 bg-slate-400 rounded '>Go back</button>

        </div>

      </div>
  )
}

export default LogIn