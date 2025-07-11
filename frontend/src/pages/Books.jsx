import { useEffect, useState } from "react";
import Navbar from "../component/Navbar";
import axios from "axios";
import BookCard from "../component/BookCard";
import './BookList.css'

export default function Books(){

    const [books, setBooks] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080/api/books")
          .then((response) => setBooks(response.data))
          .catch((error) => console.error("error fetching books :", error));
    }, []);

    // Function to handle adding book to library
    const onAddToLibrary = (bookId) => {
        const token = localStorage.getItem("token");  // assuming JWT token
        axios.post(`http://localhost:8080/api/library/add/${bookId}`, null, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
        .then(() => alert("Book added to library!"))
        .catch(err => {
            const msg = err.response?.data || "Failed to add book to library";
            alert(msg);
        });
    };

    return (
        <div>
            <Navbar />
            <section className="bookcontainer">
                <h2>All Books</h2>
                <div className="books">
                    {books.map((book) => (
                       <BookCard
                            key={book.id}
                            book={book}
                            onAddToLibrary={onAddToLibrary}
                            showAddButton={true}
                            />
                    ))}
                </div>
            </section>
        </div>
    )
}
