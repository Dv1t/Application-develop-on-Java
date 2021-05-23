package RMI;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer {
    public static final String UNIC_BINDING_NAME = "server.arrays";

    public static void main(String[] args) throws Exception
    {
        final ArrayMethods service = new ArrayMethods();

        final Registry registry = LocateRegistry.createRegistry(2099);
        Remote stub = UnicastRemoteObject.exportObject(service, 0);
        registry.bind(UNIC_BINDING_NAME, stub);

        Thread.sleep(Integer.MAX_VALUE);
    }
}
