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
        document.getElementById("posts").innerHTML = "Still trying ...";
        setTimeout(refreshPosts, 1000);
    };
    request.onerror = function errorCase()
    {
        document.getElementById("posts").innerHTML = "Still trying ...";
        setTimeout(refreshPosts, 1000);
    };
    request.open("GET", "/board-view/");
    request.timeout = 5000;
    request.send();
}