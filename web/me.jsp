<%-- 
    Document   : me
    Created on : 08/06/2020, 21:53:48
    Author     : rlarg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Exception requestException = null;
    if(request.getParameter("changePassword")!=null){
        try{
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String newPassword = request.getParameter("newPassword");
            String newPassword2 = request.getParameter("newPassword2");
            if(User.getUser(login, password)==null){
                throw new Exception("Login/password incorrect");
            }else if(!newPassword.equals(newPassword2)){
                throw new Exception("Password confirmation incorrect");
            }else{
                User.changePassword(login, newPassword);
                response.sendRedirect(request.getRequestURI());
            }
        }catch(Exception ex){
            requestException = ex;
        }
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Meu perfil</title>
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
            <h3>Login: <%= session.getAttribute("user.login") %></h3>
            <h3>Nome: <%= session.getAttribute("user.name") %></h3>
            <h3>Papel: <%= session.getAttribute("user.role") %> </h3>
            <h3>Redefiniçao de senha</h3>
            <form method="post">
                <input type="hidden" name="login" value="<%=session.getAttribute("user.login")%>"/>
                Senha atual:<br/><input type="password" name="password"/><br/>
                Nova senha:<br/><input type="password" name="newPassword"/><br/>
                Confirmação da nova senha:<br/><input type="password" name="newPassword2"/><br/>
                <br/><input type="submit" name="changePassword" value="Redefinir"/>
            </form>
        <%}%>
        
            <%@include file="WEB-INF/jspf/footer.jspf" %> 
    </body>
</html>
