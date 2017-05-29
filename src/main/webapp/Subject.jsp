<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Предмети</title>
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
    <script src="js/Subject.js" type="text/javascript" charset="UTF-8"></script>
    <link rel="stylesheet" href="styles.css" />
</head>
<body>
    <header id="header">
        <div class="menuBackground">
            <div class="left">
                <a href="index.html">
                <img src="css/images/logo-cad.png" alt="Home" style="width:50px;height:37px;border:0;">
                </a>
            </div>

            <div class="center">
                <ul class="dropDownMenu">
                    <li><a href="#">Дані</a>
                        <ul>
                            <li><a href="#">Предмети</a>
                                <ul>
                                    <li><a href="#">Словник предметів</a></li>
                                    <li><a href="#">Академічні предмети</a></li>
                                    <li><a href="ControlDictionary.jsp">Словник контрольних заходів</a></li>
                                    <li><a href="#">Контрольні заходи</a></li>
                                    <li><a href="SubjectType.jsp">Типи предметів</a></li>
                                    <li><a href="Cycle.jsp">Розділи предметів</a></li>
                                </ul>
                            </li>

                            <li><a href="#">Факультет</a>
                                <ul>
                                    <li><a href="Department.jsp">Кафедри</a></li>
                                    <li><a href="AcademicGroup.jsp">Групи</a></li>
                                    <li><a href="EducationForm.jsp">Форми навчання</a></li>
                                    <li><a href="Qualification.jsp">Освітньо-кваліфікаційні рівні</a></li>    
                                    <li><a href="#">Практика</a></li>
                                    <li><a href="#">Державна атестація</a></li>
                                    <li><a href="#">Підготовка диплому</a></li>
                                    <li><a href="WorkType.jsp">Види робіт з підготовки диплому</a></li>
                                </ul>
                            </li>

                            <li><a href="#">Документи</a>
                                <ul>
                                    <li><a href="#">НП/РНП</a></li>
                                    <li><a href="#">НП/РНП - предмети</a></li>
                                    <li><a href="#">Цикли</a></li>
                                    <li><a href="#">Секції</a></li>
                                </ul>
                            </li>
                        </ul>
                    </li>

                    <li><a href="#">Генерація</a>
                        <ul>
                            <li><a href="#">НП</a></li>
                            <li><a href="#">РНП</a></li>
                            <li><a href="#">К3</a></li>
                        </ul>
                    </li>

                    <li><a href="#">Робота з шаблонами</a>
                        <ul>
                            <li><a href="#">НП</a></li>
                            <li><a href="#">РНП</a></li>
                            <li><a href="#">К3</a></li>
                        </ul>   
                    </li>

                </ul>
            </div>
        </div>
    </header>
    <div style="text-align: center;">
        <div id="SubjectTableContainer"></div>
    </div>
</body>
</html>
