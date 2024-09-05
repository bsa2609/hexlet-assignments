package exercise.repository;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import exercise.model.Product;

public class ProductsRepository extends BaseRepository {

    // BEGIN
    public static void save(Product product) throws SQLException {
        String sql = """
                INSERT INTO products(
                    title,
                    price)
                VALUES(?, ?)""";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }

        }
    }

    public static Optional<Product> find(long id) throws SQLException {
        String sql = """
                SELECT
                    title,
                    price
                FROM products
                WHERE
                    id = ?""";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Product product = new Product(
                        resultSet.getString("title"),
                        resultSet.getInt("price")
                );
                product.setId(id);

                return Optional.of(product);
            }
        }

        return Optional.empty();
    }

    public static List<Product> getEntities() throws SQLException {
        List<Product> products = new ArrayList<>();

        String sql = """
                SELECT
                    id,
                    title,
                    price
                FROM products""";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getString("title"),
                        resultSet.getInt("price")
                );
                product.setId(resultSet.getLong("id"));

                products.add(product);
            }
        }

        return products;
    }
    // END
}
