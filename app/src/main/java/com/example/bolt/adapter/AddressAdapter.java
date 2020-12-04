package com.example.bolt.adapter;

import android.content.Context;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bolt.R;
import com.example.bolt.domain.Address;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {

    Context context;
    List<Address> addressList;
    private RadioButton selectedRB;
    public AddressAdapter(Context context, List<Address> addressList) {
        this.context = context;
        this.addressList = addressList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_address_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.addressTV.setText(addressList.get(position).getAddress());
        holder.selectAddressRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Address address: addressList) {
                    address.setSelected(false);
                }
                addressList.get(position).setSelected(true);

                //for selecting only one address radiobutton at a time
                if(selectedRB != null) {
                    selectedRB.setChecked(false);
                }
                selectedRB = (RadioButton) v;
                selectedRB.setChecked(true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView addressTV;
        private RadioButton selectAddressRB;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            addressTV = itemView.findViewById(R.id.address_tv);
            selectAddressRB = itemView.findViewById(R.id.select_adress_rb);
        }
    }

}
