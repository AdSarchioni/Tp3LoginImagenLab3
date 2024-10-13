package com.movi.loginobjetoutputstream.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.movi.loginobjetoutputstream.modelo.Usuario;
import com.movi.loginobjetoutputstream.request.ApiClient;
import com.movi.loginobjetoutputstream.ui.registro.Registro;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private ApiClient apiClient;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        apiClient = new ApiClient();
    }

    public void mostrarFormulario() {

        Intent intent = new Intent(context, Registro.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    public void leerUsuarios(String usuario, String contrasena) {

        if (!usuario.isEmpty() && !contrasena.isEmpty()){

       Usuario usu =  apiClient.login(context,usuario,contrasena);


        if (usu != null) {
            Toast.makeText(context, "Bienvenido", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, Registro.class);
            intent.putExtra("usuario",true);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }else{
            Toast.makeText(context, "Valores incorrectos", Toast.LENGTH_SHORT).show();

        }

        }else{
            Toast.makeText(context, "Campos vacios", Toast.LENGTH_SHORT).show();
        }

    }


}



















