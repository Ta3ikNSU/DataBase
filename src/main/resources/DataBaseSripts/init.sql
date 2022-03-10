CREATE TABLE "ANNOUNCEMENT"
(
    ID      bigint not null GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    Region  int CHECK (Region > 0 ),
    Date    timestamp,
    Status  int,
    Price   bigint
);

CREATE TABLE "OLD_ANNOUNCEMENT"
(
    ID        bigint not null PRIMARY KEY,
    Region    int CHECK (Region > 0 ),
    DateStart timestamp,
    DateEnd   timestamp,
    Status    int,
    Price     bigint
);

CREATE TABLE "USER"
(
    ID                     bigint      not null GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    RegisterDate           timestamp   not null,
    NickName               varchar(36) not null,
    FavoriteAnnouncementID bigint      not null REFERENCES "ANNOUNCEMENT",
    OLD_AnnouncementIDs bigint REFERENCES "OLD_ANNOUNCEMENT",
    AnnouncementIDs bigint REFERENCES "ANNOUNCEMENT"
);

CREATE TABLE "CAR"
(
    ID                 bigint      not null GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    AnnouncementID     bigint      null UNIQUE REFERENCES "ANNOUNCEMENT",
    OLD_AnnouncementID bigint REFERENCES "OLD_ANNOUNCEMENT",
    Brand              varchar(36) not null,
    Model              varchar(36) not null,
    Transmission       int,
    Gear               int,
    Engine_capacity    int,
    Engine_power       int,
    Color              varchar(36),
    Mileage            varchar(36),
    Performance        int,
    Vin_number         bigint,
    Description        json
);

CREATE TABLE "REVIEW"
(
    ID       bigint not null GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    AuthorID bigint not null,
    Text     json,
    Grade    int    not null,
    CarID    bigint not null REFERENCES "CAR"
);

CREATE TABLE "DETAIL"
(
    ID                 bigint not null GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    AnnouncementID     bigint null UNIQUE REFERENCES "ANNOUNCEMENT",
    OLD_AnnouncementID bigint REFERENCES "OLD_ANNOUNCEMENT",
    Type               int,
    Brand              varchar(36),
    Model              varchar(36),
    Status             json
);
