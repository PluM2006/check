package ru.clevertec.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.app.entity.Card;
import ru.clevertec.app.service.CheckService;

import java.util.Optional;

@RestController("cards")
@RequiredArgsConstructor
public class CardController {

    private final CheckService<Card> cardCheckService;

    @GetMapping("/{id})")
    public ResponseEntity<Card> getCard(@PathVariable String id){
        Optional<Card> byId = cardCheckService.findById(id);
        return ResponseEntity.ok(new Card());
    }



}
