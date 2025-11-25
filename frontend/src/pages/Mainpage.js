import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { getBoardList } from '../api/boardApi';

export default function MainPage() {
    const [boardList, setBoardList] = useState([]);

    const getBoardLists = async () => {
        try {
            const response = await getBoardList();
            console.log("Fetched board list:", response);
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
            <main>
                <section className="boardList">
                    <h1>Testpage boardList</h1>
                    {boardList.map((board, index) => (
                        <span key={board.id}>
                            <Link to={`/board/${board.id}`}>제목: {board.title} 조회수: {board.hitCount}</Link>
                            <br></br>
                        </span>
                    ))}
                </section>
            </main>
        </div>
    );
}       
