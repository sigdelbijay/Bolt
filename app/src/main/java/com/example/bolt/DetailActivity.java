package com.example.bolt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bolt.domain.Feature;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    private ImageView itemImage;
    private TextView itemName;
    private TextView itemPrice;
    private Button itemRat;
    private TextView itemRatDes;
    private TextView itemDes;
    private Button itemAddCartBtn;
    private Button itemBuyNowBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Feature feature = (Feature) getIntent().getSerializableExtra("itemDetails");
//        Toast.makeText(DetailActivity.this, "" + feature.getName(), Toast.LENGTH_SHORT).show();
        itemImage = findViewById(R.id.item_img);
        itemName = findViewById(R.id.item_name);
        itemPrice = findViewById(R.id.item_price);
        itemRat = findViewById(R.id.item_rat_btn);
        itemRatDes = findViewById(R.id.item_rat_des);
        itemDes = findViewById(R.id.item_des);
        itemAddCartBtn = findViewById(R.id.itm_add_cart_btn);
        itemBuyNowBtn = findViewById(R.id.itm_buy_now_btn);

        Glide.with(DetailActivity.this).load(feature.getImg_url()).into(itemImage);
        itemName.setText(feature.getName());
        itemPrice.setText(feature.getPrice() + "$");
        itemRat.setText(feature.getRating() + "");
        if (feature.getRating() > 3) {
            itemRatDes.setText("Very Good");
        } else {
            itemRatDes.setText("Good");
        }
        itemAddCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        itemBuyNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, AddressActivity.class);
                intent.putExtra("itemDetails", feature);
                startActivity(intent);
            }
        });


    }
}