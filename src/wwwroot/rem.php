<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pt-br" lang="pt-br">
<html>

 <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="style.css">

<head> </head>
<body> 
<div class="form-container">
<div class="form-title">
<?php
	include ("/php/conecao.php");
	$id = $_POST['tipo'];
	$c=0;
	$query = "SELECT id, placa, tipo FROM veiculo";
	$check = mysql_query($query, $dbc)
	or die ("Falha no query 1". mysql_error());
	while($consulta= mysql_fetch_array($check))
	{
		if($id == $consulta['id'])
		{
			$query = "DELETE FROM veiculo WHERE veiculo . id ='$id' ";
			$result = mysql_query($query, $dbc)
				or die ("Falha no query In ". mysql_error());
			echo "<h2>VEÍCULO REMOVIDO</h2>";
			echo "<a>ID :$id </a></br>";
		
			break;
		}
		else
		{
			$c++;
		}
	}
	if(c!=0)
	{
		print "<h2>Veiculo Não existe</h2>";
		echo "<a>ID :$id </a></br>";
			
	}



?>
</div>

<a class="button"  href="remove.php">Voltar</a>


</body> 
</html>
