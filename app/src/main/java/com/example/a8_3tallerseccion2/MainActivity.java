package com.example.a8_3tallerseccion2;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
        //conecto con el botón del diseño
        android.widget.Button miBoton = findViewById(R.id.btnRegistrar);

        //programo el clic
        miBoton.setOnClickListener(new android.view.View.OnClickListener(){
            @Override
            public void onClick(android.view.View v) {
                //Referencie los EditText para ponerles el error
                android.widget.EditText campoCorreo = findViewById(R.id.etCorreo);
                android.widget.EditText campoClave = findViewById(R.id.etClave);

                //Se recibe lo que ingreso la persona
                String correo = campoCorreo.getText().toString().trim();
                String clave = campoClave.getText().toString();

                //Se valida el correo que tenga @y punto
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
                    campoCorreo.setError("El correo es incorrecto");
                    campoCorreo.requestFocus(); //Hace que el puntero salte aquí
                    return; //aqui se detiene por el correo invalido
                }

                //se restringe claves consecutivas usando matches para buscarlas
                if (clave.matches(".*(012|123|234|345|456|567|678|789).*")){
                    campoClave.setError("No puedes usar números seguidos");
                    campoClave.requestFocus();
                    return;
                }

                if (clave.length() < 6) {
                    campoClave.setError("La clave debe tener por lo menos 6 caracteres");
                    campoClave.requestFocus();
                    return;
                }
                //Se muestra el mensaje de éxito
                android.widget.Toast.makeText(MainActivity.this, "¡Tu registro fue un éxito!", android.widget.Toast.LENGTH_LONG).show();
            }
        });
    }
}