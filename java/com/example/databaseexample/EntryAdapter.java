package com.example.databaseexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class EntryAdapter extends ArrayAdapter<Person>
{
    public EntryAdapter(Context context, List<Person> people)
    {
        super(context, 0, people);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Person person = getItem(position);
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);

        TextView name = convertView.findViewById(R.id.rowName);
        TextView age = convertView.findViewById(R.id.rowAge);

        name.setText(person.getName() + " " + person.getLast_name());
        age.setText(String.valueOf(person.getAge()));

        return convertView;
    }
}