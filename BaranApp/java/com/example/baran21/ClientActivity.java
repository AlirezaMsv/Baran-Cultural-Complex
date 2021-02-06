package com.example.baran21;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ClientActivity extends AppCompatActivity{
    static String clientkode;
    TextView app , pre , kodemelli , age , regyear , regmonth , birthyear , birthmonth ,birthday , appdates , predates ;
    JSONObject jsonObject;
    private static final int pic_id = 1;
    int flag=0  , flagggg=0 ;
    ImageView imageView;
    Bitmap bitmap;
    String birthdate , regdate , image ,id;
    Spinner category;
    CheckBox heyAt , shakhe , family , football , volleyball , basketball , pingpong , quran , memorize , computer , sing , art , photography , other , tel , insta , whatsapp , rubika , soroush
            , facebook , Ita , tweeter , bale;
    EditText firstname , lastname ,    phoneNum , homeNum , dadNum , job , grade , reshte , educationplace , address , coach , res
             , familycome , whoinvitesyou , invitenum , describe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        flag=0;
        appdates = findViewById(R.id.appdates);
        predates = findViewById(R.id.txtpredates);
        age=findViewById(R.id.clientage);
        app=findViewById(R.id.appsentnum);
        pre=findViewById(R.id.presentnum);
        describe = findViewById(R.id.clientdescribe);
        imageView=findViewById(R.id.clientprofile);
        category = findViewById(R.id.clientcategory);
        heyAt = findViewById(R.id.clientheyAtreg);
        shakhe = findViewById(R.id.clientshakhereg);
        family = findViewById(R.id.clientfamilyreg);
        football = findViewById(R.id.clientfootball);
        volleyball = findViewById(R.id.clientvolleyball);
        basketball = findViewById(R.id.clientbasketball);
        pingpong = findViewById(R.id.clientpingpong);
        quran = findViewById(R.id.clientquran);
        memorize = findViewById(R.id.clientmemorize);
        computer = findViewById(R.id.clientcomputer);
        sing = findViewById(R.id.clientsing);
        photography = findViewById(R.id.clientphotography);
        art=findViewById(R.id.clientart);
        other = findViewById(R.id.clientother);
        tel = findViewById(R.id.clienttelegram);
        insta = findViewById(R.id.clientinsta);
        rubika = findViewById(R.id.clientrubika);
        whatsapp = findViewById(R.id.clientwhatsapp);
        soroush = findViewById(R.id.clientsoroush);
        facebook = findViewById(R.id.clientfacebook);
        Ita = findViewById(R.id.clientIta);
        tweeter = findViewById(R.id.clienttweeter);
        bale = findViewById(R.id.clientbale);
        firstname = findViewById(R.id.clientfirstname);
        lastname = findViewById(R.id.clientlastname);
        kodemelli = findViewById(R.id.clientkodemelli);
        birthday = findViewById(R.id.clientbirthday);
        birthyear = findViewById(R.id.clientbirthyear);
        birthmonth = findViewById(R.id.clientbirthmonth);
        phoneNum = findViewById(R.id.clientphoneNum);
        homeNum = findViewById(R.id.clienthomeNum);
        dadNum = findViewById(R.id.clientdadNum);
        job = findViewById(R.id.clientjob);
        grade = findViewById(R.id.clientgrade);
        reshte = findViewById(R.id.clientreshte);
        educationplace = findViewById(R.id.clienteducationplace);
        address = findViewById(R.id.clientaddress);
        coach = findViewById(R.id.clientcoach);
        res = findViewById(R.id.clientresponsibility);
        regmonth = findViewById(R.id.clientregmonth);
        regyear = findViewById(R.id.clientregyear);
        familycome = findViewById(R.id.clientfamilycome);
        whoinvitesyou = findViewById(R.id.clientwhoinvitesyou);
        invitenum = findViewById(R.id.clientinvitenum);
        showdata();
    }

    public void changepic(View view) {
       try
        {
            flag=1;
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED)
            {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 1);
                changepic(view);
            }
            else if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
            {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera_intent, pic_id);
            }
        }
        catch (Exception ex)
        {
            startActivity(new Intent(ClientActivity.this , ListActivity.class));
        }
    }
    public void cancel(View view) {
        startActivity(new Intent(ClientActivity.this , ListActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ClientActivity.this , ListActivity.class));
        finish();
    }
    //___________________________________________________________
    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {

        int width = bm.getWidth();

        int height = bm.getHeight();

        float scaleWidth = ((float) newWidth) / width;

        float scaleHeight = ((float) newHeight) / height;

        // create a matrix for the manipulation

        Matrix matrix = new Matrix();

        // resize the bit map

        matrix.postScale(scaleWidth, scaleHeight);

        // recreate the new Bitmap

        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);

        return resizedBitmap;

    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("MissingSuperCall")
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        try
        {
            if (requestCode == pic_id) {
                bitmap = (Bitmap) Objects.requireNonNull(data.getExtras())
                        .get("data");
                bitmap = getResizedBitmap(bitmap , 150 , 150);
                imageView.setImageBitmap(bitmap);
            }
        }
        catch (Exception ex)
        {
            startActivity(new Intent(ClientActivity.this , ListActivity.class));
        }
    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    //________________________________________________________________________
    public void update(View view) {
        if (kodemelli.getText().toString().trim().length()!=10)
        {
            Toast.makeText(this, "کد ملی باید شامل 10 رقم باشد", Toast.LENGTH_SHORT).show();
            return;
        }
        if (phoneNum.getText().toString().trim().length()<11)
        {
            Toast.makeText(this, "شماره نامعتبر است", Toast.LENGTH_SHORT).show();
            return;
        }
        if (regyear.getText().toString().trim().length()!=4||birthyear.getText().toString().trim().length()!=4)
        {
            Toast.makeText(ClientActivity.this, "تاریخ وارد شده نامعتبر است", Toast.LENGTH_SHORT).show();
            return;
        }
        try
        {
            birthdate = birthyear.getText().toString().trim() + "/" + birthmonth.getText().toString().trim() + "/" + birthday.getText().toString().trim();
            regdate = regyear.getText().toString().trim() + "/" + regmonth.getText().toString().trim();
            final ProgressDialog loading = ProgressDialog.show(this, "Inserting...", "Please wait...",
                    false, false);
            StringRequest request = new StringRequest
                    (Request.Method.POST, Config.update_api, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            loading.dismiss();
                            Toast.makeText(ClientActivity.this, s.trim(), Toast.LENGTH_SHORT).show();
                            if (s.trim().equals("ok")) {
                                Toast.makeText(ClientActivity.this, "تغییر با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ClientActivity.this, ListActivity.class));
                                finish();
                            }
                        }}, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Toast.makeText(ClientActivity.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
//                            Toast.makeText(ClientActivity.this, "اتصال به سرور امکان پذیر نمی باشد." +"\n" + "مجددا امتحان کنید", Toast.LENGTH_SHORT).show();
                            loading.dismiss();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    image="";
                    image=getStringImage(bitmap);
                    params.put("id", id);
                    params.put("photo", image);
                    params.put("firstname", firstname.getText().toString().trim());
                    params.put("lastname", lastname.getText().toString().trim());
                    params.put("kodemelli", kodemelli.getText().toString().trim());
                    params.put("birthdate", birthdate);
                    params.put("phoneNum", phoneNum.getText().toString().trim());
                    params.put("homeNum", homeNum.getText().toString().trim());
                    params.put("dadNum", dadNum.getText().toString().trim());
                    params.put("category", category.getSelectedItemId()+"");
                    params.put("job", job.getText().toString().trim());
                    params.put("grade", grade.getText().toString().trim());
                    params.put("reshte", reshte.getText().toString().trim());
                    params.put("educationplace", educationplace.getText().toString().trim());
                    params.put("address", address.getText().toString().trim());
                    if (heyAt.isChecked())
                        params.put("heyAtreg", "1");
                    else
                        params.put("heyAtreg", "0");
                    if (shakhe.isChecked())
                        params.put("shakhereg", "1");
                    else
                        params.put("shakhereg", "0");
                    if (family.isChecked())
                        params.put("familyreg", "1");
                    else
                        params.put("familyreg", "0");
                    params.put("coach", coach.getText().toString().trim());
                    params.put("res", res.getText().toString().trim());
                    params.put("regdate", regdate);
                    params.put("familycome", familycome.getText().toString().trim());
                    params.put("whoInvitesYou", whoinvitesyou.getText().toString().trim());
                    params.put("inviteNum", invitenum.getText().toString().trim());
                    if (football.isChecked())
                        params.put("football", "1");
                    else
                        params.put("football", "0");
                    if (volleyball.isChecked())
                        params.put("volleyball", "1");
                    else
                        params.put("volleyball", "0");
                    if (basketball.isChecked())
                        params.put("basketball", "1");
                    else
                        params.put("basketball", "0");
                    if (pingpong.isChecked())
                        params.put("pingpong", "1");
                    else
                        params.put("pingpong", "0");
                    if (quran.isChecked())
                        params.put("readquran", "1");
                    else
                        params.put("readquran", "0");
                    if (memorize.isChecked())
                        params.put("memorize", "1");
                    else
                        params.put("memorize", "0");
                    if (photography.isChecked())
                        params.put("photography", "1");
                    else
                        params.put("photography", "0");
                    if (computer.isChecked())
                        params.put("computer", "1");
                    else
                        params.put("computer", "0");
                    if (sing.isChecked())
                        params.put("sing", "1");
                    else
                        params.put("sing", "0");
                    if (art.isChecked())
                        params.put("art", "1");
                    else
                        params.put("art", "0");
                    if (other.isChecked())
                        params.put("other", "1");
                    else
                        params.put("other", "0");
                    if (insta.isChecked())
                        params.put("instagram", "1");
                    else
                        params.put("instagram", "0");
                    if (tel.isChecked())
                        params.put("telegram", "1");
                    else
                        params.put("telegram", "0");
                    if (soroush.isChecked())
                        params.put("soroush", "1");
                    else
                        params.put("soroush", "0");
                    if (Ita.isChecked())
                        params.put("ita", "1");
                    else
                        params.put("ita", "0");
                    if (bale.isChecked())
                        params.put("bale", "1");
                    else
                        params.put("bale", "0");
                    if (whatsapp.isChecked())
                        params.put("whatsApp", "1");
                    else
                        params.put("whatsApp", "0");
                    if (tweeter.isChecked())
                        params.put("tweeter", "1");
                    else
                        params.put("tweeter", "0");
                    if (facebook.isChecked())
                        params.put("facebook", "1");
                    else
                        params.put("facebook", "0");
                    if (rubika.isChecked())
                        params.put("rubika", "1");
                    else
                        params.put("rubika", "0");
                    params.put("describe", describe.getText().toString().trim());
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(ClientActivity.this);
            requestQueue.add(request);
        }
        catch (Exception ex)
        {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void showdata()
    {
        Picasso.with(this).load(Config.ip_value + "baranapp/profiles/" + clientkode +".png").into(imageView);
        final ProgressDialog loading = ProgressDialog.show(this, "Inserting...", "Please wait...",
                false, false);
        StringRequest request = new StringRequest
                (Request.Method.POST, Config.clientdetails_api, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        loading.dismiss();
//                        Toast.makeText(ClientActivity.this, s.trim(), Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject jsonObject1 = new JSONObject(s);
                            JSONArray result = jsonObject1.getJSONArray("result");
                            try {
                                jsonObject = result.getJSONObject(0);
                                id=jsonObject.getString("id") ;
                                age.setText(jsonObject.getString("age"));
                                firstname.setText(jsonObject.getString("firstname"));
                                lastname.setText(jsonObject.getString("lastname"));
                                kodemelli.setText(jsonObject.getString("kodemelli"));
                                birthdate=(jsonObject.getString("birthdate"));
                                //______________________________________________---
                                if (birthdate.length()==10)
                                {
                                    char[] chars = birthdate.toCharArray();
                                    // chars = regdate.toCharArray();
                                    StringBuilder yearchar= new StringBuilder();
                                    StringBuilder monthchar= new StringBuilder();
                                    StringBuilder daychar= new StringBuilder();
                                    for (int i=0;i<4;i++)
                                    {
                                        yearchar.append(chars[i]);
                                    }
                                    for (int i=5;i<7;i++)
                                    {
                                        monthchar.append(chars[i]);
                                    }
                                    for (int i=8;i<10;i++)
                                    {
                                        daychar.append(chars[i]);
                                    }
                                    birthyear.setText(yearchar.toString());
                                    birthmonth.setText(monthchar.toString());
                                    birthday.setText(daychar.toString());
                                }
                                else if (birthdate.length()==9)
                                {
                                    char[] chars =birthdate.toCharArray();
                                    //chars = regdate.toCharArray();
                                    StringBuilder yearchar= new StringBuilder();
                                    StringBuilder monthchar= new StringBuilder();
                                    StringBuilder daychar= new StringBuilder();
                                    for (int i=0;i<4;i++)
                                    {
                                        yearchar.append(chars[i]);
                                    }
                                    for (int i=5;i<6;i++)
                                    {
                                        if (chars[i]=='/')
                                            flagggg=1;
                                        monthchar.append(chars[i]);
                                    }
                                    for (int i=7;i<9;i++)
                                    {
                                        if (chars[i]=='/')
                                            flagggg=1;
                                        daychar.append(chars[i]);
                                    }
                                    birthyear.setText(yearchar.toString());
                                    birthmonth.setText(monthchar.toString());
                                    birthday.setText(daychar.toString());
                                }
                                if (flagggg==1&&birthdate.length()==9)
                                {
                                    char[] chars = birthdate.toCharArray();
                                    //chars = regdate.toCharArray();
                                    StringBuilder yearchar= new StringBuilder();
                                    StringBuilder monthchar= new StringBuilder();
                                    StringBuilder daychar= new StringBuilder();
                                    for (int i=0;i<4;i++)
                                    {
                                        yearchar.append(chars[i]);
                                    }
                                    for (int i=5;i<7;i++)
                                    {
                                        monthchar.append(chars[i]);
                                    }
                                    for (int i=8;i<9;i++)
                                    {
                                        daychar.append(chars[i]);
                                    }
                                    birthyear.setText(yearchar.toString());
                                    birthmonth.setText(monthchar.toString());
                                    birthday.setText(daychar.toString());
                                }
                                else if (birthdate.length()==8)
                                {
                                    char[] chars = birthdate.toCharArray();
                                    //chars = regdate.toCharArray();
                                    StringBuilder yearchar= new StringBuilder();
                                    StringBuilder monthchar= new StringBuilder();
                                    StringBuilder daychar= new StringBuilder();
                                    for (int i=0;i<4;i++)
                                    {
                                        yearchar.append(chars[i]);
                                    }
                                    for (int i=5;i<6;i++)
                                    {
                                        if (chars[i]=='/')
                                            flagggg=1;
                                        monthchar.append(chars[i]);
                                    }
                                    for (int i=7;i<8;i++)
                                    {
                                        if (chars[i]=='/')
                                            flagggg=1;
                                        daychar.append(chars[i]);
                                    }
                                    birthyear.setText(yearchar.toString());
                                    birthmonth.setText(monthchar.toString());
                                    birthday.setText(daychar.toString());
                                }
                                //_________________________________________________
                                phoneNum.setText(jsonObject.getString("phoneNum"));
                                homeNum.setText(jsonObject.getString("homeNum"));
                                dadNum.setText(jsonObject.getString("dadNum"));
                                category.setSelection(Integer.parseInt(jsonObject.getString("category").trim()));
                                job.setText(jsonObject.getString("job"));
                                grade.setText(jsonObject.getString("grade"));
                                reshte.setText(jsonObject.getString("reshte"));
                                educationplace.setText(jsonObject.getString("educationplace"));
                                address.setText(jsonObject.getString("address"));
                                if (jsonObject.getString("heyAtreg").equals("1"))
                                {
                                    heyAt.setChecked(true);
                                }
                                if (jsonObject.getString("familyreg").equals("1"))
                                {
                                    family.setChecked(true);
                                }
                                if (jsonObject.getString("shakhereg").equals("1"))
                                {
                                    shakhe.setChecked(true);
                                }
                                coach.setText(jsonObject.getString("coach"));
                                res.setText(jsonObject.getString("res"));
                                regdate=(jsonObject.getString("regdate"));
                                //__________________________________
                                if (regdate.length()==7)
                                {
                                    char[] chars = regdate.toCharArray();
                                    // chars = regdate.toCharArray();
                                    StringBuilder yearchar= new StringBuilder();
                                    StringBuilder monthchar= new StringBuilder();
                                    for (int i=0;i<4;i++)
                                    {
                                        yearchar.append(chars[i]);
                                    }
                                    for (int i=5;i<7;i++)
                                    {
                                        monthchar.append(chars[i]);
                                    }
                                    regyear.setText(yearchar.toString());
                                    regmonth.setText(monthchar.toString());
                                }
                                else if (regdate.length()==6)
                                {
                                    char[] chars = regdate.toCharArray();
                                    //chars = regdate.toCharArray();
                                    StringBuilder yearchar= new StringBuilder();
                                    StringBuilder monthchar= new StringBuilder();
                                    for (int i=0;i<4;i++)
                                    {
                                        yearchar.append(chars[i]);
                                    }
                                    for (int i=5;i<6;i++)
                                    {
                                        monthchar.append(chars[i]);
                                    }
                                    regyear.setText(yearchar.toString());
                                    regmonth.setText(monthchar.toString());
                                }
                                //___________________________________
                                familycome.setText(jsonObject.getString("familycome"));
                                whoinvitesyou.setText(jsonObject.getString("whoInvitesYou"));
                                invitenum.setText(jsonObject.getString("inviteNum"));
                                if (jsonObject.getString("football").equals("1"))
                                {
                                    football.setChecked(true);
                                }
                                if (jsonObject.getString("volleyball").equals("1"))
                                {
                                    volleyball.setChecked(true);
                                }
                                if (jsonObject.getString("basketball").equals("1"))
                                {
                                    basketball.setChecked(true);
                                }
                                if (jsonObject.getString("pingpong").equals("1"))
                                {
                                    pingpong.setChecked(true);
                                }
                                if (jsonObject.getString("readquran").equals("1"))
                                {
                                    quran.setChecked(true);
                                }
                                if (jsonObject.getString("memorize").equals("1"))
                                {
                                    memorize.setChecked(true);
                                }
                                if (jsonObject.getString("photography").equals("1"))
                                {
                                    photography.setChecked(true);
                                }
                                if (jsonObject.getString("computer").equals("1"))
                                {
                                    computer.setChecked(true);
                                }
                                if (jsonObject.getString("sing").equals("1"))
                                {
                                    sing.setChecked(true);
                                }
                                if (jsonObject.getString("art").equals("1"))
                                {
                                    art.setChecked(true);
                                }
                                if (jsonObject.getString("other").equals("1"))
                                {
                                    other.setChecked(true);
                                }
                                if (jsonObject.getString("instagram").equals("1"))
                                {
                                    insta.setChecked(true);
                                }
                                if (jsonObject.getString("telegram").equals("1"))
                                {
                                    tel.setChecked(true);
                                }
                                if (jsonObject.getString("soroush").equals("1"))
                                {
                                    soroush.setChecked(true);
                                }
                                if (jsonObject.getString("ita").equals("1"))
                                {
                                    Ita.setChecked(true);
                                }
                                if (jsonObject.getString("bale").equals("1"))
                                {
                                    bale.setChecked(true);
                                }
                                if (jsonObject.getString("whatsApp").equals("1"))
                                {
                                    whatsapp.setChecked(true);
                                }
                                if (jsonObject.getString("tweeter").equals("1"))
                                {
                                    tweeter.setChecked(true);
                                }
                                if (jsonObject.getString("facebook").equals("1"))
                                {
                                    facebook.setChecked(true);
                                }
                                if (jsonObject.getString("rubika").equals("1"))
                                {
                                    rubika.setChecked(true);
                                }
                                describe.setText(jsonObject.getString("describes"));
                                pre.setText(jsonObject.getString("presennum"));
                                app.setText(jsonObject.getString("appsentnum"));
                                predates.setText(jsonObject.getString("presendates"));
                                appdates.setText(jsonObject.getString("appsentdates"));
                                //_________________________________________________

                                //__________________________
                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }}, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
//                            Toast.makeText(ClientActivity.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(ClientActivity.this, "اتصال به سرور امکان پذیر نمی باشد." +"\n" + "مجددا امتحان کنید", Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("flag", clientkode);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ClientActivity.this);
        requestQueue.add(request);
    }
}