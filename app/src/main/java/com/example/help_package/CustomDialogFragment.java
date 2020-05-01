package com.example.help_package;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

@SuppressLint("ValidFragment")
public class CustomDialogFragment extends DialogFragment {
 //   EditText your_name;
@SuppressLint("ValidFragment")
public CustomDialogFragment (EditText context2) {
  //  your_name = context2;
}
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
       // LayoutInflater factory = LayoutInflater.from(this);
        //final View textEntryView = factory.inflate(R.layout.dialog, null);
        //CustomDialogFragment dlg = new CustomDialogFragment(this);
        //EditText your_name = findViewById(R.id.your_name);

        return builder
                .setTitle("Окно регистрации")
                .setIcon(android.R.drawable.ic_dialog_email)
                .setView(R.layout.dialog)
                //.setMessage("Введите ваше имя: ")
                .setPositiveButton("Продолжить",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText your_name =getDialog().findViewById(R.id.your_name);
                        Toast.makeText(getContext(),  " Добро пожаловать, " + your_name.getText() , Toast.LENGTH_LONG).show();
                    }
                    })
                .setNegativeButton("Отмена", null)
                .create();

    }
}
