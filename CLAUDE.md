# geo-platform

Framework webgis multi-modulo Maven (Java 21). Le versioni delle dipendenze stanno nel progetto
`geoplatform-dependencies` (in `../geoplatform-dependencies`), importato come BOM.

## Remote

- `upstream`: `geosdi/geo-platform`, il progetto del gruppo.
- `origin`: il fork personale.

## Commit

- Nei messaggi di commit e nelle descrizioni delle PR non citare mai Claude: niente riga
  `Co-Authored-By: Claude ...` né "Generated with Claude Code".

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

- Duplicato `com.sun.activation:jakarta.activation` / `jakarta.activation-api` nei 9 moduli della posta
  (`mail-support`, `async-mail-support`, `scheduler-delegate` e chi ne dipende): resta finché `jakarta.mail` 2.0.2 non
  passa ad `angus-mail` con `angus-activation`.
- Gli 8 test di `geoplatform-services-test` sui server esterni: GeoServer va pulito prima di creare stile e namespace,
  per il WFS va capito cosa restituisce oggi.
- Fork di RestyGWT. La 2.2.7 (maggio 2020) è l'ultima pubblicata e il suo generatore (`rebind`:
  `RestServiceClassCreator`, `JsonEncoderDecoderClassCreator`, `AnnotationCopyUtil`) legge solo `javax.ws.rs` e
  `javax.xml.bind.annotation.XmlTransient`; il codice `client` non usa `javax`. Nel fork il generatore va fatto leggere
  sia `javax` sia `jakarta`; poi si portano a `jakarta.ws.rs` le interfacce client RestyGWT di `gp_advanced_extensions`
  (`LayerBuilderService`, `CSVImporterService`, `FileImporterService`), che fino ad allora restano `javax.ws.rs`. Da
  decidere: dove ospitarlo, groupId, versione nel BOM `geoplatform-gwt-dependency`.
- Verificare dal client, con l'applicazione avviata, una chiamata del layer builder e un import CSV: dopo il passaggio
  a jakarta Jackson sul server legge `@XmlAccessorType(FIELD)` e il JSON di alcuni DTO è cambiato (vedi i commit
  "Migrate layer-builder/csv-importer to jakarta.xml.bind" di `gp_advanced_extensions`).
