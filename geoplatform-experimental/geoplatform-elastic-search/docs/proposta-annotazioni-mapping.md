# Proposta: annotazioni per il mapping dei documenti

Stato: **proposta da decidere** (2026-11-12). Le scelte aperte sono nell'ultima sezione.

## Obiettivo

Oggi il mapping di un indice si scrive a mano nel DAO, in `preparePutMapping()`: un `XContentBuilder` per
ElasticSearch 7, un `TypeMapping` (anche da JSON) per ElasticSearch 9. È facile che il mapping e i documenti non
coincidano, per esempio dopo una `@JsonProperty` o con il nome radice.

La proposta: annotazioni sui campi dei documenti, lette una volta in un **metamodello** del documento (pattern
**Composite**), da cui un **Visitor** costruisce il mapping per la versione di ElasticSearch che serve. Le stesse
annotazioni valgono per la 7 e per la 9: per una versione nuova basta un Visitor nuovo.

## Dove stanno

| Cosa | Modulo | Package |
|---|---|---|
| Annotazioni | `geoplatform-elastic-search-commons` | `org.geosdi.geoplatform.experimental.el.mapping.annotation` |
| Metamodello e lettura | `geoplatform-elastic-search-commons` | `org.geosdi.geoplatform.experimental.el.mapping.model`, `...mapping.reader` |
| Visitor in JSON, per log e test | `geoplatform-elastic-search-commons` | `org.geosdi.geoplatform.experimental.el.mapping.visitor` |
| Visitor per `TypeMapping` | `geoplatform-elastic-search-9-rs-connector` | `org.geosdi.geoplatform.experimental.el9.rest.mapping` |
| Visitor per `XContentBuilder` (se lo vogliamo) | `geoplatform-elastic-search-rs-connector` | `org.geosdi.geoplatform.experimental.el.rest.mapping` |

Niente in `commons` dipende da ElasticSearch.

## Come si leggono le annotazioni

- **Spring core come libreria, senza contesto**: `MergedAnnotations` e `ReflectionUtils`. Danno le annotazioni
  composte (`@GPKeyword` meta-annotata con `@GPElasticSearchField(type = KEYWORD)`, con `@AliasFor`) e quelle
  ereditate da superclassi e interfacce. `spring-core` è già una dipendenza di `commons`
  (`GPBaseMapper` usa `AnnotationUtils`). Non servono bean né scan: è una funzione da una `Class` al metamodello.
- **I nomi dei campi li dà Jackson**, con il `JsonMapper` del DAO (`geoplatform-jackson-support`): valgono
  `@JsonProperty`, `@JsonIgnore`, la naming strategy e il nome radice. Così il mapping usa esattamente i nomi del JSON
  salvato. Le nostre annotazioni si cercano sul campo o sul getter di ogni proprietà trovata da Jackson.
- Il metamodello di ogni classe si legge una volta e resta in cache.

## Annotazioni

Valgono sul campo o sul getter (`@Target({FIELD, METHOD, ANNOTATION_TYPE})`, `@Retention(RUNTIME)`).

### Sul documento

```java
@GPElasticSearchMapping(dynamic = GPDynamic.STRICT, strings = GPStringMapping.TEXT_WITH_KEYWORD)
```

| Attributo | Default | Significato |
|---|---|---|
| `dynamic` | `TRUE` | cosa fa ElasticSearch con i campi non previsti: `TRUE`, `FALSE`, `STRICT` (errore), `RUNTIME` |
| `strings` | `TEXT_WITH_KEYWORD` | come mappare le `String` senza annotazione: `TEXT_WITH_KEYWORD`, `KEYWORD` o `TEXT` |
| `inferTypes` | `true` | se `false`, ogni campo deve essere annotato, altrimenti la lettura dà errore |

Il nome dell'indice resta in `@GPElasticSearchIndex`, che c'è già.

### Annotazione base

```java
@GPElasticSearchField(type = GPFieldType.KEYWORD, ignoreAbove = 256)
```

| Attributo | Default | Mapping |
|---|---|---|
| `type` | `AUTO` (ricavato dal tipo Java) | `type` |
| `index` | `true` | `index` |
| `docValues` | `true` | `doc_values` |
| `store` | `false` | `store` |
| `analyzer`, `searchAnalyzer` | `""` | `analyzer`, `search_analyzer` (solo `text`) |
| `normalizer` | `""` | `normalizer` (solo `keyword`) |
| `format` | `""` | `format` (solo date) |
| `ignoreAbove` | `-1` (assente) | `ignore_above` (solo `keyword`) |
| `scalingFactor` | `-1` | `scaling_factor` (solo `scaled_float`) |
| `nullValue` | `""` | `null_value` |
| `copyTo` | `{}` | `copy_to` |
| `fields` | `{}` | sottocampi, con `@GPSubField` |

