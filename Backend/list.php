<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		$flag=$_POST['flag'];
		require_once('dbConnect.php'); 
		/*$sql2 = "SELECT * from clienttbl";
		$rows = @mysqli_query($con,$sql2);
        $num_rows = mysqli_num_rows($rows);
        if($num_rows==0){
			echo ("not");}*/
		$sql="";
		if($flag==0){
			$sql = "SELECT `firstname`,`lastname`,`kodemelli`,`res` from clienttbl";
		}
		else if($flag==1){
			$sql = "SELECT `firstname`,`lastname`,`kodemelli`,`res` from clienttbl where heyAtreg='1'"; 
		}
		else if($flag==2){
			$sql = "SELECT `firstname`,`lastname`,`kodemelli`,`res` from clienttbl where shakhereg='1'";
		}
		else if($flag==3){
			$sql = "SELECT `firstname`,`lastname`,`kodemelli`,`res` from clienttbl where familyreg='1'";
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
		 /*else{
			 echo ("not");
		 }*/
        @mysqli_close($dbLink); 
    }
 ?>