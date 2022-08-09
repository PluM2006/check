package ru.clevertec.app.repository.shop;

import ru.clevertec.app.connection.ConnectionPool;
import ru.clevertec.app.customlist.CustomArrayList;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.entity.Cashier;
import ru.clevertec.app.repository.Repository;

import java.sql.*;
import java.util.Optional;

public class CashierRepositoryImpl implements Repository<Cashier> {

    private static final ConnectionPool INSTANCE = ConnectionPool.getInstance();

    private static final String ADD_CASHIER = "INSERT INTO cashier(name, number) VALUES (?, ?)";
    private static final String UPDATE_CASHIER = "UPDATE cashier SET name=?, number=? WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM cashier WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM cashier";
    private static final String DELETE_CASHIER_BY_ID = "DELETE FROM cashier WHERE id=?";

    @Override
    public Cashier add(Cashier cashier) {
        try (Connection connection = INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_CASHIER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, cashier.getName());
            statement.setString(2, cashier.getNumber());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                cashier.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cashier;
    }

    @Override
    public Cashier update(Cashier cashier) {
        try (Connection connection = INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CASHIER)) {
            statement.setString(1, cashier.getName());
            statement.setString(2, cashier.getNumber());
            statement.setLong(3, cashier.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cashier;
    }

    @Override
    public Optional<Cashier> findById(Long id) {
        Optional<Cashier> cashierOptional = Optional.empty();
        try (Connection connection = INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cashierOptional = Optional.of(createCashierFromResultSet(resultSet));
            }
            return cashierOptional;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public CustomList<Cashier> findAll(Integer limit, Integer offset) {
        CustomList<Cashier> cashierCustomList = new CustomArrayList<>();
        try (Connection connection = INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cashierCustomList.add(createCashierFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cashierCustomList;
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CASHIER_BY_ID)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Cashier createCashierFromResultSet(ResultSet resultSet) throws SQLException {
        return new Cashier(
                resultSet.getLong(1),
                resultSet.getString(2),
                resultSet.getString(3)
        );
    }
}
