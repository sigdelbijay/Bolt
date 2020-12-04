package com.example.bolt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddAddressActivity extends AppCompatActivity {

    private EditText adName;
    private EditText adAddressLane;
    private EditText adCity;
    private EditText adPostal;
    private EditText adPhone;
    private Button addNewAddressBtn;
    FirebaseFirestore mStore;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        adName = findViewById(R.id.ad_name);
        adAddressLane = findViewById(R.id.ad_address_lane);
        adCity = findViewById(R.id.ad_city);
        adPostal = findViewById(R.id.ad_postal);
        adPhone = findViewById(R.id.ad_phone);
        addNewAddressBtn = findViewById(R.id.add_new_adress_btn);

        mStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        addNewAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = adName.getText().toString();
                String addressLane = adAddressLane.getText().toString();
                String city = adCity.getText().toString();
                String postal = adPostal.getText().toString();
                String phone = adPhone.getText().toString();
                String finalAddress = "";
                if(!name.isEmpty()) finalAddress += name+", ";
                if(!addressLane.isEmpty()) finalAddress += addressLane+", ";
                if(!city.isEmpty()) finalAddress += city+", ";
                if(!postal.isEmpty()) finalAddress += postal+", ";
                if(!phone.isEmpty()) finalAddress += phone+", ";

                Map<String, String> mMap = new HashMap<>();
                mMap.put("address", finalAddress);

                mStore.collection("User").document(mAuth.getCurrentUser().getUid())
                        .collection("Address").add(mMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(AddAddressActivity.this, "Address added", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });
    }
}