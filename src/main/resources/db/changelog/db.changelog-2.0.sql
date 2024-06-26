--liquibase formatted sql

--changeset mgvozdev:1
INSERT INTO authority(id, permission)
VALUES ('238723cf-4497-4a51-85f1-fd9a5807d074', 'ADD_USER'),
       ('446d9765-d4b5-4a6a-99a9-7b3dbd411ea5', 'DELETE_USER'),
       ('be508662-0621-43b9-9edd-b4daddcbcc0f', 'UPDATE_ROLE'),
       ('00c2ec32-4b44-4150-8e81-ad448f328a3e', 'FIND_PLAYER'),
       ('1b99dd3b-be29-4049-81f6-cd5292e89944', 'UPDATE_PLAYER'),
       ('8a18db1c-9b23-4cc7-86aa-30858d26b894', 'FIND_SESSION'),
       ('eea735c6-e89b-4c89-8c95-41dcc4c67f11', 'FIND_TABLE'),
       ('37e6a78e-b652-49c7-aa54-30974516825b', 'WRITE_REPORT'),
       ('2927c78e-e8b9-41cb-a712-ab72d05a5491', 'GIVE_REWARD'),
       ('1f8595d8-e97f-4c6f-802b-de27e02a1802', 'READ_REPORT');

--changeset mgvozdev:2
INSERT INTO role(id, title)
VALUES ('c6412eae-acb5-4467-8183-1bc528a7b925', 'ROLE_SUPERVISOR'),
       ('9de36d70-83af-44ba-854b-e46e9171b2ba', 'ROLE_PIT_BOSS'),
       ('06aaf8f5-13c3-48b3-93cc-d6075ebdd3c7', 'ROLE_SHIFT_MANAGER'),
       ('cc7e2bf9-70fe-4738-b7b8-f8b95338231c', 'ROLE_HOST'),
       ('258ff0f8-77ac-47b0-a002-55a03fa8205b', 'ROLE_ADMIN');

--changeset mgvozdev:3
INSERT INTO role_authority(role_id,
                           authority_id)
VALUES ((SELECT id FROM role WHERE title = 'ROLE_SUPERVISOR'),
        (SELECT id FROM authority WHERE permission = 'FIND_PLAYER')),
       ((SELECT id FROM role WHERE title = 'ROLE_SUPERVISOR'),
        (SELECT id FROM authority WHERE permission = 'UPDATE_PLAYER')),
       ((SELECT id FROM role WHERE title = 'ROLE_SUPERVISOR'),
        (SELECT id FROM authority WHERE permission = 'FIND_SESSION')),
       ((SELECT id FROM role WHERE title = 'ROLE_SUPERVISOR'),
        (SELECT id FROM authority WHERE permission = 'FIND_TABLE')),
       ((SELECT id FROM role WHERE title = 'ROLE_PIT_BOSS'),
        (SELECT id FROM authority WHERE permission = 'WRITE_REPORT')),
       ((SELECT id FROM role WHERE title = 'ROLE_HOST'),
        (SELECT id FROM authority WHERE permission = 'GIVE_REWARD')),
       ((SELECT id FROM role WHERE title = 'ROLE_SHIFT_MANAGER'),
        (SELECT id FROM authority WHERE permission = 'READ_REPORT')),
       ((SELECT id FROM role WHERE title = 'ROLE_ADMIN'),
        (SELECT id FROM authority WHERE permission = 'ADD_USER')),
       ((SELECT id FROM role WHERE title = 'ROLE_ADMIN'),
        (SELECT id FROM authority WHERE permission = 'DELETE_USER')),
       ((SELECT id FROM role WHERE title = 'ROLE_ADMIN'),
        (SELECT id FROM authority WHERE permission = 'UPDATE_ROLE'));

--changeset mgvozdev:4
INSERT INTO users(id, username, password)
VALUES ('616deeeb-b47f-4550-95f7-cb31dabd14ea', 'alamazza', '$2a$12$l8NQJEpuRk7.sHpA.VGjaOmXO0B0DUZ2Y3U8sKbgleo8mXix8ehTa'),
       ('5ba92800-9645-4d21-ab44-f78cb6ca5e58', 'rgreen', '$2a$12$9W29LSGUTGczlDTY9fmD8u1KL36hoYCj16dLrhn.N5ZKqSgG.aZmC'),
       ('7fa59e9a-2fa1-4d9d-88f3-8cb80563e3ab', 'abecker', '$2a$12$7SVftZjq6b.m2tL6ahf6mOsPYjm2KohHUtleINuue.WUou26RAbO6'),
       ('020625cf-ddc6-47da-a517-402fb4906f9f', 'samtally', '$2a$12$iMcyZfmZlQ4gDs93YHt7W.hW1FmgDXw1ttFiY2P5A3oSvhFAtMGna'),
       ('a9d86390-3940-4ed3-9182-1c1090386a4a', 'nick', '$2a$12$j7WuEBII67pRBLNTI/DPlOovtzfBdI5rLKAHzYlU0tz02JwiLUh2G'),
       ('b0b2dc10-b807-462b-932a-a6fc2499abde', 'admin', '$2a$12$8PxPTg4Vbajo4s8FA4jHu.bqPq2OUYMuUXtPPaIklmcf6RRV1HZt.');

