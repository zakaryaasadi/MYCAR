package shahbasoft.mycar;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothService;

import java.util.ArrayList;

import Adapter.BluetoothAdapter;
import Models.Common;

public class BluetoothMainActivity extends AppCompatActivity {

    private ArrayList<BluetoothDevice> devices;
    private RecyclerView recyclerView;
    private BluetoothAdapter bAdapter;
    private BluetoothService service;
    private SwipeRefreshLayout swipe;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_main);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BluetoothMainActivity.this, StartCarActivity.class);
                startActivity(i);
            }
        });

        swipe = findViewById(R.id.swipe);
        swipe.getLayoutParams().height = Common.getScreenHeight(BluetoothMainActivity.this) + 250;
        swipe.setColorSchemeResources(R.color.orange, R.color.twittercolor, R.color.redBg);
        swipe.setOnRefreshListener(onRefreshListener);

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BluetoothMainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        devices = new ArrayList<>();
        bAdapter = new BluetoothAdapter(BluetoothMainActivity.this, devices);
        recyclerView.setAdapter(bAdapter);

        service = BluetoothService.getDefaultInstance();

        service.setOnScanCallback(new BluetoothService.OnBluetoothScanCallback() {
            @Override
            public void onDeviceDiscovered(BluetoothDevice device, int rssi) {
                devices.add(device);
                bAdapter.notifyDataSetChanged();
            }

            @Override
            public void onStartScan() {
                swipe.setRefreshing(true);
            }

            @Override
            public void onStopScan() {
                swipe.setRefreshing(false);
            }
        });

        //service.startScan();


    }

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            devices.clear();
            bAdapter.notifyDataSetChanged();
            service.startScan();
        }
    };
}
