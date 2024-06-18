import React, { useState } from 'react';
import axios from '../api/axios';
import { useNavigate } from 'react-router-dom';
import '../css/AdminRegister.css';

const AdminRegister = () => {
  const [userData, setUserData] = useState({
    email: '',
    password: '',
    firstName: '',
    lastName: '',
    gender: '',
    country: ''
  });
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem('adminToken'); // Remove admin token from local storage
    navigate('/admin/login'); // Redirect to admin login page
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUserData({ ...userData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const token = localStorage.getItem('adminToken');
    if (!token) {
      navigate('/admin/login');
      return;
    }

    try {
      await axios.post('/admin/register', userData, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      navigate('/admin/login'); // Redirect to admin login page after registration
    } catch (error) {
      console.error('User registration failed', error);
    }
  };

  return (
    <div className="signup-container">
      <h1>Admin Register User</h1>
      <button className="logout-btn" onClick={handleLogout}>Logout</button> {/* Logout button */}
      <form className="signup-form" onSubmit={handleSubmit}>
        <input
          type="text"
          name="firstName"
          value={userData.firstName}
          onChange={handleChange}
          placeholder="First Name"
          required
        />
        <input
          type="text"
          name="lastName"
          value={userData.lastName}
          onChange={handleChange}
          placeholder="Last Name"
          required
        />
        <input
          type="email"
          name="email"
          value={userData.email}
          onChange={handleChange}
          placeholder="Email"
          required
        />
        <input
          type="password"
          name="password"
          value={userData.password}
          onChange={handleChange}
          placeholder="Password"
          required
        />
        <select
          name="gender"
          value={userData.gender}
          onChange={handleChange}
          required
        >
          <option value="">Select Gender</option>
          <option value="Male">Male</option>
          <option value="Female">Female</option>
          <option value="Other">Other</option>
        </select>

        <input
          type="text"
          name="country"
          value={userData.country}
          onChange={handleChange}
          placeholder="Country"
          required
        />
        <button type="submit">Register User</button>
      </form>
    </div>
  );
};

export default AdminRegister;
