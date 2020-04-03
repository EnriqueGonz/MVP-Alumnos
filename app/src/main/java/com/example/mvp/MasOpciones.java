package com.example.mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mvp.Presentador.AlumnoPresentador;

public class MasOpciones extends AppCompatActivity implements AlumnoPresentador.View{
    private AlumnoPresentador alumnoPresentador;

    Button btnEnviar;
    EditText txtNombre;
    EditText txtApellidoPaterno;
    EditText txtApellidoMaterno;
    EditText txtCarrera;
    EditText txtEdad;
    EditText txtSexo;
    EditText txtDireccion;
    String token;
    int idProduct = 0;
    boolean Post_or_Create = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mas_opciones);

        txtNombre = findViewById(R.id.txtNombre);
        txtApellidoPaterno = findViewById(R.id.txtApellidoPaterno);
        txtApellidoMaterno = findViewById(R.id.txtApellidoMaterno);
        txtCarrera = findViewById(R.id.txtCarrera);
        txtEdad = findViewById(R.id.txtEdad);
        txtSexo = findViewById(R.id.txtSexo);
        txtDireccion = findViewById(R.id.txtDireccion);
        btnEnviar = findViewById(R.id.btnEnviar);

        Bundle extra = getIntent().getExtras();
        token = (String) extra.get("token");
        idProduct = (int) extra.get("idProduct");
        Post_or_Create = (boolean) extra.get("Post_or_Create");

        alumnoPresentador = new AlumnoPresentador(this);
        alumnoPresentador.setDataToken(token);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Post_or_Create == false){
                    alumnoPresentador.SendPost();
                    GoToDashboard();
                }else{
                    alumnoPresentador.setIdUpdateProduct(idProduct);
                    GoToDashboard();
                }
            }
        });

        txtNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                alumnoPresentador.setDataName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        txtApellidoPaterno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                alumnoPresentador.setDataApellidoPaterno(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        txtApellidoMaterno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                alumnoPresentador.setDataApellidoMaterno(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        txtCarrera.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                alumnoPresentador.setCarrera(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        txtEdad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                alumnoPresentador.setEdad(Integer.parseInt(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        txtSexo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                alumnoPresentador.setSexo(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        txtDireccion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                alumnoPresentador.setDireccion(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    @Override
    public void sendDataToken(String Token) {}
    @Override
    public void sendDataName(String name) {}
    @Override
    public void sendDataApellidoPaterno(String apellidoPaterno) {}
    @Override
    public void sendDataApellidoMaterno(String apellidoMaterno) {}
    @Override
    public void sendDataCarrera(String carrera) {}
    @Override
    public void sendDataEdad(int edad) {}
    @Override
    public void sendDataSexo(String sexo) {}
    @Override
    public void sendDataDireccion(String direccion) {}

    public void GoToDashboard(){
        Intent intent = new Intent(this, Dashboard.class);
        startActivity(intent);
    }
}
