package org.geosdi.geoplatform.connector.server.request.v110.query.factory;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class QueryRestrictionsSet<E> extends AbstractSet<E> implements Serializable {

    private static final long serialVersionUID = 5357217240056652258L;
    //
    private final ArrayList<E> list = new ArrayList<E>();
    private final Iterator<? extends E> iterator;

    public QueryRestrictionsSet(Iterator<? extends E> iterator) {
        this.iterator = iterator;

        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean add(E element) {
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
