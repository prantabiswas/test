package com.example.msi.test;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Prescription extends Activity {

    private EditText problem_name,date_of_pres, blood, weightP, doctor_nameD, adviceD,medicine,dose,day;
    private FirebaseAuth auth;
    DatabaseReference databaseReference;
    private String UserID;
    private Button button,button_add;
    ArrayList<Medicine> medicines;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_prescription);

        auth=FirebaseAuth.getInstance();
        medicines=new ArrayList<>();
        if (auth.getCurrentUser() != null) {
            UserID=auth.getCurrentUser().getUid().toString();
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Prescription").child(UserID);
        problem_name= findViewById(R.id.editText_problem);
        date_of_pres= findViewById(R.id.editText_date);
        blood= findViewById(R.id.editText_bp);
        weightP= findViewById(R.id.editText_weight);
        problem_name= findViewById(R.id.editText_problem);
        doctor_nameD= findViewById(R.id.editText_doctorname);
        adviceD= findViewById(R.id.editText_advice);
        button= findViewById(R.id.button_upload);
        button_add=(Button)findViewById(R.id.button_add);
        medicine=(EditText)findViewById(R.id.editText_medicinename);
        dose=(EditText)findViewById(R.id.editText_dose);
        day=(EditText)findViewById(R.id.editText_day);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddPrescription();
            }
        });
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMedicines();
                Toast.makeText(Prescription.this,"medicine added", Toast.LENGTH_LONG).show();
                medicine.setText("");
                dose.setText("");
                day.setText("");
            }
        });
    }
    public void AddPrescription(){
        String name=problem_name.getText().toString().trim();
        String date=date_of_pres.getText().toString().trim();
        String bp=blood.getText().toString();
        String weight=weightP.getText().toString();
        String doctor=doctor_nameD.getText().toString();
        String advice=adviceD.getText().toString();

        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(date) ){
            String ID=databaseReference.push().getKey();
            Makeprescription makeprescription=new Makeprescription(UserID,name,date,bp,weight,doctor,advice,medicines);
            databaseReference.child(ID).setValue(makeprescription);

            Toast.makeText(Prescription.this,"Info Added!",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(this,Main2Activity.class);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }
    public void addMedicines(){
        String name=medicine.getText().toString();
        String mdose=dose.getText().toString();
        String mday=day.getText().toString();
        Medicine e=new Medicine(name,mdose,mday);
        medicines.add(e);

    }
}
