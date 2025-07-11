import { NavLink, Link } from "react-router-dom";
import "./Navbar.css";
import Button from "./Button";
import { useEffect, useState } from "react";

function Navbar() {
  const [isloggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem("token");
    setIsLoggedIn(!!token);

    const handleStorageChange = () => {
      const token = localStorage.getItem("token");
      setIsLoggedIn(!!token);
    };

    window.addEventListener("storage", handleStorageChange);
    window.addEventListener("focus", handleStorageChange);

    return () => {
      window.removeEventListener("storage", handleStorageChange);
      window.removeEventListener("focus", handleStorageChange);
    };
  }, []);

  return (
    <div className="navbar">
      <h2>📚 Book Nest</h2>
      <ul>
        <li>
          <NavLink
            to="/"
            style={({ isActive }) => ({
              padding: "8px 16px",
              borderRadius: "6px",
              backgroundColor: isActive ? "rgb(108, 168, 10)" : "transparent",
            })}
            className="nav"
          >
            Home
          </NavLink>
        </li>
        <li>
          <NavLink
            to="/books"
            style={({ isActive }) => ({
              padding: "8px 16px",
              borderRadius: "6px",
              backgroundColor: isActive ? "rgb(108, 168, 10)" : "transparent",
            })}
            className="nav"
          >
            Books
          </NavLink>
        </li>
        <li>
          <NavLink
            to="/library"
            style={({ isActive }) => ({
              padding: "8px 16px",
              borderRadius: "6px",
              backgroundColor: isActive ? "rgb(108, 168, 10)" : "transparent",
            })}
            className="nav"
          >
            Library
          </NavLink>
        </li>

        {isloggedIn ? (
          <Link to="/account">
            <Button name="Account" />
          </Link>
        ) : (
          <Link to="/login">
            <Button name="Login" />
          </Link>
        )}
      </ul>
    </div>
  );
}

export default Navbar;
