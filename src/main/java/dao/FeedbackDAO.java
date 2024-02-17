package dao;

import model.Feedback;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FeedbackDAO {
    // Логгер для вывода сообщений об ошибках
    private static final Logger LOGGER = Logger.getLogger(FeedbackDAO.class.getName());

    // JDBC URL, username и password для подключения к базе данных PostgreSQL
    private static final String URL = "jdbc:postgresql://localhost:5432/feedback";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    // SQL запросы для операций CRUD
    private static final String INSERT_FEEDBACK_SQL = "INSERT INTO feedback (name, email, message) VALUES (?, ?, ?)";

    public void saveFeedback(Feedback feedback) throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FEEDBACK_SQL)) {
            preparedStatement.setString(1, feedback.getName());
            preparedStatement.setString(2, feedback.getEmail());
            preparedStatement.setString(3, feedback.getMessage());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error saving feedback", e);
            throw e;
        }
    }
}
