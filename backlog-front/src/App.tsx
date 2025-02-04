import { useState } from 'react'
import { BrowserRouter, Routes, Route } from "react-router-dom";
import './App.css'
import SearchPage from "./components/Search"
function App() {
  const [count, setCount] = useState(0)

  return (
    <BrowserRouter>
      <Routes>
        < Route path="/search" element= {<SearchPage />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
