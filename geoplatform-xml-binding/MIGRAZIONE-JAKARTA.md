# Migrazione dei binding a jakarta.xml.bind

I binding di questo modulo sono le classi Java generate dagli XSD (e da una DTD) degli standard OGC e ISO. Erano generati
con `org.jvnet.jaxb2.maven2:maven-jaxb2-plugin` e jaxb2-basics per `javax.xml.bind`. La migrazione li porta a
`jakarta.xml.bind` (JAXB 4) con `org.jvnet.jaxb:jaxb-maven-plugin` 4.x e `jaxb-plugins`, insieme al codice che li usa.
Il lavoro si fa sul ramo `jakarta-bindings` e a fine lavoro va su `spring-upgrade`.

## Stato

I binding non si possono migrare uno alla volta: un `JAXBContext` jakarta non legge classi annotate javax e viceversa,
quindi un binding passa a jakarta insieme a quelli di cui usa i tipi. I gruppi vengono dagli episodi e dalle dipendenze
Maven tra i moduli.

| Gruppo | Moduli | Stato |
|---|---|---|
| 1 | `wms-1.1.1` | fatto |
| 3 | `xlink-1.0.0`, `gml-2.1.2`, `filter-1.0.0`, `sld-1.0.0`, `wmc-1.1.0`, `ows-1.1.0`, `wps-1.0.0`, `wms-1.3.0` | fatto |
| 2 | `gml-3.2.0`, `iso19139-v20060504` | da fare |
| 4 | `gml-api`, `gml-3.1.1`, `gml-3.2.1`, `filter-1.1.0`, `ows-1.0.0`, `w3c-xsd`, `wfs-1.1.0`, `csw`, `ebrim`, `feature-catalogue`, `iso19139-v20070417` | da fare |

Con il gruppo 3 sono passati a jakarta anche il connettore GeoServer (il suo modello contiene i tipi SLD),
`wps-connector`, `wmc-jaxb-support` e la parte WMS 1.3.0 di `wms-connector`.

`geoplatform-xml-context-provider` non dipende da JAXB e non va migrato.

## Configurazione

- Il pom di questo modulo (`pluginManagement`) ha due plugin: `org.jvnet.jaxb2.maven2:maven-jaxb2-plugin` per i gruppi
  ancora javax e `org.jvnet.jaxb:jaxb-maven-plugin` (versione `${jaxb-tools-version}`) per quelli jakarta, con gli stessi
  argomenti più `-Xannotate`.
- I moduli jakarta dipendono da `jakarta.xml.bind-api`, `org.glassfish.jaxb:jaxb-runtime` e
  `org.jvnet.jaxb:jaxb-plugins-runtime`, non più da `jaxb-api`, `jaxb-impl`, `javax.activation` e
  `jaxb2-basics-runtime`.
- I `.xjb` usano il namespace `https://jakarta.ee/xml/ns/jaxb` con `version="3.0"`.

## Le classi generate stanno in `src`

Il plugin non gira durante il build (nessuna `execution`): le classi generate sono salvate in `src/main/java`. In alcune
erano state fatte modifiche a mano. Con la migrazione queste modifiche sono state spostate nei `.xjb`, così una nuova
generazione le riproduce:

| Modulo | Modifica | Come è scritta nel `.xjb` |
|---|---|---|
| `gml-2.1.2` | `@XmlRootElement` su `Box`, `Point`, `LineString`, `LinearRing`, `Polygon`, `MultiPoint`, `MultiLineString`, `MultiPolygon`, `MultiGeometry` | `annox:annotateClass` |
| `wmc-1.1.0` | `@XmlRootElement` su `Extension`, `FormatList`, `General`, `LayerList`, `Layer`, `Server`, `StyleList`, `ViewContextType` | `annox:annotateClass` |
| `sld-1.0.0` | `@XmlType(name = "NamedLayer")` e `@XmlType(name = "UserStyle")` al posto del tipo anonimo | `annox:annotateClass`, che sostituisce l'`@XmlType` generato |
| `wms-1.3.0` | prefisso `xmlns` `wms` nel `package-info` | `namespace:prefix`, con `-Xnamespace-prefix` attivo solo in questo modulo |

