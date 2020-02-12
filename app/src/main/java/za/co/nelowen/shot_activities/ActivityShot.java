package za.co.nelowen.shot_activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import za.co.nelowen.R;

public class ActivityShot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shot);
    }

    public void endShot(View view) {
        Log.v(this.getClass().toString(), "Ending shot!");
        finish();
    }
}
