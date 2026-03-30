import dayjs from 'dayjs'
import { apiDeleteComment, apiUpdateComment } from '../../api/commentApi'

export default function CommentList({comments, onUpdate, onDelete}) {

    if(!comments || comments.length ===0) {
        return <div>아직 댓글이 없습니다.</div>
    }

    return (
        <div>
            {comments.map((comment) => (
                <div 
                key = {comment.commentId}
                >
                    <div>
                        작성자: {comment.memberName}
                    </div>
                    <div>
                        {comment.contents}
                    </div>
                    <div>
                        {dayjs(comment.createDate).format('YYYY-MM-DD HH:mm')}
                    </div>
                    <hr></hr>
                    <div>
                        {/* 수정을 위한 Textarea 추가해야함 */}
                        <button onClick={() => onUpdate(comment.commentId, comment.contents)}>수정</button>
                        <button onClick={() => onDelete(comment.commentId)}>삭제</button>
                    </div>
                </div>
                
            ))}
        </div>
    )
}