package sample;

import java.io.*;

public class Saver {
    public static final Saver INSTANCE = new Saver();

    private Saver() {
    }

    public void saveAccount(Account acc, String filename) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename));
        objectOutputStream.writeObject(acc);
        objectOutputStream.close();
    }

    public Account reestablishAccount(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename));
        Account acc = (Account) objectInputStream.readObject();
        objectInputStream.close();
        return acc;
    }

    public void saveUser(User user) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(user.toString()));
        objectOutputStream.writeObject(user);
        objectOutputStream.close();
    }

    public User reestablishUser(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename));
        User user = (User) objectInputStream.readObject();
        objectInputStream.close();
        return user;
    }

    public  User getUserByUserString(String user){
        try {
            return reestablishUser(user);
        }
        catch (Exception exception)
        {
            return null;
        }
    }
}