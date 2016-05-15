package org.geosdi.geoplatform.support.jackson.jts.bridge;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class ImplementorArraySet<I> extends AbstractSet<I> implements
        Serializable {

    private static final long serialVersionUID = 2250017711118796696L;
    //
    private final ArrayList<I> list = new ArrayList<>();
    private final Iterator<? extends I> iterator;

    public ImplementorArraySet(Iterator<? extends I> iterator) {
        this.iterator = iterator;

        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
    }

    @Override
    public Iterator<I> iterator() {
        return list.iterator();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean add(I element) {
        boolean modified;
        if (modified = !list.contains(element)) {
            list.add(element);
        }
        return modified;
    }

    @Override
    public boolean remove(Object element) {
        return list.remove(element);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object element) {
        return list.contains(element);
    }

    @Override
    public void clear() {
        list.clear();
    }
}