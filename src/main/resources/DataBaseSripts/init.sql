CREATE TABLE "USER"
(
    ID                     bigint not null GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    FavoriteAnnouncementID json,
    RegisterDate           timestamp not null,
    NickName               varchar(36) not null
);

CREATE TABLE "ANNOUNCEMENT"
(
    ID      bigint not null GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    OwnerID bigint not null REFERENCES "USER",
    Region  int CHECK (Region > 0 ),
    Date    timestamp,
    Status  int,
    Price bigint
);

CREATE TABLE "CAR"
(
    ID              bigint not null GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    AnnouncementID  bigint not null REFERENCES "ANNOUNCEMENT",
    Brand           varchar(36) not null,
    Model           varchar(36) not null,
    Transmission    int,
    Gear            int,
    Engine_capacity int,
    Engine_power    int,
    Color           varchar(36),
    Mileage         varchar(36),
    Performance     int,
    Vin_number      bigint,
    Description     json
);

CREATE TABLE "REVIEW"
(
    ID       bigint not null GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    AuthorID bigint not null,
    Text     json,
    Grade    int         not null,
    CarID    bigint not null REFERENCES "CAR"
);

CREATE TABLE "DETAIL"
(
    ID             bigint not null GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    AnnouncementID bigint not null REFERENCES "ANNOUNCEMENT",
    Type           int,
    Brand          varchar(36),
    Model          varchar(36),
    Status         json
);



