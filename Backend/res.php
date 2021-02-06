<?php  

	if($_SERVER['REQUEST_METHOD']=='POST'){
		$kodemelli = $_POST['kodemelli'];
		require_once('dbConnect.php');
		$sql = "SELECT `responsibility` FROM coachtbl WHERE kodemelli =
		'$kodemelli'";
		$r =@mysqli_query($con,$sql);
		$row = mysqli_fetch_array($r);
		$data = $row[0];
       	if(@mysqli_query($con,$sql)){
			echo $data;
		}
		else{
			echo (mysqli_error($con));
		}
        @mysqli_close($con);
	}		
 ?>