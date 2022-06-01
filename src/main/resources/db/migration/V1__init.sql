create table accident
(
    id bigserial primary key
);


create table announcement
(
    id          bigint not null primary key,
    close_date  timestamp,
    photos_list varchar(255),
    price       bigint check (price > 0),
    region      integer,
    start_date  timestamp,
    status      integer
);

create table car
(
    id              bigserial primary key,
    brand           varchar(255) not null,
    color           varchar(255),
    description     varchar(255),
    engine_capacity integer,
    engine_power    integer,
    gear            varchar(255),
    mileage         integer,
    model           varchar(255) not null,
    performance     varchar(255),
    transmission    varchar(255),
    vin_number      bigint       not null,
    announcement_id bigint references announcement
);

create table accident_cars
(
    accident_id bigint not null,
    cars_id     bigint not null,
    constraint fk_accident
        foreign key (accident_id)
            references accident (id),
    constraint fk_cars
        foreign key (cars_id)
            references car (id)
);

create table car_old_announcement
(
    car_id              bigint not null references car,
    old_announcement_id bigint not null unique references announcement
);


create table detail
(
    id              bigserial primary key,
    brand           varchar(255),
    model           varchar(255),
    status          varchar(255),
    type            integer,
    announcement_id bigint references announcement
);


create table profile
(
    id            bigserial primary key,
    mail          varchar(255) not null,
    nickname      varchar(255),
    register_date timestamp    not null
);

create table profile_announcements_car
(
    profile_id           bigint not null references profile,
    announcements_car_id bigint not null unique references car
);

create table profile_announcements_detail
(
    profile_id              bigint not null references profile,
    announcements_detail_id bigint not null unique references detail
);


create table profile_favorite_announcement_car
(
    profile_id                   bigint not null references profile,
    favorite_announcement_car_id bigint not null unique references car
);

create table profile_favorite_announcement_detail
(
    profile_id                      bigint not null references profile,
    favorite_announcement_detail_id bigint not null unique references detail
);

create table profile_old_announcements_car
(
    profile_id               bigint not null references profile,
    old_announcements_car_id bigint not null unique references car
);

create table profile_old_announcements_detail
(
    profile_id                  bigint not null references profile,
    old_announcements_detail_id bigint not null unique references detail
);

create table review
(
    id        bigserial primary key,
    author_id bigint not null,
    grade     integer,
    car_id    bigint references car
);

create table user_lk
(
    id         bigserial primary key,
    mail       varchar(255) not null unique,
    password   varchar(255) not null,
    profile_id bigint references profile
);


