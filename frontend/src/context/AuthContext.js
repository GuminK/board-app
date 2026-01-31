import React, { createContext, useContext, useEffect, useState } from "react";
import { fetchMe, apiLogout } from "../api/authApi";

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
    const [me, setMe] = useState(null);
    const [loading, setLoading] = useState(true);

    const refreshMe = async () => {
        setLoading(true);

        try{
            const res = await fetchMe();
            setMe(res.data);
            return res.data;
        } catch (err) {
            // 401에러 (비로그인) 
            if(err?.response?.status === 401) {
                setMe(null);
            } else{
                // 그 외의 에러
                setMe(null);
                console.warn("[refreshMe] failed", err);
            }
            return null;
        } finally {
            setLoading(false);
        }
    };

    const logout = async () => {
        try {
            await apiLogout();
            setMe(null);
        } catch (err) {
            // 로그아웃 실패
            console.warn("[logout] failed", err);
            setMe(null);
            // await refreshMe(); 서버 상태 재확인 

            throw err;
        }
    };

    useEffect(() => {
        refreshMe();
    }, []);

    return (
        <AuthContext.Provider value={{ me, loading, refreshMe, logout }}>
            {children}  
        </AuthContext.Provider>
    );
}

export function useAuth() {
    const context = useContext(AuthContext);
    if(!context) {
        throw new Error("useAuth must be used within an AuthProvider");
    }
    return context;
}
