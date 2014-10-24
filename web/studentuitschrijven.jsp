<%-- 
    Document   : studentuitschrijven
    Created on : 29-sep-2008, 17:45:54
    Author     : Ton van Beuningen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "school.Student"%>
<%
Student student;
int fout = 0;

if(request.getParameter("terug") != null) {
     response.sendRedirect(response.encodeURL("index.jsp"));
     return;
}
if(request.getParameter("submit") == null) {
    student = new Student(request.getParameter("id"));
}
else {
   student = new Student(request.getParameter("ovnummer")); 
   if(student.uitschrijven(request.getParameter("uitschrijfdatum")) == 0) {
        response.sendRedirect(response.encodeURL("index.jsp"));
   }
   else {
        fout = 1;
    }
}

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
            <h2>Student uitschrijven</h2>
            <% if(fout == 1) {
                out.println("<p class = \"error\"> Er is een fout opgetreden bij het uitschrijven van een student..!</p>");
                out.println("       </div>");
                out.println("   </body>");
                out.println("</html>");
                return;
               }
            %>
            <form action = "studentuitschrijven.jsp" method="post">
                <fieldset>
                    <input type = "hidden" id = "ovnummer" name = "ovnummer" 
                                    value="<%= student.getOvnr() %>" /> 
                    <legend>Student <%= student.getNaam() %></legend>
                    <label for = "uitschrijfdatum">Uitschrijdatum</label>
                    <input type = "text" id = "uitschrijfdatum" name = "uitschrijfdatum" 
                                        value="<%= student.getUitgeschreven() %>" />
                    <div id = "knoppenbalk">
                        <input type = "submit" class = "knop" name = "submit" value="Verzenden" />
                        <input type = "submit" class = "knop" name = "terug" value = "Terug" />
                    </div>
                </fieldset>
            </form>
        </div>
    </body>
</html>
