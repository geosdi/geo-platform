# Binding JAXB: configurazione e rigenerazione

I binding di questo modulo sono le classi Java generate dagli XSD (e, per `wms-1.1.1`, da una DTD) degli standard OGC e
ISO, per `jakarta.xml.bind` (JAXB 4). Le genera `org.jvnet.jaxb:jaxb-maven-plugin` con `jaxb-plugins`.

Il plugin non gira durante il build (nessuna `execution`): le classi generate sono salvate in `src/main/java` e si
rigenerano solo a mano, seguendo la procedura qui sotto. In alcune classi ci sono modifiche fatte a mano: prima di
rigenerare va controllato dove stanno.

## Configurazione

- `pluginManagement` di questo pom: `org.jvnet.jaxb:jaxb-maven-plugin` (versione `${jaxb-tools-version}`) con
  `jaxb-plugins` e `jaxb-plugin-annotate`; argomenti comuni `-XtoString`, `-Xsetters`, `-Xwildcard`, `-Xinject-code`,
  `-Xinheritance`, `-Xannotate`. I moduli aggiungono i propri con `<args combine.children="append">`.
- I moduli dipendono da `jakarta.xml.bind-api`, `org.glassfish.jaxb:jaxb-runtime` e `org.jvnet.jaxb:jaxb-plugins-runtime`.
- I `.xjb` usano il namespace `https://jakarta.ee/xml/ns/jaxb` con `version="3.0"`. I vecchi namespace
  `http://jaxb2-commons.dev.java.net/basic/...` di jaxb2-basics sono ancora accettati da `jaxb-plugins`.
- `geoplatform-xml-context-provider` e le classi `*ContextServiceProvider*` (e `WMCServiceProviderV110`) sono scritte a
  mano, in file separati: la generazione non le tocca.

## Dove stanno le modifiche fatte a mano

### Scritte nei `.xjb`: una rigenerazione le riproduce

| Modulo | Modifica | Come è scritta nel `.xjb` |
|---|---|---|
| `gml-2.1.2` | `@XmlRootElement` su `Box`, `Point`, `LineString`, `LinearRing`, `Polygon`, `MultiPoint`, `MultiLineString`, `MultiPolygon`, `MultiGeometry` | `annox:annotateClass` |
| `wmc-1.1.0` | `@XmlRootElement` su `Extension`, `FormatList`, `General`, `LayerList`, `Layer`, `Server`, `StyleList`, `ViewContextType` | `annox:annotateClass` |
| `sld-1.0.0` | `@XmlType(name = "NamedLayer")` e `@XmlType(name = "UserStyle")` al posto del tipo anonimo | `annox:annotateClass`, che sostituisce l'`@XmlType` generato |
| `feature-catalogue` | `@XmlRootElement` su `FC_FeatureCatalogue`, prefisso `gfc` | `annox:annotateClass`, `namespace:prefix` |
| `wms-1.3.0`, `ows-1.0.0`, `iso19139-v20070417` | prefissi `xmlns` nei `package-info` (`wms`, `ows`, `gco`, `gmd`, `gmx`, `gsr`, `gss`, `gts`) | `namespace:prefix` |
| `gml-3.2.0` | `equals`, `hashCode`, `copyTo`, `mergeFrom` | argomenti `-Xequals`, `-XhashCode`, `-Xcopyable`, `-Xmergeable` nel pom del modulo |

`-Xnamespace-prefix` si attiva solo nei moduli che lo usano: senza personalizzazioni aggiunge un prefisso vuoto a ogni
namespace. `-XremoveAnnotation` non funziona insieme a `-Xannotate`; per sostituire un'annotazione basta `annotateClass`.

### Solo nelle classi: una rigenerazione le perde

Questi moduli hanno modifiche che non si possono scrivere (o non sono ancora scritte) nei `.xjb`. Le loro classi sono
state convertite a jakarta senza rigenerarle; prima di rigenerarli le modifiche vanno riportate nei `.xjb` o rifatte a mano:

| Modulo | Modifiche |
|---|---|
| `gml-3.1.1` | 42 classi implementano le interfacce di `gml-api`, metodi aggiunti (`getAbstractGeometry`, `addPointMember`, `setPosList`...), 13 `@XmlRootElement`, package SMIL rinominato in `org.geosdi.geoplatform.xml.gml.w3._2001.smil20` |
| `filter-1.1.0` | `@XmlRootElement` su `Filter`, prefisso `ogc`, costruttori di `GmlObjectIdType`, default e setter di `escapeChar` |
| `wfs-1.1.0` | `@XmlRootElement` su 8 classi, costruttori e getter `final` di `BaseRequestType`, `@XmlType` di `DescribeFeatureType`, campo `Value` di `PropertyType` con `PropertyValueType` (scritta a mano) |
| `w3c-xsd` | metodi `getTopLevelElement`, `getTopLevelComplexType`, `getElements`; prefisso `xsd` |
| `csw` | `@XmlRootElement` su 5 classi, costruttori, `fromValue` con `equalsIgnoreCase` in 2 enum, `@XmlElementRefs` di `GetRecordsType`, prefisso `csw` |
| `ebrim` | le classi stanno in package (`xml.ebrim._3`, `xml.wrs._1`) diversi da quelli del `.xjb` |

## Come rigenerare un modulo

1. Generare il modulo in una cartella fuori da `src` e confrontarlo con `src/main/java`, ignorando formattazione e import:
   le differenze che non vengono dalla versione del generatore sono modifiche fatte a mano, da scrivere nel `.xjb`.
2. Generare un modulo alla volta, nell'ordine delle dipendenze, con i moduli da cui dipende nello stesso reactor (gli
   episodi devono essere quelli dei moduli nel reactor):

   ```
   mvn -pl <dipendenze>,<modulo> clean process-resources \
       org.jvnet.jaxb:jaxb-maven-plugin:4.0.16:generate -Dmaven.xjc2.generateDirectory=target/gen-jakarta
   ```
3. Confrontare `target/gen-jakarta` con `src/main/java`: devono restare solo differenze del generatore (ordine di
   `@XmlSeeAlso` e dell'`ObjectFactory`, `new ArrayList<>()`, `@XmlSchemaType` in più).
4. Copiare le classi in `src/main/java` aggiungendo l'intestazione della licenza (`license-maven-plugin`) e copiare
   `META-INF/sun-jaxb.episode` (dove il modulo lo ha). Non copiare l'`ObjectFactory` dei package da cui il modulo dipende,
   che la generazione produce per via degli episodi.
5. Lanciare i test di tutti i moduli che dipendono dal binding nello stesso reactor (`-pl <modulo> -amd`), mai con `-rf`:
   altrimenti Maven prende da `~/.m2` le versioni installate prima.

## Cose da sapere sul codice che usa i binding

- Il codice che crea da solo un `JAXBContext` sulle classi dei binding compila anche con il package sbagliato e fallisce
  solo quando gira: va controllato a mano.
- I test di `geoplatform-wms-connector` vanno lanciati con `-Dsurefire.timeout=300`: un test multithread resta in attesa
  per sempre se i thread falliscono.
