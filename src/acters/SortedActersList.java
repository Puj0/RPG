package acters;

import javax.inject.Inject;
import java.util.*;

public class SortedActersList {

    private int size = 0;

    private ActerWithInitiative[] array;

    @Inject
    public SortedActersList() {
        array = new ActerWithInitiative[]{};
    }

    public ActerWithInitiative[] getArray() {
        return Arrays.copyOfRange(array, 0, size);
    }

    public void addActer(ActerWithInitiative acter) {
        if (size == size()){
            expandCapacity();
        }

        size++;
        int index = size - 1;

        while(index > 0){
            if (array[index-1].getInitiative() > acter.getInitiative()){
                insertNewElement(index, acter);
                break;
            }

            if(array[index-1].getInitiative() < acter.getInitiative()){
                index--;
                continue;
            }

            if (array[index-1].getActer().getInitiative() >= acter.getActer().getInitiative()){
                insertNewElement(index,acter);
                break;
            }

            index--;
        }

        if (index == 0){
            insertNewElement(0,acter);
        }
    }

    private void insertNewElement(int index, ActerWithInitiative acter) {
        System.arraycopy(array, index, array, index+1, size-index-1);
        array[index] = acter;
    }

    private void expandCapacity() {
        int newSize = size() * 2 + 1;
        array = Arrays.copyOf(array, newSize);
    }

    private int size() {
        return array.length;
    }

    public void remove(Acter acter){
        for (int i = 0; i < size; i++) {
            if (array[i].getActer() == acter){
                remove(i);
                return;
            }
        }
    }

    private void remove(int index) {
        System.arraycopy(array, index + 1, array, index, size - index);
        size--;
    }
}
