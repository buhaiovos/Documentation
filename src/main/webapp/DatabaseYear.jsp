<%@page import="java.io.IOException"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.util.TreeSet"%>
<%@page import="edu.cad.utils.databaseutils.DatabaseSwitcher"%>
<%@page import="org.hibernate.cfg.Configuration"%>
<%@page import="edu.cad.utils.databaseutils.DatabaseYears"%>
<%@page import="edu.cad.utils.Utils"%>
<%@page import="java.util.Set"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Вибір року БД</title>
        <!-- jTable Metro styles. -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
        <link href="css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
        <!-- jTable script file. -->
        <script src="js/jquery-1.8.2.js" type="text/javascript"></script>
        <script src="js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
        <script src="js/jquery.jtable.min.js" type="text/javascript"></script>
        <script src="js/jquery.jtable.editinline.js" type="text/javascript"></script>
        <!-- User Defined Jtable js file -->
        <link rel="stylesheet" href="styles.css" />
    </head>
    <body>
        <!-- MENU -->
        <header id="header"></header>
        <script type="text/javascript">
        $(function() {
            $( "#header" ).load( "menu.html" );
        });
        </script>
        <!-- MENU END -->
        <%
            String filePathOnServer = getServletContext().getRealPath("WEB-INF/classes/DatabaseYears.txt");
            DatabaseYears.setYearsFilePath(filePathOnServer);
            Set<Integer> availableYears = DatabaseYears.getAllYears();
        %>
        <div id="existingYearsForm">
            Оберіть існуюючий:
            <form action="DatabaseYear.jsp">
                <select name="year">
                    <%
                    for (Integer year : availableYears) {
                    %>
                        <option value="<%=year.toString()%>"> <%=year.toString()%> </option>
                    <%
                    }
                    %>
                </select>
                <input type="submit" value="Обрати">
            </form>
        </div>
        <div id='createNewField'>
            Або створити новий рік
            <form action="DatabaseYear.jsp">
                <input type="text" name="year">
                <input type="submit" value="Створити">
            </form>
        </div>
                
        <%String yearSelected = request.getParameter("year");
        if (yearSelected == null) {
            yearSelected = "0";
        }
        %>
        <p>Ви обрали: <b><%=yearSelected%></b></p>
        <%
            if (Utils.isParseable(yearSelected)) {
                int year = Integer.parseInt(yearSelected);
                DatabaseSwitcher.switchDatabase(year);
            }
        %>
        <%int currentYear = DatabaseSwitcher.getDatabaseYear();%>
        <p>БД: <b><%=currentYear%></b></p>
        
    </body>
</html>
