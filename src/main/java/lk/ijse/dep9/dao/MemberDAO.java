package lk.ijse.dep9.dao;

import lk.ijse.dep9.dao.exception.ConstraintViolationException;
import lk.ijse.dep9.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberDAO {

    long countMembers();

    void deleteMemberById(String id) throws ConstraintViolationException;

    boolean existsMemberById(String id);

    List<Member> findAllMembers();

    Optional<Member> findMemberById(String id);

    Member saveMember(Member member);

    Member updateMember(Member member);

    List<Member> findMembersByQuery(String query);

    List<Member> findMembersByQuery(String query, int size, int page);

    List<Member> findAllMembers(int size, int page);
}
