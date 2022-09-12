package ru.clevertec.app.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.app.check.CheckBuilderInterface;
import ru.clevertec.app.check.PrintInterface;
import ru.clevertec.app.check.impl.CheckBuilderImpl;
import ru.clevertec.app.check.impl.CheckItemsDBImpl;
import ru.clevertec.app.check.impl.PrintToFileImpl;
import ru.clevertec.app.customlist.CustomArrayList;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.dto.ProductDTO;
import ru.clevertec.app.entity.*;
import ru.clevertec.app.repository.CashierRepository;
import ru.clevertec.app.repository.ShopRepository;
import ru.clevertec.app.service.CheckService;
import ru.clevertec.app.utils.CheckErrorsStringFormatting;
import ru.clevertec.app.utils.CheckStringFormatting;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class PrintToFileImplTest {

    private final PrintInterface printInterface = new PrintToFileImpl();
    private final CustomList<CheckItem> checkItems = new CustomArrayList<>();
    private final Map<Long, Integer> mapParam = new HashMap<>();
    private CheckBuilderInterface checkBuilder;
    private Shop shop;
    private Cashier cashier;
    private Card card;
    @Mock
    private CashierRepository cashierRepository;
    @Mock
    private ShopRepository shopRepository;
    @Mock
    private CheckService<ProductDTO, Product> productRepository;

    @BeforeEach
    void setUp() {
        checkBuilder = new CheckBuilderImpl(
                cashierRepository,
                shopRepository,
                new CheckItemsDBImpl(productRepository),
                new CheckErrorsStringFormatting(),
                new CheckStringFormatting()
        );
        shop = new Shop("Krama N646", "3-я ул. Строителей, 25");
        cashier = new Cashier("Luke Skywalker", "007");
        card = new Card(1L, "1-1-1-1", new BigDecimal("7"));


        List<ProductDTO> productList = List.of(
                ProductDTO.builder()
                        .id(1L)
                        .name("banana")
                        .price(new BigDecimal("32.0"))
                        .count(23)
                        .sale(false).build(),
                ProductDTO.builder()
                        .id(1L)
                        .name("banana")
                        .price(new BigDecimal("32.0"))
                        .count(23)
                        .sale(false).build(),
                ProductDTO.builder()
                        .id(1L)
                        .name("banana")
                        .price(new BigDecimal("20.0"))
                        .count(23)
                        .sale(false).build()

        );
        checkItems.add(new CheckItem(productList.get(0), 12, new BigDecimal("10"), new BigDecimal("1"), false));
        checkItems.add(new CheckItem(productList.get(2), 5, new BigDecimal("30"), new BigDecimal("3"), true));
        checkItems.add(new CheckItem(productList.get(1), 10, new BigDecimal("20"), new BigDecimal("2"), false));
    }

    @Test
    void print() throws IOException {
//        String check = checkBuilder.getCheck(mapParam, card);
//        printInterface.print(check);
//        Path file = Paths.get("check.txt");
//        String allLine;
//        try (Stream<String> stream = Files.lines(file)) {
//            allLine = stream.collect(Collectors.joining("\n"));
//        }
//        Assertions.assertEquals(allLine, check);
    }
}