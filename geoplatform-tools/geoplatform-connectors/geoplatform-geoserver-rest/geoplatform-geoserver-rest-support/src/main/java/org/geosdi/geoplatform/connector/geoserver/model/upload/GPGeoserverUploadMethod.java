package org.geosdi.geoplatform.connector.geoserver.model.upload;

import com.google.common.base.Preconditions;
import org.apache.http.entity.ContentType;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public enum GPGeoserverUploadMethod implements GeoserverUploadMethod {

    FILE(ContentType.create("application/zip")),
    URL(ContentType.create("text/plain")),
    EXTERNAL(URL.contentType);

    private final ContentType contentType;

    /**
     * @param theContentType
     */
    GPGeoserverUploadMethod(@Nonnull(when = When.NEVER) ContentType theContentType) {
        Preconditions.checkArgument((theContentType != null), "The Parameter contentType must not be null.");
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