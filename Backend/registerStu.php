<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){
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
		$presendates = $_POST['presendates'];
		$appsentdates = $_POST['appsentdates'];
		$presennum = $_POST['presennum'];
		$appsentnum = $_POST['appsentnum'];
		$age = $_POST['age'];
		require_once('dbConnect.php');
		$path = "profiles/$kodemelli.png";
		//$actualpath = "http://localhost/baranapp/$path";
		$sql = "INSERT INTO `clienttbl`
		(firstname , lastname , kodemelli , birthdate , phoneNum , homeNum , dadNum , category , job , grade , reshte , educationplace , address , heyAtreg , shakhereg , familyreg , 
		coach , res , regdate , familycome ,  whoInvitesYou , inviteNum , football , volleyball , basketball , pingpong , readquran , memorize , photography , computer , sing ,
		art , other , instagram , telegram , soroush , ita , bale , whatsApp , tweeter , facebook , rubika , describes,presendates,appsentdates,presennum,appsentnum,age) 
		VALUES 
		(N'$firstname',N'$lastname',N'$kodemelli',N'$birthdate',N'$phoneNum',N'$homeNum',N'$dadNum',N'$category',N'$job',N'$grade',N'$reshte',N'$educationplace',
		N'$address',N'$heyAtreg',N'$shakhereg',N'$familyreg',N'$coach',N'$res',N'$regdate',N'$familycome',N'$whoInvitesYou',N'$inviteNum',N'$football',N'$volleyball',N'$basketball',
		N'$pingpong',N'$readquran',N'$memorize',N'$photography',N'$computer',N'$sing',N'$art',N'$other',N'$instagram',N'$telegram',N'$soroush',
		N'$ita',N'$bale',N'$whatsApp',N'$tweeter',N'$facebook',N'$rubika',N'$describe' , N'$presendates', N'$appsentdates' , N'$presennum' , N'$appsentnum',N'$age')";
		$sql2 = "SELECT * FROM clienttbl WHERE kodemelli =
		'$kodemelli'";
		$r = @mysqli_query($con,$sql2);
        $num_rows = mysqli_num_rows($r);
        if($num_rows>0){
			echo ("no");}
		else if(mysqli_query($con,$sql)){
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