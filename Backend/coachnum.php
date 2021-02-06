<?php  

	if($_SERVER['REQUEST_METHOD']=='POST'){
		require_once('dbConnect.php');
		$sql = "SELECT * FROM coachtbl";
		$r = @mysqli_query($con,$sql);
        $num_rows = mysqli_num_rows($r);
        if($num_rows>=0){
			echo ($num_rows);}
		else{
			echo (mysqli_error($con));
		}
        @mysqli_close($con); 
    }
 
 ?>