--changeset mgvozdev:5
INSERT INTO user_info(id, user_id,
                      first_name, last_name, shift, hired_on, salary)
VALUES ('1c377d65-f00a-4258-86ef-3dcea7c9a353', (SELECT id FROM users WHERE username = 'alamazza'),
        'Anton', 'Lamazza', 'SWING', '2010-10-24', 33),
       ('0ac7fcae-0a95-4a6c-9a21-f02c495caf03', (SELECT id FROM users WHERE username = 'rgreen'),
        'Robert', 'Green', 'SWING', '2017-03-14', 30),
       ('d82b5c10-a3a9-47ff-a30b-4650fd540070', (SELECT id FROM users WHERE username = 'abecker'),
        'Allison', 'Becker', 'SWING', '2009-01-05', 41),
       ('97d5934e-2b7e-4795-a4c8-725349aff6c3', (SELECT id FROM users WHERE username = 'samtally'),
        'Samuel', 'Tally', 'SWING', '2012-06-01', 50),
       ('4f6550b1-8a31-4a4c-9424-f29b78377811', (SELECT id FROM users WHERE username = 'nick'),
        'Nicholas', 'Brown', 'SWING', '2016-04-17', 35),
       ('c9ead3bf-8136-46ff-ae7c-d3dc97e88533', (SELECT id FROM users WHERE username = 'admin'),
        'Andrew', 'Johnson', 'DAY', '2013-11-20', 36);

--changeset mgvozdev:6
INSERT INTO user_role(user_id,
                      role_id)
VALUES ((SELECT id FROM users WHERE username = 'alamazza'),
        (SELECT id FROM role WHERE title = 'ROLE_SUPERVISOR')),
       ((SELECT id FROM users WHERE username = 'rgreen'),
        (SELECT id FROM role WHERE title = 'ROLE_SUPERVISOR')),
       ((SELECT id FROM users WHERE username = 'abecker'),
        (SELECT id FROM role WHERE title = 'ROLE_PIT_BOSS')),
       ((SELECT id FROM users WHERE username = 'samtally'),
        (SELECT id FROM role WHERE title = 'ROLE_SHIFT_MANAGER')),
       ((SELECT id FROM users WHERE username = 'nick'),
        (SELECT id FROM role WHERE title = 'ROLE_HOST')),
       ((SELECT id FROM users WHERE username = 'admin'),
        (SELECT id FROM role WHERE title = 'ROLE_ADMIN'));

--changeset mgvozdev:7
INSERT INTO profile (id, document_type, country, document_number, first_name, last_name, date_of_birth,
                     issue_date, expiration_date, address, phone_number,
                     membership_type, status, total_deposit, total_winnings)
VALUES ('a6a43441-ac01-4de7-911e-94d7c6cb6fbd', 'ID_CARD', 'USA', '46728918', 'John', 'White', '1985-04-28',
        '2022-03-15', '2027-03-15', '35 Oak Road, PA, Philadelphia, 19114', '+1 224 890 6711',
        'BRONZE', 'PERMITTED', 1200, 600),
       ('c5120555-5e7d-4500-b710-c9c7f2a5f4c5', 'DRIVER_LICENSE', 'USA', '18401294', 'Olivia', 'Sin', '1990-10-11', '2023-12-04', '2028-12-04',
        '103 Springfield St, NJ, Cherry Hill, 08124', '+1 320 087 4534', 'SILVER', 'PERMITTED', 12000, 10000),
       ('61344bca-9608-4ab7-9385-43af2848f506', 'PASSPORT', 'MXC', '89019381', 'Ricardo', 'Ramirez', '1976-08-01',
        '2015-10-10', '2025-10-10', '1089 Spring Garden, PA, Philadelphia, 19135', '+1 871 210 3344',
        'SILVER', 'BANNED', 500, 11200),
       ('64158c86-bcd8-4267-bb00-16f9fe82051e', 'DRIVER_LICENSE', 'USA', '98139401', 'Henry', 'Styles', '1981-01-07',
        '2020-04-22', '2025-04-22', '657 3rd Ave , NY, New York, 10016', '+1 651 222 7641',
        'GOLD', 'PERMITTED', 56000, 32000),
       ('72f17432-54ed-4a85-a62d-b95cba05cf00', 'DRIVER_LICENSE', 'USA', '46791002', 'Lang', 'Lee', '1965-09-03',
        '2021-05-30', '2026-05-30', '902 Arch St, PA, Philadelphia, 19107', '+1 450 411 3090',
        'BLACK', 'PERMITTED', 185000, 170000);

