package org.example.secondTask.service;

import lombok.AllArgsConstructor;
import org.example.secondTask.domain.Ward;

import java.util.ArrayList;

@AllArgsConstructor
public class WriterInfo {
    private ArrayList<Ward> wards;

    public void showAllInfo(){
        wards.forEach(ward -> {
            System.out.println("///////////////////////////////////");
            System.out.println(ward.getClass().getSimpleName() + " info:");
            System.out.println(ward);
            System.out.println(ward.showCountOfPlaces());
            System.out.println(ward.getDoctorsNameMessage());
            System.out.println("/////////////////////////////////// \n");
        });
    }
}
