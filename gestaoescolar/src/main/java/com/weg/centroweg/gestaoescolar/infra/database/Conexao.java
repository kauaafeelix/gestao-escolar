package com.weg.centroweg.gestaoescolar.infra.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/gestao_escolar?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "mysqlPW";

    public static Connection conectar()throws SQLException{

        return DriverManager.getConnection(URL, USER, PASS);
    }

}
