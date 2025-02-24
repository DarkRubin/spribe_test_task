-- liquibase formatted sql

-- changeset Vadim:2350239232258-1
INSERT INTO public.units (id, description, price)
VALUES ('7f2afa72-87ad-4d86-bda1-83178740874d', 'Cool description', 2000),
       ('0a816f59-811d-49f9-94ad-20842c92643a', 'Very cool description', 2800),
       ('3ed9020f-a8c6-4a78-a252-15221352a90f', 'Normal description', 1900),
       ('c398a109-4ab4-49cb-9c28-81aff44e8278', 'Empty description', 999999),
       ('bfbd9a5a-a6c8-48d8-9933-e2c9529153a7', 'Good description', 2030),
       ('c60fe36d-a7b3-4a98-8caa-3d5d7d85f53e', 'Epic description', 8000),
       ('8d438da4-3450-49e6-a567-d8fe118869e3', 'legendary description', 40700),
       ('8948c5c9-5486-404e-970a-e9f8e3c4299d', 'Natural description', 2030),
       ('694a1fad-525e-474a-96da-7bd8036d428a', 'Ultra cool description', 9000),
       ('76953943-06ff-43cd-84b6-43be7f2d5592', 'Super description', 2001),
       ('7719c8cd-276e-45fe-b54b-b2f5160e155b', 'Genius description', 28800),
       ('ce7ad47f-82f8-444e-98fd-5214cf09bc71', 'Best apartments in world', 326627);

-- changeset Vadim:2350239232258-2
INSERT INTO public.unit_properties (unit_id, floor, rooms_count, type)
VALUES ('7f2afa72-87ad-4d86-bda1-83178740874d', 1, 2, 'FLAT'),
       ('0a816f59-811d-49f9-94ad-20842c92643a', 2, 3, 'FLAT'),
       ('3ed9020f-a8c6-4a78-a252-15221352a90f', 4, 1, 'APARTMENTS'),
       ('c398a109-4ab4-49cb-9c28-81aff44e8278', 4, 2, 'FLAT'),
       ('bfbd9a5a-a6c8-48d8-9933-e2c9529153a7', 7, 7, 'FLAT'),
       ('c60fe36d-a7b3-4a98-8caa-3d5d7d85f53e', 2, 3, 'FLAT'),
       ('8d438da4-3450-49e6-a567-d8fe118869e3', 3, 4, 'APARTMENTS'),
       ('8948c5c9-5486-404e-970a-e9f8e3c4299d', 5, 3, 'FLAT'),
       ('694a1fad-525e-474a-96da-7bd8036d428a', 6, 4, 'APARTMENTS'),
       ('76953943-06ff-43cd-84b6-43be7f2d5592', 7, 2, 'FLAT'),
       ('7719c8cd-276e-45fe-b54b-b2f5160e155b', 8, 3, 'FLAT'),
       ('ce7ad47f-82f8-444e-98fd-5214cf09bc71', 9, 4, 'APARTMENTS');

-- changeset Vadim:2350239232258-3
INSERT INTO users (username, password)
VALUES
    ('alice', 'hashed_pass_1'),
    ('bob', 'hashed_pass_2'),
    ('charlie', 'hashed_pass_3'),
    ('david', 'hashed_pass_4'),
    ('eva', 'hashed_pass_5'),
    ('frank', 'hashed_pass_6'),
    ('grace', 'hashed_pass_7'),
    ('henry', 'hashed_pass_8'),
    ('isabel', 'hashed_pass_9'),
    ('jack', 'hashed_pass_10'),
    ('kate', 'hashed_pass_11'),
    ('leo', 'hashed_pass_12'),
    ('mia', 'hashed_pass_13'),
    ('nathan', 'hashed_pass_14'),
    ('olivia', 'hashed_pass_15'),
    ('peter', 'hashed_pass_16'),
    ('quincy', 'hashed_pass_17'),
    ('rachel', 'hashed_pass_18'),
    ('steve', 'hashed_pass_19'),
    ('tina', 'hashed_pass_20');

