<?php
	include ("/php/conecao.php");
	$query ="SELECT id,tipoveiculo FROM tipoveiculo";
	$check = mysql_query($query, $dbc);
	while ($dados = mysql_fetch_array($check)) 
	{
		echo("<option value='".$dados['id']."'>".$dados['tipoveiculo']."</option>");
	}
?>
