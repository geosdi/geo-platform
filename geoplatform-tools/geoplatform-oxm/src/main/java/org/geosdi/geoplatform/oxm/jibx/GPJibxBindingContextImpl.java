package org.geosdi.geoplatform.oxm.jibx;

import org.jibx.runtime.*;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPJibxBindingContextImpl implements GPJibxBindingContext {

    private final IBindingFactory bindingFactory;
    private final Class<?> targetClass;

    /**
     * @param theTargetClass
     * @throws Exception
     */
    public GPJibxBindingContextImpl(@Nonnull(when = NEVER) Class<?> theTargetClass) {
        checkNotNull(theTargetClass != null, "The Parameter targetClass must not be null.");
        this.targetClass = theTargetClass;
        try {
            this.bindingFactory = BindingDirectory.getFactory(this.targetClass);
        } catch (JiBXException ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link IMarshallingContext}
     */
    @Override
    public IMarshallingContext createMarshallingContext() throws JiBXException {
        return this.bindingFactory.createMarshallingContext();
    }

    /**
     * @return {@link IUnmarshallingContext}
     */
    @Override
    public IUnmarshallingContext createUnmarshallingContext() throws JiBXException {
        return this.bindingFactory.createUnmarshallingContext();
    }
}