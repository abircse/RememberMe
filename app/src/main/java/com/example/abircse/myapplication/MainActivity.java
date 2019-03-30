package com.example.abircse.myapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText PHONENO,PASSWORD;
    Button LOGIN;
    CheckBox REMEMBERME;

    SharedPreferences userPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PHONENO = findViewById(R.id.phoneno);
        PASSWORD = findViewById(R.id.password);
        LOGIN = findViewById(R.id.loginbutton);
        REMEMBERME = findViewById(R.id.remember);
        userPreferences = getSharedPreferences("User Authentication",MODE_PRIVATE);
        editor = userPreferences.edit();

        // data exist or not
        if (userPreferences.contains("phone") && userPreferences.contains("password"))
        {
            Toast.makeText(getApplicationContext(), "Going to main activity", Toast.LENGTH_LONG).show();
        }

        PHONENO.setText(userPreferences.getString("phone",null));
        PASSWORD.setText(userPreferences.getString("password",null));

        // IF WE WANT CLEAR
        // first editor.clear
        // then editor.commit



        LOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (PHONENO.getText().toString().trim() == null && PASSWORD.getText().toString().trim() == null)
                {
                    Toast.makeText(getApplicationContext(),"Fill Up all field", Toast.LENGTH_LONG).show();
                }
                else
                {
                    if (REMEMBERME.isChecked())
                    {
                        String phone = PHONENO.getText().toString();
                        String password = PASSWORD.getText().toString();
                        editor.putString("phone",phone);
                        editor.putString("password",password);
                        editor.commit();
                        Toast.makeText(getApplicationContext(), "Phone no & Password Saved", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        editor.clear();
                        editor.commit();
                        Toast.makeText(getApplicationContext(), "No Information Saved", Toast.LENGTH_LONG).show();


                    }
                }
            }
        });



    }
}
