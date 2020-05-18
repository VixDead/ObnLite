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
        logButton = this.findViewById(R.id.logButton);

        this.tangoButton.setOnClickListener(view -> launchActionActivity(ActionActivity.class, true));
        this.personButton.setOnClickListener(view -> launchActionActivity(ActionActivity.class, false));
        this.carButton.setEnabled(false);
        this.miscButton.setOnClickListener(view ->launchActionActivity(MiscActivity.class, false));
        this.logButton.setOnClickListener(view ->launchActionActivity(LogsActivity.class,false));
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
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    //endregion

    //region class functions


    private void launchActionActivity(Class<?> activity, Boolean isTango) {
        Intent intent = new Intent(ObnActivity.this, activity);
        intent.putExtra("isTango",isTango);
        startActivity(intent);
    }

    //endregion

}