--changeset mgvozdev:8
INSERT INTO dealer(id, first_name, last_name, status)
VALUES ('8b7c9463-e1c0-4b24-b5b0-78c63eda4ea5', 'Charles', 'Nolan', 'AVAILABLE'),
       ('559f360f-2102-4143-ac6d-24fe072df6b6', 'Joseph', 'Mooney', 'AVAILABLE'),
       ('615238c8-fcd7-4b17-a646-5abf37111291', 'Anthony', 'Gilardino', 'AVAILABLE');

--changeset mgvozdev:9
INSERT INTO tables(id, game, number)
VALUES ('46843dcb-3f0f-44cf-ae24-684b8ccbda26', 'BLACKJACK', 901),
       ('df2596d4-09a1-4842-8adc-803f6f314a62', 'BLACKJACK', 902),
       ('3ebbbdae-7205-487d-84be-d63488aa2336', 'ROULETTE', 701),
       ('94d78aa4-dda1-4e45-b3a2-d9bdd1a6d4a1', 'BACCARAT', 302);

--changeset mgvozdev:10
INSERT INTO player(id, profile_id,
                   opened_at, buy_in, closed_at, avg_bet)
VALUES ('4c36dfef-9a2f-414e-a2d8-1fb33a25c57e', (SELECT id FROM profile WHERE total_deposit = 12000),
        '2024-03-29 11:30:00', 1000, '2024-03-29 16:00:00', 50),
       ('a9f6c8cf-fec4-4ffc-99d6-13e6bc1e9b5f', (SELECT id FROM profile WHERE total_deposit = 56000),
        '2024-03-29 19:25:00', 5000, '2024-03-30 02:10:00', 150),
       ('d9ba4962-aa83-40b1-a027-794d5531e586', (SELECT id FROM profile WHERE total_deposit = 12000),
        '2024-03-30 16:00:00', 800, null, null),
       ('dfafbb82-414b-4dc5-872e-f9dc63b1ee42', (SELECT id FROM profile WHERE total_deposit = 1200),
        '2024-03-30 16:30:00', 100, null, null);

--changeset mgvozdev:11
INSERT INTO table_session(id,
                          table_id, dealer_id,
                          min_bet, max_bet,
                          opened_at, opened_by,
                          closed_at, closed_by)
VALUES ('d237dbdc-0da1-437b-adcb-270d47e0241e',
        (SELECT id FROM tables WHERE number = 901), (SELECT id FROM dealer WHERE first_name = 'Charles'),
        25, 500,
        '2024-03-29 10:00:00', (SELECT id FROM users WHERE username = 'alamazza'),
        '2024-03-29 22:00:00', (SELECT id FROM users WHERE username = 'rgreen')),
       ('ea0c1cae-7fa4-4bff-8be7-1f8e657f4c1f',
        (SELECT id FROM tables WHERE number = 902), (SELECT id FROM dealer WHERE first_name = 'Joseph'),
        25, 500,
        '2024-03-29 18:00:00', (SELECT id FROM users WHERE username = 'rgreen'),
        '2024-03-30 04:00:00', (SELECT id FROM users WHERE username = 'rgreen')),
       ('05bc647a-6081-46e9-8e10-d5c77b126656',
        (SELECT id FROM tables WHERE number = 902), (SELECT id FROM dealer WHERE first_name = 'Joseph'),
        15, 500,
        '2024-03-30 12:00:00', (SELECT id FROM users WHERE username = 'rgreen'),
        null, null);

--changeset mgvozdev:12
INSERT INTO player_table_session(id,
                                 player_id,
                                 table_session_id,
                                 started_at)
