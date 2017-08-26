package com.rohitss.webservice_example;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.rohitss.api_calling_flow.ApiCallingFlow;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button) findViewById(R.id.btnRequestApi)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestTestApi();
            }
        });
    }

    private void requestTestApi() {
        RelativeLayout parentLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        final ApiCallingFlow apiCallingFlow = new ApiCallingFlow(this, parentLayout, false) {
            @Override
            public void callCurrentApiHere() {
                requestTestApi();
            }
        };
        if (apiCallingFlow.getNetworkState()) {
            AndroidNetworking.get("https://jsonplaceholder.typicode.com/posts/1")
                    .setPriority(Priority.HIGH)
                    .setTag("requestTestApi")
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("MainActivity", "onResponse: " + response);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    apiCallingFlow.onSuccessResponse();
                                }
                            }, 5000);
                        }

                        @Override
                        public void onError(ANError anError) {
                            Log.d("MainActivity", "onError Code: " + anError.getErrorCode());
                            Log.d("MainActivity", "onError Body: " + anError.getErrorBody());
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    apiCallingFlow.onErrorResponse();
                                }
                            }, 5000);
                        }
                    });
        }
    }
}
