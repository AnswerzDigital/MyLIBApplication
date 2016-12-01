package chehara.mylibapplication;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.http.SslError;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.ConsoleMessage;
import android.webkit.ValueCallback;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xwalk.core.XWalkActivity;

import org.xwalk.core.XWalkPreferences;
import org.xwalk.core.XWalkResourceClient;
import org.xwalk.core.XWalkUIClient;
import org.xwalk.core.XWalkView;

import java.util.Timer;
import java.util.TimerTask;

public class RoomActivity extends XWalkActivity {


    public int pageIndex;
    public static final String ARG_PAGEINDEX = "pageIndex";

    String screenName = "HomeScreen.Room";

    public XWalkView xWalkWebView;

    public String url;
    ProgressDialog dialog;
    Dialog alertDialog;
    Button btnOk;
    TextView txtMsg;
    public boolean isLeave;
    public String shareURL;
    public String message;
    public LinearLayout bannerLinear;
    Timer loadTimer;
    boolean isPageLoadedComplete;
    long loadingLimit = 30 * 1000;
    public String room;
    public String altUrl;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crosswalk);

        try {
            //XWalkPreferences.setValue(XWalkPreferences.ANIMATABLE_XWALK_VIEW, true);
            Intent intent = getIntent();
            context = this;

            Bundle extras = intent.getExtras();
            url = intent.hasExtra(CheharaUtils.URL) ? intent.getStringExtra(CheharaUtils.URL) : "www.google.com";
            shareURL = intent.hasExtra(CheharaUtils.SHAREURL) ? intent.getStringExtra(CheharaUtils.SHAREURL) : "";
            room = intent.hasExtra(CheharaUtils.ROOM) ? intent.getStringExtra(CheharaUtils.ROOM) : "";
            dialog = new ProgressDialog(context);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setIndeterminate(true);
            // dialog.setIndeterminateDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.animation));
            // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            //dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);


            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    finish();

                }
            });


            xWalkWebView = (XWalkView) findViewById(R.id.webView);


            alertDialog = new Dialog(context);
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            alertDialog.setContentView(R.layout.layout_custom_dialog);
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            txtMsg = (TextView) alertDialog.getWindow().findViewById(R.id.textViewdialogTitle);
            btnOk = (Button) alertDialog.getWindow().findViewById(R.id.btnOk);
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.cancel();
                }
            });
            //  xWalkWebView.load(url, null);

            //  xWalkWebView.clearCache(true);
            // Log.e("TAG", url);


            // xWalkWebView.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.banner4));
            //  xWalkWebView.addJavascriptInterface(new JsInterface(), "Mobile");

            XWalkPreferences.setValue(XWalkPreferences.REMOTE_DEBUGGING, true);
            //  xWalkWebView.setBackgroundColor(ContextCompat.getColor(context, R.color.theme_new_secondary));


            //  xWalkWebView.getNavigationHistory().clear();
            xWalkWebView.setUIClient(new XWalkUIClient(xWalkWebView) {
                public boolean onConsoleMessage(ConsoleMessage cm) {
                    Log.e("CheharaTime", cm.message() + " -- From line "
                            + cm.lineNumber() + " of "
                            + cm.sourceId());
                    return true;
                }

                @Override
                public void onPageLoadStarted(XWalkView view, String url) {

                    //System.out.println("onPageLoadStarted  " + url);
                    try {

                        message = "Loading";


                        loadTimer = new Timer();
                        loadingLimit = 30 * 1000;
                        loadTimer.schedule(new loaderTask(), loadingLimit);
                        isPageLoadedComplete = false;
                        dialog.setMessage(message);

                        if (Build.VERSION.SDK_INT >= 21) {
                            getWindow().setStatusBarColor(ContextCompat.getColor(context, R.color.room));
                        }
                        //dialog.setIndeterminate(false);
                        dialog.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // dialog.setContentView(R.layout.layout_custom_dialog);
                }


                @Override
                public void onPageLoadStopped(XWalkView view, String url,
                                              XWalkUIClient.LoadStatus status) {
                    System.out.println("onPageLoadStopped  " + status);
                    try {

                        dialog.dismiss();
                        isPageLoadedComplete = true;
                        loadTimer.cancel();
                        if (isLeave)
                            xWalkWebView.getNavigationHistory().clear();

                        if (alertDialog.isShowing())
                            alertDialog.cancel();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            });

            xWalkWebView.setResourceClient(new XWalkResourceClient(xWalkWebView) {


                @Override
                public boolean shouldOverrideUrlLoading(XWalkView view, String url) {
                    altUrl = url;
                    Log.e("TAG", altUrl);
                    return super.shouldOverrideUrlLoading(view, url);
                }

                @Override
                public void onReceivedLoadError(XWalkView view, int errorCode, String description, String failingUrl) {

                    // if (dialog != null)
                    dialog.dismiss();

                    showError("Error in Loading");
                    //  super.onReceivedLoadError(view, errorCode, description, failingUrl);


                }


                @Override
                public void onReceivedSslError(XWalkView view, ValueCallback<Boolean> callback, SslError error) {
                    // super.onReceivedSslError(view, callback, error);
                    // if (dialog != null)
                    dialog.dismiss();

                    showError("Error in Loading");


                }

            });


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onXWalkReady() {
        xWalkWebView.load(url, null);
    }

    /*
        public class JsInterface {

            public JsInterface() {
            }

            @org.xwalk.core.JavascriptInterface
            public void closeRoom() {
                try {
                    isPageLoadedComplete = true;
                    loadTimer.cancel();

                    isLeave = false;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @org.xwalk.core.JavascriptInterface
            public void inviteRoom(String pin) {

                //  Toast.makeText(getActivity(), pin, Toast.LENGTH_LONG).show();
                if (!pin.equalsIgnoreCase("")) {
                    shareURL = shareURL + "\nPin : " + pin;
                    Log.e("TAG", shareURL);
                }
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, shareURL);
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "CheharaTime"));
            }

            @org.xwalk.core.JavascriptInterface
            public void inviteRoom() {

                //  Toast.makeText(getActivity(), pin, Toast.LENGTH_LONG).show();

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, shareURL);
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "CheharaTime"));
            }
        }

    */
    @Override
    public void onStart() {
        try {


        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onStart();
    }

    @Override
    public void onStop() {
        try {


        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onStop();
    }

    @Override
    public void onPause() {

        if (xWalkWebView != null) {
            //  xWalkWebView.pauseTimers();
            xWalkWebView.onHide();
        }
        super.onPause();
    }


    @Override
    public void onResume() {

        super.onResume();
        if (xWalkWebView != null) {
            // xWalkWebView.resumeTimers();
            // xWalkWebView.onShow();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (xWalkWebView != null) {
            xWalkWebView.onDestroy();
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        //  super.onNewIntent();
        if (xWalkWebView != null) {
            xWalkWebView.onNewIntent(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (xWalkWebView != null) {
            xWalkWebView.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void showError(String Message) {

        txtMsg.setText(Message);
        alertDialog.show();

    }


    class loaderTask extends TimerTask {
        public void run() {
            //  System.out.println("Times Up");
            try {
                if (isPageLoadedComplete) {

                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (alertDialog.isShowing())
                                alertDialog.cancel();
                            showError("Connection  Timeout");
                            loadTimer.cancel();
                            dialog.dismiss();


                            isLeave = false;

                        }
                    });

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //show error message as per you need.
        }


    }


}
