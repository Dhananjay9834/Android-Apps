Bpackage com.example.mymusicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class BluetoothActivity extends AppCompatActivity {

    Button turnon, getvisible, paireddevices, turnoff;
    ListView pairedDevices;

    BluetoothAdapter bluetoothAdapter;
    Set<BluetoothDevice> bluetoothDevices;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        turnon = findViewById(R.id.btn_bluetooth_turn_on);
        getvisible = findViewById(R.id.btn_bluetooth_get_visible);
        paireddevices = findViewById(R.id.btn_bluetooth_paired_devices);
        turnoff = findViewById(R.id.btn_bluetooth_turn_off);
        pairedDevices = findViewById(R.id.lv_bluetooth_list);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    @SuppressLint("MissingPermission")
    public void turnOn(View view) {
        if (!bluetoothAdapter.isEnabled())
        {
            Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(i, 999);
        }
        else
        {
            Toast.makeText(BluetoothActivity.this,"Your bluetooth is already turned on",Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingPermission")
    public void getVisible(View view) {
        Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(i,888);
    }


    @SuppressLint("MissingPermission")
    public void pairedDevices(View view) {
        bluetoothDevices = bluetoothAdapter.getBondedDevices();
        ArrayList list = new ArrayList();
        for(BluetoothDevice bd : bluetoothDevices)
        {
            list.add(bd.getName());
            ArrayAdapter adapter = new ArrayAdapter(BluetoothActivity.this,
                    android.R.layout.simple_dropdown_item_1line,list);

            pairedDevices.setAdapter(adapter);
        }
    }

    @SuppressLint("MissingPermission")
    public void turnOff(View view) {
        if (bluetoothAdapter.isEnabled())
        {
            bluetoothAdapter.disable();
        }
        else
        {
            Toast.makeText(BluetoothActivity.this,"Your bluetooth is already turned off",Toast.LENGTH_SHORT).show();
        }
    }
}
