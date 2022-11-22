package lk.ijse.dep9.dao;

import lk.ijse.dep9.dao.exception.ConstraintViolationException;
import lk.ijse.dep9.entity.IssueNote;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IssueNoteDAO {

    public long countIssueNotes();

    public void deleteIssueNoteById(int id) throws ConstraintViolationException;

    public boolean existsIssueNoteById(int id);

    public List<IssueNote> findAllIssueNotes();

    public Optional<IssueNote> findIssueNoteById(int id);

    public IssueNote saveIssueNote(IssueNote issueNote);

    public IssueNote updateIssueNote(IssueNote issueNote);

}
