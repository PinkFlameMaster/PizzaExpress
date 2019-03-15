window.onload = function() {
    let content = document.getElementsByTagName("body")[0];
    content.style.width = window.innerWidth  + "px";
    content.style.height = window.innerHeight + "px";
}
window.onresize = function(){
    let content = document.getElementsByTagName("body")[0];
    content.style.width = window.innerWidth  + "px";
    content.style.height = window.innerHeight  + "px";
}