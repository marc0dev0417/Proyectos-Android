package com.example.menucontextual;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creamos la lista de starks para el menú
        TextView starks = (TextView)findViewById(R.id.id_textJugar);
        registerForContextMenu(starks);
    }
    @Override
    public void onCreateContextMenu (ContextMenu menu, View vista, ContextMenu.ContextMenuInfo menuInfo)
    {
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.menu_contextual, menu);
        super.onCreateContextMenu(menu, vista, menuInfo);
    }
    @Override
    public boolean onContextItemSelected (MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId())
        {
            case R.id.matar:
                Toast.makeText(getApplicationContext(), "Hemos matado a Iker", Toast.LENGTH_LONG).show();
                return true;
            case R.id.sanar:
                Toast.makeText(getApplicationContext(),"Hemos sanado a iker", Toast.LENGTH_LONG).show();
                return true;
            case R.id.enviarmensaje:
                Toast.makeText(getApplicationContext(), "hola", Toast.LENGTH_LONG).show();
                return true;
        }
        return  super.onContextItemSelected(item);
    }
}