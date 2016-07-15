package com.techHome.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.neopixl.pixlui.components.edittext.EditText;
import com.neopixl.pixlui.components.textview.TextView;
import com.techHome.R;
import com.techHome.asynctasks.HitJSPService;
import com.techHome.asynctasks.LoginAsyncTask;
import com.techHome.asynctasks.TaskCompleted;
import com.techHome.dto.LoginDTO;
import com.techHome.dto.MessageCustomDialogDTO;
import com.techHome.ui.SnackBar;
import com.techHome.util.NetworkCheck;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Dell on 4/14/2016.
 */

//Login page

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.etUsername)
    EditText etUsername;
    @Bind(R.id.etPassword)
    EditText etPassword;
    @Bind(R.id.btnLogin)
    Button btnLogin;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    /* @InjectView(R.id.txtRegister)
     TextView txtRegister;*/
    @Bind(R.id.txtForgotPassword)
    TextView txtForgotPassword;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp=getSharedPreferences("TechHomeLogin", Context.MODE_PRIVATE);
        populate();
    }

    private void populate() {
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.login);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            btnLogin.setBackgroundResource(R.drawable.ripple);
        }

        //logging into the application and clearing the flags
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               authenticate();
            }
        });

        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });

        /*txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });*/

    }
    public void authenticate()
    {
        try {
            new HitJSPService(this, null, new TaskCompleted() {

                @Override
                public void onTaskCompleted(String result, int resultType) {
                    try {
                        JSONObject jo = new JSONObject(result);
                        JSONArray ja = jo.getJSONArray("result");
                        JSONObject jo1 = ja.getJSONObject(0);
                        sp.edit().putString("Name", jo1.getString("name")).commit();
                        sp.edit().putString("Mobile", jo1.getString("mobile")).commit();
                        sp.edit().putInt("Id", Integer.parseInt(jo1.getString("id"))).commit();
                        sp.edit().putBoolean("isTrue", true).commit();
                        Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        // TODO: handle exception
                        Toast.makeText(getApplicationContext(), "Invalid Details.", Toast.LENGTH_SHORT).show();
                    }

                }
            }, "http://techhome.net16.net/login.php?mobile=" + etUsername.getText().toString().trim() + "&password=" + etPassword.getText().toString().trim(), 1).execute();
        }catch (Exception e){ Toast.makeText(getApplicationContext(), "Invalid character found", Toast.LENGTH_SHORT).show();}
    }
}
