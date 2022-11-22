package lk.ijse.dep9.dao.custom;

import lk.ijse.dep9.dao.SuperDAO;
import lk.ijse.dep9.entity.Member;

import java.util.List;

public interface MemberDAO extends SuperDAO<Member, String> {

    List<Member> findMembersByQuery(String query);

    List<Member> findMembersByQuery(String query, int size, int page);

    List<Member> findAllMembers(int size, int page);
}
