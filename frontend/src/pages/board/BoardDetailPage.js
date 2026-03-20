import { useEffect, useState } from 'react';
import { deleteBoard, getBoardById } from '../../api/boardApi';
import { Link, useNavigate, useParams } from 'react-router-dom';
import dayjs from 'dayjs';

export default function BoardDetailPage() {
    // 게시글 ID 가져오기
    const boardId = useParams().id;

    const [board, setBoard] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchBoardById = async () => {
        try {
            const response = await getBoardById(boardId);
            setBoard(response.data);
            setError(null);
        } catch (error){
            setBoard(null);
            setError("게시글을 불러오지 못했습니다.");
        } finally {
            setLoading(false);
        }
    };
        fetchBoardById();
    }, [boardId]);

    function handleDelete() {
        // 삭제 로직 구현
        if (window.confirm("정말로 이 게시물을 삭제하시겠습니까?")) {
            deleteBoard(boardId)
            .then(() => {
                alert("게시물이 성공적으로 삭제되었습니다.");
                // 삭제 후 메인 페이지로 이동
                navigate('/board');
            })
            .catch((error) => {
                alert("게시물 삭제에 실패했습니다.");
            });
        }
    }

    if (loading){
        return <div>게시물을 불러오는 중입니다.</div>
    }
    
    if(error) return <div>{error}</div>
    
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
                <Link to={`/board/update/${boardId}`}><button>수정</button></Link> 
                <button onClick={handleDelete}>삭제</button>
            </div>
            
        </div>
    </>
    );
}