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
    person_navn  varchar(50) not null,
    foedselsdato varchar(50) not null,
    nationalitet varchar(50) not null,
    person_image_path   varchar(50)
);

create table rolle
(
    id        serial primary key,
    rolletype varchar(50) UNIQUE ,
    rolle_image_path varchar(50)


);

create table credit
(
    id          serial primary key,
    program_id  integer not null references program(id),
    person_id   integer not null references person (id),
    rolle_id    integer not null references rolle (id),
    beskrivelse text

);


--insert users
INSERT INTO public.bruger (brugernavn, email, adgangskode, rettighed) VALUES ('admin', 'admin', 'admin', 'Administrator');
INSERT INTO public.bruger (brugernavn, email, adgangskode, rettighed) VALUES ('nordisk', 'nordisk', 'nordisk', 'Producer');
INSERT INTO public.bruger (brugernavn, email, adgangskode, rettighed) VALUES ('steenherdel', 'steenherdel', 'steenherdel', 'Producer');
INSERT INTO public.bruger (brugernavn, email, adgangskode, rettighed) VALUES ('M&M', 'M&M', 'M&M', 'Producer');

--insert persons
INSERT INTO public.person (person_navn, foedselsdato, nationalitet, person_image_path) VALUES ('Trine Heidegaard', '1967-12-04', 'dansk', null);
INSERT INTO public.person (person_navn, foedselsdato, nationalitet, person_image_path) VALUES ('Jakob Steffensen', '1975-06-06', 'dansk', null);
INSERT INTO public.person (person_navn, foedselsdato, nationalitet, person_image_path) VALUES ('Thorbjørn Christoffersen', '1975-06-06', 'dansk', null);
INSERT INTO public.person (person_navn, foedselsdato, nationalitet, person_image_path) VALUES ('Troels Christoffersen', '1975-06-06', 'dansk', null);
INSERT INTO public.person (person_navn, foedselsdato, nationalitet, person_image_path) VALUES ('Christian Vinten', '1975-06-06', 'dansk', null);
INSERT INTO public.person (person_navn, foedselsdato, nationalitet, person_image_path) VALUES ('Oskar Skriver', '1975-06-06', 'dansk', null);
INSERT INTO public.person (person_navn, foedselsdato, nationalitet, person_image_path) VALUES ('Alfred Bjerre Larsen', '1975-06-06', 'dansk', null);
INSERT INTO public.person (person_navn, foedselsdato, nationalitet, person_image_path) VALUES ('Anders Matthesen', '1975-06-06', 'dansk', 'Matthesen.jpg');
INSERT INTO public.person (person_navn, foedselsdato, nationalitet, person_image_path) VALUES ('Lasse Spang Olsen', '1975-10-10', 'dansk', null);
INSERT INTO public.person (person_navn, foedselsdato, nationalitet, person_image_path) VALUES ('Anders Thomas Jensen', '1975-10-10', 'dansk', null);
INSERT INTO public.person (person_navn, foedselsdato, nationalitet, person_image_path) VALUES ('Steen Herdel', '1975-10-10', 'dansk', null);
INSERT INTO public.person (person_navn, foedselsdato, nationalitet, person_image_path) VALUES ('Morten Søborg', '1975-10-10', 'dansk', null);
INSERT INTO public.person (person_navn, foedselsdato, nationalitet, person_image_path) VALUES ('Kim Bodina', '1975-10-10', 'dansk', null);
INSERT INTO public.person (person_navn, foedselsdato, nationalitet, person_image_path) VALUES ('Dejan Cucik', '1975-10-10', 'dansk', null);
INSERT INTO public.person (person_navn, foedselsdato, nationalitet, person_image_path) VALUES ('Nikolaj Lie Kaas', '1990-10-10', 'Dansk', null);
INSERT INTO public.person (person_navn, foedselsdato, nationalitet, person_image_path) VALUES ('Lars Kolding Andersen', '1990-10-10', 'dansk', null);
INSERT INTO public.person (person_navn, foedselsdato, nationalitet, person_image_path) VALUES ('Robert Grant', '1990-10-10', 'dansk', null);
INSERT INTO public.person (person_navn, foedselsdato, nationalitet, person_image_path) VALUES ('Brian Patterson', '1990-10-10', 'dansk', null);
INSERT INTO public.person (person_navn, foedselsdato, nationalitet, person_image_path) VALUES ('Peter Gantzler', '1990-10-10', 'dansk', null);
INSERT INTO public.person (person_navn, foedselsdato, nationalitet, person_image_path) VALUES ('Anders Thomas Jensen', '1990-10-10', 'dnask', null);
INSERT INTO public.person (person_navn, foedselsdato, nationalitet, person_image_path) VALUES ('Kim Magnusson', '1990-10-10', 'dnask', null);
INSERT INTO public.person (person_navn, foedselsdato, nationalitet, person_image_path) VALUES ('Rolf Konov', '1990-10-10', 'dnask', null);
INSERT INTO public.person (person_navn, foedselsdato, nationalitet, person_image_path) VALUES ('Mads Mikkelsen', '1990-10-10', 'dansk', null);
INSERT INTO public.person (person_navn, foedselsdato, nationalitet, person_image_path) VALUES ('Tivi Magnusson', '1990-10-10', 'Dansk', null);
INSERT INTO public.person (person_navn, foedselsdato, nationalitet, person_image_path) VALUES ('Eric Kress', '1990-10-10', 'dansk', null);
INSERT INTO public.person (person_navn, foedselsdato, nationalitet, person_image_path) VALUES ('Søren Pilmark', '1990-10-10', 'dansk', null);

