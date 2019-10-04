package com.modanez.pdx.pdx;


import android.os.Bundle;


import io.flutter.app.FlutterActivity;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugins.GeneratedPluginRegistrant;


public class MainActivity extends FlutterActivity {

    private static final String CHANNEL = "plugins";

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        GeneratedPluginRegistrant.registerWith(this);

        new MethodChannel(getFlutterView(), CHANNEL).setMethodCallHandler(
                new MethodChannel.MethodCallHandler() {
                    @Override
                    public void onMethodCall(MethodCall call, Result result) {
                        if (call.method.equals("auttarLogin")) {
                            Auttar payment = new Auttar();
                            payment.Init(getBaseContext(), getApplicationContext());
                            payment.Login(MainActivity.this);

                        } else if (call.method.equals("auttarConfigure")) {
                            Auttar payment = new Auttar();
                            payment.Init(getBaseContext(), getApplicationContext());
                            payment.Configure(MainActivity.this);

                        } else {
                            result.notImplemented();
                        }
                    }
                });
    }
}






