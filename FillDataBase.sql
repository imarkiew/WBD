insert into "Biura" values
(idBiuro.nextval,'Vertex','1234567890','224807666','Vertex@gmail.com','Imperatora','Warszawa','10a','
00-840' );
insert into "Marketingi" values
(idMarketing.nextval,'15','Czarna','Radom','12-124','183319422','Mark1@gmail.com','Opis dzialu',1 );
insert into "Punkty_Obslugi_Klienta" values
(idPunktyObKl.nextval,'10','Bialej','Sosnowiec','16-120','133315402','PunktObKl1@gmail.com','Opis
dzialu',1 );
insert into "Serwisy" values
(idSerwis.nextval,'15','Wolnosci','Warszawa','09-122','123445412','Serwis1@gmail.com','Opis dzialu',1
);
insert into "Finanse" values
(idFinanse.nextval,'07','Konstytucji','Lodz','11-112','56545487','Finanse1@gmail.com','Opis dzialu',1 );
insert into "Reklamy" values (idReklamy.nextval, 'Reklama1',1 );
insert into "Reklamy" values (idReklamy.nextval, 'Reklama2',1 );
insert into "Promocje" values (idPromocji.nextval, 'Opis1', 'Promocja1',1 );
insert into "Promocje" values (idPromocji.nextval, 'Opis2', 'Promocja2',1 );
insert into "Taryfy" values (idTaryfy.nextval, 'Nazwa1', 'Opis1');
insert into "Taryfy" values (idTaryfy.nextval, 'Nazwa2', 'Opis2');
insert into "Taryfy" values (idTaryfy.nextval, 'Nazwa3', 'Opis3');
insert into "Nr_Telefonow" values (idNrTelefonu.nextval, '663124584', 'Karta', 1, 1);
insert into "Nr_Telefonow" values (idNrTelefonu.nextval, '122532644', 'Karta', 1, 2);
insert into "Nr_Telefonow" values (idNrTelefonu.nextval, '354763738', 'Abonament', 1, 2);
insert into "Nr_Telefonow" values (idNrTelefonu.nextval, '225662737', 'Abonament', 1, 3);
insert into "Klienci" values(idKlienta.nextval, '89110307442', 'Andrzej', 'Mostowski', 'Powstancow
Slaskich', '011', '26-553', 'Katowice', null, 1);
insert into "Klienci" values(idKlienta.nextval, null, 'Anita', 'Morawska', 'Popielnicz', '102', '04-112',
'Warszawa', null, 1);
insert into "Klienci" values(idKlienta.nextval, '85040464432', 'Piotr', 'Mostowski', 'Karnicka', '011',
'05-523', 'Warszawa', '1024942719', 1);
insert into "Promocja" values (idPromocja.nextval, 1, 1);
insert into "Promocja" values (idPromocja.nextval, 1, 3);
insert into "Umowy" values (idUmowy.nextval, 'Opis1', 100.50, TO_DATE('11-03-2015',
'DD-MM-YYYY'), 1, 3);
insert into "Umowy" values (idUmowy.nextval, 'Opis2', 50.00, TO_DATE('05-08-2007',
'DD-MM-YYYY'), 2, 1);
insert into "Umowy" values (idUmowy.nextval, 'Opis3', 125.15, TO_DATE('26-10-2011',
'DD-MM-YYYY'), 3, 2);
insert into "Reklamacje" values (idReklamacji.nextval, 11, 'Opis1', TO_DATE('08-03-2014',
'DD-MM-YYYY'), 'Rozpatrzona Pozytywnie', 2, 2);
insert into "Pracownicy" values (idPracownika.nextval, 'Adam', 'Pardo', '93071156343', '021',
'Krzaczynska', 'Warszawa', '24-403', '432042932', 'APardo@gmail.com', 'Opis1',
TO_DATE('01-11-2013', 'DD-MM-YYYY'), 'Nie', null, null, null, null, 1);
insert into "Pracownicy" values (idPracownika.nextval, 'Klaudia', 'Wojtasik', '95062353439', '111',
'Wilensa', 'Warszawa', '11-363', '582427916', 'KWojtasik@gmail.com', 'Opis2',
TO_DATE('11-04-2015', 'DD-MM-YYYY'), 'Tak', null, null, null, null, 1);
insert into "Pracownicy" values (idPracownika.nextval, 'Piotr', 'Pawlak', '67071156343', '030', 'Debska',
'Lodz', '22-103', '432042932', 'APawlak@gmail.com', 'Opis3', TO_DATE('01-11-1984',
'DD-MM-YYYY'), 'Tak', null, null, null, 1, null);
insert into "Pracownicy" values (idPracownika.nextval, 'Jacek', 'Kowalski', '59101254433', '041',
'Raclawicka', 'Lodz', '04-113', '115672932', 'JKowalski@gmail.com', 'Opis4', TO_DATE('01-11-1994',
'DD-MM-YYYY'), 'Tak', null, null, null, 1, null);
insert into "Pracownicy" values (idPracownika.nextval, 'Robert', 'Nowak', '67111135483', '121',
'Bursztynowa', 'Warszawa', '67-403', '765042759', 'RNowak@gmail.com', 'Opis5',
TO_DATE('05-10-1986', 'DD-MM-YYYY'), 'Tak', null, null, 1, null, null);
insert into "Pracownicy" values (idPracownika.nextval, 'Katarzyna', 'Widawska', '53091164843', '111',
'Karniewska', 'Warszawa', '05-003', '637042789', 'Kwidawska@gmail.com', 'Opis6',
TO_DATE('04-03-1978', 'DD-MM-YYYY'), 'Tak', null, 1, null, null, null);
insert into "Pracownicy" values (idPracownika.nextval, 'Aneta', 'Zaremba', '76020253579', '101',
'Dobra', 'Sosnowiec', '10-333', '557473516', 'AZaremba@gmail.com', 'Opis7', TO_DATE('15-09-1998',
'DD-MM-YYYY'), 'Tak', null, 1, null, null, null);
insert into "Pracownicy" values (idPracownika.nextval, 'Konrad', 'Lewandowski', '79071156557', '041',
'Sloneczna', 'Sosnowiec', '20-400', '436754932', 'KLewandowski@gmail.com', 'Opis8',
TO_DATE('01-10-2000', 'DD-MM-YYYY'), 'Tak', null, 1, null, null, null);
insert into "Pracownicy" values (idPracownika.nextval, 'Marcin', 'Daszczuk', '5812244563', '007',
'Leopolda', 'Radom', '04-003', '487035632', 'MDaszczuk@gmail.com', 'Opis9',
TO_DATE('01-05-1982', 'DD-MM-YYYY'), 'Tak', 1, null, null, null, null);
insert into "Pracownicy" values (idPracownika.nextval, 'Adrian', 'Dziedzic', '82101456544', '033',
'Krawiecka', 'Radom', '11-523', '365758932', 'ADziedzic@gmail.com', 'Opis10',
TO_DATE('01-06-2004', 'DD-MM-YYYY'), 'Tak', 1, null, null, null, null);
insert into "Wynagrodzenia" values (idWynagrodzenia.nextval, 30, 160, 4800,
TO_DATE('01-12-2016', 'DD-MM-YYYY'), 1);
insert into "Wynagrodzenia" values (idWynagrodzenia.nextval, 60, 160, 10000,
TO_DATE('01-12-2016', 'DD-MM-YYYY'), 2);
insert into "Wynagrodzenia" values (idWynagrodzenia.nextval, 25, 140, 3500,
TO_DATE('01-12-2016', 'DD-MM-YYYY'), 3);
insert into "Wynagrodzenia" values (idWynagrodzenia.nextval, 45, 160, 7500,
TO_DATE('01-12-2016', 'DD-MM-YYYY'), 4);
insert into "Wynagrodzenia" values (idWynagrodzenia.nextval, 35, 155, 5425,
TO_DATE('01-12-2016', 'DD-MM-YYYY'), 5);
insert into "Wynagrodzenia" values (idWynagrodzenia.nextval, 30, 160, 4800,
TO_DATE('01-12-2016', 'DD-MM-YYYY'), 6);
insert into "Wynagrodzenia" values (idWynagrodzenia.nextval, 45, 160, 7200,
TO_DATE('01-12-2016', 'DD-MM-YYYY'), 7);
insert into "Wynagrodzenia" values (idWynagrodzenia.nextval, 60, 160, 9600,
TO_DATE('01-12-2016', 'DD-MM-YYYY'), 8);
insert into "Wynagrodzenia" values (idWynagrodzenia.nextval, 20, 160, 3500,
TO_DATE('01-12-2016', 'DD-MM-YYYY'), 9);
insert into "Wynagrodzenia" values (idWynagrodzenia.nextval, 35, 145, 5100,
TO_DATE('01-12-2016', 'DD-MM-YYYY'), 10);
insert into "Pojazdy_Sluzbowe" values (idPojazduSluzbowego.nextval, 'WA063937A3',
TO_DATE('01-05-2016', 'DD-MM-YYYY'), TO_DATE('01-05-2017', 'DD-MM-YYYY'),
TO_DATE('01-05-2016', 'DD-MM-YYYY'), TO_DATE('01-05-2017', 'DD-MM-YYYY'), 1);
insert into "Pojazdy_Sluzbowe" values (idPojazduSluzbowego.nextval, 'WA3C293436',
TO_DATE('01-05-2016', 'DD-MM-YYYY'), TO_DATE('01-05-2017', 'DD-MM-YYYY'),
TO_DATE('01-05-2016', 'DD-MM-YYYY'), TO_DATE('01-05-2017', 'DD-MM-YYYY'), 1);
insert into "Pojazdy_Sluzbowe" values (idPojazduSluzbowego.nextval, 'WA3426G385',
TO_DATE('01-05-2016', 'DD-MM-YYYY'), TO_DATE('01-05-2017', 'DD-MM-YYYY'),
TO_DATE('01-05-2016', 'DD-MM-YYYY'), TO_DATE('01-05-2017', 'DD-MM-YYYY'), 1);
insert into "Zgloszenia" values (idZgloszenia.nextval, 11, 'Opis1', 'W trakcie realizacji',
TO_DATE('15-12-2016','DD-MM-YYYY'), 1 ,1);
insert into "Zgloszenia" values (idZgloszenia.nextval, 15, 'Opis2', 'Zrealizowane',
TO_DATE('10-10-2016','DD-MM-YYYY'), 1 ,2);
insert into "Obsluga_Zgloszen" values (idObslugaZgloszen.nextval, 1);
insert into "Obsluga_Zgloszen" values (idObslugaZgloszen.nextval, 2);
insert into "Wykorzystanie_Pojazdu" values (idWykorzystaniePojazdu.nextval, 1, 1);
insert into "Wykorzystanie_Pojazdu" values (idWykorzystaniePojazdu.nextval, 2, 3);
insert into "Obsluga_Przez_Pracownika" values (idObslugiPrzezPracownika.nextval, 5, 1);
insert into "Obsluga_Przez_Pracownika" values (idObslugiPrzezPracownika.nextval, 6, 2);