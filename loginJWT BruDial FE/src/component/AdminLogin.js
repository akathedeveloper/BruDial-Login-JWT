// src/component/AdminLogin.js
import React, { useState } from 'react';
import axios from '../api/axios';
import { useNavigate } from 'react-router-dom';
import '../css/Login.css';
import image from './Login.png';

const AdminLogin = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('/admin/login', { email, password });
      const token = response.data.token;
      localStorage.setItem('adminToken', token);
      navigate('/admin/register');
    } catch (error) {
      console.error('Login failed', error);
    }
  };

  return (
    <div className="login-page">
      <div className="circle top-right"></div>
      <div className="circle bottom-right"></div>
      <div className="login-container">
        <div className="login-left">
          <div className="logo">
            <div className="square"></div>
            <h3>Admin Portal</h3>
          </div>
          <div className='welcome-text'>
            <h2>Welcome Admin</h2>
          </div>
          <div className='welcome-para'>
            <p>Please login with your admin credentials</p>
          </div>
          <form className="login-form" onSubmit={handleSubmit}>
            <div className="form-group">
              <label htmlFor="email">Email</label>
              <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} id="email" name="email" required />
            </div>
            <div className="form-group">
              <label htmlFor="password">Password</label>
              <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} id="password" name="password" required />
            </div>
            <button type="submit" className="login-button">Login</button>
          </form>
        </div>
        <div className="login-right">
          <img src={image} alt="Login Illustration" />
        </div>
      </div>
    </div>
  );
};

export default AdminLogin;
