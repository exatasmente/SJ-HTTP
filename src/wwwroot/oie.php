<html>
<title>TESTE</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>

<meta name="viewport" content="width=device-width, initial-scale=0.8">
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="style.css">

<head>
<script>
function formatar(mascara, documento){
  var i = documento.value.length;
  var saida = mascara.substring(0,1);
  var texto = mascara.substring(i)
  
  if (texto.substring(0,1) != saida){
            documento.value += texto.substring(0,1);
  }
  
}
</script>
</head>
<body>

<form class="form-container" action="cad2.php" method="post" >
          
	<div class="form-title">
		<h2>
			SysGEt
		</h2>
	</div>
	<div class="form-title">
		<h2>
			CADASTAR VEICULO
		</h2>
	</div>
	<div class="form-title">
		<label for= "placa"> 
			Placa
		</label>
	</div>
	<input class="form-field" type="text" name="placa" autocomplete="off" maxlength="8" placeholder="NNN-0000" pattern=[a-z-A-Z]{3}-[0-9]{4}$ required OnKeyPress="formatar('###-####', this)" title="Digie a Placa do Veículo no formato 'NNN-0000' " /><span class="req">*</span><br/>
	
	<div class="form-title">
		<lable for="id">
			ID
		</lable>
	</div>	
	<input class="form-field" title="Digie um ID com no máximo 3 Números" type="text" name="id" placeholder="000" maxlength="3" pattern="[0-9]{3}$" autocomplete="off" required/><span class ="req">*</span><br />


		
	<div class="form-title">
		<lable for="tipo">
			Tipo
		</lable>
		<select class="form-field" name="tipo" id="tipo" required >
			<?php
				include("/cad/tipo.php");
			
			?>
		</select>
	</div>

	<div class="form-title">
		<a class="button"  href="index.html">Voltar</a>
	
		<input class="submit-button" type="submit" value="Cadastrar" />
	
	</div>

</form>

</body>

</html>
