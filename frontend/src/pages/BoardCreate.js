import React, { useState } from "react";
import instance from '../api/axiosInstance';

export default function BoardRegister() {
    const [form, setForm] = useState({
        title: "",
        content: "",
    });
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    function handleChange(e) {
        const { name, value } = e.target;
        setForm((prev) => ({ ...prev, [name]: value }));
    }


    // 등록 버튼 클릭 시 호출   
    async function handleSubmit(e) {
        // 기본 폼 제출 동작 방지
        e.preventDefault();
        setError(null);

        if (!form.title.trim() || !form.content.trim()) {
            setError("입력하지 않은 칸이 있습니다.");
            return;
        }

        setLoading(true);
        try {
            const res = await instance.post("/board/create", {
                title: form.title,
                contents: form.content,
            });

            // 성공 시 폼 초기화 및 알림
            setForm({ title: "", content: ""});
            alert("게시물이 성공적으로 등록되었습니다.");
            // 게시판 목록 페이지로 이동
            window.location.href = "/board";
        } catch (err) {
            setError(err.message || "게시물 등록에 실패했습니다.");
        } finally {
            setLoading(false);
        }
    }

    return (
        <div style={{ maxWidth: 800, margin: "24px auto", padding: 16 }}>
            <h2>Create Board</h2>
            <form onSubmit={handleSubmit}>
                <Input 
                    label="제목"
                    name="title"
                    value={form.title}
                    onChange={handleChange}
                    disabled={loading}
                />

                <TextArea 
                    label="내용"
                    name="content"
                    value={form.content}
                    onChange={handleChange}
                    rows={8}
                    style={{ width: "100%", padding: 8 }}
                    disabled={loading}
                />

                {error && (
                    <div style={{ color: "red", marginBottom: 12 }}>{error}</div>
                )}

                <button type="submit" disabled={loading} style={{ padding: "8px 16px" }}>
                    {loading ? "저장 중..." : "저장"}
                </button>
            </form>
        </div>
    );
}

function Input({label, ...props}){
    return (
        <div style={{ marginBottom: 12 }}>
            <label>
                {label}
                <br />
                <input {...props} style={{ width: "100%", padding: 8 }} />
            </label>
        </div>
    )
}

function TextArea({label, ...props}){
    return (
        <div style={{ marginBottom: 12 }}>
            <label>
                {label}
                <br />
                <textarea {...props} style={{ width: "100%", padding: 8 }} />
            </label>
        </div>
    )
}