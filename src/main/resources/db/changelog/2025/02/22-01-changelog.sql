-- liquibase formatted sql

-- changeset Vadim:1740239202258-1
CREATE TABLE events
(
    id            UUID                        NOT NULL DEFAULT gen_random_uuid(),
    start_time    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    end_time      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    unit_id       UUID                        NOT NULL,
    user_id       INTEGER                     NOT NULL,
    payment_id    UUID,
    creation_time TIMESTAMP WITHOUT TIME ZONE          DEFAULT now(),
    CONSTRAINT pk_events PRIMARY KEY (id)
);

-- changeset Vadim:1740239202258-2
CREATE TABLE payments
(
    id       UUID    NOT NULL DEFAULT gen_random_uuid(),
    event_id UUID    NOT NULL,
    user_id  INTEGER NOT NULL,
    CONSTRAINT pk_payments PRIMARY KEY (id)
);


-- changeset Vadim:1740239202258-3
CREATE TABLE unit_properties
(
    unit_id     UUID        NOT NULL,
    floor       INTEGER     NOT NULL,
    rooms_count INTEGER     NOT NULL,
    type        VARCHAR(10) NOT NULL,
    CONSTRAINT pk_unit_properties PRIMARY KEY (unit_id)
);


-- changeset Vadim:1740239202258-4
CREATE TABLE units
(
    id          UUID    NOT NULL,
    description VARCHAR(256),
    price       DECIMAL NOT NULL,
    CONSTRAINT pk_units PRIMARY KEY (id)
);

-- changeset Vadim:1740239202258-5
CREATE TABLE users
(
    id       SERIAL      NOT NULL,
    username VARCHAR(20) NOT NULL,
    password VARCHAR(70) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

-- changeset Vadim:1740239202258-6
ALTER TABLE payments
    ADD CONSTRAINT payments_pk_2 UNIQUE (event_id);

-- changeset Vadim:1740239202258-7
ALTER TABLE events
    ADD CONSTRAINT FK_EVENTS_ON_UNIT FOREIGN KEY (unit_id) REFERENCES units (id);

ALTER TABLE events
    ADD CONSTRAINT FK_EVENTS_ON_PAYMENTS FOREIGN KEY (payment_id) REFERENCES payments (id);

ALTER TABLE events
    ADD CONSTRAINT FK_EVENTS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE events
    ADD CONSTRAINT UK_EVENTS_ON_USER_UNIT UNIQUE (user_id, unit_id);

-- changeset Vadim:1740239202258-8
ALTER TABLE payments
    ADD CONSTRAINT FK_PAYMENTS_ON_EVENT FOREIGN KEY (event_id) REFERENCES events (id);

-- changeset Vadim:1740239202258-9
ALTER TABLE payments
    ADD CONSTRAINT FK_PAYMENTS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

-- changeset Vadim:1740239202258-10
ALTER TABLE unit_properties
    ADD CONSTRAINT FK_UNIT_PROPERTIES_ON_UNIT FOREIGN KEY (unit_id) REFERENCES units (id);

