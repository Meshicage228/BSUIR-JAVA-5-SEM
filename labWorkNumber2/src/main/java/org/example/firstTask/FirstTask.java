package org.example.firstTask;

import org.example.firstTask.model.Ward;
import org.example.firstTask.service.WriterInfo;
import org.example.firstTask.service.impl.WriterInfoImpl;

import java.util.ArrayList;

public class FirstTask {
   private static final ArrayList<Ward> wards = new ArrayList<>(){{
       add(new Ward(10, "Freeman", true));
       add(new Ward(12, false));
       add(new Ward(55, true));
       add(new Ward(10, true));
       add(new Ward(10, false));
       add(new Ward(true));
   }};

    public static void main(String[] args) {
        Integer totalPlaces = Ward.countTotalPlaces(wards);
        System.out.println("Total places : " + totalPlaces);

        Integer emptyPlaces = Ward.countEmptyPlacesInWards(wards);
        System.out.println("Empty places " + emptyPlaces);

        System.out.println("||||||||||||");

        WriterInfo writerInfo = new WriterInfoImpl(wards);

        System.out.println("Total places : " + writerInfo.countTotalPlaces());
        System.out.println("Empty places " + writerInfo.countEmptyPlacesInWards());
    }
}