package com.example.help_package;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class CustomDialogFragment extends DialogFragment {

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        return builder
                .setTitle("Окно регистрации")
                .setIcon(android.R.drawable.ic_dialog_email)
                .setView(R.layout.dialog)
                //.setMessage("Для закрытия окна нажмите ОК")
                .setPositiveButton("Продолжить", null)
                .setNegativeButton("Отмена", null)
                .create();
    }
}
