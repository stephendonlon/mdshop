DROP TABLE IF EXISTS cart_event;

CREATE TABLE cart_event
(
    id           BIGINT UNIQUE PRIMARY KEY,
    cart_id      VARCHAR(255) NOT NULL,
    payload      VARCHAR(255) NOT NULL,
    created_time DATETIME     NOT NULL,
    action       VARCHAR(255) NOT NULL
);