VALUES ('dec32eed-5858-4a58-83d9-75107d931f26',
        (SELECT id FROM player WHERE opened_at = '2024-03-29 11:30:00'),
        (SELECT id FROM table_session WHERE opened_at = '2024-03-29 10:00:00'),
        '2024-03-29 11:30:00'),
       ('ae788385-b23b-4e7f-af45-fc8ccd51a65a',
        (SELECT id FROM player WHERE opened_at = '2024-03-29 19:25:00'),
        (SELECT id FROM table_session WHERE opened_at = '2024-03-29 10:00:00'),
        '2024-03-29 19:25:00'),
       ('610e7d60-8fdd-408f-ae43-18f564b3ec32',
        (SELECT id FROM player WHERE opened_at = '2024-03-29 19:25:00'),
        (SELECT id FROM table_session WHERE opened_at = '2024-03-29 18:00:00'),
        '2024-03-29 21:30:00'),
       ('4b13accc-6e96-4f81-92c0-9cf5a2de8e29',
        (SELECT id FROM player WHERE opened_at = '2024-03-30 16:00:00'),
        (SELECT id FROM table_session WHERE opened_at = '2024-03-30 12:00:00'),
        '2024-03-29 16:00:00'),
       ('a8146e94-942d-4699-919c-87978ed39c37',
        (SELECT id FROM player WHERE opened_at = '2024-03-30 16:30:00'),
        (SELECT id FROM table_session WHERE opened_at = '2024-03-30 12:00:00'),
        '2024-03-29 16:30:00');

--changeset mgvozdev:13
INSERT INTO report(id, user_id,
                   date, notes,
                   total_drop_in, total_winnings)
VALUES ('3c0d021d-fb2c-4ffd-938d-04a40914ef02', (SELECT id FROM users WHERE username = 'abecker'),
        '2024-03-29', 'Advantage player was detected. He was banned from playing in the casino',
        null, null),
       ('537400e3-22f5-4ae8-ac0f-f33af133dea9', (SELECT id FROM users WHERE username = 'abecker'),
        '2024-03-29', 'Daily report',
        200000, 180000);

--changeset mgvozdev:14
INSERT INTO reward(id,
                   profile_id,
                   user_id,
                   type, given_at, expires_at, redeemed_at, status)
VALUES ('90509797-2eb0-4f8a-bff3-086913f1e0e7',
        (SELECT id FROM profile WHERE membership_type = 'BLACK'),
        (SELECT id FROM users WHERE username = 'nick'),
        'FREE_HOTEL_STAY', '2024-03-29 15:20:00', '2024-03-30', null, 'OPENED'),
       ('3c538ec9-70e9-4a26-b775-2188fa20399c',
        (SELECT id FROM profile WHERE membership_type = 'GOLD'),
        (SELECT id FROM users WHERE username = 'nick'),
        'FREE_MEAL', '2024-03-29 15:30:00', '2024-04-07', '2024-03-29 18:00:00', 'REDEEMED');

--changeset mgvozdev:15
INSERT INTO table_chip_set(id, chip, amount, total,
                           table_id)
VALUES ('056fbe6c-f58f-4201-a5f1-83f4e63c653a', 'BLACK', 40, 4000,
        (SELECT id FROM tables WHERE number = 901)),
       ('f80611f7-bc3a-4401-933f-9c534d821508', 'GREEN', 80, 2000,
        (SELECT id FROM tables WHERE number = 901)),
       ('940ac810-0e97-40f4-8237-4617213c48e5', 'RED', 100, 500,
        (SELECT id FROM tables WHERE number = 901)),
       ('9b9f65bf-e0cc-4eed-86b6-4da1ed5d49b2', 'PINK', 40, 100,
        (SELECT id FROM tables WHERE number = 901)),
       ('f314aa0a-01c7-4ccc-b4c0-e7987d5a9c1c', 'WHITE', 60, 60,
        (SELECT id FROM tables WHERE number = 901));

--changeset mgvozdev:16
INSERT INTO player_chip_set(id, chip, amount, total,
                            player_id)
VALUES ('b2fb1dc4-8a29-4d53-afdf-1f6a69f8ba92', 'BLACK', 2, 200,
        (SELECT id FROM player WHERE opened_at = '2024-03-30 16:00:00')),
       ('b3e70586-2621-4288-b8a5-079d8cc85794', 'GREEN', 40, 1000,
        (SELECT id FROM player WHERE opened_at = '2024-03-30 16:00:00')),
       ('b66cb839-77d0-43e9-aa8c-7d0179f83315', 'RED', 25, 125,
        (SELECT id FROM player WHERE opened_at = '2024-03-30 16:30:00'));
