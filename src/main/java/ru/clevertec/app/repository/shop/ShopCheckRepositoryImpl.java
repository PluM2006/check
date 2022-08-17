package ru.clevertec.app.repository.shop;

import org.springframework.stereotype.Repository;
import ru.clevertec.app.connection.ConnectionPool;
import ru.clevertec.app.customlist.CustomArrayList;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.entity.Shop;
import ru.clevertec.app.repository.CheckRepository;

import java.sql.*;
import java.util.Optional;

@Repository
public class ShopCheckRepositoryImpl implements CheckRepository<Shop> {

    private static final String ADD_SHOP = "INSERT INTO shop(name, adress) VALUES (?, ?)";
    private static final String UPDATE_SHOP = "UPDATE shop SET name=?, adress=? WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM shop WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM shop";
    private static final String DELETE_SHOP_BY_ID = "DELETE FROM shop WHERE id=?";

    @Override
    public Shop add(Shop shop) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_SHOP, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, shop.getName());
            statement.setString(2, shop.getAddress());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                long key = generatedKeys.getLong(1);
                shop.setId(key);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return shop;
    }

    @Override
    public Shop update(Shop shop) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SHOP)) {
            statement.setString(1, shop.getName());
            statement.setString(2, shop.getAddress());
            statement.setLong(3, shop.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return shop;
    }

    @Override
    public Optional<Shop> findById(Long id) {
        Optional<Shop> optionalShop = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Shop shop = createShopFromResultSet(resultSet);
                optionalShop = Optional.of(shop);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return optionalShop;
    }

    @Override
    public CustomList<Shop> findAll(Integer limit, Integer offset) {
        CustomList<Shop> shopCustomList = new CustomArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Shop shop = createShopFromResultSet(resultSet);
                shopCustomList.add(shop);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return shopCustomList;
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SHOP_BY_ID)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Shop createShopFromResultSet(ResultSet resultSet) throws SQLException {
        return new Shop(
                resultSet.getLong(1),
                resultSet.getString(2),
                resultSet.getString(3)
        );
    }
}
