package com.example.baran21;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText kode , pass;
    public static String kodemelli;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kode = findViewById(R.id.edloginkode);
        pass = findViewById(R.id.edloginpass);
    }

    public void gotoregistercoach(View view) {
        startActivity(new Intent(LoginActivity.this , RegisterCoach.class));
    }

    public void loginvoid(View view) {
        if (TextUtils.isEmpty(kode.getText().toString().trim())||
                TextUtils.isEmpty(pass.getText().toString().trim()))
        {
            Toast.makeText(this, "لظفا همه خانه ها را تکمیل کنید", Toast.LENGTH_SHORT).show();
            return;
        }
        final ProgressDialog loading = ProgressDialog.show(this, "Logging in...", "Please wait...", false, false);
        StringRequest request = new StringRequest(Request.Method.POST, Config.login_Api, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                loading.dismiss();
                Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
                if (s.trim().equals("ok")) {
                    //Toast.makeText(LoginActivity.this, "success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this , MainPage.class));
                    kodemelli=kode.getText().toString().trim();
                } else {
                    Toast.makeText(LoginActivity.this, "نام کاربری یا گذرواژه شما اشتباه است", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(LoginActivity.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(LoginActivity.this, "اتصال به سرور امکان پذیر نمی باشد." +
//                        "\n" + "مجددا امتحان کنید", Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", kode.getText().toString().trim());
                params.put("password", pass.getText().toString().trim());
                return params;
            }

        };
        RequestQueue requestQueue =
                Volley.newRequestQueue(LoginActivity.this);
        requestQueue.add(request);
    }
}
