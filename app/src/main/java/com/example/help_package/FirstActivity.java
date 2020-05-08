package com.example.help_package;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class FirstActivity extends AppCompatActivity {

    private Button act_change,btnRegister,storage;
    AppCompatActivity context = this;
    private static final int STORAGE_PERMISSION_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        addListenerOnButton();
        btnRegister = findViewById(R.id.btnRegister);
        storage = findViewById(R.id.Storage);
        /*btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterWindow();
            }
        }); */

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialogFragment dialog = new CustomDialogFragment((EditText)context.findViewById(R.id.your_name));
                dialog.show(getSupportFragmentManager(), "custom");
            }});

        storage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                checkPermission(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        STORAGE_PERMISSION_CODE);
            }
        });

    }
//Проверка разрешений
public void checkPermission(String permission, int requestCode)
{
    if (ContextCompat.checkSelfPermission(FirstActivity.this, permission)
            == PackageManager.PERMISSION_DENIED) {


        ActivityCompat.requestPermissions(FirstActivity.this,
                new String[] { permission },
                requestCode);
    }
    else {
        Toast.makeText(FirstActivity.this,
                "Разрешения уже предоставлены", //Permission already granted
                Toast.LENGTH_SHORT)
                .show();
    }
}

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super
                .onRequestPermissionsResult(requestCode,
                        permissions,
                        grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(FirstActivity.this,
                        "Разрешения предоставлены", //Storage Permission Granted
                        Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(FirstActivity.this,
                        "Разрешения запрещены", //Storage Permission Denied
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }


//кнопка входа
    public void addListenerOnButton () {
        act_change = (Button)findViewById(R.id.btnSignIn);
        act_change.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(FirstActivity.this,MainActivity.class);
                        startActivity(intent);
                       /* Toast.makeText(MainActivity.this,
                               "123",
                                Toast.LENGTH_LONG
                        ).show(); */
                    }
                }
        );
    }


    //Всплывающее окно
    /*
    private  void  showRegisterWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Зарегистрироваться");
        dialog.setMessage("Введите данные для регистрации");

        LayoutInflater inflater = LayoutInflater.from(this);
        View register_window = inflater.inflate(R.layout.register_window,null);
        dialog.setView(register_window);
//
//        final MaterialEditText email = register_window.findViewById(R.id.emailField);
//        final MaterialEditText pass = register_window.findViewById(R.id.passField);
//        final MaterialEditText name = register_window.findViewById(R.id.nameField);
//        final MaterialEditText phone = register_window.findViewById(R.id.phoneField);

        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });

        dialog.show();


    } */
}
