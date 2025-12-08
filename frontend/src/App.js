// import logo from './logo.svg';
import './App.css';

import Time from './components/Time.js';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Test from './tests/Test.js';
import Header from './components/layout/Header.js';
import BoardList from './pages/board/BoardList.js';
import Board from './pages/board/BoardDetail.js'
import BoardCreate from './pages/board/BoardCreate.js';
import BoardUpdate from './pages/board/BoardUpdate.js';
import Login from './pages/login/Login.js';
import Register from './pages/login/Register.js';


function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Header />
        <Routes>
          <Route path="/" element={<>
            <Time/>
        </>}>
          </Route>
          <Route path="/board/new" element={<BoardCreate />}>
          </Route>
          <Route path="/board" element={<BoardList />}>
          </Route>
          <Route path="/board/:id" element={<Board />}>
          </Route>
          <Route path="/test" element={<Test />}>
          </Route>
          <Route path="/time" element={<Time />}>
          </Route>
          <Route path="/board/update/:id" element={<BoardUpdate />}>
          </Route>
          <Route path="/login" element={<Login />}>
          </Route>
          <Route path="/register" element={<Register />}>
          </Route>
        </Routes>
        {/* <Footer /> */}
      </BrowserRouter>
    </div>
  );
  
}

export default App;
