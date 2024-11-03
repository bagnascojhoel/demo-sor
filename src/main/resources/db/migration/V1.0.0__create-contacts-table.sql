CREATE TABLE
    contact (
        id smallserial primary key,
        name varchar(100) not null,
        email varchar(320) not null unique,
        created_at timestamptz not null,
        updated_at timestamptz not null
    );