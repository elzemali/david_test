/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.util;

import davidtest.model.BasePerson;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author lenovo
 * @param <T>
 */

public class BaseParentIterator <T extends BasePerson> implements Iterator<T> {
    private final List<T> mList;
    private int indice = 0;
    
    public BaseParentIterator(List<T> mList) {
        this.mList = mList;
    
    }

    @Override
    public boolean hasNext() {
        return indice < mList.size();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        T element = mList.get(indice);  
        
        indice++;
        return element;
    }
}
