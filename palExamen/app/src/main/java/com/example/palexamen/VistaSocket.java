package com.example.palexamen;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class VistaSocket extends AppCompatActivity {
    ServerSocket sv;
    Socket socket;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        Button b = findViewById(R.id.SocketBtnEncender);
        TextView tv = findViewById(R.id.SocketTv);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            sv = new ServerSocket(1234);

                            while (!sv.isClosed()) {

                                socket = sv.accept();
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
        b = findViewById(R.id.SocketBtnEnviar);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (sv.isClosed()) return;
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
        b=findViewById(R.id.SocketBtnApagar);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (sv.isClosed()) return;
                        try {
                            sv.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

    }
}
