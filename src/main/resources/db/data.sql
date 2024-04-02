INSERT INTO authority(id, permission)
VALUES (gen_random_uuid(), 'ADD_USER'),
       (gen_random_uuid(), 'DELETE_USER'),
       (gen_random_uuid(), 'UPDATE_ROLE'),
       (gen_random_uuid(), 'FIND_PLAYER'),
       (gen_random_uuid(), 'UPDATE_PLAYER'),
       (gen_random_uuid(), 'FIND_SESSION'),
       (gen_random_uuid(), 'FIND_TABLE'),
       (gen_random_uuid(), 'WRITE_REPORT'),
       (gen_random_uuid(), 'GIVE_REWARD'),
       (gen_random_uuid(), 'READ_REPORT');

INSERT INTO role(id, title)
VALUES (gen_random_uuid(), 'SUPERVISOR'),
       (gen_random_uuid(), 'PIT_BOSS'),
       (gen_random_uuid(), 'SHIFT_MANAGER'),
       (gen_random_uuid(), 'HOST'),
       (gen_random_uuid(), 'ADMIN');

INSERT INTO role_authority(id, role_id, authority_id)
VALUES (gen_random_uuid(),
        (SELECT id FROM role WHERE title = 'SUPERVISOR'),
        (SELECT id FROM authority WHERE permission = 'FIND_PLAYER')),
       (gen_random_uuid(),
        (SELECT id FROM role WHERE title = 'SUPERVISOR'),
        (SELECT id FROM authority WHERE permission = 'UPDATE_PLAYER')),
       (gen_random_uuid(),
        (SELECT id FROM role WHERE title = 'SUPERVISOR'),
        (SELECT id FROM authority WHERE permission = 'FIND_SESSION')),
       (gen_random_uuid(),
        (SELECT id FROM role WHERE title = 'SUPERVISOR'),
        (SELECT id FROM authority WHERE permission = 'FIND_TABLE')),
       (gen_random_uuid(),
        (SELECT id FROM role WHERE title = 'PIT_BOSS'),
        (SELECT id FROM authority WHERE permission = 'WRITE_REPORT')),
       (gen_random_uuid(),
        (SELECT id FROM role WHERE title = 'HOST'),
        (SELECT id FROM authority WHERE permission = 'GIVE_REWARD')),
       (gen_random_uuid(),
        (SELECT id FROM role WHERE title = 'SHIFT_MANAGER'),
        (SELECT id FROM authority WHERE permission = 'READ_REPORT')),
       (gen_random_uuid(),
        (SELECT id FROM role WHERE title = 'ADMIN'),
        (SELECT id FROM authority WHERE permission = 'ADD_USER')),
       (gen_random_uuid(),
        (SELECT id FROM role WHERE title = 'ADMIN'),
        (SELECT id FROM authority WHERE permission = 'DELETE_USER')),
       (gen_random_uuid(),
        (SELECT id FROM role WHERE title = 'ADMIN'),
        (SELECT id FROM authority WHERE permission = 'UPDATE_ROLE'));

INSERT INTO users(id, username, password)
VALUES (gen_random_uuid(), 'alamazza', 'sv1234'),
       (gen_random_uuid(), 'rgreen', 'sv1111'),
       (gen_random_uuid(), 'abecker', 'pb1234'),
       (gen_random_uuid(), 'samtally', 'sm1234'),
       (gen_random_uuid(), 'nick', 'ho3333'),
       (gen_random_uuid(), 'admin', 'admin');

INSERT INTO users_info(id, user_id, first_name, last_name, shift, hired_on, salary)
VALUES (gen_random_uuid(), (SELECT id FROM users WHERE username = 'alamazza'), 'Anton', 'Lamazza', 'SWING',
        '2010-10-24', 33),
       (gen_random_uuid(), (SELECT id FROM users WHERE username = 'rgreen'), 'Robert', 'Green', 'SWING', '2017-03-14',
        30),
       (gen_random_uuid(), (SELECT id FROM users WHERE username = 'abecker'), 'Allison', 'Becker', 'SWING',
        '2009-01-05', 41),
       (gen_random_uuid(), (SELECT id FROM users WHERE username = 'samtally'), 'Samuel', 'Tally', 'SWING', '2012-06-01',
        50),
       (gen_random_uuid(), (SELECT id FROM users WHERE username = 'nick'), 'Nicholas', 'Brown', 'SWING', '2016-04-17',
        35),
       (gen_random_uuid(), (SELECT id FROM users WHERE username = 'admin'), 'Andrew', 'Johnson', 'DAY', '2013-11-20',
        36);

