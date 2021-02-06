<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		$flag = $_POST['flag'];
		require_once('dbConnect.php');   
		$sql="";
		$sql = "SELECT `date`,`note` from meetingdates";
		$r = @mysqli_query($con,$sql);
        $result = array();
		while($res = @mysqli_fetch_assoc($r))
        { 
            $result[] = $res;
            //echo json_encode(array("result"=>$result));
        }
         if(isset($result)){
			echo json_encode(array("result"=>$result));}
		 else{
			 echo ("not");
		 }
        @mysqli_close($dbLink); 
    }
 ?>