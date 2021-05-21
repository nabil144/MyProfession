package com.example.myprofession.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myprofession.R;
import com.example.myprofession.models.ProfessionVO;

import java.util.ArrayList;

public class ProfessionsListAdapter extends ArrayAdapter<ProfessionVO> {

    public ProfessionsListAdapter(Context context, ArrayList<ProfessionVO> professions) {
        super(context, 0, professions);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProfessionVO profession = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.profession_list, parent, false);

        TextView nameText = convertView.findViewById(R.id.name);
        TextView descriptionText = convertView.findViewById(R.id.description);

        nameText.setText(profession.getName());
        descriptionText.setText(profession.getDescription());

        return convertView;
    }
}
