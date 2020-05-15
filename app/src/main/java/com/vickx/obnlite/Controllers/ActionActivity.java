package com.vickx.obnlite.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.vickx.obnlite.Models.DAO.ObservationDAO;
import com.vickx.obnlite.Models.Event;
import com.vickx.obnlite.Models.Observation;
import com.vickx.obnlite.R;
import com.vickx.obnlite.VFactory;

public class ActionActivity extends AppCompatActivity {

    //region members
    Button getOutPlageButton;
    Button getInPlageButton;
    Button getInOutAdressButton;
    Button moveStopbutton;
    Button goToButton;
    Button isButton;
    Button dealButton;
    Button exchangeButton;
    Button contactButton;
    Button getInOutCarButton;
    Button contactCarButton;
    Button otherActionButton;
    Button cancelPreviousButton;
    ImageButton cameraButton;
    Observation observation;
    String subject = "Une personne";
    boolean isMoving = false;
    //endregion

    //region Activity events
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        this.getOutPlageButton = this.findViewById(R.id.getOutPlageButton);
        this.getInPlageButton = this.findViewById(R.id.getInPlageButton);
        this.getInOutAdressButton = this.findViewById(R.id.getOutAdrButton);
        this.moveStopbutton = this.findViewById(R.id.startStopButton);
        this.goToButton = this.findViewById(R.id.directionButton);
        this.isButton = this.findViewById(R.id.is);
        this.dealButton = this.findViewById(R.id.deal);
        this.exchangeButton = this.findViewById(R.id.exchange);
        this.contactButton = this.findViewById(R.id.contact);
        this.getInOutCarButton = this.findViewById(R.id.inVV);
        this.contactCarButton = this.findViewById(R.id.contactvv);
        this.otherActionButton = this.findViewById(R.id.action);
        this.cancelPreviousButton = this.findViewById(R.id.cancelPrevious);
        this.cameraButton = this.findViewById(R.id.cameraButton);
        this.observation = VFactory.getObservation();

        this.goToButton.setEnabled(false);
        this.goToButton.setEnabled(false);
        this.getInOutAdressButton.setEnabled(false);
        this.isButton.setEnabled(false);
        this.contactButton.setEnabled(false);
        this.contactCarButton.setEnabled(false);
        this.otherActionButton.setEnabled(false);
        this.cancelPreviousButton.setEnabled(false);
        this.cameraButton.setEnabled(false);

        this.getOutPlageButton.setOnClickListener(this::onGetOutPlageClick);
        this.getInPlageButton.setOnClickListener(this::onGetInPlageClick);
        this.dealButton.setOnClickListener(view -> ObservationDAO.log(this.observation,new Event(this.subject,"deal")));
        this.exchangeButton.setOnClickListener(view -> ObservationDAO.log(this.observation,new Event(this.subject,"échange")));
        this.moveStopbutton.setOnClickListener(this::movesStops);

        if(this.getIntent().hasExtra("isTango") && this.getIntent().getExtras().getBoolean("isTango"))
            this.subject = "Tango";
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

    private void movesStops(View view) {
        if(!this.isMoving)
            ObservationDAO.log(this.observation,new Event(this.subject,"se met en mouvement"));
        else
            ObservationDAO.log(this.observation,new Event(this.subject,"s'arrête"));
        this.isMoving = !this.isMoving;
    }

    private void onGetInPlageClick(View view) {
        ObservationDAO.log(this.observation,new Event(subject, "entre", "dans la plage"));
    }

    private void onGetOutPlageClick(View view) {
        ObservationDAO.log(this.observation,new Event(subject, "sort", "de la plage"));
    }

}
