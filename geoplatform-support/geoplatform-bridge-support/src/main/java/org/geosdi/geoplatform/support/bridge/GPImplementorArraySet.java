package org.geosdi.geoplatform.support.bridge;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.geosdi.geoplatform.support.bridge.implementor.GPImplementor;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPImplementorArraySet<I extends GPImplementor<?>> extends AbstractSet<I>
        implements Serializable {

    private static final long serialVersionUID = -6200125483133700829L;
    //
    private final ArrayList<I> list = Lists.newArrayList();
    private final Iterator<? extends I> iterator;

    public GPImplementorArraySet(Iterator<? extends I> theIterator) {
        Preconditions.checkArgument(theIterator != null, "The Parameter iterator must not be null.");
        this.iterator = theIterator;
        this.iterator.forEachRemaining(this.list::add);
    }

    /**
     * @return {@link Iterator<I>}
     */
    @Override
    public Iterator<I> iterator() {
        return list.iterator();
    }

    /**
     * @return int
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * @param element
     * @return boolean
     */
    @Override
    public boolean add(I element) {
        boolean modified;
        if (modified = !list.contains(element)) {
            list.add(element);
        }
        return modified;
    }

    /**
     * @param element
     * @return boolean
     */
    @Override
    public boolean remove(Object element) {
        return list.remove(element);
    }

    /**
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * @param element
     * @return boolean
     */
    @Override
    public boolean contains(Object element) {
        return list.contains(element);
    }

    @Override
    public void clear() {
        list.clear();
    }
}
