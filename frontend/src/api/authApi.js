import instance from './axiosInstance';

export const apiMyInfo = () => {
    return instance.get('/api/myInfo');
}

export const apiLogin = (memberId, memberPw) => {
    return instance.post('/api/login', { memberId, memberPw });
}

export const apiLogout = () => {
    return instance.post('/api/logout');
}

export const apiRegister = (memberId, memberPw, memberName) => {
    return instance.post('/api/register', { memberId, memberPw, memberName });
}