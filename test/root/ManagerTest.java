package root;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ManagerTest {
    Manager manager;
    Method  rtf;

    @BeforeEach
    public void setUp() throws NoSuchMethodException {
        manager = new Manager();
        rtf = Manager.class.getDeclaredMethod("readTextFile", String.class);
        rtf.setAccessible(true);
    }



    @Test
    public void readTextFileメソッドでRandomSeedドットtxtからシード値が取り込まれている() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String currentDirectory = System.getProperty("user.dir");
        String textFilePath = currentDirectory + "/src/root/RandomSeed.txt";

        BufferedReader actual = (BufferedReader) rtf.invoke(manager, textFilePath);
    }



}