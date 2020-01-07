package org.geosdi.geoplatform.rs.support.compression.provider;

import org.geosdi.geoplatform.rs.support.compression.annotation.GPCompress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;
import java.util.zip.GZIPOutputStream;

import static java.lang.Boolean.FALSE;
import static java.util.Arrays.stream;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Provider
@GPCompress
public class GPGZIPWriterInterceptor implements WriterInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(GPGZIPWriterInterceptor.class);

    /**
     * Interceptor method wrapping calls to {@link javax.ws.rs.ext.MessageBodyWriter#writeTo} method.
     * The parameters of the wrapped method called are available from {@code context}.
     * Implementations of this method SHOULD explicitly call
     * {@link WriterInterceptorContext#proceed} to invoke the next interceptor in the chain,
     * and ultimately the wrapped {@code MessageBodyWriter.writeTo} method.
     *
     * @param context invocation context.
     * @throws IOException             if an IO error arises or is thrown by the wrapped
     *                                 {@code MessageBodyWriter.writeTo} method.
     * @throws WebApplicationException thrown by the wrapped {@code MessageBodyWriter.writeTo} method.
     */
    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
        Optional<GPCompress> value = stream(context.getAnnotations())
                .filter(a -> (a instanceof GPCompress))
                .map(a -> (GPCompress) a)
                .findFirst();
        int threshold = ((value != null) && !(value.equals(Optional.empty())) ? value.get().thrushold() : 512);
        boolean syncFlush = ((value != null) && !(value.equals(Optional.empty())) ? value.get().syncFlush() : FALSE);
        logger.trace("############################Threshold : {} - SyncFlush : {}\n", threshold, syncFlush);
        MultivaluedMap<String, Object> headers = context.getHeaders();
        headers.add("Content-Encoding", "gzip");
        OutputStream outputStream = context.getOutputStream();
        context.setOutputStream(new GZIPOutputStream(outputStream, threshold));
        context.proceed();
    }
}