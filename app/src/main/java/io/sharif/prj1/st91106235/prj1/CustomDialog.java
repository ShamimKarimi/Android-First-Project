package io.sharif.prj1.st91106235.prj1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;


public class CustomDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomDialog);

        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setTitle(R.string.dialog_title);
        builder.setView(inflater.inflate(R.layout.custom_dialog, null));

        return builder.create();

    }
}
