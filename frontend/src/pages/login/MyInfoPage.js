import { useAuth } from '../../context/AuthContext';

export default function MyInfoPage() {

    const {me, loading} = useAuth();



    return <div>
        <h2>내 정보 페이지</h2>
        <p>이곳은 내 정보 페이지입니다.</p>
        {me && <p>로그인된 사용자 이름: {me.memberName}</p>}
        {me && <p>역할: {(me.roles.includes("ROLE_USER"))? "일반 유저" : "관리자"}</p>}

    </div>;
}