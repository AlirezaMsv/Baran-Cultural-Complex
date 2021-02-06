package com.example.baran21;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.alirezaafkar.sundatepicker.interfaces.DateSetListener;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Register extends AppCompatActivity implements DateSetListener {
    private static final int pic_id = 1;
    int flag=0  , c1=0 , c2=0 ;
    ImageView imageView;
    Bitmap bitmap;
    String birthdate , regdate , image , flaggg , age;
    Spinner category;
    CheckBox heyAt , shakhe , family , football , volleyball , basketball , pingpong , quran , memorize , computer , sing , art , photography , other , tel , insta , whatsapp , rubika , soroush
            , facebook , Ita , tweeter , bale;
    EditText firstname , lastname , kodemelli , birthyear , birthmonth , birthday , phoneNum , homeNum , dadNum , job , grade , reshte , educationplace , address , coach , res , regyear ,
            regmonth , familycome , whoinvitesyou , invitenum , describe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        describe = findViewById(R.id.describe);
        imageView=findViewById(R.id.registerpic);
        imageView.setImageResource(R.drawable.ic_account_circle_black_24dp);
        category = findViewById(R.id.category);
        heyAt = findViewById(R.id.heyAtreg);
        shakhe = findViewById(R.id.shakhereg);
        family = findViewById(R.id.familyreg);
        football = findViewById(R.id.football);
        volleyball = findViewById(R.id.volleyball);
        basketball = findViewById(R.id.basketball);
        pingpong = findViewById(R.id.pingpong);
        quran = findViewById(R.id.quran);
        memorize = findViewById(R.id.memorize);
        computer = findViewById(R.id.computer);
        sing = findViewById(R.id.sing);
        photography = findViewById(R.id.photography);
        art=findViewById(R.id.art);
        other = findViewById(R.id.other);
        tel = findViewById(R.id.telegram);
        insta = findViewById(R.id.insta);
        rubika = findViewById(R.id.rubika);
        whatsapp = findViewById(R.id.whatsapp);
        soroush = findViewById(R.id.soroush);
        facebook = findViewById(R.id.facebook);
        Ita = findViewById(R.id.Ita);
        tweeter = findViewById(R.id.tweeter);
        bale = findViewById(R.id.bale);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        kodemelli = findViewById(R.id.kodemelli);
        birthday = findViewById(R.id.birthday);
        birthyear = findViewById(R.id.birthyear);
        birthmonth = findViewById(R.id.birthmonth);
        phoneNum = findViewById(R.id.phoneNum);
        homeNum = findViewById(R.id.homeNum);
        dadNum = findViewById(R.id.dadNum);
        job = findViewById(R.id.job);
        grade = findViewById(R.id.grade);
        reshte = findViewById(R.id.reshte);
        educationplace = findViewById(R.id.educationplace);
        address = findViewById(R.id.address);
        coach = findViewById(R.id.coach);
        res = findViewById(R.id.responsibility);
        regmonth = findViewById(R.id.regmonth);
        regyear = findViewById(R.id.regyear);
        familycome = findViewById(R.id.familycome);
        whoinvitesyou = findViewById(R.id.whoinvitesyou);
        invitenum = findViewById(R.id.invitenum);
    }

    public void takepic(View view) {
        try
        {
            flag=1;
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED)
            {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 1);
                takepic(view);
            }
            else if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
            {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera_intent, pic_id);
            }
        }
        catch (Exception ex)
        {
            startActivity(new Intent(Register.this , MainPage.class));
        }
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
            startActivity(new Intent(Register.this , MainPage.class));
        }
    }
    public void cancelregister(View view) {
        finish();
        startActivity(new Intent(Register.this , MainPage.class));
    }

    public void register(View view) {
        if ((TextUtils.isEmpty(firstname.getText().toString().trim()))
                || (TextUtils.isEmpty(lastname.getText().toString().trim()))
                || (TextUtils.isEmpty(phoneNum.getText().toString().trim()))
                || (!heyAt.isChecked() && !shakhe.isChecked() && !family.isChecked())
                || (TextUtils.isEmpty(kodemelli.getText().toString().trim())))
        {
            Toast.makeText(this, "لطفا همه خانه های اصلی را کامل کنید", Toast.LENGTH_SHORT).show();
            return;
        }
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
            Toast.makeText(Register.this, "تاریخ وارد شده نامعتبر است", Toast.LENGTH_SHORT).show();
            return;
        }
        try
        {
            birthdate = birthyear.getText().toString().trim() + "/" + birthmonth.getText().toString().trim() + "/" + birthday.getText().toString().trim();
            regdate = regyear.getText().toString().trim() + "/" + regmonth.getText().toString().trim();
            final ProgressDialog loading = ProgressDialog.show(this, "Inserting...", "Please wait...",
                    false, false);
            StringRequest request = new StringRequest
                    (Request.Method.POST, Config.registerStu_Api, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            loading.dismiss();
                            Toast.makeText(Register.this, s.trim(), Toast.LENGTH_SHORT).show();
                            if (s.trim().equals("no"))
                            {
                                Toast.makeText(Register.this, "شخص دیگری با این کد ملی ثبت نام کرده است", Toast.LENGTH_SHORT).show();
                            }
                            else if (s.trim().equals("ok")) {
                                Toast.makeText(Register.this, "ثبت نام با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this, MainPage.class));
                            }
                        }}, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Toast.makeText(Register.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
//                            Toast.makeText(Register.this, "اتصال به سرور امکان پذیر نمی باشد." +"\n" + "مجددا امتحان کنید", Toast.LENGTH_SHORT).show();
//                            loading.dismiss();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    image="";
                    if (flag==0)
                        bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.profile);
                    image = getStringImage(bitmap);
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
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy,MM,dd");
                    String formattedDate = format.format(c.getTime());
                    int[] PersianDateInt = gregorian_to_jalali(Integer.parseInt(formattedDate.substring(0, 4)),
                            Integer.parseInt(formattedDate.substring(5, 7)),
                            Integer.parseInt(formattedDate.substring(8, 10))
                    );
                    String todaysdate = PersianDateInt[0] +"/" + PersianDateInt[1] + "/" + PersianDateInt[2];
                    if (todaysdate.equals(flaggg))
                    {
                        flaggg = "[" + flaggg + "]" ;
                        params.put("presendates", flaggg);
                        params.put("appsentdates", "");
                        params.put("presennum", "1");
                        params.put("appsentnum", "0");
                    }
                    else
                    {
                        params.put("presendates", "");
                        params.put("appsentdates", "");
                        params.put("presennum", "0");
                        params.put("appsentnum", "0");
                    }
                    age = (PersianDateInt[0] - Integer.parseInt(birthyear.getText().toString().trim()))+"";
                    params.put("age", age);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(Register.this);
            requestQueue.add(request);
        }
        catch (Exception ex)
        {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    public void opencalenderbirth(View view) {
        c1=1;
        new com.alirezaafkar.sundatepicker.DatePicker.Builder()
                .build(this).show(getSupportFragmentManager(), "");
    }

    public void opencalenderreg(View view) {
        c2=1;
        new com.alirezaafkar.sundatepicker.DatePicker.Builder()
                .build(this).show(getSupportFragmentManager(), "");
    }

    @Override
    public void onDateSet(int i, @Nullable Calendar calendar, int day, int month, int year) {
        if (c1==1)
        {
            birthday.setText(day+"");
            birthmonth.setText(month+"");
            birthyear.setText(year+"");
        }
        else if (c2==1)
        {
            regyear.setText(year+"");
            regmonth.setText(month+"");
            flaggg = year +"/" + month + "/" + day ;
        }
        c1=c2=0;
    }
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
    public static int[] gregorian_to_jalali(int gy, int gm, int gd){
        int[] g_d_m = {0,31,59,90,120,151,181,212,243,273,304,334};
        int jy;
        if(gy>1600){
            jy=979;
            gy-=1600;
        }else{
            jy=0;
            gy-=621;
        }
        int gy2 = (gm > 2)?(gy + 1):gy;
        int days = (365 * gy) + ((int)((gy2 + 3) / 4)) - ((int)((gy2 + 99) / 100)) + ((int)((gy2 + 399) / 400)) - 80 + gd + g_d_m[gm - 1];
        jy += 33 * ((int)(days / 12053));
        days %= 12053;
        jy += 4 * ((int)(days / 1461));
        days %= 1461;
        if(days > 365){
            jy+=(int)((days-1)/365);
            days=(days-1)%365;
        }
        int jm = (days < 186)?1 + (int)(days / 31):7 + (int)((days - 186) / 30);
        int jd = 1 + ((days < 186)?(days % 31):((days - 186) % 30));
        int[] out = {jy,jm,jd};
        return out;
    }
}
