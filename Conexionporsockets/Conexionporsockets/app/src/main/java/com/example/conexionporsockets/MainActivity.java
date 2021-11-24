package com.example.conexionporsockets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    ServerSocket serverSocket;
    Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button iniciar = findViewById(R.id.button);
        TextView tv = findViewById(R.id.textView);
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            serverSocket = new ServerSocket(1234);

                            while (!serverSocket.isClosed()) {

                                socket = serverSocket.accept();
                                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                                String txt = entrada.readLine();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv.setText(tv.getText().toString() + txt + " ");
                                    }
                                });
                                Log.d("TAG", "run: " + txt);
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });

        Button enviar = findViewById(R.id.button2);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (serverSocket.isClosed()) return;
                        BufferedWriter miEscritor = null;
                        try {
                            Socket socketE = new Socket("127.0.0.1", 1234);
                            miEscritor = new BufferedWriter(new OutputStreamWriter(socketE.getOutputStream()));

                            miEscritor.write("Mensaje enviado");
                            miEscritor.flush();//Fuerza la escritura
                            miEscritor.close();
                            socketE.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        Button parar = findViewById(R.id.button3);

        parar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (serverSocket.isClosed()) return;
                        try {
                            serverSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
}