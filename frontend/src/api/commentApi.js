import instance from './axiosInstance';
export const getCommentList = (boardId) => {
    return instance.get(`/api/boards/${boardId}/comments`);
};

export const createComment = (boardId, data) => {
    return instance.post(`/api/board/${boardId}/comments`, data);
};