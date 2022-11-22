package lk.ijse.dep9.dao;

import com.github.javafaker.Faker;
import lk.ijse.dep9.dao.exception.ConstraintViolationException;
import lk.ijse.dep9.dao.impl.MemberDAOImpl;
import lk.ijse.dep9.entity.Member;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MemberDAOImplTest {

    private MemberDAOImpl memberDAOImpl;
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException, URISyntaxException, IOException {
        connection = DriverManager.getConnection("jdbc:h2:mem:");
        List<String> lines = Files.readAllLines(Paths.get(MemberDAOImplTest.class.getResource("/db-script.sql").toURI()));
        String dbScriptContent = lines.stream().reduce((previous, current) -> previous + '\n' + current).get();
        Statement stm = connection.createStatement();
        stm.execute(dbScriptContent);
        this.memberDAOImpl = new MemberDAOImpl(connection);
    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.close();
    }

    //@Order(1)
    @Test
    void countMembers() {
        long actualMemberCount = memberDAOImpl.countMembers();
        assertEquals(3, actualMemberCount);
    }

    //@Order(1)
    @Test
    void findAllMembers() {
        List<Member> members = memberDAOImpl.findAllMembers();
        assertEquals(3, members.size());
        members.forEach(member -> {
            assertNotNull(member);
            assertNotNull(member.getId());
            assertNotNull(member.getName());
            assertNotNull(member.getAddress());
            assertNotNull(member.getContact());
            System.out.println(member);
        });
    }

    //@Order(2)
    @RepeatedTest(5)
    void saveMember() {
        Faker faker = new Faker();
        Member expectedMember =
                new Member(UUID.randomUUID().toString(),
                        faker.name().fullName(),
                        faker.address().fullAddress(),
                        faker.regexify("0\\d{2}-\\d{7}"));
        System.out.println(expectedMember);
        long expectedCount = memberDAOImpl.countMembers() + 1;
        Member actualMember = memberDAOImpl.saveMember(expectedMember);
        assertEquals(expectedMember, actualMember);
        assertEquals(expectedCount, memberDAOImpl.countMembers());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/member-test-data.csv")
    void deleteMemberById(String memberId, boolean expectedResult) {
        try {
            memberDAOImpl.deleteMemberById(memberId);
        } catch (ConstraintViolationException e) {
            System.out.println("Failed to delete " + memberId);
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/member-test-data.csv")
    void existsMemberById(String memberId, boolean expectedResult) {
        //String memberId = "104ccff3-c584-4782-a582-8a06479b46f6";
        boolean actualValue = memberDAOImpl.existsMemberById(memberId);
        assertEquals(actualValue, expectedResult);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/member-test-data.csv")
    void findMemberById(String memberId, boolean expectedResult) {
        Optional<Member> optMember = memberDAOImpl.findMemberById(memberId);
        assertEquals(optMember.isPresent(), expectedResult);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/member-test-data.csv")
    void updateMember(String memberId, boolean exist) {
        Optional<Member> optMember = memberDAOImpl.findMemberById(memberId);
        Faker faker = new Faker();
        optMember.ifPresent(member -> {
            member.setName(faker.name().fullName());
            member.setAddress(faker.address().fullAddress());
            member.setContact(faker.regexify("0\\d{2}-\\d{7}"));
            Member updatedMember = memberDAOImpl.updateMember(member);
            assertEquals(member, updatedMember);
        });
    }

    @Test
    void findMembersByQuery() {
    }

    @Test
    void testFindMembersByQuery() {
    }

    @Test
    void testFindAllMembers() {
    }
}