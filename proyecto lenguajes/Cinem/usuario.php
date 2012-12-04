
<?php

/*LOGIN*/

$usuario = $_POST['usuario'];
$passw = $_POST['password'];


require_once 'funciones_bd.php';
$db = new funciones_BD();

	if($db->login($usuario,$passw)){

	$resultado[]=array("logstatus"=>"0");
	}else{
	$resultado[]=array("logstatus"=>"1");
	}


$sqldata = mysql_query("SELECT USER,CEDULA,NOMBRE,APELLIDO,SALDO FROM usuario WHERE USER='$usuario' AND CEDULA='$passw' ");

$rows = array();
while($r = mysql_fetch_assoc($sqldata)) {
  $rows[] = $r;
}

echo json_encode($rows);

 
 


?>

