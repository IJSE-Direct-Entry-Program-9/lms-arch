package lk.ijse.dep9.dao;

import lk.ijse.dep9.dao.exception.ConstraintViolationException;
import lk.ijse.dep9.entity.IssueItem;
import lk.ijse.dep9.entity.IssueItemPK;

import java.util.List;
import java.util.Optional;

public interface IssueItemDAO {

    long countIssueItems();

    void deleteIssueItemByPK(IssueItemPK issueItemPK) throws ConstraintViolationException;

    boolean existsIssueItemByPK(IssueItemPK issueItemPK);

    List<IssueItem> findAllIssueItems();

    Optional<IssueItem> findIssueItemByPK(IssueItemPK issueItemPK);

    IssueItem saveIssueItem(IssueItem issueItem);
}
