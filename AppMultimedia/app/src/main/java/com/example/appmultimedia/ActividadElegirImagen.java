package com.example.appmultimedia;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActividadElegirImagen extends AppCompatActivity {

    private RecyclerView miReciclador;
    private AdaptadorImagen adaptadorImagen;
    private List<ModeloImagen> listaImagen;
    private ArrayList<String> listaImportada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_elegir_imagen);

        listaImportada = new ArrayList<String>();
        miReciclador = (RecyclerView) findViewById(R.id.miReciclador);

        listaImagen = new ArrayList<>();

        adaptadorImagen = new AdaptadorImagen(this, listaImagen);

        miReciclador.setAdapter(adaptadorImagen);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        miReciclador.setLayoutManager(gridLayoutManager);

        buscarImagen();

        miReciclador.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector  = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent event){
                    return true;
                }
            });
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                View hijo = rv.findChildViewUnder(e.getX(), e.getY());

                if (hijo != null && gestureDetector.onTouchEvent(e)){
                    int position = rv.getChildAdapterPosition(hijo);
                    listaImportada.add(listaImagen.get(position).getSrcOriginal());
                    Toast.makeText(getApplicationContext(), listaImagen.get(position).getDescripcion(), Toast.LENGTH_LONG).show();
                  Intent actividadImagenAnimada = new Intent(getApplicationContext(), ActividadImagenAnimada.class);
                  actividadImagenAnimada.putExtra("urlImagen", listaImagen.get(position).getSrcOriginal());
                  actividadImagenAnimada.putStringArrayListExtra("listaImportada", listaImportada);
                  startActivity(actividadImagenAnimada);
                }
                return false;
            }
            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }
    public void buscarImagen(){
        //Clase sencilla que me permite hacer peticiones de manera más eficiente con el metodo GET
        StringRequest peticion = new StringRequest(Request.Method.GET, "https://api.pexels.com/v1/search?query=cat&per_page=1&per_page=80", new Response.Listener<String>() {
           //https://api.pexels.com/v1/search?query=cat&per_page=1&per_page=80
            //https://api.pexels.com/v1/curated/?page=1&per_page=80

            /*
            - Primer direccion https => Busqueda solamente de gatos
            - Segundo enlace https => Cualquier tipo de tema

            La propia api me da que parametros usar en la direccion https en este caso ?query para buscar por categoria
             */

            @Override
            public void onResponse(String respuesta) {
                //Mañana la extraccion de datos del JSON que me ofrece esta api de imagenes...
                try {
                    JSONObject respuestaJson = new JSONObject(respuesta);
                    JSONArray arrayFoto = respuestaJson.getJSONArray("photos");

                    int longitudArrayFoto = arrayFoto.length();

                    for (int x = 0; x < longitudArrayFoto; x++){
                        JSONObject imagenesObjeto = arrayFoto.getJSONObject(x);
                        int idFoto = imagenesObjeto.getInt("id");

                        JSONObject fuenteImagen = imagenesObjeto.getJSONObject("src");

                        String srcOriginal = fuenteImagen.getString("original");
                        String descripcion = imagenesObjeto.getString("alt");

                        Log.d("buscar", "descripcion => "+descripcion);
                        Log.d("buscar", "id => "+srcOriginal);

                        ModeloImagen modeloImagen = new ModeloImagen(idFoto, srcOriginal, descripcion);
                        listaImagen.add(modeloImagen);
                    }
                   adaptadorImagen.notifyDataSetChanged();
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "No se ha podido realizar la peticion");
            }
        }){
            /*
            Devuelve una lista de encabezados HTTPS adicionales para acompañar esta solicitud.
            Puede lanzar AuthFailureError ya que es posible que se requiera autenticación para proporcionar estos valores.
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String, String> accesoApi = new HashMap<>();

                //Clave valor
                //El segundo parametro será mi token de acceso a la api de Pexels
                accesoApi.put("Authorization","563492ad6f91700001000001431f306ac2b44e48bfefa59420d746cb");
                return accesoApi;
            }
        };
        //Esperamos a que termine el hilo de peticion
        //Se realiza en segundo plano
        RequestQueue colaPeticion = Volley.newRequestQueue(getApplicationContext());
        colaPeticion.add(peticion);
    }
}