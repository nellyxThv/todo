package com.example.r1_navdrawe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class tabla1Fragment extends Fragment implements View.OnClickListener {

    TableLayout vistita;
    int clave[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    String puesto[] = {"secretaria", "director", "programador", "capturista", "tester",
            "carpintero", "electricista", "albañil", "azulejeros", "pintor"};
    String depto[] = {"programacion", "programacion", "programacion", "programacion", "conservacion",
            "conservacion", "conservacion", "conservacion", "conservacion", "conservacion"};
    int sueldo[] = {10000, 50000, 30000, 12000, 15000, 5000, 5000, 5000, 5000, 5000};

    public tabla1Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabla1, container, false);

        vistita = view.findViewById(R.id.tablita);

        for (int i = 0; i < 10; i++) {
            TableRow fila = new TableRow(getContext());
            TextView txt1 = new TextView(getContext());
            TextView txt2 = new TextView(getContext());
            TextView txt3 = new TextView(getContext());
            TextView txt4 = new TextView(getContext());

            txt1.setOnClickListener(this);
            txt2.setOnClickListener(this);
            txt3.setOnClickListener(this);
            txt4.setOnClickListener(this);

            txt1.setText(String.valueOf(clave[i]));
            txt2.setText(puesto[i]);
            txt3.setText(depto[i]);
            txt4.setText(String.valueOf(sueldo[i]));

            txt1.setPadding(8, 8, 8, 8);
            txt2.setPadding(8, 8, 8, 8);
            txt3.setPadding(8, 8, 8, 8);
            txt4.setPadding(8, 8, 8, 8);

            fila.addView(txt1);
            fila.addView(txt2);
            fila.addView(txt3);
            fila.addView(txt4);

            vistita.addView(fila);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        TextView vistitaaa = (TextView) v;
        Toast.makeText(getContext(),
                vistitaaa.getText(),
                Toast.LENGTH_SHORT).show();
    }
}