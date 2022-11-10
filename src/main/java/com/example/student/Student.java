package com.example.student;

import java.util.ArrayList;
import java.util.Arrays;

public class Student {


    public String getName() {
        return name;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    private String name;
    private String groupNumber;

    public Student(String name, String groupNumber) {
        this.name = name;
        this.groupNumber = groupNumber;
    }


    private final static ArrayList<Student> students = new ArrayList<Student>(
            Arrays.asList(

                    new Student("Доманский Филипп", "301"),
                    new Student("Дубич Александр", "301"),
                    new Student("Фарион Иван", "302"),
                    new Student("Сидоров Эрнест", "302"),
                    new Student("Савченко Александр", "303"),
                    new Student("Кощей Бессмертный", "303")

            )

    );

    public static ArrayList<Student> getStudents(String groupNumber) {
        ArrayList<Student> stList = new ArrayList<>();
        for (Student s : students) {
            if (s.getGroupNumber().equals(groupNumber) || (groupNumber == "")) {
                stList.add(s);
            }
        }
        return stList;
    }
    public static ArrayList<Student> getStudents() {
        return getStudents("");
    }
    @Override
    public String toString() {
        return name;
    }





}
