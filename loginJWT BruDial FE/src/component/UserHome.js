// src/component/UserHome.js
import React, { useEffect, useState } from 'react';
import axios from '../api/axios';
import { useNavigate } from 'react-router-dom';
import '../css/UserHome.css';

const UserHome = () => {
  const [userDetails, setUserDetails] = useState({});
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchUserDetails = async () => {
      const token = localStorage.getItem('token');
      if (!token) {
        navigate('/user/login');
        return;
      }

      try {
        const response = await axios.get('/user/home', {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        setUserDetails(response.data.data); // Assuming response.data.data contains user details
        setLoading(false); // Set loading to false once data is fetched
      } catch (error) {
        console.error('Failed to fetch user details', error);
        navigate('/user/login');
      }
    };

    fetchUserDetails();
  }, [navigate]);

  const handleLogout = () => {
    localStorage.removeItem('token'); // Remove token from localStorage
    navigate('/user/login'); // Redirect to the login page
  };

  if (loading) {
    return <div>Loading...</div>; // Display loading indicator
  }

  return (
    <div className="user-home-container">
      <h1 className="user-home-header">Welcome {userDetails.firstName} {userDetails.lastName}</h1>
      <div className="user-details">
        <h1>Email: {userDetails.email}</h1>
        <h1>Gender: {userDetails.gender}</h1>
        <h1>Assigned Number: {userDetails.assignedNumber}</h1>
        <h1>Country: {userDetails.country}</h1>
        <img src={userDetails.profilePicture} alt="Profile Picture" />
      </div>
      <button className="user-home-button" onClick={handleLogout}>Logout</button>
    </div>
  );
};

export default UserHome;
