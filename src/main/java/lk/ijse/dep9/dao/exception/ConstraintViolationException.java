package lk.ijse.dep9.dao.exception;

import java.sql.SQLException;

public class ConstraintViolationException extends SQLException {

    public ConstraintViolationException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
