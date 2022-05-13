"use strict";

function rate_post(id){
    //alert(id);
    document.getElementById('rate_post').innerHTML =[
        `<div>
<input type="radio" name="rating" value="1"> 1
<input type="radio" name="rating" value="2"> 2
<input type="radio" name="rating" value="3"> 3 
<input type="radio" name="rating" value="4"> 4
<input type="radio" name="rating" value="5"> 5
        </div>
<button class="view_post" onclick="rate_post2(`+id+`);">Rate</button>`
        ].join("\n") ;
    
    
}


function rate_post2(id){
    //alert(id);
    /*var xhr = new XMLHttpRequest();
    
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById('ajaxContent').innerHTML = xhr.responseText;

        } else if (xhr.status != 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.open('POST', 'RatePost');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send("id="+id);*/
}