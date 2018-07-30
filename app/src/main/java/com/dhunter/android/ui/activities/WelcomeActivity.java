package com.dhunter.android.ui.activities;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.dhunter.android.R;
import com.dhunter.android.config.Constant;
import com.dhunter.common.easypermissions.EasyPermissions;
import com.dhunter.common.network.SharePreferenceHelper;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.List;

/**
 * Created by dhunter on 2018/7/30.
 */

public class WelcomeActivity extends RxAppCompatActivity implements EasyPermissions.PermissionCallbacks {
    private static final String TAG = "WelcomeActivity";

    private boolean isLogin = false;//是否登录了
    private boolean isUseFirst ;//是否第一次使用
    private Toast toast = null;
    private SharePreferenceHelper mSharePreDate;
    private String[] permission_perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE, Manifest.permission.SEND_SMS};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mSharePreDate = new SharePreferenceHelper(getApplicationContext());
        setPermission();
    }

    private void startToLoginActivity() {
        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
        finish();
    }

    private void startToNext() {
        if (isLogin) {
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            startToLoginActivity();
        }
    }

    private void setPermission() {
        if(!EasyPermissions.hasPermissions(getApplicationContext(), permission_perms)) {
            EasyPermissions.requestPermissions(this, getString(R.string.get_permission),
                    Constant.LOCATION_PERMISSION_REQUESTCODE, permission_perms);
        } else {
            startToNext();
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
//        toast("onPermissionsGranted:" + requestCode + ":" + perms.size());
        if(EasyPermissions.hasPermissions(getApplicationContext(), permission_perms)) {
            startToNext();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        toast("onPermissionsDenied: Please granted permission");
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }



    public void toast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }
}
