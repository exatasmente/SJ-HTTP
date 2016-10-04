<html>
<title>TESTE</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

 
<meta name="viewport" content="width=device-width, initial-scale=0.8">
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="style.css">
 
<head></head>
<body>
 
<div class="form-container" >
 
<div class="form-title"><h2>SysGEt</h2></div>
<div class="form-title"><h2>STATUS</h2></div>
	<?php
		$i=0;
		$iM=0;
		$iC=0;
		$iA=0;
		include ("php/conecao.php");
		$query ="SELECT * FROM veiculo";
		$check = mysql_query($query, $dbc);
		while ($dados = mysql_fetch_array($check)) 
		{
			$i++;
			switch($dados['tipo'])
			{
				case 1:
					$iC++;
					break;
				case 2:
					$iM++;
					break;
				case 3:
					$iA++;
					break;
			}
 
		}
 
		echo "<div class='form-title'>Existem $i Veículos cadastrados</div>";
		echo "<div class='form-title'>$iC CARROS</div>";
		echo "<div class='form-title'>$iM MOTOS</div>";
		echo "<div class='form-title'>$iA ADM</div>";
	?>
 
<br/>
<br/>
<div class="form-title">
<a class="button"  href="index.html">Voltar</a>
 
 
 
</div>
</form>
 
</body>
 
</html>
