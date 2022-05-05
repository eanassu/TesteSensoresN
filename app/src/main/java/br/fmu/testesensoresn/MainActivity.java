package br.fmu.testesensoresn;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textViewX;
    private TextView textViewY;
    private TextView textViewZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewX = findViewById(R.id.textViewX);
        textViewY = findViewById(R.id.textViewY);
        textViewZ = findViewById(R.id.textViewZ);

        SensorManager mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> lista = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        for( Sensor sensor: lista ) {
            System.out.println(sensor.getName());
        }
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];
                float z = sensorEvent.values[2];
                textViewX.setText(Float.toString(x));
                textViewY.setText(Float.toString(y));
                textViewZ.setText(Float.toString(z));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) { }
        }, sensor, SensorManager.SENSOR_DELAY_FASTEST);

    }
}