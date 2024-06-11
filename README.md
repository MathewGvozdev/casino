# Casino Management Tool Backend

## Description

The application is designed for the management staff in live casinos. The main purpose is accounting money (chips) around
players and table racks, assigning rewards for certain players and creating daily reports or statistics.

### Class Diagram

![PhotoDependencyClasses](https://github.com/MathewGvozdev/casino/blob/master/class-diagram.jpg)

### Database Structure

![PhotoDependencyClasses](https://github.com/MathewGvozdev/casino/blob/master/database.jpg)

## Table role

| Column name | Type        | Description                                |
|-------------|-------------|--------------------------------------------|
| id          | UUID        | PRIMARY KEY Unique identifier for the role |
| title       | VARCHAR(32) | role title (name)                          |

## Table authority

| Column name | Type        | Description                                     |
|-------------|-------------|-------------------------------------------------|
| id          | UUID        | PRIMARY KEY Unique identifier for the authority |
| permission  | VARCHAR(64) | authority name                                  |

## Table users

| Column name | Type        | Description                                |
|-------------|-------------|--------------------------------------------|
| id          | UUID        | PRIMARY KEY Unique identifier for the user |
| username    | VARCHAR(32) | Unique username                            |
| password    | VARCHAR(64) | User password                              |

## Table user_info

| Column name | Type        | Description                                     |
|-------------|-------------|-------------------------------------------------|
| id          | UUID        | PRIMARY KEY Unique identifier for the user info |
| user_id     | UUID        | FOREIGN KEY REFERENCES users(id) UNIQUE         |
| first_name  | VARCHAR(32) | User's first name                               |
| last_name   | VARCHAR(32) | User's last name                                |
| shift       | VARCHAR(8)  | User's shift - Enum Shift                       |
| hired_on    | DATE        | Date the user was hired                         |
| salary      | NUMERIC     | User's salary                                   |

## Table profile

| Column name     | Type        | Description                                          |
|-----------------|-------------|------------------------------------------------------|
| id              | UUID        | PRIMARY KEY Unique identifier for the player profile |
| document_type   | VARCHAR(16) | Document type - Enum DocumentType                    |
| country         | CHAR(3)     | Country code                                         |
| document_number | VARCHAR(32) | Document number                                      |
| first_name      | VARCHAR(32) | First name                                           |
| last_name       | VARCHAR(32) | Last name                                            |
| date_of_birth   | DATE        | Date of birth                                        |
| issue_date      | DATE        | Issue date                                           |
| expiration_date | DATE        | Expiration date                                      |
| address         | VARCHAR(64) | Address                                              |
| phone_number    | VARCHAR(16) | Phone number                                         |
| membership_type | VARCHAR(8)  | Membership type - Enum MembershipType                |
| status          | VARCHAR(16) | Status - Enum ProfileStatus                          |
| total_deposit   | NUMERIC     | Total deposit                                        |
| total_winnings  | NUMERIC     | Total winnings                                       |

## Table player

| Column name | Type      | Description                                                |
|-------------|-----------|------------------------------------------------------------|
| id          | UUID      | PRIMARY KEY Unique identifier for the player (his session) |
| profile_id  | UUID      | FOREIGN KEY REFERENCES profile(id)                         |
| opened_at   | TIMESTAMP | Player session opened at (started at)                      |
| buy_in      | NUMERIC   | Buy-in amount                                              |
| closed_at   | TIMESTAMP | Player session closed at time                              |
| avg_bet     | INT       | Average bet amount                                         |

## Table dealer

| Column name | Type        | Description                                  |
|-------------|-------------|----------------------------------------------|
| id          | UUID        | PRIMARY KEY Unique identifier for the dealer |
| first_name  | VARCHAR(32) | Dealer's first name                          |
| last_name   | VARCHAR(32) | Dealer's last name                           |
| status      | VARCHAR(16) | Dealer's status - Enum DealerStatus          |

## Table tables

| Column name | Type        | Description                                 |
|-------------|-------------|---------------------------------------------|
| id          | UUID        | PRIMARY KEY Unique identifier for the table |
| game        | VARCHAR(32) | Table game name - Enum Game                 |
| number      | INT         | Unique table number                         |

## Table table_session

| Column name | Type      | Description                                         |
|-------------|-----------|-----------------------------------------------------|
| id          | UUID      | PRIMARY KEY Unique identifier for the table session |
| table_id    | UUID      | FOREIGN KEY REFERENCES tables(id)                   |
| dealer_id   | UUID      | FOREIGN KEY REFERENCES dealer(id)                   |
| min_bet     | INT       | Minimum bet amount                                  |
| max_bet     | INT       | Maximum bet amount                                  |
| opened_at   | TIMESTAMP | Table opened at time                                |
| opened_by   | UUID      | FOREIGN KEY REFERENCES users(id)                    |
| closed_at   | TIMESTAMP | Table closed at time                                |
| closed_by   | UUID      | FOREIGN KEY REFERENCES users(id)                    |

## Table table_chip_set

| Column name | Type        | Description                                         |
|-------------|-------------|-----------------------------------------------------|
| id          | UUID        | PRIMARY KEY Unique identifier for the table chipset |
| chip        | VARCHAR(16) | Chip type - Enum Chip                               |
| amount      | INT         | Amount of chips                                     |
| total       | NUMERIC     | Total value of chips                                |
| table_id    | UUID        | FOREIGN KEY REFERENCES tables(id)                   |

## Table player_chip_set

| Column name | Type        | Description                                          |
|-------------|-------------|------------------------------------------------------|
| id          | UUID        | PRIMARY KEY Unique identifier for the player chipset |
| chip        | VARCHAR(16) | Chip type - Enum Chip                                |
| amount      | INT         | Amount of chips                                      |
| total       | NUMERIC     | Total value of chips                                 |
| player_id   | UUID        | FOREIGN KEY REFERENCES player(id)                    |

## Table reward

| Column name | Type        | Description                                  |
|-------------|-------------|----------------------------------------------|
| id          | UUID        | PRIMARY KEY Unique identifier for the reward |
| profile_id  | UUID        | FOREIGN KEY REFERENCES profile(id)           |
| user_id     | UUID        | FOREIGN KEY REFERENCES users(id)             |
| type        | VARCHAR(32) | Reward type - Enum RewardType                |
| given_at    | TIMESTAMP   | Reward given at time                         |
| expires_at  | DATE        | Reward expiration date                       |
| redeemed_at | TIMESTAMP   | Reward redeemed at time                      |
| status      | VARCHAR(16) | Reward status - Enum RewardStatus            |

## Table report

| Column name    | Type         | Description                                  |
|----------------|--------------|----------------------------------------------|
| id             | UUID         | PRIMARY KEY Unique identifier for the report |
| user_id        | UUID         | FOREIGN KEY REFERENCES users(id)             |
| date           | DATE         | Report date                                  |
| notes          | VARCHAR(256) | Report notes                                 |
| total_drop_in  | NUMERIC      | Total drop in amount                         |
| total_winnings | NUMERIC      | Total winnings amount                        |

## Link table role<->authority

| Column name    | Type       | Description                          |
|----------------|------------|--------------------------------------|
| id             | UUID       | PRIMARY KEY Unique ID                |
| role_id        | UUID       | FOREIGN KEY REFERENCES role(id)      |
| authority_id   | UUID       | FOREIGN KEY REFERENCES authority(id) |

## Link table user<->role

| Column name | Type       | Description                      |
|-------------|------------|----------------------------------|
| id          | UUID       | PRIMARY KEY Unique ID            |
| user_id     | UUID       | FOREIGN KEY REFERENCES users(id) |
| role_id     | UUID       | FOREIGN KEY REFERENCES role(id)  |

## Link table player<->table_session

| Column name        | Type       | Description                                       |
|--------------------|------------|---------------------------------------------------|
| id                 | UUID       | PRIMARY KEY Unique ID                             |
| player_id          | UUID       | FOREIGN KEY REFERENCES player(id)                 |
| table_session_id   | UUID       | FOREIGN KEY REFERENCES table_session(id)          |
| started_at         | TIMESTAMP  | The time that player started playing at the table |

### Project structure

- `src/main/java/com/mgvozdev/casino` — source
- `src/main/resources` — configuration resources

### Used Technologies:

Programming Language: Java
Frameworks and Libraries:
Spring Framework:
Spring Boot
Spring Security
Hibernate (via Spring Data JPA)
SpringDoc OpenAPI
Lombok
MapStruct
Swagger (via swagger-annotations)
BCrypt 
Liquibase 
Database: PostgreSQL (main database), H2 (for testing)
Build and Testing Tools:
Gradle
Jacoco (for code coverage analysis)
API: RESTful API for client-server interaction

### Validators

- `@UuidChecker` — custom annotation for checking UUID format
- `@ChipsChecker` — custom annotation for checking each chip in a set

## Contacts

If you have questions or suggestions, please contact me:

- Name: Mathew Gvozdev
- Email: mathew15gen@gmail.com
- GitHub: [MathewGvozdev](https://github.com/MathewGvozdev)