package ru.clevertec.app.repository.product.fileimpl;

import ru.clevertec.app.entity.Product;
import ru.clevertec.app.repository.Repository;
import ru.clevertec.app.customlist.CustomArrayList;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.utils.CheckFormatBuilder;
import ru.clevertec.app.utils.PropertiesUtil;
import ru.clevertec.app.validator.ValidationProduct;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Stream;

public class ProductFileRepositoryImpl implements Repository<Product> {

    private static final String SEPARATOR = ";";
    private final Path pathProduct = Paths.get(PropertiesUtil.get("PATH_PRODUCT"));

    private final ValidationProduct validationProduct = new ValidationProduct();


    @Override
    public Product add(Product product) {
        Optional<String> max;
        try (Stream<String> stream = Files.lines(pathProduct)) {
            max = stream.map(line -> line.split(SEPARATOR))
                    .map(arr -> arr[0])
                    .filter(s -> s.matches("\\d+"))
                    .max(Comparator.comparingInt(Integer::parseInt));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        product.setId(Long.parseLong(max.orElse("0")) + 1L);
        String lineCsv = (product.getId().equals(1L) ? "" : System.lineSeparator()) + createLineCsv(product);
        try {
            Files.write(pathProduct, lineCsv.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public Product update(Product product) {
        List<String> resultProduct = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(pathProduct);
            for (String line : lines) {
                if (line.substring(0, line.indexOf(SEPARATOR)).equals(String.valueOf(product.getId()))) {
                    String lineCsv = createLineCsv(product);
                    resultProduct.add(lineCsv);
                } else {
                    resultProduct.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (FileWriter fileWriter = new FileWriter(pathProduct.toString())) {
            fileWriter.write(String.join(System.lineSeparator(), resultProduct));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        Optional<Product> productOptional = Optional.empty();
        try (Stream<String> stream = Files.lines(pathProduct)) {
            Optional<String> productString = stream
                    .filter(r -> r.substring(0, r.indexOf(SEPARATOR)).contains(id.toString()))
                    .findAny();
            if (productString.isPresent()) {
                String correctProduct = validationProduct.getCorrectProduct(productString.get());
                if (correctProduct.length()>0) {
                    Product product = createProduct(correctProduct);
                    productOptional = Optional.of(product);
                } else {
                    productOptional = Optional.empty();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return productOptional;
    }

    @Override
    public CustomList<Product> findAll() {
        CustomList<Product> allProduct = new CustomArrayList<>();

        try {
            List<String> lines = Files.readAllLines(pathProduct);
            List<String> correctProducts = validationProduct.getCorrectProducts(lines);
            for (String correctProduct: correctProducts) {
                allProduct.add(createProduct(correctProduct));
            }
        } catch (IOException e) {
            CheckFormatBuilder.errorProductFIle();
            throw new RuntimeException(e);
        }
        return allProduct;
    }

    @Override
    public boolean delete(Long id) {
        List<String> result = new ArrayList<>();
        List<String> lines;
        try {
            lines = Files.readAllLines(pathProduct);
            for (String line : lines) {
                if (!line.substring(0, line.indexOf(SEPARATOR)).equals(String.valueOf(id))) {
                    result.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (FileWriter fileWriter = new FileWriter(pathProduct.toString())) {
            fileWriter.write(String.join(System.lineSeparator(), result));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result.size() < lines.size();
    }

    private Product createProduct(String line) {
        String[] arr = line.split(SEPARATOR);
        return new Product(
                Long.parseLong(arr[0]),
                arr[1],
                new BigDecimal(arr[2].replaceAll(",", ".")),
                Integer.parseInt(arr[3]),
                Boolean.parseBoolean(arr[4]));
    }

    private String createLineCsv(Product product) {
        return product.getId() + SEPARATOR +
                product.getName() + SEPARATOR +
                product.getPrice().setScale(2, RoundingMode.HALF_DOWN) + SEPARATOR +
                product.getCount() + SEPARATOR +
                product.getSale();
    }
}
