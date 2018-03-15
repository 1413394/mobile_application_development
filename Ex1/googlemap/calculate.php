<?php
		$distance="";
		if ($_SERVER["REQUEST_METHOD"] == "POST") {
			$json=$_POST['json'];
            $json = json_decode($json);
            $lat1 = $json->lat1;
            $lon1 = $json->lon1;
            $lat2 = $json->lat2;
            $lon2 = $json->lon2;
            $R=6371;
            $dLat = deg2rad($lat2-$lat1); 
			$dLon = deg2rad($lon2-$lon1); 
            $a = sin($dLat/2) * sin($dLat/2) + cos(deg2rad($lat1)) * cos(deg2rad($lat2)) * sin($dLon/2) * sin($dLon/2); 
			$c = 2 * atan2(sqrt($a), sqrt(1-$a)); 
			$d = $R * $c; // Distance in km
			echo $d;
		} 
?>