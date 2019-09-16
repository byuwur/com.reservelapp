package com.APC.Reserv;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AReservar extends AppCompatActivity {
    private static DefaultValues dv = new DefaultValues();
    private static String URLreservar=dv.urlcanchas+"reserva/reserv.php";
    //
    //set context
    private Context ctx;
    //
    private static RequestQueue rq;
    //create request
    private static StringRequest jsrqcancelreserva;
    //
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    //buttons
    private static Button buttoncancelar, buttonconfirmar;
    private static String idu, idc, fecha, horainicio, horaabrir, horacerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.APC.Reserv.R.layout.activity_areservar);
        setFinishOnTouchOutside(false);

        ctx= AReservar.this;
        rq = Volley.newRequestQueue(ctx);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(com.APC.Reserv.R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(1);

        TabLayout tabLayout = findViewById(com.APC.Reserv.R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        buttoncancelar = findViewById(com.APC.Reserv.R.id.buttoncancelar);
        buttoncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetdata();
                finish();
            }
        });
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        //The fragment argument representing the section number for this fragment.
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {}

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
                                 Bundle savedInstanceState) {
            final Context ctx = getActivity();
            buttonconfirmar = getActivity().findViewById(com.APC.Reserv.R.id.buttonconfirmar);

            final View rootViewdate = inflater.inflate(com.APC.Reserv.R.layout.areservar_selectdate, container, false);
            final View rootViewtime = inflater.inflate(com.APC.Reserv.R.layout.areservar_selecttime, container, false);

            final Calendar calendar = Calendar.getInstance();
            final int minYear = calendar.get(Calendar.YEAR);
            final int minMonth = calendar.get(Calendar.MONTH);
            final int minDay   = calendar.get(Calendar.DAY_OF_MONTH);

            final String[] sdia = new String[1];
            final String[] smes = new String[1];
            final String[] sano = { String.valueOf(minYear) };
            int month = minMonth +1;
            if (month < 10){ smes[0] = "0"+String.valueOf(month); }
            else{ smes[0] = String.valueOf(month); }
            if (minDay < 10){ sdia[0] = "0"+String.valueOf(minDay); }
            else{ sdia[0] = String.valueOf(minDay); }
            AReservar.fecha = sano[0] + "-" + smes[0] + "-" + sdia[0];

            final WebView viewdisp = rootViewtime.findViewById(com.APC.Reserv.R.id.viewdisp);
            viewdisp.setWebChromeClient(new WebChromeClient());
            viewdisp.getSettings().setJavaScriptEnabled(true);
            viewdisp.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url){
                    view.loadUrl(url);
                    return true;
                }
            });

            final DefaultValues dv = new DefaultValues();
            viewdisp.loadUrl(dv.urlcanchas+"disponible/verreservas.php?id="+AReservar.idc+"&fecha="+AReservar.fecha);

            assert getArguments() != null;
            if(getArguments().getInt(ARG_SECTION_NUMBER)==1){

                DatePicker dp = rootViewdate.findViewById(com.APC.Reserv.R.id.date_picker);
                dp.init(minYear, minMonth, minDay, new DatePicker.OnDateChangedListener()
                {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                    {
                        if (year < minYear){
                            view.updateDate(minYear, minMonth, minDay);
                            Toast.makeText(ctx,"No se permite reservar en fechas anteriores a la de hoy.", Toast.LENGTH_SHORT).show();
                        } else if (monthOfYear < minMonth && year == minYear){
                            view.updateDate(minYear, minMonth, minDay);
                            Toast.makeText(ctx,"No se permite reservar en fechas anteriores a la de hoy.", Toast.LENGTH_SHORT).show();
                        } else if (dayOfMonth < minDay && year == minYear && monthOfYear == minMonth){
                            view.updateDate(minYear, minMonth, minDay);
                            Toast.makeText(ctx,"No se permite reservar en fechas anteriores a la de hoy.", Toast.LENGTH_SHORT).show();
                        } /* else if (year > maxYear){
                            view.updateDate(maxYear, maxMonth, maxDay);
                            Toast.makeText(ctx,"No recomendamos reservar en fechas que superen un mes.", Toast.LENGTH_SHORT).show();
                        } else if (monthOfYear > maxMonth && year == maxYear){
                            view.updateDate(maxYear, maxMonth, maxDay);
                            Toast.makeText(ctx,"No recomendamos reservar en fechas que superen un mes.", Toast.LENGTH_SHORT).show();
                        } else if (dayOfMonth > maxDay && year == maxYear && monthOfYear == maxMonth){
                            view.updateDate(maxYear, maxMonth, maxDay);
                            Toast.makeText(ctx,"No recomendamos reservar en fechas que superen un mes.", Toast.LENGTH_SHORT).show();
                        } */ else {
                            final String[] sdia = new String[1];
                            final String[] smes = new String[1];
                            final String[] sano = { String.valueOf(year) };
                            int month = monthOfYear +1;
                            if (month < 10){ smes[0] = "0"+String.valueOf(month); }
                            else{ smes[0] = String.valueOf(month); }
                            if (dayOfMonth < 10){ sdia[0] = "0"+String.valueOf(dayOfMonth); }
                            else{ sdia[0] = String.valueOf(dayOfMonth); }
                            AReservar.fecha = sano[0] + "-" + smes[0] + "-" + sdia[0];
                            Log.d("Fecha seleccionada", AReservar.fecha);
                        }
                    }
                }); // dp.init()

                return rootViewdate;

            } else if (getArguments().getInt(ARG_SECTION_NUMBER) == 2){

                final TextView texthorafin = rootViewtime.findViewById(com.APC.Reserv.R.id.texthorafin);
                final ArrayList<String> horarioinicio = new ArrayList<>();
                final String[] horainicio = {""}, horafin = {""};
                int hi= Integer.parseInt(AReservar.horaabrir), hf= Integer.parseInt(AReservar.horacerrar);
                //initial values
                horarioinicio.add("Hora");

                for (int i=hi; i < hf; i++ ){
                    if (i<10){
                        horarioinicio.add("0"+i);
                    } else {
                        horarioinicio.add(""+i);
                    }
                }

                final Spinner inicio = rootViewtime.findViewById(com.APC.Reserv.R.id.spinnerinicio);

                assert ctx != null;
                ArrayAdapter<String> adin = new ArrayAdapter<>(ctx, android.R.layout.simple_spinner_item,horarioinicio);
                adin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                inicio.setAdapter(adin);
                inicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                        if(pos==0){
                            horainicio[0] ="";
                            texthorafin.setText("Seleccione un horario.");

                            buttonconfirmar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(getActivity(), "Considere seleccionar un horario para su reserva.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            horainicio[0]=horarioinicio.get(pos)+":00:00";
                            int uhf= Integer.parseInt( horainicio[0].length() < 2 ? horainicio[0] : horainicio[0].substring(0, 2) ) + 1;
                            horafin[0]=uhf+":00:00";
                            texthorafin.setText(horafin[0]);

                            buttonconfirmar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AReservar.horainicio=horainicio[0];
                                    //Toast.makeText(getActivity(), "Reserve.", Toast.LENGTH_SHORT).show();
                                    AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity()); dialog.setCancelable(false);
                                    dialog.setTitle("¿Desea realizar la reserva?");
                                    dialog.setMessage("Fecha: "+AReservar.fecha+
                                            "\nHora de inicio: "+ horainicio[0]+
                                            "\nHora de término: "+ horafin[0]);
                                    dialog.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //if user pressed "yes", then he is allowed to exit from application


                                            // Showing progress dialog at user registration time.
                                            final ProgressDialog progreso = new ProgressDialog(ctx);
                                            progreso.setMessage("Por favor, espere...");
                                            progreso.show();

                                            jsrqcancelreserva = new StringRequest(Request.Method.POST, URLreservar,
                                                    new Response.Listener<String>() {
                                                        @Override
                                                        public void onResponse(String response) {
                                                            JSONArray resp = null;
                                                            try {
                                                                resp = new JSONArray( response );
                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
                                                            }
                                                            assert resp != null;
                                                            for (int i = 0; i < resp.length(); i++) {
                                                                try {
                                                                    progreso.dismiss();
                                                                    JSONObject res = resp.getJSONObject(i);

                                                                    Boolean error = res.getBoolean("error");
                                                                    if (error) {
                                                                        AlertDialog.Builder dialogo = new AlertDialog.Builder(ctx);
                                                                        dialogo.setTitle("ERROR");
                                                                        dialogo.setMessage("\n" + res.getString("mensaje"));
                                                                        dialogo.setCancelable(false);
                                                                        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                                                            public void onClick(DialogInterface dialogo, int id) {
                                                                                //Ejecute acciones, deje vacio para solo aceptar
                                                                                dialogo.cancel();
                                                                            }
                                                                        });
                                                                        dialogo.show();
                                                                    } else {
                                                                        AlertDialog.Builder dialogo = new AlertDialog.Builder(ctx);
                                                                        dialogo.setTitle("RESERVAR");
                                                                        dialogo.setMessage("\n" + res.getString("mensaje"));
                                                                        dialogo.setCancelable(false);
                                                                        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                                                            public void onClick(DialogInterface dialogo, int id) {
                                                                                //Ejecute acciones, deje vacio para solo aceptar
                                                                                dialogo.cancel();
                                                                            }
                                                                        });
                                                                        dialogo.show();
                                                                    }
                                                                } catch (Exception e) {
                                                                    e.printStackTrace();
                                                                }
                                                            }
                                                        }
                                                    }, new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    progreso.dismiss();

                                                    AlertDialog.Builder dialogoerror = new AlertDialog.Builder(ctx);
                                                    dialogoerror.setTitle("ERROR");
                                                    dialogoerror.setMessage("\nNo es posible contactar al servidor. Verifique su conexión a Internet e inténtelo más tarde.");
                                                    dialogoerror.setCancelable(false);
                                                    dialogoerror.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialogoerror, int id) {
                                                            //Ejecute acciones, deje vacio para solo aceptar
                                                            dialogoerror.cancel();
                                                        }
                                                    });
                                                    dialogoerror.show();

                                                    Log.d("Error", error.toString());
                                                }
                                            }) {
                                                @Override
                                                protected Map<String, String> getParams() {
                                                    Map<String, String> parametros = new HashMap<>();

                                                    parametros.put("idu", AReservar.idu);
                                                    parametros.put("idc", AReservar.idc);
                                                    parametros.put("fecha", AReservar.fecha);
                                                    parametros.put("hora", AReservar.horainicio);

                                                    return parametros;
                                                }
                                            };
                                            rq.add(jsrqcancelreserva);


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
                            });
                        }
                        Log.d("POSINICIO", horainicio[0]);
                        Log.d("POSFIN", horafin[0]);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent){}
                });

                return rootViewtime;

            } else{
                View rootView = inflater.inflate(com.APC.Reserv.R.layout.fragment_areservar, container, false);
                TextView textView = rootView.findViewById(com.APC.Reserv.R.id.section_label);
                textView.setText(getString(com.APC.Reserv.R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
                return rootView;
            }
        }
    }

    //A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the sections/tabs/pages.
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }
        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }

    public void setid(String idu, String idc, String horaabrir, String horacerrar){
        AReservar.idu= idu;
        AReservar.idc= idc;
        AReservar.horaabrir= horaabrir;
        AReservar.horacerrar= horacerrar;
    }
    private void resetdata(){
        AReservar.idu= null;
        AReservar.idc= null;
        AReservar.horaabrir= null;
        AReservar.horacerrar= null;
    }

    @Override
    public void onBackPressed() {}
}
