/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package sw;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.Usuario;
import model.modelPGconexion;
import model.modelTransaccion;
import model.modelUsuario;

/**
 *
 * @author Bryan
 */
@WebService(serviceName = "ConversionSW")
public class ConversionSW {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";

    }

//    //REGISTRO
//        @WebMethod(operationName = "Registro")
//    public boolean Registro(@WebParam(name = "user") String user, @WebParam(name = "password") String password) {
//        boolean ban = false;
//
//        return ban;
//    }
//    
//    
//    
    //==============================================================================================================
//    /**
//     * Web service operation
//     */
//    @WebMethod(operationName = "euroAdolar")
//    public Double euroAdolar(@WebParam(name = "euro") double euro) {
//        //TODO write your implementation code here:
//        return euro * 1.08;
//    }
//
//    /**
//     * Web service operation
//     */
//    @WebMethod(operationName = "Suma")
//    public Double Suma(@WebParam(name = "n1") double n1, @WebParam(name = "n2") double n2) {
//        //TODO write your implementation code here:
//        return n1 + n2;
//    }
//
//    /**
//     * Web service operation
//     */
//    @WebMethod(operationName = "Resta")
//    public Double Resta(@WebParam(name = "n1") double n1, @WebParam(name = "n2") double n2) {
//        //TODO write your implementation code here:
//        return n1 - n2;
//    }
//
//    /**
//     * Web service operation
//     */
//    @WebMethod(operationName = "Multiplicacion")
//    public Double Multiplicacion(@WebParam(name = "n1") double n1, @WebParam(name = "n2") double n2) {
//        //TODO write your implementation code here:
//        return n1 * n2;
//    }
//
//    /**
//     * Web service operation
//     */
//    @WebMethod(operationName = "Division")
//    public Double Division(@WebParam(name = "n1") double n1, @WebParam(name = "n2") double n2) {
//        //TODO write your implementation code here:
//        return n1 / n2;
//    }
//
//    /**
//     * Web service operation
//     */
//    @WebMethod(operationName = "TPitagoras")
//    public Double TPitagoras(@WebParam(name = "CA") double CA, @WebParam(name = "CB") double CB) {
//        //TODO write your implementation code here:
//        return Math.sqrt((Math.pow(CA, 2)) + (Math.pow(CB, 2)));
//    }
//
//    /**
//     * Web service operation
//     */
//    @WebMethod(operationName = "Fuerza")
//    public Double Fuerza(@WebParam(name = "masa") double masa, @WebParam(name = "aceleracion") double aceleracion) {
//        //TODO write your implementation code here:
//        return masa * aceleracion;
//    }
//
//    /**
//     * Web service operation
//     */
//    @WebMethod(operationName = "Aceleracion")
//    public Double Aceleracion(@WebParam(name = "V0") double V0, @WebParam(name = "Vf") double Vf, @WebParam(name = "Tiempo") double Tiempo) {
//        //TODO write your implementation code here:
//        return (Vf - V0) / Tiempo;
//    }
//
//    /**
//     * Web service operation
//     */
//    @WebMethod(operationName = "Velocidad")
//    public Double Velocidad(@WebParam(name = "distancia") double distancia, @WebParam(name = "tiempo") double tiempo) {
//        //TODO write your implementation code here:
//        return distancia / tiempo;
//    }
//
//    //==============================================================================================================
    modelPGconexion mpgc = new modelPGconexion();

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Registro")
    public String Registro(@WebParam(name = "user") String user, @WebParam(name = "password") String password, @WebParam(name = "password2") String password2, @WebParam(name = "saldo") String saldo) {
        modelUsuario usu = new modelUsuario();
        String mensaje = "",
                ban = ValidarRegistro(user, password, password2, saldo);

        System.out.println("validacion= " + ban);
        if (ban.equalsIgnoreCase("OK")) {
            usu.setUser(user);
            usu.setPassword(password);
            saldo = saldo.replaceAll("\\s", "");
            saldo = saldo.replace(",", ".");
            usu.setSaldo(Double.parseDouble(saldo));

            if (usu.setUsuario()) {
                mensaje = "Registro exitoso";
            } else {
                mensaje = "registro fallido";
            }

        } else {
            mensaje = ban;
        }

        return mensaje;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "ValidarRegistro")
    public String ValidarRegistro(@WebParam(name = "user") String user, @WebParam(name = "pass") String pass, @WebParam(name = "pass2") String pass2, @WebParam(name = "saldo") String saldo) {

        String ban = "OK";
        //USER
        if (user.trim().isEmpty() && ban.equalsIgnoreCase("OK")) {
            ban = "Ingrese su nombre de usuario";
        }
        //PASSWORD
        if (pass.trim().isEmpty() && ban.equalsIgnoreCase("OK")) {
            ban = "Ingrese su contraseña";
        }
        //PASSWORD 2
        if (pass2.trim().isEmpty() && ban.equalsIgnoreCase("OK")) {
            ban = "Confirme su contraseña";
        }
        //PASSWORD EQUALS
        if (!pass.trim().equals(pass2.trim()) && ban.equalsIgnoreCase("OK")) {
            ban = "Escriba correctamente su contraseña " + pass + "==" + pass2;
        }

        //SALDO
        if (saldo.trim().isEmpty() && ban.equalsIgnoreCase("OK")) {
            System.out.println("vacio= " + saldo.trim().isEmpty() + " saldo= " + saldo);
            ban = "Ingrese su saldo";
        }
        return ban;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "LogIn")
    public String LogIn(@WebParam(name = "user") String user, @WebParam(name = "password") String password) {
        modelUsuario usu = new modelUsuario();
        String ban = ValidarLogIn(user, password);
        System.out.println("hola");
        if (ban.equalsIgnoreCase("OK")) {
            System.out.println("acceso= " + usu.Acceso(user, password));
            if (!usu.Acceso(user, password)) {
                ban = "Contraseña incorrecta";
            } else {
                ban = "OK";
            }
        }

        return ban;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "ValidarLogIn")
    public String ValidarLogIn(@WebParam(name = "user") String user, @WebParam(name = "pass") String pass) {
        modelUsuario usu = new modelUsuario();

        String ban = "OK";
        //USER
        if (user.trim().isEmpty() && ban.equalsIgnoreCase("OK")) {
            ban = "Ingrese su nombre de usuario";
        } else {
            //Existe
            if (!usu.Duplicado(user)) {
                ban = "El usuario que ingreso no existe";
            }
        }
        //PASSWORD
        if (pass.trim().isEmpty() && ban.equalsIgnoreCase("OK")) {
            ban = "Ingrese su contraseña";
        }

//        System.out.println("login= " + ban);
        return ban;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "ObtenerDatos")
    public Usuario ObtenerDatos(@WebParam(name = "user") String user, @WebParam(name = "pass") String pass) {
        modelUsuario usu = new modelUsuario();
        Usuario usuario = new Usuario();
        usuario = usu.Datos(user, pass);

        return usuario;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "ValidarTransaccion")
    public String ValidarTransaccion(@WebParam(name = "tipo") String tipo, @WebParam(name = "valor") String valor, @WebParam(name = "saldo") String saldo) {
        String ban = "OK";
        //tipo
        if (tipo.trim().isEmpty() && ban.equalsIgnoreCase("OK")) {
            ban = "Seleccione el tipo de transacción";
        }

        //valor
        if (valor.trim().isEmpty() && ban.equalsIgnoreCase("OK")) {
            ban = "Ingrese el valor de la transaccion";
        }
        return ban;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Transaccion")
    public String Transaccion(@WebParam(name = "tipo") String tipo, @WebParam(name = "valor") String valor, @WebParam(name = "idUser") int idUser, @WebParam(name = "saldo") String saldo) {
        String mssj = "",
                ban = ValidarTransaccion(tipo, valor, saldo);

        modelTransaccion transaccion = new modelTransaccion();
        if (ban.equalsIgnoreCase("OK")) {
            valor = valor.replaceAll("\\s", "");
            valor = valor.replace(",", ".");
            double valorD = Double.parseDouble(valor),
                    saldoD = Double.parseDouble(saldo);
            transaccion.setIdUser(idUser);
            transaccion.setTipo(tipo);
            transaccion.setValor(valorD);

            if (tipo.equalsIgnoreCase("Deposito")) {
                saldoD = saldoD + valorD;
                if (transaccion.setTransaccion()) {
                    mssj = "Transaccion exitosa";

                    transaccion.updateUser(idUser, saldoD);
                } else {
                    mssj = "Transaccion fallida";
                }
            } else {

                if (valorD > saldoD) {
                    mssj = "Fondos insuficientes";
                } else {
                    saldoD = saldoD - valorD;
                    if (transaccion.setTransaccion()) {
                        mssj = "Transaccion exitosa";

                        transaccion.updateUser(idUser, saldoD);
                    } else {
                        mssj = "Transaccion fallida";
                    }
                }
            }

        } else {
            mssj = ban;
        }
        return mssj;
    }

}
