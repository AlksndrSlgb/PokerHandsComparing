package com.example.pokerhand.model;

import com.example.pokerhand.Util.Util;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.pokerhand.model.HandRang.*;

@Data
public class Hand implements Comparable<Hand> {
    private Long handNumber;
    private List<Card> cards = new ArrayList<>();

    private HandRang combination = NOTHING;
    private int rate = NOTHING.getValue();

    public Hand(List<String> cards, Long id) {
        this.handNumber = id;
        for (String value : cards) {
            Card card = new Card(value.split("")[0], value.split("")[1], id);
            this.cards.add(card);
        }
    }

    public Hand(Hand hand) {
        this.cards = new ArrayList<>();
        cards.addAll(hand.getCards());
    }

    public Hand(String cards, Long id) {
        Arrays.stream(cards.split(" ")).forEach(element -> {
                Card card = new Card(element.split("")[0], element.split("")[1], id);
                this.cards.add(card);
        });
    }

    @Override
    public int compareTo(Hand hand) {
        Util.finalComparing(hand, this, PAIR, HIGH_PAIR);
        Util.finalComparing(hand, this, TWO_PAIR, HIGH_TWO_PAIR);
        Util.finalComparing(hand, this, THREE_OF_A_KIND, HIGH_THREE_OF_A_KIND);
        Util.finalComparing(hand, this, STRAIGHT, HIGH_STRAIGHT);
        Util.finalComparing(hand, this, FLUSH, HIGH_FLUSH);
        Util.finalComparing(hand, this, FULL_HOUSE, HIGH_FULL_HOUSE);
        Util.finalComparing(hand, this, FOUR_OF_A_KIND, HIGH_FOUR_OF_A_KIND);
        Util.finalComparing(hand, this, STRAIGHT_FLUSH, HIGH_STRAIGHT_FLUSH);
        return Integer.compare(this.getRate(), hand.getRate());
    }

    @JsonIgnore
    public String getAllValues() {
        StringBuilder values = new StringBuilder();
        for (Card card : cards) {
            values.append(card.getValue());
        }
        return values.toString();
    }
}
