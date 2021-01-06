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
package org.geosdi.geoplatform.hibernate.validator.support.interpoletor;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.spi.resourceloading.ResourceBundleLocator;

import javax.el.ExpressionFactory;
import java.util.Locale;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPI18NMessageInterpoletor extends ResourceBundleMessageInterpolator implements IGPI18NMessageInterpolator {

    private ThreadLocal<Locale> defaultLocale = ThreadLocal.withInitial(() -> Locale.ENGLISH);

    public GPI18NMessageInterpoletor() {
    }

    /**
     * @param userResourceBundleLocator
     */
    public GPI18NMessageInterpoletor(ResourceBundleLocator userResourceBundleLocator) {
        super(userResourceBundleLocator);
    }

    /**
     * @param userResourceBundleLocator
     * @param contributorResourceBundleLocator
     */
    public GPI18NMessageInterpoletor(ResourceBundleLocator userResourceBundleLocator, ResourceBundleLocator contributorResourceBundleLocator) {
        super(userResourceBundleLocator, contributorResourceBundleLocator);
    }

    /**
     * @param userResourceBundleLocator
     * @param contributorResourceBundleLocator
     * @param cachingEnabled
     */
    public GPI18NMessageInterpoletor(ResourceBundleLocator userResourceBundleLocator,
            ResourceBundleLocator contributorResourceBundleLocator, boolean cachingEnabled) {
        super(userResourceBundleLocator, contributorResourceBundleLocator, cachingEnabled);
    }

    /**
     * @param userResourceBundleLocator
     * @param cachingEnabled
     */
    public GPI18NMessageInterpoletor(ResourceBundleLocator userResourceBundleLocator,
            boolean cachingEnabled) {
        super(userResourceBundleLocator, cachingEnabled);
    }

    /**
     * @param userResourceBundleLocator
     * @param cachingEnabled
     * @param expressionFactory
     */
    public GPI18NMessageInterpoletor(ResourceBundleLocator userResourceBundleLocator, boolean cachingEnabled,
            ExpressionFactory expressionFactory) {
        super(userResourceBundleLocator, cachingEnabled, expressionFactory);
    }

    /**
     * Interpolates the message template based on the constraint validation context.
     * <p/>
     * The locale is defaulted according to the {@code MessageInterpolator}
     * implementation. See the implementation documentation for more detail.
     *
     * @param messageTemplate the message to interpolate
     * @param context         contextual information related to the interpolation
     * @return interpolated error message
     */
    @Override
    public String interpolate(String messageTemplate, Context context) {
        return interpolate(messageTemplate, context, this.defaultLocale.get());
    }

    /**
     * Interpolates the message template based on the constraint validation context.
     * The {@code Locale} used is provided as a parameter.
     *
     * @param messageTemplate the message to interpolate
     * @param context         contextual information related to the interpolation
     * @param locale          the locale targeted for the message
     * @return interpolated error message
     */
    @Override
    public String interpolate(String messageTemplate, Context context, Locale locale) {
        return super.interpolate(messageTemplate, context, locale);
    }

    /**
     * @param theDefaultLocale
     */
    @Override
    public void setDefaultLocale(Locale theDefaultLocale) {
        this.defaultLocale.set((theDefaultLocale != null) ? theDefaultLocale : Locale.ENGLISH);
    }
}
