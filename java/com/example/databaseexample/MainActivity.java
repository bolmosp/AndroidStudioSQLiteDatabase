package com.example.databaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity
{
    private ListView entryListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        loadFromDBToMemory();
        setEntryAdapter();
        setOnClickListener();
    }


    private void initWidgets()
    {
        entryListView = findViewById(R.id.entryListView);
    }

    private void loadFromDBToMemory()
    {
        SQLiteHandler sqLiteHandler = SQLiteHandler.instanceOfDatabase(this);
        sqLiteHandler.populatePeopleListArray();
    }

    private void setEntryAdapter()
    {
        EntryAdapter entryAdapter = new EntryAdapter(getApplicationContext(), Person.nonDeletedPeople());
        entryListView.setAdapter(entryAdapter);
    }


    private void setOnClickListener()
    {
        entryListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Person selectedPerson = (Person) entryListView.getItemAtPosition(position);
                Intent editEntryIntent = new Intent(getApplicationContext(), EditEntryActivity.class);
                editEntryIntent.putExtra(Person.ENTRY_EDIT_EXTRA, selectedPerson.getId());
                startActivity(editEntryIntent);
            }
        });
    }


    public void newEntry(View view)
    {
        Intent newEntryIntent = new Intent(this, EditEntryActivity.class);
        startActivity(newEntryIntent);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setEntryAdapter();
    }
}