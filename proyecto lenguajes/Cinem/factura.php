
<?php

/*LOGIN*/

$usuario = $_POST['usuario'];
$passw = $_POST['password'];
$facturaid = $_POST['id'];



require_once 'funciones_bd.php';
$db = new funciones_BD();

	if($db->login($usuario,$passw)){

	$resultado[]=array("logstatus"=>"0");
	}else{
	$resultado[]=array("logstatus"=>"1");
	}


$sqldata = mysql_query("SELECT HORARIO_FUNCION,SALA_FUNCION,ASIENTO_FUNCION FROM factura WHERE ID_FACTURA='$facturaid'");

$rows = array();
while($r = mysql_fetch_assoc($sqldata)) {
  $rows[] = $r;
}

echo json_encode($rows);

 
 


?>

