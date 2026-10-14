package com.mario.ejcontrolesbasicos;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
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
    TextView tvNombre, tvPersonas;
    EditText etEmail, etTelefono, etPassword, etResumen;
    Spinner spTipoBici;
    RadioGroup rgDificultad;
    SeekBar sknumpersonas;
    CheckBox checkBox;
    Switch switch1;
    RatingBar ratingBar;
    Button btnLimpiar;

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
        material = findViewById(R.id.etMaterial);
        tbRuta  =findViewById(R.id.toggleButton);
        tvNombre = findViewById(R.id.etNombreCompleto);
        tvPersonas = findViewById(R.id.tvPersonas);
        spTipoBici  =findViewById(R.id.spinner);
        rgDificultad = findViewById(R.id.rgDificultad);
        sknumpersonas = findViewById(R.id.seekBar);
        checkBox = findViewById(R.id.checkBox);
        switch1 = findViewById(R.id.switch1);
        ratingBar = findViewById(R.id.ratingBar);
        btnLimpiar = findViewById(R.id.btnLimpiar);
        etEmail = findViewById(R.id.etEmail);
        etTelefono = findViewById(R.id.etTelefono);
        etPassword = findViewById(R.id.etContrasenia);
        etResumen = findViewById(R.id.etTextoMulti);


        // Adaptador AutoCompleteTextView
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this,R.array.ciudades, android.R.layout.simple_dropdown_item_1line);
        ciudades.setAdapter(adaptador);

        // Adaptador Spinner
        ArrayAdapter<CharSequence> adaptadorBici = ArrayAdapter.createFromResource(this, R.array.tipos_bicicletas, android.R.layout.simple_spinner_item);
        adaptadorBici.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTipoBici.setAdapter(adaptadorBici);

        ciudades.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // v: el elemento en concreto seleccionado
                // accion que se realizo -> Pulsar la lupa, cuando es el ultimo campo de un formulario, cuando pulso el boton de compartir informacion.
                // event indica si viene de una accion del teclado o de codigo
                // En el XML hay que indicar tambien el imeOption

                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    Toast.makeText(MainActivity.this, "Valor no encontrado " +ciudades.getText(), Toast.LENGTH_LONG).show();
                    return true;    // Para saber que se ha gestionado la accion
                }

                return false;
            }
        });

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
                // Log.i("BICIS", "metodo de EditText");                                Hace de sistem.Out.Print

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

        // Escuchador del radioGroup

        rgDificultad.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {
                // Se refiere al radio group
                // el id del elemento seleccionado
                if(checkedId == -1){
                    // No hay ningun elemento pulsado
                    Log.i("NOPULSADO", "ningun radio button pulsado");
                }else{
                    // Cambia la referencia en funcion del boton seleccionado
                    RadioButton rb = findViewById(checkedId);

                    if(checkedId == R.id.radioButton3){
                        // Si pulso el primer boton --> Facil
                        String txt = rb.getText().toString();
                        Log.i("PULSADO",txt);
                    }

                }
            }
        });

        // escuchador del seekbar

        sknumpersonas.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // mientras esta cambiando
                // progreso actualizado en tiempo real
                // fromUser: True --> el usuario cambia el valor desde la parte grafica
                //           False --> si se cambia desde Java

                tvPersonas.setText("Numero de personas: " + progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // al inciar la interaccion (primera vez que pulso antes de arrastrar)
                Log.i("SEEK", "primera vez que pulso");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // al finalizar el movimiento
                Log.i("SEEK", "cuando levanto el dedo");
            }
        });

        //escuchador del checkBox
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton checkbox, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(MainActivity.this, "Botón pulsado", Toast.LENGTH_LONG).show();
                }
            }
        });

        // escuchador del switch
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton varswitch, boolean pulsado) {

                View v =findViewById(R.id.main);

                if(pulsado){
                    v.setBackgroundColor(Color.GREEN);
                    varswitch.setBackgroundColor(Color.RED);
                }else{
                    v.setBackgroundColor(Color.BLUE);
                    varswitch.setBackgroundColor(Color.YELLOW);
                }
            }
        });

        float puntuacion = ratingBar.getRating();
        // escuchador de la ratingBar
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                // ratingBar la variable de la barra de estrellas que hemos pulsado
                // rating es el valor de estrellas pulsado
                // fromUser indica si el acambio lo ha realizado el usuario o desde codigo

                if (puntuacion>0.0){
                    Toast.makeText(MainActivity.this,"Valor inicial "+puntuacion, Toast.LENGTH_LONG).show();
                }

                Log.i("RATING", String.valueOf(rating));
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View m =findViewById(R.id.main);

                tvNombre.setText("");
                etEmail.setText("");
                etTelefono.setText("");
                etPassword.setText("");
                etResumen.setText("");
                ciudades.setText("");
                material.setText("");
                spTipoBici.setSelection(0);
                rgDificultad.clearCheck();
                tbRuta.setChecked(false);
                tvPersonas.setText("Numero de personas: 0");
                sknumpersonas.setProgress(0);
                checkBox.setChecked(false);
                switch1.setChecked(false);
                    m.setBackgroundColor(Color.WHITE);
                    switch1.setBackgroundColor(Color.WHITE);
                ratingBar.setRating(0);
            }
        });

    }// Fuera del oncreate

    public void botonImagen(View v){
        Intent intent = new Intent(MainActivity.this, LanzarVentana.class);
        startActivity(intent);
    }

    public void botonReservar(View v){
        if(tvNombre.length() == 0){
            Toast.makeText(MainActivity.this,"Campo Nombre Vacio", Toast.LENGTH_SHORT).show();
        } else if (etTelefono.length() == 0) {
            Toast.makeText(MainActivity.this,"Campo Telefono Vacio", Toast.LENGTH_SHORT).show();
        }else if (etEmail.length() == 0){
            Toast.makeText(MainActivity.this,"Campo Email Vacio", Toast.LENGTH_SHORT).show();
        }else if (etPassword.length() == 0) {
            Toast.makeText(MainActivity.this, "Campo Contraseña Vacio", Toast.LENGTH_SHORT).show();
        }
    }
}