package ru.clevertec.app.repository.dbImpl;

import ru.clevertec.app.connectionpool.ConnectionPool;
import ru.clevertec.app.entity.Card;
import ru.clevertec.app.repository.Repository;
import ru.clevertec.app.service.interfaces.CustomList;
import ru.clevertec.app.service.impl.CustomArrayList;

import java.sql.*;
import java.util.Optional;

public class CardRepositoryImpl implements Repository<Card> {

    private static final String ADD_CARD = "INSERT INTO card(numbercard, discount) VALUES (?, ?)";
    private static final String UPDATE_CARD = "UPDATE card SET numbercard=?, discount=? WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM card WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM card";
    private static final String DELETE_CARD_BY_ID = "DELETE FROM card WHERE id=?";

    @Override
    public Card add(Card card) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_CARD, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, card.getNumbercard());
            statement.setBigDecimal(2, card.getDiscount());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                card.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return card;
    }

    @Override
    public Card update(Card card) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CARD)) {
            statement.setString(1, card.getNumbercard());
            statement.setBigDecimal(2, card.getDiscount());
            statement.setLong(3, card.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return card;
    }

    @Override
    public Optional<Card> findById(Long id) {
        Optional<Card> optionalCard = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Card card = createFromResultSet(resultSet);
                optionalCard = Optional.of(card);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return optionalCard;
    }

    @Override
    public CustomList<Card> findAll() {
        CustomList<Card> cardCustomList = new CustomArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Card card = createFromResultSet(resultSet);
                cardCustomList.add(card);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cardCustomList;
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CARD_BY_ID)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Card createFromResultSet(ResultSet resultSet) throws SQLException {
        return new Card(
                resultSet.getLong(1),
                resultSet.getString(2),
                resultSet.getBigDecimal(3)
        );
    }
}
