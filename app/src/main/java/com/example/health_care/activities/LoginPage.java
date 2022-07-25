package com.example.health_care.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.health_care.Exceptions.PharmacyLoginException;
import com.example.health_care.PharmacyPanelActivity;
import com.example.health_care.PharmacyRegisterActivity;
import com.example.health_care.R;
import com.example.health_care.controllers.Exceptions.LoginExceptions;
import com.example.health_care.controllers.Exceptions.LoginOnceException;
import com.example.health_care.controllers.PharmacyController;
import com.example.health_care.controllers.UserController;
import com.example.health_care.models.Pharmacy;

public class LoginPage extends AppCompatActivity {
    EditText loginUsername;
    EditText loginPassword;
    AppCompatButton loginButton;
    TextView dontHave;
    TextView dontHavePh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        loginUsername = findViewById(R.id.login_username_id);
        loginPassword = findViewById(R.id.login_password_id);
        loginButton = findViewById(R.id.login_button_id);
        dontHave = findViewById(R.id.dont_have);
        dontHavePh = findViewById(R.id.sign_in_as_pharmacy);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = loginUsername.getText().toString();
                String password = loginPassword.getText().toString();
                if (!isEmptyInput(username, password)) {
                    if(Pharmacy.findByInfo(username, password) != null){
                        try {
                            PharmacyController.login(username, password);
                            Toast toast = Toast.makeText(LoginPage.this, "login", Toast.LENGTH_SHORT);
                            toast.show();
                            Intent intent = new Intent(LoginPage.this, PharmacyPanelActivity.class);
                            intent.putExtra("username", username);
                            intent.putExtra("password", password);
                            startActivity(intent);
                        } catch (PharmacyLoginException e) {
                            showNotFoundError(e);
                        }
                    }else{
                        String loginResultMessage = "";
                        try {
                            UserController.getInstance().login(username, password);
                            loginResultMessage = getString(
                                    R.string.login_successful_message
                            );
                            createIntentToPanel(
                                    UserController.getInstance().getCurrentUserType()
                            );
                        } catch (LoginExceptions loginExceptions) {
                            loginResultMessage = getString(
                                    R.string.login_failed_message
                            );
                        } catch (LoginOnceException loginOnceException) {
                            loginResultMessage = getString(
                                    R.string.once_login_error_message
                            ) + loginOnceException.getUserType();
                        } finally {
                            createToast(loginResultMessage);
                        }
                    }

                } else {
                    createToast("empty input");
                }
            }
        });
        dontHave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, SignInPage.class);
                setEmptyTextEditors();
                startActivity(intent);
            }
        });
        dontHavePh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, PharmacyRegisterActivity.class);
                startActivity(intent);
            }
        });
        // TODO: by clicking on sign in your pharmacy go to page
    }

    protected void showNotFoundError(PharmacyLoginException e) {
        Toast toast = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
        toast.show();
    }

    private boolean isEmptyInput(String username, String password) {
        return username.isEmpty() || password.isEmpty();
    }

    private void createToast(String message) {
        Toast toast = Toast.makeText(LoginPage.this, message, Toast.LENGTH_LONG);
        toast.show();
    }

    private void createIntentToPanel(String userType) {
        Intent intent = null;
        if (userType.equals("Customer")) {
            intent = new Intent(LoginPage.this, CustomerMainPage.class);
        }
        else if(userType.equals("Admin")){
//            intent = new Intent(
//                    LoginPage.this,
//                    TeacherPanelPageActivity.class
//            );
        }
        else{
            intent = new Intent(LoginPage.this, PharmacyPanelActivity.class);
            intent.putExtra("username", loginUsername.getText().toString());
            intent.putExtra("password", loginPassword.getText().toString());
        }
        // TODO: login pages or main pages for pharmacy and admin
        setEmptyTextEditors();
        startActivity(intent);
    }

    private void setEmptyTextEditors(){
        loginUsername = findViewById(R.id.login_username_id);
        loginPassword = findViewById(R.id.login_password_id);
        loginUsername.setText("");
        loginPassword.setText("");
    }

}