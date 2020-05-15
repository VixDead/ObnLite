package com.vickx.obnlite.Controllers;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.vickx.obnlite.Models.DAO.DossierDAO;
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
        final ArrayList<Dossier> dossiers = new DossierDAO().getAll();
        final ArrayAdapter<Dossier> adapter = new ArrayAdapter<Dossier>(this,android.R.layout.simple_expandable_list_item_1,dossiers);

        quickStartButton.setOnClickListener(this::onQuickStartButtonClick);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this::onListViewItemClick);

        Toast.makeText(this,"onCreateMainActivity", Toast.LENGTH_SHORT).show();
    }

    private void onQuickStartButtonClick(View view) {
        launchActivity(Dossier.createNew(), true);
    }

    private void onListViewItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        launchActivity((Dossier) this.listView.getItemAtPosition(i),false);
    }

    private void launchActivity(Dossier dossier, boolean quickStart){
        if(dossier == null) {
            Toast.makeText(this, "Erreur dans la récupération du dossier", Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(MainActivity.this, ObnActivity.class);
        intent.putExtra("dossier",dossier);
        intent.putExtra("quickStart",quickStart);
        startActivity(intent);

    }
}
