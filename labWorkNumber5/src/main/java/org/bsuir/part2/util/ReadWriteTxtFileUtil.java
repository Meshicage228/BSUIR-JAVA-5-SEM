package org.bsuir.part2.util;

import org.bsuir.part2.model.Book;

import javax.swing.*;
import java.io.*;
import java.util.StringJoiner;

import static java.util.Objects.nonNull;
import static org.bsuir.part2.util.Constraints.PATH_BOOK_FILE;

public class ReadWriteTxtFileUtil {

    public void loadBookData(DefaultListModel<String> bookListModel) {
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH_BOOK_FILE))) {
            String line;
            while (nonNull((line = reader.readLine()))) {
                String[] fields = line.split(" ");
                Book book = new Book(fields[0], fields[1], fields[2], Boolean.parseBoolean(fields[3]), fields[4]);
                bookListModel.addElement(book.getTitle());
            }
        } catch (IOException e) {
            System.err.println("Error loading book data: " + e.getMessage());
        }
    }

    public void saveBookData(Book book) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_BOOK_FILE, true))) {

            String string = new StringJoiner(" ")
                    .add(book.getTitle())
                    .add(book.getAuthor())
                    .add(book.getDescription())
                    .add(String.valueOf(book.getAvailable()))
                    .add(book.getCategory())
                    .add(" \n").toString();

            writer.write(string);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
