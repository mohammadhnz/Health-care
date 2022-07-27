package com.example.health_care.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.health_care.R;
import com.example.health_care.controllers.Exceptions.LoginExceptions;
import com.example.health_care.controllers.Exceptions.LoginOnceException;
import com.example.health_care.controllers.UserController;
import com.example.health_care.models.Admin;
import com.example.health_care.models.User;

public class SignInPage extends AppCompatActivity {
    EditText signUsername;
    EditText signFullName;
    EditText signEmail;
    EditText signPassword;
    EditText validationKey;
    AppCompatButton signInButton;
    TextView alreadyHavAcc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_page);

        signUsername = findViewById(R.id.sign_username_id_pharmacy);
        signPassword = findViewById(R.id.sign_password_id_pharmacy);
        signFullName = findViewById(R.id.sign_name_id_pharmacy);
        signEmail = findViewById(R.id.sign_email_id_pharmacy);
        validationKey = findViewById(R.id.admin_validation_id);
        signInButton = findViewById(R.id.sign_in_button_id_pharmacy);
        alreadyHavAcc = findViewById(R.id.already_hav);

        alreadyHavAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                setResult(100, intent1);
                setEmptyTextEditors();
                finish();
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sUsername = signUsername.getText().toString();
                String sPassword = signPassword.getText().toString();
                String sFirstname = signFullName.getText().toString();
                String sLastname = signEmail.getText().toString();
                if (!isThereEmptyInput(sUsername, sPassword, sFirstname, sLastname)) {
                    if (User.is_username_valid(signUsername.getText().toString())) {
                        if (!validationKey.getText().toString().isEmpty()) {
                            if (validationKey.getText().toString().equals(Admin.ADMIN_KEY)) {
                                String response = UserController.getInstance().registerAdmin(
                                        sUsername,
                                        sPassword,
                                        sFirstname,
                                        sLastname
                                );
                                createToast(response);
                                // TODO: change intent to admin activity
                            }else{
                                createToast("incorrect validation key");
                                setEmptyTextEditors();
                            }
                        } else {
                            String response = UserController.getInstance().registerCustomer(
                                    sUsername,
                                    sPassword,
                                    sFirstname,
                                    sLastname
                            );
                            try {
                                UserController.getInstance().login(sUsername, sPassword);
                            } catch (LoginOnceException | LoginExceptions e) {
                                e.printStackTrace();
                            }
                            createToast(response);
                            Intent intent = new Intent(SignInPage.this, CustomerMainPage.class);
                            setEmptyTextEditors();
                            startActivity(intent);
                        }
                    } else {
                        createToast("repeated username");
                    }
                } else {
                    createToast("no empty input");
                }
            }
        });
    }

    private void setEmptyTextEditors() {
        signUsername = findViewById(R.id.sign_username_id_pharmacy);
        signEmail = findViewById(R.id.sign_email_id_pharmacy);
        signPassword = findViewById(R.id.sign_password_id_pharmacy);
        signFullName = findViewById(R.id.sign_name_id_pharmacy);
        validationKey = findViewById(R.id.admin_validation_id);
        String emptyString = "";
        signUsername.setText(emptyString);
        signEmail.setText(emptyString);
        signPassword.setText(emptyString);
        signFullName.setText(emptyString);
        validationKey.setText(emptyString);
    }

    private void createToast(String message) {
        Toast toast = Toast.makeText(
                SignInPage.this,
                message,
                Toast.LENGTH_LONG
        );
        toast.show();
    }

    private boolean isThereEmptyInput(String username, String password, String firstname, String lastname) {
        return username.isEmpty() || password.isEmpty() || firstname.isEmpty() || lastname.isEmpty();
    }

}