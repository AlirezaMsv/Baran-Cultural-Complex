package com.example.baran21;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterCoach extends AppCompatActivity {
    EditText name , kode , phone , pass ;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registercoach);
        name=findViewById(R.id.edregistername);
        kode=findViewById(R.id.edregisterkode);
        phone=findViewById(R.id.edregisterphone);
        pass=findViewById(R.id.edregisterpass);
        spinner=findViewById(R.id.sprescoach);
    }

    public void register(View view) {
        if (TextUtils.isEmpty(name.getText().toString().trim())||
                TextUtils.isEmpty(kode.getText().toString().trim())||
                TextUtils.isEmpty(phone.getText().toString().trim())||
                TextUtils.isEmpty(pass.getText().toString().trim()))
        {
            Toast.makeText(this, "لظفا همه خانه ها را تکمیل کنید", Toast.LENGTH_SHORT).show();
            return;
        }
        if (kode.getText().toString().trim().length()!=10)
        {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            return;
        }
        if (phone.getText().toString().trim().length()!=11)
        {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pass.getText().toString().trim().length()<8)
        {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            return;
        }
        final ProgressDialog loading = ProgressDialog.show(this, "Registering...", "Please wait...", false, false);
        StringRequest request = new StringRequest(Request.Method.POST, Config.register_Api, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                loading.dismiss();
                Toast.makeText(RegisterCoach.this, s, Toast.LENGTH_SHORT).show();
                if (s.trim().equals("no"))
                {
                    Toast.makeText(RegisterCoach.this, "شخص دیگری با این کد ملی ثبت نام کرده است", Toast.LENGTH_SHORT).show();
                }
                else if (s.trim().equals("ok")) {
                    startActivity(new Intent(RegisterCoach.this , LoginActivity.class));
                    Toast.makeText(RegisterCoach.this, "ثبت نام با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
//                Toast.makeText(LoginActivity.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(RegisterCoach.this, "اتصال به سرور امکان پذیر نمی باشد." +
                        "\n" + "مجددا امتحان کنید", Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name.getText().toString().trim());
                params.put("kode", kode.getText().toString().trim());
                params.put("phone", phone.getText().toString().trim());
                params.put("password", pass.getText().toString().trim());
                params.put("res",(spinner.getSelectedItemId())+"");
                return params;
            }

        };
        RequestQueue requestQueue =
                Volley.newRequestQueue(RegisterCoach.this);
        requestQueue.add(request);
    }
}
