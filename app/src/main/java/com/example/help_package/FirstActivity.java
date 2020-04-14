package com.example.help_package;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {

    private Button act_change,btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        addListenerOnButton();
        btnRegister = findViewById(R.id.btnRegister);
        /*btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterWindow();
            }
        }); */
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialogFragment dialog = new CustomDialogFragment();
                dialog.show(getSupportFragmentManager(), "custom");
            }});

    }
//кнопка регистрации


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
