package com.example.msi.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main2Activity extends Activity {
    private TextView text_upload;
    private TextView text_signoutt;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main2);

        text_upload=findViewById(R.id.textView_upload);
        user=FirebaseAuth.getInstance().getCurrentUser();

    }
    public void onClicktext(View v)
    {
        AlertDialog.Builder a_builder = new AlertDialog.Builder(Main2Activity.this);
        a_builder.setMessage("Do you want to Close this App !!!")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
                        Intent mainIntent = new Intent(Main2Activity.this,MainActivity.class);
                        // mainIntent.putExtra("name", 1);

                        startActivity(mainIntent);
                        finish();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }) ;
        AlertDialog alert = a_builder.create();
        alert.setTitle("Alert..!!");
        alert.show();
    }
    public void OnClick(View v) {
        Intent mainIntent = new Intent(Main2Activity.this,Prescription.class);
        // mainIntent.putExtra("name", 1);

        startActivity(mainIntent);

    }
}
