package com.example.intermediatequizapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    static Login INSTANCE;
    SQLiteDatabase db;
    public String Name;
    SQLiteOpenHelper openHelper;
    Button btnLoginLogin;
    EditText etEmail,etPassword;
    Cursor cursor;
    TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        INSTANCE=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        openHelper = new DataBaseHelper(this);
        db = openHelper.getReadableDatabase();
        btnLoginLogin=(Button)findViewById(R.id.btnLoginLogin);
        etEmail=(EditText)findViewById(R.id.etEmaillogin);
        etPassword=(EditText)findViewById(R.id.etPasswordlogin);

        ///////////////////////////////////////////
        signup = findViewById(R.id.signup);

        String text = "Dont have account SIGNUP";

        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                opensignUp();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
                ds.setUnderlineText(false);
            }
        };

        ss.setSpan(clickableSpan1, 18, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        signup.setText(ss);
        signup.setMovementMethod(LinkMovementMethod.getInstance());
        ///////////////////////////////////////////

        /*signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opensignUp();
            }
        });*/
        btnLoginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                Name = email;
                String pass= etPassword.getText().toString();
                if(Name==""||pass==""){
                    Toast.makeText(getApplicationContext(),"ERROR! Email or password is wrong",Toast.LENGTH_LONG).show();
                }
                else {
                    cursor = db.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_NAME + " WHERE " + DataBaseHelper.COL_5 + " =? AND " + DataBaseHelper.COL_4 + " =? ", new String[]{email, pass});
                    if (cursor != null) {
                        if (cursor.getCount() > 0) {
                            cursor.moveToNext();
                            openMcqs();
                            Toast.makeText(getApplicationContext(), "Welcome! Login successfull", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
    }
    private void opensignUp(){
        Intent intent = new Intent(Login.this,MainActivityLogin.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.overshoot_interpolator,android.R.anim.linear_interpolator);
    }
    public static Login getINSTANCE(){
        return INSTANCE;
    }
    public String getName(){
        return this.Name;
    }
    private void openMcqs(){
        Intent intent = new Intent(this,StartingScreenActivity.class);
        startActivity(intent);

    }
}
