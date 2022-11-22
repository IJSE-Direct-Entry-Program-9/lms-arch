package lk.ijse.dep9.dao;

import lk.ijse.dep9.entity.Return;
import lk.ijse.dep9.entity.ReturnPK;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReturnDAO {

    private final Connection connection;

    public ReturnDAO(Connection connection) {
        this.connection = connection;
    }

    public long countReturns() {
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT COUNT(isbn) FROM `return`");
            ResultSet rst = stm.executeQuery();
            rst.next();
            return rst.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteReturnByPK(ReturnPK returnPK) {
        try {
            PreparedStatement stm = connection.prepareStatement("DELETE FROM `return` WHERE isbn = ? AND issue_id = ?");
            stm.setInt(1, returnPK.getIssueId());
            stm.setString(2, returnPK.getIsbn());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsReturnByPK(ReturnPK returnPK) {
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT isbn FROM `return` WHERE issue_id=? AND isbn=?");
            stm.setInt(1, returnPK.getIssueId());
            stm.setString(2, returnPK.getIsbn());
            return stm.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Return> findAllReturns() {
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM `return`");
            ResultSet rst = stm.executeQuery();
            List<Return> returnList = new ArrayList<>();
            while (rst.next()) {
                Date date = rst.getDate("date");
                int issueId = rst.getInt("issue_id");
                String isbn = rst.getString("isb");
                returnList.add(new Return(date, issueId, isbn));
            }
            return returnList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Return> findReturnByPK(ReturnPK returnPK) {
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM `return` WHERE issue_id = ? AND isbn = ?");
            stm.setInt(1, returnPK.getIssueId());
            stm.setString(2, returnPK.getIsbn());
            ResultSet rst = stm.executeQuery();
            if (rst.next()) {
                Date date = rst.getDate("date");
                int issueId = rst.getInt("issue_id");
                String isbn = rst.getString("isb");
                return Optional.of(new Return(date, issueId, isbn));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Return saveReturn(Return returnItem) {
        try {
            PreparedStatement stm = connection.prepareStatement("INSERT INTO `return` (date, issue_id, isbn) VALUES (?, ?, ?)");
            stm.setDate(1, returnItem.getDate());
            stm.setInt(2, returnItem.getReturnPK().getIssueId());
            stm.setString(3, returnItem.getReturnPK().getIsbn());
            if (stm.executeUpdate() == 1) {
                return returnItem;
            } else {
                throw new SQLException("Failed to save the issue note");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Return updateReturn(Return returnItem) {
        try {
            PreparedStatement stm = connection.prepareStatement("UPDATE `return` SET date = ? WHERE issue_id=? AND isbn=?");
            stm.setDate(1, returnItem.getDate());
            stm.setInt(2, returnItem.getReturnPK().getIssueId());
            stm.setString(3, returnItem.getReturnPK().getIsbn());
            if (stm.executeUpdate() == 1) {
                return returnItem;
            } else {
                throw new SQLException("Failed to update the issue note");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
