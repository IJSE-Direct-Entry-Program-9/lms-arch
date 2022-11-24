package lk.ijse.dep9.dao.custom.impl;

import lk.ijse.dep9.dao.custom.QueryDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class QueryDAOImpl implements QueryDAO {

    private final Connection connection;

    public QueryDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Integer> getAvailableBookCopies(String isbn) {
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "SELECT b.copies - COUNT(ii.isbn) + COUNT(r.isbn) as `available_copies` " +
                            "FROM issue_item ii " +
                            "LEFT OUTER JOIN `return` r ON ii.issue_id = r.issue_id AND ii.isbn = r.isbn " +
                            "LEFT OUTER JOIN book b ON ii.isbn = b.isbn WHERE b.isbn=? " +
                            "GROUP BY b.isbn");
            stm.setString(1, isbn);
            ResultSet rst = stm.executeQuery();
            if (!rst.next()) return Optional.empty();
            return Optional.of(rst.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
