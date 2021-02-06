<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		$id = $_POST['id'];
		$photo = $_POST['photo'];
		$firstname = $_POST['firstname'];
		$lastname = $_POST['lastname'];
		$kodemelli = $_POST['kodemelli'];
		$birthdate = $_POST['birthdate'];
		$phoneNum = $_POST['phoneNum'];
		$homeNum = $_POST['homeNum'];
		$dadNum = $_POST['dadNum'];
		$category = $_POST['category'];
		$job = $_POST['job'];
		$grade = $_POST['grade'];
		$reshte = $_POST['reshte'];
		$educationplace = $_POST['educationplace'];
		$address = $_POST['address'];
		$heyAtreg = $_POST['heyAtreg'];
		$shakhereg = $_POST['shakhereg'];
		$familyreg = $_POST['familyreg'];
		$coach = $_POST['coach'];
		$res = $_POST['res'];
		$regdate = $_POST['regdate'];
		$familycome = $_POST['familycome'];
		$whoInvitesYou = $_POST['whoInvitesYou'];
		$inviteNum = $_POST['inviteNum'];
		$football = $_POST['football'];
		$volleyball = $_POST['volleyball'];
		$basketball = $_POST['basketball'];
		$pingpong = $_POST['pingpong'];
		$readquran = $_POST['readquran'];
		$memorize = $_POST['memorize'];
		$photography = $_POST['photography'];
		$computer = $_POST['computer'];
		$sing = $_POST['sing'];
		$art = $_POST['art'];
		$other = $_POST['other'];
		$instagram = $_POST['instagram'];
		$telegram = $_POST['telegram'];
		$soroush = $_POST['soroush'];
		$ita = $_POST['ita'];
		$bale = $_POST['bale'];
		$whatsApp = $_POST['whatsApp'];
		$tweeter = $_POST['tweeter'];
		$facebook = $_POST['facebook'];
		$rubika = $_POST['rubika'];
		$describe = $_POST['describe'];
		require_once('dbConnect.php');
		$path = "profiles/$kodemelli.png";
		$actualpath = "http://localhost/baranapp/$path";
		chmod("$path",0777);
		unlink($path);
		$sql = "UPDATE `clienttbl` SET
		`firstname`=N'$firstname' , `lastname`=N'$lastname' , `kodemelli`=N'$kodemelli' , `birthdate`=N'$birthdate' , `phoneNum`=N'$phoneNum' ,
		`homeNum`=N'$homeNum' , `dadNum`=N'$dadNum' , `category`=N'$category' , `job`=N'$job' , `grade`=N'$grade' , `reshte`=N'$reshte' , 
		`educationplace`=N'$educationplace' , `address`=N'$address'
		, `heyAtreg`=N'$heyAtreg' , `shakhereg`=N'$shakhereg' , `familyreg`=N'$familyreg' , 
		`coach`=N'$coach' , `res`=N'$res' , `regdate`=N'$regdate' , `familycome`=N'$familycome' ,  `whoInvitesYou`=N'$whoInvitesYou' , `inviteNum`=N'$inviteNum' ,
		`football`=N'$football' , `volleyball`=N'$volleyball' , `basketball`=N'$basketball' , `pingpong`=N'$pingpong' , `readquran`=N'$readquran' ,
		`memorize`=N'$memorize' , `photography`=N'$photography' , `computer`=N'$computer' , `sing`=N'$sing' ,
		`art`=N'$art' , `other`=N'$other' , `instagram`=N'$instagram' , `telegram`=N'$telegram' , `soroush`=N'$soroush' , `ita`=N'$ita' , 
		`bale`=N'$bale' , `whatsApp`=N'$whatsApp' , `tweeter`=N'$tweeter' , `facebook`=N'$facebook' , `rubika`=N'$rubika' , `describes`=N'$describe' WHERE `clienttbl`.`id` = '$id'"; 
		if(mysqli_query($con,$sql)){
			file_put_contents($path,base64_decode($photo));
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