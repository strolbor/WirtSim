<?php
    function infogetter($sql){
    //Verbindungsdaten zum Server//
        $server='server.example';
        $user = 'db_user';
        $pw = 'db_password';
        $dbname = 'db_name';
        
        //Verbindung zum MySQL Server wird hergestellt//
        $conn = new mysqli($server,$user,$pw, $dbname);
        
        //Verbindung wird auf Erfolgreichsein überprüft//
        if($conn->connect_error){
            die("Connection failed: ".$conn->connect_error);
        }  //end of if//
        $result = $conn->query($sql);
        $conn->close();
        return $result;
    }
    
function getHighscore(){
    $sqlIM = "SELECT * from Score order by Score desc";
    $resultIM = infogetter($sqlIM);
    echo "<h1>Highscore von dem Spiel PaperGame</h1><table><tr><th>Score</th><th>Name</th><th>Datum</th></tr>";
    if($resultIM->num_rows > 0){
       while($row = $resultIM->fetch_assoc()){
           echo "<tr><td>";
           echo $row['Score']."</td><td>";
           echo $row['Name']."</td><td>";
           echo $row['Date']."</td></tr>";
       }//End of While//
    }//End of if//  
    echo "</table>";
}//end of function

?>

<!DOCTYPE html>
<html dir="ltr" lang="de-DE"><head><!-- Created by Artisteer v4.1.0.59861 -->
    <meta charset="utf-8">

       <title>PaperGame</title>

    
    <meta name="viewport" content="initial-scale = 1.0, maximum-scale = 1.0, user-scalable = no, width = device-width">

    <!--[if lt IE 9]><script src="https://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
    <link rel="stylesheet" href="https://ursb.de/style.css" media="screen">
    <!--[if lte IE 7]><link rel="https://ursb.de/stylesheet" href="style.ie7.css" media="screen" /><![endif]-->
    <link rel="stylesheet" href="https://ursb.de/style.responsive.css" media="all">


    <script src="https://ursb.de/jquery.js"></script>
    <script src="https://ursb.de/script.js"></script>
    <script src="https://ursb.de/script.responsive.js"></script>
<meta name="description" content="Offizelle Webseite des PaperGame">
<meta name="keywords" content="PaperGame">