`GPFieldType`: `AUTO, KEYWORD, TEXT, LONG, INTEGER, SHORT, BYTE, DOUBLE, FLOAT, HALF_FLOAT, SCALED_FLOAT, BOOLEAN, DATE,
DATE_NANOS, GEO_POINT, GEO_SHAPE, IP, BINARY, OBJECT, NESTED, FLATTENED`.

```java
@GPSubField(name = "keyword", type = GPFieldType.KEYWORD, ignoreAbove = 256)
```

`@GPSubField` ha `name`, `type`, `analyzer`, `normalizer` e `ignoreAbove`.

### Annotazioni comode, composte dalla base

| Annotazione | Equivale a |
|---|---|
| `@GPKeyword(ignoreAbove, normalizer)` | `type = KEYWORD` |
| `@GPText(analyzer, searchAnalyzer, keyword = true)` | `type = TEXT`; con `keyword = true` aggiunge il sottocampo `keyword` |
| `@GPDate(format)` | `type = DATE` |
| `@GPGeoPoint` | `type = GEO_POINT` |
| `@GPGeoShape` | `type = GEO_SHAPE` |
| `@GPNested(includeInParent = false)` | `type = NESTED`: per le liste di oggetti di cui vanno conservate le coppie di valori |
| `@GPObject(enabled = true, dynamic)` | `type = OBJECT`; con `enabled = false` l'oggetto si salva ma non si indicizza e la visita si ferma lì |
| `@GPFlattened` | `type = FLATTENED`: per le `Map` con chiavi libere |

Per escludere un campo dal mapping c'è `@GPMappingIgnore`. Un campo con `@JsonIgnore` è già escluso, perché
l'elenco dei campi lo dà Jackson.

## Regole di lettura

1. **Nomi**: quelli del JSON scritto da Jackson. Con il wrap del nome radice, tutto il mapping va dentro l'oggetto
   radice (per esempio `place`). L'`id` di `GPElasticSearchDocument` è sempre `keyword`.
2. **Tipo ricavato**, quando non c'è annotazione:
   - `String`: secondo `strings` (default `text` con il sottocampo `keyword`);
   - `long`/`Long` → `long`, `int`/`Integer` → `integer`, `short` → `short`, `byte` → `byte`;
   - `double` → `double`, `float` → `float`, `BigDecimal` → `double`;
   - `boolean`/`Boolean` → `boolean`;
   - `Instant`, `ZonedDateTime`, `OffsetDateTime`, `LocalDate`, `LocalDateTime`, `Date` → `date`;
   - enum → `keyword`;
   - `byte[]` → `binary`;
   - un POJO → `object`, visitato ricorsivamente.

   I tipi di JTS (`Point`, `Geometry`) richiedono `@GPGeoPoint` o `@GPGeoShape`, perché dipende da come li serializza
   il mapper.
3. **Collezioni** (`List`, `Set`, `Collection`, array, `Optional`). In ElasticSearch non esiste un tipo "array":
   qualunque campo può avere uno o più valori, quindi si mappa l'**elemento**.
   - Il tipo dell'elemento si legge dal `JavaType` di Jackson, anche nei generics delle superclassi.
   - Gli array di array si appiattiscono sull'elemento più interno.
   - Un'annotazione sul campo vale per l'elemento: `@GPNested List<Contatto>`, `@GPKeyword Set<String>`.
   - Le collezioni dentro oggetti dentro collezioni seguono la stessa ricorsione degli oggetti annidati: nel
     metamodello la collezione non è un nodo in più, il campo prende il nodo del suo elemento.
   - Un raw type o `List<?>` dà errore: va annotato il campo.
4. **`object` o `nested`** per le liste di oggetti. Con `object` (il default, come ElasticSearch) gli elementi si
   appiattiscono:
   `[{"tipo": "email", "valore": "a@b.it"}, {"tipo": "pec", "valore": "c@d.it"}]` diventa
   `tipo = [email, pec]`, `valore = [a@b.it, c@d.it]`, e una ricerca "tipo = pec e valore = a@b.it" trova il documento
   anche se nessun contatto ha quella coppia. Con `@GPNested` ogni elemento resta un documento a sé e le coppie si
   conservano; costa di più e va interrogato con la query `nested`.
5. **`Map<String, X>`**: senza annotazione è un `object` senza campi fissi, che ElasticSearch mappa dai dati; con
   `@GPFlattened` è `flattened`. Le chiavi devono essere stringhe, altrimenti la lettura dà errore.
6. **Tipi che contengono se stessi** (`Nodo` con `List<Nodo> figli`): la lettura dà un errore che indica il campo. Si
   risolve con `@GPObject(enabled = false)`, `@GPFlattened` o `@GPMappingIgnore` su quel campo.
7. **Polimorfismo** (`@JsonTypeInfo`/`@JsonSubTypes`): si uniscono i campi di tutti i sottotipi che Jackson conosce,
   più il campo del tipo come `keyword`. Se due sottotipi danno allo stesso campo tipi diversi, la lettura dà errore.
