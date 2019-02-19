package com.example.navdrawer.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.navdrawer.MainActivity;
import com.example.navdrawer.R;

public class Login extends AppCompatActivity {

    //TODO 4.1 Declaration
    EditText username;
    Button login_but;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //TODO 4.2 Connect The Variable with the id
        username = findViewById(R.id.username_textbox);
        login_but = findViewById(R.id.login_button);

        login_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = login_but.getText().toString().trim(); //When user accidently input space, ignore the space
                if(user.equals(""))
                    username.setError("Please Insert Your Username!");
                else {//TODO 4.3 Session Class

                    //TODO 4.7
                    SessionPreferences mySession = new SessionPreferences(Login.this);
                    mySession.setName(user);

                    startActivity(new Intent(Login.this, MainActivity.class));
                    finish();
                }

            }
        });
    }
    //TODO 4.4 Class Session
    public static class SessionPreferences{
        String KEY_NAME = "NAMA";
        String PREF_NAME = "SIMPAN";

        SharedPreferences myPref;
        SharedPreferences.Editor myEditor;


        public SessionPreferences(Context context)
        {
            myPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        }

        public void setName(String Name)
        {
            myEditor = myPref.edit();
            myEditor.putString(KEY_NAME, Name).apply();
        }

        public String getName(){ return myPref.getString(KEY_NAME, null); }

        public void logOut()
        {
            myEditor = myPref.edit();
            myEditor.clear().commit();
        }
    }
}
