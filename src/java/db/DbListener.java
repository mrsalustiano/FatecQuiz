/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import app.User;
import java.sql.ResultSet;

/**
 * Web application lifecycle listener.
 *
 * @author re039859
 */
public class DbListener implements ServletContextListener {

    public static final String URL = "jdbc:sqlite:C:\\Users\\re039859\\Documents\\workspace-netbeans\\FatecQuiz\\FatecQuiz.db";

    public static String exceptionMessage = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String step = "Starting database creation";
        int qtd = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection(URL);
            Statement stmt = con.createStatement();

            String SQL = null;

            ResultSet result = stmt.executeQuery("select count(*) as qtd from sqlite_master where tbl_name = 'usuario'  and type = 'table' ");

            while (result.next()) {
                qtd = result.getInt("qtd");
            }

            if (qtd == 0) {
                step = "users Table creation";
                SQL = "CREATE TABLE IF NOT EXISTS users("
                        + "name VARCHAR(200) NOT NULL,"
                        + "login VARCHAR(50) UNIQUE NOT NULL,"
                        + "password_hash LONG,"
                        + "role VARCHAR(20) NOT NULL"
                        + ")";
                stmt.executeUpdate(SQL);

                step = "Default users creation";
                if (User.getUsers().isEmpty()) {
                    SQL = "INSERT INTO users(name, login, password_hash, role) "
                            + "VALUES('Administrador', 'admin', '" + ("123456".hashCode()) + "', 'ADMIN')";
                    stmt.executeUpdate(SQL);
                    SQL = "INSERT INTO users(name, login, password_hash, role) "
                            + "VALUES('Fulano da Silva', 'fulano', '" + ("123456".hashCode()) + "', 'USER')";
                    stmt.executeUpdate(SQL);
                }

            }
            qtd = 0;

            ResultSet result2 = stmt.executeQuery("select count(*) as qtd from sqlite_master where tbl_name = 'quiz'  and type = 'table' ");

            while (result2.next()) {
                qtd = result2.getInt("qtd");
            }

