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
import android.view.View;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);

        ShortcutInfo webShortcut = new ShortcutInfo.Builder(this, "shortcut_web")
                .setShortLabel("catinean.com")
                .setLongLabel("Open catinean.com web site")
                .setIcon(Icon.createWithResource(this, R.drawable.ic_dynamic_shortcut))
                .setIntent(new Intent(Intent.ACTION_VIEW, Uri.parse("https://catinean.com")))
                .setRank(0)
                .build();

        ShortcutInfo dynamicShortcut = new ShortcutInfo.Builder(this, "shortcut_dynamic")
                .setShortLabel("Dynamic")
                .setLongLabel("Open dynamic shortcut")
                .setIcon(Icon.createWithResource(this, R.drawable.ic_dynamic_shortcut_2))
                .setIntents(
                        new Intent[]{
                                new Intent(Intent.ACTION_MAIN, Uri.EMPTY, this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK),
                                new Intent(DynamicShortcutActivity.ACTION)
                        })
                .setRank(1)
                .build();

        shortcutManager.setDynamicShortcuts(Arrays.asList(webShortcut, dynamicShortcut));

        findViewById(R.id.main_rank_button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(android.R.color.holo_red_dark, getTheme()));
                String label = "catinean.com";
                SpannableStringBuilder colouredLabel = new SpannableStringBuilder(label);
                colouredLabel.setSpan(colorSpan, 0, label.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);

                ShortcutInfo webShortcut = new ShortcutInfo.Builder(MainActivity.this, "shortcut_web")
                        .setShortLabel(colouredLabel)
                        .setRank(1)
                        .build();

                ShortcutInfo dynamicShortcut = new ShortcutInfo.Builder(MainActivity.this, "shortcut_dynamic")
                        .setRank(0)
                        .build();

                shortcutManager.updateShortcuts(Arrays.asList(webShortcut, dynamicShortcut));
            }
        });
    }
}
