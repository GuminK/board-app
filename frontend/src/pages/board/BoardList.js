import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { getBoardList } from '../../api/boardApi';
import dayjs from 'dayjs'
import { getMemberById } from '../../api/memberApi';

export default function BoardList() {
    // const [author, setAuthor] = useState(null);

    // const getMemberNameById = async (id) => {
    //     try {
    //         const response = await getMemberById(id);
    //         setAuthor(response.data);
    //     } catch (error) {
    //         console.error("Failed to fetch member By id: ", error);
    //     }
    // }

    const [boardList, setBoardList] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const getBoardLists = async () => {
        try {
            const response = await getBoardList();
            setBoardList(response.data);
            setLoading(false);
        } catch (error) {
            setError(error);
            setLoading(false);
        }
    };

    useEffect(() => {
        getBoardLists();
    }, []);

    return (
        <div className="main-page">
            <main style={{ maxWidth: 800, margin: "24px auto", padding: 16 }}>
                <section className="boardList">
                    <h1>게시판 목록</h1>
                    <Link to ="/board/create">새 글 작성</Link>
                    <br></br>
                    {loading && <p>게시물을 불러오는 중입니다.</p>}
                    {boardList.length === 0 && !loading && <p>게시물이 없습니다.</p>}
                    {error && <p>게시물을 불러오는 중에 오류가 발생했습니다.</p>}
                    {boardList.map((board, index) => (
                        <span key={board.id}>
                            <Link to={`/board/${board.id}`}>
                            제목: {board.title}  
                            조회수: {board.hitCount}  
                            작성일: {dayjs(board.createDate).format('YYYY-MM-DD')} 
                            작성자: {board.memberName}
                            </Link>
                            <br></br>
                        </span>
                    ))}
                </section>
            </main>
        </div>
    );
}       
