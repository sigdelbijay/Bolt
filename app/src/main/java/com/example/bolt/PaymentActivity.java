package com.example.bolt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bolt.domain.Feature;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONObject;

public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {

    private TextView subtotalTV;
    private TextView totalTV;
    private Button checkoutBtn;
    double price = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        price = getIntent().getDoubleExtra("price", 0.0);

        subtotalTV = findViewById(R.id.subtotal_TV);
        totalTV = findViewById(R.id.total_tv);
        checkoutBtn = findViewById(R.id.checkout_btn);

        subtotalTV.setText("$" + price);
        totalTV.setText("$" + price);

        //payment
        Checkout.preload(getApplicationContext());
        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });
    }

    public void startPayment() {
        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_JoA92J5pK7jlXO");


        /**
         * Reference to current activity
         */
        final Activity activity = PaymentActivity.this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            options.put("name", "bolt tech");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "USD");
            options.put("amount", price);//pass amount in currency subunits
            options.put("prefill.email", "sigdelbjay@gmail.com");
            options.put("prefill.contact","9988776655");
            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(PaymentActivity.this, "Payment Successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(PaymentActivity.this, ""+s, Toast.LENGTH_SHORT).show();
    }
}