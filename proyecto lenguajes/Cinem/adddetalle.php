<?php

$idped = $_POST['id'];
$descrip = $_POST['descripcion'];
$total = $_POST['total'];

require_once 'funciones_bd.php';
$db = new funciones_BD();
if($db->addpedido($ced, $nom,$ape, $hor,$cin, $horari,$sal, $asien) )
{	
	$resultado[]=array("logstatus"=>"0");
	
 }else{
$resultado[]=array("logstatus"=>"1");
	}		

	
echo json_encode($resultado);


?>



