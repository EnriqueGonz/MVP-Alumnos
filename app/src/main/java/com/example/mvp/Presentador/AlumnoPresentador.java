package com.example.mvp.Presentador;

import com.example.mvp.Modelo.AlumnoModelo;

import java.util.ArrayList;

public class AlumnoPresentador {

    private AlumnoModelo alumnoModelo;
    private View view;


    ArrayList<Integer> ids = new ArrayList<>();
    ArrayList<String> nombres = new ArrayList<>();
    ArrayList<String> apellidoPaterno = new ArrayList<>();
    ArrayList<String> apellidoMaterno = new ArrayList<>();
    ArrayList<String> carrera = new ArrayList<>();
    ArrayList<Integer> edad = new ArrayList<>();
    ArrayList<String> sexo = new ArrayList<>();
    ArrayList<String> direccion = new ArrayList<>();

    public AlumnoPresentador(View view) {
        this.view = view;
        this.alumnoModelo = new AlumnoModelo();
    }

    public void setDataToken(String Token){
        alumnoModelo.setToken(Token);
        view.sendDataToken(Token);
    }

    public void setDataName(String name){
        alumnoModelo.setNombre(name);
        view.sendDataName(name);
    }

    public void setDataApellidoPaterno(String apellidoPaterno){
        alumnoModelo.setApellidoPaterno(apellidoPaterno);
        view.sendDataApellidoPaterno(apellidoPaterno);
    }

    public void setDataApellidoMaterno(String apellidoMaterno){
        alumnoModelo.setApellidoMaterno(apellidoMaterno);
        view.sendDataApellidoMaterno(apellidoMaterno);
    }

    public void setCarrera(String carrera){
        alumnoModelo.setCarrera(carrera);
        view.sendDataCarrera(carrera);
    }

    public void setEdad(int edad){
        alumnoModelo.setEdad(edad);
        view.sendDataEdad(edad);
    }

    public void setSexo(String sexo){
        alumnoModelo.setSexo(sexo);
        view.sendDataSexo(sexo);
    }

    public void setDireccion(String direccion){
        alumnoModelo.setDireccion(direccion);
        view.sendDataDireccion(direccion);
    }

    public interface View {
        void sendDataToken(String Token);
        void sendDataName(String Name);
        void sendDataApellidoPaterno(String apellidoPaterno);
        void sendDataApellidoMaterno(String apellidoMaterno);
        void sendDataCarrera(String carrera);
        void sendDataEdad(int edad);
        void sendDataSexo(String sexo);
        void sendDataDireccion(String direccion);
    }

    public void showDatas(){
        alumnoModelo.getData();
    }

    public void SendPost(){
        alumnoModelo.sendDataToServer();
    }

    public ArrayList<Integer> getIds() {
        ids = alumnoModelo.getIds();
        return ids;
    }

    public void setIdDeleteProducto(int idProducto){
        alumnoModelo.DeleteU(idProducto);
    }


    public void setIdUpdateProduct(int idUpdateProduct){
        alumnoModelo.UpdateData(idUpdateProduct);
    }


    public ArrayList<String> getNombres() {
        nombres = alumnoModelo.getNombres();
        return nombres;
    }

    public ArrayList<String> getApellidoPaterno() {
        apellidoPaterno = alumnoModelo.getApellidoP();
        return apellidoPaterno;
    }

    public ArrayList<String> getApellidoMaterno() {
        apellidoMaterno = alumnoModelo.getApellidoM();
        return apellidoMaterno;
    }

    public ArrayList<String> getCarrera() {
        carrera = alumnoModelo.getCarrera();
        return carrera;
    }

    public ArrayList<Integer> getEdad() {
        edad = alumnoModelo.getEdad();
        return edad;
    }

    public ArrayList<String> getSexo() {
        sexo = alumnoModelo.getSexo();
        return sexo;
    }

    public ArrayList<String> getDireccion() {
        direccion = alumnoModelo.getDireccion();
        return direccion;
    }
}
