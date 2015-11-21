package org.geosdi.geoplatform.support.async.spring.task.notification;

import com.google.common.collect.ImmutableMap;
import org.geosdi.geoplatform.support.async.spring.task.GPAsyncTask;

/**
 * <p>Basic Interface for Notification Tasks to send :
 * <ul>
 * <li><strong>Emails.</strong></li>
 * <li><strong>Tweets.</strong></li>
 * <li><strong>XMPP Messages.</strong></li>
 * </ul>
 * </p>
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPAsyncNotificationTask<Message extends Object, S extends GPAsyncNotificationTask.GPNotificationMessage, R extends Object> extends GPAsyncTask<S, R> {

    /**
     * @param theNotificationMessage
     * @return {@link Message}
     * @throws Exception
     */
    Message prepareMessage(S theNotificationMessage) throws Exception;

    /**
     * <p>Notification Message Interface</p>
     */
    interface GPNotificationMessage {

        /**
         * @param <K>
         * @param <V>
         * @return {@link ImmutableMap<K, V>}
         */
        <K extends String, V extends Object> ImmutableMap<K, V> getMessageParameters();
    }
}
