package com.example.propagacionintents;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Definición del ActivityResultLauncher
        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            //Si queremos recoger datos

                            Log.d("tag", "encontrado"+"Ok");
                            Intent data = result.getData();
                            Bundle b = data.getExtras();
                        }else{
                            Log.d("tag","encontrado"+"mal");
                        }

                    }
                });
        //Lanzamiento del ActivityResultLauncher
        Intent intent = new Intent(MainActivity.this, OtraActividad.class);

        someActivityResultLauncher.launch(intent);

    }
}