import { useNavigate } from 'react-router-dom';
import styles from '../../styles/Login.module.css'
import { useState } from 'react';
import instance from '../../api/axiosInstance';


export default function Register() {

    const navigate = useNavigate();
    const [error, setError] = useState(null);

    async function handleSubmit(e) {
        e.preventDefault();
        const formData = new FormData(e.target);
        const memberId = formData.get('userId');
        const memberPw = formData.get('password');
        const passwordConfirm = formData.get('passwordConfirm');
        const memberName = formData.get('userName');

        if (memberPw !== passwordConfirm) {
            setError("비밀번호가 일치하지 않습니다.");
            return;
        }


        try {
            const res = await instance.post('/register', { memberId, memberPw, memberName});
            console.log(res.data);
            if(res.data === "register success"){
                alert('회원가입에 성공했습니다.');
                navigate('/');
            }
            else {
                alert('회원가입에 실패했습니다. 아이디와 비밀번호를 확인해주세요.');
                navigate('/register');
                console.log(res);
                setError(res.message);
            }
        } catch (error) {
            setError(error);
        }
    }

    return <div className={styles.div}>
        <h2>회원가입</h2>
        <form onSubmit={handleSubmit}>
            <input className={styles.input} type="text" name="userId" placeholder="아이디" />
            <br></br>
            <input className={styles.input} type="password" name="password" placeholder="비밀번호" />
            <br></br>
            <input className={styles.input} type="password" name="passwordConfirm" placeholder="비밀번호 확인" />
            <br></br>
            <input className={styles.input} type="text" name="userName" placeholder="이름" />
            <br></br>
            {error && <p className={styles.error}>{error}</p>}
            <button className={styles.button}type="submit">회원가입</button>
        </form>
    </div>;
}