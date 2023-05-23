/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Bryan
 */
public class modelTransaccion extends Transaccion {

    modelPGconexion mpgc = new modelPGconexion();

    public boolean setTransaccion() {
        String sql = "INSERT INTO public.transaccion(\n"
                + "	tipo, valor, id_user)\n"
                + "	VALUES ('" + getTipo() + "', " + getValor() + ", " + getIdUser() + ");";
        return mpgc.accion(sql);
    }

    public boolean updateUser(int id, double saldo) {
        String sql;
        sql = "UPDATE public.usuario\n"
                + "	SET saldo=" + saldo + "\n"
                + "	WHERE id_user="+id+";";
        return mpgc.accion(sql);
    }
}
