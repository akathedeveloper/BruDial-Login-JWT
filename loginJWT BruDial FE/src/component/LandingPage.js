import React from 'react';
import { Link } from 'react-router-dom';
import "../css/LandingPage.css"

const LandingPage = () => {
  return (
    <div className="landing-container">
      <h1 className="landing-header">Welcome To BruDial</h1>
      <div className="button-container">
        <Link to="/user/login" className="landing-button">Login</Link>
      </div>
    </div>
  );
};

export default LandingPage;
