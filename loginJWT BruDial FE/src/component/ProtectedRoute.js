import React from 'react';
import { Navigate } from 'react-router-dom';

const ProtectedRoute = ({ children }) => {
  const token = localStorage.getItem('token');
  console.log('ProtectedRoute token:', token); // Debug log

  if (!token) {
    console.log('No token found, redirecting to login'); // Debug log
    return <Navigate to="/login" />;
  }

  return children;
};

export default ProtectedRoute;
