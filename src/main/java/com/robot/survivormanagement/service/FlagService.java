package com.robot.survivormanagement.service;

import com.robot.survivormanagement.entity.Flag;
import com.robot.survivormanagement.entity.Survivor;
import com.robot.survivormanagement.repository.FlagRepository;
import com.robot.survivormanagement.repository.SurvivorRespository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FlagService implements FlagServiceInterface {
    @Autowired
    private FlagRepository flagRepository;
    @Autowired
    private SurvivorRespository survivorRespository;


    //Reporter - the one reporting the person who is infected
    //Reported is the infected person
    public boolean report(String reporterId, String reportedId) {
        Survivor reporter = survivorRespository.findById(reporterId).orElse(null);
        Survivor reported = survivorRespository.findById(reportedId).orElse(null);

        if (reporter != null && reported != null){
            Flag flag = reported.getFlag();
            int newNoOfReports = flag.getNoOfReports() + 1;

            flag.setNoOfReports(newNoOfReports);
            flagRepository.save(flag);

            if (flag.getNoOfReports() >= 3){
             flag.setStatus(true);
             flagRepository.save(flag);
            }
            return true;
        }else {

            return false;
        }

    }
    public int getListOfInfected(){

        List<Flag> flags = flagRepository.findAll();
        int count = 0;

        for (Flag flag:flags){
            if (flag.getNoOfReports() >= 3){
                count++;
            }
        }

        return count;
    }

    public int getListOfNonInfected(){

        List<Flag> flags = flagRepository.findAll();
        int count = 0;

        for (Flag flag:flags){
            if (flag.getNoOfReports() < 3){
                count++;
            }
        }

        return count;
    }


}
