package com.monsalud.kitchencounter.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.monsalud.kitchencounter.R;

public class LoginActivity extends AppCompatActivity {

    private EditText name;
    private EditText password;
    private Button login;
    public static int mEmployeeID;
    public static String mEmployeeName;

    CoordinatorLayout coordinatorLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = findViewById(R.id.etName_splash);
        password = findViewById(R.id.etPassword_splash);
        login = findViewById(R.id.btnLogin_splash);

        coordinatorLogin = findViewById(R.id.coordinatorLogin);

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
            }
        });
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.setText("");
            }
        });

    }

    public void validate(String name, int password) {

        if (((name.toLowerCase().equals("darrell")) && (password == 1234)) ||
                ((name.toLowerCase().equals("alyssa")) && (password == 5678)) ||
                ((name.toLowerCase().equals("sara")) && (password == 1111))) {

            //Assign the user to this session with the user's ID
            if(name.toLowerCase().equals("darrell")) mEmployeeID = 1;
            else if(name.toLowerCase().equals("alyssa")) mEmployeeID = 2;
            else if(name.toLowerCase().equals("sara")) mEmployeeID = 3;

            //Get the user's name for fragments to use
            if(mEmployeeID == 1) mEmployeeName = "Darrell Johnson";
            else if(mEmployeeID == 2) mEmployeeName = "Alyssa Smith";
            else if(mEmployeeID == 3) mEmployeeName = "Sara Mitchell";

            //Hide the soft keyboard
            hideKeyboard();

            //Navigate to MainActivity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            //Hide the soft keyboard
            hideKeyboard();

            //Snackbar
            Snackbar errorLogin = Snackbar.make(coordinatorLogin, "Name and/or password are incorrect", Snackbar.LENGTH_LONG);
            errorLogin.setBackgroundTint(Color.DKGRAY);
            TextView errorLoginText = (TextView) (errorLogin.getView()).findViewById(com.google.android.material.R.id.snackbar_text);
            errorLoginText.setTextSize(32);
            errorLoginText.setTextColor(Color.WHITE);
            errorLogin.show();

        }
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if (view == null) view = new View(this);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void onLogin(View view) {
        validate(name.getText().toString(), Integer.parseInt(String.valueOf(password.getText())));
    }
}