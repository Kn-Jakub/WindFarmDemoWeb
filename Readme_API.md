
# Dokumentacia k API

### Navrh Jakub:
Kazdy uzivatel ma databazu priradeny miest.
Po prihlaseni uzivatela sa na **HomePage** zobrazí list nakonfigurovanych miest.

Každé mesto obsahuje zakladne info:

- nazov mesta
- datum poslednej aktualizacie
- posledna vyska teploty, vlhkosti, vetra ...

Na dane polozky listu sa da kliknut, po kliknuti sa zobrazí stranka daneho mesta s informáciami o počasí [*tu sa bude dopytovat back-endu na data pre dane mesto* ]

... treba sa dohodnut co sa bude zobrazovat na zaklade moznosti openwheaterinfo.com




##### Klasifikácia

Databaza ulozenych miest pre kazdeho pouzivatela:

- nazov: Cities +  *userID*

Prvky:

                    name        Temperature    Humidity       ....
    1.record        Vieden      30             80             ....
    2.record        .....         .....         .....         ....
