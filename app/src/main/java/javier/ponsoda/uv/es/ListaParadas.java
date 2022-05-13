package javier.ponsoda.uv.es;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ListaParadas extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();

        AdapterParadas adapter = new AdapterParadas(context);
        ListView lv = (ListView)findViewById(R.id.mi_lista);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                Parada parada_seleccionada = (Parada)arg0.getItemAtPosition(position);
                // Realiza lo que deseas, al recibir clic en el elemento de tu listView determinado por su posicion.



                Intent miIntent = new Intent(getApplicationContext(), detalle_parada2.class);
                miIntent.putExtra("miparada",parada_seleccionada);
                startActivity(miIntent);

            }
        });

    }
}
