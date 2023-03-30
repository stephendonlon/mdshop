# Cart Command Service

This project is a reactive Micronaut application that provides an API for managing shopping cart events. It uses MySQL
as its database and Kafka for event streaming.

## Features

- Create a new shopping cart
- Update the contents of an existing shopping cart
- Publish cart events to a Kafka topic 

## Prerequisites

- JDK 17
- Docker
- Docker Compose

## Getting Started

1. Clone the repository:

```
git clone https://github.com/your-username/cart-command.git
cd cart-command
```

2. Start the MySQL and Kafka services using Docker Compose:

```
docker-compose up -d
```

3. Build the project:

```
./gradlew build
```

4. Run the application:

```
./gradlew run
```

The application will start on port 8080 by default.

## API Endpoints

### Create a new shopping cart

POST `/carts`

Request body:

```json
{
  "item": {
    "productId": "product-1",
    "quantity": 1
  },
  "action": "ADD"
}
```

Response:

```json
{
  "cartId": "generated-cart-id"
}
```

### Update an existing shopping cart

POST `/carts/{cartId}/update`

Request body:

```json
{
  "item": {
    "productId": "product-2",
    "quantity": 2
  },
  "action": "ADD"
}
```

Response: HTTP status 200 (OK)

## Running Tests

To run the tests, execute the following command:

```
./gradlew test
```
This will run both unit and integration tests.


