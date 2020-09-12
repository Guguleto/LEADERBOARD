package com.guguzitha.leaderboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.guguzitha.leaderboard.model.UsersForm;
import com.guguzitha.leaderboard.services.FormService;
import com.guguzitha.leaderboard.services.FormServiceBuilder;

import java.io.IOException;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmissionActivity extends AppCompatActivity {
    AlertDialog.Builder confirmationDialog;
    Dialog failureDialog;
    Dialog successDialog;
    ProgressDialog mProgressDialog;
    private Button SubmitBotton;
    private EditText mName;
    private EditText mLastName;
    private EditText mEmail;
    private EditText mGithubLink;
    private Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Loading...");


        mName = (EditText) findViewById(R.id.name);
        mLastName = (EditText) findViewById(R.id.last_name);
        mEmail = (EditText) findViewById(R.id.email_address);
        mGithubLink = (EditText) findViewById(R.id.github_link);


        SubmitBotton = (Button) findViewById(R.id.submit_button);
        SubmitBotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest(mName.getText().toString(),
                        mLastName.getText().toString(),
                        mEmail.getText().toString(),
                        mGithubLink.getText().toString());

                if (mName.getText().toString().isEmpty() &&
                        mLastName.getText().toString().isEmpty() &&
                        mEmail.getText().toString().isEmpty() &&
                        mGithubLink.getText().toString().isEmpty()) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Alert");
                    builder.setMessage("Some fields Empty");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();


                } else {
                    confirmationDialog = new AlertDialog.Builder(context);
                    showSubmitConfirmation();
                }
            }

        });
    }

    View initView(int layout) {
        return LayoutInflater.from(context).inflate(layout, null, false);

    }

    AlertDialog.Builder initDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        return builder.setView(view);
    }

    private void showSubmitConfirmation() {
        View view = initView(R.layout.confirmation_dialog_box);
        confirmationDialog = initDialog(view);
        final AlertDialog dialog = confirmationDialog.create();


        ImageView closePopUpDialog = (ImageView) view.findViewById(R.id.back_arrow);
        SubmitBotton = (Button) view.findViewById(R.id.yesButton);

        closePopUpDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        SubmitBotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest(mName.getText().toString(),
                        mLastName.getText().toString(),
                        mEmail.getText().toString(),
                        mGithubLink.getText().toString());
            }
        });

        confirmationDialog.show();

    }

    private void showErrorDialog() {
        failureDialog.setContentView(R.layout.failure_dialog);
        failureDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        failureDialog.show();

    }

    private void showSuccessDialog() {
        successDialog.setContentView(R.layout.success_dialog);
        successDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        successDialog.show();

    }

    private void sendRequest(String name, String lstName, String email,
                             String Link) {

        FormService service = FormServiceBuilder.createService(FormService.class);

        Call<Void> usersFormCall = service.submitForm(name, lstName,
                email, Link);
        usersFormCall.enqueue(new Callback<Void>() {


            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    mProgressDialog.dismiss();
                    showErrorDialog();
                } else {
                    showSuccessDialog();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                mProgressDialog.dismiss();
                showErrorDialog();
                Log.d("Gugu", "onResponse: unsuccessful" + t.getMessage());
            }
        });
    }
}





         
        