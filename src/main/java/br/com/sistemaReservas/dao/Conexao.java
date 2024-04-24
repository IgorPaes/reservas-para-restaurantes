package br.com.sistemaReservas.dao;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
public class Conexao {
    public static String STATUS = "Não conectado";
    public static String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String SERVER = "localhost";
    public static String DATABASE = "sistema_reserva";
    public static String LOGIN = "root";
    public static String SENHA = "45Pass#DB";
    public static String URL = "";
    public static Connection CONEXAO;
    public Conexao() {}
    public static Connection abrirConexao() throws ClassNotFoundException,SQLException {

        URL = "jdbc:mysql://" + SERVER + ":3306/" +DATABASE + "?useTimezone=true&serverTimezone=UTC&useSSL=false";
        if(CONEXAO==null)
        {
            try {

                Class.forName(DRIVER);
                CONEXAO = DriverManager.getConnection(URL, LOGIN, SENHA);

                if (CONEXAO != null) {
                    STATUS = "Conexão realizada com sucesso!";
                } else {
                    STATUS = "Não foi possivel realizar a conexão";
                }

            } catch (ClassNotFoundException e) {

                throw new ClassNotFoundException("O driver expecificado nao foi encontrado.");

            } catch (SQLException e) {

                //Outra falha de conexão
                throw new SQLException("Erro ao estabelecer a conexão (Ex: login ou senha errados).");
            }

        }
        else
        {
            try {
                if(CONEXAO.isClosed())
                    CONEXAO = DriverManager.getConnection(URL, LOGIN, SENHA);
            } catch (SQLException ex) {
                throw new SQLException("Falha ao fechar a conexão.");
            }
        }
        return CONEXAO;
    }

    public static String getStatusConexao() {
        return STATUS;
    }

    public static boolean fecharConexao() throws SQLException {

        boolean retorno = false;

        try {
            if(CONEXAO!=null){
                if(!CONEXAO.isClosed())
                    CONEXAO.close();
            }

            STATUS = "Não conectado";
            retorno = true;

        } catch (SQLException e) {
            retorno = false;
        }

        return retorno;
    }
}
