package com.example.pokerhand.model;

import lombok.Getter;

public enum HandRang {
    HIGH_STRAIGHT_FLUSH(1),
    STRAIGHT_FLUSH(2),
    HIGH_FOUR_OF_A_KIND(3),
    FOUR_OF_A_KIND(4),
    HIGH_FULL_HOUSE(5),
    FULL_HOUSE(6),
    HIGH_FLUSH(7),
    FLUSH(8),
    HIGH_STRAIGHT(9),
    STRAIGHT(10),
    HIGH_THREE_OF_A_KIND(11),
    THREE_OF_A_KIND(12),
    HIGH_TWO_PAIR(13),
    TWO_PAIR(14),
    HIGH_PAIR(15),
    PAIR(16),
    HIGH_CARD(17),
    NOTHING(18);

    @Getter
    public int value;

    private HandRang(int value)
    {
        this.value = value;
    }
}
