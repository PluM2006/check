package ru.clevertec.app.repository.product.fileimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.clevertec.app.constant.Constants;
import ru.clevertec.app.customlist.CustomArrayList;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.repository.CheckRepository;
import ru.clevertec.app.utils.YamlUtils;
import ru.clevertec.app.validator.ValidationProduct;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
public class ProductFileCheckRepositoryImpl implements CheckRepository<Product> {

    private static final String SEPARATOR = ";";
    private final Path pathProduct = Paths.get(YamlUtils.getYamlProperties().getConstants().getPathProduct());
    private final ValidationProduct validationProduct;


    @Override
    public Product add(Product product) {
        Optional<String> max;
        try (Stream<String> stream = Files.lines(pathProduct)) {
            max = stream.map(line -> line.split(SEPARATOR))
                    .map(arr -> arr[0])
                    .filter(s -> s.matches(Constants.REG_ONLY_NUMBERS))
                    .max(Comparator.comparingInt(Integer::parseInt));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        product.setId(Long.parseLong(max.orElse(Constants.ZERO_STRING)) + 1L);
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
                if (correctProduct.length() > 0) {
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
    public CustomList<Product> findAll(Integer limit, Integer offset) {
        CustomList<Product> allProduct = new CustomArrayList<>();
        try (Stream<String> stream = Files.lines(pathProduct)) {
            stream.map(validationProduct::getCorrectProduct).limit(limit)
                    .skip(offset)
                    .forEach(s -> allProduct.add(createProduct(s)));
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
        return allProduct;
    }

    public CustomList<Product> findAll() {
        CustomList<Product> allProduct = new CustomArrayList<>();
        try (Stream<String> stream = Files.lines(pathProduct)) {
            stream.map(validationProduct::getCorrectProduct)
                    .filter(s -> !s.equals(""))
                    .forEach(s -> allProduct.add(createProduct(s)));
        } catch (IOException e) {

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
