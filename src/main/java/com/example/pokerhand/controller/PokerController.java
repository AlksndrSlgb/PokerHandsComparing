package com.example.pokerhand.controller;

import com.example.pokerhand.service.PokerService;
import com.example.pokerhand.model.Hand;
import com.example.pokerhand.model.PokerHands;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@Tag(name="Контроллер дляранжирования покерных рук от лучшего к худшему")
public class PokerController {
    private final PokerService pokerService;

    @Autowired
    public PokerController(PokerService pokerService) {
        this.pokerService = pokerService;
    }

    @PostMapping("/compareHands")
    @Operation(summary = "Сравнение карт на руках")
    public List<Hand> compareHand(@RequestBody String[] pokerHands) {
        PokerHands hands = new PokerHands(pokerHands);
        return pokerService.rangeHands(hands);
    }
}
