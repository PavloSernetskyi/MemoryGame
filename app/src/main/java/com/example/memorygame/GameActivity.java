package com.example.memorygame;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private GridLayout tileGrid;
    private TextView statusText;
    private String playerName;
    private TileButton firstTile = null;
    private TileButton secondTile = null;
    private int matchedPairs = 0;
    private int totalPairs = 3;
    private Button backToMenuBtn;

    // properties for game logic
    private int attemptsLeft = 4;
    private TextView attemptsText;
    private boolean gameOver = false;

    private DBHandler dbHandler;
    private int gamesPlayed = 1;
    private int wins = 0;
    private int losses = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tileGrid = findViewById(R.id.tileGrid);
        statusText = findViewById(R.id.statusText);
        backToMenuBtn = findViewById(R.id.backToMenuBtn);
        backToMenuBtn.setOnClickListener(v -> {
            Intent intent = new Intent(GameActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // optional: close game activity from backstack
        });
        playerName = getIntent().getStringExtra("playerName");

        attemptsText = findViewById(R.id.attemptsText);
        attemptsText.setText("Attempts Left: " + attemptsLeft);

        dbHandler = new DBHandler(this);

        setupGameTiles();
    }
    private void setupGameTiles() {
        ArrayList<String> tileContent = new ArrayList<>();
        tileContent.add("🐶");
        tileContent.add("🐱");
        tileContent.add("🐵");
        tileContent.add("🐶");
        tileContent.add("🐱");
        tileContent.add("🐵");
        Collections.shuffle(tileContent);

        tileGrid.removeAllViews(); // clear any old tiles

        int tileSize = 200; // in pixels
        for (int i = 0; i < 6; i++) {
            TileButton tile = new TileButton(this);
            tile.setTileContent(tileContent.get(i));
            tile.setTextSize(32f);
            tile.setPadding(8, 8, 8, 8);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = tileSize;
            params.height = tileSize;
            params.setMargins(12, 12, 12, 12);
            tile.setLayoutParams(params);

            tile.setOnClickListener(view -> handleTileClick(tile));
            tileGrid.addView(tile);
        }
    }

    private void handleTileClick(TileButton clickedTile) {
        if (clickedTile.isMatched() || clickedTile == firstTile) return;

        clickedTile.reveal();

        if (firstTile == null) {
            firstTile = clickedTile;
        } else {
            secondTile = clickedTile;

            // Correct match
            if (firstTile.getTileContent().equals(secondTile.getTileContent())) {
                firstTile.setMatched(true);
                secondTile.setMatched(true);
                matchedPairs++;
                firstTile = null;
                secondTile = null;

                if (matchedPairs == totalPairs) {
                    statusText.setText("🎉 You win!");
                    wins++;
                    dbHandler.addOrUpdateGameRecord(playerName, 1, 0, 1); // +1 win, +0 loss, +1 game
                    backToMenuBtn.setVisibility(View.VISIBLE);
                }
            } else {
                // Wrong guess — reduce attempts
                attemptsLeft--;
                attemptsText.setText("Attempts Left: " + attemptsLeft);

                if (attemptsLeft == 0) {
                    statusText.setText("💀 Game Over!");
                    losses++;
                    dbHandler.addOrUpdateGameRecord(playerName, 0, 1, 1); // +0 win, +1 loss, +1 game
                    disableAllTiles();
                    backToMenuBtn.setVisibility(View.VISIBLE);
                } else {
                    // Flip tiles back after short delay
                    new Handler().postDelayed(() -> {
                        firstTile.hide();
                        secondTile.hide();
                        firstTile = null;
                        secondTile = null;
                    }, 1000);
                }
            }
        }
    }

    private void disableAllTiles() {
        for (int i = 0; i < tileGrid.getChildCount(); i++) {
            View view = tileGrid.getChildAt(i);
            if (view instanceof TileButton) {
                view.setEnabled(false);
            }
        }
    }
}