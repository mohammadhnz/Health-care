package com.example.health_care.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.health_care.R;
import com.example.health_care.controllers.UserController;
import com.example.health_care.models.Admin;

public class AdiminSettingPage extends AppCompatActivity {

    String imgDecodableString;
    Button addImageButton;
    ImageView adminImage;
    EditText adminEditName;
    EditText adminEditPass;
    EditText adminEditEmail;
    Button editBtn;
    Button cancelBtn;
    TextView adminInfoSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adimin_setting_page);

        //addImageButton = findViewById(R.id.admin_add_image_button);
        adminImage = findViewById(R.id.admin_image_setting);
        adminEditName = findViewById(R.id.admin_edit_name);
        adminEditPass = findViewById(R.id.admin_edit_password);
        adminEditEmail = findViewById(R.id.admin_edit_email);
        editBtn = findViewById(R.id.admin_edit_btn);
        cancelBtn = findViewById(R.id.admin_cancel_btn);
        adminInfoSetting = findViewById(R.id.adminInfoSetting);

        Admin admin = (Admin) UserController.getInstance().getCurrentUser();
        String info = "name: " + admin.getFirstName() + "\n" + "password: " + admin.getPassword() + "\n" + "email: " + admin.getEmail();
        adminInfoSetting.setText(info);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertSubmit = new AlertDialog.Builder(AdiminSettingPage.this);
                alertSubmit.setTitle("Editing Personal Info");
                alertSubmit.setMessage("Are you sure you want to apply changes?");
                alertSubmit.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adminInfoSetting.setText(info);
                        boolean t = false;
                        if (!adminEditName.getText().toString().isEmpty()) {
                            admin.setFirstName(adminEditName.getText().toString());
                            String info = "name: " + admin.getFirstName() + "\n" + "password: " + admin.getPassword() + "\n" + "email: " + admin.getEmail();
                            t = true;
                        }
                        if (!adminEditPass.getText().toString().isEmpty()) {
                            admin.setPassword(adminEditPass.getText().toString());
                            String info = "name: " + admin.getFirstName() + "\n" + "password: " + admin.getPassword() + "\n" + "email: " + admin.getEmail();
                            t = true;
                        }
                        if (!adminEditEmail.getText().toString().isEmpty()) {
                            String info = "name: " + admin.getFirstName() + "\n" + "password: " + admin.getPassword() + "\n" + "email: " + admin.getEmail();
                            t = true;
                        } else {
                            Toast.makeText(AdiminSettingPage.this, "No Changes Were Made", Toast.LENGTH_LONG).show();
                        }
                        if (t) {
                            Toast.makeText(AdiminSettingPage.this, "changes applied", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                alertSubmit.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast toast = Toast.makeText(AdiminSettingPage.this, "No Changes Were Made", Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
                alertSubmit.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    public void onDismiss(DialogInterface dialog) {
                        String info = "name: " + admin.getFirstName() + "\n" + "password: " + admin.getPassword() + "\n" + "email: " + admin.getEmail();
                        adminInfoSetting.setText(info);
                    }
                });
                alertSubmit.show();

            }
        });

    }
}