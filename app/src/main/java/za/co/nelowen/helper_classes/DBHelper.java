package za.co.nelowen.helper_classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static za.co.nelowen.helper_classes.JavaConstants.BALL_FLIGHT;
import static za.co.nelowen.helper_classes.JavaConstants.CLUB_HIT;
import static za.co.nelowen.helper_classes.JavaConstants.COURSE_NAME;
import static za.co.nelowen.helper_classes.JavaConstants.COURSE_PAR;
import static za.co.nelowen.helper_classes.JavaConstants.DB_NAME;
import static za.co.nelowen.helper_classes.JavaConstants.FAIRWAYS;
import static za.co.nelowen.helper_classes.JavaConstants.GIR;
import static za.co.nelowen.helper_classes.JavaConstants.HOLES;
import static za.co.nelowen.helper_classes.JavaConstants.HOLE_ID;
import static za.co.nelowen.helper_classes.JavaConstants.HOLE_MEDAL;
import static za.co.nelowen.helper_classes.JavaConstants.HOLE_NUMBER;
import static za.co.nelowen.helper_classes.JavaConstants.HOLE_PAR;
import static za.co.nelowen.helper_classes.JavaConstants.HOLE_STABLE;
import static za.co.nelowen.helper_classes.JavaConstants.HOLE_STROKE;
import static za.co.nelowen.helper_classes.JavaConstants.HOLE_TRUE;
import static za.co.nelowen.helper_classes.JavaConstants.MEDAL_SCORE;
import static za.co.nelowen.helper_classes.JavaConstants.OUTCOME;
import static za.co.nelowen.helper_classes.JavaConstants.PUTTS;
import static za.co.nelowen.helper_classes.JavaConstants.ROUNDS;
import static za.co.nelowen.helper_classes.JavaConstants.ROUND_ID;
import static za.co.nelowen.helper_classes.JavaConstants.SHOTS;
import static za.co.nelowen.helper_classes.JavaConstants.SHOT_ID;
import static za.co.nelowen.helper_classes.JavaConstants.SHOT_NUMBER;
import static za.co.nelowen.helper_classes.JavaConstants.SHOT_TYPE;
import static za.co.nelowen.helper_classes.JavaConstants.STABLEFORD;
import static za.co.nelowen.helper_classes.JavaConstants.TARGET_DISTANCE;
import static za.co.nelowen.helper_classes.JavaConstants.VERSION;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void settingsTableCreate(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder();

        query.append("CREATE TABLE IF NOT EXISTS ");
        query.append("settings ");
        query.append("(");
        query.append("setting_id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        query.append("setting_name VARCHAR(255) NOT NULL, ");
        query.append("setting_value VARCHAR(255) NOT NULL");
        query.append(")");

        db.execSQL(query.toString());
    }

    public void playerTableCreate(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder();

        query.append("CREATE TABLE IF NOT EXISTS ");
        query.append("players ");
        query.append("(");
        query.append("player_id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        query.append("player_name VARCHAR(255) NOT NULL, ");
        query.append("player_handicap INT NOT NULL");
        query.append(")");

        db.execSQL(query.toString());
    }

    public void roundTableCreate(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder();

        query.append("CREATE TABLE IF NOT EXISTS ");
        query.append("rounds ");
        query.append("(");
        query.append("round_id VARCHAR(255) PRIMARY KEY, ");
        query.append("course_name VARCHAR(255) NOT NULL, ");
        query.append("course_par INT NOT NULL, ");
        query.append("medal_score INT NOT NULL, ");
        query.append("stableford_score INT NOT NULL, ");
        query.append("fairways_hit INT NOT NULL, ");
        query.append("gir INT NOT NULL, ");
        query.append("putts INT NOT NULL");
        query.append(")");

        db.execSQL(query.toString());
    }

    public void holeTableCreate(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder();

        query.append("CREATE TABLE IF NOT EXISTS ");
        query.append("holes ");
        query.append("(");
        query.append("hole_id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        query.append("round_id INT NOT NULL, ");
        query.append("hole_number INT NOT NULL, ");
        query.append("hole_par INT NOT NULL, ");
        query.append("hole_stroke INT NOT NULL, ");
        query.append("medal_score INT NOT NULL, ");
        query.append("stableford_score INT NOT NULL, ");
        query.append("true_score INT NOT NULL");
        query.append(")");

        db.execSQL(query.toString());
    }

    public void shotTableCreate(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder();

        query.append("CREATE TABLE IF NOT EXISTS ");
        query.append("shots ");
        query.append("(");
        query.append("shot_id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        query.append("round_id INT NOT NULL, ");
        query.append("hole_id INT NOT NULL, ");
        query.append("shot_number INT NOT NULL, ");
        query.append("shot_type VARCHAR(35) NOT NULL, ");
        query.append("target_distance INT NOT NULL, ");
        query.append("club_hit VARCHAR(2) NOT NULL, ");
        query.append("ball_flight VARCHAR(2) NOT NULL, ");
        query.append("outcome VARCHAR(20) NOT NULL");
        query.append(")");

        db.execSQL(query.toString());
    }

    public void dropTable(SQLiteDatabase db, String tableName) {
        String deleteString = "DROP TABLE IF EXISTS " + tableName;
        db.execSQL(deleteString);
    }

    public int numberOfRows(String tableName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, tableName);
    }

    public long insertRound(SQLiteDatabase db, String roundId, String courseName, int coursePar, int fairways, int gir, int putts, int medal, int stableford) {
        ContentValues values = new ContentValues();
        values.put(ROUND_ID, roundId);
        values.put(COURSE_NAME, courseName);
        values.put(COURSE_PAR, coursePar);
        values.put(FAIRWAYS, fairways);
        values.put(GIR, gir);
        values.put(PUTTS, putts);
        values.put(MEDAL_SCORE, medal);
        values.put(STABLEFORD, stableford);

        return db.insert(ROUNDS, null, values);
    }

    public long insertHole(SQLiteDatabase db, String holeId, String roundId, int holeNumber, int holePar, int holeStroke, int holeMedal, int holeStable, int holeTrue) {
        ContentValues values = new ContentValues();
        values.put(HOLE_ID, holeId);
        values.put(ROUND_ID, roundId);
        values.put(HOLE_NUMBER, holeNumber);
        values.put(HOLE_PAR, holePar);
        values.put(HOLE_STROKE, holeStroke);
        values.put(HOLE_MEDAL, holeMedal);
        values.put(HOLE_STABLE, holeStable);
        values.put(HOLE_TRUE, holeTrue);

        return db.insert(HOLES, null, values);
    }

    public long insertShot(SQLiteDatabase db, String shotId, String roundId, String holeId, int shotNumber, String shotType, int targetDistance, String clubHit, String ballFlight, String outcome) {
        ContentValues values = new ContentValues();
        values.put(SHOT_ID, shotId);
        values.put(ROUND_ID, roundId);
        values.put(HOLE_ID, holeId);
        values.put(SHOT_NUMBER, shotNumber);
        values.put(SHOT_TYPE, shotType);
        values.put(TARGET_DISTANCE, targetDistance);
        values.put(CLUB_HIT, clubHit);
        values.put(BALL_FLIGHT, ballFlight);
        values.put(OUTCOME, outcome);

        return db.insert(SHOTS, null, values);
    }
}