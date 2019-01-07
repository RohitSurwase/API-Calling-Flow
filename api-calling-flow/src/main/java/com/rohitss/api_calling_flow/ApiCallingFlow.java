package com.rohitss.api_calling_flow;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public abstract class ApiCallingFlow {
    private ViewGroup parentLayout;
    private Context mContext;
    private View progressLayout;
    private Button btnTryAgain;
    private ProgressBar pbLoading;
    private TextView tvApiError;
    private TextView tvEnableNetwork;
    private ImageView ivCancel;
    private boolean isTransparent;
    private boolean isNetworkAvailable;
    private ConnectivityManager connectivityManager;
    private int mProgressBarColor = R.color.colorAccent;

    /**
     * Constructor used to initialize this functionality
     *
     * @param context       context
     * @param parentLayout  parentLayout
     * @param isTransparent isTransparent
     *                      Created by Rohit.
     */
    public ApiCallingFlow(Context context, ViewGroup parentLayout, boolean isTransparent) {
        this.parentLayout = parentLayout;
        mContext = context;
        this.isTransparent = isTransparent;
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        inflateAndSetUpLayout();
    }

    /**
     * Constructor used to initialize this functionality
     *
     * @param context          context
     * @param parentLayout     parentLayout
     * @param isTransparent    isTransparent
     * @param progressBarColor progressBarColor
     *                         Created by Rohit.
     */
    public ApiCallingFlow(Context context, ViewGroup parentLayout, boolean isTransparent, int progressBarColor) {
        this.parentLayout = parentLayout;
        mContext = context;
        this.isTransparent = isTransparent;
        mProgressBarColor = progressBarColor;
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        inflateAndSetUpLayout();
    }

    private void inflateAndSetUpLayout() {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        progressLayout = layoutInflater.inflate(R.layout.layout_api_calling_flow, parentLayout, false);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        progressLayout.setLayoutParams(layoutParams);
        //Calling function to initialize required views.
        initializeViews();
        if (parentLayout != null) {
            removeProgressView();
            parentLayout.addView(progressLayout);
        }
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        // if no network is available networkInfo will be null, otherwise check if we are connected
        if (networkInfo != null && networkInfo.isConnected()) {
            isNetworkAvailable = true;
            btnTryAgain.setVisibility(View.GONE);
            ivCancel.setVisibility(View.GONE);
            pbLoading.setVisibility(View.VISIBLE);
            tvEnableNetwork.setVisibility(View.GONE);
        } else {
            isNetworkAvailable = false;
            btnTryAgain.setVisibility(View.VISIBLE);
            ivCancel.setVisibility(View.VISIBLE);
            pbLoading.setVisibility(View.GONE);
            tvEnableNetwork.setVisibility(View.VISIBLE);
        }
        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                // if no network is available networkInfo will be null, otherwise check if we are connected
                if (networkInfo != null && networkInfo.isConnected()) {
                    isNetworkAvailable = true;
                    removeProgressView();
                    callCurrentApiHere();
                }
            }
        });
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeProgressView();
            }
        });
        //Set spannable text to enable WiFI and Mobile Data
        SpannableString spannableString = new SpannableString("No Internet connection. Enable Wi-Fi or Cellular Mobile data, then try again.");
        ClickableSpan spanWiFi = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                //to enable wifi
                try {
                    mContext.getApplicationContext().startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS).setFlags(FLAG_ACTIVITY_NEW_TASK));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        ClickableSpan spanMobile = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                //to enable mobile data
                try {
                    mContext.getApplicationContext().startActivity(new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS).setFlags(FLAG_ACTIVITY_NEW_TASK));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        spannableString.setSpan(spanWiFi, 31, 36, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), 31, 36, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(spanMobile, 40, 60, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), 40, 60, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvEnableNetwork.setText(spannableString);
        tvEnableNetwork.setMovementMethod(LinkMovementMethod.getInstance());
        tvEnableNetwork.setHighlightColor(Color.TRANSPARENT);
    }

    /**
     * <b>private void initializeViews()</b>
     * <p>This function is used to initialize required views.</p>
     * Created by - Rohit
     */
    private void initializeViews() {
        btnTryAgain = ((Button) progressLayout.findViewById(R.id.btnTryAgain));
        btnTryAgain.setVisibility(View.GONE);
        pbLoading = ((ProgressBar) progressLayout.findViewById(R.id.progressBar));
        pbLoading.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(mContext, mProgressBarColor),
                android.graphics.PorterDuff.Mode.MULTIPLY);
        pbLoading.setVisibility(View.GONE);

        tvApiError = ((TextView) progressLayout.findViewById(R.id.tvApiError));
        tvApiError.setVisibility(View.GONE);
        ivCancel = (ImageView) progressLayout.findViewById(R.id.imgCancel);
        ivCancel.setVisibility(View.GONE);
        tvEnableNetwork = (TextView) progressLayout.findViewById(R.id.tvEnableNetwork);
        tvEnableNetwork.setVisibility(View.GONE);
        RelativeLayout rlBaseLayout = (RelativeLayout) progressLayout.findViewById(R.id.rl_base_layout);
        if (isTransparent) {
            rlBaseLayout.setBackgroundColor(Color.TRANSPARENT);
        } else {
            rlBaseLayout.setBackgroundColor(Color.WHITE);
        }
    }

    /**
     * function to be called when API need to be retried.
     */
    public abstract void callCurrentApiHere();

    /**
     * remove inflated layout
     */
    private void removeProgressView() {
        if (parentLayout != null && progressLayout.getParent() == parentLayout) {
            parentLayout.removeView(progressLayout);
        }
    }

    /**
     * function to be called when API failed.
     */
    public void onErrorResponse() {
        pbLoading.setVisibility(View.GONE);
        tvApiError.setVisibility(View.VISIBLE);
        ivCancel.setVisibility(View.VISIBLE);
    }

    /**
     * function to be called when API succeed.
     */
    public void onSuccessResponse() {
        removeProgressView();
    }

    public boolean getNetworkState() {
        return isNetworkAvailable;
    }
}
