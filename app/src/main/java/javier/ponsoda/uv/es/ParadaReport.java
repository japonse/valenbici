package javier.ponsoda.uv.es;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

public class ParadaReport extends AppCompatActivity {

    private TextView name;
    private TextView description;
    private TextView station;
    private TextView status;
    private TextView type;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.report_detail);
        Report report = (Report) getIntent().getSerializableExtra("miparada");

       // Log.i("Click", "click en el elemento " + parada.address + " de mi ListView");
        name    =(TextView)findViewById(R.id.name);
        description    = (TextView)findViewById(R.id.description);
        station    = (TextView)findViewById(R.id.station);
        status    = (TextView)findViewById(R.id.status);
        type    = (TextView)findViewById(R.id.type);

        name.setText(report.name);
        description.setText(String.valueOf(report.descripcion));
        station.setText(String.valueOf(report.estacion));
        status.setText(String.valueOf(report.status));
        type.setText(String.valueOf(report.type));

    }

}