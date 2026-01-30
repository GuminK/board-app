import React, {useEffect, useState} from 'react';
import { fetchMe, logout } from '../api/authApi';
import { Link } from 'react-router-dom';
import Button from './Button';


export default function MeBox() {
    const [me, setMe] = useState(null);
    const [error, setError] = useState("");

    // TODO: get으로 /me 호출해서 사용자 정보 불러오기 해야함. / 2026-01-29

    // fetchMe();

    useEffect(() => {
        fetchMe()
        .then(res => {
            setMe(res.data);
            console.log(res.data);
        })
        .catch(err => {
            if (err?.response?.status === 401) {
                setError("로그인이 필요합니다.");
            }
        });
    }, []);

    const handleLogout = () => {
        logout()
        .then(() => {
            alert("로그아웃 되었습니다.");
            setMe(null);
        })
        .catch(err => {
            setError("로그아웃 중 오류가 발생했습니다.");
        });
    };

    if (error) return <p>{error}</p>
    if (!me) return <p>Loading. . .</p>

    return (<div>
        <div>memberId : {me.memberId}</div>
        <div>memberName : {me.memberName}</div>
        <div>roles: {Array.isArray(me.roles) ? me.roles.join(', ') : String(me.roles)}</div>
        <Button label="로그아웃" onClick={handleLogout} />
        <div>{error}</div>
    </div>);
}