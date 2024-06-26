package br.com.sistemaReservas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.sistemaReservas.model.Restaurante;

import java.util.ArrayList;

public class DAORestaurante {
    
    Connection connection = null;
    PreparedStatement comandoSQL = null;
    ResultSet resultSet = null;

    public List<Restaurante> capturaRestaurantes() {

        List<Restaurante> listaRestaurantes = new ArrayList<>();
        
        try {
            connection = Conexao.abrirConexao();
            comandoSQL = connection.prepareStatement("SELECT * FROM Restaurantes");
            resultSet = comandoSQL.executeQuery();

            while(resultSet.next()) {
                Restaurante restaurante = new Restaurante(
                    (long) resultSet.getInt("Id"), 
                    resultSet.getString("NomeRestaurante"), 
                    resultSet.getString("Endereco"), 
                    resultSet.getString("Cep"), 
                    resultSet.getString("Telefone"),
                    resultSet.getString("nomeImagem")
                );
                listaRestaurantes.add(restaurante);
            }

            return listaRestaurantes;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("ERRO 10");
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (comandoSQL != null) comandoSQL.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("ERRO 20");
            }
        }

        return null;
    }

    public List<Restaurante> buscarRestaurantesPorNome(String nome) {
        List<Restaurante> listaRestaurantes = new ArrayList<>();

        try {
            connection = Conexao.abrirConexao();
            comandoSQL = connection.prepareStatement("SELECT * FROM Restaurantes WHERE UPPER(NomeRestaurante) LIKE ?");
            comandoSQL.setString(1, "%" + nome.toUpperCase() + "%"); // Usamos LIKE para buscar por parte do nome
            resultSet = comandoSQL.executeQuery();

            while(resultSet.next()) {
                Restaurante restaurante = new Restaurante(
                        (long) resultSet.getInt("Id"),
                        resultSet.getString("NomeRestaurante"),
                        resultSet.getString("Endereco"),
                        resultSet.getString("Cep"),
                        resultSet.getString("Telefone"),
                        resultSet.getString("nomeImagem")
                );
                listaRestaurantes.add(restaurante);
            }

            return listaRestaurantes;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("ERRO 10");
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (comandoSQL != null) comandoSQL.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("ERRO 20");
            }
        }

        return null;
    }

    public Restaurante buscaRestauranteID(String id) {
        try {
            connection = Conexao.abrirConexao();
            comandoSQL = connection.prepareStatement("SELECT * FROM restaurantes WHERE id = ?");
            comandoSQL.setString(1, id);
            resultSet = comandoSQL.executeQuery();

            if(resultSet.next()) {
                Restaurante restaurante = new Restaurante(
                    (long) resultSet.getInt("Id"), 
                    resultSet.getString("NomeRestaurante"), 
                    resultSet.getString("Endereco"), 
                    resultSet.getString("Cep"), 
                    resultSet.getString("Telefone"),
                    resultSet.getString("nomeImagem")
                );
                return restaurante;
            }
    
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("ERRO 100");
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (comandoSQL != null) comandoSQL.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("ERRO 200");
            }
        }
        
        return null;
    }

}