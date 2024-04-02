package com.example.sit708_31c;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    TextView quesTextProgress, quesTitle, quesText;
    ProgressBar progressBar;
    int question = 1;
    Map<String, Map<String, Object>> androidQuestions;

    RadioGroup radioGroup;
    RadioButton rb1, rb2, rb3;

    Button submitBtn, nextBtn;

    int score = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String user = intent.getStringExtra("name");
        TextView welcomeText = findViewById(R.id.textView3);
        welcomeText.setText("Welcome " + user);
        quesTextProgress = findViewById(R.id.textView4);
        progressBar = findViewById(R.id.progressBar);
        quesTitle = findViewById(R.id.textView5);
        quesText = findViewById(R.id.textView6);
        radioGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.radioButton);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        submitBtn = findViewById(R.id.button2);
        nextBtn = findViewById(R.id.button3);

        androidQuestions = new HashMap<>();

        // Question 1
        Map<String, Object> question1 = new HashMap<>();
        question1.put("question_title", "Android Basics");
        question1.put("question", "What is the primary programming language used for Android app development?");
        String[] options1 = {"Java", "Kotlin", "Python"};
        question1.put("options", options1);
        question1.put("correct_option", "Java");
        androidQuestions.put("question1", question1);

        // Question 2
        Map<String, Object> question2 = new HashMap<>();
        question2.put("question_title", "User Interface");
        question2.put("question", "Which component is used to display a simple piece of text in an Android app?");
        String[] options2 = {"TextView", "EditText", "ImageView"};
        question2.put("options", options2);
        question2.put("correct_option", "TextView");
        androidQuestions.put("question2", question2);

        // Question 3
        Map<String, Object> question3 = new HashMap<>();
        question3.put("question_title", "Activity Lifecycle");
        question3.put("question", "Which method is called when an activity is first created in Android?");
        String[] options3 = {"onStart()", "onCreate()", "onResume()"};
        question3.put("options", options3);
        question3.put("correct_option", "onCreate()");
        androidQuestions.put("question3", question3);

        // Question 4
        Map<String, Object> question4 = new HashMap<>();
        question4.put("question_title", "Communication between components");
        question4.put("question", "What mechanism is used to communicate between different components in Android?");
        String[] options4 = {"Service", "Intent", "Content Provider"};
        question4.put("options", options4);
        question4.put("correct_option", "Intent");
        androidQuestions.put("question4", question4);

        // Question 5
        Map<String, Object> question5 = new HashMap<>();
        question5.put("question_title", "Resource Management");
        question5.put("question", "Which directory is used to store string resources in an Android project?");
        String[] options5 = {"/res/layout", "/res/values", "/res/drawable"};
        question5.put("options", options5);
        question5.put("correct_option", "/res/values");
        androidQuestions.put("question5", question5);

        setProgress();

        submitBtn.setOnClickListener(v -> {
            int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
            if (selectedRadioButtonId != -1) {
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                String selectedOption = selectedRadioButton.getText().toString();
                String correctOption = (String) androidQuestions.get("question" + question).get("correct_option");
                selectionResult(selectedOption, correctOption);
            } else {
                Toast.makeText(MainActivity2.this, "Please select an option", Toast.LENGTH_SHORT).show();
            }
        });

        nextBtn.setOnClickListener(v -> {
            if (question == 5) {
                Intent i = new Intent(MainActivity2.this, MainActivity3.class);
                i.putExtra("name", user);
                i.putExtra("score", String.valueOf(score));
                startActivity(i);
            }else {
                question++;
                setProgress();
            }
        });
    }

    private void setProgress() {
        quesTextProgress.setText(question + "/5");
        progressBar.setProgress((question * 20));
        Map<String, Object> q = androidQuestions.get("question" + question);
        quesTitle.setText((String) q.get("question_title"));
        quesText.setText((String) q.get("question"));
        String[] options = (String[]) q.get("options");
        rb1.setText(options[0]);
        rb2.setText(options[1]);
        rb3.setText(options[2]);
        nextBtn.setVisibility(View.INVISIBLE);
        submitBtn.setVisibility(View.VISIBLE);
        rb1.setEnabled(true);
        rb2.setEnabled(true);
        rb3.setEnabled(true);
        rb1.setBackground(null);
        rb2.setBackground(null);
        rb3.setBackground(null);
        radioGroup.clearCheck();
    }

    private void selectionResult(String selectedOption, String correctOption) {
        nextBtn.setVisibility(View.VISIBLE);
        submitBtn.setVisibility(View.INVISIBLE);
        if (selectedOption.equalsIgnoreCase(correctOption)) {
            score++;
            RadioButton rd = getRadioButton(selectedOption);
            rd.setBackgroundColor(Color.parseColor("#90EE90"));
        }else {
            RadioButton seelectedRadioButton = getRadioButton(selectedOption);
            seelectedRadioButton.setBackgroundColor(Color.parseColor("#FF8488"));
            RadioButton correctRadioButton = getRadioButton(correctOption);
            correctRadioButton.setBackgroundColor(Color.parseColor("#90EE90"));
        }
        rb1.setEnabled(false);
        rb2.setEnabled(false);
        rb3.setEnabled(false);
    }

    private RadioButton getRadioButton(String text) {
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            View radioButtonView = radioGroup.getChildAt(i);
            if (radioButtonView instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) radioButtonView;
                if (radioButton.getText().toString().equals(text)) {
                    return radioButton;
                }
            }
        }
        return null;
    }
}
