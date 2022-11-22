package lk.ijse.dep9.service;

import lk.ijse.dep9.dao.custom.impl.BookDAOImpl;
import lk.ijse.dep9.dto.BookDTO;
import lk.ijse.dep9.dto.MemberDTO;
import lk.ijse.dep9.entity.Book;
import lk.ijse.dep9.util.ConnectionUtil;

public class BOLogic {

    public static boolean deleteMember(String memberId) {
        return true;
    }

    public static boolean createMember(MemberDTO member) {
        return true;
    }

    public static boolean updateMember(MemberDTO member) {
        return true;
    }

    public static boolean saveBook(BookDTO book) {
        BookDAOImpl bookDAOImpl = new BookDAOImpl(ConnectionUtil.getConnection());
        if (!bookDAOImpl.existsBookByISBN(book.getIsbn())){
            bookDAOImpl.saveBook(new Book(book.getIsbn(), book.getTitle(), book.getAuthor(),book.getCopies()));
            return true;
        }
        return false;
    }

    public static boolean updateBook(BookDTO book) {
        BookDAOImpl bookDAOImpl = new BookDAOImpl(ConnectionUtil.getConnection());
        if (!bookDAOImpl.existsBookByISBN(book.getIsbn())){
            bookDAOImpl.updateBook( new Book(book.getIsbn(), book.getTitle(), book.getAuthor(),book.getCopies()));
            return true;
        }
        return false;
    }

}
