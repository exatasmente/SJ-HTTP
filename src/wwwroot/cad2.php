<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pt-br" lang="pt-br">
<html>
 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">


<meta name="viewport" content="width=device-width, initial-scale=0.8">
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="style.css">

<head> </head>
<body> 
<div class="form-container">
<div class="form-title">
<?php
	include ("php/conecao.php");
	$placa= $_POST['placa'];
	$id = $_POST['id'];
	$tipo = $_POST['tipo'];
	switch($tipo)
	{
		case 1:
			$tipoN = "CARRO";
			break;
		case 2:
			$tipoN = "MOTO";
			break;
		case 3:
			$tipoN  = "ADM";
			break;
		default:
			$tipo= "ERROR";
			break;
	}	
	$c=0;
	$placa = strtoupper($placa);
	$query = "SELECT id, placa, tipo FROM veiculo";
	$check = mysql_query($query, $dbc)
	or die ("Falha no query 1". mysql_error());

		while($consulta= mysql_fetch_array($check))
		{
			if($placa == $consulta[placa])
			{
				$c++;
				print "<h2>Veiculo Já Cadastrado</h2>";
				break;
				
			}
			if($id == $consulta[id])
			{
				$c++;
				print "<h2>ID Já Cadastrado</h2>";
				break;
			}
			
		}
	if($c==0)
	{
		$query = "INSERT veiculo (id, placa, tipo) VALUES ('$id','$placa','$tipo')";
		$result = mysql_query($query, $dbc)
			or die ("Falha no query In". mysql_error());
		echo '<h2>Cadastro Realizado.</h2>';
		echo "<h1>ID :$id </h1>";
	  	echo "<h1>PLACA: $placa </h1>";
		echo "<h1>TIPO : $tipoN </h1>";
	
	}



?>
</div>

<a class="button"  href="oie.php">Voltar</a>
</body> 
</html>
