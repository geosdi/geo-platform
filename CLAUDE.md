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

1. Far unire da DominoKit la correzione di domino-jackson che trova `@JsonTypeInfo`/`@JsonSubTypes` anche sui supertipi
   (classi e interfacce), come fa Jackson. **Correzione, prova, issue e PR fatte il 2026-11-04; resta la revisione.**
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
   - Aperte il 2026-11-04 l'issue [#101](https://github.com/DominoKit/domino-jackson/issues/101) e la PR
     [#102](https://github.com/DominoKit/domino-jackson/pull/102) su `development`. Da fare: seguire la revisione di
     DominoKit (la CI `deploy.yaml` non era ancora partita, forse in attesa dell'approvazione di un maintainer) e
     correggere sul ramo del fork quello che chiedono.
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
2. Sostituire RestyGWT con Domino REST. **Migrazione fatta il 2026-11-04 sui rami `domino-rest`** di
   `geoplatform-dependencies`, geo-platform e `gp_advanced_extensions` (pubblicati su `origin`, non ancora uniti a
   `spring-upgrade`): Domino REST è mantenuto, usa `jakarta.ws.rs` e funziona con GWT 2, mentre RestyGWT 2.2.7 (maggio
   2020) legge solo `javax.ws.rs` e `javax.xml.bind`.
   - BOM `geoplatform-gwt-dependency` (pom e `build.gradle`): `domino-rest-client`/`domino-rest-processor` 2.0.2,
     `domino-jackson`/`domino-jackson-processor` 1.0.6, `elemental2` (`core`, `dom`, `promise`) 1.3.2. GWT resta
     2.13.1, che è l'ultima. RestyGWT e gwt-jackson sono ancora nel BOM.
   - Client migrati: `LayerBuilderService` (`layer-builder-gwt`), `FileImporterService`, `CSVImporterService`,
     `WFSGeocodingService` (`gp-wfst-widget`): interfacce `jakarta.ws.rs` con `@RequestFactory(serviceRoot =
     "http://localhost:8080/geoplatform-service")`, stessi URL di prima; i delegate usano la factory generata
     (`...ServiceFactory.INSTANCE.metodo(...).onSuccess(...).onFailed(...).send()`) e chiamano
     `DominoRestConfig.initDefaults()` in un blocco statico; dove il chiamante passava un `MethodCallback` ora passa
     `Success` e `Fail`. I messaggi d'errore (`{"gpRestExceptionMessage":{"message":...}}`) si leggono con
     `GPRestErrorMessage` di `basic-widget`. Tolti perché senza chiamanti: `createLayer` (il layer si crea con il
     command dispatcher), `findJobExecution` del CSV importer e il mapper gwt-jackson di `LayerUpdateFeatureRequest`.
     Il secondo `searchAddress` del layer builder è diventato `searchAddressByLonLat` (Domino genera una classe per
     nome di metodo). `geofence-gwt` aveva RestyGWT e `GwtJackson` solo nel pom e nel `.gwt.xml`: tolti.
   - I sorgenti generati dai processor di Domino vanno nel jar, perché il compilatore GWT dell'applicazione legge i
     sorgenti da lì: `maven-resources-plugin` (`copy-generated-gwt-sources`, fase `process-classes`) li copia in
     `target/classes`. In `gp_advanced_extensions` l'esecuzione sta nel `pluginManagement` di `gp-advanced-widget`,
     spenta per default (il plugin gira in tutti i moduli), e i moduli client la accendono con
     `copy-generated-gwt-sources.skip=false`; in geo-platform sta nel pom di `gp-wfst-widget`.
   - DTO corretti, errori che c'erano già con RestyGWT: `setFid()` in `JDBCTableNameQueryParam` (domino-jackson non usa i
     campi privati senza setter); `@JsonProperty("jDBCTemplateQueryUpdateParam")` in `LayerUpdateFeatureRequestGWT` (il
     server ignorava la proprietà e l'aggiornamento falliva la validazione); `@JsonValue` su `name()` in
     `JDBCColumnSchemaDataType` (Jackson 3 scrive gli enum con `toString()` per default, domino-jackson li legge per
     nome); DTO del geocoding tipizzati (`FeatureDTO` con `FeaturePointDTO` e `FeaturePropertiesDTO` al posto di
     `JSONValue` e `Map<String, Object>`, con `ignoreUnknown`).
   - Verifiche: la prova `prove/domino-rest-count-layer-features` del ramo `domino-rest` di `gp_advanced_extensions`
     (README con prerequisiti) copre tutti i metodi dei quattro servizi, richieste lette dal mapper del server e
     risposte scritte dal server lette da domino-jackson, sulla JVM e in GWT `-prod` (12 + 9 test verdi); la
     compilazione GWT completa della base app (`mvn clean compile gwt:compile`, il goal `compile` nel pom è commentato)
     riesce. Ignora 18 file già rotti e non usati: 15 di un vecchio `gwt-user-2.0.3.jar` che arriva nel classpath della
     base app (da capire da dove), `java.sql.Timestamp` di GWT, `GPLayerBuilderFeatureStore` e
     `GPLayerBuilderFeatureAttributeStore` di `layer-builder-service-api`.
   - Finché domino-jackson non rilascia la correzione del punto 1, `countLayerFeatures` e `loadLayerSchema` mandano
     `JDBCTableNameQueryParam` senza `"type"` e il server risponde `InvalidTypeIdException`. `-Ddomino-jackson.version`
     da riga di comando non cambia la versione del BOM importato: per provare il fork in un modulo del progetto va
     cambiata la versione nel BOM.
   - Da fare: unire i rami `domino-rest` a `spring-upgrade` (chiedere prima a Peppino); togliere RestyGWT dal BOM;
     passare a domino-jackson i due usi rimasti di gwt-jackson (`FileImporterUploader` e `CSVFileUploader`, che leggono
     `GPCreateLayerBuilderResponse` con una `Map<String, Object>`) e poi togliere gwt-jackson; spostare la service root
     `localhost:8080` in una configurazione (serve per Docker, punto 3).
3. Dockerizzare il frontend (`geoplatform-base-app`) e il backend (lo stack dei servizi di `geoplatform-services`), per
   avviare davvero l'applicazione: dettagli e decisioni nel `CLAUDE.md` di `../gp_advanced_extensions` ("Stato del
   lavoro" e "`mvn gwt:run` della base app"). La compilazione GWT della base app riesce di nuovo con i rami
   `domino-rest` (prima si fermava perché il generatore di RestyGWT cercava `javax.xml.bind`).
4. Con l'applicazione avviata, verificare dal client le chiamate REST migrate a Domino (layer builder, workspace del
   file e del CSV importer, geocoding WFS).
5. Gli 8 test di `geoplatform-services-test` sui server esterni: GeoServer va pulito prima di creare stile e namespace,
   per il WFS va capito cosa restituisce oggi.
6. `geoplatform-geoserver-rest`: nelle request PUT/DELETE il successo viene dedotto dal body vuoto; va deciso dallo
   status HTTP.
