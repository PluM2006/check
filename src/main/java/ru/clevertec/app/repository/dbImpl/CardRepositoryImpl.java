package ru.clevertec.app.repository.dbImpl;

import ru.clevertec.app.connectionpool.ConnectionPool;
import ru.clevertec.app.entity.Card;
import ru.clevertec.app.repository.Repository;
import ru.clevertec.app.service.CustomList;

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
             PreparedStatement statement = connection.prepareStatement(ADD_CARD, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, card.getNumbercard());
            statement.setBigDecimal(2, card.getDiscount());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()){
                card.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return card;
    }

    @Override
    public Card update(Card card, Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CARD)
        ) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Optional<Card> findById(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)){

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public CustomList<Card> findAll() {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ALL)){

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_CARD_BY_ID)){

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}
