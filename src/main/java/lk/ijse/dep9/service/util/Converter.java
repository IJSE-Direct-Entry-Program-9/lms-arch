package lk.ijse.dep9.service.util;

import lk.ijse.dep9.dto.BookDTO;
import lk.ijse.dep9.entity.Book;
import org.modelmapper.ModelMapper;

public class Converter {

    private ModelMapper mapper = new ModelMapper();

    public BookDTO fromBook(Book bookEntity) {
        return mapper.map(bookEntity, BookDTO.class);
    }

    public Book toBook(BookDTO bookDTO) {
        return mapper.map(bookDTO, Book.class);
    }
}