Restano scritte a mano, in file separati, le classi che registrano i binding (`*ContextServiceProvider*`, e
`WMCServiceProviderV110`): la generazione non le tocca.

Note sui plugin:
- `-Xnamespace-prefix` senza personalizzazioni aggiunge un prefisso vuoto a ogni namespace: va attivato solo dove serve.
- `-XremoveAnnotation` non funziona insieme a `-Xannotate` (il secondo rifiuta i suoi elementi); per sostituire
  un'annotazione basta `annotateClass`.
- I vecchi namespace `http://jaxb2-commons.dev.java.net/basic/...` di jaxb2-basics sono ancora accettati.

## Come rigenerare un gruppo

1. Prima della migrazione, generare il modulo con il plugin javax in una cartella fuori da `src` e confrontarlo con
   `src/main/java` ignorando formattazione e import: le differenze che non vengono dalla versione del generatore sono
   modifiche fatte a mano da riportare nel `.xjb`.
2. Portare pom e `.xjb` a jakarta.
3. Generare un modulo alla volta, nell'ordine delle dipendenze, con i moduli da cui dipende nello stesso reactor (gli
   episodi devono essere quelli jakarta, non quelli javax in `~/.m2`):

   ```
   mvn -pl <dipendenze>,<modulo> clean process-resources \
       org.jvnet.jaxb:jaxb-maven-plugin:4.0.16:generate -Dmaven.xjc2.generateDirectory=target/gen-jakarta
   ```
4. Confrontare `target/gen-jakarta` con `src/main/java`: devono restare solo differenze del generatore (`ToString2` che
   diventa `ToString`, `appendField`, ordine di `@XmlSeeAlso` e dell'`ObjectFactory`, `new ArrayList<>()`).
5. Copiare le classi in `src/main/java` aggiungendo l'intestazione della licenza (`license-maven-plugin`) e copiare
   `META-INF/sun-jaxb.episode`. Non copiare l'`ObjectFactory` dei package da cui il modulo dipende, che la generazione
   produce per via degli episodi.

## Classi jakarta parallele

Finché ci sono binding javax, accanto alle classi javax condivise ci sono le versioni jakarta, che restano dopo la
migrazione:
- `geoplatform-connector-api`: `GPJakartaConnectorRequest`, `GPGetJakartaConnectorRequest`,
  `GPPostJakartaConnectorRequest`, `IGPostJakartaConnectorRequest` e le basi, `JakartaContextConnectorRepository`;
- `geoplatform-wms-connector`: `WMSJakartaContext`, `WMSConnectorJakartaContextV111` e `V130`, le basi
  `*JakartaRequest` (le classi javax WMS sono già state tolte);
- `geoplatform-ws-base-util`: `GPGenericJakartaMapAdapter`, `GPGenericJakartaMapType`.

## Cose da controllare in ogni gruppo

- Il codice che usa un contesto javax (`GPJAXBContextBuilder`, `JAXBContext.newInstance`, i repository javax) sulle classi
  migrate compila lo stesso e fallisce solo quando gira: va cercato a mano, non lo segnala il compilatore.
- Per i test, tutti i moduli cambiati vanno nello stesso reactor (`-pl ... -amd`), mai con `-rf`: altrimenti Maven prende
  da `~/.m2` le versioni javax.
- Lanciare i test con `-Dsurefire.timeout=300`: un test multithread di `wms-connector` resta in attesa per sempre se i
  thread falliscono.
- In `geoplatform-services-test` falliscono 8 test anche senza la migrazione: dipendono da server esterni (GeoServer con
  stile e namespace già creati, WFS che non restituisce più i dati attesi).

## Alla fine

1. Allineare `jakarta-bindings` all'ultimo `spring-upgrade`.
2. Togliere le classi javax non più usate, il builder javax deprecato (`GPJAXBContextBuilder` e il pool), le librerie javax
   e il plugin `maven-jaxb2-plugin`; togliere il suffisso `Jakarta` dalle classi parallele.
3. Build completo con i test, poi merge su `spring-upgrade`.
4. Di questo documento tenere la configurazione e la procedura di rigenerazione, togliendo lo stato dei gruppi.
