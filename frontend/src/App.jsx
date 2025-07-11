import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import Login from "./component/Login";

import {createBrowserRouter , RouterProvider} from "react-router-dom"
import Home from "./pages/Home";
import SignUp from "./component/SignUp"; 
import Library from "./pages/Library"
import Books from "./pages/Books"
import Read from "./pages/Read";
import Account from "./pages/Account";


const router = createBrowserRouter(
  [
    {
      path:"/",
      element:
      <Home/>
    },
    {
      path:"/login",
      element:
      <Login/>
    },
    {
      path:"/signup",
      element:
      <SignUp/>
    },
    {
      path:"/library",
      element: <Library/>
    },
    {
      path:"/books",
      element: <Books/>
    },
    {
      path:"/books/read/:id",
      element: <Read/>
    },
    {
      path:"/account",
      element: <Account/>
    }
  ]
)

function App() {
  return (
    < RouterProvider router={router}/>
   
  );
}

export default App;
