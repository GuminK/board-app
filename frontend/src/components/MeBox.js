// 2026-01-31
// context/AuthContext.js 를 추가함으로써 기존 MeBox 필요 없어짐 -> 기존 내용 삭제 후 UserName을 보여줄 예정


import React, {useEffect, useState} from 'react';
import { fetchMe, apiLogout } from '../api/authApi';
import Button from './Button';


export default function MeBox() {
    const [me, setMe] = useState(null);
    const [error, setError] = useState("");

    useEffect(() => {
        fetchMe()
        .then(res => {
            setMe(res.data);
        })
        .catch(err => {
            if (err?.response?.status === 401) {
                setError("로그인이 필요합니다.");
            }
            setMe(null);
        });
    }, []);

    const handleLogout = () => {
        apiLogout()
        .then(() => {
            alert("로그아웃 되었습니다.");
            setMe(null);
        })
        .catch(err => {
            alert("로그아웃 중 오류가 발생했습니다.");
            setError("로그아웃 중 오류가 발생했습니다.");
        });
    };

    if (error) return <p>{error}</p>
    if (!me) return null;

    return (<div>
        <Button label="로그아웃" onClick={handleLogout} />
    </div>);
}