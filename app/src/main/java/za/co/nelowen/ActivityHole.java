package za.co.nelowen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import za.co.nelowen.helper_classes.DBHelper;
import za.co.nelowen.helper_classes.Util;
import za.co.nelowen.shot_activities.ActivityShot;

import static za.co.nelowen.helper_classes.JavaConstants.HOLE_ID;
import static za.co.nelowen.helper_classes.JavaConstants.HOLE_NUMBER;
import static za.co.nelowen.helper_classes.JavaConstants.HOLE_PAR;
import static za.co.nelowen.helper_classes.JavaConstants.HOLE_STROKE;
import static za.co.nelowen.helper_classes.JavaConstants.PLAYER_HANDICAP;
import static za.co.nelowen.helper_classes.JavaConstants.ROUND_ID;
import static za.co.nelowen.helper_classes.JavaConstants.SHOT_NUMBER;

public class ActivityHole extends AppCompatActivity {
    private DBHelper dbHelper = new DBHelper(this);
    private int shotNumber = 0;
    private int holeNumber;
    private String roundId;
    private int holePar;
    private int holeStroke;
    private String holeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hole);
        getDataFromIntent();
    }

    private void getDataFromIntent() {
        Intent roundIntent = getIntent();
        holeNumber = roundIntent.getIntExtra(HOLE_NUMBER, 0);
        roundId = roundIntent.getStringExtra(ROUND_ID);
        holeNumber = roundIntent.getIntExtra(HOLE_NUMBER, 0);
        holePar = roundIntent.getIntExtra(HOLE_PAR, 0);
        holeStroke = roundIntent.getIntExtra(HOLE_STROKE, 0);
    }

    public void startShot(View view) {
        shotNumber++;
        holeId = Util.generateGuid();
        Intent intent = new Intent(this, ActivityShot.class);
        intent.putExtra(ROUND_ID, roundId);
        intent.putExtra(HOLE_ID, holeId);
        intent.putExtra(SHOT_NUMBER, shotNumber);
        startActivity(intent);
    }

    public void endHole(View view) {
        int stablefordScore = calculateStableford(holePar, holeStroke);
        int medalScore = calculateMedalScore(holePar, holeStroke);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long result = dbHelper.insertHole(db, holeId, roundId, holeNumber, holePar, holeStroke, medalScore, stablefordScore, shotNumber);

        String message = (result == 1) ? "ADDED ROUND TO THE DATABASE!" : "SOMETHING WENT WRONG WRITING TO THE DATABASE!";
        Log.v(this.getClass().toString(), message);

        finish();
    }

    private int calculateMedalScore(int par, int stroke) {
        int result = -1000;
        if (stroke > 0) {
            if (stroke <= PLAYER_HANDICAP) {
                if (shotNumber == par - 2)
                    result = -3;
                else if (shotNumber == par - 1)
                    result = -2;
                else if (shotNumber == par)
                    result = -1;
                else if (shotNumber == par + 1)
                    result = 0;
                else if (shotNumber == par + 2)
                    result = 1;
                else if (shotNumber >= par + 3)
                    result = 2;
            }
            else {
                if (shotNumber == par - 2)
                    result = -2;
                else if (shotNumber == par - 1)
                    result = -1;
                else if (shotNumber == par)
                    result = 0;
                else if (shotNumber == par + 1)
                    result = 1;
                else if (shotNumber >= par + 2)
                    result = 2;
            }
        }
        return result;
    }

    public int calculateStableford(int par, int stroke) {
        int result = -1000;
        if (stroke > 0) {
            if (stroke <= PLAYER_HANDICAP) {
                if (shotNumber == par - 2)
                    result = 5;
                else if (shotNumber == par - 1)
                    result = 4;
                else if (shotNumber == par)
                    result = 3;
                else if (shotNumber == par + 1)
                    result = 2;
                else if (shotNumber == par + 2)
                    result = 1;
                else if (shotNumber >= par + 3)
                    result = 0;
            }
            else {
                if (shotNumber == par - 2)
                    result = 4;
                else if (shotNumber == par - 1)
                    result = 3;
                else if (shotNumber == par)
                    result = 2;
                else if (shotNumber == par + 1)
                    result = 1;
                else if (shotNumber >= par + 2)
                    result = 0;
            }
        }
        return result;
    }
}
