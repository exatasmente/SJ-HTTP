<?php
	$host='localhost';
	$user='1184697';
	$db='1184697';
	$key='31071996'

	$dbc = mysql_connect("$host","$user","$key")
		or die ('Erro');

	mysql_select_db("$db",$dbc)
		or die ('ERRO');

?>

