package com.example.baran21;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainPage extends AppCompatActivity {
    public static String resp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    }

    public void gotoregisterclient(View view) {
        startActivity(new Intent(MainPage.this , Register.class));
    }

    public void gotolist(View view) {
        startActivity(new Intent(MainPage.this , ListActivity.class));
    }

    public void gotomeeting(View view) {
        startActivity(new Intent(MainPage.this , MeetingsActivity.class));
    }

    public void coachnum(View view) {
        final ProgressDialog loading = ProgressDialog.show(this, "Searching...", "Please wait...", false, false);
        StringRequest request = new StringRequest(Request.Method.POST, Config.coachnum_api, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                loading.dismiss();
                Toast.makeText(MainPage.this, s, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
//                Toast.makeText(MainPage.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainPage.this, "اتصال به سرور امکان پذیر نمی باشد." +
                        "\n" + "مجددا امتحان کنید", Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return null;
            }

        };
        RequestQueue requestQueue =
                Volley.newRequestQueue(MainPage.this);
        requestQueue.add(request);
    }

    public void restoast(View view) {
        final ProgressDialog loading = ProgressDialog.show(this, "Searching...", "Please wait...", false, false);
        StringRequest request = new StringRequest(Request.Method.POST, Config.res_api, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                loading.dismiss();
                //Toast.makeText(MainPage.this, s, Toast.LENGTH_SHORT).show();
                resp=s;
                int a = Integer.parseInt(s.trim());
                String myString = getResources().getStringArray(R.array.rescoach)[a];
                Toast.makeText(MainPage.this, myString, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
//                Toast.makeText(MainPage.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainPage.this, "اتصال به سرور امکان پذیر نمی باشد." +
                        "\n" + "مجددا امتحان کنید", Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("kodemelli", LoginActivity.kodemelli);
                return params;
            }

        };
        RequestQueue requestQueue =
                Volley.newRequestQueue(MainPage.this);
        requestQueue.add(request);
    }

    public void clientnum(View view) {
        final ProgressDialog loading = ProgressDialog.show(this, "Searching...", "Please wait...", false, false);
        StringRequest request = new StringRequest(Request.Method.POST, Config.clientnum_api, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                loading.dismiss();
                Toast.makeText(MainPage.this, s, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
//                Toast.makeText(MainPage.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainPage.this, "اتصال به سرور امکان پذیر نمی باشد." +
                        "\n" + "مجددا امتحان کنید", Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return null;
            }

        };
        RequestQueue requestQueue =
                Volley.newRequestQueue(MainPage.this);
        requestQueue.add(request);
    }
}
