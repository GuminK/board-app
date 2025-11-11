import axios from "axios";

export const getHello = async () => {
    const res = await axios.get("http://localhost:8080/api/hello");
    return res.data;
};
