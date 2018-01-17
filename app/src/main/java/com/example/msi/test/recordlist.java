package com.example.msi.test;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class recordlist extends Activity {
    private ListView listView;
    private FirebaseUser user;
    private ArrayList<String > medicine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_recordlist);
        setListView();
        medicine=new ArrayList<>();
        user= FirebaseAuth.getInstance().getCurrentUser();
        String UserID=user.getUid();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Prescription").child(UserID);
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                        collectdata((Map<String,Object>) dataSnapshot.getValue());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });
    }
    public void setListView(){
        listView=(ListView)findViewById(R.id.listView);


    }
    private void collectdata(Map<String,Object> users) {

        ArrayList<Long> medicines = new ArrayList<>();

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()) {

            //Get user map
            Map singleUser = (Map) entry.getValue();
            //Get phone field and append to list
            medicines.add((Long) singleUser.get("medicines"));
        }

        System.out.println(medicines.toString());
    }


    }
