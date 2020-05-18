package com.vickx.obnlite.Controllers;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.vickx.obnlite.Models.DAO.EventDAO;
import com.vickx.obnlite.Models.Event;
import com.vickx.obnlite.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LogsActivity extends AppCompatActivity {

    Button eraseAllButton;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);

        this.eraseAllButton = findViewById(R.id.eraseAllButton);
        this.eraseAllButton.setOnClickListener(this::eraseAll);

        this.listView = findViewById(R.id.listViewLogs);

        this.refresh();
    }

    private void refresh() {
        EventDAO dao = new EventDAO(this);
        ArrayList<Event> events = dao.getAll();
        this.listView.setAdapter(null);
        if(events !=null) {

            List<HashMap<String,String>> list = new ArrayList<>();
            HashMap<String,String> element;

            for(int i = 0; i< events.size();i++) {
                element = new HashMap<>();
                element.put("date",events.get(i).getDateTime());
                element.put("message", events.get(i).getMessage());
                list.add(element);
            }

            SimpleAdapter adapter = new SimpleAdapter(this,list,android.R.layout.simple_expandable_list_item_2, new String[]{"date","message"}, new int[]{android.R.id.text1, android.R.id.text2});
            this.listView.setAdapter(adapter);
        }
    }

    private void eraseAll(View view) {

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        Toast.makeText(LogsActivity.this,"Suppression des données",Toast.LENGTH_SHORT).show();
                        new EventDAO(LogsActivity.this).eraseAll();
                        refresh();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Êtes-vous sûr de vouloir tout éffacer?").setPositiveButton("Oui", dialogClickListener)
                .setNegativeButton("Non", dialogClickListener).show();
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
