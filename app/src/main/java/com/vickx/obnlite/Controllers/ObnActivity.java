package com.vickx.obnlite.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vickx.obnlite.Models.Dossier;
import com.vickx.obnlite.Models.Observation;
import com.vickx.obnlite.R;
import com.vickx.obnlite.VFactory;

public class ObnActivity extends AppCompatActivity {

    //region class members

    private Dossier dossier;
    Button tangoButton = null;
    Button personButton = null;
    Button carButton = null;
    Button miscButton = null;
    Button startStopButton = null;
    Button logButton = null;
    Observation observation = null;

    //endregion

    //region activity events

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obn);
        tangoButton = this.findViewById(R.id.tangoButton);
        personButton = this.findViewById(R.id.personButton);
        carButton = this.findViewById(R.id.carButton);
        miscButton = this.findViewById(R.id.miscButton);
        startStopButton = this.findViewById(R.id.buttonStartStop);
        logButton = this.findViewById(R.id.logButton);
        this.setButtonsEnabled(false);

        //Récupération de l'objet Dossier
        Intent intent = getIntent();
        if(intent.getExtras() != null && intent.getExtras().size() != 0) {
            this.dossier = (Dossier) intent.getExtras().get("dossier");
            if(intent.getExtras().getBoolean("quickStart"))
                this.startStopObn(null);
            if(dossier != null)
                this.setTitle(this.dossier.getName());
            else
                this.finishActivity(-1);
        }
        else
            this.finishActivity(-1);

        //événements

        startStopButton.setOnClickListener(this::startStopObn);
        this.tangoButton.setOnClickListener(view -> launchActionActivity(ActionActivity.class, true));
        this.personButton.setEnabled(false);
        this.carButton.setOnClickListener(this::carClick);
        this.miscButton.setOnClickListener(view ->launchActionActivity(MiscActivity.class, false));
        this.logButton.setOnClickListener(this::logClick);
    }

    @Override
    protected  void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu,menu);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        return true;
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    //endregion

    //region controls events

    private void logClick(View view) {
        //Ici on lance l'activité du journal
        Toast.makeText(this,"Logs",Toast.LENGTH_SHORT).show();
    }

    private void carClick(View view) {
        //Ici on lance l'activité des véhicules
        Toast.makeText(this,"Car",Toast.LENGTH_SHORT).show();
    }

    private void personClick(View view) {
        //Ici on lance l'activité des actions
        Toast.makeText(this,"Person",Toast.LENGTH_SHORT).show();
    }

    private void startStopObn(View view) {
        this.observation = VFactory.getObservation();
        if(this.observation == null || this.observation.getState() == Observation.ObnState.stopped) {
            Toast.makeText(this, "Début de l'observation", Toast.LENGTH_SHORT).show();
            this.observation = Observation.startNew(this.dossier);
            VFactory.setObservation(this.observation);
            this.startStopButton.setText(R.string.stopObn);
            this.setButtonsEnabled(true);
        }
        else{
            this.startStopButton.setText(R.string.startObn);
            this.setButtonsEnabled(false);
            VFactory.getObservation().stop();
        }
    }

    //endregion

    //region class functions

    private void setButtonsEnabled(boolean b) {
        tangoButton.setEnabled(b);
        personButton.setEnabled(b);
        carButton.setEnabled(b);
        miscButton.setEnabled(b);
    }

    private void launchActionActivity(Class<?> activity, Boolean isTango) {
        Intent intent = new Intent(ObnActivity.this, activity);
        intent.putExtra("isTango",isTango);
        startActivity(intent);
    }

    //endregion

}
