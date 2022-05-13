package javier.ponsoda.uv.es;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

public class detalle_parada2 extends AppCompatActivity {

    private TextView address;
    private TextView number;
    private TextView total;
    private TextView free;
    private TextView available;
    private TextView coords;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.paradas_detail);
        Parada parada = (Parada) getIntent().getSerializableExtra("miparada");
        Log.i("Click", "click en el elemento " + parada.address + " de mi ListView");
        address    =(TextView)findViewById(R.id.address);
        number    = (TextView)findViewById(R.id.number);
        total    = (TextView)findViewById(R.id.total);
        free    = (TextView)findViewById(R.id.free);
        available    = (TextView)findViewById(R.id.available);
        coords    = (TextView)findViewById(R.id.coords);

        address.setText(parada.address);
        number.setText(String.valueOf(parada.number));
        total.setText(String.valueOf(parada.total));
        free.setText(String.valueOf(parada.free));
        available.setText(String.valueOf(parada.available));
        coords.setText(String.valueOf(parada.coords_x) + ", " +String.valueOf(parada.coords_y));
    }
    @Override public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mimenu, menu);
        return true;
    }
}
