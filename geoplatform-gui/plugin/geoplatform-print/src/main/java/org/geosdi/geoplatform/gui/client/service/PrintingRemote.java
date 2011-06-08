/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;

/**
 *
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @mail francesco.izzi@geosdi.org
 */
@RemoteServiceRelativePath("PrintingRemote")
public interface PrintingRemote extends RemoteService {

    public static class Util {

        private static PrintingRemoteAsync instance;

        public static PrintingRemoteAsync getInstance() {
            if (instance == null) {
                instance = (PrintingRemoteAsync) GWT.create(PrintingRemote.class);
            }
            return instance;
        }
    }

    public boolean print()
            throws GeoPlatformException;
}
