package com.example.sit708_31c;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView u = findViewById(R.id.textView7);
        TextView scoreText = findViewById(R.id.textView9);
        Intent intent = getIntent();
        String user = intent.getStringExtra("name");
        String score = intent.getStringExtra("score");
        u.setText("Congratulations " + user);
        scoreText.setText(score + "/5");
        Button newQuiz = findViewById(R.id.button5);
        Button finish = findViewById(R.id.button6);
        newQuiz.setOnClickListener(v -> {
            Intent i = new Intent(this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        });
        finish.setOnClickListener(v -> {
            finishAffinity();
        });
    }
}