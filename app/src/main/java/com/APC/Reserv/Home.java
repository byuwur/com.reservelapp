package com.APC.Reserv;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ReservarFragment.OnFragmentInteractionListener {

    DefaultValues dv = new DefaultValues();
    private Context ctx;
    //
    private String URLfotoperfil = dv.imgfotoperfil;
    //create edittexts
    private TextView tvusername,tvuserid, tvuseremail;
    //profile photo
    private ImageView fotoperfil;
    //
    private String usrid, usrnombre, usrcorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(com.APC.Reserv.R.layout.activity_home);

        ctx = Home.this;

        //create fragments for calling
        Fragment fragmentreservas = new ReservasFragment();
        getSupportFragmentManager().beginTransaction().replace(com.APC.Reserv.R.id.home, fragmentreservas).commit();
        //Textview on side panel
        NavigationView navigationView = findViewById(com.APC.Reserv.R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        tvusername= headerView.findViewById(com.APC.Reserv.R.id.usernombre);
        tvuserid= headerView.findViewById(com.APC.Reserv.R.id.userid);
        tvuseremail= headerView.findViewById(com.APC.Reserv.R.id.useremail);
        fotoperfil= headerView.findViewById(com.APC.Reserv.R.id.fotoperfil);

        usrnombre = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getString("nombre", null);
        usrid = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getString("id", null);
        usrcorreo = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getString("email", null);

        setuserdata();

        Toolbar toolbar = findViewById(com.APC.Reserv.R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Reservas");

        final FloatingActionButton fabsearch = findViewById(com.APC.Reserv.R.id.search);
        fabsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportActionBar().setTitle("Buscar");
                //switch fragment
                Fragment fragmentbuscar = new SearchFragment();
                getSupportFragmentManager().beginTransaction().replace(com.APC.Reserv.R.id.home, fragmentbuscar).commit();
                //hide fab
                fabsearch.setVisibility(View.GONE);
            }
        });

        DrawerLayout drawer = findViewById(com.APC.Reserv.R.id.home_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, com.APC.Reserv.R.string.navigation_drawer_open, com.APC.Reserv.R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @SuppressLint("SetTextI18n")
    private void setuserdata(){
        tvusername.setText(usrnombre);
        tvuserid.setText(usrid);
        tvuseremail.setText(usrcorreo);

        //LOAD IMAGE
        Picasso.get().load(URLfotoperfil+usrid+"/1.jpg")
                .networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE)
                .fit().centerCrop()
                .into(fotoperfil, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.d("Carga","Cargada");
                    }
                    @Override
                    public void onError(Exception e) {
                        fotoperfil.setImageResource(com.APC.Reserv.R.drawable.no_profile);
                    }
                });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(com.APC.Reserv.R.id.home_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);dialog.setCancelable(false);
            dialog.setMessage("¿Desea salir de la aplicación?");
            dialog.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //if user pressed "yes", then he is allowed to exit from application
                    finish();
                }
            });
            dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //if user select "No", just cancel this dialog and continue with app
                    dialog.cancel();
                }
            });
            dialog.create();
            dialog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.APC.Reserv.R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == com.APC.Reserv.R.id.action_settings) {
            Intent intent = new Intent(Home.this, Settings.class);
            startActivity(intent);
        } else if (id == com.APC.Reserv.R.id.action_logout) {
            //LOGOUT ACTION
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                    .putBoolean("loginsesion", false).apply();
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                    .putString("id", null).apply();
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                    .putString("nombre", null).apply();
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                    .putString("email", null).apply();
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                    .putString("ciudad", null).apply();
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                    .putString("phone", null).apply();
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                    .putString("pass", null).apply();

            deleteCache(ctx);

            Intent intent = new Intent(Home.this, Login.class);
            startActivity(intent);
            finish();
        } else if (id == com.APC.Reserv.R.id.action_exit) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this); dialog.setCancelable(false);
            dialog.setMessage("¿Desea salir de la aplicación?");
            dialog.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //if user pressed "yes", then he is allowed to exit from application
                    finish();
                }
            });
            dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //if user select "No", just cancel this dialog and continue with app
                    dialog.cancel();
                }
            });
            dialog.create();
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FloatingActionButton fabsearch = findViewById(com.APC.Reserv.R.id.search);

        switch (id) {
            case com.APC.Reserv.R.id.nav_reservar:
                getSupportActionBar().setTitle("Reservar");
                //
                Fragment fragmentreservar = new ReservarFragment();
                getSupportFragmentManager().beginTransaction().replace(com.APC.Reserv.R.id.home, fragmentreservar).commit();
                //show fab
                fabsearch.setVisibility(View.VISIBLE);
                break;
            case com.APC.Reserv.R.id.nav_reservas:
                getSupportActionBar().setTitle("Reservas");
                //
                Fragment fragmentreservas = new ReservasFragment();
                getSupportFragmentManager().beginTransaction().replace(com.APC.Reserv.R.id.home, fragmentreservas).commit();
                //show fab
                fabsearch.setVisibility(View.VISIBLE);
                break;
            case com.APC.Reserv.R.id.nav_favorito:
                getSupportActionBar().setTitle("Favoritos");
                //
                Fragment fragmentfavoritos = new FavoritosFragment();
                getSupportFragmentManager().beginTransaction().replace(com.APC.Reserv.R.id.home, fragmentfavoritos).commit();
                //show fab
                fabsearch.setVisibility(View.VISIBLE);
                break;
            case com.APC.Reserv.R.id.nav_perfil:
                getSupportActionBar().setTitle("Mi perfil");
                //
                Fragment fragmentperfil = new PerfilFragment();
                getSupportFragmentManager().beginTransaction().replace(com.APC.Reserv.R.id.home, fragmentperfil).commit();
                break;
            case com.APC.Reserv.R.id.nav_cuenta:
                Intent intent1 = new Intent(Home.this, Cuenta.class);
                startActivity(intent1);
                break;
            case com.APC.Reserv.R.id.nav_feed:
                Intent intent2 = new Intent(Home.this, Feed.class);
                startActivity(intent2);
                break;
        }
        DrawerLayout drawer = findViewById(R.id.home_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onFragmentInteraction(Uri uri) {}

    private boolean shouldRefreshOnResume = false;
    @Override
    public void onResume() {
        super.onResume();
        // Check should we need to refresh the fragment
        if(shouldRefreshOnResume){
            setuserdata();
            shouldRefreshOnResume=false;
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        shouldRefreshOnResume = true;
    }

    //DELETE CACHE WHEN SESSION'S GONE
    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) { e.printStackTrace();}
    }
    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }
}