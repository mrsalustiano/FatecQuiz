<%-- 
    Document   : ListaUsuario
    Created on : 29/06/2020, 02:14:57
    Author     : re039859
--%>
<%@page import="app.Quiz"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collections"%>
<%@page import="db.DbListener" %>
<%@page import="app.Pontuacao" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Exception requestException = null;
    
         String login2 = request.getParameter("login");
            String name = String.valueOf(session.getAttribute("user.nome"));

        ArrayList<Pontuacao> listUser = new ArrayList<>();
        try {
            listUser = Pontuacao.buscaPorUsuario(login2);
        } catch (Exception ex) {
            out.println("<h3>Erro: " + ex.getMessage() + "</h3>");
    }     
 %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listagem Por Usuario</title>
          <%@include file="WEB-INF/jspf/bootstrap.jspf" %>
    </head>
    <body>
         <%@include file="WEB-INF/jspf/menu.jspf" %>
        <h2>Meu perfil</h2>
        
        <%if(session.getAttribute("user.login") == null){%>
            <p>Você precisa estar autenticado para ter acesso ao conteúdo desta página.</p>
        <%}else{%>
            <%if(requestException != null){%>
                <div style="color:red"><%= requestException.getMessage() %></div>
            <%}%>
            
                <h2>Listagem Dos ultimos Quizes efetuados por : <%= request.getParameter("login") %> </h2>
                <br>
            <table border="1">
                <thead>
                <th>Nome</th>
                <th>Pontuação</th>
                <th>Data </th>
                </thead>
           
                   <% for (Pontuacao p2: listUser){ %>
               <tbody>
                <td><%= p2.getNome() %> </td>
                <td><%= p2.getPontos() %></td>
                <td> <%= p2.getData() %></td>
                <% } %>
                 </tbody>
                
            </table>
 
                
        <%}%>
        
            <%@include file="WEB-INF/jspf/footer.jspf" %>   
           </body>
</html>
