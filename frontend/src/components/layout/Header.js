import React, { useState } from 'react';
import styles from '../../styles/Header.module.css';
import { Link } from 'react-router-dom';
import MeBox from '../MeBox';
import { useAuth } from '../../context/AuthContext';
import { apiLogout } from '../../api/authApi';

const Header = () => {
    const {me, logout, loading} = useAuth();
    const [isLogout, setIsLogout] = useState(false);

    const handleLogout = async () => {
        if(isLogout) return;

        try{
            await logout();
            setIsLogout(true);
            alert("로그아웃 되었습니다.");
        } catch (err) {
            alert("로그아웃 중 오류가 발생했습니다.");
        } finally {
            setIsLogout(false);
        }
    };

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

                        {/* ✅ 로딩 끝났고, me 없을 때만 로그인 버튼 보여줌 */} 
                        {!loading && !me && <li><Link to="/login">로그인</Link></li>}
                        {!loading && me && <li><button type="button" onClick={handleLogout} disabled={isLogout}>로그아웃</button></li>}
                        <li><Link to="/contact">Test3</Link></li>
                        {/* <li><MeBox /></li> */}
                    </ul>
                </nav>
            </div>
        </header>
    );
};

export default Header;