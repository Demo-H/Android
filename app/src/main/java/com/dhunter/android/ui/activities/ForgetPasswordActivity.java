package com.dhunter.android.ui.activities;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dhunter.android.R;
import com.dhunter.android.base.BaseActivity;
import com.dhunter.android.entity.login.LoginRequest;
import com.dhunter.android.http.LoginDataManager;
import com.dhunter.android.ui.component.DaggerForgetPasswordActivityComponent;
import com.dhunter.android.ui.contract.ForgetPasswordContract;
import com.dhunter.android.ui.module.ForgetPasswordPresenterModule;
import com.dhunter.android.ui.presenter.ForgetPasswordPresenter;
import com.dhunter.android.utils.CheckUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by dhunter on 2018/2/9.
 * 忘记密码和修改密码在该UI上进行
 */

public class ForgetPasswordActivity extends BaseActivity implements ForgetPasswordContract.View {


    @BindView(R.id.toolbar_title)
    TextView mTitle;
    @BindView(R.id.right_text)
    TextView mRightText;

    @BindView(R.id.phone_num)
    EditText mPhoneNumberEdit;
    @BindView(R.id.desc)
    TextView mDescText;
    @BindView(R.id.modified_psw_rl)
    RelativeLayout mModifiedPswRl;
    @BindView(R.id.code_edit)
    EditText mSmsCodeEdit;
    @BindView(R.id.get_code_btn)
    Button mGetCode;
    @BindView(R.id.pws)
    EditText mOriPasswd;
    @BindView(R.id.re_pws)
    EditText mRePasswd;

    private String mPhoneString;
    private String passwd;
    private String rePasswd;
    private String mSmsCode;
    private int time = 60;

    private boolean isrun = false; //标识短验控件线程销毁

    @Inject
    ForgetPasswordPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void initLayout() {
        initToolBar();
        DaggerForgetPasswordActivityComponent.builder()
                .appComponent(getAppComponent())
                .forgetPasswordPresenterModule(new ForgetPasswordPresenterModule(this, LoginDataManager.getInstance(mDataManager)))
                .build()
                .inject(this);
    }

    @Override
    protected void requestData() {
    }

    private void initToolBar() {
        mTitle.setText(getResources().getString(R.string.forget_password));
    }

    @Override
    protected void onResume() {
        super.onResume();
        isrun = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isrun = false;
    }

    @OnClick({R.id.left_back, R.id.next, R.id.get_code_btn})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_back:
                onBackPressed();
                break;
            case R.id.next:
                nextToSubmitModified();
                break;
            case R.id.get_code_btn:
                getSmsCode();
                break;
        }
    }

    //重写返回按钮
    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        ActivityManager.getInstance().removeActivity(this);
        finish();
    }

    private void getSmsCode() {
        mPhoneString = mPhoneNumberEdit.getText().toString().trim();
        if(mPhoneString.isEmpty()) {
            toast(getResources().getString(R.string.not_input_phone_number));
            return;
        }
        if(!CheckUtils.isPhoneNumber(mPhoneString)) {
            toast(getResources().getString(R.string.not_match_phone_number));
            return;
        }
        mGetCode.setEnabled(false);
        showDialog();
        mPresenter.getSMSCode(mPhoneString);
    }


    private void nextToSubmitModified() {
        mSmsCode = mSmsCodeEdit.getText().toString().trim();
        passwd = mOriPasswd.getText().toString().trim();
        rePasswd = mRePasswd.getText().toString().trim();
        if(passwd.isEmpty()) {
            toast(getResources().getString(R.string.input_new_password));
            return;
        }else if(rePasswd.isEmpty()) {
            toast(getResources().getString(R.string.input_new_password_again));
            return;
        }else if(mSmsCode.isEmpty()) {
            toast(getResources().getString(R.string.input_sms_code));
        }
        if(!passwd.equals(rePasswd)) {
            toast(getResources().getString(R.string.password_not_equal));
            return;
        }
        showDialog();
        mPresenter.forgetResetPwd(getResetRequest());
    }


    @Override
    public void setSMSCodeSuccess() {
        mGetCode.post(timer);
    }

    @Override
    public void setSMSCodeError() {
        mGetCode.setEnabled(true);
    }

    @Override
    public void setResetPwdSuccess() {
        finish();
    }

    private LoginRequest getResetRequest() {
        LoginRequest request = new LoginRequest();
        request.setPhone(mPhoneString);
        request.setCode(mSmsCode);
        request.setNewPwd(passwd);
        request.setAgainPwd(rePasswd);
        return request;
    }

    Runnable timer = new Runnable() {
        @Override
        public void run() {
            if(isrun) {
                mGetCode.setText("剩余" + (time--) + "秒");
                if (time > 0) {
                    mGetCode.postDelayed(timer, 1000);
                    mGetCode.requestLayout();
                } else {
                    mGetCode.setEnabled(true);
                    mGetCode.setText("重新获取验证码");
                    time = 60;
                }
            }
        }
    };
}
