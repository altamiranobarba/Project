
<?php

/*LOGIN*/

$ced = $_POST['cedula'];
$sal = $_POST['saldo'];


require_once 'funciones_bd.php';
$db = new funciones_BD();

	if($db->actualizarsaldo($ced,$sal)){

	$resultado[]=array("logstatus"=>"0");
	}else{
	$resultado[]=array("logstatus"=>"1");
	}

echo json_encode($resultado);

 
 


?>
