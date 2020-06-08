package com.fformigli.exclusivecarsapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sunmi.peripheral.printer.SunmiPrinterService;
import com.sunmi.peripheral.printer.WoyouConsts;

import java.math.BigInteger;

import models.Client;
import utils.BluetoothUtil;
import utils.BytesUtil;
import utils.ESCUtil;
import utils.SunmiPrintHelper;

public class ClientActivity extends AppCompatActivity {
    public Client[] clients = {
            new Client(new BigInteger("654168"), "Juan", "Perez", "+595983265381")
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
    }

    public void printTest(View view){
        printClientProfile(clients[0]);
    }

    private void printClientProfile(Client client) {
        if (!BluetoothUtil.isBlueToothPrinter) {
            //header
            SunmiPrintHelper.getInstance().printTable(new String[]{"Nombre","Apellido","Telefono"},
                    new int[]{1,1,1}, new int[]{0,0,0});
            //values
            SunmiPrintHelper.getInstance().printTable(new String[]{client.firstname, client.lastname, client.phone},
                    new int[]{1,1,1}, new int[]{0,0,0});

            SunmiPrintHelper.getInstance().feedPaper();
        } else {
            BluetoothUtil.sendData(ESCUtil.printBitmap(BytesUtil.initTable(6, 12)));
        }
    }
}
