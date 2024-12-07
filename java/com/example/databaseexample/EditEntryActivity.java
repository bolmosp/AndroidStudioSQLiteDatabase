package com.example.databaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class EditEntryActivity extends AppCompatActivity
{
    private EditText nameEditText, lastNameEditText, ageEditText;
    private Button deleteButton;
    private Person selectedEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry);
        initWidgets();
        checkForEditEntry();
    }

    private void initWidgets()
    {
        nameEditText = findViewById(R.id.nameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        ageEditText = findViewById(R.id.ageEditText);
        deleteButton = findViewById(R.id.deleteEntryButton);
    }

    private void checkForEditEntry()
    {
        Intent previousIntent = getIntent();

        int passedEntryID = previousIntent.getIntExtra(Person.ENTRY_EDIT_EXTRA, -1);
        selectedEntry = Person.getEntryForID(passedEntryID);

        if (selectedEntry != null)
        {
            nameEditText.setText(selectedEntry.getName());
            lastNameEditText.setText(selectedEntry.getLast_name());
            ageEditText.setText(String.valueOf(selectedEntry.getAge()));
        }
        else
        {
            deleteButton.setVisibility(View.INVISIBLE);
        }
    }

    public void saveEntry(View view)
    {
        SQLiteHandler sqLiteHandler = SQLiteHandler.instanceOfDatabase(this);
        String name = String.valueOf(nameEditText.getText());
        String lastName = String.valueOf(lastNameEditText.getText());
        int age = Integer.parseInt(String.valueOf(ageEditText.getText()));


        if(selectedEntry == null)
        {
            int id = Person.peopleArrayList.size();
            Person newPerson = new Person(id, name, lastName, age);
            Person.peopleArrayList.add(newPerson);
            sqLiteHandler.addPeopleToDB(newPerson);
        }
        else
        {
            selectedEntry.setName(name);
            selectedEntry.setLast_name(lastName);
            selectedEntry.setAge(age);
            sqLiteHandler.updatePersonInDB(selectedEntry);
        }

        finish();
    }

    public void deleteEntry(View view)
    {
        selectedEntry.setDeleted(new Date());
        SQLiteHandler sqLiteHandler = SQLiteHandler.instanceOfDatabase(this);
        sqLiteHandler.updatePersonInDB(selectedEntry);
        finish();
    }
}
