<?php
 
class funciones_BD {
 
    private $db;
 
    // constructor

    function __construct() {
        require_once 'connectbd.php';
        // connecting to database

        $this->db = new DB_Connect();
        $this->db->connect();

    }
 
    // destructor
    function __destruct() {
 
    }
 
    /**
     * agregar nuevo usuario
     */
    public function adduser($username, $password) {

    $result = mysql_query("INSERT INTO usuario(username,passw) VALUES('$username', '$password')");
        // check for successful store

        if ($result) {

            return true;

        } else {

            return false;
        }

    }
 

/**
     * agregar nuevo pedido
     */
    public function addpedido($cedula, $nombre,$apellido, $hora,$cine, $horario,$sala, $asiento,$detalle,$total) {

    $result = mysql_query("INSERT INTO pedido(CEDULA,NOMBRE,APELLIDO,HORA,CINE,HORARIO_FUNCION,SALA_FUNCION,ASIENTO_FUNCION,DETALLE,TOTAL) VALUES('$cedula', '$nombre','$apellido','$hora','$cine','$horario','$sala','$asiento','$detalle','$total')");
        // check for successful store

        if ($result) {

            return true;

        } else {

            return false;
        }

    }


/**
     * agregar detalle pedido
     */
    public function adddetalle($idped, $descrip,$tot) {

    $result = mysql_query("INSERT INTO detalle_pedido(ID_PEDIDO,DESCRIPCION,TOTAL) VALUES('$idped', '$descrip','$tot')");
        // check for successful store

        if ($result) {

            return true;

        } else {

            return false;
        }

    }

public function consulta_id_pedido($cedula, $nombre,$apellido, $hora,$cine, $horario,$sala, $asiento) {

        $result = mysql_query("SELECT ID_PEDIDO from pedido WHERE CEDULA='$cedula' AND NOMBRE='$nombre' AND APELLIDO='$apellido'AND HORA='$hora'AND CINE='$cine'AND HORARIO_FUNCION='$horario'AND SALA_FUNCION='$sala'AND ASIENTO_FUNCION='$asiento'");

        $num_rows = mysql_num_rows($result); //numero de filas retornadas

        
           echo mysql_fetch_array($result);
           }



 
     /**
     * Verificar si el usuario ya existe por el username
     */

    public function isuserexist($username) {

        $result = mysql_query("SELECT username from usuarios WHERE username = '$username'");

        $num_rows = mysql_num_rows($result); //numero de filas retornadas

        if ($num_rows > 0) {

            // el usuario existe 

            return true;
        } else {
            // no existe
            return false;
        }
    }
 

public function consulta_usuario($username,$pass) {

        $result = mysql_query("SELECT NOMBRE,APELLIDO,SALDO from usuario WHERE USER='$username' AND CEDULA='$pass' ");

        $num_rows = mysql_num_rows($result); //numero de filas retornadas

        
           echo mysql_fetch_array($result);
           }

public function actualizarsaldo($cedula,$saldo) {

        $result = mysql_query("UPDATE usuario SET SALDO='$saldo' WHERE CEDULA='$cedula' ");

        $num_rows = mysql_num_rows($result); //numero de filas retornadas

        
           echo mysql_fetch_array($result);
         }



   
	public function login($user,$passw){

	$result=mysql_query("SELECT COUNT(*) FROM usuario WHERE USER='$user' AND CEDULA='$passw' "); 
	$count = mysql_fetch_row($result);

	/*como el usuario debe ser unico cuenta el numero de ocurrencias con esos datos*/


		if ($count[0]==0){

		return true;

		}else{

		return false;

		}
	}
  
}
 
?>
