CREATE TABLE role
(
    id    UUID PRIMARY KEY,
    title VARCHAR(32) UNIQUE NOT NULL
);

CREATE TABLE authority
(
    id         UUID PRIMARY KEY,
    permission VARCHAR(64) UNIQUE NOT NULL
);

CREATE TABLE role_authority
(
    id           UUID PRIMARY KEY,
    role_id      UUID REFERENCES role (id),
    authority_id UUID REFERENCES authority (id)
);

CREATE TABLE users
(
    id       UUID PRIMARY KEY,
    username VARCHAR(32) UNIQUE NOT NULL,
    password VARCHAR(64)        NOT NULL,
    role_id  UUID REFERENCES role (id)
);

CREATE TABLE users_role
(
    id      UUID PRIMARY KEY,
    user_id UUID REFERENCES users (id),
    role_id UUID REFERENCES role (id)
);

CREATE TABLE users_info
(
    id         UUID PRIMARY KEY,
    user_id    UUID REFERENCES users (id) UNIQUE NOT NULL,
    first_name VARCHAR(32)                       NOT NULL,
    last_name  VARCHAR(32)                       NOT NULL,
    shift      VARCHAR(8),
    hired_on   DATE,
    salary     NUMERIC
);

CREATE TABLE player
(
    id              UUID PRIMARY KEY,
    membership_type VARCHAR(8)  NOT NULL,
    status          VARCHAR(16) NOT NULL,
    total_deposit   NUMERIC,
    total_winnings  NUMERIC
);

CREATE TABLE profile
(
    id              UUID PRIMARY KEY,
    player_id       UUID REFERENCES player (id) UNIQUE NOT NULL,
    document_type   VARCHAR(16)                        NOT NULL,
    country         CHAR(3)                            NOT NULL,
    document_number VARCHAR(32)                        NOT NULL,
    first_name      VARCHAR(32)                        NOT NULL,
    last_name       VARCHAR(32)                        NOT NULL,
    date_of_birth   DATE                               NOT NULL,
    issue_date      DATE                               NOT NULL,
    expiration_date DATE                               NOT NULL,
    address         VARCHAR(64),
    document_image  VARCHAR(128) UNIQUE                NOT NULL,
    phone_number    VARCHAR(16)
);

CREATE TABLE player_session
(
    id         UUID PRIMARY KEY,
    player_id  UUID REFERENCES player (id),
    opened_at  TIMESTAMP NOT NULL,
    buy_in     NUMERIC   NOT NULL,
    closed_at  TIMESTAMP,
    avg_bet    INT
);

CREATE TABLE dealer
(
    id         UUID PRIMARY KEY,
    first_name VARCHAR(32) NOT NULL,
    last_name  VARCHAR(32) NOT NULL,
    status     VARCHAR(16) NOT NULL
);

CREATE TABLE tables
(
    id          UUID PRIMARY KEY,
    game        VARCHAR(32),
    number      INT UNIQUE NOT NULL
);

CREATE TABLE tables_session
(
    id        UUID PRIMARY KEY,
    table_id  UUID REFERENCES tables (id)  NOT NULL,
    dealer_id UUID REFERENCES dealer (id) NOT NULL,
    min_bet   INT                         NOT NULL,
    max_bet   INT                         NOT NULL,
    opened_at TIMESTAMP                   NOT NULL,
    opened_by UUID REFERENCES users (id)  NOT NULL,
    closed_at TIMESTAMP,
    closed_by UUID REFERENCES users (id)
);

CREATE TABLE session
(
    id                UUID PRIMARY KEY,
    player_session_id UUID REFERENCES player_session (id) NOT NULL,
    table_session_id  UUID REFERENCES tables_session (id)  NOT NULL,
    started_at        TIMESTAMP                           NOT NULL
);

CREATE TABLE table_chip_set
(
    id       UUID PRIMARY KEY,
    chip     VARCHAR(16) NOT NULL ,
    amount   INT,
    total    NUMERIC,
    table_id UUID REFERENCES tables (id)
);

CREATE TABLE player_session_chip_set
(
    id       UUID PRIMARY KEY,
    chip     VARCHAR(16) NOT NULL ,
    amount   INT,
    total    NUMERIC,
    player_session_id UUID REFERENCES player_session(id)
);

CREATE TABLE reward
(
    id          UUID PRIMARY KEY,
    player_id   UUID REFERENCES player (id) NOT NULL,
    user_id     UUID REFERENCES users (id)  NOT NULL,
    type        VARCHAR(32)                 NOT NULL,
    given_at    TIMESTAMP                   NOT NULL,
    expires_at  DATE                        NOT NULL,
    redeemed_at TIMESTAMP,
    status      VARCHAR(16)                 NOT NULL
);

CREATE TABLE report
(
    id             UUID PRIMARY KEY,
    user_id        UUID REFERENCES users (id) NOT NULL,
    date           DATE                       NOT NULL,
    notes          VARCHAR(256)               NOT NULL,
    total_drop_in  NUMERIC,
    total_winnings NUMERIC
);

