package com.example.antony.firebaeandroid;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    Button signout;

    ListView listViewItems;
    ArrayList<Item> itemList;

    Item temp;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        signout = findViewById(R.id.sign_out);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                launchLogin();
            }
        });

        //Links list view
        listViewItems = (ListView)findViewById(R.id.listViewItems);
        itemList = new ArrayList<>();

        db = FirebaseFirestore.getInstance();

        db.collection("Items")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int i = 0;
                            for (DocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                String Name = document.get("Name").toString();
                                if(Name == null){Name = "N/A";}
                                String Description = document.get("Description").toString();
                                if(Description == null){Description = "N/A";}
                                String Price = document.get("Price").toString();
                                if(Price == null){Price = "N/A";}
                                temp = new Item(Name, Description, Price);
                                if(temp != null) {
                                    itemList.add(temp);
                                }
                            }

                            ItemAdapter adapter = new ItemAdapter(MainActivity.this, itemList);
                            listViewItems.setAdapter(adapter);
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });

    }

    private void launchLogin() {
        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);
    }


}
