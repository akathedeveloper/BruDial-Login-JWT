import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LandingPage from './component/LandingPage';
import Login from './component/Login';
import Logout from './component/Logout';
import ProtectedRoute from './component/ProtectedRoute';
import Signup from './component/Signup';
import Home from './component/Home';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/logout" element={<Logout />} />
        <Route
          path="/home"
          element={
            <ProtectedRoute>
              <Home />
            </ProtectedRoute>
          }
        />
        <Route path="/" element={<LandingPage />} /> {/* Set LandingPage as the default route */}
        {/* Add other routes here */}
      </Routes>
    </Router>
  );
};

export default App;
