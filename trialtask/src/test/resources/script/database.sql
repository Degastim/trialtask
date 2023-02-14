CREATE TABLE users (
    user_id         BIGINT AUTO_INCREMENT,
    email           VARCHAR(100),
    user_name       VARCHAR(10),
    password        VARCHAR(30),
    creation_date   TIMESTAMP(6),
    update_date     TIMESTAMP(6),
    PRIMARY KEY (user_id)
);
CREATE TABLE quotes (
    quote_id        BIGINT AUTO_INCREMENT,
    content         VARCHAR(255),
    user_id BIGINT  NOT NULL,
    creation_date   TIMESTAMP(6),
    update_date     TIMESTAMP(6),
    PRIMARY KEY (quote_id),
    FOREIGN KEY (user_id) references users(user_id)
);
CREATE TABLE votes (
    vote_id         BIGINT AUTO_INCREMENT,
    mark            TINYINT,
    quote_id        BIGINT,
    user_id         BIGINT,
    creation_date   TIMESTAMP(6),
    update_date     TIMESTAMP(6),
    PRIMARY KEY (vote_id),
    FOREIGN KEY (user_id) references users(user_id),
    FOREIGN KEY (quote_id) references quotes(quote_id)
);
