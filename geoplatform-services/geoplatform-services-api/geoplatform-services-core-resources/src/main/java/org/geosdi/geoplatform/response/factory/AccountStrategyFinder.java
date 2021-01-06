/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.response.factory;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ServiceLoader;
import java.util.Set;
import net.jcip.annotations.ThreadSafe;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public final class AccountStrategyFinder {

    private static final ServiceLoader<AccountDTOStrategy> loader = ServiceLoader.<AccountDTOStrategy>load(
            AccountDTOStrategy.class);

    private AccountStrategyFinder() {
    }

    private static synchronized <S extends AccountDTOStrategy> Set<S> getStrategies(
            final Class<S> type, final boolean all) {
        final Iterator<AccountDTOStrategy> strategies = loader.iterator();

        return new StrategyArraySet<S>(new Iterator<S>() {

            private S next;

            @Override
            public boolean hasNext() {
                if (next != null) {
                    return true;
                }
                synchronized (AccountStrategyFinder.class) {
                    while (strategies.hasNext()) {
                        final AccountDTOStrategy strategy = strategies.next();
                        if ((type == null) || (type.isInstance(strategy))) {
                            if (all || strategy.isValid()) {
                                next = (S) strategy;
                                return true;
                            }
                        }
                    }
                }
                return false;
            }

            @Override
            public S next() {
                if (hasNext()) {
                    final S s = next;
                    next = null;
                    return s;
                }
                throw new NoSuchElementException("No more elements");
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException(
                        "Cannot remove elements "
                        + "from this Iterator");
            }
        });
    }

    public static <S extends AccountDTOStrategy> Set<S> getAllStrategies(
            Class<S> type) {
        return getStrategies(type, true);
    }

    public static <S extends AccountDTOStrategy> Set<S> getValidStrategies(
            Class<S> type) {
        return getStrategies(type, false);
    }

    public static synchronized void reload() {
        loader.reload();
    }

}
