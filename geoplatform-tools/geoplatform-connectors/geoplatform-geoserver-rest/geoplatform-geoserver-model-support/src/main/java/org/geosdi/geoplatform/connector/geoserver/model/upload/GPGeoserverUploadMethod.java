package org.geosdi.geoplatform.connector.geoserver.model.upload;

import org.apache.hc.core5.http.ContentType;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.apache.hc.core5.http.ContentType.create;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public enum GPGeoserverUploadMethod implements GeoserverUploadMethod {

    FILE(create("application/zip")),
    URL(create("text/plain")),
    EXTERNAL(URL.contentType);

    private final ContentType contentType;

    /**
     * @param theContentType
     */
    GPGeoserverUploadMethod(@Nonnull(when = NEVER) ContentType theContentType) {
        checkArgument((theContentType != null), "The Parameter contentType must not be null.");
        this.contentType = theContentType;
    }

    /**
     * @return {@link ContentType}
     */
    @Override
    public ContentType toContentType() {
        return this.contentType;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}