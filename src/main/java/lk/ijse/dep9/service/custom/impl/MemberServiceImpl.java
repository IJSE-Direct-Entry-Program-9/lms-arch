package lk.ijse.dep9.service.custom.impl;

import lk.ijse.dep9.dto.MemberDTO;
import lk.ijse.dep9.service.custom.MemberService;
import lk.ijse.dep9.service.exception.DuplicateException;
import lk.ijse.dep9.service.exception.InUseException;
import lk.ijse.dep9.service.exception.NotFoundException;

import java.util.List;

public class MemberServiceImpl implements MemberService {
    @Override
    public void signupMember(MemberDTO member) throws DuplicateException {

    }

    @Override
    public void updateMemberDetails(MemberDTO member) throws NotFoundException {

    }

    @Override
    public void removeMemberAccount(String memberId) throws NotFoundException, InUseException {

    }

    @Override
    public MemberDTO getMemberDetails(String memberId) throws NotFoundException {
        return null;
    }

    @Override
    public List<MemberDTO> findMembers(String query, int size, int page) {
        return null;
    }
}
