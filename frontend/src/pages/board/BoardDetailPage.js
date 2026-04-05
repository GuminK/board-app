import { useCallback, useEffect, useState } from 'react';
import { useAuth } from '../../context/AuthContext';
import { deleteBoard, getBoardDetail, increaseHitCount } from '../../api/boardApi';
import { apiGetCommentList, apiCreateComment, apiUpdateComment, apiDeleteComment } from '../../api/commentApi';
import { Link, useNavigate, useParams } from 'react-router-dom';
import CommentList from '../../components/comment/CommentList';
import dayjs from 'dayjs';
import CommentForm from '../../components/comment/CommentForm';

export default function BoardDetailPage() {
    // 게시글 ID 가져오기
    const boardId = useParams().id;

    const [board, setBoard] = useState(null);
    const [commentList, setCommentList] = useState([]);
    const { me } = useAuth();
    const isAuthor = me && board && me.memberId === board.memberId;
    

    const [loading, setLoading] = useState(true);
    const [commentSubmitting, setCommentSubmitting] = useState(false);
    const [boardError, setBoardError] = useState(null);
    const [commentError, setCommentError] = useState(null);

    const navigate = useNavigate();

    const fetchCommentList = useCallback(async () => {
            try {
                const commentResponse = await apiGetCommentList(boardId);
                setCommentList(commentResponse.data);
            } catch (error) {
                setCommentError("댓글을 불러오는 중에 오류가 발생했습니다.");
            }
        }, [boardId]);

    useEffect(() => {
        const fetchBoardById = async () => {
            try{
                await increaseHitCount(boardId); // 조회수 증가 API 호출
            } catch (error) {
                console.error("Failed to increase hit count:", error);
            }

            try {
                const response = await getBoardDetail(boardId); // 게시글 내용 불러오기 API 호출
                setBoard(response.data);
                setBoardError(null);
            } catch (error){
                setBoard(null);
                setBoardError("게시글을 불러오지 못했습니다.");
            } finally {
                setLoading(false);
            }
        };

        fetchBoardById();
        fetchCommentList();
    }, [boardId, fetchCommentList]);

    async function handleUpdateComment(commentId, contents) {
        try {
            await apiUpdateComment(commentId, {contents});
            await fetchCommentList();
        } catch(error) {
            setCommentError("댓글 수정에 실패했습니다. ");
            throw error;
        }
    }

    async function handleDeleteComment(commentId) {
        try {
            await apiDeleteComment(commentId);
            await fetchCommentList();
        } catch (error) {
            setCommentError("댓글 삭제에 실패했습니다. ");
        }
    }


    async function handleDeleteBoard() {
        // 삭제 로직 구현
        if (window.confirm("정말로 이 게시물을 삭제하시겠습니까?")) {
            try{
                await deleteBoard(boardId);
                alert("게시물이 성공적으로 삭제되었습니다.");
                navigate('/board');
            } catch (error){
                alert("게시물 삭제에 실패했습니다.");
            } 
        }
    }

    async function handleCreateComment(contents) {
        setCommentSubmitting(true);
        setCommentError(null);
        try {
            await apiCreateComment(boardId, {contents});
            await fetchCommentList();
        } catch (error) {
            setCommentError("댓글 작성에 실패했습니다.");
            throw error;
        } finally {
            setCommentSubmitting(false);
        }
    }
    
    if (loading){
        return <div>게시물을 불러오는 중입니다.</div>
    }
    
    if(boardError) return <div>{boardError}</div>
    
    if (!board) {
        return <div>게시글이 없습니다.</div>
    }

    
    return (<>
        <div style={{ maxWidth: 800, margin: "24px auto", padding: 16 }}>
            {/* 제목 조회수 화면에 표시 */}
            <div style={{ marginBottom: 12 }}>
                <span style= {{fontSize:20}}>제목: {board.title} </span>
                <span style={{ marginLeft: 12 }}> 작성일: {dayjs(board.createDate).format('YYYY-MM-DD HH:mm')}</span>
                <span style={{ marginLeft: 12 }}> 조회수: {board.hitCount}</span>
                <span style={{ marginLeft: 6 }}> 작성자: {board.memberName}</span>
            </div>
            <hr></hr>
            <div style={{ whiteSpace: "pre-line" }}> {board.contents}</div>
            <br></br>
            <hr></hr>
            {/* 수정 삭제 버튼 */}
            <div>
                {isAuthor && <Link to={`/board/update/${boardId}`}><button>게시글 수정</button></Link> }
                {isAuthor && <button onClick={handleDeleteBoard}>게시글 삭제</button>}
            </div>

            <CommentList comments={commentList} onUpdate={handleUpdateComment} onDelete={handleDeleteComment}></CommentList>
            <br></br>
            <CommentForm onSubmit={handleCreateComment} loading={commentSubmitting}></CommentForm>
            {commentError && <div style={{ color: "red" }}>{commentError}</div>}
        </div>
    </>
    );
}