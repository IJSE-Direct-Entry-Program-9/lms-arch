package lk.ijse.dep9.dao.custom;

import lk.ijse.dep9.dao.SuperDAO;
import lk.ijse.dep9.entity.Book;

import java.util.List;

public interface BookDAO extends SuperDAO<Book, String> {

    List<Book> findBooksByQuery(String query);

    List<Book> findBooksByQuery(String query, int size, int page);

    List<Book> findAllBooks(int size, int page);
}
