/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.pet.dogui.poo.respositories;

import br.unipar.pet.dogui.poo.domain.Raca;
import br.unipar.pet.dogui.poo.infraestructure.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Daniel
 */    
//Responsavel pelas interações com o banco de dados
//Table raca
public class RacaRepository {
    
    //Constantes com os SQLs
    private static final String INSERT = 
            "INSERT INTO RACA(DS_RACA) VALUES(?)";
    
    private static final String UPDATE = 
            "UPDATE raca SET ds_raca=? WHERE id=?";
    
    private static final String DELETE = 
            "DELETE FROM raca WHERE id=?";
    
    private static final String FIND_BY_ID = 
            "SELECT id, ds_raca " +
            "FROM raca WHERE id = ?";
    
    private static final String FIND_ALL =
            "SELECT id, ds_raca FROM raca";
    
    //Insert de uma nova raca
    public Raca insert(Raca raca) throws SQLException {
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            //Pego a conexão de banco de dados
            conn = new ConnectionFactory().getConnection();
            //Crio o preparedStatement
            pstmt = conn.prepareStatement(INSERT, 
                    Statement.RETURN_GENERATED_KEYS);
            //seto os parametros da minha consulta
            pstmt.setString(1, raca.getDescricao());
            //Executo a query no banco
            pstmt.executeUpdate();
            //recupera para o resultset o id gerado pelo banco
            rs = pstmt.getGeneratedKeys();
            //ativa o cursor
            rs.next();
            //Recupero o id do resultset e atribuo ao objeto raca
            raca.setId(rs.getInt(1));
            
        } finally {
            //Fecho os objetos de conexão com banco de dados
            if (rs != null)
                rs.close();
            
            if (pstmt != null)
                pstmt.close();
            
            if (conn != null)
                conn.close();
        }
        
        return raca;
    }
    
    //Update de raca
    public Raca update(Raca raca) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
                
        try {

            conn = new ConnectionFactory().getConnection();

            ps = conn.prepareStatement(UPDATE);
            ps.setString(1, raca.getDescricao());
            ps.setInt(2, raca.getId());
            ps.executeUpdate();

        } finally {
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }

        return raca;
    }
    
    //Busca uma unica raca pela pk 
    public Raca findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Raca retorno = null;
        
        try {
            
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
               retorno = new Raca();
               retorno.setId(rs.getInt("ID"));
               retorno.setDescricao(rs.getString("DS_RACA"));
            }
            
        } finally {
            
            if (rs != null)
                rs.close();
            
            if (pstmt != null)
                pstmt.close();
            
            if (conn != null)
                conn.close();
            
        }
        
        return retorno;
        
    }
    
    //Busca todas as Raças cadastradas no banco
    public ArrayList<Raca> findAll() throws SQLException {
        
        ArrayList<Raca> retorno = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = new ConnectionFactory().getConnection();
            //SELECT id, ds_raca FROM raca
            pstmt = conn.prepareStatement(FIND_ALL);
            //EXecuta a consulta no banco e recupera os resultados
            //no resultset
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                
                Raca raca = new Raca();
                raca.setId(rs.getInt("ID"));
                raca.setDescricao(rs.getString("DS_RACA"));
                //adiciono no arraylist de retorno
                retorno.add(raca);
                
            }
            
        } finally {
            
            if (rs != null)
                rs.close();
            
            if (pstmt != null)
                pstmt.close();
            
            if (conn != null) 
                conn.close();
        }
        
        return retorno;
    }
    
    public void delete(int id) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
                
        try {

            conn = new ConnectionFactory().getConnection();

            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, id);
            ps.execute();

        } finally {
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }

    }
}
