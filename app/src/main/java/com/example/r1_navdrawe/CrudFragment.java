package com.example.r1_navdrawe;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class CrudFragment extends Fragment {

    private EditText txtClave, txtNombre, txtPrecio;
    private Button btnAltas, btnConsultas;
    private BaseHelper bd;

    public CrudFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crud, container, false);

        initViews(view);
        habilitarCampos(false);

        btnAltas.setOnClickListener(v -> manejarAltas());
        btnConsultas.setOnClickListener(v -> manejarConsultas());

        return view;
    }

    private void initViews(View view) {
        txtClave = view.findViewById(R.id.clave);
        txtNombre = view.findViewById(R.id.nombre);
        txtPrecio = view.findViewById(R.id.precio);
        btnAltas = view.findViewById(R.id.altas);
        btnConsultas = view.findViewById(R.id.consultas);

        bd = new BaseHelper(requireContext());
    }

    private void manejarAltas() {
        if (btnAltas.getText().toString().equalsIgnoreCase("Altas")) {
            prepararModoAltas();
        } else {
            guardarRegistro();
        }
    }

    private void manejarConsultas() {
        if (btnConsultas.getText().toString().equalsIgnoreCase("Consultas")) {
            prepararModoConsulta();
        } else {
            buscarRegistro();
        }
    }

    private void prepararModoAltas() {
        btnAltas.setText("Agregar");
        btnConsultas.setEnabled(false);
        habilitarCampos(true);
    }

    private void guardarRegistro() {
        String clave = txtClave.getText().toString();
        String nombre = txtNombre.getText().toString();
        String precioTexto = txtPrecio.getText().toString();

        double precio = 0;
        if (!precioTexto.isEmpty()) {
            try {
                precio = Double.parseDouble(precioTexto);
            } catch (Exception ignored) { }
        }

        boolean exito = bd.crear(clave, nombre, precio);


        limpiarCampos();
        restablecerEstado();
    }

    private void prepararModoConsulta() {
        btnConsultas.setText("Buscar");
        btnAltas.setEnabled(false);

        txtClave.setEnabled(true);
        txtNombre.setEnabled(false);
        txtPrecio.setEnabled(false);
    }

    private void buscarRegistro() {
        String clave = txtClave.getText().toString();

        try (Cursor cursor = bd.consultar(clave)) {
            if (cursor != null && cursor.moveToFirst()) {
                txtNombre.setText(cursor.getString(1));
                txtPrecio.setText(String.valueOf(cursor.getDouble(2)));
            } else {
                Toast.makeText(getContext(), "No existe el registro", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error al consultar", Toast.LENGTH_SHORT).show();
        }

        restablecerEstado();
    }

    private void restablecerEstado() {
        habilitarCampos(false);

        btnAltas.setText("Altas");
        btnAltas.setEnabled(true);

        btnConsultas.setText("Consultas");
        btnConsultas.setEnabled(true);
    }

    private void habilitarCampos(boolean estado) {
        txtClave.setEnabled(estado);
        txtNombre.setEnabled(estado);
        txtPrecio.setEnabled(estado);
    }

    private void limpiarCampos() {
        txtClave.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }
}