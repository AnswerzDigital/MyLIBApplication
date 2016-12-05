package chehara.mylibapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by answerz on 16/11/16.
 */
public class CheharaTime {

    public static void callEvent(Context context, String room) {


        List<String> permissionsNeeded = new ArrayList<String>();
        String read = Manifest.permission.READ_EXTERNAL_STORAGE, write = Manifest.permission.WRITE_EXTERNAL_STORAGE, camera = Manifest.permission.CAMERA, audio = Manifest.permission.RECORD_AUDIO, modi_audio = Manifest.permission.MODIFY_AUDIO_SETTINGS, loc = Manifest.permission.ACCESS_FINE_LOCATION;
        Intent intent;

        try {
            if (CheharaConst.DEVICE_API_INT >= CheharaConst.ANROID_API_MARSHMALLOW) {

                if (!CheharaUtils.checkPermission(read, context))
                    permissionsNeeded.add(read);

                if (!CheharaUtils.checkPermission(write, context))
                    permissionsNeeded.add(write);

                if (!CheharaUtils.checkPermission(camera, context))
                    permissionsNeeded.add(camera);

                if (!CheharaUtils.checkPermission(audio, context))
                    permissionsNeeded.add(audio);

                if (!CheharaUtils.checkPermission(modi_audio, context))
                    permissionsNeeded.add(modi_audio);

                if (!CheharaUtils.checkPermission(loc, context))
                    permissionsNeeded.add(loc);


                if (permissionsNeeded.size() != 0) {
                    throw new IllegalStateException("");
                }
            }
            String url = CheharaConst.HOST + "room.jsp?roomname=" + room + "&type=mobileapp";
            intent = new Intent(context, RoomActivity.class);
            intent.putExtra(CheharaUtils.URL, url);
            intent.putExtra(CheharaUtils.ROOM, room);
            intent.putExtra(CheharaUtils.SHAREURL, CheharaConst.CHEHARAHOST + room);
            context.startActivity(intent);


            // Toast.makeText(context,"Add Permission in Application",To)

        } catch (IllegalStateException e) {
            CustomDialog.buildAlertDialogTitle(context, "Add Permission in Application", "").show();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
