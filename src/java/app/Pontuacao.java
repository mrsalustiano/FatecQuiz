/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import db.DbListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author re039859
 */
public class Pontuacao {
    
    
    private String nome;
    private String login;
    private String data;
    private int pontos;

    public Pontuacao( String nome, String login, String data, int pontos) {
        
        this.nome = nome;
        this.login = login;
        this.data = data;
        this.pontos = pontos;
    }




    public String getNome() {
        return nome;
    }

    public void setUser(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

  
    
    
    public static  ArrayList<Pontuacao> buscaPorUsuario(String usuario) throws ClassNotFoundException, SQLException {
        
        ArrayList<Pontuacao> list = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.URL);
        
        String SQL = "SELECT * FROM pontuacao where login=? order by data desc";
        
        PreparedStatement preparedStatement = con.prepareStatement(SQL);
        preparedStatement.setString(1,usuario);
        
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            
            list.add(new Pontuacao( rs.getString("nome"), rs.getString("login"), rs.getString("data"),rs.getInt("pontos")));
        }
        rs.close();
        preparedStatement.close();
        con.close();
        return list;
    }
    
    
      
    public static ArrayList<Pontuacao> buscaTodos() throws ClassNotFoundException, SQLException {
       
        ArrayList<Pontuacao> list = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.URL);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM pontuacao order by data desc");
        while(rs.next()){
            
            list.add(new Pontuacao( rs.getString("nome"), rs.getString("login"), rs.getString("data"),rs.getInt("pontos")));
        }
        rs.close();
        stmt.close();
        con.close();
        return list;
       
    }
    
    
      public static void addPontuacao(String nome, String login, int  pontos) throws Exception{
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
          
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.URL);
        String SQL = "INSERT INTO pontuacao(nome, login, data, pontos) VALUES(?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setString(1, nome);
        stmt.setString(2, login);
        
        // pega a data atual 
        Date date = new Date();
        String strDate = sdf.format(date);
        
        stmt.setString(3,strDate );
        
        stmt.setInt(4, pontos);
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    
    
}
