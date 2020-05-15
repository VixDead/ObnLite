package com.vickx.obnlite.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.vickx.obnlite.Models.DAO.ObservationDAO;
import com.vickx.obnlite.Models.Event;
import com.vickx.obnlite.Models.Observation;
import com.vickx.obnlite.R;
import com.vickx.obnlite.VFactory;


public class MiscActivity extends AppCompatActivity {

    Observation observation = null;
    Button addButton = null;
    EditText editText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misc);

        this.observation = VFactory.getObservation();
        this.setTitle( this.observation.getDossier().getName() + " - " + getString(R.string.misc));
        this.addButton = findViewById(R.id.addButton);
        this.editText = findViewById(R.id.editText);
        
        this.addButton.setOnClickListener(this::addButtonClick);

    }

    private void addButtonClick(View view) {
        ObservationDAO.log(this.observation,new Event(this.editText.getText().toString()));
        this.finish();
    }
}
