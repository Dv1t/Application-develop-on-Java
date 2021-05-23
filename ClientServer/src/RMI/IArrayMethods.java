package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IArrayMethods extends Remote {
    public TwoArrays<Double> GetArrays(Double[] array) throws RemoteException;





}
