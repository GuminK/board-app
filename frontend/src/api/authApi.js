import instance from './axiosInstance';

export const fetchMe = () => {
    return instance.get('/me');
}

export const logout = () => {
    return instance.post('/logout');
}