package com.example.databaseexample;

import java.util.ArrayList;
import java.util.Date;

public class Person {
    public static ArrayList<Person> peopleArrayList = new ArrayList<>();
    public static String ENTRY_EDIT_EXTRA =  "entryEdit";

    private int id;
    private String name;
    private String last_name;
    private int age;
    private Date deleted;

    public Person(int id, String name, String last_name, int age, Date deleted)
    {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.age = age;
        this.deleted = deleted;
    }

    public Person(int id, String name, String last_name, int age)
    {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.age = age;
        deleted = null;
    }

    public static Person getEntryForID(int passedEntryID)
    {
        for (Person person : peopleArrayList)
        {
            if(person.getId() == passedEntryID)
                return person;
        }

        return null;
    }

    public static ArrayList<Person> nonDeletedPeople()
    {
        ArrayList<Person> nonDeleted = new ArrayList<>();
        for(Person person : peopleArrayList)
        {
            if(person.getDeleted() == null)
                nonDeleted.add(person);
        }

        return nonDeleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDeleted()
    {
        return deleted;
    }

    public void setDeleted(Date deleted)
    {
        this.deleted = deleted;
    }
}
