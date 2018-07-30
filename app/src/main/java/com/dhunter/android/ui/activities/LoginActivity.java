package com.dhunter.android.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dhunter.android.R;
import com.dhunter.android.base.BaseActivity;
import com.dhunter.android.entity.BaseResponse;
import com.dhunter.android.entity.login.LoginRequest;
import com.dhunter.android.http.LoginDataManager;
import com.dhunter.android.ui.component.DaggerLoginActivityComponent;
import com.dhunter.android.ui.contract.LoginContract;
import com.dhunter.android.ui.module.LoginPresenterModule;
import com.dhunter.android.ui.presenter.LoginPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录界面
 */

public class LoginActivity extends BaseActivity implements LoginContract.View{

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
        DaggerLoginActivityComponent.builder()
                .appComponent(getAppComponent())
                .loginPresenterModule(new LoginPresenterModule(this, LoginDataManager.getInstance(mDataManager)))
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
    @OnClick({R.id.login, R.id.register_new, R.id.forget_password})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                showDialog();
                mPresenter.login(getLoginRequest());
                break;
            case R.id.register_new:
                jumpToActivity(LoginActivity.this, RegisterActivity.class);
                break;
            case R.id.forget_password:
                jumpToActivity(LoginActivity.this, ForgetPasswordActivity.class);
                break;
        }
    }

    private LoginRequest getLoginRequest() {
        mobile = mUserName.getText().toString().trim();
        psw = mPsw.getText().toString().trim();
        LoginRequest request = new LoginRequest();
        request.setPhone(mobile);
        request.setPwd(psw);
        return request;
    }

    @Override
    public void setLoginResult(BaseResponse response) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void jumpToActivity(Context context, Class<?> _cls) {
        Intent intent = new Intent();
        intent.setClass(context, _cls);
        context.startActivity(intent);
    }

}
