package com.example.bolt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bolt.adapter.AddressAdapter;
import com.example.bolt.domain.Address;
import com.example.bolt.domain.Feature;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends AppCompatActivity {

    private RecyclerView addressRecy;
    private Button addAddBtn;
    private Button contPayBtn;
    private AddressAdapter addressAdapter;
    private List<Address> addressList;

    private FirebaseFirestore mStore;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        Feature itemDetails = (Feature) getIntent().getSerializableExtra("itemDetails");
        addressRecy = findViewById(R.id.address_recy);
        addAddBtn = findViewById(R.id.add_address_btn);
        contPayBtn = findViewById(R.id.cont_pay_btn);
        addressList = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();

        addressAdapter = new AddressAdapter(getApplicationContext(), addressList);
        addressRecy.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        addressRecy.setAdapter(addressAdapter);

        mStore.collection("User").document(mAuth.getCurrentUser().getUid())
                .collection("Address").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for(DocumentSnapshot doc: task.getResult()) {
                        Address address = doc.toObject(Address.class);
                        addressList.add(address);
                        addressAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        addAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressActivity.this, AddAddressActivity.class);
                startActivity(intent);
            }
        });

        contPayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressActivity.this, PaymentActivity.class);
                intent.putExtra("price", itemDetails.getPrice());
                startActivity(intent);
            }
        });
    }
}