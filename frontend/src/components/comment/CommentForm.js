import { useState } from 'react';

export default function CommentForm({onSubmit, loading}) {
    const [contents, setContents] = useState("");
    const [error, setError] = useState(null);

    const handleSubmit = async (e) => {
        e.preventDefault();

        if(!contents.trim()){
            setError("댓글 내용을 입력해주세요. ");
            return;
        }
        setError(null);
        try {
            await onSubmit(contents);
            setContents("");
        } catch (error) {
        }
    };

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <textarea 
                    value={contents}
                    onChange={(e) => setContents(e.target.value)}
                    rows={4}
                    placeholder="댓글을 입력하세요."
                    disabled={loading}
                />
                {error && (
                    <div style={{ color: "red" }}>{error}</div>
                )}
                <br></br>
                <button
                    type="submit"
                    disabled={loading}
                >
                    {loading ? "댓글 작성 중. . ." : "댓글 작성"}
                </button>

            </form>
        </div>
    )
}