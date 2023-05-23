/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bryan
 */
public class modelUsuario extends Usuario {

    modelPGconexion mpgc = new modelPGconexion();

    public boolean setUsuario() {
        String sql = "INSERT INTO public.usuario(\n"
                + "	usuario, password, saldo)\n"
                + "	VALUES ('" + getUser() + "', '" + getPassword() + "', " + getSaldo() + ");";
        return mpgc.accion(sql);
    }

   

    public Usuario Datos(String usu, String pass) {
        Usuario z = new Usuario();
        String sql = "SELECT id_user, usuario, password, saldo\n"
                + "	FROM public.usuario where (usuario='" + usu + "') and (password='" + pass + "');";
        ResultSet rs = mpgc.consulta(sql);
        try {
            while (rs.next()) {
                z.setId(rs.getInt(1));
                z.setUser(rs.getString(2));
                z.setPassword(rs.getString(3));
                z.setSaldo(rs.getDouble(4));
            }
        } catch (SQLException e) {
            Logger.getLogger(modelUsuario.class.getName()).log(Level.SEVERE, null, e);
        }
        try {
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(modelUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return z;
    }

//    public Usuario DatosXid(int id) {
//        Usuario u = new Usuario();
//        String sql = "SELECT id_user, usuario, password, saldo\n"
//                + "	FROM public.usuario where (id_user=" + id + ");";
//        ResultSet rs = mpgc.consulta(sql);
//        try {
//            while (rs.next()) {
//                u.setId(rs.getInt(1));
//                u.setUser(rs.getString(2));
//                u.setPassword(rs.getString(3));
//                u.setSaldo(rs.getDouble(4));
//            }
//        } catch (SQLException e) {
//            Logger.getLogger(modelUsuario.class.getName()).log(Level.SEVERE, null, e);
//        }
//        try {
//            rs.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(modelUsuario.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return u;
//    }

    public boolean Acceso(String usu, String pass) {
        boolean ban = false;

        int cont = 0;
        String sql = "SELECT count(*)\n"
                + "	FROM public.usuario where (usuario='" + usu + "') AND (password='" + pass + "');";
        ResultSet rs = mpgc.consulta(sql);
        try {
            while (rs.next()) {
                cont = rs.getInt(1);
            }

            if (cont > 0) {
                ban = true;
            }
        } catch (SQLException e) {
            Logger.getLogger(modelUsuario.class.getName()).log(Level.SEVERE, null, e);
        }
        try {
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(modelUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ban;
    }

    public boolean Duplicado(String usu) {
        boolean ban = false;

        int cont = 0;
        String sql = "SELECT count(*)\n"
                + "	FROM public.usuario where (usuario='" + usu + "');";
        ResultSet rs = mpgc.consulta(sql);
        try {
            while (rs.next()) {
                cont = rs.getInt(1);
            }

            if (cont > 0) {
                ban = true;
            }
        } catch (SQLException e) {
            Logger.getLogger(modelUsuario.class.getName()).log(Level.SEVERE, null, e);
        }
        try {
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(modelUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ban;
    }

//    public boolean updateZapato(int id) {
//        String sql;
//        sql = "UPDATE zapato "
//                + "	SET marca='" + getMarca() + "', costo=" + getCosto() + ", cantidad=" + getCantidad() + ", tipo='" + getTipo() + "' "
//                + "	WHERE zapato.id =" + id + ";";
//        return mpgc.accion(sql);
//    }
}
