function login(name, pass)
{
	const request = new XMLHttpRequest();

    request.onload = function()
    {
        document.getElementById("info").innerHTML = this.responseText;
    };

  	request.open("POST", "/auth", true);
	request.timeout = 5000;

  	request.send();
}

function loadBoard(option)
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
        document.getElementById("title").innerHTML = "<h1>Loading Board Title...</h1>";
        setTimeout(loadBoard, 1000);
    };

    this.refreshPosts(option);
    request.open("GET", "/board-view/option");
    request.timeout = 5000;
    request.send();
}

function refreshPosts(option)
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

    request.open("GET", "/board-posts/" + option);
    request.timeout = 5000;
    request.send();
}