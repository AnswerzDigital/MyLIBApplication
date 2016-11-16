package chehara.mylibapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Camera;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.io.File;

/**
 * Created by answerz on 4/8/16.
 */
public class CheharaUtils {

    static final String tag = CheharaUtils.class.getSimpleName();

    public static final String URL = "Url", ROOM = "Room", SHAREURL = "shareURL";

    public static File getFilesDirectory(Context context) {
        // creates files directory under data/data/package name
        return context.getFilesDir();
    }

    public static boolean isOnline(Context context) {// requires network state
        // access permisstion
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
    // set fullscreen activity


    public static boolean checkPermission(String permission, Context context) {
        int result = ContextCompat.checkSelfPermission(context, permission);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }


    public static void showMessageOKCancel(Context context, String message, DialogInterface.OnClickListener okListener, DialogInterface.OnClickListener cancelListener) {

        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", cancelListener)
                .create()
                .show();
    }

    public static void showSetting(Context context, String message, DialogInterface.OnClickListener okListener, DialogInterface.OnClickListener cancelListener) {

        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                //.setNegativeButton("Cancel", cancelListener)
                .create()
                .show();
    }


    public static boolean hasFrontCamCameraApi() {
        boolean hasFrontCam = false;
        Camera.CameraInfo ci = new Camera.CameraInfo();
        for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
            Camera.getCameraInfo(i, ci);
            if (ci.facing == Camera.CameraInfo.CAMERA_FACING_FRONT)
                hasFrontCam = true;
        }
        return hasFrontCam;
    }

    public static final int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }
}