            // Tabela Quiz
            if (qtd == 0) {
                step = "users Table creation";
                SQL = "CREATE TABLE IF NOT EXISTS quiz("
                        + "id int not null Primary key,"
                        + "questao VARCHAR(200) NOT NULL,"
                        + "resp1 VARCHAR(200) NOT NULL,"
                        + "resp2 VARCHAR(200) NOT NULL,"
                        + "resp3 VARCHAR(200) NOT NULL,"
                        + "resp4 VARCHAR(200) NOT NULL,"
                        + "respCerta VARCHAR(200) NOT NULL"
                        + ")";
                stmt.executeUpdate(SQL);

                step = "Default users creation";

                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz ( id, questao , resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (1, "
                        + "' Which of the following a is not a keyword in Java ?', "
                        + "'class', "
                        + "'interface', "
                        + "'extends', "
                        + "'abstraction', "
                        + "'abstraction' )";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz ( id, questao , resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (2, "
                        + "'What is true about Java ?', "
                        + "'Java is platform specific', "
                        + "'Java does not support multiple inheritance', "
                        + "'Java does not run on Linux and Mac', "
                        + "'Java is not a multi-threaded language ', "
                        + "'Java does not support multiple inheritance' ) ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz ( id, questao , resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (3, "
                        + "'Which of the following is an interface ?', "
                        + "'Thread', "
                        + "'Runnable', "
                        + "'Date', "
                        + "'Calendar', "
                        + "'Runnable' ) ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz ( id, questao , resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (4, "
                        + "'Which company released Java Version 8 ?', "
                        + "'Sun', "
                        + "'Oracle', "
                        + "'Adobe', "
                        + "'Google', "
                        + "'Oracle' ) ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz ( id, questao , resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (5, "
                        + "'Java comes under which category of languages ?', "
                        + "'First Generation Languages', "
                        + "'Second Generation Languages', "
                        + "'Third Generation Languages', "
                        + "'Fourth Generation Languages', "
                        + "'Third Generation Languages' ) ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz ( id, questao , resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (6, "
                        + "'Which is the default package that is automatically visible to your program ?', "
                        + "'java.net', "
                        + "'javax.swing', "
                        + "'java.io', "
                        + "'java.lang', "
                        + "'java.lang') ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz ( id, questao , resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (7, "
                        + "'Which entry in WEB-INF is used to map a servlet ?', "
                        + "'servlet-mapping', "
                        + "'servlet-registration', "
                        + "'servlet-entry', "
                        + "'servlet-attachment', "
                        + "'servlet-mapping') ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz ( id, questao , resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (8, "
                        + "'What is the length of Java datatype int ?', "
                        + "'32 bit', "
                        + "'16 bit', "
                        + "'64 bit', "
                        + "'Runtime specific', "
                        + "'32 bit') ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz ( id, questao , resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (9, "
                        + "'What is the default value of Java datatype boolean?', "
                        + "'true', "
                        + "'false', "
                        + "'1', "
                        + "'0', "
                        + "'false' ) ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz ( id, questao , resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (10, "
                        + "'What does PHP stands for ?', "
                        + "'Post Hypertext Processor', "
                        + "'Pre Hypertext Processor', "
                        + "'Hypertext Preprocessor', "
                        + "'Paper High Processor', "
                        + "'Pre Hypertext Processor' ) ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz ( id, questao , resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (11, "
                        + "'Where does PHP scripts runs ?', "
                        + "'Local Client Machine', "
                        + "'Server Machine', "
                        + "'Any Machine having PHP processor', "
                        + "'None of the above', "
                        + "'Any Machine having PHP processor') ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz ( id, questao , resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (12, "
                        + "'Which company vendor the PHP ?', "
                        + "'Zend', "
                        + "'Oracle', "
                        + "'Microsoft', "
                        + "'Its open source', "
                        + "'Its open source') ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz ( id, questao , resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (13, "
                        + "'Who invented PHP ?', "
                        + "'Eric Gamma', "
                        + "'Rasmus Ledroff', "
                        + "'E.R. Rodolf', "
                        + "'Mark Henrick', "
                        + "'Rasmus Ledroff') ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz ( id, questao , resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (14, "
                        + "'What is the extension of a PHP file ?', "
                        + "'.ppp', "
                        + "'.pptx', "
                        + "'.pph', "
                        + "'.php', "
                        + "'.php') ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz ( id, questao , resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (15, "
                        + "'How to write PHP code in a web page ?', "
                        + "'Inside php tag', "
                        + "'Inside php-script tag', "
                        + "'Inside php-code tag', "
                        + "'Inside hypertext tag', "
                        + "'Inside php tag') ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz ( id, questao , resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (16, "
                        + "'Which function you will use to print a \"Hello World\" on screen ?', "
                        + "'echo', "
                        + "'message', "
                        + "'execute', "
                        + "'show', "
                        + "'echo') ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz ( id, questao , resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (17, "
                        + "'Which function counts all the values of an array. ?', "
                        + "'array_count()', "
                        + "'array_all()', "
                        + "'array_count_values()', "
                        + "'array_total()', "
                        + "'array_count_values()') ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz ( id, questao , resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (18, "
                        + "'How can you access global variables in PHP ?', "
                        + "'$GLOBALS array', "
                        + "'$ALL array', "
                        + "'$COLLECTION array', "
                        + "'$VAR array', "
                        + "'$GLOBALS array') ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz ( id, questao , resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (19, "
                        + "'What is the default sorting order of PHP sort() function ?', "
                        + "'Descending Order', "
                        + "'Runtime Specific ', "
                        + "'Ascending Order', "
                        + "'Unpredictable', "
                        + "'Descending Order') ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz ( id, questao , resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (20, "
                        + "' ________ command is used to count the total number of lines, words and character in a file ?',"
                        + "'countw', "
                        + "' wcount', "
                        + "'wc', "
                        + "' count p', "
                        + "'wc') ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz ( id, questao , resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (21, "
                        + "' _______ command is used to remove the directory ?',"
                        + "'rdir', "
                        + "'remove', "
                        + "'rd', "
                        + "'rmdir', "
                        + "'rmdir') ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz ( id, questao , resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (22, "
                        + "'________ command is used to remove files ?',"
                        + "'dm', "
                        + "'rm', "
                        + "'delete', "
                        + "'erase', "
                        + "'rm') ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz ( id, questao , resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (23, "
                        + "'_______ hardware architectures are not supported by Red Hat ?',"
                        + "'SPARC', "
                        + "'IBM-compatible', "
                        + "'Macintosh', "
                        + "'Alpha', "
                        + "'Macintosh') ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz ( id, questao , resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (24, "
                        + "'Which of the following is not a communication command ?',"
                        + "'grep', "
                        + "'mail', "
                        + "'mesg', "
                        + "'write', "
                        + "'grep') ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz ( id, questao , resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (25, "
                        + "' What command is used to add printing jobs to the queue ?',"
                        + "'lpd', "
                        + "'lpr', "
                        + "'lpq', "
                        + "'lpc', "
                        + "'lpr') ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz ( id, questao , resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (26, "
                        + "'#!/bin/bash is commonly called as ',"
                        + "'shebang', "
                        + "'hashbang', "
                        + "'Script Initialiser', "
                        + "'1 and 2 is correct', "
                        + "'1 and 2 is correct') ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz ( id, questao , resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (27, "
                        + "'_____ is the world’s largest non-commercial Linux distribution ',"
                        + "'Debian', "
                        + "'Ubuntu', "
                        + "'Fedora', "
                        + "'Mint', "
                        + "'Debian') ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz ( id, questao , resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (28, "
                        + "'Which of the following is a command in Linux ?',"
                        + "'w', "
                        + "'x', "
                        + "'t', "
                        + "'All of the above', "
                        + "'w') ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz ( id, questao , resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (29, "
                        + "'What does GNU stands for ?',"
                        + "'Gnu’s not Unix', "
                        + "'Geek Needed Unix', "
                        + "'General Unix', "
                        + "'None of the Above', "
                        + "'Gnu’s not Unix') ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz (id, questao, resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (30, "
                        + "'SQL stands for ',"
                        + "'Show Query Language',"
                        + "'Structured Quantum Language',"
                        + "'Simple Query Language',"
                        + "'Structured Query Language',"
                        + "'Structured Query Language' ) ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz (id, questao, resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (31, "
                        + "'Which function is used to get number of total rows in a table ?',"
                        + "'all()',"
                        + "'collect()',"
                        + "'aggregate()',"
                        + "'count()',"
                        + "'count()' ) ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz (id, questao, resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (32, "
                        + "'What you understand by DML ?',"
                        + "'Data Manipulation Language',"
                        + "'Data Modelling Language',"
                        + "'Data Matured Language',"
                        + "'Data Mining Language',"
                        + "'Data Manipulation Language' ) ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz (id, questao, resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (33, "
                        + "'SQL is considered as ',"
                        + "'First Generation Language',"
                        + "'Second Generation Language',"
                        + "'Third Generation Language',"
                        + "'Fourth Generation Language',"
                        + "'Fourth Generation Language' ) ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz (id, questao, resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (34, "
                        + "'Which Join can possibly produce largest result ?',"
                        + "'Left Outer Join',"
                        + "'Natural Join',"
                        + "'Right Outer Join',"
                        + "'Cross Join',"
                        + "'Cross Join' ) ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz (id, questao, resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (35, "
                        + "'What does self join means ? ',"
                        + "'Joinning a table with itself',"
                        + "'Performing a right outer join with another table',"
                        + "'Performing a left outer join with another table',"
                        + "'Performing a full outer join with another table',"
                        + "'Joinning a table with itself' ) ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz (id, questao, resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (36, "
                        + "'Which of the following SQL clause can be used to group related rows',"
                        + "'having',"
                        + "'group by',"
                        + "'connect',"
                        + "'together',"
                        + "'group by' ) ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz (id, questao, resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (37, "
                        + "'Which operator is used to perform matching ?',"
                        + "'like',"
                        + "'similar',"
                        + "'same',"
                        + "'almost',"
                        + "'like' ) ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz (id, questao, resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (38, "
                        + "'which of the following a not a constraint type in SQL ?',"
                        + "'Primary Key',"
                        + "'Foreign Key',"
                        + "'Not Null',"
                        + "'Put Null',"
                        + "'Put Null' ) ";
                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz (id, questao, resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (39, "
                        + "'How can you show an alert box using JavaScript ?',"
                        + "' frame.alert()',"
                        + "' window.alert()',"
                        + "'warning.alert()',"
                        + "' authenticate.alert()',"
                        + "' window.alert()' )";

                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz (id, questao, resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (40, "
                        + "' Which operator can be used to find out the data type of a Javascript variable ?',"
                        + "'varType',"
                        + "'getType',"
                        + "'showType',"
                        + "'typeof',"
                        + "'typeof' )";

                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz (id, questao, resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (41, "
                        + "'What is true about Javascript ?',"
                        + "'Javascript can be used to perform client side validation ?',"
                        + "'Javascript can be used to perform server side validation',"
                        + "'Javascript is an acronym for Java language',"
                        + "'Javascript is an obsolete language that is not used nowadays',"
                        + "'Javascript can be used to perform client side validation ?' )";

                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz (id, questao, resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (42, "
                        + "'What is the relationship between Javascript and jQuery ?',"
                        + "'Javascript is a library of jQuery',"
                        + "'jQuery is a library of Javascript',"
                        + "'jQuery is an abbreviation of Javascript',"
                        + "'jQuery is a database query language',"
                        + "'jQuery is a library of Javascript' )";

                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz (id, questao, resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (43, "
                        + "'Which Javascript method can be used to convert a string into a number ?',"
                        + "'parseString()',"
                        + "'parseInt()',"
                        + "'parseNumber()',"
                        + "'parseInteger()',"
                        + "'parseInt()' )";

                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz (id, questao, resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (44, "
                        + "'Inside which HTML element do we put the JavaScript ?',"
                        + "'script',"
                        + "'javascript',"
                        + "'scripting',"
                        + "'js',"
                        + "'script' )";

                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz (id, questao, resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (45, "
                        + "'Where is the correct place to insert a JavaScript ?',"
                        + "'head section',"
                        + "'body section',"
                        + "'title section',"
                        + "'both head and body section',"
                        + "'head section' )";

                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz (id, questao, resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (46, "
                        + "'How can you add a comment in a JavaScript ?',"
                        + "'//This is a comment',"
                        + "'!!This is a comment',"
                        + "'@@This is a comment',"
                        + "'##This is a comment',"
                        + "'//This is a comment' )";

                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz (id, questao, resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (47, "
                        + "'Which function can be used to find out if an expression (or a variable) is true:  ?',"
                        + "'checkFalse()',"
                        + "'bool()',"
                        + "'checkTrue()',"
                        + "'Boolean()',"
                        + "'Boolean()' )";

                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz (id, questao, resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (48, "
                        + "'What does the following code do?', "
                        + "'defines a list and initializes it', "
                        + "'defines a function, which does nothing', "
                        + "'defines a function, which passes its parameters through', "
                        + "'defines an empty class', "
                        + "'defines a function, which does nothing' )";

                stmt.executeUpdate(SQL);

        
      
                SQL = "INSERT INTO quiz (id, questao, resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (49, "
                        + "'What gets printed? Assuming python version 2.x ?', "
                        + "'type int', "
                        + "'type number', "
                        + "'type float', "
                        + "'type double', "
                        + "'type int'  )";

                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz (id, questao, resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (50, "
                        + "'What is the output of the following code ?', "
                        + "'type tuple', "
                        + "'type int', "
                        + "'type list', "
                        + "'type complex', "
                        + "'type list'  )";

                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz (id, questao, resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (51, "
                        + "'What gets printed  def f(): pass print type(f()) ?', "
                        + "'type function', "
                        + "'type tuple', "
                        + "'type NoneType', "
                        + "'type str', "
                        + "'type NoneType' )";

                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz (id, questao, resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (52, "
                        + "'What should the below code print ?', "
                        + "'type complex', "
                        + "'type unicode', "
                        + "'type int', "
                        + "'type float', "
                        + "'type complex' )";

                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz (id, questao, resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (53, "
                        + "'What is the output of the following code ?', "
                        + "'type NoneType', "
                        + "'type tuple', "
                        + "'type type', "
                        + "'type function', "
                        + "'type function' )";

                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz (id, questao, resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (54, "
                        + "'What is the output of the below program  a = [1,2,3,None,(),[],] print len(a)?', "
                        + "'syntax error', "
                        + "'6', "
                        + "'4', "
                        + "'5', "
                        + "'6' )";

                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz (id, questao, resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (55, "
                        + "'What gets printed? Assuming python version 3.x ?', "
                        + "'type int', "
                        + "'type number', "
                        + "'type float', "
                        + "'type double', "
                        + "'type float' )";

                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz (id, questao, resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (56, "
                        + "'What gets printed x = 4.5 y = 2 print x//y ?', "
                        + "'2.0', "
                        + "'3.0', "
                        + "'4.0', "
                        + "'4.5', "
                        + "'2.0' )";

                stmt.executeUpdate(SQL);

                SQL = "INSERT INTO quiz (id, questao, resp1, resp2, resp3, resp4, respCerta) "
                        + "VALUES (57, "
                        + "'What gets printed nums = set([1,1,2,3,3,3,4]) print len(nums) ?', "
                        + "'1', "
                        + "'2', "
                        + "'4', "
                        + "'7', "
                        + "'4' )";

                stmt.executeUpdate(SQL);

            }

            //tabela pontuacao
            qtd = 0;
            ResultSet result3 = stmt.executeQuery("select count(*) as qtd from sqlite_master where tbl_name = 'pontuacao'  and type = 'table' ");

            while (result3.next()) {
                qtd = result3.getInt("qtd");
            }

            if (qtd == 0) {
                step = "users Table creation";
                SQL = "CREATE TABLE IF NOT EXISTS pontuacao("
                        + "nome VARCHAR(200) NOT NULL,"
                        + "login VARCHAR(50) NOT NULL,"
                        + "data String VARCHAR(20)  NOT NULL,"
                        + "pontos int NOT NULL DEFAULT 0)";
                stmt.executeUpdate(SQL);

            }

            stmt.close();
            con.close();
        } catch (Exception ex) {
            exceptionMessage = step + ": " + ex;
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
