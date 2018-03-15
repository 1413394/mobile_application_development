$(document).on("submit", "#map_form", function(e) {
	e.preventDefault(); 
	var lat1= $("#latitude1").val();
	var lon1= $("#longtitude1").val();
	var lat2= $("#latitude2").val();
	var lon2= $("#longtitude2").val();
	if(checklegit(lat1,lon1,lat2,lon2))
	{
		var add_data={"lat1":lat1,"lon1":lon1,"lat2":lat2,"lon2":lon2};
  		var myJSON = JSON.stringify(add_data);
		var url = "calculate.php";
		 
		 $.ajax({
	        type: "POST",
	       url: url,
	       data: { json:myJSON}, 
	       success: function(returndata)
	       {
	       		
		        
	       	   	$("#result").text(""+returndata+" km");
	       	   	
	          
                     
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
function checklegit(lat1,lon1,lat2,lon2)
{
	
	if(isNaN(lat1) || lat1>90 ||lat1<-90)
	{
		alert("Illegal Input");
		return false;
	}
	if(isNaN(lon1) || lon1>180 ||lon1<-180)
	{
		alert("Illegal Input");
		return false;
	}
	if(isNaN(lat2) || lat2>90 ||lat2<-90)
	{
		alert("Illegal Input");
		return false;
	}
	if(isNaN(lon2) || lon2>180 ||lon2<-180)
	{
		alert("Illegal Input");
		return false;
	}
	return true;
}