function checkUserAuth()
{
	const request = new XMLHttpRequest();

    request.onload = function()
    {
        if(request.status !== 401)
        {
            window.location.href = window.location.origin + '/myboards';
        }
    };

  	request.open("GET", "/user", true);
	request.timeout = 5000;

    const token = getTokenCookie();

    if(token)
    {
        request.setRequestHeader("Authorization", token);
    }

  	request.send();
}

function loadBoard()
{
    var request = new XMLHttpRequest();
    request.onload = function upDate()
    {
        document.getElementById("title").innerHTML = this.responseText;
    };
    request.ontimeout = function timeoutCase()
    {
        document.getElementById("title").innerHTML = "<h1>Loading Board Title...</h1>";
        setTimeout(loadBoard, 1000);
    };
    request.onerror = function errorCase()
    {
        document.getElementById("title").innerHTML = "<h1>Loading Board Title...</h1>"";
        setTimeout(loadBoard, 1000);
    };

    this.refreshPosts();
    request.open("GET", "/board-view/");
    request.timeout = 5000;
    request.send();
}

function refreshPosts()
{
    var request = new XMLHttpRequest();
    request.onload = function upDate()
    {
        document.getElementById("posts").innerHTML = this.responseText;
        setTimeout(refreshPosts, 1500);
    };
    request.ontimeout = function timeoutCase()
    {
        document.getElementById("posts").innerHTML = "<h3>Loading Posts...</h3>";
        setTimeout(refreshPosts, 1000);
    };
    request.onerror = function errorCase()
    {
        document.getElementById("posts").innerHTML = "<h3>Loading Posts...</h3>";
        setTimeout(refreshPosts, 1000);
    };

    request.open("GET", "/board-posts/");
    request.timeout = 5000;
    request.send();
}