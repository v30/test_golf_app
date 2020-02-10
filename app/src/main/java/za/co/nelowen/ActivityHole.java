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
        Intent roundIntent = getIntent();
        int holeNumber = roundIntent.getIntExtra(ActivityRound.HOLE_NUMBER, 0);
        System.out.println("[HOLE NUMBER]: "+ holeNumber);
        shotNumber++;
        Intent intent = new Intent(this, ActivityShot.class);
        intent.putExtra("hole_number", shotNumber);
        startActivity(intent);
        System.out.println("[SHOT NUMBER]: "+ shotNumber);
    }

    public void endHole(View view) {
        finish();
    }
}
