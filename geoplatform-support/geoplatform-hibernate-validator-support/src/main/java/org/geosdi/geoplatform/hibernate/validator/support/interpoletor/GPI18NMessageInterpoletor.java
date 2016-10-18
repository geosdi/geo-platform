package org.geosdi.geoplatform.hibernate.validator.support.interpoletor;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.spi.resourceloading.ResourceBundleLocator;

import javax.el.ExpressionFactory;
import java.util.Locale;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPI18NMessageInterpoletor extends ResourceBundleMessageInterpolator
        implements IGPI18NMessageInterpolator {

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
    public GPI18NMessageInterpoletor(ResourceBundleLocator userResourceBundleLocator,
            ResourceBundleLocator contributorResourceBundleLocator) {
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
