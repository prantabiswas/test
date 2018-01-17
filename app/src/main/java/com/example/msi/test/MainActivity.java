package com.example.msi.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends Activity {


    Button button;
    TextView SignUp;
    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
    //   android.app.ActionBar actionBar=getActionBar();
    //   actionBar.setDisplayShowTitleEnabled(false);
    //   actionBar.setDisplayShowHomeEnabled(false);

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(MainActivity.this, Main2Activity.class));
            finish();
        }

        inputEmail = (EditText) findViewById(R.id.EditText_email);
        inputPassword = (EditText) findViewById(R.id.EditText_pass);
        button = findViewById(R.id.button_login);
        SignUp = findViewById(R.id.textView_signup);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }


                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.

                                if (!task.isSuccessful()) {
                                    // there was an error

                                    Toast.makeText(MainActivity.this, "LogIn Failed..!", Toast.LENGTH_LONG).show();
                                } else {

                                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                //Intent mainIntent = new Intent(MainActivity.this, Main2Activity.class);
                                // mainIntent.putExtra("name", 1);

                                //startActivity(mainIntent);
                            }
                        });


            }

        });


}


    public void OnClick(View v) {
        Intent mainIntent = new Intent(MainActivity.this,SignUp.class);
        // mainIntent.putExtra("name", 1);

        startActivity(mainIntent);
    }
}






