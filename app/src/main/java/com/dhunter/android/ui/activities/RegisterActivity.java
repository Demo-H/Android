package com.dhunter.android.ui.activities;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dhunter.android.R;
import com.dhunter.android.base.BaseActivity;
import com.dhunter.android.entity.login.LoginRequest;
import com.dhunter.android.http.LoginDataManager;
import com.dhunter.android.ui.component.DaggerRegisterActivityComponent;
import com.dhunter.android.ui.contract.RegisterContract;
import com.dhunter.android.ui.module.RegisterPresenterModule;
import com.dhunter.android.ui.presenter.RegisterPresenter;
import com.dhunter.android.utils.CheckUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by dhunter on 2018/3/16.
 */

public class RegisterActivity extends BaseActivity implements RegisterContract.View {

    private static final String TAG = "RegisterActivity";


    @BindView(R.id.toolbar_title)
    TextView mTitle;
    @BindView(R.id.right_text)
    TextView mRightText;

    @BindView(R.id.phone_num)
    EditText mPhoneNum;
    @BindView(R.id.code_edit)
    EditText mSmsCode;
    @BindView(R.id.get_sms_code)
    TextView mGetSmsCode;
    @BindView(R.id.invite_code)
    EditText mInviteCode;

    private String mPhoneNumberString;
    private String mSmsCodeString;
    private String mInviteCodeString;


    private boolean isrun = false; //标识短验控件线程销毁
    private int time = 60;

    @Inject
    RegisterPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initLayout() {
        DaggerRegisterActivityComponent.builder()
                .appComponent(getAppComponent())
                .registerPresenterModule(new RegisterPresenterModule(this, LoginDataManager.getInstance(mDataManager)))
                .build()
                .inject(this);
        mTitle.setText(getResources().getString(R.string.register_new_user));
        mRightText.setText(getResources().getString(R.string.submit));
    }

    @Override
    protected void requestData() {

    }

    @OnClick({R.id.left_back, R.id.get_sms_code, R.id.next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_back:
                finish();
                break;
            case R.id.get_sms_code:
                mPhoneNumberString = mPhoneNum.getText().toString();
                if (mPhoneNumberString.isEmpty()) {
                    toast("电话号码未填写");
                    return;
                } else if(!checkPhoneNumber()) {
                    toast("请输入正确的手机号码");
                    return;
                } else {
                    mGetSmsCode.setEnabled(false);
                    showDialog();
                    mPresenter.getSMSCode(mPhoneNumberString);
                }
                break;
            case R.id.next:
                mPhoneNumberString = mPhoneNum.getText().toString().trim();
                mSmsCodeString = mSmsCode.getText().toString().trim();
                mInviteCodeString = mInviteCode.getText().toString().trim();
                if (mPhoneNumberString == null || mPhoneNumberString.isEmpty()) {
                    toast(getResources().getString(R.string.not_input_phone_number));
                } else if (!checkPhoneNumber()) {
                    toast(getResources().getString(R.string.not_match_phone_number));
                } else if (mSmsCodeString == null || mSmsCodeString.isEmpty()) {
                    toast(getResources().getString(R.string.input_sms_code));
                    return;
                } else if (mInviteCodeString == null || mInviteCodeString.isEmpty()) {
                    toast(getResources().getString(R.string.input_invite_code));
                    return;
                } else {
                    mRightText.setEnabled(false);
                    showDialog();
                    startRegister();
                }
                break;
        }
    }

    @Override
    public void setSmsCodeResult() {
        mGetSmsCode.post(timer);
    }

    @Override
    public void setSMSCodeError() {
        mRightText.setEnabled(true);
        mGetSmsCode.setEnabled(true);
    }

    @Override
    public void setRegisterResult() {
//        showResultDialog();
    }

    @Override
    public void setRegisterError() {
        mRightText.setEnabled(true);
    }

    private boolean checkPhoneNumber() {
        boolean result = false;
        result = CheckUtils.isPhoneNumber(mPhoneNumberString);
        return result;
    }


    private void startRegister() {
        LoginRequest request = new LoginRequest();
        request.setPhone(mPhoneNumberString);
        request.setCode(mSmsCodeString);
        request.setInviteCode(mInviteCodeString);
        mPresenter.startRegister(request);
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


    Runnable timer = new Runnable() {
        @Override
        public void run() {
            if(isrun) {
                mGetSmsCode.setText("剩余" + (time--) + "秒");
                if (time > 0) {
                    mGetSmsCode.postDelayed(timer, 1000);
                    mGetSmsCode.requestLayout();
                } else {
                    mGetSmsCode.setEnabled(true);
                    mGetSmsCode.setText("重新获取验证码");
                    time = 60;
                }
            }
        }
    };


    //重写返回按钮
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
