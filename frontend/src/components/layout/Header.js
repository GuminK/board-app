import React from 'react';
import styles from '../../styles/Header.module.css';
import { Link } from 'react-router-dom';

const Header = () => {

    

    return (
        <header className={styles.AppHeader}>
            <div className={styles.headerContainer}>
                <div className={styles.logo}>
                    <h1><Link to="/">My Homepage</Link></h1>
                </div>
                <nav className={styles.navMenu}>
                    <ul>
                        <li><Link to="/board">게시판</Link></li>
                        <li><Link to="/test">Test1</Link></li>
                        <li><Link to="/login">로그인</Link></li>
                        <li><Link to="/contact">Test3</Link></li>
                    </ul>
                </nav>
            </div>
        </header>
    );
};

export default Header;