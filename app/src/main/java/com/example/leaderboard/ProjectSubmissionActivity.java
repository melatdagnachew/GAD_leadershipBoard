package com.example.leaderboard;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.leaderboard.viewmodels.submitViewModel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class ProjectSubmissionActivity extends AppCompatActivity {


    private EditText firstNameInput;
    private EditText lastNameInput;
    private EditText emailInput;
    private EditText githubLinkINput;
    private submitViewModel viewModel;

    String firstname;
    String email;
    String lastName;
    String githubLink;

    Button mButton;
    View confimation_dialog;
    private ProgressDialog progressDialog;
    AlertDialog.Builder mBuilder;
    View mView;
    AlertDialog dialog;
    AlertDialog dialog1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_submission_form);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("submitting...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);

        mBuilder = new AlertDialog.Builder(ProjectSubmissionActivity.this);
    }

    public void submit(View view) {

        firstNameInput = findViewById(R.id.edit_firstname);
        firstname = firstNameInput.getText().toString().trim();
        lastNameInput = findViewById(R.id.edit_lastname);
        lastName=lastNameInput.getText().toString().trim();
        emailInput = findViewById(R.id.edit_emailAddress);
        email = emailInput.getText().toString().trim();
        githubLinkINput = findViewById(R.id.edit_githubLink);
        githubLink = githubLinkINput.getText().toString().trim();

        viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(submitViewModel.class);
        mView = getLayoutInflater().inflate(R.layout.check_action_dialog, null);
        mButton = (Button) mView.findViewById(R.id.yes_button);

        ImageButton cancelBtn = mView.findViewById(R.id.cancel_button);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        if(!emptyValidation()) {
            hideKeyboard();
            check_action_dialog();
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewModel.submitForm(firstname,lastName,email,githubLink);
                    dialog.dismiss();
                    SuccessAlertDialog();
                }
            });
        }
        else {
            Toast.makeText(ProjectSubmissionActivity.this,
                   "empty field",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void check_action_dialog(){

        mBuilder.setView(mView);
        dialog = mBuilder.create();
        dialog.show();
    }

    private void SuccessAlertDialog(){
        confimation_dialog = getLayoutInflater().inflate(R.layout.confirmation_dialog,null);
        mBuilder.setView(confimation_dialog);
        dialog1 = mBuilder.create();
        dialog1.show();

    }

    private boolean emptyValidation() {
        if (TextUtils.isEmpty(firstNameInput.getText().toString())
                || TextUtils.isEmpty(lastNameInput.getText().toString())
                || TextUtils.isEmpty(emailInput.getText().toString())
                || TextUtils.isEmpty(githubLinkINput.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(githubLinkINput.getWindowToken(), 0);
        }
    }

