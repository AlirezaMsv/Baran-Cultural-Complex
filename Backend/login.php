<?php  

	if($_SERVER['REQUEST_METHOD']=='POST'){
		$username = $_POST['username'];
		$password = $_POST['password'];
		require_once('dbConnect.php');
		$sql = "SELECT * FROM coachtbl WHERE kodemelli =
		'$username'
		AND password='$password'";
		$r = @mysqli_query($con,$sql);
        $num_rows = mysqli_num_rows($r);
         if($num_rows>0){
			echo ("ok");}
		 else{
			 echo (mysqli_error($con));
		 }
        @mysqli_close($con); 
    }
 
 ?>