// src/App.js
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LandingPage from './component/LandingPage';
import UserLogin from './component/UserLogin';
import AdminLogin from './component/AdminLogin';
import AdminRegister from './component/AdminRegister';
import Logout from './component/Logout';
import ProtectedRoute from './component/ProtectedRoute';
import AdminProtectedRoute from './component/AdminProtectedRoute';
import UserHome from './component/UserHome';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LandingPage />} />
        <Route path="/user/login" element={<UserLogin />} />
        <Route path="/admin/login" element={<AdminLogin />} />
        <Route path="/logout" element={<Logout />} />
        <Route path="/user/home" element={
          <ProtectedRoute>
            <UserHome />
          </ProtectedRoute>
        } />
        <Route path="/admin/register" element={
          <AdminProtectedRoute>
            <AdminRegister />
          </AdminProtectedRoute>
        } />
      </Routes>
    </Router>
  );
};

export default App;
