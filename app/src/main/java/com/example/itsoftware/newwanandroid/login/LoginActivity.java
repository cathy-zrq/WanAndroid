package com.example.itsoftware.newwanandroid.login;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.itsoftware.newwanandroid.R;
import com.example.itsoftware.newwanandroid.sms.SMSMethod;

import java.util.Random;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent;
    TextView  textView;
    TextInputLayout  til_account;
    TextInputLayout til_password;
    Button  btnLogin;
    private TimeCount time;
    private Button btnSendMessage;
    private String verifyCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        til_account=findViewById(R.id.til_account);
        til_password=findViewById(R.id.til_password);
        btnLogin=findViewById(R.id.btn_login);

        textView =findViewById(R.id.txt_register);
        textView.setOnClickListener(this);
        btnLogin.setOnClickListener(this);


        time = new TimeCount(60000, 1000);
        btnSendMessage=(Button) findViewById(R.id.send_Message);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_register:
                intent=new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                String account = til_account.getEditText().getText().toString();
                String password = til_password.getEditText().getText().toString();

                til_account.setErrorEnabled(false);
                til_password.setErrorEnabled(false);

                //验证用户名和密码
                if(validateAccount(account)&&validatePassword(password)){
                    Toast.makeText(LoginActivity.this,"登录成功", Toast.LENGTH_LONG).show();
                }
                break;
                default:
                    break;
        }
    }

    /**
     * 显示错误提示，并获取焦点
     * @param textInputLayout
     * @param error
     */
    private void showError(TextInputLayout textInputLayout, String error){
        textInputLayout.setError(error);
        textInputLayout.getEditText().setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }



    /**
     * 验证用户名
     * @param account
     * @return
     */
    private boolean validateAccount(String account){
        if(TextUtils.isEmpty(account)){
            showError(til_account,"用户名不能为空");
            return false;
        }
        return true;
    }

    /**
     * 验证码校验
     * @param password
     * @return
     */
    private boolean validatePassword(String password) {
        if (TextUtils.isEmpty(password)) {
            showError(til_password,"验证码不能为空");
            return false;
        }
        return true;
    }

    public void  sendTextMessage(View view){
        time.start();
        verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);//生成短信验证码
        SMSMethod.getInstance(this).SendMessage(til_account.getEditText().getText().toString(),"您的验证码为:" + verifyCode+"，该码有效期为24小时，该码只能使用一次！【短信签名】");
    }


    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
         //   btnSendMessage.setBackgroundColor(Color.parseColor("#B6B6D8"));
            btnSendMessage.setClickable(false);
            btnSendMessage.setText("("+millisUntilFinished / 1000 +") s后可重新发送");
        }

        @Override
        public void onFinish() {
            btnSendMessage.setText("重新获取");
            btnSendMessage.setClickable(true);
       //     btnSendMessage.setBackgroundColor(Color.parseColor("#4EB84A"));

        }

    }


    @Override
    protected void onPause() {
        SMSMethod.getInstance(this).unregisterReceiver();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