-- changeset Vadim:2350239232258-4
INSERT INTO events (id, start_time, end_time, unit_id, user_id)
VALUES
    ('0ba6ec4e-84aa-4303-a6db-19e1e0d33bf0', '2025-02-04 19:00:00', '2025-02-10 21:00:00', '7f2afa72-87ad-4d86-bda1-83178740874d', 1),
    ('53dcfee5-db34-4f85-94ce-ee4e6b130ab1', '2025-02-11 10:00:00', '2025-02-07 12:00:00', '0a816f59-811d-49f9-94ad-20842c92643a', 2),
    ('4414e06c-7ddc-4d62-a55b-e749cb1b4fd4', '2025-02-12 11:00:00', '2025-02-22 13:00:00', 'bfbd9a5a-a6c8-48d8-9933-e2c9529153a7', 1),
    ('d25b4bca-242b-4dfa-9d51-c258479aaae7', '2025-02-04 14:00:00', '2025-02-10 21:00:00', '3ed9020f-a8c6-4a78-a252-15221352a90f', 2),
    ('7bcb9038-2706-4146-9d6a-01428ccde9df', '2025-02-01 10:00:00', '2025-02-07 12:00:00', 'c398a109-4ab4-49cb-9c28-81aff44e8278', 3),
    ('20223702-a1d7-4305-a078-4f99c0c422bf', '2025-02-03 12:00:00', '2025-02-05 14:00:00', 'c60fe36d-a7b3-4a98-8caa-3d5d7d85f53e', 4),
    ('92c49c14-259b-48e8-8cb8-34b36841cec6', '2025-02-04 13:00:00', '2025-02-07 15:00:00', '8d438da4-3450-49e6-a567-d8fe118869e3', 5),
    ('ae2b64ec-e894-40a4-a49e-deb8e72d72f8', '2025-02-05 14:00:00', '2025-02-08 16:00:00', '8948c5c9-5486-404e-970a-e9f8e3c4299d', 6),
    ('8e0ab841-c178-4478-b340-532db7027227', '2025-02-01 13:00:00', '2025-03-07 18:00:00', 'c398a109-4ab4-49cb-9c28-81aff44e8278', 7),
    ('9952b135-5c2a-41e7-ac25-c63cafa4e9bc', '2025-02-03 12:00:00', '2025-05-05 10:00:00', 'c60fe36d-a7b3-4a98-8caa-3d5d7d85f53e', 8),
    ('2dd488ea-dc98-4d8a-aecf-31341107206e', '2025-02-19 13:00:00', '2025-02-07 13:00:00', '8d438da4-3450-49e6-a567-d8fe118869e3', 9),
    ('7d521d71-1318-4875-adee-b45589bce1e0', '2025-02-06 14:00:00', '2025-02-08 16:00:00', '8948c5c9-5486-404e-970a-e9f8e3c4299d', 11),
    ('fe600e3c-6d3c-4c3e-a3dd-145afd31ee66', '2025-02-06 15:00:00', '2025-02-09 17:00:00', '694a1fad-525e-474a-96da-7bd8036d428a', 12),
    ('091e0d96-c351-400b-ad7d-12de6435bdff', '2025-02-07 16:00:00', '2025-02-14 18:00:00', '76953943-06ff-43cd-84b6-43be7f2d5592', 13),
    ('b50134fc-24c7-482b-9612-97d1816dd139', '2025-02-08 17:00:00', '2025-02-18 19:00:00', '7719c8cd-276e-45fe-b54b-b2f5160e155b', 14),
    ('759c8882-b3aa-441b-9d36-6851df251410', '2025-02-28 16:00:00', '2025-04-30 18:00:00', '76953943-06ff-43cd-84b6-43be7f2d5592', 15),
    ('52eba5d9-e576-4ecc-b539-9807235552a4', '2025-04-01 17:00:00', '2025-05-18 19:00:00', '7719c8cd-276e-45fe-b54b-b2f5160e155b', 16),
    ('d66f2148-3a9e-4d90-ac6d-0c1535d12c83', '2025-02-09 18:00:00', '2025-02-26 20:00:00', 'ce7ad47f-82f8-444e-98fd-5214cf09bc71', 17);


-- changeset Vadim:2350239232258-5
INSERT INTO payments (event_id, user_id)
VALUES
    ('0ba6ec4e-84aa-4303-a6db-19e1e0d33bf0', 1),
    ('53dcfee5-db34-4f85-94ce-ee4e6b130ab1', 2),
    ('4414e06c-7ddc-4d62-a55b-e749cb1b4fd4', 3),
    ('d25b4bca-242b-4dfa-9d51-c258479aaae7', 4),
    ('7bcb9038-2706-4146-9d6a-01428ccde9df', 5),
    ('20223702-a1d7-4305-a078-4f99c0c422bf', 6),
    ('92c49c14-259b-48e8-8cb8-34b36841cec6', 7),
    ('ae2b64ec-e894-40a4-a49e-deb8e72d72f8', 8),
    ('8e0ab841-c178-4478-b340-532db7027227', 9),
    ('9952b135-5c2a-41e7-ac25-c63cafa4e9bc', 10),
    ('2dd488ea-dc98-4d8a-aecf-31341107206e', 11),
    ('7d521d71-1318-4875-adee-b45589bce1e0', 12),
    ('fe600e3c-6d3c-4c3e-a3dd-145afd31ee66', 13),
    ('091e0d96-c351-400b-ad7d-12de6435bdff', 14),
    ('b50134fc-24c7-482b-9612-97d1816dd139', 15),
    ('759c8882-b3aa-441b-9d36-6851df251410', 16);