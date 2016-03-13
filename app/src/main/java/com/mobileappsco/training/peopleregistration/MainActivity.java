package com.mobileappsco.training.peopleregistration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager llmanager;
    List<Person> persons;
    PersonAdapter pAdapter;
    EditText etFirstName, etLastName, etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Retrieven layout objects
        etFirstName = (EditText) findViewById(R.id.et_firstname);
        etLastName = (EditText) findViewById(R.id.et_lastname);
        etEmail = (EditText) findViewById(R.id.et_email);

        // Setting up the recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        llmanager = new LinearLayoutManager(this);
        llmanager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llmanager);
        persons = Person.listAll(Person.class);
        setupClickListener();
        if (persons.size()>0) {
            pAdapter = new PersonAdapter(persons);
            recyclerView.setAdapter(pAdapter);
        }
    }

    public void setupClickListener() {
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(MainActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        Toast.makeText(MainActivity.this, "click en :"+position, Toast.LENGTH_SHORT).show();
                        persons.remove(position);
                        pAdapter.notifyItemRemoved(position);
                    }
                })
        );
    }

    public void addPerson(View view) {
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String email = etEmail.getText().toString();

        if (firstName.length()>0 && lastName.length()>0 && email.length()>0) {
            Person p = new Person(firstName, lastName, email);
            p.save();
            if (persons.size()==0) {
                pAdapter = new PersonAdapter(persons);
                recyclerView.setAdapter(pAdapter);
            }
            pAdapter.addPerson(p);
            pAdapter.notifyItemInserted(0);

            etFirstName.setText("");
            etLastName.setText("");
            etEmail.setText("");

        } else {
            Toast.makeText(MainActivity.this, "Fill in all the fields", Toast.LENGTH_SHORT).show();
        }
    }
}
