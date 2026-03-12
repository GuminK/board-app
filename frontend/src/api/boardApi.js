import instance from './axiosInstance';
export const getBoardList = () => {
    return instance.get('/board/list');
};

export const getBoardById = (id) => {
    return instance.get(`/board/detail/${id}`);
}

export const createBoard = (data) => {
    return instance.post('/board/create', data);
};

export const updateBoard = (id, data) => {
    return instance.put(`/board/update/${id}`, data);
}

export const deleteBoard = (id) => {
    return instance.delete(`/board/delete/${id}`);
}