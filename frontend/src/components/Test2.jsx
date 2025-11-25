import { useEffect, useState } from "react";
import { getHello } from '../api/Test';

function Test2() {
    const [message, setMessage] = useState("");

    useEffect(() => {
    // getHello().then(setMessage);
    getHello().then(res => setMessage(res));
    }, []);

    return <div>{message||"로딩 중..."}</div>;
}

export default Test2;
