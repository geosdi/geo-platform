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
     2.13.1, che è l'ultima. gwt-jackson tolto; RestyGWT è ancora nel BOM (lo usa la prova
     per il confronto).
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
   - Le risposte degli upload, che le servlet della base app girano al browser così come le scrive il servizio, si
     leggono con domino-jackson in DTO del client e poi si convertono nei tipi di prima, perché domino-jackson non
     legge `Map<String, Object>`, `Object` né campi dichiarati come interfaccia senza `@JsonTypeInfo` (ignora
     `@JsonDeserialize`): `FileImporterLayerResponse` per il `GPCreateLayerBuilderResponse` del file importer (la mappa
     `layerProperties` si ricostruisce con `toLayerPropertiesMap()`), `CSVSchemaResponse` per il `GPCSVSchema`
     dell'upload e della ricarica del CSV importer (`toSchema()`, valori delle celle come stringhe). gwt-jackson non è
     più usato.
   - Verifiche: la prova `prove/domino-rest-count-layer-features` del ramo `domino-rest` di `gp_advanced_extensions`
     (README con prerequisiti) copre tutti i metodi dei quattro servizi, richieste lette dal mapper del server e
     risposte scritte dal server lette da domino-jackson, sulla JVM e in GWT `-prod` (14 + 11 test verdi); la
     compilazione GWT completa della base app (`mvn clean compile gwt:compile`, il goal `compile` nel pom è commentato)
     riesce. Ignorava 18 file già rotti e non usati; 15 erano di un vecchio `com.google.gwt:gwt-user` 2.0.3, portato da
     `gwt-ajaxloader` (via `gwt-maps-api` di `streetview`), finito anche nel `WEB-INF/lib` del war: escluso nel pom di
     `streetview` (ramo `domino-rest` di `gp_advanced_extensions`), ora le unità ignorate sono 2.
   - Finché domino-jackson non rilascia la correzione del punto 1, `countLayerFeatures` e `loadLayerSchema` mandano
     `JDBCTableNameQueryParam` senza `"type"` e il server risponde `InvalidTypeIdException`. `-Ddomino-jackson.version`
     da riga di comando non cambia la versione del BOM importato: per provare il fork in un modulo del progetto va
     cambiata la versione nel BOM.
   - Lo stesso DTO ha il suo mapper generato in più moduli (stesso nome e package, per esempio quello di
     `GPLayerBuilderWorkSpacesResponse`): con la stessa versione di domino-jackson sono identici, ma si potrebbero
     generare una volta sola nel modulo dei DTO.
   - Da fare: provare dalla UI (punti 3 e 4); unire i rami `domino-rest` a `spring-upgrade` (chiedere prima a
     Peppino) e togliere RestyGWT dal BOM; spostare la service root `localhost:8080` in una configurazione (serve per
     Docker, punto 3).
3. Dockerizzare il frontend (`geoplatform-base-app`) e il backend, per avviare davvero l'applicazione. **Backend fatto
   il 2026-11-04**: cartella `docker/` del ramo `docker` di `geoplatform-base-app` (pubblicato su `origin`), con
   README. `geoplatform-service.war` (tutti i profili, `-Dfull`) su Tomcat 11 (Spring 7 vuole Jakarta EE 11), PostGIS
   (database `gp`, `jdbc_template` con le tabelle di Spring Batch, `preview_gp`), GeoServer 2.28.4 (la `V28x` di
   `GeoserverVersion`) con l'estensione Importer e la risorsa JNDI `jdbc/postgres_layerbuilder`, Mailpit per le mail.
   La configurazione sta in `GP_DATA_DIR` (system property, come le altre `GP_*`). I dati del core li carica
   `BootstrapInitializerTest` di `geoplatform-initializer` (`bootstrap-core.sh`, cancella e ricrea il core):
   `insert.sql` di `geoplatform-dao` non corrisponde più al modello. Provati da `curl`: servizi CXF, geocoding
   (Nominatim vero), CORS, `loadWorkspaces`, `createWorkspace`, `countLayerFeatures` e `loadLayerSchema` (con
   `"type"`). Al primo avvio sono saltati fuori tre errori, corretti nei rami `domino-rest`: ehcache 3.4.0 javax di
   `gt-geotiff` nel war, FreeMarker 2.3.34 di `hibernate-reveng` (versione ora nel BOM `geoplatform-spring-dependency`),
   NPE di `loadWorkspaces` con GeoServer senza workspace. **Frontend fatto il 2026-11-04**: container `baseapp`
   (`baseapp.war` nella radice di Tomcat 11, http://localhost:9393/BaseApp.html, account `admin`/`admin`), costruito da
   `build-frontend.sh`, configurazione in `frontend/conf/gp-ws.properties`; in `web.xml` la configurazione della stampa
   ora è relativa (`WEB-INF/config.yaml`) invece del percorso assoluto di un Mac. Il browser chiama i servizi Domino su
   `localhost:8080` (CORS). Da fare: la service root in una configurazione e nginx davanti (stessa origine); gli
   indirizzi di GeoServer (`http://geoserver:8080` dentro compose, che il browser non risolve); il datastore JNDI del layer builder si crea solo alla creazione del layer, da provare dalla UI;
   un `logback.xml` meno verboso per il container (oggi alcuni logger scrivono chiavi e password). Dettagli e decisioni
   sulla modalità sviluppo nel `CLAUDE.md` di `../gp_advanced_extensions` ("`mvn gwt:run` della base app").
4. Con l'applicazione avviata, verificare dal client le chiamate REST migrate a Domino (layer builder, workspace del
   file e del CSV importer, geocoding WFS). **Prova dalla UI iniziata il 2026-11-04**: stato, correzioni fatte e
   lavori rimasti, in ordine, nei "Lavori aperti" di `../geoplatform-base-app/CLAUDE.md` (ramo `docker`). Quello è il
   riferimento per i punti 3 e 4.
