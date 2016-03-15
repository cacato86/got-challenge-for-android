package es.npatarino.android.gotchallenge;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by carloscarrasco on 15/3/16.
 */
public class UtilsTest {

    public String getStringFromAssets(String nameJson) throws IOException {
        InputStream in = getClass().getClassLoader().getResourceAsStream(nameJson + ".json");
        String jsonString = convertInputStreamToJson(in);
        return jsonString;
    }

    private String convertInputStreamToJson(InputStream is) throws IOException {
        String json = null;
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        json = new String(buffer, "UTF-8");
        return json;
    }
}
