import logo from './logo.svg';
import './App.css';

import { useEffect, useState } from 'react';
import axios from 'axios';
import Test2 from './components/Test2.jsx';


function App() {
  // const [message, setMessage] = useState("");

  // useEffect(() => {
  //   axios.get("http://localhost:8080/api/hello")
  //   .then((res) => setMessage(res.data))
  //   .catch((err) => console.error(err));  
  // }, []);

  return (
    <div className="App">
      <header className="App-header">
        {/* <h1>Hello</h1>
        <div style={{ textAlign: "center", marginTop: "50px" }}>
          <h1>{message||"로딩 중 ..."}</h1>
        </div> */}
        <Test2></Test2>
      </header>
    </div>
  );
  
}

export default App;
