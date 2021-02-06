package com.example.baran21;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class RollcallActivity extends AppCompatActivity {
    RecyclerView recyclerView ;
    ArrayList<Client> clientlist = new ArrayList<>();
    String responseString;
    EditText firstname , lastname , kode;
    int flagse=0 , spinnerid=0;
    String firstnamese="" , lastnamese="" , kodese="";
    Spinner spinner;
    Button search , cancel;
    static String date;
    static int istoday=0;
    AlertDialog alertDialog;
    static RollcallActivity instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_rollcall);
        recyclerView = findViewById(R.id.lstrollcall);
        if (istoday==1)
            getdatatoday();
        else
            getdatapast();
    }

    void getdatatoday()
    {
        final StringRequest request = new StringRequest
                (Request.Method.POST, Config.rollcalllist_api, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
//                            Toast.makeText(RollcallActivity.this, s, Toast.LENGTH_SHORT).show();
                            showJSON(s);
                        }
                        catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(RollcallActivity.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
//                        Toast.makeText(RollcallActivity.this, "اتصال به سرور امکان پذیر نمی باشد." +
//                                "\n" + "مجددا امتحان کنید", Toast.LENGTH_SHORT).show();
                    }
                }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("date", date);
                params.put("flag", flagse+"");
                params.put("firstname", firstnamese);
                params.put("lastname", lastnamese);
                params.put("kodemelli", kodese);
                params.put("category", spinnerid+"");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(RollcallActivity.this);
        requestQueue.add(request);
    }
    private void getdatapast()
    {
        final StringRequest request = new StringRequest
                (Request.Method.POST, Config.rollcalllistpast_api, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
//                            Toast.makeText(RollcallActivity.this, s, Toast.LENGTH_SHORT).show();
                            showJSONpast(s);
                        }
                        catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(RollcallActivity.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
//                        Toast.makeText(RollcallActivity.this, "اتصال به سرور امکان پذیر نمی باشد." +
//                                "\n" + "مجددا امتحان کنید", Toast.LENGTH_SHORT).show();
                    }
                }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("date", date);
                params.put("flag", flagse+"");
                params.put("firstname", firstnamese);
                params.put("lastname", lastnamese);
                params.put("kodemelli", kodese);
                params.put("category", spinnerid+"");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(RollcallActivity.this);
        requestQueue.add(request);
    }

    //_________________________________________________________________________________________
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
                    String kodemellli = (jsonObject.getString("kodemelli"));
                    String flagpresent = (jsonObject.getString("flag"));
                    Client client = new Client(firstname , lastname , kodemellli , flagpresent);
                    clientlist.add(client);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (clientlist.size() > 0) {
                RoolCallAdapter radapter = new RoolCallAdapter(clientlist , this);
                recyclerView.setLayoutManager(new LinearLayoutManager(RollcallActivity.this));
                recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext() ,
                        LinearLayoutManager.VERTICAL));
                recyclerView.setAdapter(radapter);
            }
            else if (clientlist.size() == 0)
            {
                Toast.makeText(RollcallActivity.this, "داده ای برای نمایش وجود ندارد", Toast.LENGTH_SHORT).show();
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void showJSONpast(String response) throws ParseException {
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
                    String flagpresent = (jsonObject.getString("flag"));
                    Client client = new Client(firstname , lastname , kodemelli,flagpresent);
                    clientlist.add(client);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (clientlist.size() > 0) {
                RoolCallAdapter radapter = new RoolCallAdapter(clientlist , this);
                recyclerView.setLayoutManager(new LinearLayoutManager(RollcallActivity.this));
                recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext() ,
                        LinearLayoutManager.VERTICAL));
                recyclerView.setAdapter(radapter);
            }
            else if (clientlist.size() == 0)
            {
                Toast.makeText(RollcallActivity.this, "داده ای برای نمایش وجود ندارد", Toast.LENGTH_SHORT).show();
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void search(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View v_alert = getLayoutInflater().inflate(R.layout.aler_rollcall_search, null);
        firstname = v_alert.findViewById(R.id.alertsearchrollcallfirstname);
        lastname = v_alert.findViewById(R.id.alertsearchrollcalllastname);
        kode = v_alert.findViewById(R.id.alertsearchrollcallkode);
        search = v_alert.findViewById(R.id.alertrollcallsearch);
        cancel = v_alert.findViewById(R.id.alertrollcallcancel);
        spinner=v_alert.findViewById(R.id.spinnersearchrollcallalert);
        builder.setView(v_alert);
        alertDialog = builder.create();
        alertDialog.show();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagse=0;
                firstnamese="";
                lastnamese="";
                kodese="";
                spinnerid=0;
                alertDialog.dismiss();
                if (istoday==1)
                    getdatatoday();
                else
                    getdatapast();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagse =1;
                firstnamese = firstname.getText().toString().trim();
                lastnamese = lastname.getText().toString().trim();
                kodese = kode.getText().toString().trim();
                spinnerid = (int)spinner.getSelectedItemId();
                alertDialog.dismiss();
                if (istoday==1)
                    getdatatoday();
                else
                    getdatapast();
//              //________________________________________________________________
            }
        });
    }
    public static RollcallActivity getInstance()
    {
        return instance;
    }
}
