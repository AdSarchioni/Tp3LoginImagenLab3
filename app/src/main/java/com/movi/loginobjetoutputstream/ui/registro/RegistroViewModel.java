package com.movi.loginobjetoutputstream.ui.registro;

import static android.app.Activity.RESULT_OK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.movi.loginobjetoutputstream.request.ApiClient;
import com.movi.loginobjetoutputstream.ui.login.MainActivity;
import com.movi.loginobjetoutputstream.modelo.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class RegistroViewModel extends AndroidViewModel {
private Context context;
private MutableLiveData<Uri> uriMutableLiveData;
private Uri uri;
    private MutableLiveData<Usuario> mUsuario;
    public RegistroViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

    }

    public LiveData<Usuario> getUsuario(){
        if (mUsuario == null) {
            mUsuario = new MutableLiveData<>();
        }
        return mUsuario;
    }
    public LiveData<Uri> getUriMutable(){

        if(uriMutableLiveData==null){
            uriMutableLiveData=new MutableLiveData<>();
        }
        return uriMutableLiveData;
    }
    public void recibirFoto(ActivityResult result) {
        if(result.getResultCode() == RESULT_OK){
            Intent data=result.getData();
            uri=data.getData();
            uriMutableLiveData.setValue(uri);


        }
    }


public void guardarUsuarios(String apellido, String nombre, String dni, String email, String contrasena ) {
    try {
        // Validar que ningún campo esté vacío
        if (!apellido.isEmpty() && !nombre.isEmpty() && !dni.isEmpty() && !email.isEmpty() && !contrasena.isEmpty()) {
            int dnis = Integer.parseInt(dni);

            // Manejar el caso de URI null asignando un valor por defecto
            String uriString = (uri != null) ? uri.toString() : "";

            // Crear el nuevo usuario con la URI adecuada
            Usuario usuarioNuevo = new Usuario(apellido, nombre, dnis, email, contrasena, uriString);

            // Guardar el usuario usando la API
            ApiClient.guardar(context, usuarioNuevo);

            Toast.makeText(context, "Usuario guardado", Toast.LENGTH_SHORT).show();

            // Redirigir a la actividad principal
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "Valores incorrectos", Toast.LENGTH_SHORT).show();
        }
    } catch (NumberFormatException nfe) {
        Toast.makeText(context, "Valores incorrectos", Toast.LENGTH_SHORT).show();
    }
}





    public void leerDatos(Intent intent) {
        boolean usu=intent.getBooleanExtra("usuario",false);
        if(usu){
            Usuario usuario = ApiClient.leerDatos(context);
            mUsuario.setValue(usuario);}
    }





        }







