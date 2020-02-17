package za.co.nelowen.shot_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import za.co.nelowen.R;

import static za.co.nelowen.helper_classes.JavaConstants.CLUB_HIT;
import static za.co.nelowen.helper_classes.JavaConstants.HOLE_ID;
import static za.co.nelowen.helper_classes.JavaConstants.ROUND_ID;
import static za.co.nelowen.helper_classes.JavaConstants.SHOT_NUMBER;
import static za.co.nelowen.helper_classes.JavaConstants.SHOT_TYPE;
import static za.co.nelowen.helper_classes.JavaConstants.TARGET_DISTANCE;

public class ActivityShot extends AppCompatActivity implements View.OnClickListener{
    private String roundId;
    private String holeId;
    private String shotType;
    private String clubHit;
    private int shotNumber;
    private Button btnClubDriver, btnClub3W, btnClub5W, btnClub3H, btnClub4H, btnClub5H, btnClub2I, btnClub3I, btnClub4I, btnClub5I, btnClub6I, btnClub7I, btnClub8I, btnClub9I, btnClubPW, btnClubSW, btnClubGW, btnClubLW;
    private Button btnShotTypeDrive, btnShotTypeApproach, btnShotTypeLayup, btnShotTypePitch, btnShotTypeChip, btnShotTypePutt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shot);
        getIntentValues();
        initButtons();
    }

    private void initButtons() {
        btnClubDriver = findViewById(R.id.btnClubDriver);
        btnClub3W = findViewById(R.id.btnClub3W);
        btnClub5W = findViewById(R.id.btnClub5W);
        btnClub3H = findViewById(R.id.btnClub3H);
        btnClub4H = findViewById(R.id.btnClub4H);
        btnClub5H = findViewById(R.id.btnClub5H);
        btnClub2I = findViewById(R.id.btnClub2I);
        btnClub3I = findViewById(R.id.btnClub3I);
        btnClub4I = findViewById(R.id.btnClub4I);
        btnClub5I = findViewById(R.id.btnClub5I);
        btnClub6I = findViewById(R.id.btnClub6I);
        btnClub7I = findViewById(R.id.btnClub7I);
        btnClub8I = findViewById(R.id.btnClub8I);
        btnClub9I = findViewById(R.id.btnClub9I);
        btnClubPW = findViewById(R.id.btnClubPW);
        btnClubSW = findViewById(R.id.btnClubSW);
        btnClubGW = findViewById(R.id.btnClubGW);
        btnClubLW = findViewById(R.id.btnClubLW);
        btnShotTypeDrive = findViewById(R.id.btnShotTypeDrive);
        btnShotTypeApproach = findViewById(R.id.btnShotTypeApproach);
        btnShotTypeLayup = findViewById(R.id.btnShotTypeLayup);
        btnShotTypePitch = findViewById(R.id.btnShotTypePitch);
        btnShotTypeChip = findViewById(R.id.btnShotTypeChip);
        btnShotTypePutt = findViewById(R.id.btnShotTypePutt);
        btnClubDriver.setOnClickListener(this);
        btnClub3W.setOnClickListener(this);
        btnClub5W.setOnClickListener(this);
        btnClub3H.setOnClickListener(this);
        btnClub4H.setOnClickListener(this);
        btnClub5H.setOnClickListener(this);
        btnClub2I.setOnClickListener(this);
        btnClub3I.setOnClickListener(this);
        btnClub4I.setOnClickListener(this);
        btnClub5I.setOnClickListener(this);
        btnClub6I.setOnClickListener(this);
        btnClub7I.setOnClickListener(this);
        btnClub8I.setOnClickListener(this);
        btnClub9I.setOnClickListener(this);
        btnClubPW.setOnClickListener(this);
        btnClubSW.setOnClickListener(this);
        btnClubGW.setOnClickListener(this);
        btnClubLW.setOnClickListener(this);
        btnShotTypeDrive.setOnClickListener(this);
        btnShotTypeApproach.setOnClickListener(this);
        btnShotTypeLayup.setOnClickListener(this);
        btnShotTypePitch.setOnClickListener(this);
        btnShotTypeChip.setOnClickListener(this);
        btnShotTypePutt.setOnClickListener(this);
    }

    private void getIntentValues() {
        Intent holeIntent = getIntent();
        roundId = holeIntent.getStringExtra(ROUND_ID);
        holeId = holeIntent.getStringExtra(HOLE_ID);
        shotNumber = holeIntent.getIntExtra(SHOT_NUMBER, 0);
    }

    public void getOutcome() {
        EditText etTargetDistance = findViewById(R.id.etDistanceToPin);
        int targetDistance = Integer.parseInt(etTargetDistance.getText().toString());
        Intent intent = new Intent(this, ActivityShotOutcome.class);
        intent.putExtra(ROUND_ID, roundId);
        intent.putExtra(HOLE_ID, holeId);
        intent.putExtra(SHOT_NUMBER, shotNumber);
        intent.putExtra(SHOT_TYPE, shotType);
        intent.putExtra(CLUB_HIT, clubHit);
        intent.putExtra(TARGET_DISTANCE, targetDistance);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnClubDriver:
                 clubHit = "DR";
                getOutcome();
                break;
            case R.id.btnClub3W:
                clubHit = "3W";
                getOutcome();
                break;
            case R.id.btnClub5W:
                clubHit = "5W";
                getOutcome();
                break;
            case R.id.btnClub3H:
                clubHit = "3H";
                getOutcome();
                break;
            case R.id.btnClub4H:
                clubHit = "4H";
                getOutcome();
                break;
            case R.id.btnClub5H:
                clubHit = "5H";
                getOutcome();
                break;
            case R.id.btnClub2I:
                clubHit = "2I";
                getOutcome();
                break;
            case R.id.btnClub3I:
                clubHit = "3I";
                getOutcome();
                break;
            case R.id.btnClub4I:
                clubHit = "4I";
                getOutcome();
                break;
            case R.id.btnClub5I:
                clubHit = "5I";
                getOutcome();
                break;
            case R.id.btnClub6I:
                clubHit = "6I";
                getOutcome();
                break;
            case R.id.btnClub7I:
                clubHit = "7I";
                getOutcome();
                break;
            case R.id.btnClub8I:
                clubHit = "8I";
                getOutcome();
                break;
            case R.id.btnClub9I:
                clubHit = "9I";
                getOutcome();
                break;
            case R.id.btnClubPW:
                clubHit = "PW";
                getOutcome();
                break;
            case R.id.btnClubSW:
                clubHit = "SW";
                getOutcome();
                break;
            case R.id.btnClubGW:
                clubHit = "GW";
                getOutcome();
                break;
            case R.id.btnShotTypeDrive:
                shotType = "LW";
                break;
            case R.id.btnShotTypeApproach:
                shotType = btnShotTypeApproach.getText().toString();
                break;
            case R.id.btnShotTypeLayup:
                shotType = btnShotTypeLayup.getText().toString();
                break;
            case R.id.btnShotTypePitch:
                shotType = btnShotTypePitch.getText().toString();
                break;
            case R.id.btnShotTypeChip:
                shotType = btnShotTypeChip.getText().toString();
                break;
            case R.id.btnShotTypePutt:
                shotType = btnShotTypePutt.getText().toString();
                break;
        }
    }
}