INSERT INTO users_role(id, user_id, role_id)
VALUES (gen_random_uuid(),
        (SELECT id FROM users WHERE username = 'alamazza'),
        (SELECT id FROM role WHERE title = 'SUPERVISOR')),
       (gen_random_uuid(),
        (SELECT id FROM users WHERE username = 'rgreen'),
        (SELECT id FROM role WHERE title = 'SUPERVISOR')),
       (gen_random_uuid(),
        (SELECT id FROM users WHERE username = 'abecker'),
        (SELECT id FROM role WHERE title = 'SUPERVISOR')),
       (gen_random_uuid(),
        (SELECT id FROM users WHERE username = 'abecker'),
        (SELECT id FROM role WHERE title = 'PIT_BOSS')),
       (gen_random_uuid(),
        (SELECT id FROM users WHERE username = 'samtally'),
        (SELECT id FROM role WHERE title = 'SUPERVISOR')),
       (gen_random_uuid(),
        (SELECT id FROM users WHERE username = 'samtally'),
        (SELECT id FROM role WHERE title = 'PIT_BOSS')),
       (gen_random_uuid(),
        (SELECT id FROM users WHERE username = 'samtally'),
        (SELECT id FROM role WHERE title = 'SHIFT_MANAGER')),
       (gen_random_uuid(),
        (SELECT id FROM users WHERE username = 'nick'),
        (SELECT id FROM role WHERE title = 'HOST')),
       (gen_random_uuid(),
        (SELECT id FROM users WHERE username = 'nick'),
        (SELECT id FROM role WHERE title = 'SHIFT_MANAGER')),
       (gen_random_uuid(),
        (SELECT id FROM users WHERE username = 'admin'),
        (SELECT id FROM role WHERE title = 'SUPERVISOR')),
       (gen_random_uuid(),
        (SELECT id FROM users WHERE username = 'admin'),
        (SELECT id FROM role WHERE title = 'PIT_BOSS')),
       (gen_random_uuid(),
        (SELECT id FROM users WHERE username = 'admin'),
        (SELECT id FROM role WHERE title = 'SHIFT_MANAGER')),
       (gen_random_uuid(),
        (SELECT id FROM users WHERE username = 'admin'),
        (SELECT id FROM role WHERE title = 'HOST')),
       (gen_random_uuid(),
        (SELECT id FROM users WHERE username = 'admin'),
        (SELECT id FROM role WHERE title = 'admin'));

INSERT INTO player (id, membership_type, status, total_deposit, total_winnings)
VALUES (gen_random_uuid(), 'BRONZE', 'PERMITTED', 1200, 600),
       (gen_random_uuid(), 'SILVER', 'PERMITTED', 12000, 10000),
       (gen_random_uuid(), 'SILVER', 'BANNED', 500, 11200),
       (gen_random_uuid(), 'GOLD', 'PERMITTED', 56000, 32000),
       (gen_random_uuid(), 'BLACK', 'PERMITTED', 185000, 170000);

INSERT INTO profile (id, player_id, document_type, country, document_number,
                     first_name, last_name, date_of_birth, issue_date, expiration_date,
                     address, document_image, phone_number)
