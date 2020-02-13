package za.co.nelowen.helper_classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "golf_stat_tracker";
    private static final int VERSION = 1;

    private static final String ROUNDS = "rounds";
    private static final String ROUND_ID = "round_id";
    private static final String COURSE_NAME = "course_name";
    private static final String COURSE_PAR = "course_par";
    private static final String MEDAL_SCORE = "medal_score";
    private static final String STABLEFORD = "stableford_score";
    private static final String FAIRWAYS = "fairways_hit";
    private static final String GIR = "gir";
    private static final String PUTTS = "putts";

    private static final String HOLES = "holes";
    private static final String HOLE_ID = "hole_id";
    private static final String HOLE_NUMBER = "hole_number";
    private static final String HOLE_PAR = "hole_par";
    private static final String HOLE_STROKE = "hole_stroke";
    private static final String HOLE_MEDAL = "medal_score";
    private static final String HOLE_STABLE = "stableford_score";
    private static final String HOLE_TRUE = "true_score";

    private static final String SHOTS = "shots";
    private static final String SHOT_ID = "shot_id";
    private static final String SHOT_NUMBER = "shot_number";
    private static final String SHOT_TYPE = "shot_type";
    private static final String TARGET_DISTANCE = "target_distance";
    private static final String CLUB_HIT = "club_hit";
    private static final String BALL_FLIGHT = "ball_flight";
    private static final String OUTCOME = "outcome";


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
//        query.append("PRIMARY KEY(setting_id)");
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
        query.append("round_id INTEGER PRIMARY KEY AUTOINCREMENT, ");
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

    public long insertRound(SQLiteDatabase db, String courseName, int coursePar, int fairways, int gir, int putts, int medal, int stableford) {
        ContentValues values = new ContentValues();
        values.put(COURSE_NAME, courseName);
        values.put(COURSE_PAR, coursePar);
        values.put(FAIRWAYS, fairways);
        values.put(GIR, gir);
        values.put(PUTTS, putts);
        values.put(MEDAL_SCORE, medal);
        values.put(STABLEFORD, stableford);

        return db.insert(ROUNDS, null, values);
    }

    public long insertHole(SQLiteDatabase db, int roundId, int holeNumber, int holePar, int holeStroke, int holeMedal, int holeStable, int holeTrue) {
        ContentValues values = new ContentValues();
        values.put(ROUND_ID, roundId);
        values.put(HOLE_NUMBER, holeNumber);
        values.put(HOLE_PAR, holePar);
        values.put(HOLE_STROKE, holeStroke);
        values.put(HOLE_MEDAL, holeMedal);
        values.put(HOLE_STABLE, holeStable);
        values.put(HOLE_TRUE, holeTrue);

        return db.insert(HOLES, null, values);
    }

    public long insertShot(SQLiteDatabase db, int roundId, int holeId, int shotNumber, String shotType, int targetDistance, String clubHit, String ballFlight, String outcome) {
        ContentValues values = new ContentValues();
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