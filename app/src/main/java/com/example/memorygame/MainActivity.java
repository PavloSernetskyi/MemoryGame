package com.example.memorygame;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button startGameBtn, viewScoreboardBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGameBtn = findViewById(R.id.startGameBtn);
        viewScoreboardBtn = findViewById(R.id.viewScoreboardBtn);
        Button exitBtn = findViewById(R.id.exitBtn);

        exitBtn.setOnClickListener(v -> finishAffinity());

        ImageView image = findViewById(R.id.gameImage);
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        image.startAnimation(shake);


        startGameBtn.setOnClickListener(v -> showNamePrompt());
        viewScoreboardBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ScoreboardActivity.class);
            startActivity(intent);
        });
    }

    private void showNamePrompt() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Your Name");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Start", (dialog, which) -> {
            String name = input.getText().toString().trim();
            if (!name.isEmpty()) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("playerName", name);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }
}
