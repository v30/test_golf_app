package za.co.nelowen;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import za.co.nelowen.helper_classes.DBHelper;
import za.co.nelowen.helper_classes.Util;

import static za.co.nelowen.helper_classes.JavaConstants.HOLE_NUMBER;
import static za.co.nelowen.helper_classes.JavaConstants.HOLE_PAR;
import static za.co.nelowen.helper_classes.JavaConstants.HOLE_STROKE;
import static za.co.nelowen.helper_classes.JavaConstants.ROUND_ID;

public class ActivityRound extends AppCompatActivity {
    private DBHelper dbHelper = new DBHelper(this);

    private int holeNumber = 0;
    private EditText etHolePar, etHoleStroke;
    private String roundId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);
        etHolePar = this.findViewById(R.id.editTextHolePar);
        etHoleStroke = this.findViewById(R.id.editTextHoleStroke);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        etHolePar.requestFocus();
        etHolePar.setText("");
        etHoleStroke.setText("");
        roundId = Util.generateGuid();
    }

    public void startHole(View view) {
        if (holeNumber < 18) {
            holeNumber++;
            Intent intent = new Intent(this, ActivityHole.class);
            intent.putExtra(ROUND_ID, roundId);
            intent.putExtra(HOLE_NUMBER, holeNumber);

            int holePar;
            int holeStroke;
            if (!etHolePar.getText().toString().isEmpty()) {
                holePar = Integer.parseInt(etHolePar.getText().toString());
                intent.putExtra(HOLE_PAR, holePar);
            }
            if (!etHoleStroke.getText().toString().isEmpty()) {
                holeStroke = Integer.parseInt(etHoleStroke.getText().toString());
                intent.putExtra(HOLE_STROKE, holeStroke);
            }

            startActivity(intent);
        }
        else
            Toast.makeText(this, "YOU HAVE PLAYED 18 HOLES", Toast.LENGTH_SHORT).show();
    }

    public void endRound(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long result = dbHelper.insertRound(db, roundId, "Irene", 72, 8, 6, 27, 9, 27);

        String message = (result == 1) ? "ADDED ROUND TO THE DATABASE!" : "SOMETHING WENT WRONG WRITING TO THE DATABASE!";
        Log.v(this.getClass().toString(), message);

        finish();
    }
}
