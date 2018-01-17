package com.example.msi.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Medical_File extends Activity {

    private EditText editText_name,editText_dob,editText_number;
    Spinner spinner;
    Button button;
    DatabaseReference databaseReference;
    private FirebaseAuth auth;
    private String UserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_medical_file);

        databaseReference = FirebaseDatabase.getInstance().getReference("Info");
        auth=FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            UserID=auth.getCurrentUser().getUid().toString();
        }

        editText_name = (EditText) findViewById(R.id.editText_problem);
        editText_dob = (EditText) findViewById(R.id.editText_dob);
        editText_number = (EditText) findViewById(R.id.editText_number);
        spinner = (Spinner) findViewById(R.id.spinner);
        button = (Button) findViewById(R.id.button_submit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddInfo();
            }
        });
    }
        public void AddInfo(){
            String name=editText_name.getText().toString().trim();
            String gender=spinner.getSelectedItem().toString();
            String dob=editText_dob.getText().toString().trim();
            String number=editText_number.getText().toString().trim();

            if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(number) ){
                Info info=new Info(UserID,name,dob,gender,number);
                databaseReference.child(UserID).setValue(info);
                editText_name.setText("");
                editText_number.setText("");
                editText_dob.setText("");
                Toast.makeText(Medical_File.this,"Info Added!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(this,Main2Activity.class);
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
            }
    }

}
