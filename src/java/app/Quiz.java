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
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author re039859
 */
public class Quiz {

    private int id;
    private String questao;
    private String resp1;
    private String resp2;
    private String resp3;
    private String resp4;
    private String respCerta;

    public static int buscaResposta(String resposta, int questao) throws Exception {
        int qtd = 0;

        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.URL);
        String SQL = "SELECT count(*) as qtd FROM quiz WHERE id=? AND respCerta=?";
        PreparedStatement stmt = con.prepareStatement(SQL);
        stmt.setInt(1, questao);
        stmt.setString(2, resposta);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            qtd = rs.getInt("qtd");
        }

        rs.close();
        stmt.close();
        con.close();

        return qtd;

    }

    public static ArrayList<Quiz> getQuiz() throws Exception {
        ArrayList<Quiz> list = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection(DbListener.URL);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM quiz");
        while (rs.next()) {
            list.add(new Quiz(
                    rs.getInt("id"),
                    rs.getString("questao"),
                    rs.getString("resp1"),
                    rs.getString("resp2"),
                    rs.getString("resp3"),
                    rs.getString("resp4"),
                    rs.getString("respCerta")));
        }
        rs.close();
        stmt.close();
        con.close();
        return list;
    }

    public Quiz(int id, String questao, String resp1, String resp2, String resp3, String resp4, String respCerta) {
        this.id = id;
        this.questao = questao;
        this.resp1 = resp1;
        this.resp2 = resp2;
        this.resp3 = resp3;
        this.resp4 = resp4;
        this.respCerta = respCerta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestao() {
        return questao;
    }

    public void setQuestao(String questao) {
        this.questao = questao;
    }

    public String getResp1() {
        return resp1;
    }

    public void setResp1(String resp1) {
        this.resp1 = resp1;
    }

    public String getResp2() {
        return resp2;
    }

    public void setResp2(String resp2) {
        this.resp2 = resp2;
    }

    public String getResp3() {
        return resp3;
    }

    public void setResp3(String resp3) {
        this.resp3 = resp3;
    }

    public String getResp4() {
        return resp4;
    }

    public void setResp4(String resp4) {
        this.resp4 = resp4;
    }

    public String getRespCerta() {
        return respCerta;
    }

    public void setRespCerta(String respCerta) {
        this.respCerta = respCerta;
    }

}
