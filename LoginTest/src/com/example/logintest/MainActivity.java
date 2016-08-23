package com.example.logintest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements OnClickListener {

	public EditText et_username;
	// 属性为private 时普通反射获取不到该对象
	// private  EditText et_password;
	public EditText et_password;
	
	public Button bt_login;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        et_username = (EditText) findViewById(R.id.et_username);
    	et_password = (EditText) findViewById(R.id.et_password);
    	
    	bt_login = (Button) findViewById(R.id.bt_login);
    	bt_login.setOnClickListener(this);
    }


    private boolean isCorrectInfo(String username, String password) {
    	// 校验用户名密码是否正确，直接返回true
    	return true;
    }


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_login:
			if(isCorrectInfo(et_username.getText().toString(), et_password.getText().toString())) {
				// 帐号密码校验成功，弹出当前密码
				Toast.makeText(MainActivity.this, "password:"+et_password.getText().toString(), Toast.LENGTH_SHORT).show();
			}
			break;

		default:
			break;
		}
	}
    
}
