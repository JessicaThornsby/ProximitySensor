package com.jessicathornsby.proximitysensor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements SensorEventListener {

    private Sensor proximitySensor;
    private SensorManager proximitySensorManager;
    private TextView proximityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        proximityTextView = (TextView) findViewById(R.id.proximityTextView);

        proximitySensorManager = (SensorManager) getSystemService(
                Context.SENSOR_SERVICE);

        proximitySensor = proximitySensorManager.getDefaultSensor(
                Sensor.TYPE_PROXIMITY);
        String sensor_error = getResources().getString(R.string.no_sensor);

        if (proximitySensor == null) {
            proximityTextView.setText(sensor_error);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (proximitySensor != null) {
            proximitySensorManager.registerListener(this, proximitySensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        proximitySensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float currentValue = sensorEvent.values[0];
        proximityTextView.setText(getResources().getString(
                R.string.proximity_sensor, currentValue));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

        //To do//

    }
}