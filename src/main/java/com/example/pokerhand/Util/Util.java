package com.example.pokerhand.Util;

import com.example.pokerhand.model.Hand;
import com.example.pokerhand.model.HandRang;

import java.util.*;

public class Util {
    public static List<Integer> getSortedValues(Hand hand) {
        Map<String, Integer> hashedMap = getHashedValues();
        String[] values = hand.getAllValues().split("");
        List<Integer> valuesList = new ArrayList<>();

        Arrays.stream(values).forEach(element -> {
            if (hashedMap.containsKey(element)) {
                valuesList.add(hashedMap.get(element));
            } else {
                valuesList.add(Integer.valueOf(element));
            }
        });
        Collections.sort(valuesList);
        return valuesList;
    }

    public static void setHandRang(Hand hand, HandRang handRang) {
        hand.setRate(handRang.getValue());
        hand.setCombination(handRang);
    }

    public static void finalComparing(Hand hand, Hand compHand,
                                      HandRang handRang, HandRang highRang) {
        if (hand.getCombination() == compHand.getCombination() && hand.getCombination() == handRang) {
            List<Integer> sortedValuesOtherHand = Util.getSortedValues(hand);
            List<Integer> sortedValuesThisHand = Util.getSortedValues(compHand);
            if (sortedValuesOtherHand.get(4) > sortedValuesThisHand.get(4)) {
                Util.setHandRang(hand, highRang);
            } else if (sortedValuesOtherHand.get(4) < sortedValuesThisHand.get(4)) {
                Util.setHandRang(compHand, highRang);
            }
        }
    }

    public static Map<String, Integer> getHashedValues() {
        return Map.of(
                "T", 10,
                "J", 11,
                "Q", 12,
                "K", 13,
                "A", 14);
    }
}
