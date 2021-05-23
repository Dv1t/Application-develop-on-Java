package RMI;

import java.io.Serializable;

public class TwoArrays<T> implements Serializable {
    public T[] FirstArray;
    public T[] SecondArray;

    public TwoArrays(T[] firstArray,T[] secondArray)
    {
        this.FirstArray = firstArray;
        this.SecondArray = secondArray;
    }
}
