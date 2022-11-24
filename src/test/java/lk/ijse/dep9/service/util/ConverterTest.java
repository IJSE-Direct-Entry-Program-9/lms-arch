package lk.ijse.dep9.service.util;

import com.github.javafaker.Faker;
import lk.ijse.dep9.dto.BookDTO;
import lk.ijse.dep9.dto.MemberDTO;
import lk.ijse.dep9.entity.Book;
import lk.ijse.dep9.entity.Member;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {

    private Converter converter = new Converter();

    @Test
    void toBookDTO() {
        Faker faker = new Faker();
        Book bookEntity = new Book(faker.code().isbn10(), faker.book().title(), faker.book().author(),
                faker.number().numberBetween(1, 3));
        BookDTO bookDTO = converter.toBookDTO(bookEntity);
        assertEquals(bookEntity.getIsbn(), bookDTO.getIsbn());
        assertEquals(bookEntity.getTitle(), bookDTO.getTitle());
        assertEquals(bookEntity.getAuthor(), bookDTO.getAuthor());
        assertEquals(bookEntity.getCopies(), bookDTO.getCopies());
    }

    @Test
    void toBook() {
        Faker faker = new Faker();
        BookDTO bookDTO = new BookDTO(faker.code().isbn10(), faker.book().title(), faker.book().author(),
                faker.number().numberBetween(1, 3));
        Book bookEntity = converter.toBook(bookDTO);
        assertEquals(bookDTO.getIsbn(), bookEntity.getIsbn());
        assertEquals(bookDTO.getTitle(), bookEntity.getTitle());
        assertEquals(bookDTO.getAuthor(), bookEntity.getAuthor());
        assertEquals(bookDTO.getCopies(), bookEntity.getCopies());
    }

    @Test
    void toMemberDTO() {
        Faker faker = new Faker();
        Member memberEntity = new Member(UUID.randomUUID().toString(),
                faker.name().fullName(), faker.address().fullAddress(),
                faker.regexify("0\\d{3}-\\d{7}"));

        MemberDTO memberDTO = converter.toMemberDTO(memberEntity);

        assertEquals(memberEntity.getId(), memberDTO.getId());
        assertEquals(memberEntity.getName(), memberDTO.getName());
        assertEquals(memberEntity.getAddress(), memberDTO.getAddress());
        assertEquals(memberEntity.getContact(), memberDTO.getContact());
    }

    @Test
    void toMember() {
        Faker faker = new Faker();
        MemberDTO memberDTO = new MemberDTO(UUID.randomUUID().toString(),
                faker.name().fullName(), faker.address().fullAddress(),
                faker.regexify("0\\d{3}-\\d{7}"));

        Member memberEntity = converter.toMember(memberDTO);

        assertEquals(memberDTO.getId(), memberEntity.getId());
        assertEquals(memberDTO.getName(), memberEntity.getName());
        assertEquals(memberDTO.getAddress(), memberEntity.getAddress());
        assertEquals(memberDTO.getContact(), memberEntity.getContact());
    }
}