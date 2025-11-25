import instance from './axiosInstance';
export const getBoardList = () => {
    return instance.get('/boardList');
};

export const getBoardById = (id) => {
    return instance.get(`/board/${id}`);
}

export const createBoard = (data) => {
    return instance.post('/board', data);
};

export const deleteBoard = (id) => {
    return instance.delete(`/board/${id}`);
}