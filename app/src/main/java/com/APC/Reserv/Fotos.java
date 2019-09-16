package com.APC.Reserv;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class Fotos extends AppCompatActivity {
    private DefaultValues dv = new DefaultValues();
    //array to present photos
    private String[] urlimages = new String[]{
            dv.imgcanchasurl+id+"/1.jpg", dv.imgcanchasurl+id+"/2.jpg", dv.imgcanchasurl+id+"/3.jpg"
    };
    //viewflipper de las fotos de la cancha
    private ViewPager pagerfotos;
    //
    private static String id;
    private static int pos;
    private Toolbar toolbar;
    //set context
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.APC.Reserv.R.layout.activity_fotos);
        toolbar = findViewById(com.APC.Reserv.R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        ctx=Fotos.this;

        pagerfotos = findViewById(com.APC.Reserv.R.id.viewpagerfotos);
        AdaptadorPhotoViewFotos adfotos = new AdaptadorPhotoViewFotos(ctx, urlimages);
        pagerfotos.setAdapter(adfotos);
        pagerfotos.setCurrentItem(pos);
    }

    public void setid(String id, int pos){
        Fotos.id =id;
        Fotos.pos =pos;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id==android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
