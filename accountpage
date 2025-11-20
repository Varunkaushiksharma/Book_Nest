import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import './Account.css'

export default function Account() {
  const [user, setUser] = useState(null);
  const [books, setBooks] = useState([]);

  // For Add/Edit form
  const [editingBook, setEditingBook] = useState(null);
  const [formData, setFormData] = useState({ name: "", author: "", summary: "" });

  const navigate = useNavigate();
  const token = localStorage.getItem("token");

  useEffect(() => {
    if (!token) {
      navigate("/login");
      return;
    }

    // Fetch user info
    axios.get("http://localhost:8080/api/users/me", {
      headers: { Authorization: `Bearer ${token}` }
    }).then(res => setUser(res.data))
      .catch(() => {
        localStorage.removeItem("token");
        navigate("/login");
      });

    // Fetch user's books
    fetchBooks();
  }, []);

  const fetchBooks = () => {
    axios.get("http://localhost:8080/api/books/my", {
      headers: { Authorization: `Bearer ${token}` }
    }).then(res => setBooks(res.data))
      .catch(err => console.error(err));
  };

  const handleLogout = () => {
    localStorage.removeItem("token");
    navigate("/login");
    window.dispatchEvent(new Event("storage"));
  };

  const handleDeleteBook = (id) => {
    if (!window.confirm("Are you sure you want to delete this book?")) return;
    axios.delete(`http://localhost:8080/api/books/${id}`, {
      headers: { Authorization: `Bearer ${token}` }
    }).then(() => {
      setBooks(books.filter(book => book.id !== id));
    }).catch(() => alert("Failed to delete book"));
  };

  const startEditBook = (book) => {
    setEditingBook(book);
    setFormData({ name: book.name, author: book.author, summary: book.summary });
  };

  const cancelEdit = () => {
    setEditingBook(null);
    setFormData({ name: "", author: "", summary: "" });
  };

  const handleInputChange = (e) => {
    setFormData(prev => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!formData.name || !formData.author) {
      alert("Name and author are required");
      return;
    }

    if (editingBook) {
      // Update book
      axios.put(`http://localhost:8080/api/books/${editingBook.id}`, formData, {
        headers: { Authorization: `Bearer ${token}` }
      }).then(() => {
        fetchBooks();
        cancelEdit();
      }).catch(() => alert("Failed to update book"));
    } else {
      // Create book
      axios.post("http://localhost:8080/api/books", formData, {
        headers: { Authorization: `Bearer ${token}` }
      }).then(() => {
        fetchBooks();
        cancelEdit();
      }).catch(() => alert("Failed to add book"));
    }
  };

  return (
    <div style={{ maxWidth: "800px", margin: "2rem auto", padding: "0 1rem" }}>
      <h2>Welcome, {user?.username || user?.name || "User"}</h2>
      <button
        onClick={handleLogout}
        style={{
          margin: "1rem 0",
          backgroundColor: "#ff4d4d",
          color: "white",
          padding: "10px 20px",
          border: "none",
          borderRadius: "5px",
          cursor: "pointer"
        }}
      >
        Logout
      </button>

      {/* Book Form */}
      <div style={{ marginBottom: "2rem" }}>
        <h3>{editingBook ? "Edit Book" : "Add New Book"}</h3>
        <form onSubmit={handleSubmit}>
          <input
            type="text"
            name="name"
            placeholder="Book Name"
            value={formData.name}
            onChange={handleInputChange}
            required
            style={{ display: "block", marginBottom: "0.5rem", width: "100%", padding: "8px" , backgroundColor:"white"}}
          />
          <input
            type="text"
            name="author"
            placeholder="Author"
            value={formData.author}
            onChange={handleInputChange}
            required
            style={{ display: "block", marginBottom: "0.5rem", width: "100%", padding: "8px" , backgroundColor:"white"}}
          />
          <textarea
            name="summary"
            placeholder="Summary"
            value={formData.summary}
            onChange={handleInputChange}
            style={{ display: "block", marginBottom: "0.5rem", width: "100%", padding: "8px" }}
          />
          <button
            type="submit"
            style={{
              backgroundColor: "#2ecc71",
              color: "white",
              border: "none",
              padding: "10px 20px",
              borderRadius: "5px",
              cursor: "pointer",
              marginRight: "1rem"
            }}
          >
            {editingBook ? "Update Book" : "Add Book"}
          </button>
          {editingBook && (
            <button
              type="button"
              onClick={cancelEdit}
              style={{
                backgroundColor: "#95a5a6",
                color: "white",
                border: "none",
                padding: "10px 20px",
                borderRadius: "5px",
                cursor: "pointer"
              }}
            >
              Cancel
            </button>
          )}
        </form>
      </div>

      {/* Books List */}
      <h3>Your Books</h3>
      {books.length === 0 ? (
        <p>No books uploaded yet.</p>
      ) : (
        <ul style={{ listStyle: "none", padding: 0 }}>
          {books.map(book => (
            <li
              key={book.id}
              style={{
                backgroundColor: "#f4f4f4",
                marginBottom: "1rem",
                padding: "1rem",
                borderRadius: "8px"
              }}
            >
              <strong>{book.name}</strong> by {book.author}
              <p>{book.summary}</p>
              <button
                onClick={() => startEditBook(book)}
                style={{
                  marginRight: "1rem",
                  padding: "6px 12px",
                  backgroundColor: "#3498db",
                  color: "white",
                  border: "none",
                  borderRadius: "5px",
                  cursor: "pointer"
                }}
              >
                Edit
              </button>
              <button
                onClick={() => handleDeleteBook(book.id)}
                style={{
                  padding: "6px 12px",
                  backgroundColor: "#e74c3c",
                  color: "white",
                  border: "none",
                  borderRadius: "5px",
                  cursor: "pointer"
                }}
              >
                Delete
              </button>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}
