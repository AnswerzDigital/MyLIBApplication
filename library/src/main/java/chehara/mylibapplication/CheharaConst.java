package chehara.mylibapplication;

import android.os.Build;
import android.os.Environment;

import java.io.File;

/**
 * Created by answerz on 4/8/16.
 */
public class CheharaConst {

    public static final int DEVICE_API_INT = Build.VERSION.SDK_INT;
    public static final int ANROID_API_LOILLIPOP = Build.VERSION_CODES.LOLLIPOP;
    public static final int ANROID_API_KITKAT = Build.VERSION_CODES.KITKAT;
    public static final int ANROID_API_MARSHMALLOW = Build.VERSION_CODES.M;

    public static final String IMAGEFORMAT = ".jpg";
    public static final String SDCARD = Environment
            .getExternalStorageDirectory().getAbsolutePath();
    public static final String CHEHARA_DIR = File.separator + "MyChehara";


    // new server
    private static final String SERVER_IP = "http://54.254.137.0";

    private static final String SERVER_PORT = "1234";


    public static final String SAMPLE_RESUME = "http://d820er5mm3k0u.cloudfront.net/mychehara/webm.webm";

    //public static final String SAMPLE_RESUME_POSTER = MEDIAHOST
    //	+ "/mychehara/poster.jpg";

    private static final String LOCAL_IP = "http://192.168.1.57";
    private static final String LOCAL_PORT = "8084";
    private static final String IP = SERVER_IP;
    private static final String PORT = SERVER_PORT;

    // private static final String IP = SERVER_IP;
    // private static final String PORT = SERVER_PORT;

    // public static final String SERVERHOST = IP + ":" + PORT + "/mychehara";
    public static final String SERVERHOST = "https://m.cheharatime.com/";
    public static final String TESTHOST = "http://192.168.1.234:8084/cheharatime/";
    public static final String LOCALHOST = "http://192.168.1.2:8084/mcheharatime/";
    public static final String CHEHARAHOST = "https://cheharatime.com/";

    private static final String API = "/mychehara/api";

    public static final String HOST = SERVERHOST;
    public static final String ENDPOINT_FILE_UPLOAD = HOST + "/UploadServlet";
    public static final String ENDPOINT_PDF_UPLOAD = HOST + "/Uploadpdf";
    public static final String ENDPOINT_IS_VIDEO_AVAIL = HOST
            + "/mycheharavideovalid";
    public static final String ENDPOINT_GET_VIDEO_STATUS = HOST
            + "/mycheharavideostatus";

    public static final String APP_VERSION = "appVersion";

    public static final String ENDPOINT_TERMS = "https://m.cheharatime.com/terms.jsp";
    public static final String ENDPOINT_FAQ = "https://m.cheharatime.com/#FAQ";
    public static final String ENDPOINT_REGISTER = HOST
            + "register.do";
    public static final String ENDPOINT_GET_COUNTRY = HOST
            + "register";


    public static final String GOOGLE_PROJECT_ID = "1046021982796";
    public static final String MESSAGE_KEY = "message";

}
