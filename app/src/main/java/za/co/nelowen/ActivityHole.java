package za.co.nelowen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import za.co.nelowen.helper_classes.DBHelper;
import za.co.nelowen.shot_activities.ActivityShot;

import static za.co.nelowen.helper_classes.JavaConstants.HOLE_NUMBER;
import static za.co.nelowen.helper_classes.JavaConstants.HOLE_PAR;
import static za.co.nelowen.helper_classes.JavaConstants.HOLE_STROKE;
import static za.co.nelowen.helper_classes.JavaConstants.PLAYER_HANDICAP;

public class ActivityHole extends AppCompatActivity {
    private DBHelper dbHelper = new DBHelper(this);
    private int shotNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hole);
    }

    public void startShot(View view) {
        Intent roundIntent = getIntent();
        int holeNumber = roundIntent.getIntExtra(HOLE_NUMBER, 0);
        shotNumber++;
        Intent intent = new Intent(this, ActivityShot.class);
        startActivity(intent);
    }

    public void endHole(View view) {
        // write the hole data to the database
        Intent roundIntent = getIntent();
        int holeNumber = roundIntent.getIntExtra(HOLE_NUMBER, 0);
        int holePar = roundIntent.getIntExtra(HOLE_PAR, 0);
        int holeStroke = roundIntent.getIntExtra(HOLE_STROKE, 0);
        int stablefordScore = calculateStableford(holePar, holeStroke);
        int medalScore = calculateMedalScore(holePar, holeStroke);

        // write the hole number with FK round_number, par, stroke, medal, stableford
        System.out.println("[HOLE NUMBER]: "+ holeNumber);
        System.out.println("[HOLE PAR]: "+ holePar);
        System.out.println("[HOLE STROKE]: "+ holeStroke);
        System.out.println("[SF SCORE]: "+ stablefordScore);
        System.out.println("[MED SCORE]: "+ medalScore);
        System.out.println("[TRUE SCORE]: "+ shotNumber);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long result = dbHelper.insertHole(db, 1, holeNumber, holePar, holeStroke, medalScore, stablefordScore, shotNumber);
        System.out.println("[HOLE RESULT]: "+result);

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
