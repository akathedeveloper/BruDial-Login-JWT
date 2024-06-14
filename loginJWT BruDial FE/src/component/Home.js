import React from 'react';
import { Link } from 'react-router-dom';
import '../css/Home.css'; // Import the CSS file

const Home = () => {
  return (
    <div className="home-container">
      <h1 className="home-header">Welcome to the Home Page</h1>
      <Link to="/logout" className="logout-link">Logout</Link> {/* Logout link */}
    </div>
  );
};

export default Home;
