--liquibase formatted sql

--changeset mgvozdev:1
CREATE TABLE roles
(
    id    UUID PRIMARY KEY,
    title VARCHAR(32) UNIQUE NOT NULL
);

--changeset mgvozdev:2
CREATE TABLE authorities
(
    id         UUID PRIMARY KEY,
    permission VARCHAR(64) UNIQUE NOT NULL
);

--changeset mgvozdev:3
CREATE TABLE roles_authorities
(
    id           UUID PRIMARY KEY,
    roles_id     UUID REFERENCES roles (id),
    authority_id UUID REFERENCES authorities (id)
);

--changeset mgvozdev:4
CREATE TABLE users
(
    id       UUID PRIMARY KEY,
    username VARCHAR(32) UNIQUE NOT NULL,
    password VARCHAR(64)        NOT NULL,
    role_id  UUID REFERENCES roles (id)
);

--changeset mgvozdev:5
CREATE TABLE user_info
(
    id         UUID PRIMARY KEY,
    user_id    UUID REFERENCES users (id) UNIQUE NOT NULL,
    first_name VARCHAR(32)                       NOT NULL,
    last_name  VARCHAR(32)                       NOT NULL,
    shift      VARCHAR(8),
    hired_on   DATE,
    salary     NUMERIC
);

--changeset mgvozdev:6
CREATE TABLE players
(
    id              UUID PRIMARY KEY,
    membership_type VARCHAR(8)  NOT NULL,
    status          VARCHAR(16) NOT NULL,
    total_deposit   NUMERIC,
    total_winnings  NUMERIC
);

--changeset mgvozdev:7
CREATE TABLE profiles
(
    id              UUID PRIMARY KEY,
    player_id       UUID REFERENCES players (id) UNIQUE NOT NULL,
    document_type   VARCHAR(16)                         NOT NULL,
    country         CHAR(3)                             NOT NULL,
    document_number VARCHAR(32)                         NOT NULL,
    first_name      VARCHAR(32)                         NOT NULL,
    last_name       VARCHAR(32)                         NOT NULL,
    date_of_birth   DATE                                NOT NULL,
    issue_date      DATE                                NOT NULL,
    expiration_date DATE                                NOT NULL,
    address         VARCHAR(64),
    document_image  VARCHAR(128) UNIQUE                 NOT NULL,
    phone_number    VARCHAR(16)
);

--changeset mgvozdev:8
CREATE TABLE player_sessions
(
    id         UUID PRIMARY KEY,
    player_id  UUID REFERENCES players (id),
    opened_at  TIMESTAMP NOT NULL,
    buy_in     NUMERIC   NOT NULL,
    closed_at  TIMESTAMP,
    cashed_out NUMERIC,
    avg_bet    INT
);

--changeset mgvozdev:9
CREATE TABLE dealers
(
    id         UUID PRIMARY KEY,
    first_name VARCHAR(32) NOT NULL,
    last_name  VARCHAR(32) NOT NULL,
    status     VARCHAR(16) NOT NULL
);

--changeset mgvozdev:10
CREATE TABLE tables
(
    id          UUID PRIMARY KEY,
    game        VARCHAR(32),
    number      INT UNIQUE NOT NULL,
    grey5000    INT,
    orange1000  INT,
    purple500   INT,
    black100    INT,
    green25     INT,
    yellow20    INT,
    red5        INT,
    pink2_50    NUMERIC,
    white1      INT,
    quarter0_25 NUMERIC
);

--changeset mgvozdev:11
CREATE TABLE table_sessions
(
    id        UUID PRIMARY KEY,
    table_id  UUID REFERENCES tables (id)  NOT NULL,
    dealer_id UUID REFERENCES dealers (id) NOT NULL,
    min_bet   INT                          NOT NULL,
    max_bet   INT                          NOT NULL,
    opened_at TIMESTAMP                    NOT NULL,
    opened_by UUID REFERENCES users (id)   NOT NULL,
    closed_at TIMESTAMP,
    closed_by UUID REFERENCES users (id)
);

--changeset mgvozdev:12
CREATE TABLE sessions
(
    id                UUID PRIMARY KEY,
    player_session_id UUID REFERENCES player_sessions (id) NOT NULL,
    table_session_id  UUID REFERENCES table_sessions (id)  NOT NULL,
    started_at        TIMESTAMP                            NOT NULL
);

--changeset mgvozdev:13
CREATE TABLE rewards
(
    id          UUID PRIMARY KEY,
    player_id   UUID REFERENCES players (id) NOT NULL,
    user_id     UUID REFERENCES users (id)   NOT NULL,
    type        VARCHAR(32)                  NOT NULL,
    given_at    TIMESTAMP                    NOT NULL,
    expires_at  DATE                         NOT NULL,
    redeemed_at TIMESTAMP,
    status      VARCHAR(16)                  NOT NULL
);

--changeset mgvozdev:14
CREATE TABLE reports
(
    id             UUID PRIMARY KEY,
    user_id        UUID REFERENCES users (id) NOT NULL,
    date           DATE                       NOT NULL,
    notes          VARCHAR(256)               NOT NULL,
    total_drop_in  NUMERIC,
    total_winnings NUMERIC
);