VALUES (gen_random_uuid(), (SELECT id FROM player WHERE membership_type = 'BRONZE'),
        'ID_CARD', 'USA', '46728918', 'John', 'White', '1985-04-28', '2022-03-15', '2027-03-15',
        '35 Oak Road, PA, Philadelphia, 19114', '/path/to/image/photo1.jpg', '+1 224 890 6711'),
       (gen_random_uuid(), (SELECT id FROM player WHERE membership_type = 'SILVER' AND status = 'PERMITTED'),
        'DRIVER_LICENSE', 'USA', '18401294', 'Olivia', 'Sin', '1990-10-11', '2023-12-04', '2028-12-04',
        '103 Springfield St, NJ, Cherry Hill, 08124', '/path/to/image/photo2.jpg', '+1 320 087 4534'),
       (gen_random_uuid(), (SELECT id FROM player WHERE membership_type = 'SILVER' AND status = 'BANNED'),
        'PASSPORT', 'MXC', '89019381', 'Ricardo', 'Ramirez', '1976-08-01', '2015-10-10', '2025-10-10',
        '1089 Spring Garden, PA, Philadelphia, 19135', '/path/to/image/photo3.jpg', '+1 871 210 3344'),
       (gen_random_uuid(), (SELECT id FROM player WHERE membership_type = 'GOLD'),
        'DRIVER_LICENSE', 'USA', '98139401', 'Henry', 'Styles', '1981-01-07', '2020-04-22', '2025-04-22',
        '657 3rd Ave , NY, New York, 10016', '/path/to/image/photo4.jpg', '+1 651 222 7641'),
       (gen_random_uuid(), (SELECT id FROM player WHERE membership_type = 'BLACK'),
        'DRIVER_LICENSE', 'USA', '46791002', 'Lang', 'Lee', '1965-09-03', '2021-05-30', '2026-05-30',
        '902 Arch St, PA, Philadelphia, 19107', '/path/to/image/photo5.jpg', '+1 450 411 3090');

INSERT INTO dealer(id, first_name, last_name, status)
VALUES (gen_random_uuid(), 'Charles', 'Nolan', 'AVAILABLE'),
       (gen_random_uuid(), 'Joseph', 'Mooney', 'AVAILABLE'),
       (gen_random_uuid(), 'Anthony', 'Gilardino', 'AVAILABLE');

INSERT INTO tables(id, game, number)
VALUES (gen_random_uuid(), 'BLACKJACK', 901),
       (gen_random_uuid(), 'BLACKJACK', 902),
       (gen_random_uuid(), 'ROULETTE', 701),
       (gen_random_uuid(), 'BACCARAT', 302);

INSERT INTO player_session(id, player_id, opened_at, buy_in, closed_at, avg_bet)
VALUES (gen_random_uuid(),
        (SELECT id FROM player WHERE total_deposit = 12000),
        '2024-03-29 11:30:00', 1000, '2024-03-29 16:00:00', 50),
       (gen_random_uuid(),
        (SELECT id FROM player WHERE total_deposit = 56000),
        '2024-03-29 19:25:00', 5000, '2024-03-30 02:10:00', 150),
       (gen_random_uuid(),
        (SELECT id FROM player WHERE total_deposit = 12000),
        '2024-03-30 16:00:00', 800, null, null),
       (gen_random_uuid(),
        (SELECT id FROM player WHERE total_deposit = 1200),
        '2024-03-30 16:30:00', 100, null, null);

INSERT INTO tables_session(id,
                           table_id, dealer_id,
                           min_bet, max_bet,
                           opened_at, opened_by,
                           closed_at, closed_by)
VALUES (gen_random_uuid(),
        (SELECT id FROM tables WHERE number = 901), (SELECT id FROM dealer WHERE first_name = 'Charles'),
        25, 500,
        '2024-03-29 10:00:00', (SELECT id FROM users WHERE username = 'alamazza'),
        '2024-03-29 22:00:00', (SELECT id FROM users WHERE username = 'rgreen')),
       (gen_random_uuid(),
        (SELECT id FROM tables WHERE number = 902), (SELECT id FROM dealer WHERE first_name = 'Joseph'),
        25, 500,
        '2024-03-29 18:00:00', (SELECT id FROM users WHERE username = 'rgreen'),
        '2024-03-30 04:00:00', (SELECT id FROM users WHERE username = 'rgreen')),
       (gen_random_uuid(),
        (SELECT id FROM tables WHERE number = 902), (SELECT id FROM dealer WHERE first_name = 'Joseph'),
        15, 500,
        '2024-03-30 12:00:00', (SELECT id FROM users WHERE username = 'rgreen'),
        null, null);

