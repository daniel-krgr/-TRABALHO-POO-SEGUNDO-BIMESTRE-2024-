/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.pet.dogui.poo.respositories;

import br.unipar.pet.dogui.poo.domain.Cachorro;
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
//Table cachorro
public class CachorroRepository {
    
    //Constantes com os SQLs
    private static final String INSERT = 
            "INSERT INTO CACHORRO (NOME, VL_TAMANHO, ST_PERFUME, DT_NASCIMENTO, ID_RACA, ID_PELAGEM, ID_COR) VALUES (?, ?, ?, ?, ?, ?, ?);";
    
    private static final String UPDATE = 
            "UPDATE cachorro SET nome=?, vl_tamanho=?, st_perfume=?, dt_nascimento=?, id_raca=?, id_pelagem=?, id_cor=? WHERE id=?";
    
    private static final String DELETE = 
            "DELETE FROM cachorro WHERE id=?";
    
    private static final String FIND_BY_ID = 
            "SELECT id, nome, vl_tamanho, st_perfume, dt_nascimento, id_raca, id_pelagem, id_cor " +
            "FROM cachorro WHERE id = ?";
    
    private static final String FIND_ALL =
            "SELECT id, nome, vl_tamanho, st_perfume, dt_nascimento, id_raca, id_pelagem, id_cor FROM cachorro";
            
    
    
    //Insert de um novo cachorro
    public Cachorro insert(Cachorro cachorro) throws SQLException {
        
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
            pstmt.setString(1, cachorro.getNome());
            pstmt.setDouble(2, cachorro.getTamanho());
            pstmt.setBoolean(3, cachorro.isStPerfume());
            pstmt.setDate(4, new java.sql.Date(cachorro.getDtNascimento().getTime()));
            pstmt.setInt(5, cachorro.getRaca().getId());
            pstmt.setInt(6, cachorro.getPelagem().getId());
            pstmt.setInt(7, cachorro.getCor().getId());
            //Executo a query no banco
            pstmt.executeUpdate();
            //recupera para o resultset o id gerado pelo banco
            rs = pstmt.getGeneratedKeys();
            //ativa o cursor
            rs.next();
            //Recupero o id do resultset e atribuo ao objeto cachorro
            cachorro.setId(rs.getInt(1));
            
        } finally {
            //Fecho os objetos de conexão com banco de dados
            if (rs != null)
                rs.close();
            
            if (pstmt != null)
                pstmt.close();
            
            if (conn != null)
                conn.close();
        }
        
        return cachorro;
    }
    
    //Update de cachorro
    public Cachorro update(Cachorro cachorro) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
                
        try {
            conn = new ConnectionFactory().getConnection();
            
            ps = conn.prepareStatement(UPDATE);
            ps.setString(1, cachorro.getNome());
            ps.setDouble(2, cachorro.getTamanho());
            ps.setBoolean(3, cachorro.isStPerfume());
            ps.setDate(4, new java.sql.Date(cachorro.getDtNascimento().getTime()));
            ps.setInt(5, cachorro.getRaca().getId());
            ps.setInt(6, cachorro.getPelagem().getId());
            ps.setInt(7, cachorro.getCor().getId());
            ps.setInt(8, cachorro.getId());
            ps.executeUpdate();

        } finally {
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }

        return cachorro;
    }
    
    //Busca uma unica cachorro pela pk 
    public Cachorro findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Cachorro retorno = null;
        
        try {
            
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {               
                retorno = new Cachorro();
                retorno.setId(rs.getInt("ID"));
                retorno.setNome(rs.getString("NOME"));
                retorno.setTamanho(rs.getDouble("VL_TAMANHO"));
                retorno.setStPerfume(rs.getBoolean("ST_PERFUME"));
                retorno.setDtNascimento(rs.getDate("DT_NASCIMENTO"));
                retorno.setRaca(new RacaRepository().findById(rs.getInt("ID_RACA")));
                retorno.setPelagem(new PelagemRepository().findById(rs.getInt("ID_PELAGEM")));
                retorno.setCor(new CorRepository().findById(rs.getInt("ID_COR")));
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
    
    //Busca todos os Cachorros cadastrados no banco
    public ArrayList<Cachorro> findAll() throws SQLException {
        
        ArrayList<Cachorro> retorno = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = new ConnectionFactory().getConnection();
            //SELECT id, nome, vl_tamanho, st_perfume, dt_nascimento, id_raca, id_pelagem, id_cor FROM cachorro";
            pstmt = conn.prepareStatement(FIND_ALL);
            //EXecuta a consulta no banco e recupera os resultados
            //no resultset
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Cachorro cachorro = new Cachorro();
                cachorro.setId(rs.getInt("ID"));
                cachorro.setNome(rs.getString("NOME"));
                cachorro.setTamanho(rs.getDouble("VL_TAMANHO"));
                cachorro.setStPerfume(rs.getBoolean("ST_PERFUME"));
                cachorro.setDtNascimento(rs.getDate("DT_NASCIMENTO"));
                cachorro.setRaca(new RacaRepository().findById(rs.getInt("ID_RACA")));
                cachorro.setPelagem(new PelagemRepository().findById(rs.getInt("ID_PELAGEM")));
                cachorro.setCor(new CorRepository().findById(rs.getInt("ID_COR")));
                retorno.add(cachorro);
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

