-- Create tables section -------------------------------------------------
-- Table Biura
CREATE TABLE "Biura"(
"ID_Biura" Integer NOT NULL,
"Nazwa" Varchar2(50 ) NOT NULL,
"NIP" Varchar2(10 ) NOT NULL,
"Telefon" Varchar2(9 ) NOT NULL,
"E-mail" Varchar2(50 ) NOT NULL,
"Adres_Ulica" Varchar2(30 ) NOT NULL,
"Adres_Miejscowosc" Varchar2(30 ) NOT NULL,
"Adres_Nr_Lokalu" Varchar2(3 ) NOT NULL,
"Kod_Pocztowy" Varchar2(6 ) NOT NULL
)
/
-- Add keys for table Biura
ALTER TABLE "Biura" ADD CONSTRAINT "ID_Biura" PRIMARY KEY ("ID_Biura")
/
-- Table Marketingi
CREATE TABLE "Marketingi"(
"ID_Marketing" Integer NOT NULL,
"Adres_Nr_Lokalu" Varchar2(3 ) NOT NULL,
"Adres_Ulica" Varchar2(30 ) NOT NULL,
"Adres_Miejscowosc" Varchar2(30 ) NOT NULL,
"Kod_Pocztowy" Varchar2(6 ) NOT NULL,
"Telefon" Varchar2(9 ) NOT NULL,
"E-mail" Varchar2(50 ) NOT NULL,
"Opis_dzialu" Varchar2(500 ),
"ID_Biura" Integer NOT NULL
)
/
-- Create indexes for table Marketingi
CREATE INDEX "IX_Posiada_Marketing" ON "Marketingi" ("ID_Biura")
/
-- Add keys for table Marketingi
ALTER TABLE "Marketingi" ADD CONSTRAINT "ID_Marketing" PRIMARY KEY ("ID_Marketing")
/
-- Table Punkty_Obslugi_Klienta
CREATE TABLE "Punkty_Obslugi_Klienta"(
"ID_Obslugi_Klienta" Integer NOT NULL,
"Adres_Nr_Lokalu" Varchar2(3 ) NOT NULL,
"Adres_Ulica" Varchar2(30 ) NOT NULL,
"Adres_Miejscowosc" Varchar2(30 ) NOT NULL,
"Kod_Pocztowy" Varchar2(6 ) NOT NULL,
"Telefon" Varchar2(9 ) NOT NULL,
"E-mail" Varchar2(50 ) NOT NULL,
"Opis_dzialu" Varchar2(500 ),
"ID_Biura" Integer NOT NULL
)
/
-- Create indexes for table Punkty_Obslugi_Klienta
CREATE INDEX "IX_Posiada_Biuro" ON "Punkty_Obslugi_Klienta" ("ID_Biura")
/
-- Add keys for table Punkty_Obslugi_Klienta
ALTER TABLE "Punkty_Obslugi_Klienta" ADD CONSTRAINT "ID_Obslugi_Klienta" PRIMARY KEY
("ID_Obslugi_Klienta")
/
-- Table Serwisy
CREATE TABLE "Serwisy"(
"ID_Serwis" Integer NOT NULL,
"Adres_Nr_Lokalu" Varchar2(3 ) NOT NULL,
"Adres_Ulica" Varchar2(30 ) NOT NULL,
"Adres_Miejscowosc" Varchar2(30 ) NOT NULL,
"Kod_Pocztowy" Varchar2(6 ) NOT NULL,
"Telefon" Varchar2(9 ) NOT NULL,
"E-mail" Varchar2(50 ) NOT NULL,
"Opis_dzialu" Varchar2(500 ),
"ID_Biura" Integer NOT NULL
)
/
-- Create indexes for table Serwisy
CREATE INDEX "IX_Posiada_Serwis" ON "Serwisy" ("ID_Biura")
/
-- Add keys for table Serwisy
ALTER TABLE "Serwisy" ADD CONSTRAINT "ID_Serwis" PRIMARY KEY ("ID_Serwis")
/
-- Table Finanse
CREATE TABLE "Finanse"(
"ID_Finanse" Integer NOT NULL,
"Adres_Nr_Lokalu" Varchar2(3 ) NOT NULL,
"Adres_Ulica" Varchar2(30 ) NOT NULL,
"Adres_Miejscowosc" Varchar2(30 ) NOT NULL,
"Kod_Pocztowy" Varchar2(6 ) NOT NULL,
"Telefon" Varchar2(9 ) NOT NULL,
"E-mail" Varchar2(50 ) NOT NULL,
"Opis_dzialu" Varchar2(500 ),
"ID_Biura" Integer NOT NULL
)
/
-- Create indexes for table Finanse
CREATE INDEX "IX_Posiada_Finanse" ON "Finanse" ("ID_Biura")
/
-- Add keys for table Finanse
ALTER TABLE "Finanse" ADD CONSTRAINT "ID_Finanse" PRIMARY KEY ("ID_Finanse")
/
-- Table Promocja
CREATE TABLE "Promocja"(
"ID_Promocja" Integer NOT NULL,
"ID_promocji" Integer NOT NULL,
"ID_Klienta" Integer NOT NULL
)
/
-- Add keys for table Promocja
ALTER TABLE "Promocja" ADD CONSTRAINT "ID_Promocji" PRIMARY KEY
("ID_promocji","ID_Klienta","ID_Promocja")
/
-- Table Klienci
CREATE TABLE "Klienci"(
"ID_Klienta" Integer NOT NULL,
"PESEL" Varchar2(11 ),
"Imie" Varchar2(30 ) NOT NULL,
"Nazwisko" Varchar2(30 ) NOT NULL,
"Adres_Ulica" Varchar2(30 ) NOT NULL,
"Adres_Nr_Mieszkania" Varchar2(3 ) NOT NULL,
"Kod_Pocztowy" Varchar2(6 ) NOT NULL,
"Adres_Miejscowosc" Varchar2(30 ) NOT NULL,
"NIP" Varchar2(10 ),
"ID_Obslugi_Klienta" Integer NOT NULL
)
/
-- Create indexes for table Klienci
CREATE INDEX "IX_Obsluguje_Klientow" ON "Klienci" ("ID_Obslugi_Klienta")
/
-- Add keys for table Klienci
ALTER TABLE "Klienci" ADD CONSTRAINT "ID_Klienta" PRIMARY KEY ("ID_Klienta")
/
-- Table Umowy
CREATE TABLE "Umowy"(
"ID_Umowy" Integer NOT NULL,
"Opis_umowy" Varchar2(4000 ) NOT NULL,
"Miesieczny_koszt_umowy" Number(8,2) NOT NULL,
"Data_Zawarcia_Umowy" Date NOT NULL,
"ID_Klienta" Integer NOT NULL,
"ID_Nr_Telefonu" Integer
)
/
-- Create indexes for table Umowy
CREATE INDEX "IX_Posiada_Umowe" ON "Umowy" ("ID_Klienta")
/
CREATE INDEX "IX_Relationship111" ON "Umowy" ("ID_Nr_Telefonu")
/
-- Add keys for table Umowy
ALTER TABLE "Umowy" ADD CONSTRAINT "ID_Umowy" PRIMARY KEY ("ID_Umowy")
/
-- Table Pracownicy
CREATE TABLE "Pracownicy"(
"ID_Pracownika" Integer NOT NULL,
"Imie" Varchar2(30 ) NOT NULL,
"Nazwisko" Varchar2(30 ) NOT NULL,
"PESEL" Varchar2(11 ),
"Adres_Nr_Mieszkania" Varchar2(3 ) NOT NULL,
"Adres_Ulica" Varchar2(30 ) NOT NULL,
"Adres_Miejscowosc" Varchar2(30 ) NOT NULL,
"Kod_Pocztowy" Varchar2(6 ) NOT NULL,
"Telefon" Varchar2(9 ) NOT NULL,
"E-mail" Varchar2(50 ) NOT NULL,
"Opis_Stanowiska" Varchar2(200 ) NOT NULL,
"Data_Zatrudnienia" Date NOT NULL,
"Prawojazdy_B2" Varchar2(3) NOT NULL CONSTRAINT Prawo_Jazdy_B2
CHECK ("Prawojazdy_B2" IN('Tak', 'Nie')),
"ID_Marketing" Integer,
"ID_Obslugi_Klienta" Integer,
"ID_Serwis" Integer,
"ID_Finanse" Integer,
"ID_Biura" Integer
)
/
-- Create indexes for table Pracownicy
CREATE INDEX "IX_Marketing_Zatrudnia" ON "Pracownicy" ("ID_Marketing")
/
CREATE INDEX "IX_Ob_Klienta_Zatrudnia_Pracow" ON "Pracownicy" ("ID_Obslugi_Klienta")
/
CREATE INDEX "IX_Serwis_Zatrudnia_Pracownika" ON "Pracownicy" ("ID_Serwis")
/
CREATE INDEX "IX_Finanse_Zatrudnia_Pracownik" ON "Pracownicy" ("ID_Finanse")
/
CREATE INDEX "IX_Relationship1" ON "Pracownicy" ("ID_Biura")
/
-- Add keys for table Pracownicy
ALTER TABLE "Pracownicy" ADD CONSTRAINT "ID_Pracownika" PRIMARY KEY ("ID_Pracownika")
/
-- Table Wynagrodzenia
CREATE TABLE "Wynagrodzenia"(
"ID_Wynagrodzenia" Integer NOT NULL,
"Stawka_za_godzine" Number(5,2) NOT NULL,
"Ilosc_godzin_przepracowanych" Varchar2(3 ) NOT NULL,
"Wysokosc_Wyplaty" Number(9,2) NOT NULL,
"Data_wynagrodzenia" Date NOT NULL,
"ID_Pracownika" Integer NOT NULL
)
/
-- Create indexes for table Wynagrodzenia
CREATE INDEX "IX_Dostaja_Wynagrodzenie" ON "Wynagrodzenia" ("ID_Pracownika")
/
-- Add keys for table Wynagrodzenia
ALTER TABLE "Wynagrodzenia" ADD CONSTRAINT "ID_Wynagrodzenia" PRIMARY KEY
("ID_Wynagrodzenia")
/
-- Table Reklamacje
CREATE TABLE "Reklamacje"(
"ID_Reklamacji" Integer NOT NULL,
"Nr_reklamacji" Integer NOT NULL,
"Opis_reklamacji" Varchar2(500 ) NOT NULL,
"Data_Reklamacji" Date NOT NULL,
"Status" VarChar2(22) NOT NULL CONSTRAINT Reklamacja
CHECK ( "Status" IN('Nierozpatrzona','W trakcie','Rozpatrzona Negatywnie','Rozpatrzona
Pozytywnie')),
"ID_Klienta" Integer NOT NULL,
"ID_Umowy" Integer
)
/
-- Create indexes for table Reklamacje
CREATE INDEX "IX_Sklada_Reklamacje" ON "Reklamacje" ("ID_Klienta")
/
CREATE INDEX "IX_Relationship5" ON "Reklamacje" ("ID_Umowy")
/
-- Add keys for table Reklamacje
ALTER TABLE "Reklamacje" ADD CONSTRAINT "ID_Reklamacji" PRIMARY KEY ("ID_Reklamacji")
/
-- Table Nr_Telefonow
CREATE TABLE "Nr_Telefonow"(
"ID_Nr_Telefonu" Integer NOT NULL,
"Nr_telefonu" Varchar2(9 ) NOT NULL,
"Karta_Abonament" Varchar2(9) NOT NULL CONSTRAINT Karta_Abonament
CHECK ("Karta_Abonament" IN('Karta', 'Abonament')),
"ID_Biura" Integer NOT NULL,
"ID_Taryfy" Integer
)
/
-- Create indexes for table Nr_Telefonow
CREATE INDEX "IX_Posiada liste numerow" ON "Nr_Telefonow" ("ID_Biura")
/
CREATE INDEX "IX_Relationship21" ON "Nr_Telefonow" ("ID_Taryfy")
/
-- Add keys for table Nr_Telefonow
ALTER TABLE "Nr_Telefonow" ADD CONSTRAINT "Nr_telefonu" PRIMARY KEY ("ID_Nr_Telefonu")
/
-- Table Reklamy
CREATE TABLE "Reklamy"(
"ID_Reklamy" Integer NOT NULL,
"Opis_Reklamy" Varchar2(300 ) NOT NULL,
"ID_Marketing" Integer NOT NULL
)
/
-- Create indexes for table Reklamy
CREATE INDEX "IX_Posiada_Reklamy" ON "Reklamy" ("ID_Marketing")
/
-- Add keys for table Reklamy
ALTER TABLE "Reklamy" ADD CONSTRAINT "ID_Reklamy" PRIMARY KEY ("ID_Reklamy")
/
-- Table Taryfy
CREATE TABLE "Taryfy"(
"ID_Taryfy" Integer NOT NULL,
"Nazwa_Taryfy" Varchar2(30 ) NOT NULL,
"Opis_Taryfy" Varchar2(300 ) NOT NULL
)
/
-- Add keys for table Taryfy
ALTER TABLE "Taryfy" ADD CONSTRAINT "ID_Taryfy" PRIMARY KEY ("ID_Taryfy")
/
-- Table Pojazdy_Sluzbowe
CREATE TABLE "Pojazdy_Sluzbowe"(
"ID_pojazdu" Integer NOT NULL,
"Tablica_rejestracyjna" Varchar2(10 ) NOT NULL,
"Ubezpieczenie_od" Date NOT NULL,
"Ubezpieczenie_do" Date NOT NULL,
"Przegl¹d_od" Date NOT NULL,
"Przegl¹d_do" Date NOT NULL,
"ID_Serwis" Integer NOT NULL
)
/
-- Create indexes for table Pojazdy_Sluzbowe
CREATE INDEX "IX_Posiada_Pojazdy" ON "Pojazdy_Sluzbowe" ("ID_Serwis")
/
-- Add keys for table Pojazdy_Sluzbowe
ALTER TABLE "Pojazdy_Sluzbowe" ADD CONSTRAINT "ID_pojazdu" PRIMARY KEY
("ID_pojazdu")
/
-- Table Zgloszenia
CREATE TABLE "Zgloszenia"(
"ID_Zgloszenia" Integer NOT NULL,
"Nr_Zgloszenia" Integer NOT NULL,
"Opis_Zgloszenia" Varchar2(500 ) NOT NULL,
"Status_Zgloszenia" Varchar2(20 ) NOT NULL CONSTRAINT Status_Zgloszenia
CHECK ( "Status_Zgloszenia" IN('Nierozpatrzone', 'W trakcie realizacji', 'Zrealizowane')),
"Data_Przyjecia_Zgloszenia" Date NOT NULL,
"ID_Serwis" Integer NOT NULL,
"ID_Klienta" Integer NOT NULL
)
/
-- Create indexes for table Zgloszenia
CREATE INDEX "IX_Relationship11" ON "Zgloszenia" ("ID_Serwis")
/
CREATE INDEX "IX_Relationship2" ON "Zgloszenia" ("ID_Klienta")
/
-- Add keys for table Zgloszenia
ALTER TABLE "Zgloszenia" ADD CONSTRAINT "Key1" PRIMARY KEY ("ID_Zgloszenia")
/
-- Table Promocje
CREATE TABLE "Promocje"(
"ID_Promocji" Integer NOT NULL,
"Nazwa promocji" Varchar2(30 ) NOT NULL,
"Opis_Promocji" Varchar2(500 ) NOT NULL,
"ID_Biura" Integer
)
/
-- Create indexes for table Promocje
CREATE INDEX "IX_Relationship4" ON "Promocje" ("ID_Biura")
/
-- Add keys for table Promocje
ALTER TABLE "Promocje" ADD CONSTRAINT "Key2" PRIMARY KEY ("ID_Promocji")
/
-- Table and Columns comments section
COMMENT ON COLUMN "Promocje"."ID_Promocji" IS 'Identyfikator promocji'
/
-- Table Obsluga_Zgloszen
CREATE TABLE "Obsluga_Zgloszen"(
"ID_Obslugi_Zgloszenia" Integer NOT NULL,
"ID_Zgloszenia" Integer NOT NULL
)
/
-- Create indexes for table Obsluga_Zgloszen
CREATE INDEX "IX_Relationship12" ON "Obsluga_Zgloszen" ("ID_Zgloszenia")
/
-- Add keys for table Obsluga_Zgloszen
ALTER TABLE "Obsluga_Zgloszen" ADD CONSTRAINT "Key3" PRIMARY KEY
("ID_Obslugi_Zgloszenia")
/
-- Table Obsluga_Przez_Pracownika
CREATE TABLE "Obsluga_Przez_Pracownika"(
"ID_Obslugi_Przez_Pracownika" Integer NOT NULL,
"ID_Pracownika" Integer NOT NULL,
"ID_Obslugi_Zgloszenia" Integer NOT NULL
)
/
-- Add keys for table Obsluga_Przez_Pracownika
ALTER TABLE "Obsluga_Przez_Pracownika" ADD CONSTRAINT "Key4" PRIMARY KEY
("ID_Obslugi_Przez_Pracownika","ID_Pracownika","ID_Obslugi_Zgloszenia")
/
-- Table Wykorzystanie_Pojazdu
CREATE TABLE "Wykorzystanie_Pojazdu"(
"ID_Wykorzystania_Pojazdu" Integer NOT NULL,
"ID_Obslugi_Zgloszenia" Integer NOT NULL,
"ID_pojazdu" Integer NOT NULL
)
/
-- Add keys for table Wykorzystanie_Pojazdu
ALTER TABLE "Wykorzystanie_Pojazdu" ADD CONSTRAINT "Key5" PRIMARY KEY
("ID_Wykorzystania_Pojazdu","ID_Obslugi_Zgloszenia","ID_pojazdu")
/
-- Create relationships section -------------------------------------------------
ALTER TABLE "Marketingi" ADD CONSTRAINT "Posiada_Marketing" FOREIGN KEY ("ID_Biura")
REFERENCES "Biura" ("ID_Biura")
/
ALTER TABLE "Serwisy" ADD CONSTRAINT "Posiada_Serwisy" FOREIGN KEY ("ID_Biura")
REFERENCES "Biura" ("ID_Biura")
/
ALTER TABLE "Finanse" ADD CONSTRAINT "Posiada_Finanse" FOREIGN KEY ("ID_Biura")
REFERENCES "Biura" ("ID_Biura")
/
ALTER TABLE "Klienci" ADD CONSTRAINT "Obsluguje_Klientow" FOREIGN KEY
("ID_Obslugi_Klienta") REFERENCES "Punkty_Obslugi_Klienta" ("ID_Obslugi_Klienta")
/
ALTER TABLE "Umowy" ADD CONSTRAINT "Posiada_Umowy" FOREIGN KEY ("ID_Klienta")
REFERENCES "Klienci" ("ID_Klienta")
/
ALTER TABLE "Pracownicy" ADD CONSTRAINT "Marketing_Zatrudnia" FOREIGN KEY
("ID_Marketing") REFERENCES "Marketingi" ("ID_Marketing")
/
ALTER TABLE "Pracownicy" ADD CONSTRAINT "Ob_Klienta_Zatrudnia" FOREIGN KEY
("ID_Obslugi_Klienta") REFERENCES "Punkty_Obslugi_Klienta" ("ID_Obslugi_Klienta")
/
ALTER TABLE "Pracownicy" ADD CONSTRAINT "Serwis_Zatrudnia_Pracownikow" FOREIGN KEY
("ID_Serwis") REFERENCES "Serwisy" ("ID_Serwis")
/
ALTER TABLE "Pracownicy" ADD CONSTRAINT "Finanse_Zatrudniaja" FOREIGN KEY
("ID_Finanse") REFERENCES "Finanse" ("ID_Finanse")
/
ALTER TABLE "Wynagrodzenia" ADD CONSTRAINT "Dostaja_Wynagrodzenie" FOREIGN KEY
("ID_Pracownika") REFERENCES "Pracownicy" ("ID_Pracownika")
/
ALTER TABLE "Punkty_Obslugi_Klienta" ADD CONSTRAINT "Posiada_Biuro" FOREIGN KEY
("ID_Biura") REFERENCES "Biura" ("ID_Biura")
/
ALTER TABLE "Reklamacje" ADD CONSTRAINT "Sklada_Reklamacje" FOREIGN KEY
("ID_Klienta") REFERENCES "Klienci" ("ID_Klienta")
/
ALTER TABLE "Nr_Telefonow" ADD CONSTRAINT "Posiada liste numerow" FOREIGN KEY
("ID_Biura") REFERENCES "Biura" ("ID_Biura")
/
ALTER TABLE "Reklamy" ADD CONSTRAINT "Posiada_Reklamy" FOREIGN KEY ("ID_Marketing")
REFERENCES "Marketingi" ("ID_Marketing")
/
ALTER TABLE "Pojazdy_Sluzbowe" ADD CONSTRAINT "Posiada_Pojazdy" FOREIGN KEY
("ID_Serwis") REFERENCES "Serwisy" ("ID_Serwis")
/
ALTER TABLE "Zgloszenia" ADD CONSTRAINT "Serwis_obsluguje_zgloszenia" FOREIGN KEY
("ID_Serwis") REFERENCES "Serwisy" ("ID_Serwis")
/
ALTER TABLE "Umowy" ADD CONSTRAINT "Umowa_dotyczy_telefonu" FOREIGN KEY
("ID_Nr_Telefonu") REFERENCES "Nr_Telefonow" ("ID_Nr_Telefonu")
/
ALTER TABLE "Promocja" ADD CONSTRAINT "Sa_Przypisane" FOREIGN KEY ("ID_promocji")
REFERENCES "Promocje" ("ID_Promocji")
/
ALTER TABLE "Promocja" ADD CONSTRAINT "Posiada_jest_przypisana" FOREIGN KEY
("ID_Klienta") REFERENCES "Klienci" ("ID_Klienta")
/
ALTER TABLE "Promocje" ADD CONSTRAINT "Biuro_Ma_Promocje" FOREIGN KEY ("ID_Biura")
REFERENCES "Biura" ("ID_Biura")
/
ALTER TABLE "Reklamacje" ADD CONSTRAINT "Jest_Skladana_Na_Umowe" FOREIGN KEY
("ID_Umowy") REFERENCES "Umowy" ("ID_Umowy")
/
ALTER TABLE "Nr_Telefonow" ADD CONSTRAINT "Maja_Telefony" FOREIGN KEY ("ID_Taryfy")
REFERENCES "Taryfy" ("ID_Taryfy")
/
ALTER TABLE "Obsluga_Zgloszen" ADD CONSTRAINT "Sa_Obslugiwane" FOREIGN KEY
("ID_Zgloszenia") REFERENCES "Zgloszenia" ("ID_Zgloszenia")
/
ALTER TABLE "Obsluga_Przez_Pracownika" ADD CONSTRAINT "Pracownicy_Obsl_Zglosz"
FOREIGN KEY ("ID_Pracownika") REFERENCES "Pracownicy" ("ID_Pracownika")
/
ALTER TABLE "Obsluga_Przez_Pracownika" ADD CONSTRAINT "Jest_Obslugiwana_Przez_Prac"
FOREIGN KEY ("ID_Obslugi_Zgloszenia") REFERENCES "Obsluga_Zgloszen"
("ID_Obslugi_Zgloszenia")
/
ALTER TABLE "Wykorzystanie_Pojazdu" ADD CONSTRAINT "Jest_Obslugiwana_Przez_Pojazdy"
FOREIGN KEY ("ID_Obslugi_Zgloszenia") REFERENCES "Obsluga_Zgloszen"
("ID_Obslugi_Zgloszenia")
/
ALTER TABLE "Wykorzystanie_Pojazdu" ADD CONSTRAINT "Sa_Wykorzystywane" FOREIGN KEY
("ID_pojazdu") REFERENCES "Pojazdy_Sluzbowe" ("ID_pojazdu")
/
ALTER TABLE "Zgloszenia" ADD CONSTRAINT "Zglasza" FOREIGN KEY ("ID_Klienta")
REFERENCES "Klienci" ("ID_Klienta")
/
ALTER TABLE "Pracownicy" ADD CONSTRAINT "Biura_zatrudnia" FOREIGN KEY ("ID_Biura")
REFERENCES "Biura" ("ID_Biura")
/
CREATE SEQUENCE idBiuro
MINVALUE 0
START WITH 0
INCREMENT BY 1;
CREATE SEQUENCE idMarketing
MINVALUE 0
START WITH 0
INCREMENT BY 1;
CREATE SEQUENCE idPunktyObKl
MINVALUE 0
START WITH 0
INCREMENT BY 1;
CREATE SEQUENCE idSerwis
MINVALUE 0
START WITH 0
INCREMENT BY 1;
CREATE SEQUENCE idFinanse
MINVALUE 0
START WITH 0
INCREMENT BY 1;
CREATE SEQUENCE idReklamy
MINVALUE 0
START WITH 0
INCREMENT BY 1;
CREATE SEQUENCE idPromocji
MINVALUE 0
START WITH 0
INCREMENT BY 1;
CREATE SEQUENCE idTaryfy
MINVALUE 0
START WITH 0
INCREMENT BY 1;
CREATE SEQUENCE idNrTelefonu
MINVALUE 0
START WITH 0
INCREMENT BY 1;
CREATE SEQUENCE idKlienta
MINVALUE 0
START WITH 0
INCREMENT BY 1;
CREATE SEQUENCE idPromocja
MINVALUE 0
START WITH 0
INCREMENT BY 1;
CREATE SEQUENCE idUmowy
MINVALUE 0
START WITH 0
INCREMENT BY 1;
CREATE SEQUENCE idPracownika
MINVALUE 0
START WITH 0
INCREMENT BY 1;
CREATE SEQUENCE idReklamacji
MINVALUE 0
START WITH 0
INCREMENT BY 1;
CREATE SEQUENCE idWynagrodzenia
MINVALUE 0
START WITH 0
INCREMENT BY 1;
CREATE SEQUENCE idPojazduSluzbowego
MINVALUE 0
START WITH 0
INCREMENT BY 1;
CREATE SEQUENCE idZgloszenia
MINVALUE 0
START WITH 0
INCREMENT BY 1;
CREATE SEQUENCE idObslugaZgloszen
MINVALUE 0
START WITH 0
INCREMENT BY 1;
CREATE SEQUENCE idWykorzystaniePojazdu
MINVALUE 0
START WITH 0
INCREMENT BY 1;
CREATE SEQUENCE idObslugiPrzezPracownika
MINVALUE 0
START WITH 0
INCREMENT BY 1;