package com.example.pokerhand.model;

import com.example.pokerhand.exception.WrongNumberOfCardsException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PokerHands {
    List<Hand> hands = new ArrayList<>();

    public PokerHands(String[] pokerHand) {
        long id = 1L;
        for (String hand : pokerHand) {
            List<String> cards = List.of(hand.trim().split(" "));
            if (cards.size() != 5) {
                throw new WrongNumberOfCardsException("Wrong number of cards in hand number: "
                        + id);
            } else {
                hands.add(new Hand(cards, id++));
            }
        }
    }

    public PokerHands(List<Hand> hands) {
        this.hands = hands;
    }
}
