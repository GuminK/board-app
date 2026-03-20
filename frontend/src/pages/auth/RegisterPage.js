import { useNavigate } from 'react-router-dom';
import styles from '../../styles/Login.module.css'
import { useState } from 'react';
import { apiCsrf, apiRegister } from '../../api/authApi';


export default function RegisterPage() {

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
            await apiCsrf();

            const res = await apiRegister(memberId, memberPw, memberName);
            
            if(res.status === 201 && res.data.message === "register success"){
                alert('회원가입에 성공했습니다.');
                navigate('/login');
                return;
            }
            setError("예상치 못한 응답입니다.");
        } catch (error) {
            const status = error.response?.status;
            const message = error.response?.data?.message;

            if(status === 409) {
                setError("이미 존재하는 아이디입니다.");
                return;
            }
            
            setError(message ?? "회원가입 중 오류가 발생했습니다.");
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