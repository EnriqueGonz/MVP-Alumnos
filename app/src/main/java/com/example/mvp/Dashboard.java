package com.example.mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.example.mvp.Presentador.AlumnoPresentador;
import java.util.ArrayList;

public class Dashboard extends AppCompatActivity implements AlumnoPresentador.View{

    private AlumnoPresentador alumnoPresentador;

    TableLayout table;
    TextView textName;
    EditText txtName;
    TextView textApellido;

    Button btnShow;
    Button btnPost;

    String token;
    int idProduct = 0;
    boolean Post_or_Create = false;  // Si es FALSE: Va registrar producto y si es TRUE: Va a Actualizar un registro

    ArrayList<Integer> Ids = new ArrayList<>();
    ArrayList<String> Names = new ArrayList<>();
    ArrayList<String> apellidosPaterno = new ArrayList<>();
    ArrayList<String> apellidosMaterno = new ArrayList<>();
    ArrayList<String> carrera = new ArrayList<>();
    ArrayList<Integer> edad = new ArrayList<>();
    ArrayList<String> sexo = new ArrayList<>();
    ArrayList<String> direcccion = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        table =  findViewById(R.id.tablelayout);
        txtName = findViewById(R.id.txtName);
        btnShow = findViewById(R.id.btnShow);
        btnPost = findViewById(R.id.btnPost);

        Bundle extra = getIntent().getExtras();
        token = (String) extra.get("token");

        alumnoPresentador = new AlumnoPresentador(this);
        alumnoPresentador.setDataToken(token);
        alumnoPresentador.showDatas();

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Show();
            }
        });

        btnPost.setOnClickListener(v -> GoToMasOpciones());
    }


    @Override
    public void sendDataToken(String Token) {}
    @Override
    public void sendDataName(String Name) {}
    @Override
    public void sendDataApellidoPaterno(String apellidoPaterno) {}
    @Override
    public void sendDataApellidoMaterno(String Descripcion) {}
    @Override
    public void sendDataCarrera(String TProducto) {}
    @Override
    public void sendDataEdad(int edad) {}
    @Override
    public void sendDataSexo(String sexo) {}
    @Override
    public void sendDataDireccion(String direccion){}

    public void Show()
    {
        Ids = alumnoPresentador.getIds();
        Names = alumnoPresentador.getNombres();
        apellidosPaterno = alumnoPresentador.getApellidoPaterno();
        apellidosMaterno = alumnoPresentador.getApellidoMaterno();
        carrera = alumnoPresentador.getCarrera();
        edad = alumnoPresentador.getEdad();
        sexo = alumnoPresentador.getSexo();
        direcccion = alumnoPresentador.getDireccion();
        recorrerDatos();
    }

    public void recorrerDatos()
    {
        int NUM_COLS= 1;
        int NUM_ROWS= Names.size();

        for(int i = 0; i<NUM_ROWS; i++){
            TableRow tableRow = new TableRow(getBaseContext());
            table.addView(tableRow);
            table.setBackgroundColor(Color.rgb(70, 117, 78));

            for(int j= 0; j<NUM_COLS; j++){

                // Botones de eliminar y editar
                final Button btnDel = new Button(getBaseContext());
                btnDel.setId(i);
                btnDel.setText("Delete");

                final Button btnEdit = new Button(getBaseContext());
                btnEdit.setId(i);
                btnEdit.setText("Edit");

                textName = new TextView(getBaseContext());
                textName.setGravity(Gravity.CENTER_VERTICAL);
                textName.setPadding(60, 40, 60, 25);
                textName.setBackgroundColor(Color.rgb(70, 117, 78));
                textName.setTextColor(Color.WHITE);

                textApellido  = new TextView(getBaseContext());
                textApellido.setPadding(60, 40, 60, 25);
                textApellido.setBackgroundColor(Color.rgb(70, 117, 78));
                textApellido.setTextColor(Color.WHITE);

                textName.setText(Names.get(i));
                textApellido.setText(apellidosPaterno.get(i));

                tableRow.addView(textName);
                tableRow.addView(textApellido);

                tableRow.addView(btnEdit);
                tableRow.addView(btnDel);


                btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        idProduct = Ids.get(btnEdit.getId());
                        Post_or_Create = true;
                        GoToMasOpciones();
                    }
                });

                btnDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        idProduct = Ids.get(btnDel.getId());
                        DeleteProduct(idProduct);
                    }
                });

            }
        }
    }

    public void GoToMasOpciones(){
        Intent intent = new Intent(this, MasOpciones.class);
        intent.putExtra("token", token);
        intent.putExtra("idProduct", idProduct);
        intent.putExtra("Post_or_Create", Post_or_Create);
        startActivity(intent);
    }

    public void DeleteProduct(int idProduct){
        alumnoPresentador.setIdDeleteProducto(idProduct);
    }
}
