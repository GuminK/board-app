import dayjs from 'dayjs'
import { useState } from 'react';
import { useAuth } from '../../context/AuthContext';

export default function CommentList({comments, onUpdate, onDelete}) {
    
    const { me } = useAuth();

    const [editingCommentId, setEditingCommentId] = useState(null);
    const [editedContents, setEditedContents] = useState("");

    function handleEditStart(comment) {
        setEditingCommentId(comment.commentId);
        setEditedContents(comment.contents);
    }

    function handleEditCancel() {
        setEditingCommentId(null);
        setEditedContents("");
    }

    async function handleEditUpdate() {
        await onUpdate(editingCommentId, editedContents);
        handleEditCancel();
    }

    if(!comments || comments.length ===0) {
        return <div>아직 댓글이 없습니다.</div>
    }

    return (
        <div style={{whiteSpace : "pre-line"}}>
            {comments.map((comment) => (
                <div key = {comment.commentId}>
                    <div>작성자: {comment.memberName}</div>
                    {(editingCommentId === comment.commentId) ? (
                        <div>
                            <textarea
                                value={editedContents}
                                onChange={(e) => setEditedContents(e.target.value)}
                                rows={4}
                                style={{ width: "100%", boxSizing: "border-box" }}
                            />
                            <br></br>
                            <button onClick={handleEditUpdate}>저장</button>
                            <button onClick={handleEditCancel}>취소</button>
                        </div>
                    ):
                    (
                        <div>{comment.contents}</div>
                    )
                    }
                    <div>{dayjs(comment.createDate).format('YYYY-MM-DD HH:mm')}</div>
                    <hr></hr>
                    {editingCommentId == null && me && me.memberId === comment.memberId && (
                        <div>
                            <button onClick={() => handleEditStart(comment)}>수정</button>
                            <button onClick={() => onDelete(comment.commentId)}>삭제</button>
                        </div>
                    )}            
                </div>
                
            ))}
        </div>
    )
}