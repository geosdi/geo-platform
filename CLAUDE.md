# geo-platform

Framework webgis multi-modulo Maven (Java 21). Le versioni delle dipendenze stanno nel progetto
`geoplatform-dependencies` (in `../geoplatform-dependencies`), importato come BOM.

## Remote

- `upstream`: `geosdi/geo-platform`, il progetto del gruppo.
- `origin`: il fork personale.

## Commit

- Nei messaggi di commit e nelle descrizioni delle PR non citare mai Claude: niente riga
  `Co-Authored-By: Claude ...` né "Generated with Claude Code".
- Promemoria e lavori da riprendere si scrivono in questo file, nei "Lavori aperti", e poi si fanno commit e push: la
  memoria di Claude Code resta sul singolo Mac e su un altro non c'è.

## Build e test

- Alcuni moduli (per esempio `geoplatform-wfs-support`, `geoplatform-wfs-connector`) dipendono da
  `org.geosdi:csv-api-support`, che sta in `../gp_advanced_extensions` e non nel repository dei snapshot: per
  compilarli in locale va installato con `mvn install -Dmaven.test.skip=true -pl gp-advanced-widget/csv-api-support -am`
  da quel progetto.
- Il `pom.xml` padre forza `<skipTests>false</skipTests>` nel surefire: per non lanciare i test serve
  `-Dmaven.test.skip=true`, `-DskipTests` non basta. Con `-Dmaven.test.skip=true` però i test-jar installati restano
  senza classi: se un test non compila per una classe che sta in `src/test` di un altro modulo (per esempio
  `GPConnectorFile` di `geoplatform-connector-api`), reinstallare quel modulo con i test.
- Sempre `mvn clean` nei comandi, e di nuovo `mvn clean` alla fine di compilazioni e test.
- Non mandare l'output completo di Maven su file: i test di `geoplatform-wms-connector` scrivono molto log e Maven
  rallenta da 30 secondi a 9 minuti. Filtrare con `grep` (Tests run, Reactor Summary, BUILD, Total time).
- Lanciare i test con `-Dsurefire.timeout=300`, così un test bloccato non ferma il build.
- I test WFS su server reali (`WFSGetFeaturesRequestTest`, 150.145.141.92 e Protezione Civile) restano reali, non mock.
- In `geoplatform-services-test` falliscono 8 test anche sul codice senza modifiche: dipendono da server esterni
  (GeoServer con stile e namespace già creati, WFS che non restituisce più i dati attesi).

## Binding JAXB

- I binding di `geoplatform-xml-binding` e tutto geo-platform usano `jakarta.xml.bind` (JAXB 4); JAXB javax non c'è più.
- Le classi dei binding stanno in `src/main/java` e in alcune ci sono modifiche fatte a mano. Configurazione, dove stanno
  le modifiche e procedura di rigenerazione: `geoplatform-xml-binding/RIGENERAZIONE-BINDING.md`. Prima di rigenerare,
  generare a parte, fare il diff e rivedere con chi mantiene i binding le modifiche da portare nei `.xjb`.

## Lavori aperti

In ordine di priorità (deciso il 2026-11-03):

