import { createFileRoute } from '@tanstack/react-router'
import axios from 'axios';
import React, { useState } from 'react'
import "../css/userForms.css"
const url = import.meta.env.VITE_BACKEND_URL


export const Route = createFileRoute('/register')({
  component: Register,
})

function Register() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [username, setUsername] = useState('')

  function registerUser(event: React.FormEvent) {
    event.preventDefault()
    return axios
    .post(`${url}/user/register`, {
      email: email,
      password: password,
      confirmPassword: confirmPassword,
      username: username,
    })
  }
  return (
    <>
      <form className='formContainer'>
            <label className='Label'>Username:</label>
            <input  onChange={(e): void =>setUsername(e.target.value)} type="username" placeholder='Enter username'/>
            <label className='Label'>Email:</label>
            <input onChange={(e): void =>setEmail(e.target.value)} type='email' placeholder='Enter Email'/>
            <label className='Label'>Password:</label>
            <input onChange={(e): void =>setPassword(e.target.value)}  type='password' placeholder='Enter Password' />
            <label className='Label'>Confirm Password:</label>
            <input onChange={(e): void =>setConfirmPassword(e.target.value)}  type='password' placeholder='Confirm Password' />
            <button onClick={registerUser} type='submit' >Register</button>
      </form>
    </>
  )
}
