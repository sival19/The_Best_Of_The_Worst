DROP SCHEMA IF EXISTS public CASCADE;
CREATE SCHEMA public;

create table program
(
    id             serial primary key,
    program_navn   varchar(50) not null,
    udgivelsesdato varchar(50) not null,
    programtype    varchar(50) not null,
    genre          varchar(50) not null,
    laengde        real        not null,
    image_path     varchar(50)
);


create table person
(
    id           serial primary key,
    navn         varchar(50) not null,
    foedselsdato date        not null,
    nationalitet varchar(50) not null,
    image_path   varchar(50)
);

create table rolle
(
    rolletype varchar(50),
    id        serial primary key

);



create table bruger
(
    id          serial primary key, --eventuelt composite key med email og brugernavn
    brugernavn  varchar(50) unique not null,
    email       varchar(70) unique not null,
    adgangskode varchar(50)        not null,
    rettighed   varchar(50)        not null

);

create table credit
(
    id          serial primary key,
    program_id  integer not null references program(id),
    person_id   integer not null references person (id),
    rolle_id    integer not null references rolle (id),
    beskrivelse text

);
INSERT INTO public.rolle (rolletype, id) VALUES ('producer', 1);
SELECT * FROM rolle;


