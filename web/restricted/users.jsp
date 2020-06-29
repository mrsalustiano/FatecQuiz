<%-- 
    Document   : index
    Created on : 01/06/2020, 11:29:43
    Author     : rlarg
--%>

<%@page import="db.DbListener"%>
<%@page import="app.User"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Exception requestException = null;
    if(request.getParameter("insert")!=null){
        try{
            String login = request.getParameter("login");
            String name = request.getParameter("name");
            String role = request.getParameter("role");
            String password = request.getParameter("password");
            User.addUser(login, name, role, password);
            response.sendRedirect(request.getRequestURI());
        }catch(Exception ex){
            requestException = ex;
        }
    }
    if(request.getParameter("delete")!=null){
        try{
            String login = request.getParameter("login");
            User.removeUser(login);
            response.sendRedirect(request.getRequestURI());
        }catch(Exception ex){
            requestException = ex;
        }
    }
    ArrayList<User> list = new ArrayList<>();
    try{
        list = User.getUsers();
    }catch(Exception ex){
        out.println("<h3>Erro: "+ex.getMessage()+"</h3>");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index - Usuários</title>
          <%@include file="../WEB-INF/jspf/bootstrap.jspf" %>
    </head>
    <body>
        <%@include file="../WEB-INF/jspf/menu.jspf" %>
        <h2>Usuários</h2>
        <%if(session.getAttribute("user.login") == null){%>
            <p>Você precisa estar autenticado para ter acesso ao conteúdo desta página.</p>
        <%}else{%>
            <%if(!session.getAttribute("user.role").equals("ADMIN")){%>
                <p>Você precisa ser administrador do sistema para ter acesso ao conteúdo desta página.</p>
            <%}else{%>
                <%if(requestException != null){%>
                    <div style="color:red"><%= requestException.getMessage() %></div>
                <%}%>
                <fieldset>
                    <legend>Novo usuário</legend>
                    <form method="post">
                        Login: <input type="text" name="login"/>
                        Nome: <input type="text" name="name"/>
                        Papel: <select name="role">
                            <option value="ADMIN">ADMIN</option>
                            <option value="USER">USER</option>
                        </select>
                        Senha: <input type="password" name="password"/>
                        <input type="submit" name="insert"/>
                    </form>
                </fieldset>
                <hr/>
                <table border="1">
                    <tr>
                        <th>Nome do usuário</th>
                        <th>Login</th>
                        <th>Papel</th>
                        <th>Comandos</th>
                    </tr>
                    <%for(User u: list){%>
                    <tr>
                        <td><%= u.getName() %></td>
                        <td><%= u.getLogin() %></td>
                        <td><%= u.getRole() %></td>
                        <td>
                            <form method="post">
                                <input type="hidden" name="login" value="<%=u.getLogin()%>"/>
                                <input type="submit" name="delete" value="Remover"/>
                            </form>
                        </td>
                    </tr>
                    <%}%>
                </table>
            <%}%>
        <%}%>
            <%@include file="../WEB-INF/jspf/footer.jspf" %> 
    </body>
</html>
