package com.movi.loginobjetoutputstream.ui.login;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.movi.loginobjetoutputstream.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel vm;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        setContentView(binding.getRoot());

        getPermisos();
        //boton para registrar
        binding.btRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.mostrarFormulario();
            }
        });

        //boton login
        binding.btIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.leerUsuarios(binding.etUsuario.getText().toString(),binding.etContrasenia.getText().toString());

            }
        });




}
    private void getPermisos() {

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1000);
        }

    }

}