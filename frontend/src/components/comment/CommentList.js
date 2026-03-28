import dayjs from 'dayjs'

const CommentList = ({ comments}) => {
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
                </div>
                
            ))}
        </div>
    )
}

export default CommentList;