<style>.blu-content .blu-postcontent-0 .layout-item-0 { border-top-width:0px;border-top-style:solid;border-top-color:#E1DF89;margin-top: 10px;margin-bottom: 10px;  }
.blu-content .blu-postcontent-0 .layout-item-1 { padding-right: 20px;padding-left: 20px;  }
.blu-content .blu-postcontent-0 .layout-item-2 { border-right-style:solid;border-right-width:0px;border-right-color:#E1DF89; padding-right: 20px;padding-left: 20px;  }
.ie7 .blu-post .blu-layout-cell {border:none !important; padding:0 !important; }
.ie6 .blu-post .blu-layout-cell {border:none !important; padding:0 !important; }

</style></head>
<body>
<div id="blu-main">
    <div class="blu-sheet clearfix">
<header class="blu-header">


    <div class="blu-shapes">

            </div>

     
   <h1 class='blu-headline' data-left='5.74%'><a href='#'>PaperGame</a></h1><h2 class='blu-slogan' data-left='4.77%'>Score und Hilfe</h2>  






<nav class="blu-nav">
    <ul class="blu-hmenu"><li><a href="?n">Home</a></li><li><a href="?id=Score">Score</a></li><li><a href="?id=Hilfe">Hilfe</a></li><li><a href="/PaperGame/doc/index.html">JavaDoc</a></li></ul> 
    </nav>

                    
</header>
<div class="blu-layout-wrapper">
                <div class="blu-content-layout">
                    <div class="blu-content-layout-row">
                        <div class="blu-layout-cell blu-content"><article class="blu-post blu-article">
                                
                                <div class="blu-postcontent blu-postcontent-0 clearfix"><div class="blu-content-layout">
    <div class="blu-content-layout-row">
    <div class="blu-layout-cell layout-item-1" style="width: 100%" >
                <!--<h3>Inhalt</h3> HIER EINFÜGEN
            <h3>Inhalt oben</h3>-->
<!-- <h1>Hier Inhalte einfügen</h1> -->
<?php 

if($_GET['id'] == "Score"){
    getHighscore();
}else if($_GET['id'] == "Hilfe"){
    echo "<h1 id='hilfsseite-vom-papergame'>Hilfsseite vom PaperGame</h1>
<h2 id='ablauf'>Ablauf</h2>
<p>Willkommen du Jungunternehmern in deiner Selbständigkeit. Du hast ein Start-Up gegründet und willst dein {Produkt} jetzt gewinnbringend verkaufen.
Allerdings bemerkst du schnell, dass diese Preise zum Einkauf, sowie Verkauf, täglich schwanken. 
Du hast zum Glück 2 {Rohstoffe} und 20€ nach der Unternehmensgründung noch übrig. Und willst deine {Produkte} teuer verkaufen und deine {rohstoffe} günstig einkaufen.
Allerdings ist deine Arbeit nicht so leicht, dass man IdR. nur 4 {Rohstoffe} verarbeiten kannst. Aber dafür kannst du um Mitarbeiter werben. Allerdings beachte, diese können krank, sich Urlaub nehmen und kündigen. Und unterliegen, wie du auch, an Stimmungschwankungen, sodass sie mal mehr oder weniger Rohstoffe verarbeiten können.</p>
<h2 id='phasen'>Phasen</h2>
<h3 id='einkaufsphase'>Einkaufsphase</h3>
<p>In dieser Phase startest du. Du kannst mithilfe des Schiebereglers deine Anzahl der zukaufende Rohstoffe bestimmen und kaufen.
Aber beachte, du kannst am Anfang nur 2 {Rohstoffe} kaufen, kannst aber später mit Hilfe deiner Kreditwürdigkeit deine Lieferanten beeindrucken, dass du mehr {Rohstoffe} kaufen kannst.</p>
<ul>
<li>(Schlauberger: Kapital / 150 bei ganzzahliger Division) <h3 id='verkaufsphase'>Verkaufsphase</h3>
In der dieser Phase kannst du deine {Produkte} mit hilfe des Schiebereglers verkaufen. Der Verkaufspreis wird dir angezeigt.</li>
</ul>
<h3 id='runde'>Runde</h3>
<p>Eine Runde besteht aus einer Verkaufsphase &amp; Einkaufsphase. In einer Runde kannst du Produkte produzieren. Dazu benötigst du eventuell noch zusätzliche Arbeiter. Pro Runde kann nur eine bestimmte Anzahl von {Rohstoffen} verarbeitet werden, welche dir im Interface angezeigt werden.
Also aufpassen und jede Runde Produkte produzieren. Allerdings kostet das Produzieren Geld, welches dir direkt abgezogen wird.
Pass auf! Die Arbeiter kostet dir auch Geld. Weitere Infos in den Arbeiteroptionen des Spiels.</p>
<h2 id='ende-des-spiels'>Ende des Spiels</h2>
<p>Das Spiel ist am Ende, wenn du Schulden hast und wenn du die nicht begleichen kannst.</p>
";
    
}else{
    echo "<h1>Willkommen auf der Webseite von PapagerGame</h1><p>Im Menu oben koennen Sie zum Scoreliste oder zur Hilfe</p>";
}
?>
 
		
    </div>
    </div>
</div>
<div class="blu-content-layout-br layout-item-0">
</div><div class="blu-content-layout">
    <div class="blu-content-layout-row">
    <div class="blu-layout-cell layout-item-2" style="width: 67%" >
       <!-- Links 
       <h3>Inhalt links</h3>-->
    </div><div class="blu-layout-cell layout-item-1" style="width: 33%" >
        <!-- Rechts
        <h3>Inhalt rechts</h3>-->
    </div>
    </div>
</div>
</div>


</article></div>
                        <div class="blu-layout-cell blu-sidebar1"><div class="blu-block clearfix">
        <div class="blu-blockcontent"><p><br></p></div>
</div><div class="blu-block clearfix">
        <div class="blu-blockcontent"><p style="text-align: center;"><br></p><p style="text-align:center;"><a href="#"><img width="17" height="20" alt="" src="https://ursb.de/images/left.png"></a><a href="#"><img width="17" height="20" alt="" src="https://ursb.de/images/right.png"></a></p></div>
</div></div>
                    </div>
                </div>
            </div><footer class="blu-footer">
<div class="blu-content-layout layout-item-0">
    <div class="blu-content-layout-row">
    <div class="blu-layout-cell" style="width: 100%">
        <p><a href='https://ursb.de/?id=imprint'>Impressum</a> <a href='https://ursb.de/?id=Datenschutz'>Datenschutz</a></p>
    </div>
    </div>
</div>

</footer>

    </div>
    <p class="blu-page-footer">
        <span id="blu-footnote-links">Designed by <a href="www.1blu.de" target="_blank">1blu AG</a>.</span>
    </p>
</div>


</body></html>
