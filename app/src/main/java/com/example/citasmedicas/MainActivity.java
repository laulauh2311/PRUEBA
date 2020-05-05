package com.example.citasmedicas;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.DatePicker;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Spinner spinner1;
    TimePicker timePicker;
    DatePicker datePicker;
    TextView txtEspecialidad;
    int hour, minute;
    static final int TIME_DIALOG_ID =0;
    String[] especialidad = {
            "Pediatría-Dr. Medina",
            "Medicina Interna-Dr. Zamora",
            "Dermatologia-Dra. Garcia"
    };
    String[] Lugar = {
            "Centro Clínico Cajamarca",
            "Centro Clínico La Valle",
            "Centro Clínico Belen",
            "Clinica del Sur",
            "Clinica San Borja",
            "Clinica Sanchéz Fernandez"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Adaptador para el control AutoCompleteTextView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line,Lugar);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.txtLugar);
        textView.setThreshold(3);
        textView.setAdapter(adapter);
        //Adaptador para el control AutoCompleteTextView
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line,especialidad);
        AutoCompleteTextView textView1 = (AutoCompleteTextView)
                findViewById(R.id.Esp);
        textView1.setThreshold(3);
        textView1.setAdapter(adapter3);
        //ESPECIALIDAD
        txtEspecialidad = (TextView) findViewById(R.id.Esp);
        //TIMEPICKER AND DATEPICKER
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        //showDialog(TIME_DIALOG_ID );
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        //spinner
        spinner1 = (Spinner)findViewById(R.id.spinner11);
        String [] opciondoctores = {"Doctor Medina", "Doctor Zamora","Doctora Garcia"};

        ArrayAdapter <String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciondoctores);
        spinner1.setAdapter(adapter1);
    }
    protected Dialog onCreateDialog(int id){

        switch (id){
            case TIME_DIALOG_ID:
                return new TimePickerDialog(
                        this, mTimeSetListener, hour, minute, false
                );
        }
        return null;
    }
    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfHour) {
            hour =hourOfDay;
            minute =minuteOfHour;

            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm aa");
            Date date = new Date(0,0,0);
            String strDate = timeFormat.format(date);

            Toast.makeText(getBaseContext(),
                    "You have selected "+strDate,
                    Toast.LENGTH_SHORT).show();
        }
    };
    public void onClick (View view){
        Toast.makeText(getBaseContext(),
                "DATOS DE LA CITA" + "\n"+
                        "Fecha de la cita: " + (datePicker.getMonth()+1)+
                        "/" + datePicker.getDayOfMonth()+
                        "/" + datePicker.getYear()+ "\n"+
                        "Hora de la cita " + timePicker.getCurrentHour() +
                        ":"+timePicker.getCurrentMinute()+
                        "CITA PROGRAMADA EXITOSAMENTE"+ "\n",
                Toast.LENGTH_LONG).show();
    }
}
