<%-- 
    Document   : quiz
    Created on : 22/06/2020, 23:32:34
    Author     : re039859
--%>
<%@page import="java.util.Collections"%>
<%@page import="db.DbListener" %>
<%@page import="app.Quiz" %>
<%@page import="app.Pontuacao" %>


<%@page import="java.util.ArrayList" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Exception requestException = null;
    int soma = 0;

    ArrayList<Quiz> list = new ArrayList<>();
    try {
        list = Quiz.getQuiz();
        Collections.shuffle(list);
        // System.out.println("Tamanho da Lista = " + list.size());
    } catch (Exception ex) {
        out.println("<h3>Erro: " + ex.getMessage() + "</h3>");
    }

    if (request.getParameter("enviar") != null) {
        try {
            String login = request.getParameter("login");
            String name = request.getParameter("nome");
            int pontuacao = 0;

            for (int i = 1; i < 11; i++) {

                String resposta = request.getParameter("respostas" + i);
                String questao = request.getParameter("id" + i);
               // System.out.println(resposta);
               // System.out.println(questao);

                // pesquisar a resposta 
                int resp = 0;
                resp = Quiz.buscaResposta(resposta, Integer.parseInt(questao));
                // resp -> resposta do banco de dados
                if (resp == 1) {
                    pontuacao++;
                }
            }
            
            System.out.println("Pontuação : " + pontuacao);
            Pontuacao.addPontuacao(name, login, pontuacao);
              response.sendRedirect("/FatecQuiz/index.jsp");
            //   response.sendRedirect(request.getRequestURI());
        } catch (Exception ex) {
            requestException = ex;
            System.out.println(requestException);
        }
    }


%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz</title>
          <%@include file="WEB-INF/jspf/bootstrap.jspf" %>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/menu.jspf" %>
        <h1>Quiz</h1>
        <form method="post">
            <table border="1">
                <tr>

                    <th> Questão </th>
                    <th> Resposta  </th>

                </tr>
                <% int qtd = 0; %>
                <% for (Quiz q : list) { %>
                <% qtd++; %>
                <% if (qtd == 11) {
                        break;
                    }%>
                <tr>
                    <td><%= q.getQuestao()%> </td> 
                    <td>
                        <label for="resposta<%=qtd%>">Selecione a Resposta :</label>
                        <select name="respostas<%=qtd%>" id="respostas<%=qtd%>"  >
                            <option value=""> Selecione a Resposta Correta:</option>

                            <option value="<%= q.getResp1()%>"> <%= q.getResp1()%></option>
                            <option value="<%= q.getResp2()%>"> <%= q.getResp2()%></option>
                            <option value="<%= q.getResp3()%>"> <%= q.getResp3()%></option>
                            <option value="<%= q.getResp4()%>"> <%= q.getResp4()%></option>
                        </select>
                        <input type="hidden" value="<%= q.getId()%>" name="id<%=qtd%>" />


                    </td>
                </tr>

                <%}%>
            </table>

            <br>

            <input type="hidden" name="login" value="<%=session.getAttribute("user.login")%>"/>
            <input type="hidden" name="nome" value="<%= session.getAttribute("user.name")%>"/>
            <input type="submit" name="enviar" value="Enviar Respostas"/>

        </form>

    <%@include file="WEB-INF/jspf/footer.jspf" %> 
    </body>
</html>
