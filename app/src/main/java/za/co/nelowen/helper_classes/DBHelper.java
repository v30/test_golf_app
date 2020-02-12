package za.co.nelowen.helper_classes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public void tableCreate(SQLiteDatabase db, String tableName) {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE IF NOT EXISTS ");
        builder.append(tableName + " ");
        builder.append("(");
        builder.append(tableName + "_hash VARCHAR(255) PRIMARY KEY, ");
        builder.append(tableName + "_name VARCHAR(255), ");
        builder.append(tableName + "_keywords VARCHAR, ");
        builder.append(tableName + "_pebble BLOB UNIQUE, ");
        builder.append(tableName + "_created TIMESTAMP, ");
        builder.append(tableName + "_modified TIMESTAMP, ");
        builder.append(tableName + "_deleted TIMESTAMP, ");
        builder.append(tableName + "_synced TIMESTAMP");
        builder.append(")");
        db.execSQL(builder.toString());
    }
}
