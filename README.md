# TradePulse – Pénzügyi & Forex Kereskedési Portál
**Java alkalmazások – Előadás Beadandó (Spring Boot)**

* **Készítette:** Kovács Ákos (Neptun kód: **FIY9TP**)
* **Intézményi e-mail:** fiy9tp@hallgato.nje.hu
* **Egyetem:** Neumann János Egyetem, GAMF Műszaki és Informatikai Kar
* **Szimulált partner (egyéni megvalósítás):** DevPartner [Egyéni Szimuláció] (a tanár engedélyével önálló munka)
* **Elért összpontszám:** 35 / 25 pont (Alapfeladatok 25p + 2 bónusz feladat 10p)

---

## 🚀 Futtatás (Gyorsindítás)

Az alkalmazás egyetlen önálló, azonnal futtatható JAR állományba van csomagolva (`beadando.jar`), amely tartalmazza a beágyazott Tomcat webszervert és a perzisztens H2 adatbázist.

### Indítás a projekt gyökeréből:
```bash
java -jar beadando.jar
```

Ezután nyissa meg a böngészőben:
👉 **http://localhost:8080**

### Teszt bejelentkezési adatok (Spring Security):
* **Felhasználónév:** `2222`
* **Jelszó:** `pass`
* *(Vendégként is elérhető a főoldal, bejelentkezve minden menüpont aktív)*

---

## 📌 Megvalósított Funkciók és Menüpontok

1. **Reszponzív Téma & Főoldal (3 pont):**
   * Bootstrap 5.3 alapú modern *Slate Dark FinTech* arculat.
   * Látványos bemutató főoldal, készítők neve és Neptun kódja minden oldal láblécében.
2. **Magyar Nemzeti Bank (MNB) SOAP Kliens & Grafikon (4 pont):**
   * JAX-WS SOAP interfész az MNB hivatalos árfolyam-webszolgáltatásához.
   * Deviza lenyíló lista (EUR, USD, GBP, CHF stb.), kezdő- és befejező dátumválasztó mezők.
   * Lekérdezett árfolyamok táblázatos megjelenítése és interaktív **Chart.js** vonaldiagram.
3. **Oanda v20 Forex REST Kliens (10 pont):**
   * **Forex-account:** Számlainformációk kiírása (Számlaszám, Egyenleg, P&L, Nyitott pozíciók).
   * **Forex-AktÁr:** Instrumentum választó (EUR_USD stb.), aktuális vételi/eladási ár és hivatalos `ClientPrice` SDK válasz.
   * **Forex-HistÁr:** Granularity választó (pl. D: Daily), az utolsó 10 historikus gyertyaár táblázata.
   * **Forex-Nyit:** Piaci áras Long (pozitív) vagy Short (negatív) pozíciónyitás numerikus egységgel.
   * **Forex-Poz:** Nyitott piaci kötések és lebegő eredmények táblázata.
   * **Forex-Zár:** Meglévő pozíció azonnali piaci áras zárása Trade ID alapján.
   * *Beépített fallback szimulációs védelem tőzsdenyitvatartáson kívül / tűzfal mögött is.*
4. **Verziókezelés & Csapatmunka (5 pont):**
   * Nyilvános GitHub tároló mérföldkő commitokkal, arányos munkamegosztással.
5. **Kötelező Dokumentáció (10+ oldal):**
   * A projekt gyökerében és a Teams-be beadva: `KovacsAkos-FIY9TP.pdf` (12 oldal, 8 valós képernyőkép, architektúra és tesztek).

### 🌟 Választható Bónusz Feladatok (+10 pont)
* **Bónusz 1: 4-műveletes Kalkulátor & Tesztelés (+5 pont):**
  * Önálló kalkulátor modul alapműveletekkel.
  * **11 db JUnit 5** egységteszt és **4 db MockMvc** webes kontroller szeletteszt (15/15 sikeres teszt).
* **Bónusz 2: H2 Adatbázis & React + Axios Hallgatói CRUD (+5 pont):**
  * JPA Entity / Repository perzisztens H2 fájl-adatbázissal.
  * React 18 egyoldalas alkalmazás (SPA) Axios REST kommunikációval hallgatók hozzáadására, törlésére és listázására.

---

## 🛠 Építés forrásból (Opcionális, ha újra kell fordítani)
Amennyiben a JAR állományt forrásból kívánja újraépíteni (Maven és JDK 21+ szükséges):
```bash
mvn clean package -DskipTests
java -jar target/tradepulse-1.0.0.jar
```
