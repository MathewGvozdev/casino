--liquibase formatted sql

--changeset mgvozdev:1
CREATE TABLE IF NOT EXISTS role
(
    id    UUID PRIMARY KEY,
    title VARCHAR(32) UNIQUE NOT NULL
);

--changeset mgvozdev:2
CREATE TABLE IF NOT EXISTS authority
(
    id         UUID PRIMARY KEY,
    permission VARCHAR(64) UNIQUE NOT NULL
);

--changeset mgvozdev:3
CREATE TABLE IF NOT EXISTS role_authority
(
    id           UUID PRIMARY KEY,
    role_id      UUID REFERENCES role (id),
    authority_id UUID REFERENCES authority (id)
);

--changeset mgvozdev:4
CREATE TABLE IF NOT EXISTS users
(
    id       UUID PRIMARY KEY,
    username VARCHAR(32) UNIQUE NOT NULL,
    password VARCHAR(64)        NOT NULL
);

--changeset mgvozdev:5
CREATE TABLE IF NOT EXISTS user_role
(
    id      UUID PRIMARY KEY,
    user_id UUID REFERENCES users (id),
    role_id UUID REFERENCES role (id)
);

--changeset mgvozdev:6
CREATE TABLE IF NOT EXISTS user_info
(
    id         UUID PRIMARY KEY,
    user_id    UUID REFERENCES users (id) UNIQUE NOT NULL,
    first_name VARCHAR(32)                       NOT NULL,
    last_name  VARCHAR(32)                       NOT NULL,
    shift      VARCHAR(8),
    hired_on   DATE,
    salary     NUMERIC
);

--changeset mgvozdev:7
CREATE TABLE IF NOT EXISTS profile
(
    id              UUID PRIMARY KEY,
    document_type   VARCHAR(16)         NOT NULL,
    country         CHAR(3)             NOT NULL,
    document_number VARCHAR(32)         NOT NULL,
    first_name      VARCHAR(32)         NOT NULL,
    last_name       VARCHAR(32)         NOT NULL,
    date_of_birth   DATE                NOT NULL,
    issue_date      DATE                NOT NULL,
    expiration_date DATE                NOT NULL,
    address         VARCHAR(64),
    phone_number    VARCHAR(16),
    membership_type VARCHAR(8)          NOT NULL,
    status          VARCHAR(16)         NOT NULL,
    total_deposit   NUMERIC,
    total_winnings  NUMERIC
);

--changeset mgvozdev:8
CREATE TABLE IF NOT EXISTS player
(
    id         UUID PRIMARY KEY,
    profile_id UUID REFERENCES profile (id) NOT NULL,
    opened_at  TIMESTAMP                    NOT NULL,
    buy_in     NUMERIC                      NOT NULL,
    closed_at  TIMESTAMP,
    avg_bet    INT
);

--changeset mgvozdev:9
CREATE TABLE IF NOT EXISTS dealer
(
    id         UUID PRIMARY KEY,
    first_name VARCHAR(32) NOT NULL,
    last_name  VARCHAR(32) NOT NULL,
    status     VARCHAR(16) NOT NULL
);

--changeset mgvozdev:10
CREATE TABLE IF NOT EXISTS tables
(
    id     UUID PRIMARY KEY,
    game   VARCHAR(32),
    number INT UNIQUE NOT NULL
);

--changeset mgvozdev:11
CREATE TABLE IF NOT EXISTS table_session
(
    id        UUID PRIMARY KEY,
    table_id  UUID REFERENCES tables (id) NOT NULL,
    dealer_id UUID REFERENCES dealer (id) NOT NULL,
    min_bet   INT                         NOT NULL,
    max_bet   INT                         NOT NULL,
    opened_at TIMESTAMP                   NOT NULL,
    opened_by UUID REFERENCES users (id)  NOT NULL,
    closed_at TIMESTAMP,
    closed_by UUID REFERENCES users (id)
);

--changeset mgvozdev:12
CREATE TABLE IF NOT EXISTS player_table_session
(
    id               UUID PRIMARY KEY,
    player_id        UUID REFERENCES player (id)         NOT NULL,
    table_session_id UUID REFERENCES table_session (id) NOT NULL,
    started_at       TIMESTAMP                           NOT NULL
);

--changeset mgvozdev:13
CREATE TABLE IF NOT EXISTS table_chip_set
(
    id       UUID PRIMARY KEY,
    chip     VARCHAR(16) NOT NULL,
    amount   INT,
    total    NUMERIC,
    table_id UUID REFERENCES tables (id)
);

--changeset mgvozdev:14
CREATE TABLE IF NOT EXISTS player_chip_set
(
    id        UUID PRIMARY KEY,
    chip      VARCHAR(16) NOT NULL,
    amount    INT,
    total     NUMERIC,
    player_id UUID REFERENCES player (id)
);

--changeset mgvozdev:15
CREATE TABLE IF NOT EXISTS reward
(
    id          UUID PRIMARY KEY,
    profile_id  UUID REFERENCES profile (id) NOT NULL,
    user_id     UUID REFERENCES users (id)   NOT NULL,
    type        VARCHAR(32)                  NOT NULL,
    given_at    TIMESTAMP                    NOT NULL,
    expires_at  DATE                         NOT NULL,
    redeemed_at TIMESTAMP,
    status      VARCHAR(16)                  NOT NULL
);

--changeset mgvozdev:16
CREATE TABLE IF NOT EXISTS report
(
    id             UUID PRIMARY KEY,
    user_id        UUID REFERENCES users (id) NOT NULL,
    date           DATE                       NOT NULL,
    notes          VARCHAR(256)               NOT NULL,
    total_drop_in  NUMERIC,
    total_winnings NUMERIC
);