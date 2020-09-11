package com.example.leaderboard.repositories;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;


import com.example.leaderboard.services.ProjectSubmissionService;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class googleFormRepository extends Application {

    private ProjectSubmissionService mService;

    public googleFormRepository(ProjectSubmissionService service) {
        this.mService = service;
    }



    public LiveData<String> SendSubmissionFormData(final String firstname, String lastname, String email, String githubLink) {

        final MutableLiveData<String> formResponse = new MutableLiveData<String>();
        mService.submissionForm(firstname, lastname, email, githubLink).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful())
                  formResponse.setValue("submission sucessful");
                else {
                    formResponse.setValue("unsuccessful");

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
            }
        });
        return formResponse;
    }
}





