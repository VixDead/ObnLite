package com.vickx.obnlite.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.vickx.obnlite.Models.DAO.EventDAO;
import com.vickx.obnlite.Models.Event;
import com.vickx.obnlite.Models.Observation;
import com.vickx.obnlite.R;


public class MiscActivity extends AppCompatActivity {

    Observation observation = null;
    Button addButton = null;
    EditText editText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misc);

        this.addButton = findViewById(R.id.addButton);
        this.editText = findViewById(R.id.editText);
        
        this.addButton.setOnClickListener(this::addButtonClick);

    }

    private void addButtonClick(View view) {
        new EventDAO(this).insert(new Event(this.editText.getText().toString(),"",""));
        this.finish();
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
}
