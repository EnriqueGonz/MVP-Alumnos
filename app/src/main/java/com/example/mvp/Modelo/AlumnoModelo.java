package com.example.mvp.Modelo;

import com.example.mvp.Enums.Enums;
import com.example.mvp.Helpers.Helpers;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;

public class AlumnoModelo {

    AsyncHttpClient client = new AsyncHttpClient();
    RequestParams params =  new  RequestParams ();

    String token;
    String nombre;
    String apellidoP;
    String apellidoM;
    String carrera;
    int edad;
    String sexo;
    String direccion;

    ArrayList<Integer> ids = new ArrayList<>();
    ArrayList<String> nombres = new ArrayList<>();
    ArrayList<String> apellidoPaterno = new ArrayList<>();
    ArrayList<String> apellidoMaterno = new ArrayList<>();
    ArrayList<String> carreras = new ArrayList<>();
    ArrayList<Integer> edads = new ArrayList<>();
    ArrayList<String> sexos = new ArrayList<>();
    ArrayList<String> direccions = new ArrayList<>();


    public void setToken(String token) {
        this.token = token;
    }
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setApellidoPaterno(String apellidoP) {this.apellidoP = apellidoP; }
    public void setApellidoMaterno(String apellidoM) {this.apellidoM = apellidoM;}
    public void setCarrera(String carrera) {this.carrera = carrera;}
    public void setEdad(int edad) {this.edad = edad;}
    public void setSexo(String sexo) { this.sexo = sexo; }
    public void setDireccion(String direccion) {this.direccion = direccion;}

    public ArrayList<Integer> getIds() {
        return ids;
    }
    public ArrayList<String> getNombres() {
        return nombres;
    }
    public ArrayList<String> getApellidoP() {
        return apellidoPaterno;
    }
    public ArrayList<String> getApellidoM() {
        return apellidoMaterno;
    }
    public ArrayList<String> getCarrera() {
        return carreras;
    }
    public ArrayList<Integer> getEdad() {
        return edads;
    }
    public ArrayList<String> getSexo() {
        return sexos;
    }
    public ArrayList<String> getDireccion() {
        return direccions;
    }

    public void getHeaders(){
        String head = "Token " + token;
        client.addHeader("Content-Type","application/x-www-form-urlencoded");
        client.addHeader("Authorization", head);
    }

    public void getData(){
        getHeaders();

        client.get(Helpers.URL + Enums.GetAlumnos,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                try {
                    JSONArray jsonArray = new JSONArray(new String(responseBody));
                    for (int i=0;i<jsonArray.length(); i++) {

                        ids.add(Integer.parseInt(jsonArray.getJSONObject(i).getString("id")));
                        nombres.add(jsonArray.getJSONObject(i).getString("nombre"));
                        apellidoPaterno.add(jsonArray.getJSONObject(i).getString("apellidoPaterno"));
                        apellidoMaterno.add(jsonArray.getJSONObject(i).getString("apellidoMaterno"));
                        carreras.add(jsonArray.getJSONObject(i).getString("carrera"));
                        edads.add(Integer.parseInt(jsonArray.getJSONObject(i).getString("edad")));
                        sexos.add(jsonArray.getJSONObject(i).getString("sexo"));
                        direccions.add(jsonArray.getJSONObject(i).getString("direccion"));
                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });
    }

        public void sendDataToServer(){
            getHeaders();

            params.put("nombre", nombre);
            params.put("edad", edad);
            params.put("direccion", direccion);
            params.put("sexo", sexo);
            params.put("apellidoPaterno", apellidoPaterno);
            params.put("apellidoMaterno", apellidoMaterno);
            params.put("carrera", carrera);


            client.post(Helpers.URL + Enums.PostAlumnos, params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    //recreate();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                }
            });
        }


        public void DeleteU(int idSelected){
            getHeaders();

            client.delete(Helpers.URL + Enums.DeleteAlumnos + idSelected + "/", new AsyncHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                    //recreate();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                }
            });
        }


        public void UpdateData(int idSelected){
            getHeaders();

            params.put("nombre", nombre);
            params.put("edad", edad);
            params.put("direccion", direccion);
            params.put("sexo", sexo);
            params.put("apellidoPaterno", apellidoPaterno);
            params.put("apellidoMaterno", apellidoMaterno);
            params.put("carrera", carrera);

            client.put(Helpers.URL + Enums.UpdateAlumnos + idSelected + "/", params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    //recreate();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                }
            });
        }
}
