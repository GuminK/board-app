import instance from './axiosInstance';

export const fetchMyInfo = () => {
    return instance.get('/myInfo');
}

export const apiLogout = () => {
    return instance.post('/logout');
}