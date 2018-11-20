package root;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.times;

import static org.powermock.api.mockito.PowerMockito.*;
import static org.hamcrest.CoreMatchers.is;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


import static root.EnvironmentalConstants.*;

@Tag("all")
class GeneralManagerTest {
    GeneralManager sut;

    @BeforeEach
    void setUp(){
        sut = new GeneralManager();
    }

    @Test
    void readTextFileメソッドでRandomSeedドットtxtからシード値が取り込まれている() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
        Method rtf;

        rtf = GeneralManager.class.getDeclaredMethod("readTextFile", String.class);
        rtf.setAccessible(true);

        String currentDirectory = System.getProperty("user.dir");
        String textFilePath = currentDirectory + "/src/root/RandomSeed.txt";
        BufferedReader actualBR = (BufferedReader) rtf.invoke(sut, textFilePath);

        File f = new File(textFilePath);
        FileReader fr = new FileReader(f);
        BufferedReader expectedBR =  new BufferedReader(fr);

        String actual, expected;
        while( (actual = actualBR.readLine()) != null ){
            expected = expectedBR.readLine();
            assertThat(actual, is(expected));
        }

    }
}