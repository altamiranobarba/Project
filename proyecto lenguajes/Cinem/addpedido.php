<?php

$ced = $_POST['cedula'];
$nom = $_POST['nombre'];
$ape = $_POST['apellido'];
$hor = $_POST['hora'];
$cin = $_POST['cine'];
$horari = $_POST['horario'];
$sal = $_POST['sala'];
$asien = $_POST['asiento'];
$det = $_POST['detalle'];
$tot = $_POST['total'];


require_once 'funciones_bd.php';
$db = new funciones_BD();
if($db->addpedido($ced, $nom,$ape, $hor,$cin, $horari,$sal, $asien,$det,$tot) )
{	
	$resultado[]=array("logstatus"=>"0");
	
 }else{
$resultado[]=array("logstatus"=>"1");
	}		

	
echo json_encode($resultado);


?>



