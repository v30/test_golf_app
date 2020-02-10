package za.co.nelowen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityRound extends AppCompatActivity {
    private int holeNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);
    }

    public void startHole(View view) {
        holeNumber++;
        Intent intent = new Intent(this, ActivityHole.class);
        startActivity(intent);
        System.out.println("[HOLE NUMBER]: "+ holeNumber);
    }
}
