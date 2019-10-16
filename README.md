## Task:
Design and implement a RESTful API (including data model and the backing implementation) for
money transfers between accounts.

## How to test:
    $ ./gradlew test

## How to run:
    $ ./gradlew run

## Application
The application will be available for requests after start by url http://localhost:8080.  
Two accounts with id 1 and 2 were added for demo purpose.  
Transfer of money with the same currency will be supported in current version of application. 

## Used technologies:
- Kotlin
- Gradle
- Micronaut
- Jooq
- H2
- Mapstruct
- Junit 5

## REST API:
- GET `/accounts` - get all accounts
- GET `/accounts/{id}` - get account by id
- POST `/accounts` - create new account


- GET `/transfers?fromAccount={id}&toAccount={id}` - get transfers with optional fromAccount, toAccount query parameters
- GET `/transfers/{id}` - get transfer by id
- POST `/transfers` - transfer money between 2 accounts

## Request samples:
GET `/accounts/{id}`  
Response:
```json
{
  "id": 1,
  "name": "ruble savings account",
  "currency": "RUB",
  "balance": "1000.00"
}
```    

POST `/accounts`  
Request:  
```json
{
  "name": "euro savings account",
  "currency": "EUR",
  "balance": "1500.00"
}
```
Response:
```
Http Status: 201 Created
Location: /accounts/2
```

GET `transfers/{id}`  
Response:
```json
{
  "id": 1,
  "fromAccount": 1,
  "toAccount": 2,
  "amount": "1000.00",
  "currency": "RUB",
  "date": "2019-01-01T10:10:00+03"
}
``` 

POST `/transfers`
Request:
```json
{
  "fromAccount": 1,
  "toAccount": 2,
  "amount": "1000.00",
  "currency": "RUB"
}
```

Response:
```
Http Status: 200
```
