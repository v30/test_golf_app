package za.co.nelowen.shot_activities;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import za.co.nelowen.R;
import za.co.nelowen.helper_classes.DBHelper;

public class ActivityShot extends AppCompatActivity {
    private DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shot);
    }

    public void endShot(View view) {
        Log.v(this.getClass().toString(), "Ending shot!");
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long result = dbHelper.insertShot(db, 1, 1, 1, "Drive", 278, "Driver", "HD", "Fairway");
        System.out.println("[SHOT RESULT]: "+result);
        finish();
    }
}
