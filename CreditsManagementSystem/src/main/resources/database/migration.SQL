DROP SCHEMA IF EXISTS public CASCADE;
CREATE SCHEMA public;

create table bruger
(
    id          serial primary key, --eventuelt composite key med email og brugernavn
    brugernavn  varchar(50) unique not null,
    email       varchar(70) unique not null,
    adgangskode varchar(50)        not null,
    rettighed   varchar(50)        not null

);

create table program
(
    id             serial primary key,
    program_navn   varchar(50) not null,
    udgivelsesdato varchar(50) not null,
    programtype    varchar(50) not null,
    genre          varchar(50) not null,
    laengde        real        not null,
    program_image_path     varchar(50),
    bruger_id integer not null references bruger(id)
);


create table person
(
    id           serial primary key,
    navn         varchar(50) not null,
    foedselsdato varchar(59) not null,
    nationalitet varchar(50) not null,
    person_image_path   varchar(50)
);

create table rolle
(
    id        serial primary key,
    rolletype varchar(50),
    rolle_image_path varchar


);

create table credit
(
    id          serial primary key,
    program_id  integer not null references program(id),
    person_id   integer not null references person (id),
    rolle_id    integer not null references rolle (id),
    beskrivelse text

);


INSERT INTO public.bruger (brugernavn, email, adgangskode, rettighed) VALUES ('admin', 'admin', 'admin', 'Administrator');
INSERT INTO public.rolle (rolletype) VALUES ('producer');
SELECT * FROM rolle;


