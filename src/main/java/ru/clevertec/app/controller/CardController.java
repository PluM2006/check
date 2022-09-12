package ru.clevertec.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.dto.CardDTO;
import ru.clevertec.app.entity.Card;
import ru.clevertec.app.service.CheckService;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {

    private final CheckService<CardDTO, Card> cardCheckService;

    @GetMapping("/{id}")
    public ResponseEntity<CardDTO> getCard(@PathVariable String id) {
        return ResponseEntity.ok(cardCheckService.findById(id));
    }

    @GetMapping
    public ResponseEntity<CustomList<CardDTO>> getAllCard(Pageable pageable) {
        return ResponseEntity.ok(cardCheckService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<CardDTO> saveCard(@RequestBody Card card) {
        return ResponseEntity.ok(cardCheckService.add(card));
    }

    @PutMapping
    public ResponseEntity<CardDTO> update(@RequestBody Card card) {
        return ResponseEntity.ok(cardCheckService.update(card));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        boolean delete = cardCheckService.delete(id);
        return ResponseEntity.ok(delete);
    }


}
