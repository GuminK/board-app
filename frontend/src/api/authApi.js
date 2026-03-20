import instance from './axiosInstance';

export const apiCsrf = () => {
    return instance.get('/api/auth/csrf');
};

export const apiMyInfo = () => {
    return instance.get('/api/auth/myinfo');
}

export const apiLogin = (memberId, memberPw) => {
    return instance.post('/api/auth/login', { memberId, memberPw });
}

export const apiLogout = () => {
    return instance.post('/api/auth/logout');
}

export const apiRegister = (memberId, memberPw, memberName) => {
    return instance.post('/api/auth/register', { memberId, memberPw, memberName });
}