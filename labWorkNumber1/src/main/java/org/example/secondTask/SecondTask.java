package org.example.secondTask;

import org.example.secondTask.domain.Ward;
import org.example.secondTask.service.WriterInfo;

import java.util.ArrayList;

public class SecondTask {
   private static final ArrayList<Ward> wards = new ArrayList<>(){{
       add(new Ward(10, "Freeman", true));
       add(new Ward(10, true));
       add(new Ward(true));
   }};

    public static void main(String[] args) {
        WriterInfo writerInfo = new WriterInfo(wards);
        writerInfo.showAllInfo();
    }
}