package com.APC.Reserv;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReservasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ReservasFragment extends Fragment {
    private DefaultValues dv = new DefaultValues();
    //register file to request
    private String IMGURL = dv.imgcanchasurl, URLlistarreservas= dv.urlreservas+"listarreservas.php";
    //set context
    private Context ctx;
    //
    private RequestQueue rq;
    //create request
    private StringRequest jsrqreservas, jsrqconfreservas;
    //public params of search
    private static boolean ifres;
    private static String usrid, idreserva, idcancha;

    private OnFragmentInteractionListener mListener;

    private ArrayList<HolderCanchasReservas> listaReservas;
    private RecyclerView recyclerReservas;
    private AdaptadorReservas adapter;

    public ReservasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewsi= inflater.inflate(com.APC.Reserv.R.layout.fragment_reservas, container, false);
        View viewno= inflater.inflate(com.APC.Reserv.R.layout.fragment_noreservas, container, false);
        //
        ctx = getActivity();
        assert ctx != null;
        rq = Volley.newRequestQueue(ctx);
        // Showing progress dialog at user registration time.

        listaReservas=new ArrayList<>();
        recyclerReservas=viewsi.findViewById(com.APC.Reserv.R.id.recyclerReservas);
        recyclerReservas.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new AdaptadorReservas(listaReservas);
        recyclerReservas.setAdapter(adapter);
        //when it click an specific frame, let's see if we can display its id
        adapter.setonItemClickListener(new AdaptadorReservas.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                CanchaReservada cancha = new CanchaReservada();
                cancha.setid(idcancha, idreserva);

                Intent intent1 = new Intent(ctx, CanchaReservada.class);
                startActivity(intent1);
            }
        });
        //Button from noreserv
        Button buttontoreserv =  viewno.findViewById(com.APC.Reserv.R.id.buttontoreserv);
        buttontoreserv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View viewno){
                onclickreservar();
            }
        });
        //and verify if there's anything,
        //if it isn't display an specific layout design
        if(ifres){
            LlenarListaReservas();
            ifres=false;
            return viewsi;
        }
        else {
            verifreservas();
            return viewno;
        }
    }

    private void verifreservas(){
        // Showing progress dialog at user registration time.
        final ProgressDialog progreso = new ProgressDialog(ctx);
        progreso.setMessage("Por favor, espere...");
        progreso.show();

        usrid = getActivity().getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getString("id", null);
        // Showing progress dialog at user registration time.
        jsrqconfreservas = new StringRequest(Request.Method.POST, URLlistarreservas,
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
                                if (res.has("error")) {
                                    Boolean error = res.getBoolean("error");
                                    if (error){ ifres=false;break; }
                                } else if (res.has("ESTADO")){
                                    int estado = res.getInt("ESTADO");
                                    if (estado == 0) { ifres = true;break; }
                                    else { ifres = false; }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        if (ifres){ onclickself(); }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<>();

                parametros.put("id", usrid);

                return parametros;
            }
        };
        rq.add(jsrqconfreservas);
    }

    private void LlenarListaReservas() {
        // Showing progress dialog at user registration time.
        final ProgressDialog progreso = new ProgressDialog(ctx);
        progreso.setMessage("Por favor, espere...");
        progreso.show();

        usrid = getActivity().getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getString("id", null);
        // Showing progress dialog at user registration time.
        jsrqreservas = new StringRequest(Request.Method.POST, URLlistarreservas,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d("Response", response.toString());
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
                                recyclerReservas.setAdapter(adapter);

                                int estado = res.getInt("ESTADO");
                                if (res.has("error")) {
                                    Boolean error = res.getBoolean("error");
                                    if (error) {
                                        AlertDialog.Builder dialogoerror = new AlertDialog.Builder(ctx);
                                        dialogoerror.setTitle("BUSCAR");
                                        dialogoerror.setMessage("\n" + res.getString("mensaje"));
                                        dialogoerror.setCancelable(false);
                                        dialogoerror.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialogoerror, int id) {
                                                //Ejecute acciones, deje vacio para solo aceptar
                                                dialogoerror.cancel();
                                            }
                                        });
                                        dialogoerror.show();
                                    }
                                } else if (estado==0){
                                    idcancha = res.getString("canchas_IDCANCHA");
                                    idreserva = res.getString("IDRESERVAS");
                                    listaReservas.add(new HolderCanchasReservas(
                                            "" + res.getString("NOMBRECANCHA"), res.getString("canchas_IDCANCHA"),
                                            "" + res.getString("DIRCANCHA"), "" + res.getString("NOMBRECIUDAD"),
                                            "",//"Reserv:" + res.getString("IDRESERVAS"),
                                            "" + res.getString("FECHA"),
                                            "De " + res.getString("HORAINICIO") + " a " + res.getString("HORAFIN"),
                                            IMGURL + res.getString("canchas_IDCANCHA") + "/1.jpg"));
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
                //Toast.makeText(ctx, "Unable to fetch data: " + error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<>();

                parametros.put("id", usrid);

                return parametros;
            }
        };
        rq.add(jsrqreservas);
    }

    private void onclickreservar(){
        Toolbar toolbar = getActivity().findViewById(com.APC.Reserv.R.id.toolbar);
        toolbar.setTitle("Reservar");
        //switch fragment
        Fragment fragmentreservar = new ReservarFragment();
        FragmentManager fragmentManager = getFragmentManager();
        assert fragmentManager != null;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(com.APC.Reserv.R.id.home, fragmentreservar);
        fragmentTransaction.commit();
    }
    private void onclickself(){
        Fragment fragmentreservas = new ReservasFragment();
        assert getFragmentManager() != null;
        getFragmentManager().beginTransaction().replace(com.APC.Reserv.R.id.home, fragmentreservas).commit();
        Log.d("Carga", "Reiniciado");
    }

    private boolean shouldRefreshOnResume = false;
    @Override
    public void onResume() {
        super.onResume();
        // Check should we need to refresh the fragment
        if(shouldRefreshOnResume){
            onclickself();
            shouldRefreshOnResume=false;
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        shouldRefreshOnResume = true;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    /*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            //throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }
    */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        //TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
