package lk.ijse.dep9.dao;

import lk.ijse.dep9.dao.exception.ConstraintViolationException;
import lk.ijse.dep9.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookDAO {

    long countBooks();

    void deleteBookByISBN(String isbn) throws ConstraintViolationException;

    boolean existsBookByISBN(String isbn);

    List<Book> findAllBooks();

    Optional<Book> findBookByISBN(String isbn);

    Book saveBook(Book book);

    Book updateBook(Book book);

    List<Book> findBooksByQuery(String query);

    List<Book> findBooksByQuery(String query, int size, int page);

    List<Book> findAllBooks(int size, int page);
}
