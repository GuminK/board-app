import { useNavigate } from 'react-router-dom';
import instance from '../../api/axiosInstance';
import styles from '../../styles/Login.module.css';
import { useState } from 'react';

export default function Login() {

    const navigate = useNavigate();
    const [error, setError] = useState(null);

    async function handleSubmit(e) {
        e.preventDefault();
        const formData = new FormData(e.target);
        const memberId = formData.get('userId');
        const memberPw = formData.get('password');
        try {
            const res = await instance.post('/login', { memberId, memberPw});
            if(res.status === 200){
                alert('로그인에 성공했습니다.');
                navigate('/');
                navigate(0); // 페이지 새로고침
            }
            else {
                alert('로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.');
                setError(res.message);
                navigate('/login');
            }
        } catch (error) {
            const msg = error.response?.data || '로그인 중 오류가 발생했습니다.';
            setError(msg);
        }
    }

    return <div className={styles.div}>
        <h2>로그인</h2>
        <form onSubmit={handleSubmit}>
            <input className={styles.input} type="text" name="userId" placeholder="아이디" />
            <br></br>
            <input className={styles.input} type="password" name="password" placeholder="비밀번호" />
            <br></br>
            <button className={styles.button}type="submit">로그인</button>
            <button className={styles.button} type="button" onClick={() => navigate('/register')}>회원가입</button>
        </form>
        {/* {error && <p className ={styles.error}>{error?.response?.data?.message ?? error.message}</p>} */}
        {error && <p className ={styles.error}>{error ?? error.message}</p>}
        
        </div>;
}