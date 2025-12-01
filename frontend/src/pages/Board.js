import { useEffect, useState } from 'react';
import { getBoardById } from '../api/boardApi';
import { useParams } from 'react-router-dom';

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
            console.log("Fetched board by id:", response);
            setBoard(response.data);
        } catch (error){
            console.error("Failed to fetch board by id:", error);
        }
    };
        fetchBoardById();
    }, [boardId]);
    
    return (<>
        <div>Board Page</div>
        <div>Title: {board.title}</div>
        <div style={{ whiteSpace: "pre-line" }}>Content: {board.contents}</div>
    </>
    );
}