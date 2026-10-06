package com.mario.ejcontrolesbasicos;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView ciudades;
    MultiAutoCompleteTextView material;
    ToggleButton tbRuta;
    TextView tvNombre;

    Spinner spTipoBici;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // referencias
        ciudades = findViewById(R.id.autoCompleteTextView);
        material = findViewById(R.id.multiAutoCompleteTextView);
        tbRuta  =findViewById(R.id.toggleButton);
        tvNombre = findViewById(R.id.etNombreCompleto);
        spTipoBici  =findViewById(R.id.spinner);

        // Adaptador AutoCompleteTextView
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this,R.array.ciudades, android.R.layout.simple_dropdown_item_1line);
        ciudades.setAdapter(adaptador);

        // Adaptador Spinner
        ArrayAdapter<CharSequence> adaptadorBici = ArrayAdapter.createFromResource(this, R.array.tipos_bicicletas, android.R.layout.simple_spinner_item);
        adaptadorBici.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTipoBici.setAdapter(adaptadorBici);
        /*
        ciudades.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // v: el elemento en concreto seleccionado
                // accion que se realizo
                // event indica si viene de una accion del teclado o de codigo

                return false;
            }
        });*/

        //Spinner
        spTipoBici.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // parent: es la lista de elementos (lista de bicis)
                // view: es el elemento seleccionado en concreto (spinner)
                // posicion: del elemento en la lista (empieza en 0)
                // identificador del elemento. Si se utiliza el arrayadapter suele ser el mismo valor que la posicion

                String elemento = parent.getItemAtPosition(position).toString();

                Toast.makeText(MainActivity.this, "Opcion seleccionada "+ elemento, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Adaptador del Multiautocompleta
        String[] valores = {"Casco", "Botella", "Zapatilla", "Candado"};
        ArrayAdapter<String> adaptadorMulti = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, valores);
        material.setAdapter(adaptadorMulti);
        material.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer()); // Añade la coma como separador

        //Escuchador del toggleButton
        tbRuta.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                // buttonView se refiere al elemento que ha cambiado de estado
                // ischecked booleano para saber si esta pulsado o no pulsado

                if(isChecked){
                    Toast.makeText(MainActivity.this, "Con guía de la tienda", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Sin guía", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Escuchador del editText

        tvNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // El texto final despues de editar. Permite modificarlo
                // Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                // Log.i("BICIS", "metodo de EditText");                                Hace de sistem.O               ut.Print

                if (tvNombre.length()<4){
                    tvNombre.setTextColor(Color.RED);
                }else{
                    tvNombre.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // s: el texto tal cual estaba antes de empezar a editar
                // start: posicion donde empezo el cambio
                // count: nº de caracteres cambiados
                // after: nº de caracteres añadidos
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // s: el texto cuando ya ha sido editado
                // start: posicion donde empezo el cambio
                // count: nº de caracteres cambiados
                // after: nº de caracteres añadidos
            }
        });







    }// Fuera del oncreate

    public void botonImagen(View v){
        Intent intent = new Intent(MainActivity.this, LanzarVentana.class);
        startActivity(intent);
    }
}