package com.example.health_care.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.health_care.R;
import com.example.health_care.controllers.UserController;
import com.example.health_care.models.Customer;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public class UserSettingPage extends AppCompatActivity {
    String imgDecodableString;
    Button addImageButton;
    ImageView customerImage;
    EditText editName;
    EditText editPass;
    EditText editEmail;
    Button editBtn;
    Button cancelBtn;
    int SELECT_PICTURE = 200;
    private static int RESULT_LOAD_IMG = 1;
    ImageView testImg;
    private String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting_page);

        addImageButton = findViewById(R.id.user_add_image_button);
        customerImage = findViewById(R.id.user_image_setting);
        editName = findViewById(R.id.edit_name);
        editPass = findViewById(R.id.edit_password);
        editEmail = findViewById(R.id.edit_email);
        editBtn = findViewById(R.id.edit_btn);
        testImg = findViewById(R.id.test_img);
        cancelBtn = findViewById(R.id.cancel_btn);

        Customer customerWithPrev = (Customer) UserController.getInstance().getCurrentUser();

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertSubmit = new AlertDialog.Builder(UserSettingPage.this);
                alertSubmit.setTitle("Editing Personal Info");
                alertSubmit.setMessage("Are you sure you want to apply changes?");
                alertSubmit.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        boolean t = false;
                        if (!editName.getText().toString().isEmpty()) {
                            customerWithPrev.setFirstName(editName.getText().toString());
                            t = true;
                        }
                        if (!editPass.getText().toString().isEmpty()) {
                            customerWithPrev.setPassword(editPass.getText().toString());
                            t = true;
                        }
                        if (!editEmail.getText().toString().isEmpty()) {
                            customerWithPrev.setEmail(editEmail.getText().toString());
                            t = true;
                        } else {
                            Toast.makeText(UserSettingPage.this, "No Changes Were Made", Toast.LENGTH_LONG).show();
                        }
                        if (t) {
                            Toast.makeText(UserSettingPage.this, "changes applied", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                alertSubmit.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast toast = Toast.makeText(UserSettingPage.this, "No Changes Were Made", Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
                alertSubmit.show();


            }
        });

        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(Intent.createChooser(photoPickerIntent, "Select Picture"), SELECT_PICTURE);
//                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
//                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                // Start the Intent
//                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            }
        });

    }

//    @Override
//    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
//        super.onActivityResult(reqCode, resultCode, data);
//
//
//        if (resultCode == RESULT_OK) {
//            //                // Get the url of the image from data
//            Uri selectedImageUri = data.getData();
//            if (null != selectedImageUri) {
//                // update the preview image in the layout
//                //customerImage.setImageURI(selectedImageUri);
//
//                String[] filePathColumn = { MediaStore.Images.Media.DATA };
//
//                Cursor cursor = getContentResolver().query(selectedImageUri,
//                        filePathColumn, null, null, null);
//                cursor.moveToFirst();
//
//                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                String picturePath = cursor.getString(columnIndex);
//                PreferenceManager.getDefaultSharedPreferences(this).edit().putString("picturePath", picturePath).commit();
//                cursor.close();
//
//                customerImage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
//            }
//
//        }else {
//            Toast.makeText(UserSettingPage.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
//        }
//    }


    @SuppressLint("SdCardPath")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (resultCode == RESULT_OK) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);



                String picturePath = cursor.getString(columnIndex);
                PreferenceManager.getDefaultSharedPreferences(this).edit().putString("/sdcard/Images/test_image.jpg", picturePath).commit();
                cursor.close();

                ImageView imgView = (ImageView) findViewById(R.id.user_image_setting);
                imgView.setImageURI(selectedImage);
                // Set the Image in ImageView after decoding the String
//                imgView.setImageBitmap(BitmapFactory
//                        .decodeFile(imgDecodableString));

            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

        testload();

    }

    private void testload() {
        @SuppressLint("SdCardPath") File imgFile = new  File(imgDecodableString);

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            ImageView myImage = (ImageView) findViewById(R.id.test_img);

            myImage.setImageBitmap(myBitmap);

        }
    }


}