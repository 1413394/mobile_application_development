$(document).on("submit", "#map_form", function(e) {
	e.preventDefault(); 
	var lat= $("#latitude").val();
	var lon= $("#longtitude").val();
	if(checklegit(lat,lon))
	{
		var latlng=""+lat+", "+lon;
		var url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + latlng + "&key=AIzaSyDgXJ7TWsLQCE6NMXI9Ncy5GsAm3T94OL8";
		 
		 $.ajax({
	        type: "POST",
	       url: url,
	       data: { }, 
	       success: function(returndata)
	       {
	       		
		        if(returndata.status=="OK")
	       	   		$("#result").text(""+returndata.results[0].formatted_address);
	       	   	else{
	       	   		alert(returndata.status);
	       	   	}
	           /*for(var i=0;i<data.results.length;i++) {
		              var adress = data.results[i].formatted_address;
		              
		              alert(adress);
          		}*/
                     
	       },
	         error: function(jqXHR, textStatus,exception) {
	          //On error do this
	              var msg = '';
	              if (jqXHR.status === 0) {
	                  msg = 'Not connect.\n Verify Network.';
	              } else if (jqXHR.status == 404) {
	                  msg = 'Requested page not found. [404]';
	              } else if (jqXHR.status == 500) {
	                  msg = 'Internal Server Error [500].';
	              } else if (exception === 'parsererror') {
	                  msg = 'Requested JSON parse failed.';
	              } else if (exception === 'timeout') {
	                  msg = 'Time out error.';
	              } else if (exception === 'abort') {
	                  msg = 'Ajax request aborted.';
	              } else {
	                  msg = 'Uncaught Error.\n' + jqXHR.responseText;
	              }
	              alert(msg);
          }
	    });
	}
});
function checklegit(lat,lon)
{
	
	if(isNaN(lat) || lat>90 ||lat<-90)
	{
		alert("Illegal Input");
		return false;
	}
	if(isNaN(lon) || lon>180 ||lon<-180)
	{
		alert("Illegal Input");
		return false;
	}
	return true;
}