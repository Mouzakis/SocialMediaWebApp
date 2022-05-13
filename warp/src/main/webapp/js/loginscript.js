'use strict';
$(document).ready(function(){
    
});
function loginFunc() {
    var x = new XMLHttpRequest();
    x.open('POST', 'LogIn');
    x.onload = function () {
        if (x.readyState === 4 && x.status === 200) {
            document.getElementById('ajaxContent').innerHTML = x.responseText;
        } else if (x.status != 200) {
            alert('Request failed. Returned status of ' + x.status);
        }
    };
    x.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    x.send("username="+document.getElementById("username").value+"&password="+document.getElementById("pw").value);
}
$("#retry").click(function(){

});