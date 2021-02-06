<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		$date = $_POST['date'];
		$note = $_POST['note'];
		require_once('dbConnect.php');
		$sql = "INSERT INTO `meetingdates`
		(date,note) 
		VALUES 
		(N'$date',N'$note')";
		if(mysqli_query($con,$sql)){
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