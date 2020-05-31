package com.example.AppStore;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

@SuppressLint("ValidFragment")
public class CustomDialogFragment extends DialogFragment {

@SuppressLint("ValidFragment")
public CustomDialogFragment (EditText context2) {

}
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

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
