<%-- 
    Document   : studentwijzigen
    Created on : 24-sep-2008, 14:30:45
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
    student.setRoepnaam(request.getParameter("roepnaam"));
    student.setVoorletters(request.getParameter("voorletters"));
    student.setTussenvoegsels(request.getParameter("tussenvoegsels"));
    student.setAchternaam(request.getParameter("achternaam"));
    student.setAdres(request.getParameter("adres"));
    student.setPostcode(request.getParameter("postcode"));
    student.setWoonplaats(request.getParameter("woonplaats"));
    if(student.wijzigen() == 0) {
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
        <title>Gegevens student wijzigen</title>
    </head>
    <body>
        <div id = "wrapper">
            <h2>Gegevens student wijzigen</h2>
            <% if(fout == 1) {
                out.print("<p class = \"error\"> Er is een fout opgetreden bij het wijzigen van gegevens van een student..!</p>");
                out.println("       </div>");
                out.println("   </body>");
                out.println("</html>");
                return;
               }
            %>
            <form action = "studentwijzigen.jsp" method="post">
                <input type = "hidden" id = "ovnummer" name = "ovnummer" 
                                    value="<%= student.getOvnr() %>" /> 
                <fieldset>
                    <legend>Gegevens student</legend>
                    <label for = "roepnaam">Roepnaam</label>
                    <input type = "text" id = "roepnaam" name = "roepnaam" 
                                        value="<%= student.getRoepnaam() %>" />
                    <label for = "voorletters">Voorletters</label>
                    <input type = "text" id = "voorletters" name = "voorletters" 
                                        value="<%= student.getVoorletters() %>" />
                    <label for = "tussenvoegsels">Tussenvoegsels</label>
                    <input type = "text" id = "tussenvoegsels" name = "tussenvoegsels" 
                                        value="<%= student.getTussenvoegsels() %>" />
                    <label for = "achternaam">Achternaam</label>
                    <input type = "text" id = "achternaam" name = "achternaam" 
                                        value="<%= student.getAchternaam() %>" />
                    <label for = "achternaam">Adres</label>
                    <input type = "text" id = "adres" name = "adres" 
                                        value="<%= student.getAdres() %>" />
                    <label for = "postcode">Postcode</label>
                    <input type = "text" id = "postcode" name = "postcode" 
                                        value="<%= student.getPostcode() %>" />
                    <label for = "woonplaats">Woonplaats</label>
                    <input type = "text" id = "woonplaats" name = "woonplaats" 
                                        value="<%= student.getWoonplaats() %>" />
                    <div id = "knoppenbalk">
                        <input type = "submit" class = "knop" name = "submit" value="Verzenden" />
                        <input type = "submit" class = "knop" name = "terug" value = "Terug" />
                </div>
            </fieldset>
        </form>
    </div>
    </body>
</html>