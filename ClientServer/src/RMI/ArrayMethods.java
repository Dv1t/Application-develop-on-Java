package RMI;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ArrayMethods implements IArrayMethods{
    @Override
    public TwoArrays<Double> GetArrays(Double[] array) throws RemoteException {
        int[] firstArray;
        int[] secondArray;

        return  new TwoArrays<>(Arrays.stream(array).map((x)-> {return x*x;}).toArray(Double[]::new),
                Arrays.stream(array).map(Math::sqrt).toArray(Double[]::new));
    }
}
