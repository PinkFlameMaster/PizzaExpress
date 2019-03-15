window.onload = function() {
    let content = document.getElementsByClassName("Login")[0];
    content.style.width = window.innerWidth  + "px";
    content.style.height = window.innerHeight + "px";
}
window.onresize = function(){
    let content = document.getElementsByClassName("Login")[0];
    content.style.width = window.innerWidth  + "px";
    content.style.height = window.innerHeight  + "px";
}