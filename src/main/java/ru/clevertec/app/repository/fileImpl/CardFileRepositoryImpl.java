package ru.clevertec.app.repository.fileImpl;

import ru.clevertec.app.CheckRunner;
import ru.clevertec.app.entity.Card;
import ru.clevertec.app.repository.Repository;
import ru.clevertec.app.service.CheckFormatBuilder;
import ru.clevertec.app.service.ParseArgsService;
import ru.clevertec.app.service.impl.CustomArrayList;
import ru.clevertec.app.service.interfaces.CustomList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

public class CardFileRepositoryImpl implements Repository<Card> {
    @Override
    public Card add(Card card) {
        return null;
    }

    @Override
    public Card update(Card card) {
        return null;
    }

    @Override
    public Optional<Card> findById(Long id) {
        Optional<Card> cardOptional = Optional.empty();
        try (Stream<String> stream = Files.lines(Paths.get(ParseArgsService.getInstance(CheckRunner.arg).getPathFileCard()))) {
            Optional<String> cardString = stream.map(line -> line.split("-")).map(strings -> strings[0]).filter(r -> r.contains(id.toString())).findAny();
            if (cardString.isPresent()){
                String[] arr = cardString.get().split(";");
                cardOptional = Optional.of(new Card(Long.parseLong(arr[0]), arr[1], new BigDecimal(arr[2])));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cardOptional;
    }

    @Override
    public CustomList<Card> findAll() {
        CustomList<Card> allCard = new CustomArrayList<>();
        try (Scanner scanner = new Scanner(new File(ParseArgsService.getInstance(CheckRunner.arg).getPathFileCard()), StandardCharsets.UTF_8)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Card card = new Card();
                String[] row = line.split(";");
                card.setId(Long.parseLong(row[0]));
                card.setNumbercard(row[1]);
                card.setDiscount(new BigDecimal(row[2]));
                allCard.add(card);
            }
        } catch (FileNotFoundException e) {
            CheckFormatBuilder.errorCardFile();
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return allCard;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
