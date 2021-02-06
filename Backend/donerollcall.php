<?php  

	if($_SERVER['REQUEST_METHOD']=='POST'){
		$kodemelli = $_POST['kodemelli'];
		$date = $_POST['date'];
		$flag = $_POST['flag'];
		require_once('dbConnect.php');
		if ($flag==0)
		{
			$sql = "SELECT `appsentdates` FROM clienttbl WHERE `kodemelli`='$kodemelli'";
			$r = @mysqli_query($con,$sql);
			$row = mysqli_fetch_array($r);
			$data = $row[0];
			$datas = "$data($date)";
			$sql = "UPDATE `clienttbl` SET `appsentdates`='$datas' WHERE `kodemelli`='$kodemelli'";
			if (@mysqli_query($con,$sql))
				echo "ok";
		}
		else if ($flag==1)
		{
			$sql = "SELECT `presendates` FROM clienttbl WHERE `kodemelli`='$kodemelli'";
			$r = @mysqli_query($con,$sql);
			$row = mysqli_fetch_array($r);
			$data = $row[0];
			$data = "$data[$date]";
			$sql = "UPDATE `clienttbl` SET `presendates`='$data' WHERE `kodemelli`='$kodemelli'";
			if (@mysqli_query($con,$sql))
				echo "ok";
		}
        @mysqli_close($con); 
    }
 
 ?>