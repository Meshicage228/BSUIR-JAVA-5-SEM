package org.example.secondTask.db;

import org.example.secondTask.exception.DiskNotFoundException;
import org.example.secondTask.model.AbstractDisk;
import org.example.secondTask.model.discImpl.FilmDisk;
import org.example.secondTask.model.discImpl.GameDisk;
import org.example.secondTask.model.discImpl.SoftDisk;
import org.example.secondTask.model.discImpl.TutorialDisk;

import java.util.ArrayList;

public class DiskStorage {
    public static ArrayList<AbstractDisk> disks = new ArrayList<>() {{
        add(new FilmDisk("Titanic", 24, 7.5f));
        add(new FilmDisk("The Whale", 10, 5.55f));
        add(new GameDisk("Team Fortress Orange", 22, 12.13f));
        add(new GameDisk("Mortal Combat 9", 55, 22.35f));
        add(new SoftDisk("Windows Activator", 125, 67.99f));
        add(new SoftDisk("Linux Installer", 66, 46f));
        add(new TutorialDisk("Paint Tutorial", 2, 3.5f));
        add(new TutorialDisk("Survival Tutorial", 6, 8.3f));
    }};

    public AbstractDisk getDiskByName(String title){
        return disks.stream()
                .filter(abstractDisk -> abstractDisk.getDiskName().equalsIgnoreCase(title))
                .findFirst()
                .orElseThrow(DiskNotFoundException::new);
    }
}
