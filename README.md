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
- [x] The user can buy and sell cryptocurrency. ***(Not covered by tests)***
- [x] The user can trade cryptocurrency for another cryptocurrency ***(Not covered by tests)***
- [ ] The user can transfer the cryptocurrency to another user.
- [ ] The user must have a portfolio where all his cryptocurrencies are stored.
- [ ] The user should be able to find out the value of his portfolio in any currency presented (USD,RUB,UAH).
- [ ] Each sale of cryptocurrency charges a certain percentage of the commission from the user.
- [ ] On the cryptocurrency page, it should be possible to find out the history of the fall, increase in the price of this cryptocurrency.
- [x] Each user has access to their transaction history. ***(Not covered by tests)***
- [ ] For security purposes, authentication via [Fortify](https://github.com/fortify), i.e. a USB key, must be implemented.

## Non-functional requirements:
- Safety
- Fault tolerance
- Efficiency
- Scalability
- Ease of use

## Progress
> Here are the important problems I encountered and the ways to solve them. It will also describe added features that were interesting for me. I am looking forward to criticism from more experienced developers with examples of more productive solutions.

### Using AOP to realize the history of cryptocurrency transactions.
It was an interesting experience to work with aspect-oriented programming, especially that I had the opportunity to implement something other than "dumb" logging, although the area in which I applied it is not far from logging, but it is something more.

```Java
/**
 * @author sabextech
 * The methods marked with this annotation will be defined as a cryptocurrency transaction,
 * which must be saved in the transaction history after execution.
 
 * @see com.example.TradeHub.web.dtos.CryptoResponse
 * To use this annotation, the method must return a CryptoResponse object
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CryptoTransaction {

}
```
