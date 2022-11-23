package lk.ijse.dep9.service.custom.impl;

import lk.ijse.dep9.dao.DAOFactory;
import lk.ijse.dep9.dao.DAOTypes;
import lk.ijse.dep9.dao.custom.BookDAO;
import lk.ijse.dep9.dto.BookDTO;
import lk.ijse.dep9.entity.Book;
import lk.ijse.dep9.service.custom.BookService;
import lk.ijse.dep9.service.exception.DuplicateException;
import lk.ijse.dep9.service.exception.NotFoundException;
import lk.ijse.dep9.util.ConnectionUtil;

import java.util.List;

public class BookServiceImpl implements BookService {

    private final BookDAO bookDAO;

    public BookServiceImpl() {
        this.bookDAO = DAOFactory.getInstance().getDAO(ConnectionUtil.getConnection(), DAOTypes.BOOK);
    }

    @Override
    public void addNewBook(BookDTO dto) throws DuplicateException {
        if (bookDAO.existsById(dto.getIsbn())){
            throw new DuplicateException("Book with this isbn already exists");
        }
        Book bookEntity = new Book(dto.getIsbn(), dto.getTitle(), dto.getAuthor(), dto.getCopies());
        bookDAO.save(bookEntity);
    }

    @Override
    public void updateBookDetails(BookDTO dto) throws NotFoundException {

    }

    @Override
    public BookDTO getBookDetails(String isbn) throws NotFoundException {
        return null;
    }

    @Override
    public List<BookDTO> findBooks(String query, int size, int page) {
        return null;
    }
}
