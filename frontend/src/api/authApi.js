import instance from './axiosInstance';

export const fetchMe = () => {
    return instance.get('/me');
}

export const apiLogout = () => {
    return instance.post('/logout');
}