package org.geosdi.geoplatform.connector.geoserver.coveragestores;

import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.FileEntity;
import org.geosdi.geoplatform.connector.geoserver.request.coveragestores.GeoserverUpdateCoverageStoreWithStoreNameRequest;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonPutConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.ThreadLocal.withInitial;
import static java.util.stream.IntStream.iterate;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GPGeoserverUpdateCoverageStoreWithStoreName extends GPJsonPutConnectorRequest<GPcoverageResponse, GeoserverUpdateCoverageStoreWithStoreNameRequest> implements GeoserverUpdateCoverageStoreWithStoreNameRequest {

    private final ThreadLocal<String> workspaceName;
    private final ThreadLocal<String> storeName;
    private final ThreadLocal<GPUploadMethod> methodName;
    private final ThreadLocal<GPCoverageStoreExtension> formatName;
    private final ThreadLocal<File> file;
    private final ThreadLocal<String> mimeType;
    private final ThreadLocal<TreeMap<String, String>> queryStringMap;

     GPGeoserverUpdateCoverageStoreWithStoreName(@Nonnull(when = NEVER) GPServerConnector theServerConnector) {
        super(theServerConnector, JACKSON_JAXB_XML_SUPPORT);
        this.workspaceName = withInitial(() -> null);
        this.storeName = withInitial(() -> null);
        this.methodName = withInitial(() -> null);
        this.formatName = withInitial(() -> null);
        this.file = withInitial(() -> null);
        this.mimeType = withInitial(() -> null);
         this.queryStringMap = withInitial(() -> new TreeMap<>());
     }

    /**
     * @param theWorkspace
     * @return {@link GeoserverUpdateCoverageStoreWithStoreNameRequest}
     */
    @Override
    public GeoserverUpdateCoverageStoreWithStoreNameRequest withWorkspace(@Nonnull(when = NEVER) String theWorkspace) {
        this.workspaceName.set(theWorkspace);
        this.queryStringMap.set(new TreeMap<>());
        return self();
    }

    /**
     * @param theStore
     * @return {@link GeoserverUpdateCoverageStoreWithStoreNameRequest}
     */
    @Override
    public GeoserverUpdateCoverageStoreWithStoreNameRequest withStore(@Nonnull(when = NEVER) String theStore) {
        this.storeName.set(theStore);
        return self();
    }

    /**
     * @param theMethod
     * @return {@link GeoserverUpdateCoverageStoreWithStoreNameRequest}
     */
    @Override
    public GeoserverUpdateCoverageStoreWithStoreNameRequest withMethod(@Nonnull(when = NEVER) GPUploadMethod theMethod) {
        this.methodName.set(theMethod);
        return self();
    }

    /**
     * @param theFormat
     * @return {@link GeoserverUpdateCoverageStoreWithStoreNameRequest}
     */
    @Override
    public GeoserverUpdateCoverageStoreWithStoreNameRequest withFormat(@Nonnull(when = NEVER) GPCoverageStoreExtension theFormat) {
        this.formatName.set(theFormat);
        return self();
    }

    /**
     * @param theParameterConfigure
     * @return {@link GeoserverUpdateCoverageStoreWithStoreNameRequest}
     */
    @Override
    public GeoserverUpdateCoverageStoreWithStoreNameRequest withConfigure(@Nonnull(when = NEVER) GPParameterConfigure theParameterConfigure) {
        this.queryStringMap.get().put("configure", theParameterConfigure.toString());
        return self();
    }

    /**
     * @param theParams
     * @return {@link GeoserverUpdateCoverageStoreWithStoreNameRequest}
     */
    @Override
    public GeoserverUpdateCoverageStoreWithStoreNameRequest withParams(@Nonnull(when = NEVER) NameValuePair... theParams) {
        this.queryStringMap.get().put("params", this.buildParams(theParams));
        return self();
    }

    /**
     * @param theFile
     * @return {@link GeoserverUpdateCoverageStoreWithStoreNameRequest}
     */
    @Override
    public GeoserverUpdateCoverageStoreWithStoreNameRequest withFile(@Nonnull(when = NEVER) File theFile) {
        this.file.set(theFile);
        return self();
    }

    /**
     * @param theFileName
     * @return {@link GeoserverUpdateCoverageStoreWithStoreNameRequest}
     */
    @Override
    public GeoserverUpdateCoverageStoreWithStoreNameRequest withFileName(@Nonnull(when = NEVER) String theFileName) {
        this.queryStringMap.get().put("filename", theFileName);
        return self();
    }

    /**
     * @param theCoverageName
     * @return
     */
    @Override
    public GeoserverUpdateCoverageStoreWithStoreNameRequest withCoverageName(@Nonnull(when = NEVER) String theCoverageName) {
        this.queryStringMap.get().put("coverageName", theCoverageName);
        return self();
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String workspace = this.workspaceName.get();
        checkArgument((workspace != null) && !(workspace.trim().isEmpty()), "The Parameter workspace must not be null or an empty string.");
        String store = this.storeName.get();
        checkArgument((store != null) && !(store.trim().isEmpty()), "The Parameter store must not be null or an empty string.");
        GPUploadMethod method = this.methodName.get();
        checkArgument((method != null), "The Parameter method must not be null or an empty string.");
        GPCoverageStoreExtension format = this.formatName.get();
        checkArgument((format != null), "The Parameter format must not be null or an empty string.");
        String baseURI = this.serverURI.toString();
        String path = ((baseURI.endsWith("/") ? baseURI.concat("workspaces/").concat(workspace).concat("/coveragestores/").concat(store).concat("/").concat(method.toString()).concat(".").concat(format.toString())
                : baseURI.concat("/workspaces/").concat(workspace).concat("/coveragestores/").concat(store).concat("/").concat(method.toString()).concat(".").concat(format.toString())));
        String queryString = this.queryStringMap.get().entrySet().stream().filter(Objects::nonNull)
                .map(e -> e.getKey().concat("=").concat(e.getValue()))
                .map(Object::toString)
                .collect(Collectors.joining("&"));
        return path.concat( queryString != null ? "?".concat(queryString) : "");
    }

    /**
     * @return {@link Class<Boolean>}
     */
    @Override
    protected Class<GPcoverageResponse> forClass() {
        return GPcoverageResponse.class;
    }

    /**
     * @param reader
     * @return {@link Boolean}
     * @throws Exception
     */
//    @Override
//    protected Boolean readInternal(BufferedReader reader) throws Exception {
//        String value = CharStreams.toString(reader);
//        return ((value != null) && (value.trim().isEmpty()) ? TRUE : FALSE);
//    }

    /**
     * @return {@link HttpEntity}
     */
    @Override
    protected HttpEntity prepareHttpEntity() throws Exception {
        GPUploadMethod method = this.methodName.get();
        if(method == GPUploadMethod.FILE){
            File fileToUpload = this.file.get();
            checkArgument(fileToUpload != null, "The Parameter file must not be null.");
            FileEntity builder = new FileEntity(fileToUpload, "image/geotiff");
            return builder;
        }
        return null;
    }

    /**
     * @param names
     * @return {@link String}
     */
    private String buildParams(NameValuePair[] names) {
        StringBuilder sbUrl = new StringBuilder();
        int index = iterate(0, n -> n + 1)
                .limit(names.length )
                .boxed()
                .filter(i -> {
                    String name = names[i].getName();
                    String value = names[i].getValue();
                    return name != null && !name.isEmpty() && value != null && !value.isEmpty();
                })
                .findFirst().orElse(null);

        sbUrl.append(names[index].getName()).append("=").append(names[index].getValue());
        Arrays.asList(names).subList(index + 1, names.length).forEach(nameValuePair -> {
            sbUrl.append(nameValuePair.getName()).append("=").append(nameValuePair.getValue());
            if (nameValuePair.getName() != null && !nameValuePair.getName().isEmpty() && nameValuePair.getValue() != null && !nameValuePair.getValue().isEmpty()) {
                sbUrl.append("&").append(nameValuePair.getName()).append("=").append(nameValuePair.getValue());
            }
        });
        return sbUrl.toString();
    }

}
