package com.usat.controlderiesgos;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.usat.controlderiesgos.databinding.ActivityNavigationDrawerBinding;

public class NavigationDrawer extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavigationDrawerBinding binding;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        binding = ActivityNavigationDrawerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if(mAuth.getUid().length()==0){
            Toast.makeText(NavigationDrawer.this,"No se encontro su crendencial, inicie sesion", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(NavigationDrawer.this, MainActivity.class);
            startActivity(i);

        }
        setSupportActionBar(binding.appBarNavigationDrawer.toolbar);
//        binding.appBarNavigationDrawer.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.nav_unidadesorganizacionles,R.id.nav_tiposactivo,R.id.nav_activos,R.id.nav_vulnerabilidades,R.id.nav_amenazas,R.id.nav_riesgos,R.id.nav_causas,R.id.nav_criteriosimpacto,R.id.nav_criteriosprobabilidad,R.id.nav_criteriosriesgo,R.id.nav_registrosimpacto,R.id.nav_reportesriesgo)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation_drawer);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Cerrar Sesion");
        builder.setMessage("??Desea cerrar sesion?");

        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                dialog.dismiss();
                mAuth.signOut();
                Intent i = new Intent(NavigationDrawer.this,MainActivity.class);
                startActivity(i);


            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.i("Item",String.valueOf(item.getItemId()));

        switch (item.getItemId()){
            case R.id.cerrarSesion:
                Toast.makeText(getApplicationContext(),"Cerrando sesion...",Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                Intent i = new Intent(NavigationDrawer.this,MainActivity.class);
                startActivity(i);
                this.finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation_drawer);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}