package com.movi.loginobjetoutputstream.ui.registro;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.movi.loginobjetoutputstream.databinding.ActivityRegistroBinding;
import com.movi.loginobjetoutputstream.modelo.Usuario;

public class Registro extends AppCompatActivity {
    private RegistroViewModel vm;
    private ActivityRegistroBinding binding;
    private Intent intent;
    private ActivityResultLauncher<Intent> arl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroViewModel.class);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vm.getUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                binding.tvDni.setText(usuario.getDni()+"");
                binding.tvApellido.setText(usuario.getApellido());
                binding.tvNombre.setText(usuario.getNombre());
                binding.tvEmail.setText(usuario.getEmail());
                binding.tvContrasena.setText(usuario.getContrasena());
                binding.tvImagen.setImageURI(Uri.parse(usuario.getImagen()));

            }
        });
        binding.btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.guardarUsuarios(
                        binding.tvApellido.getText().toString(),
                        binding.tvNombre.getText().toString(),
                        binding.tvDni.getText().toString(),
                        binding.tvEmail.getText().toString(),
                        binding.tvContrasena.getText().toString());


            }
        });

        abrirGaleria();
        vm.getUriMutable().observe(this, new Observer<Uri>() {
            @Override
            public void onChanged(Uri uri) {

                binding.tvImagen.setImageURI(uri);
            }
        });

        binding.btnImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                arl.launch(intent);
            }
        });

        vm.leerDatos(getIntent());
    }

        private void abrirGaleria(){


            intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);


            arl=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    vm.recibirFoto(result);


                }
            });



        }


    }



