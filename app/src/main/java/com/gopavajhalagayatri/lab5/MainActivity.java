package com.gopavajhalagayatri.lab5;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sP;
    SharedPreferences.Editor editor;
    storage store;
    TextView temp;
    TextView perm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        temp = findViewById(R.id.temp);
        perm = findViewById(R.id.perm);
        sP = getSharedPreferences("Settings",Context.MODE_PRIVATE);
        editor = sP.edit();
        store = new storage();
        perm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear().commit();
                show();
            }
        });
        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                store = new storage();
                show();
            }
        });

        store.create++;
        editor.putInt("create", sP.getInt("create", 0) + 1);
        show();
    }

    protected void onStart() {
        super.onStart();
        store.start++;
        editor.putInt("start", sP.getInt("start", 0) + 1);
        show();
    }

    protected void onResume() {
        super.onResume();
        store.resume++;
        editor.putInt("resume", sP.getInt("resume", 0) + 1);
        show();
    }

    protected void onPause() {
        super.onPause();
        store.pause++;
        editor.putInt("pause", sP.getInt("pause", 0) + 1);
        show();
    }

    protected void onRestart() {
        super.onRestart();
        store.restart++;
        editor.putInt("restart", sP.getInt("restart", 0) + 1);
        show();
    }

    protected void onStop() {
        super.onStop();
        store.stop++;
        editor.putInt("stop", sP.getInt("stop", 0) + 1);
        show();
    }

    protected void onDestroy() {
        super.onDestroy();
        store.destroy++;
        editor.putInt("destroy", sP.getInt("destroy", 0) + 1);
        show();
    }

    protected void show(){
        editor.apply();
        temp.setText("Create: " + store.create +
                    "\nStart: " + store.start +
                    "\nResume: " + store.resume +
                    "\nPause: " + store.pause +
                    "\nStop: " + store.stop +
                    "\nRestart: " + store.restart +
                    "\nDestroy: " + store.destroy);
        perm.setText("Create: " + sP.getInt("create", 0) +
                "\nStart: " + sP.getInt("start", 0) +
                "\nResume: " + sP.getInt("resume", 0) +
                "\nPause: " + sP.getInt("pause", 0) +
                "\nStop: " + sP.getInt("stop", 0) +
                "\nRestart: " + sP.getInt("restart", 0) +
                "\nDestroy: " + sP.getInt("destroy", 0));
    }
}

class storage{

    int create;
    int start;
    int resume;
    int pause;
    int stop;
    int restart;
    int destroy;

    public storage() {
        create = 0;
        start = 0;
        resume = 0;
        pause = 0;
        stop = 0;
        restart = 0;
        destroy = 0;
    }
}