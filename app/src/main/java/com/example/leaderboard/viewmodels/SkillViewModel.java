package com.example.leaderboard.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.leaderboard.models.Skill;
import com.example.leaderboard.repositories.SkillRepository;
import com.example.leaderboard.services.NetworkService;
import com.example.leaderboard.services.SkillService;

import java.util.List;

public class SkillViewModel extends ViewModel {

    private MutableLiveData<List<Skill>> skillList;
    private SkillRepository mSkillRepository;


    public SkillViewModel(){
        SkillService skillService = NetworkService.getConnection().create(SkillService.class);
        mSkillRepository = new SkillRepository(skillService);
    }


    public LiveData<List<Skill>> getSkills() {

        if (skillList == null) {
            skillList = new MutableLiveData<List<Skill>>();
            mSkillRepository.loadSkillScores(skillList);
        }

        return skillList;
    }
}
