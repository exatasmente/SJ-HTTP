<?php
 
	include ("conecao.php");
	function sqlcheck($VAL[])	
	{

		$query = "SELECT id, placa, tipo FROM veiculo";
		$check = mysql_query($query, $dbc)
			or die ("Falha no query 1". mysql_error());
	
			while($consulta= mysql_fetch_array($check))
			{
				if($VAL[placa] == $consulta[placa])
				{
					$c++;
					print "Veiculo Já Cadastrado";
					break;
					
				}
				if($VAL[id] == $consulta[id])
				{
					$c++;
					print "ID Já Cadastrado";
					break;
				}
				
			}

		return $c;
	}

	function sqlload($VAL[])
	{
		$query = "INSERT veiculo (id, placa, tipo) VALUES ('$VAL['id']','$VAL['placa']','$VAL['tipo']')";
		$result = mysql_query($query, $dbc)
			or die ("Falha no query In". mysql_error());
	}


	function printval($VAL[],$tipoN)
	{
		echo '<h2>Cadastro Realizado.</h2>';
		echo "<h1>ID :$VAL['id'] </h1>";
	  	echo "<h1>PLACA: $VAL['placa'] </h1>";
		echo "<h1>TIPO : $tipoN </h1>";
	}


	function formatar($VAL[])
	{
		switch($VAL['tipo'])
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
				$VAL['tipo']= null;
			break;
		}	

		$VAL[placa] = strtoupper($VAL[placa]);

		return $tipoN;
	}
?>
