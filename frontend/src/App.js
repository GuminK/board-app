import logo from './logo.svg';
import './App.css';

import { useEffect, useState } from 'react';
import axios from 'axios';
import Test2 from './components/Test2.jsx';
import Time from './components/Time.js';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Test from './tests/Test.js';
import Header from './components/Header.js';


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
          <Route path="/test" element={<Test />}>
          </Route>
          <Route path="/time" element={<Time />}>
          </Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
  
}

export default App;
