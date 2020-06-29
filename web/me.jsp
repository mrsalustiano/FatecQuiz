<%-- 
    Document   : me
    Created on : 08/06/2020, 21:53:48
    Author     : rlarg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Exception requestException = null;
    if (request.getParameter("changePassword") != null) {
        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String newPassword = request.getParameter("newPassword");
            String newPassword2 = request.getParameter("newPassword2");
            if (User.getUser(login, password) == null) {
                throw new Exception("Login/password incorrect");
            } else if (!newPassword.equals(newPassword2)) {
                throw new Exception("Password confirmation incorrect");
            } else {
                User.changePassword(login, newPassword);
                response.sendRedirect(request.getRequestURI());
            }
        } catch (Exception ex) {
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
        <%if (session.getAttribute("user.login") == null) {%>
        <div class="alert alert-danger" role="alert">
            Você precisa estar autenticado para ter acesso ao conteúdo desta página.
        </div>

        <%} else {%>
        <%if (requestException != null) {%>
        <div style="color:red"><%= requestException.getMessage()%></div>
        <%}%>

        <h3>Login
            <input class="form-control" type="text" placeholder="<%= session.getAttribute("user.login")%>" readonly>    
        </h3>    

        <h3>Nome: 
            <input class="form-control" type="text" placeholder="<%= session.getAttribute("user.name")%>" readonly>
        </h3>

        <h3>Papel: 
            <input class="form-control" type="text" placeholder="<%= session.getAttribute("user.role")%>" readonly> 
        </h3>

        <h3>Redefiniçao de senha</h3>
        <form method="post">
            <input type="hidden" name="login" value="<%=session.getAttribute("user.login")%>"/>

            <div class="form-group">
                <label for="senhaAtual">Senha Atual:</label>
                <input class="form-control" id="senhaAtual" placeholder="Digite a senha Atual" type="password" name="password"/>
            </div>
            <div class="form-group">
                <label for="senhaNova1">Nova senha:</label>
                <input class="form-control" id="senhaNova1" placeholder="Digite a nova senha" type="password" name="newPassword"/>
            </div>

            <div class="form-group">
                <label for="senhaNova2">Confirmação da nova senha:</label>
                <input class="form-control" id="senhaNova2" placeholder="Repita a nova senha"  type="password" name="newPassword2"/>
            </div>

            <input class="btn btn-primary" type="submit" name="changePassword" value="Redefinir"/>
        </form>
        <%}%>

        <%@include file="WEB-INF/jspf/footer.jspf" %> 
    </body>
</html>
