import { useEffect, useState } from 'react';
import { deleteBoard, getBoardById } from '../api/boardApi';
import { Link, useParams } from 'react-router-dom';

export default function Board() {
    const boardId = useParams().id;

    const [board, setBoard] = useState({});

    // const fetchBoardById = async () => {
    //     try {
    //         const response = await getBoardById(boardId);
    //         console.log("Fetched board by id:", response);
    //         setBoard(response.data);
    //     } catch (error){
    //         console.error("Failed to fetch board by id:", error);
    //     }
    // };

    useEffect(() => {
        const fetchBoardById = async () => {
        try {
            const response = await getBoardById(boardId);
            setBoard(response.data);
        } catch (error){
            console.error("Failed to fetch board by id:", error);
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
                window.location.href = "/board";
            })
            .catch((error) => {
                console.error("Failed to delete board:", error);
                alert("게시물 삭제에 실패했습니다.");
            });
        }
    }
    
    return (<>
        <div style={{ maxWidth: 800, margin: "24px auto", padding: 16 }}>
        <div style={{ marginBottom: 12 }}>제목: {board.title}  조회수: {board.hitCount}</div>
        <hr></hr>
        <div style={{ whiteSpace: "pre-line" }}> {board.contents}</div>
        <br></br>
        <hr></hr>
        <Link to={`/board/update/${boardId}`}>수정</Link> <button onClick={handleDelete}>삭제</button>
        </div>
    </>
    );
}