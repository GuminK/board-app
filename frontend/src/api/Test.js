import instance from './axiosInstance';

export const getHello = async () => {
    const res = await instance.get("/api/hello");
    // const res = await instance.get("/boardList");
    return res.data;
};
