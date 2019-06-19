# WindFarmDemo - Mikroservisy
Projekt sa skladá z viacerých mikroservisov, ktoré sú postavené na frameworku **Dropwizard**. Jednotlivé mikroservisy vzájomne komunikujú prostredníctvom **REST** rozhraní.

V projekte sú zahrnuté 3 repozitáre:
- Back-end server -> [WindFarmDemo](https://github.com/Kn-Jakub/WindFarmDemo.git)
- Front-end webserver -> [WindFarmDemoWeb](https://github.com/Kn-Jakub/WindFarmDemoWeb.git)
- Sensors emulator -> [SensorsEmulator](https://github.com/JosoP/SensorsEmulator)

Serverové aplikácie sú postavené na rozpracovaných aplikáciach z repozitárov vyučujúceho [Ing.Martina Hudíka PhD.](https://github.com/hudikm):
- [Windfarm demo](https://github.com/hudikm/WindFarmDemoWeb)
- [Windfarm demo web](https://github.com/hudikm/WindFarmDemoWeb)

##Požiadavky na Semestrálnu prácu
Tím študentov implemetuje niekoľko mikroservisov pre spracovanie a zobrazenie dát v prostredí IoT. Aplikácia bude mať architektúru typu mikroservis. Preferovaný programovací jazyk je Java a webová platforma Dropwizard, SQL databáza s použitím ORM Hibernate alebo JDBI rozhraní.

#### Požiadavky na dané komponenty

##### Back-end server
- prijíma a poskytuje dáta
- REST-ové rozhranie
- pripojenie do databázy
- možnosť využívania CRUD operácii
- pri prístupe na server používat Basic alebo OAuth2 autentifikáciu
- dáta musia byť validované (obmedziť možnosť zapisovania neplatných dát do databázy)

##### Front-end server
- načítava dáta zo servera prostredníctvom REST rozhrania
- umožňuje správu nad dátami
- zobrazuje uživateľovi prijaté dáta

##### End-point devices/ emulator
- zabezpečuje generovanie dát
- autentifikácia na servery

##Návrh riešenia
Nakoľko zo zadania vyplýva možnosť použitia rozpracovaného projektu **WindFarmDemo**, viaže sa riešenie tohoto projektu práve k prvkom, na ktorých je daný projekt postavený.

Windfarmdemo je aplikácia postavená na frameworku dropwizard, ktorý je poskladaný z nasledujúcich knižníc:

- Jetty HTTP web server.
- Jersey RESTové rozhranie.
- Jackson JSON serializovanie a deserializovanie.
- Logback logovanie.
- Hibernate Validator validovanie dát'
- JDBI a Hibernate databáza
- Liquibase migrácia.
- FreeMarker a mustache generovanie webových stránok

Bližší popis danej implementácie je na https://hudikm.github.io/WindfarmDemoDocs/

##Popis implementácie

### Server [WindFarmDemo]

####Sieťové parametre

**- typ:** http  **port:** 8085
**- typ:** https **port:** 8445


####Popis
Tento mikroservis slúži hlavne na spravovanie databázy systému a na poskytovanie dát frontend mikroservisu. Ďalšou funkcionalitou je komunikácia s emulátorom senzorov a jeho automatické konfigurovanie podľa potrieb systému.

Komunikácia s frontend mikroservisom je zabezpečená oauth2 autentifikáciou.

Komunikácia so senzormi je zabezpečená pomocou kľúčou, ktoré generuje tento mikroservis. Kľúč je jedinečný pre každý emulovaný senzor. Bez toho aby pri zápise dát neprišiel k daným dátam aj správny kľúč senzora, nebudú dáta uložené v databáze.


####Iná dokumentácia

Pre **API** v tomto projekte je vygenerovaná dokumntácia pomocou framework-u swagger. Táto dokumentácia je dostupná na adrese http://localhost:8085/backend/swagger.
**JavaDoc** dokumentácia je vygenerovaná v priečinku `PROJECT_DIR/javadoc/`.

Emulátor senzorov

#### Web Server [WindFarmDemoWeb]
Aplikácia predstavuje webové rozhranie, ktore zabezpečuje zobrazovanie dát uživateľovi.

Skladá sa z java aplikácie a ftl template=ov ktoré sú spojené prostredníctvom Freemaker nástroja.

Aplikácia obsahuje dokumentáciu a taktiež popis REST rozhrania pomocou nástroja **swagger**

Spomínaná dokumentácia je dostupna na:

- swagger : http://localhost:8080/swagger/
- Java DOC: ~projekt/javadoc/index.html

## Sensors Emulator

####Sieťové parametre
**- typ:** http  **port:** 8090

####Popis
Tento projekt simuluje senzory na zaznamenávanie parametrov počasia. Na túto simuláciu používa dáta z externého API https://openweathermap.org/. V jadre tohoto servisu beží vlákono ktoré v intervale 20 minút vyžiada informácie z externého API a odošle ich do Backend mikroservisu. Komunikácia s backend mikroservisom je zabezpečená kľúčom, bez ktorého nie je možné do tohoto servisu odoslať dáta, aby sa uložili v databáze. Tento kľúč je pre každý simulovaný senzor jedninečný. Kľúče k daným mestám generuje backend mikroservis.

Senzory ktoré tento mikroservis spravuje sú nakonfigurované z backend mikroservisu pomocou REST API rozhrania.

Servis nie je možné spúšťať aj keď backend mikroservis nebeží. Dáta však nebudú zhromažďované. Po spustení bakend mikroservisu sa zista potrebné informácie a začne sa vykonávanie servisu.

---

####Iná dokumentácia
Pre **API** v tomto projekte je vygenerovaná dokumntácia pomocou framework-u swagger. Táto dokumentácia je dostupná na adrese http://localhost:8090/swagger.
**JavaDoc** dokumentácie je vygenerovaná v priečinku `PROJECT_DIR/javadoc/`.

Spustenie aplikacie
---

1. Spusti `mvn clean install` pre vytvorenie aplikacie
1. Spusti aplikaciu pomocou `java -jar target/WindFarmDemo-1.0-SNAPSHOT.jar server config.yml`
1. Pre overenie funkčnosti`http://localhost:8080`

Health Check
---

Pre test aplikacie spusti `http://localhost:8081/healthcheck`
