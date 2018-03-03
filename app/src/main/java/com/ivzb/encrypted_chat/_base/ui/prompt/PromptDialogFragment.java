package com.ivzb.encrypted_chat._base.ui.prompt;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.ivzb.encrypted_chat.R;

public class PromptDialogFragment extends DialogFragment {

    private String mTitle;
    private DialogListener mListener;

    public PromptDialogFragment() {
        mTitle = "Are you sure?";
    }

    public PromptDialogFragment setTitle(String title) {
        mTitle = title;

        return this;
    }

    public PromptDialogFragment setListener(DialogListener listener) {
        mListener = listener;

        return this;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder
                .setTitle(mTitle)
                .setPositiveButton(R.string.remove, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (mListener != null) mListener.onDialogPositiveClick();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (mListener != null) mListener.onDialogNegativeClick();
                    }
                });

        return builder.create();
    }
}

