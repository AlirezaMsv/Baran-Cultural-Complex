package com.example.baran21;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;



public class ListActivity extends AppCompatActivity {
    ArrayList<Client> clientlist = new ArrayList<Client>();
    String responseString;
    RecyclerView recyclerView;
    Spinner spinner;

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ListActivity.this,MainPage.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list2);
        recyclerView = findViewById(R.id.list);
        spinner=findViewById(R.id.choosegroup);

    }

    public void showvoid(View view) {
        final StringRequest request = new StringRequest
                (Request.Method.POST, Config.list_Api, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
//                            Toast.makeText(ListActivity.this, s, Toast.LENGTH_SHORT).show();
                            showJSON(s);
                        }
                        catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
//                        Toast.makeText(ListActivity.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(ListActivity.this, "اتصال به سرور امکان پذیر نمی باشد." +
                                "\n" + "مجددا امتحان کنید", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("flag", spinner.getSelectedItemId()+"");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ListActivity.this);
        requestQueue.add(request);
    }
    private void showJSON(String response) throws ParseException {
        clientlist.clear();
        responseString = response;
        try {
            JSONObject jsonObject1 = new JSONObject(response);
            JSONArray result = jsonObject1.getJSONArray("result");
            for (int i = 0; i < result.length(); i++) {
                try {

                    JSONObject jsonObject = result.getJSONObject(i);
                    String firstname = (jsonObject.getString("firstname"));
                    String lastname = (jsonObject.getString("lastname"));
                    String kodemelli = (jsonObject.getString("kodemelli"));
                    String res = (jsonObject.getString("res"));
                    Client o = new Client(firstname , lastname , kodemelli , res);
                    clientlist.add(o);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (clientlist.size() > 0) {
                Adapter adapter = new Adapter(clientlist , this);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext() ,
                        LinearLayoutManager.VERTICAL));
                recyclerView.setAdapter(adapter);
            }
            else if (clientlist.size() == 0)
            {
                Toast.makeText(ListActivity.this, "داده ای برای نمایش وجود ندارد", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
