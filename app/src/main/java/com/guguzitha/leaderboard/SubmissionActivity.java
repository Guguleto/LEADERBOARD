package com.guguzitha.leaderboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.app.ProgressDialog;
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

import com.guguzitha.leaderboard.model.UsersForm;
import com.guguzitha.leaderboard.services.FormService;
import com.guguzitha.leaderboard.services.FormServiceBuilder;

import java.io.IOException;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmissionActivity extends AppCompatActivity {
    Dialog confirmationDialog;
    Dialog failureDialog;
    Dialog successDialog;
    ProgressDialog mProgressDialog;
    private Button SubmitBotton;
    private EditText mName;
    private EditText mLastName;
    private EditText mEmail;
    private EditText mGithubLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);
        sendRequest();

        mName = (EditText) findViewById(R.id.name);
        mLastName = (EditText) findViewById(R.id.last_name);
        mEmail = (EditText) findViewById(R.id.email_address);
        mGithubLink = (EditText) findViewById(R.id.github_link);

        SubmitBotton = (Button) findViewById(R.id.submit_button);
        SubmitBotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mName == null || (mLastName == null || mEmail == null || mGithubLink == null)) {
                    mName.setError("Enter name");
                    mLastName.setError("Enter last name");
                    mEmail.setError("Enter email address");
                    mGithubLink.setError("Enter gitHub link");
                } else {
                    showSubmitConfirmation();
                }

            }
        });
    }

    private void showSubmitConfirmation() {
        confirmationDialog.setContentView(R.layout.confirmation_dialog_box);
        ImageView closePopUpDialog = (ImageView) confirmationDialog.findViewById(R.id.back_arrow);
        SubmitBotton = (Button) confirmationDialog.findViewById(R.id.submit_button);

        closePopUpDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmationDialog.dismiss();
            }
        });
        confirmationDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        confirmationDialog.show();

    }
    private void showErrorDialog(){
        failureDialog.setContentView(R.layout.failure_dialog);
        failureDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        failureDialog.show();

    }
    private void showSuccessDialog(){
        successDialog.setContentView(R.layout.success_dialog);
        successDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        successDialog.show();

    }

    private void sendRequest() {
        String firstName = mName.getText().toString();
        String lastName = mLastName.getText().toString();
        String emailAddress = mEmail.getText().toString();
        String projectLink = mGithubLink.getText().toString();


        FormService service = FormServiceBuilder.createService(FormService.class);
        Call<UsersForm> usersFormCall = service.submitForm(firstName, lastName,
                emailAddress, projectLink);
        usersFormCall.enqueue(new Callback<UsersForm>() {
            @Override
            public void onResponse(Call<UsersForm> call, Response<UsersForm> response) {
                if (!response.isSuccessful()) {
                    mProgressDialog.dismiss();
                    showErrorDialog();
                }

                showSuccessDialog();
            }

            @Override
            public void onFailure(Call<UsersForm> call, Throwable t) {
                mProgressDialog.dismiss();
                showErrorDialog();
                Log.d("Gugu", "onResponse: unsuccessful" + t.getMessage());

            }
        });

    }
}


         
        