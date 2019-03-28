package com.example.hotelmanagementapps;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;


public class LoginActivity extends AppCompatActivity {
    EditText emails,passwords;
    Button login, register;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emails = findViewById(R.id.emailEdit);
        passwords = findViewById(R.id.passEdit);
        login = findViewById(R.id.loginBtn);
        register = findViewById(R.id.registerBtn);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emails.getText().toString();
                String password = passwords.getText().toString();
                loginUser(email,password);
            }
        });
        loadPref();
    }

    private void loadPref() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String preuser = sharedPreferences.getString("email", "");
        String prepass = sharedPreferences.getString("password", "");
        if (preuser.length()>0){
            emails.setText(preuser);
            passwords.setText(prepass);
        }
    }

    private void loginUser(final String email, final String password) {
        class LoginUser extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LoginActivity.this,
                        "Login user","...",false,false);
            }
            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("email",email);
                hashMap.put("password",password);
                RegisterRequest rh = new RegisterRequest();
                String s = rh.sendPostRequest
                        ("http://supersensitive-dabs.000webhostapp.com/logins.php",hashMap);
                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(s.equalsIgnoreCase("success")){
                    Toast.makeText(LoginActivity.this, s, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("email",email);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                }
            }
        }
        LoginUser loginUser = new LoginUser();
        loginUser.execute();
    }

    public void openRegister(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}