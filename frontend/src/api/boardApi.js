import instance from './axiosInstance';
export const getBoardList = () => {
    return instance.get('/api/boards');
};

export const getBoardDetail = (id) => {
    return instance.get(`/api/boards/${id}`);
}

export const increaseHitCount = (id) => {
    return instance.post(`/api/boards/${id}/hit`);
}

export const createBoard = (data) => {
    return instance.post('/api/boards', data);
};

export const updateBoard = (id, data) => {
    return instance.put(`/api/boards/${id}`, data);
}

export const deleteBoard = (id) => {
    return instance.delete(`/api/boards/${id}`);
}