INSERT INTO session(id, player_session_id, table_session_id, started_at)
VALUES (gen_random_uuid(),
        (SELECT id FROM player_session WHERE opened_at = '2024-03-29 11:30:00'),
        (SELECT id FROM tables_session WHERE opened_at = '2024-03-29 10:00:00'),
        '2024-03-29 11:30:00'),
       (gen_random_uuid(),
        (SELECT id FROM player_session WHERE opened_at = '2024-03-29 19:25:00'),
        (SELECT id FROM tables_session WHERE opened_at = '2024-03-29 10:00:00'),
        '2024-03-29 19:25:00'),
       (gen_random_uuid(),
        (SELECT id FROM player_session WHERE opened_at = '2024-03-29 19:25:00'),
        (SELECT id FROM tables_session WHERE opened_at = '2024-03-29 18:00:00'),
        '2024-03-29 21:30:00'),
       (gen_random_uuid(),
        (SELECT id FROM player_session WHERE opened_at = '2024-03-30 16:00:00'),
        (SELECT id FROM tables_session WHERE opened_at = '2024-03-30 12:00:00'),
        '2024-03-29 16:00:00'),
       (gen_random_uuid(),
        (SELECT id FROM player_session WHERE opened_at = '2024-03-30 16:30:00'),
        (SELECT id FROM tables_session WHERE opened_at = '2024-03-30 12:00:00'),
        '2024-03-29 16:30:00');

INSERT INTO report(id, user_id, date, notes, total_drop_in, total_winnings)
VALUES (gen_random_uuid(),
        (SELECT id FROM users WHERE username = 'abecker'),
        '2024-03-29', 'Advantage player was detected. He was banned from playing in the casino',
        null, null),
       (gen_random_uuid(),
        (SELECT id FROM users WHERE username = 'abecker'),
        '2024-03-29', 'Daily report',
        200000, 180000);

INSERT INTO reward(id, player_id, user_id, type, given_at, expires_at, redeemed_at, status)
VALUES (gen_random_uuid(),
        (SELECT id FROM player WHERE membership_type = 'BLACK'),
        (SELECT id FROM users WHERE username = 'nick'),
        'FREE_HOTEL_STAY', '2024-03-29 15:20:00', '2024-03-30', null, 'OPENED'),
       (gen_random_uuid(),
        (SELECT id FROM player WHERE membership_type = 'GOLD'),
        (SELECT id FROM users WHERE username = 'nick'),
        'FREE_MEAL', '2024-03-29 15:30:00', '2024-04-07', '2024-03-29 18:00:00', 'REDEEMED');

INSERT INTO table_chip_set(id, chip, amount, total, table_id)
VALUES (gen_random_uuid(),
        'BLACK', 40, 4000, (SELECT id FROM tables WHERE number = 901)),
       (gen_random_uuid(),
        'GREEN', 80, 2000, (SELECT id FROM tables WHERE number = 901)),
       (gen_random_uuid(),
        'RED', 100, 500, (SELECT id FROM tables WHERE number = 901)),
       (gen_random_uuid(),
        'PINK', 40, 100, (SELECT id FROM tables WHERE number = 901)),
       (gen_random_uuid(),
        'WHITE', 60, 60, (SELECT id FROM tables WHERE number = 901));

INSERT INTO player_session_chip_set(id, chip, amount, total, player_session_id)
VALUES (gen_random_uuid(),
        'BLACK', 2, 200, (SELECT id FROM player_session WHERE opened_at = '2024-03-30 16:00:00')),
       (gen_random_uuid(),
        'GREEN', 40, 1000, (SELECT id FROM player_session WHERE opened_at = '2024-03-30 16:00:00')),
       (gen_random_uuid(),
        'RED', 25, 125, (SELECT id FROM player_session WHERE opened_at = '2024-03-30 16:30:00'));