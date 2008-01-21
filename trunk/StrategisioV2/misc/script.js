var box_loaded = false;
var http_request = false;
var changeObj;

function macheContentRequest(ID,instance) 
{
	http_request = false;
    changeObj=document.getElementById(ID);
    var firefox = document.getElementById && !document.all;
    
    if (firefox)
    {
        http_request = new XMLHttpRequest();
    }
    else
    {
        try 
        {
            http_request = new ActiveXObject("Msxml2.XMLHTTP");
        } 
        catch (e) 
        {
            try 
            {
                http_request = new ActiveXObject("Microsoft.XMLHTTP");
            } 
            catch (e) {}
        }
    }
    if (!http_request) 
	{
        alert('Servererror!');
        return false;
    }
    else
    {
	http_request.onreadystatechange = alertContent;
    http_request.open('POST', "build.jsp?instance="+instance, true);
    http_request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	http_request.send();
    }
}

function alertContent() 
{
	if (http_request.readyState == 4) 
	{
		if (http_request.status == 200) 
		{
            changeObj.innerHTML = http_request.responseText;
		} 
		else 
		{
            alert('Request error!');
        }
    }
    
}