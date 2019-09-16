package com.APC.Reserv;

public class DefaultValues {

    public String url = "http://www.sistemas-i-computacion-tic.com/reserv/phone/";
    public String urlinicio = "http://www.sistemas-i-computacion-tic.com/reserv/phone/inicio/";
    public String urlcanchas = "http://www.sistemas-i-computacion-tic.com/reserv/phone/canchas/";
    public String urlreservar = "http://www.sistemas-i-computacion-tic.com/reserv/phone/reservar/";
    public String urlreservas = "http://www.sistemas-i-computacion-tic.com/reserv/phone/reservas/";
    public String urlfavoritos = "http://www.sistemas-i-computacion-tic.com/reserv/phone/favoritos/";
    public String urlcuenta = "http://www.sistemas-i-computacion-tic.com/reserv/phone/cuenta/";
    public String urllistar = "http://www.sistemas-i-computacion-tic.com/reserv/phone/listar/";
    public String imgcanchasurl = "http://www.sistemas-i-computacion-tic.com/reserv/admin/Imagenes_Canchas/";
    public String imgfotoperfil = "http://www.sistemas-i-computacion-tic.com/reserv/phone/cuenta/fotoperfil/Imagenes_Usuario/";

    /*
    public String url = "http://10.0.2.2/Reserv/phone/";
    public String urlinicio = "http://10.0.2.2/Reserv/phone/inicio/";
    public String urlcanchas = "http://10.0.2.2/Reserv/phone/canchas/";
    public String urlreservar = "http://10.0.2.2/Reserv/phone/reservar/";
    public String urlreservas = "http://10.0.2.2/Reserv/phone/reservas/";
    public String urlfavoritos = "http://10.0.2.2/Reserv/phone/favoritos/";
    public String urlcuenta = "http://10.0.2.2/Reserv/phone/cuenta/";
    public String urllistar = "http://10.0.2.2/Reserv/phone/listar/";
    public String imgcanchasurl = "http://10.0.2.2/Reserv/admin/Imagenes_Canchas/";
    public String imgfotoperfil = "http://10.0.2.2/Reserv/phone/cuenta/fotoperfil/Imagenes_Usuario/";
    */
}

    /*
    STRINGREQUESTEXAMPLE
        StringRequest strq = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("rta_servidor", response);
                        progreso1.dismiss();

                        if (response.equalsIgnoreCase("Ha ingresado correctamente.")){
                            Intent intentiniciar = new Intent(Login.this, Home.class);
                            startActivity(intentiniciar);
                            Toast.makeText(ctx, response,
                                    Toast.LENGTH_LONG).show();
                            finish();
                        }

                        else {
                            AlertDialog.Builder dialogo1 = new AlertDialog.Builder(Login.this);
                            dialogo1.setTitle("INICIAR SESIÓN");
                            dialogo1.setMessage(response);
                            dialogo1.setCancelable(false);
                            dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogo1, int id) {
                                    //Ejecute acciones, deje vacio para solo aceptar
                                    dialogo1.cancel();
                                    et_pass.setText(null);
                                }
                            });
                            dialogo1.show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error_servidor", error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<>();

                parametros.put("email", et_id.getText().toString());
                parametros.put("pass", et_pass.getText().toString());

                return parametros;
            }
        };
        rq.add(strq);

        */