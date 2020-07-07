package com.example.intermediatequizapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityLogin extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    public String Name;
    SQLiteDatabase db;
    TextView login;
    Button btnreg;
    EditText etFN,etLN,etPassword,etEmail,etPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        setContentView(R.layout.activity_main_login);

        ////////////////////////////////////////////
        login = findViewById(R.id.login);

        String text = "Already have an account SignIn";

        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                openLogin();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
                ds.setUnderlineText(false);
            }
        };

        ss.setSpan(clickableSpan1, 25, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        login.setText(ss);
        login.setMovementMethod(LinkMovementMethod.getInstance());
        ////////////////////////////////////////////

        openHelper=new DataBaseHelper(this);
        btnreg=findViewById(R.id.btnreg);
        etEmail=findViewById(R.id.etEmail);
        etFN=findViewById(R.id.etFirstName);
        etLN=findViewById(R.id.etLastName);
        etPassword=findViewById(R.id.etPassword);
        etPhone=findViewById(R.id.etPhone);
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=openHelper.getWritableDatabase();

                if(etEmail.getText()==null||etPassword.getText()==null||etFN.getText()==null||
                        etLN.getText()==null){
                    Toast.makeText(getApplicationContext(),"Email and Password and Name is necessary!"
                            ,Toast.LENGTH_LONG).show();
                }
                String fname=etFN.getText().toString();
                String lname=etLN.getText().toString();
                Name=fname+" "+lname;
                String email=etEmail.getText().toString();
                String password=etPassword.getText().toString();
                String phone=etPhone.getText().toString();
                ////////////////////////////////////////////////
                if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)||TextUtils.isEmpty(fname)||TextUtils.isEmpty(lname)||TextUtils.isEmpty(phone)) {
                    Toast.makeText(getApplicationContext(),"Email,Password,Phone no. and Name " +
                            "is necessary!",Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    ////////////////////////////////////////////////
                    insertData(fname, lname, password, email, phone);
                    ////////////////////////////////////////////////////
                    Toast.makeText(getApplicationContext(), "registered successfully",
                            Toast.LENGTH_LONG).show();
                    openLogin();
                }
            }
        });
    }

    public void openLogin(){
        Intent intent = new Intent(MainActivityLogin.this,Login.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
    public void insertData(String fname,String lname,String password,String email,String phone){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.COL_2,fname);
        contentValues.put(DataBaseHelper.COL_3,lname);
        contentValues.put(DataBaseHelper.COL_4,password);
        contentValues.put(DataBaseHelper.COL_5,email);
        contentValues.put(DataBaseHelper.COL_6,phone);
        long id=db.insert(DataBaseHelper.TABLE_NAME,null,contentValues);
    }
}
