<html>
<head></head>
<body>

<?php
	$host='localhost';
	$user='root';
	$db='1184697';
	$key='133742';

	$dbc = mysql_connect("$host","$user","$key")
		or die ('Erro');

	mysql_select_db("$db",$dbc)
		or die ('ERRO');

?>
</body>
</html>
