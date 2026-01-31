import instance from './axiosInstance';

export const fetchMe = () => {
    return instance.get('/myInfo');
}

export const apiLogout = () => {
    return instance.post('/logout');
}