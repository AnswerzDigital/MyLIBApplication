package chehara.mylibapplication;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by answerz on 15/11/16.
 */
public class TestClass {

    public static void showToast(Context context) {
        Toast.makeText(context, "Test", Toast.LENGTH_LONG).show();
    }
}
