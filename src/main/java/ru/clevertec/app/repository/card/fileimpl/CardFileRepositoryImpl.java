package ru.clevertec.app.repository.card.fileimpl;

import ru.clevertec.app.constant.Constants;
import ru.clevertec.app.entity.Card;
import ru.clevertec.app.repository.Repository;
import ru.clevertec.app.customlist.CustomArrayList;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.utils.YamlUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class CardFileRepositoryImpl implements Repository<Card> {

    private static final String SEPARATOR = ";";
    private final Path pathCard = Paths.get(YamlUtils.getYamlProperties().getConstants().getPathCard());

    @Override
    public Card add(Card card) {
        Optional<String> max;
        try (Stream<String> stream = Files.lines(pathCard)) {
            max = stream.map(line -> line.split(SEPARATOR))
                    .map(strings -> strings[0])
                    .filter(s -> s.matches(Constants.REG_ONLY_NUMBERS))
                    .max(Comparator.comparingInt(Integer::parseInt));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        card.setId(Long.parseLong(max.orElse(Constants.ZERO_STRING)) + 1L);
        String lineCsv = (card.getId().equals(1L) ? "" : System.lineSeparator()) + createLineCsv(card);
        try {
            Files.write(pathCard, lineCsv.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return card;
    }

    @Override
    public Card update(Card card) {
        List<String> result = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(pathCard);
            for (String line : lines) {
                if (line.substring(0, line.indexOf(SEPARATOR)).equals(String.valueOf(card.getId()))) {
                    String lineCsv = createLineCsv(card);
                    result.add(lineCsv);
                } else {
                    result.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (FileWriter fileWriter = new FileWriter(pathCard.toString())) {
            fileWriter.write(String.join(System.lineSeparator(), result));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return card;
    }

    @Override
    public Optional<Card> findById(Long id) {
        Optional<Card> cardOptional = Optional.empty();
        try (Stream<String> stream = Files.lines(pathCard)) {
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
    public CustomList<Card> findAll(Integer limit, Integer offset) {
        CustomList<Card> allCard;
        try (Stream<String> stream = Files.lines(pathCard)) {
            allCard = stream.collect(CustomArrayList::new, (l, s) -> l.add(createCard(s)), CustomArrayList::addAll);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return allCard;
    }

    @Override
    public boolean delete(Long id) {
        List<String> result = new ArrayList<>();
        List<String> lines;
        try {
            lines = Files.readAllLines(pathCard);
            for (String line : lines) {
                if (!line.substring(0, line.indexOf(SEPARATOR)).equals(String.valueOf(id))) {
                    result.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (FileWriter fileWriter = new FileWriter(pathCard.toString())) {
            fileWriter.write(String.join(System.lineSeparator(), result));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result.size() < lines.size();
    }

    private Card createCard(String line) {
        String[] arr = line.split(SEPARATOR);
        return new Card(Long.parseLong(arr[0]), arr[1], new BigDecimal(arr[2]));
    }

    private String createLineCsv(Card card) {
        return card.getId() + SEPARATOR +
                card.getNumberCard() + SEPARATOR +
                card.getDiscount();
    }
}
