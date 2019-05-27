package com.example.itsoftware.newwanandroid.login;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.itsoftware.newwanandroid.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout til_account;
    TextInputLayout til_password;
    TextInputLayout til_r_password;
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        til_account=findViewById(R.id.til_account);
        til_password=findViewById(R.id.til_password);
        til_r_password=findViewById(R.id.til_r_password);
        btn_register=findViewById(R.id.btn_register);

        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                String account  = til_account.getEditText().getText().toString();
                String password = til_password.getEditText().getText().toString();
                String rPassword= til_r_password.getEditText().getText().toString();

                til_account.setErrorEnabled(false);
                til_password.setErrorEnabled(false);

                //验证用户名和密码
                if(validateAccount(account)&&validatePassword(password) && validateRPassword(password,rPassword)){
                    Toast.makeText(RegisterActivity.this,"注册成功", Toast.LENGTH_LONG).show();
                }
                return;
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
     * 验证密码
     * @param password
     * @return
     */
    private boolean validatePassword(String password) {
        if (TextUtils.isEmpty(password)) {
            showError(til_password,"密码不能为空");
            return false;
        }

        if (password.length() < 6 || password.length() > 18) {
            showError(til_password,"密码长度为6-18位");
            return false;
        }

        return true;
    }


    /**
     * 验证密码是否一致
     * @param password
     * @return
     */
    private boolean validateRPassword(String password,String rPassword) {
        if (TextUtils.isEmpty(rPassword)) {
            showError(til_r_password,"密码不能为空");
            return false;
        }

        if (rPassword.length() < 6 || rPassword.length() > 18) {
            showError(til_r_password,"密码长度为6-18位");
            return false;
        }

        if (!password.equals(rPassword)) {
            showError(til_r_password,"两次密码不一致");
            return false;
        }
        return true;
    }

}
