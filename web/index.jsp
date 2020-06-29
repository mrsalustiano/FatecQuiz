<%-- 
    Document   : index
    Created on : 01/06/2020, 11:29:43
    Author     : rlarg
--%>

<%@page import="app.Quiz"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collections"%>
<%@page import="db.DbListener" %>
<%@page import="app.Pontuacao" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Exception requestException = null;

    ArrayList<Pontuacao> list = new ArrayList<>();
    try {
        list = Pontuacao.buscaTodos();
    } catch (Exception ex) {
        out.println("<h3>Erro: " + ex.getMessage() + "</h3>");
    }


%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fatec Quiz</title>
        <%@include file="WEB-INF/jspf/bootstrap.jspf" %>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/menu.jspf" %>
        <h2>Index</h2>
        <div>
            <p>
                Bem vindo ao <b><u>Fatec Quiz</u></b>, sistema de Quiz.
                        <%if (requestException != null) {%>
            <div style="color:red"><%= requestException.getMessage()%></div>
            <%}%>
        </p>
        <h2>Listagem Geral de Pontuação</h2>
        <br>
        <table border="1">
            <thead>
            <th>Nome</th>
            <th>Pontuação</th>
            <th>Data </th>
            </thead>


            <% for (Pontuacao p : list) {%>
            <tbody>
            <td><%= p.getNome()%> </td>
            <td><%= p.getPontos()%></td>
            <td> <%= p.getData()%></td>
            <% } %>
            </tbody>
        </table>
            <br>
        <%if (session.getAttribute("user.login") == null) {%>
        <p>Você precisa estar autenticado para ter acesso ao conteúdo dos quizes do seu usuario.</p>
        <%} else {%>
        <a href="ListaUsuario.jsp?login=<%=session.getAttribute("user.login")%>">Listar</a>
        <%}%>  


        <input type="hidden" name="login" value="<%=session.getAttribute("user.login")%>"/>
        <input type="hidden" name="nome" value="<%= session.getAttribute("user.name")%>"/>



        <%@include file="WEB-INF/jspf/footer.jspf" %>            
</body>
</html>
