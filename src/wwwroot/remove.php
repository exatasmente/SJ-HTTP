<html>
<title>TESTE</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>

<meta name="viewport" content="width=device-width, initial-scale=0.8">
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="style.css">

<head></head>
<body>

<form class="form-container" action="rem.php" method="post" >
          
<div class="form-title"><h2>SysGEt</h2></div>
<div class="form-title"><h2>REMOVER VEICULO</h2></div>

<div class="form-title">Placa</div>
<select class="form-field" name="tipo"  id="tipo" required >
		<?php
			
						include ("/php/conecao.php");
						$query ="SELECT id,placa, tipo FROM veiculo";
						$check = mysql_query($query, $dbc);
						while ($dados = mysql_fetch_array($check)) 
						{

							echo("<option value='".$dados['id']."'>".$dados['placa']."</option>");
						}
		?>
	</select>

<div class="form-title">
<a class="button"  href="index.html">Voltar</a>

<input class="submit-button" type="submit" value="Remover" />

</div>
</form>

</body>

</html>
