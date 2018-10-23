package root;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class GeneralManagerTest {
    GeneralManager generalManager;
    Method  rtf;

    @BeforeEach
    public void setUp() throws NoSuchMethodException {
        generalManager = new GeneralManager();
        rtf = GeneralManager.class.getDeclaredMethod("readTextFile", String.class);
        rtf.setAccessible(true);
    }



    @Test
    public void readTextFileメソッドでRandomSeedドットtxtからシード値が取り込まれている() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String currentDirectory = System.getProperty("user.dir");
        String textFilePath = currentDirectory + "/src/root/RandomSeed.txt";

        BufferedReader actual = (BufferedReader) rtf.invoke(generalManager, textFilePath);
    }



}