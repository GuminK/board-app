// import logo from './logo.svg';
import './App.css';

import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Test from './tests/Test.js';
import Header from './components/layout/Header.js';
import BoardListPage from './pages/board/BoardListPage.js';
import BoardDetailPage from './pages/board/BoardDetailPage.js'
import BoardCreatePage from './pages/board/BoardCreatePage.js';
import BoardUpdatePage from './pages/board/BoardUpdatePage.js';
import LoginPage from './pages/auth/LoginPage.js';
import RegisterPage from './pages/auth/RegisterPage.js';
import MyInfoPage from './pages/auth/MyInfoPage.js';


function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Header />
        <Routes>
          <Route path="/" element={<h1>메인 화면</h1>}></Route>
          <Route path="/board/create" element={<BoardCreatePage />}></Route>
          <Route path="/board" element={<BoardListPage />}></Route>
          <Route path="/board/:id" element={<BoardDetailPage />}></Route>
          <Route path="/test" element={<Test />}></Route>
          <Route path="/board/update/:id" element={<BoardUpdatePage />}></Route>
          <Route path="/login" element={<LoginPage />}></Route>
          <Route path="/register" element={<RegisterPage />}></Route>
          <Route path="/myinfo" element={<MyInfoPage />}></Route>
          <Route path="*" element={<h1>404 Not Found</h1>}></Route>
        </Routes>
        {/* <Footer /> */}
      </BrowserRouter>
    </div>
  );
  
}

export default App;
