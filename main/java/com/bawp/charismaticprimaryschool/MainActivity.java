package com.bawp.charismaticprimaryschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawp.charismaticprimaryschool.data.DatabaseHandler;
import com.bawp.charismaticprimaryschool.model.Note;
import com.bawp.charismaticprimaryschool.model.Question;
import com.bawp.charismaticprimaryschool.utill.Prefs;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity{
    public static final String QUESTION = "question";
    public static final String USERNAME = "username";
    TextView display_student_information, student_id, emailAddress;
    private ImageView vert;
    ConstraintLayout layout;
   private CardView cardViewPhysic, cardViewMath, cardViewChemistry, cardViewEnglish;
   private FloatingActionButton btnDashboard, btnAddNote, btnMenu, btn_notes;


    private Question[] physicsQuestions = new Question[]{
            new Question(R.string.question1,
                    R.string.option1,R.string.option2,R.string.option3,R.string.option4,
                    1),
            new Question(R.string.question2,
                    R.string.speed,R.string.mass,R.string.displacement,R.string.time,
                    2),
            new Question(R.string.question3,
                    R.string.first_law, R.string.second_law, R.string.third_law, R.string.non,
                    3),
            new Question(R.drawable.physics_question,
                    R.string.D, R.string.A, R.string.B, R.string.C,
                    3),
            new Question(R.raw.physics_question,R.string.D, R.string.second_law, R.string.third_law, R.string.non,2),
            new Question(R.string.question1,
                    R.string.option1,R.string.option2,R.string.option3,R.string.option4,
                    2),
            new Question(R.string.question2,
                    R.string.speed,R.string.mass,R.string.displacement,R.string.time,
                    3),
            new Question(R.string.question3,
                    R.string.first_law, R.string.second_law, R.string.third_law, R.string.non,
                    4),
            new Question(R.string.question1,
                    R.string.option1,R.string.option2,R.string.option3,R.string.option4,
                    2),
    };

    private Question[] mathQuestions = new Question[]{
            new Question(R.string.math_question1,R.string.math_option1,R.string.math_option2,R.string.math_option3,R.string.math_option4,1),
            new Question(R.string.math_question2,R.string.math_que_option1,R.string.math_que_option2,R.string.math_que_option1,R.string.math_que_option1,2),
            new Question(R.string.math_question2,R.string.math_que_option1,R.string.math_que_option2,R.string.math_que_option1,R.string.math_que_option1,2),
            new Question(R.drawable.math_question,R.string.math_option1,R.string.math_option2,R.string.math_option3,R.string.math_option4,2),
            new Question(R.raw.chemistry_question,R.string.math_que_option1,R.string.math_que_option2,R.string.math_que_option1,R.string.math_que_option1,3),
            new Question(R.string.math_question1,R.string.math_option1,R.string.math_option2,R.string.math_option3,R.string.math_option4,1),
            new Question(R.string.math_question2,R.string.math_que_option1,R.string.math_que_option2,R.string.math_que_option1,R.string.math_que_option1,2),
            new Question(R.string.math_question1,R.string.math_option1,R.string.math_option2,R.string.math_option3,R.string.math_option4,1),
            new Question(R.string.math_question2,R.string.math_que_option1,R.string.math_que_option2,R.string.math_que_option1,R.string.math_que_option1,2),
            new Question(R.string.math_question1,R.string.math_option1,R.string.math_option2,R.string.math_option3,R.string.math_option4,1),


    };

    private Question[] chemistryQuestions = new Question[]{
            new Question(R.string.question_1,R.string.helium, R.string.nitrogen, R.string.oxygen, R.string.neon,1),
            new Question(R.string.question_2,R.string.acetic_acid, R.string.carbonic_acid, R.string.citric_acid, R.string.hydrochloric_acid,2),
            new Question(R.string.question_3,R.string.distillation, R.string.filtration, R.string.evaporation, R.string.chromatography,4),
            new Question(R.drawable.chemistry_question,R.string.helium, R.string.nitrogen, R.string.oxygen, R.string.neon,1),
            new Question(R.raw.chemistry_question,R.string.acetic_acid, R.string.carbonic_acid, R.string.citric_acid, R.string.hydrochloric_acid,2),
            new Question(R.string.question_3,R.string.distillation, R.string.filtration, R.string.evaporation, R.string.chromatography,4),
            new Question(R.string.question_1,R.string.helium, R.string.nitrogen, R.string.oxygen, R.string.neon,1),
            new Question(R.string.question_2,R.string.acetic_acid, R.string.carbonic_acid, R.string.citric_acid, R.string.hydrochloric_acid,2),
            new Question(R.string.question_3,R.string.distillation, R.string.filtration, R.string.evaporation, R.string.chromatography,4),
            new Question(R.string.question_3,R.string.distillation, R.string.filtration, R.string.evaporation, R.string.chromatography,4),

    };

    private Question[] englishQuestions = new Question[]{
            new Question(R.string.question__1,R.string.delicate, R.string.mundane, R.string.vulgar, R.string.ambiguous,1),
            new Question(R.string.question__2,R.string.and, R.string.although, R.string.however, R.string.since,2),
            new Question(R.string.question__3,R.string.sentence1, R.string.sentence2, R.string.sentence3, R.string.sentence4,1),
            new Question(R.drawable.physics_question,R.string.delicate, R.string.mundane, R.string.vulgar, R.string.ambiguous,1),
            new Question(R.raw.chemistry_question,R.string.delicate, R.string.mundane, R.string.vulgar, R.string.ambiguous,1),
            new Question(R.string.question__1,R.string.delicate, R.string.mundane, R.string.vulgar, R.string.ambiguous,1),
            new Question(R.string.question__2,R.string.and, R.string.although, R.string.however, R.string.since,2),
            new Question(R.string.question__3,R.string.sentence1, R.string.sentence2, R.string.sentence3, R.string.sentence4,1),
            new Question(R.string.question__1,R.string.delicate, R.string.mundane, R.string.vulgar, R.string.ambiguous,1),
            new Question(R.string.question__2,R.string.and, R.string.although, R.string.however, R.string.since,2),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       display_student_information = findViewById(R.id.display_name);

        cardViewPhysic = findViewById(R.id.cardView_physic);
        cardViewChemistry = findViewById(R.id.cardView_chemistry);
        cardViewMath = findViewById(R.id.cardView_math);
        cardViewEnglish = findViewById(R.id.cardView_english);

        btnDashboard = findViewById(R.id.btnDashboard);
        btnAddNote = findViewById(R.id.btn_add_note);
        btnMenu = findViewById(R.id.btnMenu);
        btn_notes = findViewById(R.id.fbaNotes);
        student_id = findViewById(R.id.student_id);
        emailAddress = findViewById(R.id.email_address);

      layout = findViewById(R.id.homepage);

        Intent getIntent = getIntent();
        String firstname = getIntent.getStringExtra("FIRSTNAME");
        String surname = getIntent.getStringExtra("SURNAME");
        String username = getIntent.getStringExtra("USERNAME");
        int studentId = getIntent.getIntExtra("studentId", 1);
        String email_address = getIntent.getStringExtra("emailAddress");


        display_student_information.setText("Welcome, "+firstname+ " "+surname);
        student_id.setText("Student Id: " + studentId);
        emailAddress.setText("Email Address: "+ email_address);


        btnAddNote.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, noteActivity.class);
            intent.putExtra(USERNAME, username);
            startActivity(intent);
        });

        btn_notes.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, noteListActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });

        btnDashboard.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            intent.putExtra("FIRSTNAME", firstname);
            intent.putExtra("SURNAME", surname);
            intent.putExtra("USERNAME", username);
            intent.putExtra("studentId", studentId);
            intent.putExtra("emailAddress",email_address );
            startActivity(intent);
            finish();
        });

        btnMenu.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            intent.putExtra("FIRSTNAME", firstname);
            intent.putExtra("SURNAME", surname);
            intent.putExtra("USERNAME", username);
            startActivity(intent);
        });

        cardViewPhysic.setOnClickListener(view ->{
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            intent.putExtra("question", physicsQuestions);
            startActivity(intent);

        });

        cardViewMath.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            intent.putExtra("question", mathQuestions);
            startActivity(intent);
        });

        cardViewChemistry.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            intent.putExtra("question", chemistryQuestions);
            startActivity(intent);
        });

        cardViewEnglish.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            intent.putExtra("question", englishQuestions);
            startActivity(intent);
        });

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int colorId = prefs.getInt("backgroundColor", R.color.seconday_white);
        layout.setBackgroundResource(colorId);
    }
}