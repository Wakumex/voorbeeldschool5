<%-- 
    Document   : studenttoevoegen
    Created on : 25-sep-2008, 10:45:55
    Author     : Ton van Beuningen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "school.Student"%>
<%@ page import = "database.SpecialCharacters"%>
<%
Student student;
int fout = 0;

if(request.getParameter("terug") != null) {
     response.sendRedirect(response.encodeURL(""));
     return;
}
SpecialCharacters sc = new SpecialCharacters("MySQL");
out.println(sc.specialCharacters(request.getParameter("woonplaats")));
if(request.getParameter("submit") != null) {
    student = new Student();
    student.setOvnr(request.getParameter("ovnummer"));
    student.setRoepnaam(request.getParameter("roepnaam"));
    student.setVoorletters(request.getParameter("voorletters"));
    student.setTussenvoegsels(request.getParameter("tussenvoegsels"));
    student.setAchternaam(request.getParameter("achternaam"));
    student.setAdres(request.getParameter("adres"));
    student.setPostcode(request.getParameter("postcode"));
    student.setWoonplaats(sc.specialCharacters(request.getParameter("woonplaats")));
    if(student.toevoegen() == 0) {
        response.sendRedirect(response.encodeURL(""));
    }
    else {
        fout = 1;
    }
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/transitional.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "shortcut icon" href = "images/kitten1.ico">
        <link rel="stylesheet" href="pagina.css" type="text/css" media= "screen">
        <title>Gegevens student toevoegen</title>
    </head>
    <body>
      <div id = "wrapper">
        <h2>Gegevens student toevoegen</h2>
       <% if(fout == 1) {
                out.print("<p class = \"error\"> Er is een fout opgetreden bij het toevoegen van gegevens van een student..!</p>");
                out.println("       </div>");
                out.println("   </body>");
                out.println("</html>");
                return;
               }
            %>
        <form action = "" method="post">
            <fieldset>
                <legend>Gegevens student</legend>
                <label for = "ov-nummer">OV-nummer</label>
                <input type = "text" id = "ovnummer" name = "ovnummer"  />
                <label for = "roepnaam">Roepnaam</label>
                <input type = "text" id = "roepnaam" name = "roepnaam"  />
                <label for = "voorletters">Voorletters</label>
                <input type = "text" id = "voorletters" name = "voorletters" />
                <label for = "tussenvoegsels">Tussenvoegsels</label>
                <input type = "text" id = "tussenvoegsels" name = "tussenvoegsels" />
                <label for = "achternaam">Achternaam</label>
                <input type = "text" id = "achternaam" name = "achternaam" />
                <label for = "achternaam">Adres</label>
                <input type = "text" id = "adres" name = "adres" />
                <label for = "postcode">Postcode</label>
                <input type = "text" id = "postcode" name = "postcode" />
                <label for = "woonplaats">Woonplaats</label>
                <input type = "text" id = "woonplaats" name = "woonplaats" />
                <div id = "knoppenbalk">
                    <input type = "submit" class = "knop" name = "submit" value="Verzenden" />
                    <input type = "submit" class = "knop" name = "terug" value = "Terug" />
                </div>
            </fieldset>
        </form>
      </div>
    </body>
</html>