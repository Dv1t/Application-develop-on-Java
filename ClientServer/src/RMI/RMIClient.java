package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

public class RMIClient {
    public static final String UNIC_BINDING_NAME = "server.arrays";

    public static void main(String[] args) throws Exception
    {
        final Registry registry = LocateRegistry.getRegistry(2099);

        IArrayMethods service = (IArrayMethods) registry.lookup(UNIC_BINDING_NAME);

        TwoArrays result = service.GetArrays(new Double[]{4.0,9.0,12.0});

        Arrays.stream(result.FirstArray).forEach(x->System.out.print(x+" "));
        System.out.println();
        Arrays.stream(result.SecondArray).forEach(x->System.out.print(x+" "));
    }
}
