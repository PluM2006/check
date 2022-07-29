package ru.clevertec.app.repository.product.dbimpl;

import ru.clevertec.app.entity.Product;
import ru.clevertec.app.repository.Repository;
import ru.clevertec.app.connection.ConnectionPool;
import ru.clevertec.app.customlist.CustomArrayList;
import ru.clevertec.app.customlist.CustomList;

import java.sql.*;
import java.util.Optional;

public class ProductRepositoryImpl implements Repository<Product> {
    public static final String ADD_PRODUCT = "INSERT INTO product (name, price, count, sale) VALUES (?,?,?,?)";
    public static final String FIND_BY_ID = "SELECT id, name, price, count, sale FROM product WHERE id=?";
    public static final String FIND_ALL = "SELECT * FROM product";

    public static final String FIND_ALL_PAGINATOR = "SELECT * FROM product LIMIT ? OFFSET ?";
    public static final String UPDATE_PRODUCT = "UPDATE product SET name=?, price=?, count=?, sale=? WHERE id=?";
    public static final String DELETE_PRODUCT_BY_ID = "DELETE FROM product WHERE id=?";


    @Override
    public Product add(Product product) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.setInt(3, product.getCount());
            statement.setBoolean(4, product.getSale());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                final long id = generatedKeys.getLong(1);
                product.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public Product update(Product product) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT)) {
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.setInt(3, product.getCount());
            statement.setBoolean(4, product.getSale());
            statement.setLong(5, product.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        Optional<Product> optionalProduct = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Product product = createProductFromResultSet(resultSet);
                optionalProduct = Optional.of(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return optionalProduct;
    }

    @Override
    public CustomList<Product> findAll(Integer limit, Integer offset) {
        var customList = new CustomArrayList<Product>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PAGINATOR)) {
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = createProductFromResultSet(resultSet);
                customList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customList;
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_BY_ID)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Product createProductFromResultSet(ResultSet resultSet) throws SQLException {
        return new Product(
                resultSet.getLong(1),
                resultSet.getString(2),
                resultSet.getBigDecimal(3),
                resultSet.getInt(4),
                resultSet.getBoolean(5)
        );
    }
}