8. **Controlli**: `analyzer` solo su `text`; `normalizer` e `ignoreAbove` solo su `keyword`; `format` solo sulle date;
   `@GPNested` solo su oggetti o collezioni di oggetti; `@GPGeoPoint` e `@GPGeoShape` non su numeri o booleani. L'errore
   dice la classe, il campo e la regola violata.

## Metamodello e Visitor

```java
// commons, package el.mapping.model
sealed interface GPMappingNode permits GPMappingObject, GPMappingField {
    String getName();
    <R> R accept(GPMappingVisitor<R> visitor);
}
// GPMappingObject: tipo OBJECT/NESTED, enabled, dynamic, figli (Composite)
// GPMappingField: tipo, attributi, sottocampi

// commons, package el.mapping.visitor
interface GPMappingVisitor<R> {
    R visitObject(GPMappingObject node);
    R visitField(GPMappingField node);
}

// commons, package el.mapping.reader: legge la classe una volta e tiene il risultato in cache
GPMappingObject GPMappingReader.read(Class<?> documentClass, JsonMapper mapper);
```

| Visitor | Modulo | Risultato |
|---|---|---|
| `GPJsonMappingVisitor` | `commons` | il mapping in JSON, per log e test |
| `GPTypeMappingVisitor` | 9 | `TypeMapping` |
| `GPXContentMappingVisitor` | 7, se lo vogliamo | `XContentBuilder` |

Nel DAO della 9, `preparePutMapping()` avrebbe un'implementazione di default: se il documento ha
`@GPElasticSearchMapping` il mapping si genera, altrimenti resta `null` come oggi. Chi lo scrive a mano continua a
farlo.

## Esempio

```java
@JsonRootName("place")
@GPElasticSearchIndex(name = "places")
@GPElasticSearchMapping(dynamic = GPDynamic.STRICT)
public class GPPlace extends GPElasticSearchDocument {

    @GPText(analyzer = "italian")          // text + sottocampo keyword
    private String name;
    @GPKeyword
    private String code;
    @GPText(keyword = false)
    private String description;
    private Long population;                // long, ricavato
    private Instant foundation;             // date, ricavato
    @GPGeoPoint
    private String location;
    @GPKeyword
    private Set<String> tags;               // keyword, per ogni elemento
    @GPNested
    private List<GPContact> contacts;       // nested: ogni contatto a sé
    private List<GPAddress> addresses;      // object, appiattito
    @GPFlattened
    private Map<String, String> metadata;
}
```

Mapping generato:

```json
{"dynamic": "strict", "properties": {"place": {"properties": {
  "id": {"type": "keyword"},
  "name": {"type": "text", "analyzer": "italian", "fields": {"keyword": {"type": "keyword", "ignore_above": 256}}},
  "code": {"type": "keyword"},
  "description": {"type": "text"},
  "population": {"type": "long"},
  "foundation": {"type": "date"},
  "location": {"type": "geo_point"},
  "tags": {"type": "keyword"},
  "contacts": {"type": "nested", "properties": {"type": {"...": "..."}, "value": {"...": "..."}}},
  "addresses": {"properties": {"street": {"...": "..."}, "city": {"...": "..."}}},
  "metadata": {"type": "flattened"}
}}}}
```

## Test previsti

- Il mapping generato per `GPPlace` uguale a quello scritto a mano oggi in `GPPlaceDAO`.
- `@JsonProperty`, `@JsonIgnore`, nome radice ed ereditarietà.
- Collezioni:
  - una lista di valori semplici;
  - una lista di oggetti come `object` e come `nested`;
  - una collezione dentro un oggetto dentro una collezione;
  - un array di array;
  - una `Map`.
- Errori: tipo che contiene se stesso, `List<?>`, annotazione incompatibile con il tipo, conflitto tra sottotipi.
- Con Testcontainers: l'indice creato dal mapping generato, e la ricerca che distingue `object` da `nested` (l'esempio
  dei contatti).

## Da decidere

1. **Le `String` senza annotazione**: `text` con il sottocampo `keyword` (come ElasticSearch) o solo `keyword`?
   Proposta: `text` con `keyword`, come default di `strings`.
2. **Il default di `dynamic`**: `TRUE` come ElasticSearch o `STRICT`, che blocca i campi non mappati?
3. **Il polimorfismo**: unire i sottotipi (regola 7) o dare errore e chiedere il mapping a mano?
4. **ElasticSearch 7**: scrivere anche il Visitor per `XContentBuilder`, così le annotazioni valgono per tutte e due?
5. **I nomi**: prefisso corto (`@GPKeyword`) o lungo (`@GPElasticSearchKeyword`)? Il corto si legge meglio sui campi,
   ma è generico.
6. **La query `nested`**: aggiungere `GPNestedQuerySearch` all'API della 9 nello stesso lavoro? Senza, i campi
   `@GPNested` non si possono cercare.
