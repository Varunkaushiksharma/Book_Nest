import React from "react";
import { Link } from "react-router-dom";
import "./BookCard.css";
import defaultImage from '../assets/dev.png';

export default function BookCard({
  book,
  onAddToLibrary,
  onRemoveFromLibrary,
  showAddButton = false,
  showRemoveButton = false
}) {
  return (
    <div className="book-card">
      <img
        src={defaultImage}
        alt={`Cover of ${book.name}`}
        className="book-cover"
      />
      <div className="book-info">
        <h3 className="book-name">{book.name}</h3>
        <p className="book-author">by {book.author}</p>
        <p className="book-summary">{book.summary}</p>

        <div className="button-group">
          <Link to={`/books/read/${book.id}`} className="read-more-link">
            Read More
          </Link>

          {showAddButton && (
            <button
              className="add-library-btn"
              onClick={() => onAddToLibrary(book.id)}
              type="button"
            >
              Add to Library
            </button>
          )}

          {showRemoveButton && (
            <button
              className="remove-library-btn"
              onClick={() => onRemoveFromLibrary(book.id)}
              type="button"
            >
              Remove from Library
            </button>
          )}
        </div>
      </div>
    </div>
  );
}
