import instance from './axiosInstance';

export const getMemberById = (id) => {
    return instance.get(`api/member/${id}`);
}