1. Proporre a DominoKit la correzione di domino-jackson che trova `@JsonTypeInfo`/`@JsonSubTypes` anche sui supertipi
   (classi e interfacce), come fa Jackson. **Correzione e prova fatte il 2026-11-04; restano issue e PR.**
   - domino-jackson è un repository a sé, non un sottomodulo di domino-rest:
     [DominoKit/domino-jackson](https://github.com/DominoKit/domino-jackson), moduli `domino-jackson` e
     `domino-jackson-processor`. Fork su GitHub sotto `glascaleia`, clone in `../domino-jackson` con `origin` (fork) e
     `upstream` (DominoKit). Si compila con il JDK 21 (CI di DominoKit su 11, 17, 21 e 23); con il JDK 27 lo spotless
     del progetto non gira.
   - Fatto: commit `58d497d` "Inherit @JsonTypeInfo and @JsonSubTypes from supertypes, as Jackson does" sul ramo
     `fix/subtypes-from-supertypes` (da `upstream/development`), pushato sul fork. `Type.getSubTypes` cerca le
     annotazioni sul tipo e poi sui supertipi (interfacce prima della superclasse, come Jackson) e, se `@JsonSubTypes`
     viene da un supertipo, tiene solo i sottotipi assegnabili. Test nuovi nel package `supertype` del processor
     (stesso schema di `JDBCTableNameQueryParam`, confronto con Jackson); build verde con JDK 11, 17 e 21.
   - Cambia un comportamento, da scrivere nell'issue e nella PR: `InheritanceWithJsonTypeInfoTest` si aspettava
     `genericField` (dichiarato `DataInput`, sottoclasse di `Data` annotata) senza `"@type"`; Jackson lo scrive e
     rifiuta il JSON senza (`InvalidTypeIdException`). Il test ora si aspetta il `"@type"` e confronta con Jackson.
   - Da fare: issue su DominoKit (testo in inglese preparato il 2026-11-04: problema, esempio minimo, confronto con
     Jackson, il cambiamento sopra), poi PR su `development` dal ramo del fork con titolo "Fixes #N" (come le ultime
     unite, #96 e #98 del 2026-11-02). Nessuna issue esistente lo copre: la #19 è la richiesta originale del supporto,
     la #86 un altro bug di generazione dei sottotipi.
   - Prova riuscita il 2026-11-04, salvata nel ramo `domino-rest` di `gp_advanced_extensions` (pushato su `origin`),
     cartella `prove/domino-rest-count-layer-features`, con README su prerequisiti ed esecuzione. Con il processor del
     fork e senza annotazioni aggiunte ai DTO: domino-jackson, in GWT `-prod` e sulla JVM, scrive
     `{"tableName":{"type":"JDBCTableNameQueryParam",...},"lang":"it"}`, il server lo legge (anche con
     `FAIL_ON_UNKNOWN_PROPERTIES`) e senza `"type"` risponde `InvalidTypeIdException`; RestyGWT scrive lo stesso JSON.
   - Il mapper che il server usa davvero è `getConfiguredMapper()` di `CXFJacksonProvider` (introspector Jackson più
     JAXB); `getDefaultMapper()` ha solo l'introspector JAXB e ignora `@JsonTypeInfo`, quindi non va usato nelle
     verifiche.
   - Finché la PR non è rilasciata, decidere se usare la versione del fork (dove pubblicarla, groupId, versione nel
     BOM `geoplatform-gwt-dependency`).
2. Sostituire RestyGWT con Domino REST (`org.dominokit:domino-rest-client` 2.0.2, JSON con `domino-jackson` 1.0.6),
   invece del fork di RestyGWT: RestyGWT 2.2.7 (maggio 2020) è l'ultima versione e il suo generatore legge solo
   `javax.ws.rs` e `javax.xml.bind`; Domino REST è mantenuto, usa `jakarta.ws.rs` e funziona con GWT 2. RestyGWT è
   usato in 15 file: `layer-builder-gwt`, `csv-importer-gwt`, `file-importer-gwt` di `gp_advanced_extensions` e
   `gp-wfst-widget` di geo-platform. Prova del 2026-11-03 su `countLayerFeatures` (`CountLayerFeaturesRequest`): il
   client Domino si genera da un'interfaccia `jakarta.ws.rs` e il server legge il suo JSON, dopo questi adattamenti:
   - domino-jackson non usa i campi privati senza setter (genera un'assegnazione diretta che non compila): a
     `JDBCTableNameQueryParam` serve `setFid()` (aggiunto nel ramo `domino-rest` di `gp_advanced_extensions`), e così
     agli altri DTO nella stessa situazione.
   - domino-jackson legge `@JsonTypeInfo`/`@JsonSubTypes` solo sulla classe dichiarata della proprietà, non sulle
     interfacce (`Type.getSubTypes` del processor); il server invece vuole il `"type"` dichiarato sulle interfacce
     (per esempio `GPJDBCTemplateAddQueryParam`) e senza risponde `InvalidTypeIdException`. 8 file con `@JsonTypeInfo`
     e 25 sottotipi, quasi tutti nel layer builder. Si risolve con la correzione di domino-jackson del punto 1 (provata
     il 2026-11-04), non annotando i sottotipi uno per uno.
   - fissare `com.google.elemental2` (`core`, `dom`, `promise`) a una sola versione (1.3.2) nel BOM
     `geoplatform-gwt-dependency`: oggi Maven mescola 1.1.0 e 1.3.2 e non compila.
3. Controllare la compilazione GWT della base app, poi dockerizzare. Il generatore di RestyGWT carica
   `javax.xml.bind.annotation.XmlTransient` e senza `javax.xml.bind:jaxb-api` sul classpath la compilazione GWT si
   ferma; `layer-builder-gwt` non lo ha più, quindi la compilazione GWT della base app è probabilmente rotta dal commit
   "Remove the javax JAXB code and libraries" (`geoplatform-base-app` non è clonata su tutti i Mac). Poi
   dockerizzare il frontend (`geoplatform-base-app`) e il backend (lo stack dei servizi di `geoplatform-services`), per
   avviare davvero l'applicazione: dettagli e decisioni nel `CLAUDE.md` di `../gp_advanced_extensions` ("Stato del
   lavoro" e "`mvn gwt:run` della base app").
4. Con l'applicazione avviata, verificare dal client una chiamata del layer builder. Il client RestyGWT
   (`LayerBuilderService`) manda DTO passati a jakarta (per esempio `CountLayerFeaturesRequest` con
   `JDBCTableNameQueryParam`) e il loro JSON lo scrive RestyGWT, non Jackson; sul server Jackson ora legge
   `@XmlAccessorType(FIELD)` (vedi il commit "Migrate layer-builder to jakarta.xml.bind" di `gp_advanced_extensions`).
5. Gli 8 test di `geoplatform-services-test` sui server esterni: GeoServer va pulito prima di creare stile e namespace,
   per il WFS va capito cosa restituisce oggi.
6. `geoplatform-geoserver-rest`: nelle request PUT/DELETE il successo viene dedotto dal body vuoto; va deciso dallo
   status HTTP.
