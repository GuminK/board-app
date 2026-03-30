import instance from './axiosInstance';
export const apiGetCommentList = (boardId) => {
    return instance.get(`/api/boards/${boardId}/comments`);
};

export const apiCreateComment = (boardId, data) => {
    return instance.post(`/api/boards/${boardId}/comments`, data);
};

export const apiUpdateComment = (commentId, data) => {
    return instance.put(`/api/comments/${commentId}`, data);
}

export const apiDeleteComment = (commentId) => {
    return instance.delete(`/api/comments/${commentId}`);
}