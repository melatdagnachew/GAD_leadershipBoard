package com.example.leaderboard.viewmodels;

import android.util.Log;

import com.example.leaderboard.repositories.googleFormRepository;
import com.example.leaderboard.services.ProjectSubmissionService;
import com.example.leaderboard.services.googleFormService;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class submitViewModel extends ViewModel {


    MutableLiveData<List<String>> googleFormList;
    private googleFormRepository mgoogleFormRepository;

    public submitViewModel() {

        ProjectSubmissionService service = googleFormService.getConnection().create(ProjectSubmissionService.class);
        mgoogleFormRepository = new googleFormRepository(service);
    }

    public LiveData<String> submitForm(String firstname,String lastname,String email,String githublink) {
        if (googleFormList == null) {
            return  mgoogleFormRepository.SendSubmissionFormData(firstname,lastname,email,githublink);
        }

        return null;
    }

    }
