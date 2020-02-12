package za.co.nelowen.helper_classes;

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedHashMap;
import java.util.Map;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void settingsTableCreate(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder();

        query.append("CREATE TABLE IF NOT EXISTS ");
        query.append("settings ");
        query.append("(");
        query.append("setting_id INT NOT NULL AUTO_INCREMENT, ");
        query.append("setting_name VARCHAR(255) NOT NULL, ");
        query.append("setting_value VARCHAR(255) NOT NULL, ");
        query.append("PRIMARY KEY(setting_id)");
        query.append(")");

        db.execSQL(query.toString());
    }

    public int numberOfRows(String tableName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, tableName);
    }

    // I need the following tables
    //      * Settings
    //      * User
    //      * Round
    //      * Hole
    //      * Shot
}