package lk.ijse.dep9.dao.impl;

import lk.ijse.dep9.dao.IssueNoteDAO;
import lk.ijse.dep9.dao.exception.ConstraintViolationException;
import lk.ijse.dep9.entity.IssueNote;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IssueNoteDAOImpl implements IssueNoteDAO {

    private final Connection connection;

    public IssueNoteDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long countIssueNotes() {
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT COUNT(id) FROM issue_note");
            ResultSet rst = stm.executeQuery();
            rst.next();
            return rst.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteIssueNoteById(int id) throws ConstraintViolationException {
        try {
            PreparedStatement stm = connection.prepareStatement("DELETE FROM issue_note WHERE id = ?");
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            if (existsIssueNoteById(id)) throw new ConstraintViolationException("Issue Note ID still exists within other tables",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existsIssueNoteById(int id) {
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT id FROM issue_note WHERE id = ?");
            stm.setInt(1, id);
            return stm.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<IssueNote> findAllIssueNotes() {
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM issue_note");
            ResultSet rst = stm.executeQuery();
            List<IssueNote> issueNoteList = new ArrayList<>();
            while (rst.next()) {
                int id = rst.getInt("id");
                Date date = rst.getDate("date");
                String memberId = rst.getString("member_id");
                issueNoteList.add(new IssueNote(id, date, memberId));
            }
            return issueNoteList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<IssueNote> findIssueNoteById(int id) {
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM issue_note WHERE id = ?");
            stm.setInt(1, id);
            ResultSet rst = stm.executeQuery();
            if (rst.next()) {
                Date date = rst.getDate("date");
                String memberId = rst.getString("member_id");
                return Optional.of(new IssueNote(id, date, memberId));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public IssueNote saveIssueNote(IssueNote issueNote) {
        try {
            PreparedStatement stm = connection.prepareStatement("INSERT INTO issue_note (date, member_id) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            stm.setDate(1, issueNote.getDate());
            stm.setString(2, issueNote.getMemberId());
            if (stm.executeUpdate() == 1) {
                ResultSet generatedKeys = stm.getGeneratedKeys();
                generatedKeys.next();
                int generatedId = generatedKeys.getInt(1);
                issueNote.setId(generatedId);
                return issueNote;
            } else {
                throw new SQLException("Failed to save the issue note");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public IssueNote updateIssueNote(IssueNote issueNote) {
        try {
            PreparedStatement stm = connection.prepareStatement("UPDATE issue_note SET date=?, member_id=? WHERE id=?");
            stm.setDate(1, issueNote.getDate());
            stm.setString(2, issueNote.getMemberId());
            stm.setInt(3, issueNote.getId());
            if (stm.executeUpdate() == 1) {
                return issueNote;
            } else {
                throw new SQLException("Failed to update the issue note");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
