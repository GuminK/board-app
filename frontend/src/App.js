import logo from './logo.svg';
import './App.css';

import Test2 from './components/Test2.jsx';
import Time from './components/Time.js';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Test from './tests/Test.js';
import Header from './components/Header.js';
import MainPage from './pages/Mainpage.js';
import Board from './pages/Board.js'


function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Header />
        <Routes>
          <Route path="/" element={<>
            <Test2/>
            <Time/>
        </>}>
          </Route>
          <Route path="/board" element={<MainPage />}>
          </Route>
          <Route path="/board/:id" element={<Board />}>
          </Route>
          <Route path="/test" element={<Test />}>
          </Route>
          <Route path="/time" element={<Time />}>
          </Route>
        </Routes>
        {/* <Footer /> */}
      </BrowserRouter>
    </div>
  );
  
}

export default App;
