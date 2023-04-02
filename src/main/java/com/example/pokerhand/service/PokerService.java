package com.example.pokerhand.service;

import com.example.pokerhand.Util.Util;
import com.example.pokerhand.model.Hand;
import com.example.pokerhand.model.PokerHands;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.pokerhand.Util.Util.getSortedValues;
import static com.example.pokerhand.model.HandRang.*;

@Service
public class PokerService {
    public List<Hand> rangeHands(PokerHands pokerHands) {
        checkHighCard(pokerHands);
        checkPairsAndThrees(pokerHands);
        checkTwoPairs(pokerHands);
        checkStraight(pokerHands);
        checkFlush(pokerHands);
        checkFullHouse(pokerHands);
        checkFourOfAKind(pokerHands);
        checkStraightFlush(pokerHands);
        List<Hand> handList = pokerHands.getHands();
        Collections.sort(handList);
        return handList;
    }

    private void checkHighCard(PokerHands pokerHands) {
        for (Hand pokerHand : pokerHands.getHands()) {
           List<Integer> sortedValues = Util.getSortedValues(pokerHand);
           if (sortedValues.contains(14)) {
               Util.setHandRang(pokerHand, HIGH_CARD);
           }
        }
    }

    private void checkPairsAndThrees(PokerHands pokerHands) {
        for (Hand pokerHand : pokerHands.getHands()) {
            int count = 0;
            for (int i = 0; i < pokerHand.getCards().size(); i++) {
                for (int j = i + 1; j < pokerHand.getCards().size(); j++) {
                    if (pokerHand.getCards().get(i).getValue().equals(pokerHand.getCards().get(j).getValue())) {
                        count++;
                    }
                }
            }
            if (count == 1 || count == 2) {
                Util.setHandRang(pokerHand, PAIR);
            }
            if (count == 3 || count == 4) {
                Util.setHandRang(pokerHand, THREE_OF_A_KIND);
            }
        }
    }

    private void checkTwoPairs(PokerHands pokerHands) {
        for (Hand hand : pokerHands.getHands()) {
            int counter = 0;
            if (hand.getRate() == PAIR.value) {
                for (int i = 0; i < hand.getCards().size(); i++) {
                    for (int j = 1; j < hand.getCards().size(); j++) {
                        if (hand.getCards().get(i).getValue().equals(hand.getCards().get(j).getValue())) {
                            counter++;
                        }
                    }
                }
            }
            if (counter == 7) {
                Util.setHandRang(hand, TWO_PAIR);
            }
        }

    }

    private void checkStraight(PokerHands pokerHands) {
        for (Hand hand : pokerHands.getHands()) {
            List<Integer> sortedValues = getSortedValues(hand);
            if (checkStraight(sortedValues)) {
                Util.setHandRang(hand, STRAIGHT);
            }
        }
    }

    private void checkFlush(PokerHands pokerHands) {
        for (Hand hand : pokerHands.getHands()) {
            if (checkSameSuitForFlush(hand)) {
                Util.setHandRang(hand, FLUSH);
            }
        }
    }

    private void checkFullHouse(PokerHands pokerHands) {
        for (Hand hand : pokerHands.getHands()) {
            if (hand.getRate() == PAIR.value || hand.getRate() == THREE_OF_A_KIND.getValue()) {
                Hand testHand = new Hand(hand);
                int countOfThree = 0;
                String firstCardValue = hand.getCards().get(0).getValue();
                for (int i = 1; i < hand.getCards().size(); i++) {
                    if (firstCardValue.equals(hand.getCards().get(i).getValue())) {
                        testHand.getCards().remove(hand.getCards().get(i));
                        countOfThree++;
                    }
                }
                if (countOfThree == 1 || countOfThree == 2) {
                    testHand.getCards().remove(hand.getCards().get(0));
                    if (testHand.getCards().get(0).getValue().equals(testHand.getCards().get(1).getValue())) {
                        Util.setHandRang(hand, FULL_HOUSE);
                    }
                }
            }
        }
    }

    private void checkFourOfAKind(PokerHands pokerHands) {
        for (Hand hand : pokerHands.getHands()) {
            int counter = 0;
            for (int i = 0; i < hand.getCards().size(); i++) {
                for (int j = i + 1; j < hand.getCards().size(); j++) {
                    if (hand.getCards().get(i).getValue().equals(hand.getCards().get(j).getValue())) {
                        counter++;
                    }
                }
            }
            if (counter == 6) {
                Util.setHandRang(hand, FOUR_OF_A_KIND);

            }
        }
    }

    private void checkStraightFlush(PokerHands pokerHands) {
        for (Hand hand : pokerHands.getHands()) {
            if (checkSameSuitForFlush(hand)) {
                List<Integer> sortedValues = getSortedValues(hand);
                if (checkStraight(sortedValues)) {
                    Util.setHandRang(hand, STRAIGHT_FLUSH);

                }
            }
        }
    }

    private boolean checkSameSuitForFlush(Hand hand) {
        return hand.getCards().get(0).getSuit().equals(hand.getCards().get(4).getSuit());
    }

    private boolean checkStraight(List<Integer> sortedValues) {
        int prev = -1;
        for (Integer sortedValue : sortedValues) {
            if (prev == -1 || (prev + 1) == sortedValue) {
                prev = sortedValue;
            } else {
                return false;
            }
        }
        return true;
    }
}