package acters;

import java.util.*;

public class SortedActersList {

    private int size = 0;

    private ActerWithInitiative[] array;

    public SortedActersList() {
        array = new ActerWithInitiative[]{};
    }

    public SortedActersList(ActerWithInitiative[] array) {
        this.array = array;
    }

    public ActerWithInitiative[] getArray() {
        return Arrays.copyOfRange(array, 0, size);
    }

    public void add(ActerWithInitiative t) {
        if (size == size()){
            ensureCapacity();
        }
        array[size++] = t;
        for(int i = size - 1; i > 0; i--){
            if(array[i].getInitiative() > array[i-1].getInitiative()){
                ActerWithInitiative temp = array[i-1];
                array[i-1] = array[i];
                array[i] = temp;
            }
        }
    }

    private void ensureCapacity() {
        int newSize = size() * 2 + 1;
        array = Arrays.copyOf(array, newSize);
    }

    public ActerWithInitiative get(int index) {
        if (index > size || index < 0){
            throw new IndexOutOfBoundsException("Index " + index + ", Size " + index);
        }
        return array[index];
    }

    public int size() {
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
