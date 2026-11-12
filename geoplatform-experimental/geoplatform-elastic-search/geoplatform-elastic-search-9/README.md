# Connettore per ElasticSearch 9

Api e DAO per ElasticSearch 9 con l'[Elasticsearch Java API Client](https://www.elastic.co/docs/reference/elasticsearch/clients/java)
(9.5.6) e Jackson 3. I moduli per ElasticSearch 7 restano com'erano.

## Moduli

| Modulo | Contenuto |
|---|---|
| `geoplatform-elastic-search-commons` | Classi comuni a 7 e 9, senza ElasticSearch: documenti, mapper, store delle pagine, risultati. |
| `geoplatform-elastic-search-rs-commons` | Classi comuni ai connettori REST: impostazioni dell'indice, mapper REST, SSL, pool, jasypt. |
| `geoplatform-elastic-search-9-api` | Ricerche (`el9.search`) e pagine (`el9.dao`) costruite con i tipi del client 9. |
| `geoplatform-elastic-search-9-rs-connector` | Client Rest5 configurato da Spring e DAO (`el9.rest`). |

Le versioni stanno nel BOM `geoplatform-elasticsearch9-dependency` di `geoplatform-dependencies`, che esclude
`jackson-core` e `jackson-databind` 2: il client usa `Jackson3JsonpMapper`.

## Licenza di ElasticSearch

ElasticSearch 9 si usa senza chiave: la licenza Basic è gratuita e non scade. Il codice è sotto AGPLv3, SSPL o
Elastic License 2.0, a scelta; il client Java è Apache 2.0.

## Come si attiva

```java
@Configuration
@Import(GPElasticSearchRestModuleConfig.class)
class MyElasticSearchConfig {

    @Bean
    public GPElasticSearchRestPBEProperties elasticSearchRestPBEProperties() {
        return new GPElasticSearchRestPBEProperties("GP_ELASTICSEARCH_REST_PBE_KEY");
    }
}
```

Le proprietà sono le stesse della 7: `gp.elastic-search-rest.properties` nel classpath o nella cartella della system
property `GP_ELASTICSEARCH_REST_DATA_DIR`, con i valori anche cifrati con jasypt (`ENC(...)`).

```properties
gp.elasticsearch.rest.hosts=https://host1:9200;https://host2:9200
gp.elasticsearch.rest.username=elastic
gp.elasticsearch.rest.password=ENC(...)
# Truststore PKCS12 nel classpath o sul disco, per l'HTTPS con la CA di ElasticSearch
gp.elasticsearch.rest.key_store=/percorso/truststore.p12
gp.elasticsearch.rest.key_store.password=...
gp.elasticsearch.rest.connection.timeout=2
gp.elasticsearch.rest.socket.timeout=30
```

Un DAO estende `org.geosdi.geoplatform.experimental.el9.rest.api.dao.ElasticSearchRestDAO`, come nella 7, e
restituisce il mapping come `TypeMapping` (anche da JSON: `TypeMapping.of(m -> m.withJson(new StringReader(json)))`).
I documenti si scrivono e si leggono con il mapper di `geoplatform-jackson-support` passato al costruttore, nome
radice compreso: i documenti salvati dalla 7 si leggono uguali.

## Cosa cambia rispetto alla 7

| ElasticSearch 7 | ElasticSearch 9 |
|---|---|
| package `org.geosdi.geoplatform.experimental.el.search`, `el.dao`, `el.rest` | `el9.search`, `el9.dao`, `el9.rest` |
| `QueryBuilder` di ElasticSearch | `Query` del client |
| `Operator.AND`, `SortOrder.ASC` | `Operator.And`, `SortOrder.Asc` |
| `GPDateQuerySearch` con `org.joda.time.DateTime` | con `java.time.ZonedDateTime` |
| `GeoPoint` di ElasticSearch | `org.locationtech.jts.geom.Coordinate` (x longitudine, y latitudine) |
| `GPGeoShapeQuerySearch` con `GeoShapeQueryBuilder` | con una `Geometry` di JTS, mandata in WKT |
| query `geo_polygon` | tolta da ElasticSearch 8: `GPGeoPolygonQuerySearch` fa una `geo_shape` |
| `preparePutMapping()` restituisce `XContentBuilder` | restituisce `TypeMapping` |
| metodi asincroni con `ActionListener` e `Cancellable` | restituiscono `CompletableFuture` |
| `update(id, XContentBuilder)` | `update(id, Map)` con la struttura del documento |
| `find(page, AbstractAggregationBuilder)` | `find(page, Map<String, Aggregation>)` |
| scroll con `TimeValue` | scroll con `java.time.Duration` |
| `persist` con un id già usato sovrascriveva il documento e poi falliva | fallisce senza toccare il documento (`op_type=create`) |
| l'id scelto da ElasticSearch finiva nel campo `"radice.id"` fuori dal documento | finisce dentro l'oggetto radice |
| il DAO chiudeva il client condiviso in `destroy()` | i client si chiudono con il contesto di Spring |

## Test

I test del connettore avviano ElasticSearch 9.5.6 con Testcontainers: serve Docker. Provano l'HTTP con utente e
password cifrata con jasypt (`GPElasticSearchRestDAOTest`) e l'HTTPS con la CA generata da ElasticSearch
(`GPElasticSearchRestSslTest`).

```shell
mvn clean install -Dsurefire.timeout=300
mvn clean
```
