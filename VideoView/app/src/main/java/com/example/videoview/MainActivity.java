package com.example.videoview;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = (VideoView) findViewById(R.id.idVideoView);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video;
        Uri videoUri = Uri.parse(videoPath);
        videoView.setVideoURI(videoUri);

        videoView.start();

    }
}