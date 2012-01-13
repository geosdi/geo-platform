/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.gui.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group @email
 * nazzareno.sileno@geosdi.org
 */
public class ExtendedJettyClassLoader extends URLClassLoader {

    private URLClassLoader delegate;
    private boolean extend;

    /**
     * Constructs an extended class loader which delegates to the specified
     * class loader and uses the specified parent class loader.
     *
     * @param delegate class loader to delegate to
     * @param parent the designated parent of this class loader
     * @param extend should be true if the scanning methods have to be delegated
     * to the superclass
     */
    public ExtendedJettyClassLoader(URLClassLoader delegate, ClassLoader parent, boolean extend) {
        super(delegate.getURLs(), parent);
        this.delegate = delegate;
        this.extend = extend;
    }

    public void clearAssertionStatus() {
        delegate.clearAssertionStatus();
    }

    public URL getResource(String name) {
        return delegate.getResource(name);
    }

    public InputStream getResourceAsStream(String name) {
        return delegate.getResourceAsStream(name);
    }

    public Enumeration<URL> getResources(String name) throws IOException {
        if (extend) {
            return super.getResources(name);
        } else {
            return delegate.getResources(name);
        }
    }

    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return delegate.loadClass(name);
    }

    public void setClassAssertionStatus(String className, boolean enabled) {
        delegate.setClassAssertionStatus(className, enabled);
    }

    public void setDefaultAssertionStatus(boolean enabled) {
        delegate.setDefaultAssertionStatus(enabled);
    }

    public void setPackageAssertionStatus(String packageName, boolean enabled) {
        delegate.setPackageAssertionStatus(packageName, enabled);
    }

    public URL findResource(String name) {
        return delegate.findResource(name);
    }

    public Enumeration<URL> findResources(String name) throws IOException {
        if (extend) {
            return super.findResources(name);
        } else {
            return delegate.findResources(name);
        }
    }

    public URL[] getURLs() {
        return delegate.getURLs();
    }

    public static boolean isGwtJettyClassLoader(ClassLoader cl) {
        ClassLoader sys = ClassLoader.getSystemClassLoader();
        // move up until we find the system class loader or null
        while (cl != sys && cl != null) { // NOSONAR really is object equality
            cl = cl.getParent();
        }
        // found null, must be gwt classloader !
        return cl == null;
    }
}
