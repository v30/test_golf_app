package za.co.nelowen.shot_activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import za.co.nelowen.R;
import za.co.nelowen.helper_classes.DBHelper;
import za.co.nelowen.helper_classes.Util;

import static za.co.nelowen.helper_classes.JavaConstants.CLUB_HIT;
import static za.co.nelowen.helper_classes.JavaConstants.HOLE_ID;
import static za.co.nelowen.helper_classes.JavaConstants.ROUND_ID;
import static za.co.nelowen.helper_classes.JavaConstants.SHOT_NUMBER;
import static za.co.nelowen.helper_classes.JavaConstants.SHOT_TYPE;
import static za.co.nelowen.helper_classes.JavaConstants.TARGET_DISTANCE;

public class ActivityShotOutcome extends AppCompatActivity implements View.OnClickListener {
    private DBHelper dbHelper = new DBHelper(this);
    private String ballFlight;
    private String outcome;
    private String roundId;
    private String holeId;
    private String shotType;
    private String clubHit;
    private int targetDistance;
    private int shotNumber;
    private Button btnHD, btnH, btnHF, btnD, btnS, btnF, btnLD, btnL, btnLF;
    private Button btnOutcomeFairway, btnOutcomeGreen, btnbtnOutcomeBunker, btnbtnOutcomeHazard, btnbtnOutcomeFringe, btnOutcomeRough, btnOutcomeHole, btnOutcomeDuff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outcome);
        initButtons();
        getIntentValues();
    }

    private void getIntentValues() {
        Intent holeIntent = getIntent();
        roundId = holeIntent.getStringExtra(ROUND_ID);
        holeId = holeIntent.getStringExtra(HOLE_ID);
        shotNumber = holeIntent.getIntExtra(SHOT_NUMBER, 0);
        shotType = holeIntent.getStringExtra(SHOT_TYPE);
        clubHit = holeIntent.getStringExtra(CLUB_HIT);
        targetDistance = holeIntent.getIntExtra(TARGET_DISTANCE, 0);
    }

    private void initButtons() {
        btnHD = findViewById(R.id.btnHD);
        btnH = findViewById(R.id.btnH);
        btnHF = findViewById(R.id.btnHF);
        btnD = findViewById(R.id.btnD);
        btnS = findViewById(R.id.btnS);
        btnF = findViewById(R.id.btnF);
        btnLD = findViewById(R.id.btnLD);
        btnL = findViewById(R.id.btnL);
        btnLF = findViewById(R.id.btnLF);
        btnOutcomeFairway = findViewById(R.id.btnOutcomeFairway);
        btnOutcomeGreen = findViewById(R.id.btnOutcomeGreen);
        btnbtnOutcomeBunker = findViewById(R.id.btnbtnOutcomeBunker);
        btnbtnOutcomeHazard = findViewById(R.id.btnbtnOutcomeHazard);
        btnbtnOutcomeFringe = findViewById(R.id.btnbtnOutcomeFringe);
        btnOutcomeRough = findViewById(R.id.btnOutcomeRough);
        btnOutcomeHole = findViewById(R.id.btnOutcomeHole);
        btnOutcomeDuff = findViewById(R.id.btnOutcomeDuff);
        btnHD.setOnClickListener(this);
        btnH.setOnClickListener(this);
        btnHF.setOnClickListener(this);
        btnD.setOnClickListener(this);
        btnS.setOnClickListener(this);
        btnF.setOnClickListener(this);
        btnLD.setOnClickListener(this);
        btnL.setOnClickListener(this);
        btnLF.setOnClickListener(this);
        btnOutcomeFairway.setOnClickListener(this);
        btnOutcomeGreen.setOnClickListener(this);
        btnbtnOutcomeBunker.setOnClickListener(this);
        btnbtnOutcomeHazard.setOnClickListener(this);
        btnbtnOutcomeFringe.setOnClickListener(this);
        btnOutcomeRough.setOnClickListener(this);
        btnOutcomeHole.setOnClickListener(this);
        btnOutcomeDuff.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHD:
                ballFlight = "HD";
                break;
            case R.id.btnH:
                ballFlight = "H";
                break;
            case R.id.btnHF:
                ballFlight = "HF";
                break;
            case R.id.btnD:
                ballFlight = "D";
                break;
            case R.id.btnS:
                ballFlight = "S";
                break;
            case R.id.btnF:
                ballFlight = "F";
                break;
            case R.id.btnLD:
                ballFlight = "LD";
                break;
            case R.id.btnL:
                ballFlight = "L";
                break;
            case R.id.btnLF:
                ballFlight = "LF";
                break;
            case R.id.btnOutcomeFairway:
                outcome = btnOutcomeFairway.getText().toString();
                endShot();
                break;
            case R.id.btnOutcomeGreen:
                outcome = btnOutcomeGreen.getText().toString();
                endShot();
                break;
            case R.id.btnbtnOutcomeBunker:
                outcome = btnbtnOutcomeBunker.getText().toString();
                endShot();
                break;
            case R.id.btnbtnOutcomeHazard:
                outcome = btnbtnOutcomeHazard.getText().toString();
                endShot();
                break;
            case R.id.btnbtnOutcomeFringe:
                outcome = btnbtnOutcomeFringe.getText().toString();
                endShot();
                break;
            case R.id.btnOutcomeRough:
                outcome = btnOutcomeRough.getText().toString();
                endShot();
                break;
            case R.id.btnOutcomeHole:
                outcome = btnOutcomeHole.getText().toString();
                endShot();
                break;
            case R.id.btnOutcomeDuff:
                outcome = btnOutcomeDuff.getText().toString();
                endShot();
                break;
        }
    }

    public void endShot() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String shotId = Util.generateGuid();
        long result = dbHelper.insertShot(db, shotId, roundId, holeId, shotNumber, shotType, targetDistance, clubHit, ballFlight, outcome);

        String message = (result == 1) ? "ADDED ROUND TO THE DATABASE!" : "SOMETHING WENT WRONG WRITING TO THE DATABASE!";
        Log.v(this.getClass().toString(), message);

        Intent intent = new Intent(this, ActivityShot.class);
        intent.putExtra(ROUND_ID, roundId);
        intent.putExtra(HOLE_ID, holeId);
        intent.putExtra(SHOT_NUMBER, shotNumber);
        startActivity(intent);

        finish();
    }
}