import instance from './axiosInstance';
export const apiGetCommentList = (boardId) => {
    return instance.get(`/api/boards/${boardId}/comments`);
};

export const createComment = (boardId, data) => {
    return instance.post(`/api/board/${boardId}/comments`, data);
};