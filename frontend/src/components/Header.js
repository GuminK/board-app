import React from 'react';
import '../styles/Header.css'
import { Link } from 'react-router-dom';

const Header = () => {
    return (
        <header className="App-header">
            <div className="header-container">
                <div className="logo">
                    <h1><Link to="/">My App</Link></h1>
                </div>
                <nav className="nav-menu">
                    <ul>
                        <li><Link to="/">Home</Link></li>
                        <li><Link to="/test">Test</Link></li>
                        <li><Link to="/services">Services</Link></li>
                        <li><Link to="/contact">Contact</Link></li>
                    </ul>
                </nav>
            </div>
        </header>
    );
};

export default Header;