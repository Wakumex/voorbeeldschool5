<%-- 
    Document   : index2
    Created on : 24-sep-2008, 13:55:58
    Author     : ApplOntwikkelaar
--%>


<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "school.OverzichtStudenten" %>
<%@ page import = "school.Student"%>
<%@ page import = "school.GroepsOverzicht"%>
<%
OverzichtStudenten overzicht;
Student student;
GroepsOverzicht groepen;
int aantalStudenten, aantalGroepen, teller, sorteer = 0;
String gezocht = "", groep = "";
boolean requested = false;

if(request.getParameter("toevoegen") != null) {
    response.sendRedirect(response.encodeURL("studenttoevoegen.jsp"));
    return;
}

overzicht = new OverzichtStudenten();
if(request.getParameter("sorteren") != null) {
    sorteer = 1;
}
if(request.getParameter("zoekknop") != null) {
    gezocht = request.getParameter("zoeken");
    overzicht.getStudentsSearched(gezocht);
    requested = true;
}
if(request.getParameter("groepsknop") != null) {
    groep = request.getParameter("groep");
    if(groep.equals("-- geen groep --") == false) {
         overzicht.getStudentsPerGroup(groep);
         requested = true;
    }
}

if(requested == false) {
    overzicht.getStudentsSorted(sorteer);
 }

aantalStudenten = overzicht.getNumberOfStudents();
groepen = new GroepsOverzicht();
aantalGroepen = groepen.getNumberOfGroeps();

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
        <h2>Overzicht studenten</h2>
        <form action = "" method = "post" id = "knoppenbalk">
            <div class="separation">
            <input type = "submit" name = "toevoegen" id = "toevoegen"
                            value="Student toevoegen" />
            </div>
            <div class="separation">
            <input type = "submit" name = "sorteren" id = "sorteren"
                            value="Sorteren" />
            </div>

            <div class="separation">
                <legend>Zoeken</legend>
                <input type = "text" name = "zoeken" id = "zoeken" value = "<%= gezocht %>" />
                <input type = "submit" name = "zoekknop" id = "zoekknop"
                                value="Zoeken" class = "separate"/>
            </div>

            <div class="separation">
                <legend>Kies een groep</legend>
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
                <input type = "submit" name = "groepsknop" id = "groepsknop"
                                value="Groep tonen" />
            </div>
        </form>

         <% if(aantalStudenten == 0) {
            out.println("<p class = \"error\">Er zijn geen studenten gevonden..!</p>");
            out.println("       </div>");
            out.println("   </body>");
            out.println("</html>");
            return;
        }
        %>
        <table>
            <tr>
                <th>OV-nummer</th>
                <th>Naam</th>
                <th>Adres</th>
                <th>Postcode</th>
                <th>Woonplaats</th>
                <th>Uitschrijfdatum</th>
            </tr>
            <%for(teller = 0; teller < aantalStudenten; teller ++) {
                student = overzicht.getStudent(teller);%>
                <tr>
                    <td><%= student.getOvnr() %></td>
                    <td><%= student.getNaam() %></td>
                    <td><%= student.getAdres() %></td>
                    <td><%= student.getPostcode() %></td>
                    <td><%= student.getWoonplaats() %></td>
                    <td><%= student.getUitgeschreven() %></td>
                    <td><a href = "studentwijzigen.jsp?id=<%= student.getOvnr() %>">Wijzigen</a></td>
                    <td><a href = "studentgroeperen.jsp?id=<%= student.getOvnr() %>">Groeperen</a></td>
                    <td><a href = "studentuitschrijven.jsp?id=<%= student.getOvnr() %>">Uitschrijven</a></td>
                </tr>
             <% } %>
         </table>
     </div>
    </body>
</html>
