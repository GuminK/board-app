import dayjs from 'dayjs'

const CommentList = ({ comments}) => {
    if(!comments || comments.length ===0) {
        return <div>아직 댓글이 없습니다.</div>
    }

    return (
        <div>
            {comments.map((comment, index) => (
                <div 
                key = {index}
                >
                    <div>
                        작성자: {comment.memberName}
                    </div>
                    <div>
                        {comment.contents}
                    </div>
                    <div>
                        {dayjs(comment.creatDate).format('YYYY-MM-DD HH:mm')}
                    </div>
                </div>
                
            ))}
        </div>
    )
}

export default CommentList;