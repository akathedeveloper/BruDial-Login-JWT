import React from 'react';
import { Link } from 'react-router-dom';
import "../css/LandingPage.css"

const LandingPage = () => {
  return (
    <div className="landing-container">
      <h1 className="landing-header">Welcome</h1>
      <div className="button-container">
        <Link to="/login" className="landing-button">Login</Link>
        <Link to="/signup" className="landing-button">Signup</Link>
      </div>
    </div>
  );
};

export default LandingPage;
