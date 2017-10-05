package com.example.wiesiek.beatroullete2;


import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Wiesiek on 2017-08-10.
 */

public class Wheel2  extends BaseClass {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.num1).setOnClickListener(this);
        findViewById(R.id.num2).setOnClickListener(this);
        findViewById(R.id.num3).setOnClickListener(this);
        findViewById(R.id.num4).setOnClickListener(this);
        findViewById(R.id.num5).setOnClickListener(this);
        findViewById(R.id.num6).setOnClickListener(this);
        findViewById(R.id.num7).setOnClickListener(this);
        findViewById(R.id.num8).setOnClickListener(this);
        findViewById(R.id.num9).setOnClickListener(this);
        findViewById(R.id.space).setOnClickListener(this);
        findViewById(R.id.deleteId).setOnClickListener(this);
        findViewById(R.id.num0).setOnClickListener(this);
        GetExtraFromIntent("wheel2");
        DatabaseAndSetSelection(ManagerDatabase.DatabaseClass.Col2);

    }

    @Override
    protected void onStop() {
        super.onStop();
        String edStr = editText.getText().toString();
        managerDatabase.DeleteColumn(ManagerDatabase.DatabaseClass.Col2);
        managerDatabase.AddData(edStr, ManagerDatabase.DatabaseClass.Col2);
    }
}
