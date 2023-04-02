# PokerHandsComparing
Simple web controller for comparing cards in Poker.

## Required environment
1. Java 17
2. maven 3

## How to install
1. git clone https://github.com/dfrzn/PokerHandsComparing.git
2. cd PokerHandsComparing
3. mvn package
4. cd /target
5. java -jar pokerhand-0.0.1.jar (The app should run on 8080 port)

## How to test
Make POST request to 
> http://localhost:8080/compareHands 

with body :
```json
[ "2S 3S 4S TH 6D", "QS TS AS KS JS" ]
```
where strings is hand witch consist of 5 cards.

Where first value is value of card (2, 3, 4, 5, 6, 7, 8, 9, T(en),
J(ack), Q(ueen), K(ing), A(ce)) and second value is suit (S(pades), H(earts), D(iamonds), C(lubs))

In response you should get JSON like: 
```json
[
  {
    "handNumber": 2,
    "cards": [
      {
        "value": "Q",
        "suit": "S"
      },
      {
        "value": "T",
        "suit": "S"
      },
      {
        "value": "A",
        "suit": "S"
      },
      {
        "value": "K",
        "suit": "S"
      },
      {
        "value": "J",
        "suit": "S"
      }
    ],
    "combination": "STRAIGHT_FLUSH",
    "rate": 2
  },
  {
    "handNumber": 1,
    "cards": [
      {
        "value": "2",
        "suit": "S"
      },
      {
        "value": "3",
        "suit": "S"
      },
      {
        "value": "4",
        "suit": "S"
      },
      {
        "value": "T",
        "suit": "H"
      },
      {
        "value": "6",
        "suit": "D"
      }
    ],
    "combination": "NOTHING",
    "rate": 18
  }
]
```

### For your convenience, you can test app on http://13.53.164.142:8080/swagger-ui/index.html with Swagger.