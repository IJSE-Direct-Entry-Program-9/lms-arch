package lk.ijse.dep9.service.util;

import lk.ijse.dep9.dto.BookDTO;
import lk.ijse.dep9.dto.MemberDTO;
import lk.ijse.dep9.entity.Book;
import lk.ijse.dep9.entity.Member;
import org.modelmapper.ModelMapper;

public class Converter {

    private ModelMapper mapper = new ModelMapper();

    public BookDTO toBookDTO(Book bookEntity) {
        return mapper.map(bookEntity, BookDTO.class);
    }

    public Book toBook(BookDTO bookDTO) {
        return mapper.map(bookDTO, Book.class);
    }

    public MemberDTO toMemberDTO(Member memberEntity){
        return mapper.map(memberEntity, MemberDTO.class);
    }

    public Member toMember(MemberDTO memberDTO){
        return mapper.map(memberDTO, Member.class);
    }
}
