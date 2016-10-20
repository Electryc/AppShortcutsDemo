package com.catinean.appshortcutsdemo;

import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);

        ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(android.R.color.holo_red_dark, getTheme()));
        String text = "catinean.com";
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        spannableStringBuilder.setSpan(colorSpan, 0, text.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        ShortcutInfo catineanShortcut = new ShortcutInfo.Builder(this, "shortcut_catinean")
                .setShortLabel(spannableStringBuilder)
                .setLongLabel("Open catinean.com web site")
                .setIcon(Icon.createWithResource(this, R.drawable.ic_dynamic_shortcut))
                .setIntent(new Intent(Intent.ACTION_VIEW, Uri.parse("https://catinean.com")))
                .setRank(0)
                .build();

        ShortcutInfo novodaShortcut = new ShortcutInfo.Builder(this, "shortcut_dynamic")
                .setShortLabel("Dynamic")
                .setLongLabel("Open dynamic shortcut")
                .setIcon(Icon.createWithResource(this, R.drawable.ic_dynamic_shortcut_2))
                .setIntents(
                        new Intent[]{
                                new Intent(Intent.ACTION_MAIN, null, this, MainActivity.class),
                                new Intent(DynamicShortcutActivity.ACTION)
                        })
                .setRank(1)
                .build();

        shortcutManager.setDynamicShortcuts(Arrays.asList(catineanShortcut, novodaShortcut));
    }
}
