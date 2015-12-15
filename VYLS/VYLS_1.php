<?php

	$codeGroup = array(
		1 => 'Admin',
		2 => 'User'
	);
	
	if(isset($_POST['group'])){
		$registration = array();
		$registration['name'] = $_POST['username'];
		$registration['desc'] = $_POST['desc'];
		$registration['group'] = $codeGroup[$_POST['group']];

		//proses insert SQL di sini
	}


?>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Viewing POST content::toLindung</title>
	<link rel="stylesheet" type="text/css" href="library/bootstrap.min.css">
</head>
<body>
	<div class="container">
	<div class="jumbotron">
		<h3>Viewing POST content::VYLS</h3>
	</div>
	<form action="VYLS_1.php" method="post" role="form">
		<div class="form-group">
			<label>Nama</label>
			<input class="form-control" name="username" type="text">
		</div>
		<div class="form-group">
			<label>Password</label>
			<input class="form-control" name="password" type="password">
		</div>
		<div class="form-group">
			<label>Description</label>
			<input class="form-control" name="desc" type="text">
		</div>
		<div class="form-group">
			<label>Group</label>
			<select name="group" class="form-control">


				<option value="1">Administrator</option>
				<option value="2">User</option>
			</select>
		</div>
		<div class="form-group"><input class="form-control btn btn-primary" type="submit" value="submit"></div>
	</form>

		
	
	<?php if (isset($registration)): ?>
	<div id="info" class="page-header">
		<h2>Name --> <?php echo $registration['name']?></h2>
		<h4>Group --> <?php echo $registration['group']?></h4>
		<p><strong>This is the description: </strong><?php echo $registration['desc']?></p>
	</div>
	<?php endif ?>
	</div>
</body>
</html>