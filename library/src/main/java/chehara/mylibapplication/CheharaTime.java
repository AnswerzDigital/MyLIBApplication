package chehara.mylibapplication;

import android.content.Context;
import android.content.Intent;

/**
 * Created by answerz on 16/11/16.
 */
public class CheharaTime {

    public static void callEvent(Context contex, String room) {
        String url = CheharaConst.HOST + "room.jsp?roomname=" + room + "&type=mobileapp";

        Intent intent = new Intent(contex, RoomActivity.class);
        intent.putExtra(CheharaUtils.URL, url);
        intent.putExtra(CheharaUtils.ROOM, room);
        intent.putExtra(CheharaUtils.SHAREURL, CheharaConst.CHEHARAHOST + room);
        contex.startActivity(intent);

    }
}
