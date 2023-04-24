package com.bawp.charismaticprimaryschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawp.charismaticprimaryschool.R;
import com.bawp.charismaticprimaryschool.model.Login;
import com.bawp.charismaticprimaryschool.utill.Prefs;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button btnLogin;
    TextView invalidMessage;
    Login[] loginList = new Login[]{
               new Login(1,"Madhav", "Shrestha", "madhav123@gmail.com", "madhav","madhav123"),
               new Login(2, "Alex", "Smith", "alex123@gmail.com", "alex", "alex"),
               new Login(3, "John", "Doe", "john.doe@gmail.com", "johndoe", "johndoe"),
               new Login(4, "Jane", "Doe", "jane.doe@gmail.com", "janedoe", "janedoe"),
               new Login(5, "Bob", "Smith", "bob.smith@gmail.com", "bobsmith", "bobsmith"),
               new Login(6, "Emily", "Jones", "emily.jones@gmail.com", "emilyjones", "emilyjones"),
               new Login(7, "Mark", "Williams", "mark.williams@gmail.com", "markwilliams", "markwilliams"),
               new Login(8, "Sarah", "Taylor", "sarah.taylor@gmail.com", "sarahtaylor", "sarahtaylor"),
               new Login(9, "David", "Brown", "david.brown@gmail.com", "davidbrown", "davidbrown"),
               new Login(10, "Jessica", "Davis", "jessica.davis@gmail.com", "jessicadavis", "jessicadavis"),

};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        CheckBox checkBox = findViewById(R.id.checkbox_showPassword);
        invalidMessage = findViewById(R.id.invalidMessage);

        btnLogin.setOnClickListener(view -> {
            String inputUsername = username.getText().toString();
            String inputPassword = password.getText().toString();

            if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
                Snackbar.make(btnLogin, "Please enter username and password", Snackbar.LENGTH_LONG).show();
                return;
            }

            boolean foundMatch = false;
            for (Login login : loginList) {


                if (login.getUsername().equals(inputUsername) && login.getPassword().equals(inputPassword)) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("FIRSTNAME", login.getFirstName());
                    intent.putExtra("SURNAME", login.getSurname());
                    intent.putExtra("USERNAME", login.getUsername());
                    intent.putExtra("studentId", login.getStudentId());
                    intent.putExtra("emailAddress", login.getEmailAddress());
                    startActivity(intent);
                    invalidMessage.setVisibility(View.GONE);
                    foundMatch = true;
                    break;

                }
            }
            if (!foundMatch) {
                invalidMessage.setVisibility(View.VISIBLE);
            } else {
                invalidMessage.setVisibility(View.GONE);
            }
        });

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Show password
                password.setTransformationMethod(null);
            } else {
                // Hide password
                password.setTransformationMethod(new PasswordTransformationMethod());
            }
        });




    }
}