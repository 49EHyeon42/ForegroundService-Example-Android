package dev.ehyeon.foregroundserviceexampleapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startServiceButton = findViewById(R.id.start_service_button);
        Button stopServiceButton = findViewById(R.id.stop_service_button);

        Intent intent = new Intent(this, ForegroundService.class);

        startServiceButton.setOnClickListener(view -> startForegroundService(intent));

        stopServiceButton.setOnClickListener(view -> stopService(intent));
    }
}
