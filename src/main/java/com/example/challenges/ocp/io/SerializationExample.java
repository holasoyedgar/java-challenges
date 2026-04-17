package com.example.challenges.ocp.io;

import java.io.*;

public class SerializationExample {
    public static void serialize(Object object, String fileName) throws IOException {
        try (var out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))) {
             out.writeObject(object);
        }
    }

    public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
        try (var in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
            return in.readObject();
        }
    }

    public static void main(String[] args){
        User user = new User("John Wayne", "12345");
        try {
            // Serialize
            serialize(user, "john_wayne.ser");

            // Deserialize
            User deserializedUser = (User) deserialize("john_wayne.ser");
            System.out.println(deserializedUser);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{name=" + name + ", password=" + password + "}";
    }
}