5. Gli 8 test di `geoplatform-services-test` sui server esterni: GeoServer va pulito prima di creare stile e namespace,
   per il WFS va capito cosa restituisce oggi.
6. `geoplatform-geoserver-rest`: nelle request PUT/DELETE il successo viene dedotto dal body vuoto; va deciso dallo
   status HTTP.
7. ElasticSearch 9 accanto alla 7 (deciso con Peppino il 2026-11-12). **Fatto il 2026-11-12 sui rami
   `elasticsearch-9`** di `geoplatform-dependencies` e geo-platform (da `spring-upgrade`, pubblicati su `origin`), con README in
   `geoplatform-experimental/geoplatform-elastic-search/geoplatform-elastic-search-9`.
   - La 7.17 è fuori supporto dal 15 gennaio 2026. La 9 non chiede chiavi: licenza Basic gratuita, codice AGPLv3, SSPL
     o ELv2, client Java Apache 2.0.
   - BOM nuovo `geoplatform-elasticsearch9-dependency`: `elasticsearch-java` e `elasticsearch-rest5-client` 9.5.6
     (senza `jackson-core`/`jackson-databind` 2, il client usa `Jackson3JsonpMapper`), Testcontainers 2.0.5.
   - Le classi della 7 senza ElasticSearch sono passate, con gli stessi package, in `geoplatform-elastic-search-commons`
     (dall'api) e `geoplatform-elastic-search-rs-commons` (dal connettore REST): l'api e il connettore della 7 ne
     dipendono, per chi li usa non cambia niente. `gp-elasticsearch-query-builder` resta solo sulla 7.
   - Modulo `geoplatform-elastic-search-9` (`-api` e `-rs-connector`, package `el9`): stessi nomi della 7, stesse
     proprietà (`gp.elastic-search-rest.properties`, jasypt); i documenti passano dal mapper di
     `geoplatform-jackson-support` del DAO, nome radice compreso. Differenze nel README.
   - Due difetti della 7 corretti solo nella 9: `persist` con un id già usato sovrascriveva il documento e poi falliva
     (ora `op_type=create`); l'id scelto da ElasticSearch finiva in una chiave `"radice.id"` fuori dal documento.
   - Test con Testcontainers (serve Docker) su ElasticSearch 9.5.6, HTTP con password cifrata e HTTPS con la CA: verdi.
   - **Prossimo passo** (deciso con Peppino il 2026-11-12): annotazioni nostre sui documenti (tipo del campo,
     sottocampi, analyzer, geo_point e simili) da cui un pattern Visitor costruisce il mapping corretto (il
     `TypeMapping` di `preparePutMapping()`), invece di scriverlo a mano in JSON. Prima di scrivere codice proporre
     a Peppino le annotazioni e la struttura del Visitor; decidere con lui se vale anche per la 7. Proposta scritta il
     2026-11-12 in `geoplatform-experimental/geoplatform-elastic-search/docs/proposta-annotazioni-mapping.md`, con i
     punti da decidere in fondo: aspettare le sue scelte prima del codice.
   - Da fare dopo: unire i rami (chiedere a Peppino); portare itineris sulla 9 quando i server passano alla 9
     (prima 8.x, poi 9, reindicizzando gli indici creati con la 7). Itineris oggi non compila su questo Mac per
     `edu.ucar:cdm-core` (certificato del repository di Unidata), non per questo lavoro.
