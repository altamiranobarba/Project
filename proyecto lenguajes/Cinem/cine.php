
<?php

/*LOGIN*/

$usuario = $_POST['usuario'];
$passw = $_POST['password'];
$cinenom = $_POST['cine'];

require_once 'funciones_bd.php';
$db = new funciones_BD();

	if($db->login($usuario,$passw)){

	$resultado[]=array("logstatus"=>"0");
	}else{
	$resultado[]=array("logstatus"=>"1");
	}


$sqldata = mysql_query("SELECT NOMBRE,X_COORD,Y_COORD FROM cine WHERE NOMBRE='$cinenom'");

$rows = array();
while($r = mysql_fetch_assoc($sqldata)) {
  $rows[] = $r;
}

echo json_encode($rows);

 
 


?>


