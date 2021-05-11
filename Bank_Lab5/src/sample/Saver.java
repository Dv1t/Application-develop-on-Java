package sample;

import sample.DB.DbWorker;

import java.io.*;

public class Saver {
    public static final Saver INSTANCE = new Saver();

    private DbWorker dbWorker = DbWorker.getInstance();

    private Saver() {
    }

    public void saveUser(User user) {
        try {
            if(dbWorker.loadUsers().stream().filter
                    (u -> u.getId() == user.getId()).findAny().orElse(null)==null)
            {
                dbWorker.insertUser(user);
            }
            else
            {
                dbWorker.updateUser(user);
            }
        }
        catch (Exception ex) {
        }

    }

    public User reestablishUser(String name, String surname){
        try {
                return dbWorker.loadUsers().stream().filter
                        (u -> u.getId() == (name+surname).hashCode()).findAny().orElse(null);
            }
        catch (Exception exception)
        {
            return null;
        }
    }
}