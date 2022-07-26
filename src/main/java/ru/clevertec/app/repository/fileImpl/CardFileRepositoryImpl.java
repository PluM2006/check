package ru.clevertec.app.repository.fileImpl;

import ru.clevertec.app.CheckRunner;
import ru.clevertec.app.entity.Card;
import ru.clevertec.app.repository.Repository;
import ru.clevertec.app.service.ArgsUtil;
import ru.clevertec.app.service.impl.CustomArrayList;
import ru.clevertec.app.service.interfaces.CustomList;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public class CardFileRepositoryImpl implements Repository<Card> {
    public static final String SEPARATOR = ";";
    private final Path path = Paths.get(ArgsUtil.getInstance(CheckRunner.arg).getPathFileCard());

    @Override
    public Card add(Card card) {
        Optional<String> max;
        try (Stream<String> stream = Files.lines(path)) {
            max = stream.map(line -> line.split(SEPARATOR))
                    .map(strings -> strings[0])
                    .max(Comparator.comparingInt(Integer::parseInt));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        card.setId(Long.parseLong(max.orElse("0")) + 1L);
        String lineCsv = createLineCsv(card);
        try {
            Files.write(path, lineCsv.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return card;
    }

    @Override
    public Card update(Card card) {
        return null;
    }

    @Override
    public Optional<Card> findById(Long id) {
        Optional<Card> cardOptional = Optional.empty();
        try (Stream<String> stream = Files.lines(path)) {
            Optional<String> cardString = stream
                    .filter(r -> r.substring(0, r.indexOf(SEPARATOR)).contains(id.toString()))
                    .findAny();
            if (cardString.isPresent()) {
                cardOptional = Optional.of(createCard(cardString.get()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cardOptional;
    }

    @Override
    public CustomList<Card> findAll() {
        CustomList<Card> allCard;
        try (Stream<String> stream = Files.lines(path)) {
            allCard = stream.collect(CustomArrayList::new, (l, s) -> l.add(createCard(s)), CustomArrayList::addAll);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return allCard;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    private Card createCard(String line) {
        String[] arr = line.split(SEPARATOR);
        return new Card(Long.parseLong(arr[0]), arr[1], new BigDecimal(arr[2]));
    }

    private String createLineCsv(Card card) {
        return (card.getId().equals(1L) ? "" : System.lineSeparator()) +
                card.getId() + SEPARATOR +
                card.getNumbercard() + SEPARATOR +
                card.getDiscount();
    }
}
