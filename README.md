# TradeHub
### This is a web application for the exchange, purchase, sale of cryptocurrencies. A pet project aimed at mastering the skills of developing financial applications.

## Technical specification
> The terms of reference will change and be supplemented over time, as my understanding of the subject area will grow over time, in general, this is what happens in development, the customer does not immediately understand what he wants from the application)

Tech stack:
- Dev:
  - Java Spring
  - Spring Security
  - JDBC
  - PostgreSQL
  - LogBack & slf4j
  - RabbitMQ (maybe)
  - Liquibase
- DB:
  - PostgreSQL
  - Cache: Redis

## Functional requirements:
- [ ] The user can buy and sell cryptocurrency. 
- [ ] The user can transfer the cryptocurrency to another user.
- [ ] The user must have a portfolio where all his cryptocurrencies are stored.
- [ ] The user should be able to find out the value of his portfolio in any currency presented (USD,RUB,UAH).
- [ ] Each sale of cryptocurrency charges a certain percentage of the commission from the user.
- [ ] On the cryptocurrency page, it should be possible to find out the history of the fall, increase in the price of this cryptocurrency.
- [ ] Each user has access to their transaction history.
- [ ] For security purposes, authentication via [Fortify](https://github.com/fortify), i.e. a USB key, must be implemented.

## Non-functional requirements:
- Safety
- Fault tolerance
- Efficiency
- Scalability
- Ease of use

## Progress
> Here are the important problems that I had to face and how I solved them, so I want to get criticism from more experienced developers with an example of more productive solutions
