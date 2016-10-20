package com.catinean.appshortcutsdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DynamicShortcutActivity extends AppCompatActivity {

    public static final String ACTION = BuildConfig.APPLICATION_ID + ".OPEN_DYNAMIC_SHORTCUT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_shortcut);
    }
}