--insert programs
INSERT INTO public.program (program_navn, udgivelsesdato, programtype, genre, laengde, program_image_path, bruger_id) VALUES ('Ternet Ninja', '2018-12', 'Film', 'Comedy', 82, 'ternetNinja.jpg', 2);
INSERT INTO public.program (program_navn, udgivelsesdato, programtype, genre, laengde, program_image_path, bruger_id) VALUES ('I Kina Spiser De Hunde', '1999-10', 'Film', 'Action', 90, 'ikinaspiserdehunde.jpg', 3);
INSERT INTO public.program (program_navn, udgivelsesdato, programtype, genre, laengde, program_image_path, bruger_id) VALUES ('De Grønne Slagtere', '2003-02', 'Film', 'Action', 99, 'degroenne.jpg', 4);
INSERT INTO public.program (program_navn, udgivelsesdato, programtype, genre, laengde, program_image_path, bruger_id) VALUES ('Blinkende Lygter', '2000-05', 'Film', 'Action', 109, 'blinkende.jpg', 4);

--insert roles
INSERT INTO public.rolle (rolletype, rolle_image_path) VALUES ('instruktion', null);
INSERT INTO public.rolle (rolletype, rolle_image_path) VALUES ('teknisk instruktør(animation)', null);
INSERT INTO public.rolle (rolletype, rolle_image_path) VALUES ('teknisk instruktør', null);
INSERT INTO public.rolle (rolletype, rolle_image_path) VALUES ('animationsinstruktør', null);
INSERT INTO public.rolle (rolletype, rolle_image_path) VALUES ('manuskriptforfatter', null);
INSERT INTO public.rolle (rolletype, rolle_image_path) VALUES ('producer', null);
INSERT INTO public.rolle (rolletype, rolle_image_path) VALUES ('line producer', null);
INSERT INTO public.rolle (rolletype, rolle_image_path) VALUES ('komponist', null);
INSERT INTO public.rolle (rolletype, rolle_image_path) VALUES ('lyddesigner', null);
INSERT INTO public.rolle (rolletype, rolle_image_path) VALUES ('stemme', null);

--insert credits
INSERT INTO public.credit (program_id, person_id, rolle_id, beskrivelse) VALUES (1, 8, 5, '');
INSERT INTO public.credit (program_id, person_id, rolle_id, beskrivelse) VALUES (1, 8, 10, 'Alle andre roller');
INSERT INTO public.credit (program_id, person_id, rolle_id, beskrivelse) VALUES (1, 7, 10, 'Aske');
INSERT INTO public.credit (program_id, person_id, rolle_id, beskrivelse) VALUES (1, 6, 9, '');
INSERT INTO public.credit (program_id, person_id, rolle_id, beskrivelse) VALUES (1, 5, 8, '');
INSERT INTO public.credit (program_id, person_id, rolle_id, beskrivelse) VALUES (1, 1, 6, '');
INSERT INTO public.credit (program_id, person_id, rolle_id, beskrivelse) VALUES (1, 4, 3, '');
INSERT INTO public.credit (program_id, person_id, rolle_id, beskrivelse) VALUES (1, 3, 4, '');
INSERT INTO public.credit (program_id, person_id, rolle_id, beskrivelse) VALUES (1, 2, 2, '');
INSERT INTO public.credit (program_id, person_id, rolle_id, beskrivelse) VALUES (1, 8, 1, '');