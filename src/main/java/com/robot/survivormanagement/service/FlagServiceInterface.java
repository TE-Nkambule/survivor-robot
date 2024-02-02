package com.robot.survivormanagement.service;

public interface FlagServiceInterface {

    int getListOfInfected();

    boolean report(String reporterId, String reportedId);

    int getListOfNonInfected();
}
