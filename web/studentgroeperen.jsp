<%-- 
    Document   : studentgroeperen
    Created on : 30-sep-2008, 15:54:56
    Author     : Ton van Beuningen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "school.Student"%>
<%@ page import = "school.GroepsOverzicht" %>
<%
Student student;
int fout = 0;
GroepsOverzicht groepen;
String groep;
int aantalGroepen, teller;

groepen = new GroepsOverzicht();
if(request.getParameter("terug") != null) {
     response.sendRedirect(response.encodeURL("index.jsp"));
     return;
}
if(request.getParameter("submit") == null) {
    student = new Student(request.getParameter("id"));
}
else {
    student = new Student(request.getParameter("ovnummer")); 
    if(student.groeperen(request.getParameter("groep")) == 0) {
        response.sendRedirect(response.encodeURL("index.jsp"));
   }
   else {
        fout = 1;
    }
}
aantalGroepen = groepen.getNumberOfGroeps();
groep = student.getGroep();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "shortcut icon" href = "images/kitten1.ico">
        <link rel="stylesheet" href="pagina.css" type="text/css" media= "screen">
        <title>Overzicht studenten</title>    
    </head>
    <body>
        <div id = "wrapper">
            <h2>Student groeperen</h2>
            <% if(fout == 1) {
                out.println("<p class = \"error\"> Er is een fout opgetreden bij het groeperen van een student..!</p>");
                out.println("<a href = \"index.jsp\">Terug naar overzicht</a>");
                out.println("       </div>");
                out.println("   </body>");
                out.println("</html>");
                return;
               }
            %>
            <form action = "studentgroeperen.jsp" method="post">
                <fieldset>
                    <input type = "hidden" id = "ovnummer" name = "ovnummer" 
                                    value="<%= student.getOvnr() %>" /> 
                    <legend>Student <%= student.getNaam() %></legend>
                    <label for = "groep">Groep</label>
                    <select name = "groep">
                    <option>-- geen groep --</option>
                    <%
                        for(teller = 0; teller < aantalGroepen; teller ++) {
                            if(groep.equals(groepen.getGroep(teller)) == false) {
                                out.println("<option>"+ groepen.getGroep(teller) +"</option>");
                            }
                            else {
                                out.println("<option selected>"+ groepen.getGroep(teller) +"</option>");
                            }
                        }
                    %>
                </select>
                    <div id = "knoppenbalk">
                        <input type = "submit" class = "knop" name = "submit" value="Verzenden" />
                        <input type = "submit" class = "knop" name = "terug" value = "Terug" />
                    </div>
                </fieldset>
            </form>
        </div>
    </body>
</html>
