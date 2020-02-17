package za.co.nelowen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import za.co.nelowen.helper_classes.DBHelper;

public class MainActivity extends AppCompatActivity {
    private DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createTables();
//        dropAllTables();
    }

    private void createTables() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.settingsTableCreate(db);
        dbHelper.playerTableCreate(db);
        dbHelper.roundTableCreate(db);
        dbHelper.holeTableCreate(db);
        dbHelper.shotTableCreate(db);
    }

    private void dropAllTables() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.dropTables(db, new String[]{"settings", "players", "rounds", "holes", "shots"});
        Log.v(this.getClass().toString(), "TABLES DROPPED!!!");
    }

    public void startRound(View view) {
        Intent intent = new Intent(this, ActivityRound.class);
        startActivity(intent);
    }
}
