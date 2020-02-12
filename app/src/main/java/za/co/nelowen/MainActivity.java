package za.co.nelowen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import za.co.nelowen.helper_classes.DBHelper;

public class MainActivity extends AppCompatActivity {
    private DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createTables();
        System.out.println(getApplicationContext().getPackageName());
    }

    private void createTables() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.settingsTableCreate(db);
        dbHelper.playerTableCreate(db);
        dbHelper.roundTableCreate(db);
        dbHelper.holeTableCreate(db);
        dbHelper.shotTableCreate(db);
    }

    public void startRound(View view) {
        Intent intent = new Intent(this, ActivityRound.class);
        startActivity(intent);
    }
}
