package com.example.memorygame;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "memoryGameDB";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "game_stats";
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String WINS_COLUMN = "wins";
    private static final String LOSSES_COLUMN = "losses";
    private static final String PLAYED_COLUMN = "played";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String gameStatsQuery = "CREATE TABLE IF NOT EXISTS game_stats (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT UNIQUE, " +
                "wins INTEGER, " +
                "losses INTEGER, " +
                "played INTEGER)";
        db.execSQL(gameStatsQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addOrUpdateGameRecord(String name, int winDelta, int lossDelta, int playDelta) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM game_stats WHERE name = ?", new String[]{name});

        if (cursor.moveToFirst()) {
            // Player exists – update their totals
            int currentWins = cursor.getInt(cursor.getColumnIndexOrThrow("wins"));
            int currentLosses = cursor.getInt(cursor.getColumnIndexOrThrow("losses"));
            int currentPlayed = cursor.getInt(cursor.getColumnIndexOrThrow("played"));

            ContentValues values = new ContentValues();
            values.put("wins", currentWins + winDelta);
            values.put("losses", currentLosses + lossDelta);
            values.put("played", currentPlayed + playDelta);

            db.update("game_stats", values, "name = ?", new String[]{name});
        } else {
            // New player – insert new row
            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("wins", winDelta);
            values.put("losses", lossDelta);
            values.put("played", playDelta);

            db.insert("game_stats", null, values);
        }

        cursor.close();
        db.close();
    }
}
