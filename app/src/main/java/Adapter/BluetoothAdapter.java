package Adapter;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import Models.Common;
import shahbasoft.mycar.R;


public class BluetoothAdapter extends RecyclerView.Adapter<BluetoothAdapter.MyViewHolder>{

    Context context;


    private List<BluetoothDevice> OfferList;


    public class MyViewHolder extends RecyclerView.ViewHolder {



        TextView  bName, bMAC;

        public MyViewHolder(View view) {
            super(view);

            bName = view.findViewById(R.id.name);
            bMAC = view.findViewById(R.id.mac);


        }

    }


    public BluetoothAdapter(Context context, List<BluetoothDevice> offerList) {
        this.OfferList = offerList;
        this.context = context;
    }

    @Override
    public BluetoothAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bluetooth, parent, false);


        return new BluetoothAdapter.MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(@NonNull BluetoothAdapter.MyViewHolder holder, int position) {
        final BluetoothDevice device = OfferList.get(position);

        holder.bName.setText(device.getName());
        holder.bMAC.setText(device.getAddress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.device = device;
            }
        });

    }



    @Override
    public int getItemCount() {
        return OfferList.size();

    }

}


