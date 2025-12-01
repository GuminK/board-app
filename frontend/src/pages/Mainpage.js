import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { getBoardList } from '../api/boardApi';
import dayjs from 'dayjs'

export default function MainPage() {
    const [boardList, setBoardList] = useState([]);

    const getBoardLists = async () => {
        try {
            const response = await getBoardList();
            setBoardList(response.data);
        } catch (error) {
            console.error("Failed to fetch board list:", error);
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
                    <Link to ="/board/new">새 글 작성</Link>
                    <br></br>
                    {boardList.map((board, index) => (
                        <span key={board.id}>
                            <Link to={`/board/${board.id}`}>제목: {board.title} 조회수: {board.hitCount} 작성일: {dayjs(board.createDate).format('YYYY-MM-DD')}</Link>
                            <br></br>
                        </span>
                    ))}
                </section>
            </main>
        </div>
    );
}       
