<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		$name = $_POST['name'];
		$kode = $_POST['kode'];
		$phone = $_POST['phone'];
		$password = $_POST['password'];
		$res = $_POST['res'];
		require_once('dbConnect.php');
		$sql = "INSERT INTO `coachtbl`
		(name,kodemelli,phone,password,responsibility) 
		VALUES 
		(N'$name',N'$kode',N'$phone',N'$password',N'$res')";
		$sql2 = "SELECT * FROM coachtbl WHERE kodemelli =
		'$kode'";
		$r = @mysqli_query($con,$sql2);
        $num_rows = mysqli_num_rows($r);
        if($num_rows>0){
			echo ("no");}
		else if(mysqli_query($con,$sql)){
			echo "ok";
		}
		else{
			echo mysqli_error($con);
		}
	}
	else{
		echo "error";
	}
?>