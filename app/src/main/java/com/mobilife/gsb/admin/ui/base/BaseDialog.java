package com.mobilife.gsb.admin.ui.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.mobilife.gsb.admin.R;
import com.mobilife.gsb.admin.util.Strings;

/**
 * Created by thawornlimwattanachai on 11/14/2016 AD.
 */
public class BaseDialog {

    public static Dialog createProgressDialog(Context context,
                                              String title,
                                              String message) {
        ProgressDialog.Builder progressDialog = new ProgressDialog.Builder(context)
                .setCancelable(false)
                .setTitle(title)
                .setMessage(message);

        return progressDialog.create();
    }

    public static Dialog createSimpleOkErrorDialog(Context context,
                                                   String code,
                                                   String message) {

        return createOKCancelDialogWithCallback(context,
                context.getString(R.string.dialog_error_title),
                message+" ("+code+")",
                null,
                context.getString(R.string.dialog_action_ok),
                null,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                },
                true);
    }

    public static Dialog createSimpleOkErrorDialog(Context context,
                                                   String message) {

        return createOKCancelDialogWithCallback(context,
                context.getString(R.string.dialog_error_title),
                message,
                null,
                context.getString(R.string.dialog_action_ok),
                null,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                },
                true);
    }

    public static Dialog createSimpleOkDialog(Context context,
                                              String title,
                                              String message) {

        return createOKCancelDialogWithCallback(context,
                title, message,
                null,
                context.getString(R.string.dialog_action_ok),
                null,
                null,
                true);

    }

    public static Dialog createOKCancelDialogWithCallback(Context context,
                                                          String title,
                                                          String message,
                                                          String negativeText,
                                                          String positiveText,
                                                          final DialogInterface.OnClickListener
                                                                  negativeListener,
                                                          final DialogInterface.OnClickListener
                                                                  positiveListener,
                                                          boolean cancelable) {

        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .customView(R.layout.dialog_simple,false)
                .autoDismiss(true)
                .cancelable(cancelable);

        final MaterialDialog materialDialog = builder.build();
        View customView = materialDialog.getCustomView();

        TextView textTitle = (TextView)customView.findViewById(R.id.text_title);
        if ( !Strings.isEmpty(title) ) {
            textTitle.setText(title);
        } else {
            textTitle.setVisibility(View.GONE);
        }
        TextView textMessage = (TextView)customView.findViewById(R.id.text_message);
        if ( !Strings.isEmpty(message) ) {
            textMessage.setText(message);
        } else {
            textMessage.setVisibility(View.GONE);
        }
        TextView textPositive = (TextView)customView.findViewById(R.id.text_positive);
        if ( !Strings.isEmpty(positiveText) ) {
            textPositive.setText(positiveText);
            textPositive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( positiveListener != null ) {
                        positiveListener.onClick(materialDialog, DialogInterface.BUTTON_POSITIVE);
                    }
                    materialDialog.dismiss();
                }
            });
        } else {
            textPositive.setVisibility(View.GONE);
        }
        TextView textNegative = (TextView)customView.findViewById(R.id.text_negative);
        if ( !Strings.isEmpty(negativeText) ) {
            textNegative.setText(negativeText);
            textNegative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( negativeListener != null ) {
                        negativeListener.onClick(materialDialog, DialogInterface.BUTTON_NEGATIVE);
                    }
                    materialDialog.dismiss();
                }
            });
        } else {
            textNegative.setVisibility(View.GONE);
        }

        return materialDialog;
    }
}
