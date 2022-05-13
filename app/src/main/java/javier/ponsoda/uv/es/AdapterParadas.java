package javier.ponsoda.uv.es;


import android.app.Service;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.TextView;
import android.view.LayoutInflater;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import android.widget.Adapter;

public class AdapterParadas extends BaseAdapter{
    private ArrayList<Parada> paradas;
    Context context;
    static class ViewHolder {
        TextView number;
        TextView address;
        TextView partes;
    }
    public AdapterParadas(Context c)
    {
        context=c;
        Init();
    }
    public void Init() {

        //The String writer.toString() must be parsed in the bike stations ArrayList by using JSONArray and JSONObject
        paradas=new ArrayList<Parada>();

        InputStream is = context.getResources().openRawResource(R.raw.paradasvalenbici);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //////////////////GET TO JSON//////////////////////
        String jsonString = writer.toString();
        try {
            JSONObject jObject = new JSONObject(jsonString);
            JSONArray jArray = new JSONArray(jObject.getString("features"));
            int num = jArray.length();

            for (int i=0;i<num;i++)
            {
                JSONObject jsonObject = jArray.getJSONObject(i);
                String s = jsonObject.getString("properties");
                JSONObject jsonObjectParada = new JSONObject(s);

                Parada p = new Parada();

                p.address = jsonObjectParada.getString("name");
                p.partes = jsonObjectParada.getString("address");
                p.number = Integer.parseInt(jsonObjectParada.getString("number"));
                p.total = Integer.parseInt(jsonObjectParada.getString("total"));
                p.free = Integer.parseInt(jsonObjectParada.getString("free"));
                p.available = Integer.parseInt(jsonObjectParada.getString("available"));
               // p.distance = Integer.parseInt(jsonObjectParada.getString("distance"));

                String s2 = jsonObject.getString("geometry");
                JSONObject jsonObjectParadaGeometry = new JSONObject(s2);
                JSONArray jArrayCoords= new JSONArray( jsonObjectParadaGeometry.getString("coordinates"));
                p.coords_x= Float.parseFloat(jArrayCoords.getString(0));
                p.coords_y= Float.parseFloat(jArrayCoords.getString(1));

                paradas.add(p);
            }



        } catch (JSONException e) {
            Log.i("mal", "unexpected JSON exception", e);
        }

    }
    @Override
    public int getCount() {
        return paradas.size();
    }
    @Override
    public Object getItem(int position) {
        return paradas.get(position);
    }
    @Override
    public long getItemId(int position) {
        return paradas.get(position).number;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // We use the ViewHolder pattern to store the views of every list element
        //to display them faster when the list is moved
        View v = convertView;
        ViewHolder holder = null;
        if (v == null) {
            // If is null, we create it from “layout”
            LayoutInflater li = (LayoutInflater) context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.elementos_lista, null);
            holder = new ViewHolder();
            holder.number = (TextView) v.findViewById(R.id.paradaviewnumber);
            holder.address = (TextView) v.findViewById(R.id.paradaviewaddress);
            holder.partes = (TextView) v.findViewById(R.id.paradaviewpartes);

            v.setTag(holder);
        } else {
            // If is not null, we re-use it to update it.

            holder = (ViewHolder) v.getTag();
        }
        String num = String.valueOf(paradas.get(position).number);
        holder.number.setText(num);
        holder.address.setText(paradas.get(position).address);
        holder.partes.setText(paradas.get(position).partes);

        return v;
    }
}
