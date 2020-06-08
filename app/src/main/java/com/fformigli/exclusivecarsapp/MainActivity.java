package com.fformigli.exclusivecarsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import models.Menu;
import utils.SunmiPrintHelper;

public class MainActivity extends AppCompatActivity {

    private final Menu[] menu = {
        new Menu(R.string.client_menu,
                R.drawable.sunmi,
                ClientActivity.class)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPrinter();
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        final GridLayoutManager layoutManage = new GridLayoutManager(this, 2);
        RecyclerView mRecyclerView = findViewById(R.id.worklist);
        mRecyclerView.setLayoutManager(layoutManage);
        mRecyclerView.setAdapter(new WorkTogetherAdapter());
    }

    class WorkTogetherAdapter extends RecyclerView.Adapter<WorkTogetherAdapter.MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.menuDetails = menu[position];
            holder.tv.setText(menu[position].title);
            holder.tv.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(menu[position].icon), null, null);
        }

        @Override
        public int getItemCount() {
            return menu.length;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv;
            Menu menuDetails;

            MyViewHolder(View v) {
                super(v);
                tv = v.findViewById(R.id.worktext);
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(menuDetails == null){
                            return;
                        }
                        if(menuDetails.activityClass != null){
                            startActivity(new Intent(MainActivity.this, menuDetails.activityClass));
                        }
                    }
                });
            }
        }
    }

    /**
     * Connect print service through interface library
     */
    private void initPrinter(){
        SunmiPrintHelper.getInstance().initSunmiPrinterService(this);
    }
}