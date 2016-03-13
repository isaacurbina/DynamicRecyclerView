package com.mobileappsco.training.peopleregistration;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    List<Person> persons;

    public PersonAdapter(List<Person> persons) {
        this.persons = persons;
    }

    public void addPerson(Person p) {
        persons.add(0, p);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_person, parent, false);
        return new PersonViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        holder.tvNames.setText(persons.get(position).getLastName().toUpperCase()+
                            ", "+
                            persons.get(position).getFirstName());
        holder.tvEmail.setText(persons.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {

        TextView tvNames;
        TextView tvEmail;

        public PersonViewHolder(View itemView) {
            super(itemView);
            tvNames = (TextView) itemView.findViewById(R.id.tv_names);
            tvEmail = (TextView) itemView.findViewById(R.id.tv_email);
        }
    }
}
