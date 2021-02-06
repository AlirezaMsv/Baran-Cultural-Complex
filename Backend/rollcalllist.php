<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		$date=$_POST['date'];
		$flag=$_POST['flag'];
		$firstname=$_POST['firstname'];
		$lastname=$_POST['lastname'];
		$kodemelli=$_POST['kodemelli'];
		$category=$_POST['category'];
		require_once('dbConnect.php'); 
		$sql="";
		$sql = "SELECT `kodemelli` FROM `clienttbl`";
		$ro = @mysqli_query($con,$sql);
		$round=array();
		while($data = mysqli_fetch_array($ro,MYSQLI_NUM))
		{
			$round[] = $data[0];
		}
		for ($i=0;$i<count($round);$i++)
		{
			$f=0;
			$sql = "SELECT * from clienttbl WHERE `appsentdates` LIKE '%$date%' AND `kodemelli`='$round[$i]'";
			$ro = @mysqli_query($con,$sql);
			$num_rows = mysqli_num_rows($ro);
			if($num_rows>0){
				$f=1;
				$sql="UPDATE `clienttbl` SET `flag`='0' WHERE `kodemelli`='$round[$i]'";
				@mysqli_query($con,$sql);}
			if ($f==0){
				$sql = "SELECT * from clienttbl WHERE `presendates` LIKE '%$date%' AND `kodemelli`='$round[$i]'";
				$ro = @mysqli_query($con,$sql);
				$num_rows = mysqli_num_rows($ro);
				if($num_rows>0){
					$f=1;
					$sql="UPDATE `clienttbl` SET `flag`='1' WHERE `kodemelli`='$round[$i]'";
					mysqli_query($con,$sql);
				}
			}
			if ($f==0){
				$sql="UPDATE `clienttbl` SET `flag`='2' WHERE `kodemelli`='$round[$i]'";
				mysqli_query($con,$sql);
			}
		}
		if ($flag==0){
			$sql = "SELECT `firstname`,`lastname`,`kodemelli`,`flag` from clienttbl";}
		else if ($flag==1){
			if ($category==0){
				$sql = "SELECT `firstname`,`lastname`,`kodemelli`,`flag` from clienttbl WHERE `firstname` LIKE '%$firstname%'
				AND `lastname` LIKE '%$lastname%' AND `kodemelli` LIKE  '$kodemelli%'";}
			else if ($category==1){
				$sql = "SELECT `firstname`,`lastname`,`kodemelli`,`flag` from clienttbl WHERE `firstname` LIKE '%$firstname%'
				AND `lastname` LIKE '%$lastname%' AND `kodemelli` LIKE  '$kodemelli%' AND `heyAtreg`='1'";}
			else if ($category==2){
				$sql = "SELECT `firstname`,`lastname`,`kodemelli`,`flag` from clienttbl WHERE `firstname` LIKE '%$firstname%'
				AND `lastname` LIKE '%$lastname%' AND `kodemelli` LIKE  '$kodemelli%' AND `shakhereg`='1'";}
			else if ($category==3){
				$sql = "SELECT `firstname`,`lastname`,`kodemelli`,`flag` from clienttbl WHERE `firstname` LIKE '%$firstname%'
				AND `lastname` LIKE '%$lastname%' AND `kodemelli` LIKE  '$kodemelli%' AND `familyreg`='1'";}
			
			}
		$r = @mysqli_query($con,$sql);
        $result = array();
		while($res = @mysqli_fetch_assoc($r))
        { 
            $result[] = $res;
            //echo json_encode(array("result"=>$result));
        }
         if(isset($result)){
			echo json_encode(array("result"=>$result));}
        @mysqli_close($dbLink); 
    }
 ?>