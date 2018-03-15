<!DOCTYPE html>
<html>
<head>
	<title>Bai 1</title>
	<meta charset="UTF-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<script language="javascript" type="text/javascript" src="./script/bai1.js"></script>
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="container">
	<form action="" method="POST" name="map_form" id="map_form" style="text-align: center;margin-top:50px">
		<strong>Latitude: </strong><input type="number" name="latitude" id="latitude" step="0.000001"><br><br>
  		<strong>Longtitude: </strong><input type="number" name="longtitude" id="longtitude" step="0.000001"><br>
  		<input type="submit" value="Go" style="margin:10px;"><br>
  		<strong>Result: </strong><span id="result" style="font-weight: bold;"></span>
	</form>
</div>
</body>
</html>