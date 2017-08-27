package com.rohitss.apicallingflow_example;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
        ((Button) findViewById(R.id.btnReset)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((TextView) findViewById(R.id.tvDummyView)).setText(getString(R.string.dummy_text));
            }
        });
    }

    private void requestTestApi() {
        //TODO Step 1: Get reference to root layout of Activity or Fragment.
        RelativeLayout parentLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        /*
         * TODO Step 2: Initialize ApiCallingFlow
         * 1st parameter - context
         * 2nd parameter - parentLayout from step 1
         * 3rd parameter - isTransparent (if you want background color to be transparent then 'true' else for default white background 'false')
         */
        final ApiCallingFlow apiCallingFlow = new ApiCallingFlow(this, parentLayout, false) {
            @Override
            public void callCurrentApiHere() {
                //TODO Step 3: Pass function to call current API
                requestTestApi();
            }
        };
        //TODO Step 4: Get current Network state ( apiCallingFlow.getNetworkState() ) and request API accordingly.
        if (apiCallingFlow.getNetworkState()) {
            AndroidNetworking.get("https://jsonplaceholder.typicode.com/posts/1")
                    .setPriority(Priority.HIGH)
                    .setTag("requestTestApi")
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    ((TextView) findViewById(R.id.tvDummyView)).setText(getString(R.string.valid_text));
                                    //TODO Step 5: Call ( apiCallingFlow.onSuccessResponse(); ) after API is successful
                                    apiCallingFlow.onSuccessResponse();
                                }
                            }, 5000);
                        }

                        @Override
                        public void onError(ANError anError) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //TODO Step 6: Call ( apiCallingFlow.onErrorResponse(); ) after API is failed
                                    apiCallingFlow.onErrorResponse();
                                }
                            }, 5000);
                        }
                    });
        }
    }
}
