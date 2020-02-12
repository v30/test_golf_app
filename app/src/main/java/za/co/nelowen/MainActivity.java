package za.co.nelowen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createTables();
    }

    private void createTables() {

    }

    public void startNextActivity(View view) {
        Intent intent = new Intent(this, ActivityRound.class);
        startActivity(intent);
    }
}
