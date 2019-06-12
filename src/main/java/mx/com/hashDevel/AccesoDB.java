/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.hashDevel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author david
 */
public class AccesoDB {

    private Connection conexion = null;
    private Statement comando = null;
    private ResultSet resultados = null;

    /**
     * Inicia la conexion a la base de datos con un nombre de usuario y
     * contraseña predefinidos
     *
     * @throws Exception
     */
    public void iniciarConexion() throws Exception {
        String usuario = "inventario";
        String passwd = "1973";
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/juegos" + "?" + "user=" + usuario + "&" + "password=" + passwd + "");

        } catch (Exception e) {
            System.err.println("Error al inciar la conexion a base de datos\t\t");
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Consulta los datos en la tabla juegos, obtiene todos
     *
     * @throws SQLException
     */
    public void consultarDatos() throws SQLException {
        String instruccion = "SELECT * FROM juegos.juegos";
        try {
            comando = conexion.createStatement();
            resultados = comando.executeQuery(instruccion);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void cerrarConexion() throws SQLException {
        try {
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void imprimirDatos() throws SQLException {
        int sistema;
        int obtencion;
        String nombre;

        while (resultados.next()) {
            try {
                sistema = resultados.getInt("consola");
                obtencion = resultados.getInt("obtenido");
                nombre = resultados.getString("nombre");
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
            System.out.print(nombre + "\t\t\t");
            if (sistema == 1) {
                System.out.print("Nintendo DS\t");
            } else if (sistema == 2) {
                System.out.print("Nintendo 3DS\t");
            } else {
                System.out.print("Xbox 360\t");
            }

            if (obtencion == 1) {
                System.out.println("Nuevo");
            } else {
                System.out.println("Segunda mano");
            }
        }
    }
}
