package com.example.pokerhand;

import com.example.pokerhand.exception.WrongCardException;
import com.example.pokerhand.exception.WrongNumberOfCardsException;
import com.example.pokerhand.model.Hand;
import com.example.pokerhand.model.PokerHands;
import com.example.pokerhand.service.PokerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.example.pokerhand.model.HandRang.*;


class PokerServiceTest {
    PokerService service = new PokerService();
    @Test
    void pairAndHighCardTest() {
        //given
        Hand pairHand = new Hand("2S 2D 3H QD KC", 1L);
        Hand highCardHand = new Hand("2S AD 3H QD KC", 2L);
        List<Hand> handList = new ArrayList<>();
        handList.add(highCardHand);
        handList.add(pairHand);
        PokerHands pokerHands = new PokerHands(handList);
        //when
        List<Hand> hand = service.rangeHands(pokerHands);
        //then
        Assertions.assertEquals(PAIR.getValue(), hand.get(0).getRate());
        Assertions.assertEquals(HIGH_CARD.getValue(), hand.get(1).getRate());

    }

    @Test
    void twoPairsAndThreeKindTest() {
        //given
        Hand twoPairHand = new Hand("2S KD 3H 2C 3C", 1L);
        Hand threeKindHand = new Hand("2S 2D QH 4D 2C", 2L);
        List<Hand> handList = new ArrayList<>();
        handList.add(threeKindHand);
        handList.add(twoPairHand);
        PokerHands pokerHands = new PokerHands(handList);
        //when
        List<Hand> hand = service.rangeHands(pokerHands);
        //then
        Assertions.assertEquals(THREE_OF_A_KIND.getValue(), hand.get(0).getRate());
        Assertions.assertEquals(TWO_PAIR.getValue(), hand.get(1).getRate());

    }

    @Test
    void straightAndFlushTest() {
        //given
        Hand straightHand = new Hand("KS QD JH AC TC", 1L);
        Hand flushHand = new Hand("2S 2S QS 4S 2S", 2L);
        List<Hand> handList = new ArrayList<>();
        handList.add(flushHand);
        handList.add(straightHand);
        PokerHands pokerHands = new PokerHands(handList);
        //when
        List<Hand> hand = service.rangeHands(pokerHands);
        //then
        Assertions.assertEquals(FLUSH.getValue(), hand.get(0).getRate());
        Assertions.assertEquals(STRAIGHT.getValue(), hand.get(1).getRate());
    }

    @Test
    void fullHouseAndFourKindTest() {
        //given
        Hand fullHouseHand = new Hand("KS 7D KH KC 7C", 1L);
        Hand fourKindHand = new Hand("6S 2S 6D 6H 6C", 2L);
        List<Hand> handList = new ArrayList<>();
        handList.add(fullHouseHand);
        handList.add(fourKindHand);
        PokerHands pokerHands = new PokerHands(handList);
        //when
        List<Hand> hand = service.rangeHands(pokerHands);
        //then
        Assertions.assertEquals(FOUR_OF_A_KIND.getValue(), hand.get(0).getRate());
        Assertions.assertEquals(FULL_HOUSE.getValue(), hand.get(1).getRate());
    }

    @Test
    void flushAndStraightFlush() {
        //given
        Hand flushHand = new Hand("2C 2C QC 4C 2C", 1L);
        Hand straightFlushHand = new Hand("3S 5S 7S 4S 6S", 2L);
        List<Hand> handList = new ArrayList<>();
        handList.add(flushHand);
        handList.add(straightFlushHand);
        PokerHands pokerHands = new PokerHands(handList);
        //when
        List<Hand> hand = service.rangeHands(pokerHands);
        //then
        Assertions.assertEquals(STRAIGHT_FLUSH.getValue(), hand.get(0).getRate());
        Assertions.assertEquals(FLUSH.getValue(), hand.get(1).getRate());
    }

    @Test
    void generalTest() {
        //given
        Hand highCardHand = new Hand("AC 2S 3D 5C 9H", 1L);
        Hand flushHand = new Hand("2C 2C QC 4C 2C", 2L);
        Hand flushHandMore = new Hand("2D 2D QD 4D 2D", 3L);
        Hand emptyHand = new Hand("9C TD JS 2H 3D", 4L);
        Hand straightFlushHand = new Hand("3S 5S 7S 4S 6S", 5L);
        List<Hand> handList = new ArrayList<>();
        handList.add(highCardHand);
        handList.add(flushHand);
        handList.add(emptyHand);
        handList.add(flushHandMore);
        handList.add(straightFlushHand);
        PokerHands pokerHands = new PokerHands(handList);
        //when
        List<Hand> hand = service.rangeHands(pokerHands);
        //then
        Assertions.assertEquals(STRAIGHT_FLUSH.getValue(), hand.get(0).getRate());
        Assertions.assertEquals(FLUSH.getValue(), hand.get(1).getRate());
        Assertions.assertEquals(FLUSH.getValue(), hand.get(2).getRate());
        Assertions.assertEquals(HIGH_CARD.getValue(), hand.get(3).getRate());
        Assertions.assertEquals(NOTHING.getValue(), hand.get(4).getRate());
    }

    @Test
    void disputeSituations() {
        //given
        Hand straightFlushHand = new Hand("3S 5S 7S 4S 6S", 1L);
        Hand highStraightFlushHand = new Hand("QS TS AS JS KS", 2L);

        Hand fourKindHand = new Hand("6S 2S 6D 6H 6C", 3L);
        Hand highFourKindHand = new Hand("TS 2S TD TH TC", 4L);

        Hand twoPairHand = new Hand("2S KD 3H 2C 3C", 5L);
        Hand highTwoPairHand = new Hand("2S KD AH 2C AC", 6L);

        List<Hand> handList = new ArrayList<>();
        handList.add(highStraightFlushHand);
        handList.add(fourKindHand);
        handList.add(highFourKindHand);
        handList.add(twoPairHand);
        handList.add(highTwoPairHand);
        handList.add(straightFlushHand);
        PokerHands pokerHands = new PokerHands(handList);
        //when
        List<Hand> hand = service.rangeHands(pokerHands);
        //then
        Assertions.assertEquals(HIGH_STRAIGHT_FLUSH.getValue(), hand.get(0).getRate());
        Assertions.assertEquals(STRAIGHT_FLUSH.getValue(), hand.get(1).getRate());
        Assertions.assertEquals(HIGH_FOUR_OF_A_KIND.getValue(), hand.get(2).getRate());
        Assertions.assertEquals(FOUR_OF_A_KIND.getValue(), hand.get(3).getRate());
        Assertions.assertEquals(HIGH_TWO_PAIR.getValue(), hand.get(4).getRate());
        Assertions.assertEquals(TWO_PAIR.getValue(), hand.get(5).getRate());
    }

    @Test
    void shouldGetWrongNumberOfCardsException() {
        Exception ex = Assertions.assertThrows(WrongNumberOfCardsException.class, () ->
                new PokerHands(new String[]{"2H 3C"}));
        Assertions.assertEquals("Wrong number of cards in hand number: 1",
                ex.getMessage());
    }

    @Test
    void shouldGetWrongCardException() {
        Exception ex = Assertions.assertThrows(WrongCardException.class, () ->
                new PokerHands(new String[]{"2H 3C 4D 5S 12U"}));
        Assertions.assertEquals("Wrong card in hand number: 1",
                ex.getMessage());
    }
}