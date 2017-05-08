package ro.bookstore.common.utils;

import java.io.*;

/**
 * Created by nicu on 12/8/2016.
 */
public class SerializationUtil {
    /**
     * Deserialize an object from a file with given file name
     * @param fileName - String, the path of the file
     * @return Object
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object deserialize(String fileName) throws IOException,
            ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();

        return object;
    }

    /**
     * Serialize given object into the file with given file name.
     * @param object - Object
     * @param fileName - path of the file
     * @throws IOException
     */
    public static void serialize(Object object, String fileName)
            throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        ObjectOutputStream outputStream = new ObjectOutputStream(bufferedOutputStream);
        outputStream.writeObject(object);
        outputStream.close();
    }
}
