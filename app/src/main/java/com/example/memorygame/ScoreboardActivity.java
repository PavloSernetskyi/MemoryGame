package com.example.memorygame;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreboardActivity extends AppCompatActivity {

    private DBHandler dbHandler;
    private TextView scoreboardText;
    Button backToMenuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        scoreboardText = findViewById(R.id.scoreboardText);
        dbHandler = new DBHandler(this);
        displayScores();

        backToMenuBtn = findViewById(R.id.backToMenuBtn);
        backToMenuBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ScoreboardActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void displayScores() {
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM game_stats", null);

        StringBuilder builder = new StringBuilder();

        if (cursor.getCount() == 0) {
            builder.append("No games played yet.");
        } else {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                int wins = cursor.getInt(cursor.getColumnIndexOrThrow("wins"));
                int losses = cursor.getInt(cursor.getColumnIndexOrThrow("losses"));
                int played = cursor.getInt(cursor.getColumnIndexOrThrow("played"));

                builder.append("🎮 Name: ").append(name).append("\n");
                builder.append("🏆 Wins: ").append(wins)
                        .append("❌ Losses: ").append(losses)
                        .append("🎲 Played: ").append(played)
                        .append("\n\n");
            }
        }

        scoreboardText.setText(builder.toString());

        cursor.close();
        db.close();
    }
}
