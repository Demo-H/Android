package com.dhunter.android.ui.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dhunter.android.R;
import com.dhunter.android.base.BaseActivity;
import com.dhunter.android.config.Constant;
import com.dhunter.android.entity.BaseResponse;
import com.dhunter.android.http.MainDataManager;
import com.dhunter.android.ui.component.DaggerLoginActivityComponent;
import com.dhunter.android.ui.contract.LoginContract;
import com.dhunter.android.ui.module.LoginPresenterModule;
import com.dhunter.android.ui.presenter.LoginPresenter;
import com.dhunter.common.easypermissions.EasyPermissions;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录界面
 */

public class LoginActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks, LoginContract.View{

    @BindView(R.id.user_name)
    EditText mUserName;
    @BindView(R.id.password)
    EditText mPsw;
    @BindView(R.id.login)
    TextView mLogin;

    private String mobile;
    private String psw;

    @Inject
    LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initLayout() {
        setPermission();
        DaggerLoginActivityComponent.builder()
                .appComponent(getAppComponent())
                .loginPresenterModule(new LoginPresenterModule(this, MainDataManager.getInstance(mDataManager)))
                .build()
                .inject(this);
    }

    @Override
    protected void requestData() {

    }

    /**
     * 登录的过程是按正常网络请求执行，返回错误仍然进入主页面
     * @param view
     */
    @OnClick({R.id.login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                mobile = mUserName.getText().toString().trim();
                psw = mPsw.getText().toString().trim();
                showDialog();
                mPresenter.login(mobile, psw);
                break;
            case R.id.register_new:
                jumpToActivity(LoginActivity.this, RegisterActivity.class);
                break;
            case R.id.forget_password:
                jumpToActivity(LoginActivity.this, ForgetPasswordActivity.class);
                break;
        }
    }

    @Override
    public void setLoginResult(BaseResponse response) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void setPermission() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE, Manifest.permission.SEND_SMS};
        if(!EasyPermissions.hasPermissions(mContext.getApplicationContext(), perms)) {
            EasyPermissions.requestPermissions(this, getString(R.string.get_permission),
                    Constant.LOCATION_PERMISSION_REQUESTCODE, perms);
        }
    }

    private void jumpToActivity(Context context, Class<?> _cls) {
        Intent intent = new Intent();
        intent.setClass(context, _cls);
        context.startActivity(intent);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        toast("onPermissionsGranted:" + requestCode + ":" + perms.size());
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        toast("onPermissionsDenied:" + requestCode + ":" + perms.size());
    }
}
