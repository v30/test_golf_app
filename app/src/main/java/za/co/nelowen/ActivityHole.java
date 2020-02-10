package za.co.nelowen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityHole extends AppCompatActivity {
    private int shotNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hole);
    }

    public void startShot(View view) {
        shotNumber++;
        Intent intent = new Intent(this, ActivityShot.class);
        startActivity(intent);
        System.out.println("[SHOT NUMBER]: "+ shotNumber);
    }

    public void endHole(View view) {
        finish();
    }
}
