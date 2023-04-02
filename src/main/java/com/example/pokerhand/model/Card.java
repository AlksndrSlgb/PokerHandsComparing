package com.example.pokerhand.model;

import com.example.pokerhand.exception.WrongCardException;
import lombok.Data;

import java.util.List;

@Data
public class Card {
    String value;
    String suit;

    public Card(String value, String suit, Long handId) {
        List<String> allowedValueSymbols = List.of("23456789TJQKA".split(""));
        List<String> allowedSuitSymbols = List.of("SHDC".split(""));
        if (allowedSuitSymbols.contains(suit)
                && allowedValueSymbols.contains(value)) {
            this.value = value;
            this.suit = suit;
        } else {
            throw new WrongCardException("Wrong card in hand number: " + handId);
        }
    }
}
