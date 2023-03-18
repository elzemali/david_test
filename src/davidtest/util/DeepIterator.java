/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.util;

import davidtest.model.BasePerson;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author lenovo
 * @param <T>
 */
//ToDO enlenver deepIterator 
// Les algorithme de parcours 
public class DeepIterator<T extends BasePerson> implements Iterator<T > {

    private final List<T> mList;
    private int indicParent;
    private int indicChild;

    private boolean isChild;

    public DeepIterator(List<T> mList) {
        this.mList = mList;
        // this.mList = this.mList.stream().flatMap(x-> (x));
        indicParent = 0;
        isChild = false;
    }

    @Override
    public boolean hasNext() {
        return indicParent < mList.size();
    }

    @Override
    public T next() {

        if (!isChild) {
            T value = mList.get(indicParent);
            //if(value instanceof Person){}
            //TODO develop IDEA FOR all element
            indicParent++;
            return value;

        } else {
            T value = mList.get(indicChild);

            return value;

        }

        //return value;
    }

}
