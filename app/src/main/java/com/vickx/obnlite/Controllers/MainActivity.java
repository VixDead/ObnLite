package com.vickx.obnlite.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.vickx.obnlite.Models.Dossier;
import com.vickx.obnlite.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = this.findViewById(R.id.listView);
        final Button quickStartButton = this.findViewById(R.id.quickStart);

    }

    private void onListViewItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        launchActivity((Dossier) this.listView.getItemAtPosition(i),false);
    }

    private void launchActivity(Dossier dossier, boolean quickStart){

    }
}
