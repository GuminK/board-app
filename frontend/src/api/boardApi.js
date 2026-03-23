import instance from './axiosInstance';
export const getBoardList = () => {
    return instance.get('/api/board/list');
};

export const getBoardDetail = (id) => {
    return instance.get(`/api/board/detail/${id}`);
}

export const increaseHitCount = (id) => {
    return instance.post(`/api/board/${id}/hit`);
}

export const createBoard = (data) => {
    return instance.post('/api/board/create', data);
};

export const updateBoard = (data) => {
    return instance.put(`/api/board/update`, data);
}

export const deleteBoard = (id) => {
    return instance.delete(`/api/board/delete/${id}`);
}