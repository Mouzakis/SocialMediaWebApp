"use strict";
var dataurl = "";
var lusername;
$(document).ready(function (){
    
    $("body").on('click', '#login', function(){
        var match = document.cookie.match(new RegExp('(^| )' +"user" + '=([^;]+)'));
        if (match) {
            lusername = match[2];
            $("body").addClass("login_mode");
            $("body").removeClass("initial");
            $("body").html("<h1>Succesfully Logged in as</h1>"+
                        match[2] +
                        "<p>What would you like to do?</p>"+
                        "<div class='button-container'>"+
                        "<button id='logout'>Logout</button>"+
                        "<button id='memberlist'>Members List</button>"+
                        "<button id='review'>Review Data</button>"+
                        "<button id='main-posts'>Posts Interface</button>"+
                        "<button id='create-post'>Create a Post</button>"+
                        "<button id='delete-post'>My Posts</button>"+
                        "<button id='delete-user' onclick=\"delete_user();\">Delele Profile</button>"+
                        "</div>");
        } else {
            $("body").html("<div class='container'>"+
                          "<div id='login_form' action='#'>"+
                        "<div class='input-item'>"+
				"<label for='username' lang='en'>*Username:</label>"+
				"<input type='text' id='username' name='username' pattern='[A-Za-z]{8,}' title='8 or more latin characters' required>"+
			"</div>"+
			"<div class='input-item'>"+
				"<label for='pw'>*Κωδικός χρήστη:</label>"+
				"<input type='password' name='pw' id='pw' pattern='(?=.*\d)(?=.*[A-Za-z])(?=.*[^A-Za-z0-9]+){8,10}' title='From 8 to 10 characters and please include at least 1 latin character, 1 number and 1 symbol!' required>"+
			"</div>"+
                        "<button class='submit active' id='inner_login' onclick='loginFunc();' type='submit'>Log In</button>"+
                        "<button id=\"homepage\">Homepage</button>"+
                        "</div>"+
                        "</div>");
            $("body").addClass("login_mode");
            $("body").removeClass("initial");
        }
    });
    $("body").on('click', '#register', function(){
        $("body").html(getRegisterHtml());
            
    });
    $("body").on('click', '#inner_login', function(){
        lusername = $("#username").val();
    });
    $("body").on('click', "#homepage", function(){
        if ($("body").hasClass("register_mode")) {
            $(".register_mode").html("<h1>Warp Drive</h1>"+
                    "<div class='container'>"+
                     "<p>Log In or Create an Account</p>"+
                     "<button id='login'>Log In</button>"+
                     "<button id='register'>Register</button>"+
                     "</div>");
            $(".register_mode").addClass("initial");
            $(".initial").removeClass("register_mode");
             } else {
            $(".login_mode").html("<h1>Warp Drive</h1>"+
                    "<div class='container'>"+
                     "<p>Log In or Create an Account</p>"+
                     "<button id='login'>Log In</button>"+
                     "<button id='register'>Register</button>"+
                     "</div>");
            $(".login_mode").addClass("initial");
            $(".initial").removeClass("login_mode");
        }
    });
    $("body").on('click', '#memberlist', function(){
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'MembersList');
        xhr.onload = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.getElementById('ajaxContent').innerHTML = xhr.responseText;

            } else if (xhr.status !== 200) {
                alert('Request failed. Returned status of ' + xhr.status);
            }
        };

        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.send();
    });
    $("body").on('click', '#logout', function(){
        $("body").html("<h1>Warp Drive</h1>"+
               "<div class='container'>"+
                "<p>Log In or Create an Account</p>"+
                "<button id='login'>Log In</button>"+
                "<button id='register'>Register</button>"+
                "</div>");
        $("body").addClass("initial");
        $("body").removeClass("login_mode");
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'SessionTrack');
        xhr.onload = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                //gg
            } else if (xhr.status !== 200) {
                alert('Request failed. Returned status of ' + xhr.status);
            }
        };

        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.send("mode=logout");
    });
    $("body").on('click', '#update', function(){
        var gender = $("input[name=gender]:checked").val();
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'UpdateUser');
        xhr.onload = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.getElementById('ajaxContent').innerHTML = xhr.responseText;

            } else if (xhr.status != 200) {
                alert('Request failed. Returned status of ' + xhr.status);
            }
        };

        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.send("user_name="+lusername+"&email="+document.getElementById("email").value
        +"&password="+document.getElementById("pw").value+"&first_name="+document.getElementById("name").value
        +"&last_name="+document.getElementById("lastname").value+"&birth_date="+document.getElementById("birth").value
        +"&gender="+gender+"&country="+document.getElementById("country").value
        +"&town="+document.getElementById("city").value+"&address="+document.getElementById("address").value
        +"&occupation="+document.getElementById("profession").value+"&interests="+document.getElementById("interests").value
        +"&info="+document.getElementById("more_info").value);
    });
    $("body").on('click', '#review', function(){
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'GetUser');
        xhr.onload = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.getElementById('ajaxContent').innerHTML = xhr.responseText;

            } else if (xhr.status != 200) {
                alert('Request failed. Returned status of ' + xhr.status);
            }
        };

        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.send("user_name="+lusername);
    });
    $("body").on('click', '#main-posts', function(){
         var xhr = new XMLHttpRequest();
        xhr.open('POST', 'PostsInterface');
        //alert("ASDF");
        xhr.onload = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.getElementById('ajaxContent').innerHTML = xhr.responseText;

            } else if (xhr.status != 200) {
                alert('Request failed. Returned status of ' + xhr.status);
            }
        };

        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.send();
    });
    $("body").on('click', '#create-post', function(){
        document.getElementById('ajaxContent').innerHTML = `<div class='button-container'>
                    <button id='logout'>Logout</button>
                    <button id='memberlist'>Members List</button>
                    <button id='review'>Review Data</button>
                    <button id='main-posts'>Posts Interface</button>
                    <button id='delete-post'>Delete a Post</button>
                    <label for='comment'>*Comment</label>
                    <textarea name='comment' id='comment' minlength='1' width='400px' title='From 1 to 100 characters!' required></textarea>
                    <label for='resource'>*Image Source</label>
                    <input name='resource' id='resource' title='From 1 to 100 characters!'>
                    <label for='img'>*Image</label>
                    <input name='img' id='img' title='From 1 to 100 characters!'>
                    <label for='base64'>*Base64 Image</label>
                    <input name='base64' id='base64' title='From 1 to 100 characters!'>
                    <label for='lat'>Latitude</label>
                    <input name='lat' id='lat' title='From 1 to 100 characters!'>
                    <label for='long'>Longtitude</label>
                    <input name='long' id='long' title='From 1 to 100 characters!'>
                    <input type="radio" name="record_enable" id="open" onclick="record_handler()">Open
                    <input type="radio" id="close" name="record_enable" onclick="record_handler()">Close

                        <div id="record" class="record">
                          <video id='video' width='320' height='240' autoplay></video>
                          <input type='button' id="snap" value ='Take Photo'>
                          <input type='button' id="upload" value ='Upload Image'>
                          <canvas id='canvas' width='320' height='240'></canvas>
                        </div>
                    <button id='addpost'>Upload Post</button>`;
    });
    $("body").on('click', '#delete-post', function(){
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'DeletePostInterface');
        xhr.onload = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.getElementById('ajaxContent').innerHTML = xhr.responseText;

            } else if (xhr.status != 200) {
                alert('Request failed. Returned status of ' + xhr.status);
            }
        };

        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.send("username="+lusername);
    });
    $("body").on('click', '#addpost', function(){
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'CreatePost');
        xhr.onload = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.getElementById('ajaxContent').innerHTML = xhr.responseText;

            } else if (xhr.status != 200) {
                alert('Request failed. Returned status of ' + xhr.status);
            }
        };

        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.send("username="+lusername+"&comment="+document.getElementById("comment").value
        +"&resource="+document.getElementById("resource").value+"&img="+document.getElementById("img").value
        +"&base64="+dataurl+"&lat="+document.getElementById("lat").value
        +"&long="+document.getElementById("long").value);
    });
});

function deletepost(id, username) {
    //alert(username);
    var xhr = new XMLHttpRequest();
    
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById('ajaxContent').innerHTML = xhr.responseText;

        } else if (xhr.status != 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.open('POST', 'DeletePost');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send("id="+id+"&username="+username);
}
function view_post(pid){
    var xhr = new XMLHttpRequest();
        
        xhr.onload = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.getElementById('ajaxContent').innerHTML = xhr.responseText;
                if ($("#long").val()!=="" && $("#lat").val()!=="" && $("#long").val() && $("#long").val()) {
                    alert("ASDF");
                    var nreq = new XMLHttpRequest();
                    var nurl = "https://nominatim.openstreetmap.org/reverse?format=json&lat="+$("#lat").val()+"&lon="+$("#long").val()+"&addressdetails=1&zoom=18";
                    nreq.open("GET", nurl, true);
                    var flag = true;
                    nreq.onreadystatechange = function() {
                            if (nreq.readyState === 4 && nreq.status === 200) {
                                var response = JSON.parse(nreq.responseText);
                                if (response === undefined) {
                                    
                                } else {
                                    console.log(response);
                                    var map = new ol.Map({
                                         layers: [
                                           new ol.layer.Tile({
                                             source: new ol.source.OSM()
                                           })
                                        ],
                                        target: 'map',
                                        view: new ol.View({
                                            center: ol.proj.transform([25.25, 25.25], 'EPSG:4326', 'EPSG:3857'),
                                            zoom: 8
                                        })
                                    });
                                    function add_map_point(lat, lng) {
                                            var vectorLayer = new ol.layer.Vector({
                                                    source:new ol.source.Vector({
                                                            features: [new ol.Feature({
                                                                    geometry: new ol.geom.Point(ol.proj.transform([parseFloat(lng), parseFloat(lat)], 'EPSG:4326', 'EPSG:3857')),
                                                            })]
                                                    }),
                                                    style: new ol.style.Style({
                                                            image: new ol.style.Icon({
                                                                    anchor: [0.5, 0.5],
                                                                    anchorXUnits: "fraction",
                                                                    anchorYUnits: "fraction",
                                                                    src: "https://upload.wikimedia.org/wikipedia/commons/e/ec/RedDot.svg"
                                                            })
                                                    })
                                            });
                                            map.addLayer(vectorLayer);
                                    }
                                    var lati = parseFloat($("#lat").val());
                                    var long = parseFloat($("#long").val());
                                    map.getView().setCenter(ol.proj.transform([long, lati], 'EPSG:4326', 'EPSG:900913'));
                                    add_map_point(lati, long);
                                    $("#user_pos").html("<p>Address : "+response.address.road+"</p>" +
                                            "<p>Country : "+response.address.country_code+"</p>" +
                                            "<p>City : "+response.address.city+"</p>");
                                }
                            }
                            else if (nreq.status != 200 && flag) {
                                $("#user_pos").html("");
                            }
                    };
                    nreq.send();
                } else if (xhr.status !== 200) {
                    alert('Request failed. Returned status of ' + xhr.status);
                }

            } else if (xhr.status !== 200) {
                alert('Request failed. Returned status of ' + xhr.status);
            }
        };
        xhr.open('POST', 'ViewPost');
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.send("PostID="+pid+"&username="+lusername);
}


function update_post(pid){
    var xhr = new XMLHttpRequest();
        
        xhr.onload = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.getElementById('ajaxContent').innerHTML = xhr.responseText;
                document.getElementById('hdp').innerHTML = [
                    `
                    <label for='comment'>*Comment</label>
                    <textarea name='comment' id='comment' minlength='1' width='400px' title='From 1 to 100 characters!' required></textarea>
                    <label for='resource'>*Image Source</label>
                    <input name='resource' id='resource' title='From 1 to 100 characters!'>
                    <label for='img'>*Image</label>
                    <input name='img' id='img' title='From 1 to 100 characters!'>
                    <label for='base64'>*Base64 Image</label>
                    <input name='base64' id='base64' title='From 1 to 100 characters!'>
                    <label for='lat'>Latitude</label>
                    <input name='lat' id='lat' title='From 1 to 100 characters!'>
                    <label for='long'>Longtitude</label>
                    <input name='long' id='long' title='From 1 to 100 characters!'>
                    <input type="radio" name="record_enable" id="open" onclick="record_handler()">Open
                    <input type="radio" id="close" name="record_enable" onclick="record_handler()">Close

                        <div id="record" class="record">
                          <video id='video' width='320' height='240' autoplay></video>
                          <input type='button' id="snap" value ='Take Photo'>
                          <input type='button' id="upload" value ='Upload Image'>
                          <canvas id='canvas' width='320' height='240'></canvas>
                        </div>
                    <button id='update_post2' onclick="update_post2(`+pid+`);">Update</button>`
                ].join("\n");

            } else if (xhr.status != 200) {
                alert('Request failed. Returned status of ' + xhr.status);
            }
        };
        xhr.open('POST', 'ViewPost');
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.send("PostID="+pid+"&username="+lusername);
        
        
}

function update_post2(pid){
    
    var xhr = new XMLHttpRequest();
        var comment = document.getElementById('comment').value;
        var resource = document.getElementById('resource').value;
        var img = document.getElementById('img').value;
        var base64 = document.getElementById('base64').value;
        var lon = document.getElementById('long').value;
        var lat = document.getElementById('lat').value;
        
        
        xhr.onload = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.getElementById('ajaxContent').innerHTML = xhr.responseText;

            } else if (xhr.status != 200) {
                alert('Request failed. Returned status of ' + xhr.status);
            }
        };
        xhr.open('POST', 'UpdatePost');
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.send("PostID="+pid+"&comment="+comment+"&resource="+resource+"&img="+img+"&base64="+base64+"&lon="+lon+"&lat="+lat);
}

function gotouser(username) {
    var xhr = new XMLHttpRequest();
        xhr.open('POST', 'UserProfile');
        xhr.onload = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.getElementById('ajaxContent').innerHTML = xhr.responseText;
            }
        };

        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.send("username="+username);
}

function delete_user(){
    var xhr = new XMLHttpRequest();
        xhr.open('POST', 'DeleteUser');
        xhr.onload = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.getElementById('ajaxContent').innerHTML = xhr.responseText;
            }
        };

        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.send("username="+lusername);
}

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
    var rating = $("input[name=rating]:checked").val();
    //alert(rating);
    var xhr = new XMLHttpRequest();
    
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById('rate_post').innerHTML = xhr.responseText;

        } else if (xhr.status != 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.open('POST', 'RatePost');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send("id="+id+"&username="+lusername+"&rating="+rating);
}

function change_rate(id){
    //alert(id);
    document.getElementById('rate_post').innerHTML =[
        `<div>
<input type="radio" name="rating" value="1"> 1
<input type="radio" name="rating" value="2"> 2
<input type="radio" name="rating" value="3"> 3 
<input type="radio" name="rating" value="4"> 4
<input type="radio" name="rating" value="5"> 5
        </div>
<button class="view_post" onclick="change_rate2(`+id+`);">Rate</button>`
        ].join("\n") ;
    
    
}

function change_rate2(id){
    alert(id);
    var rating = $("input[name=rating]:checked").val();
    //alert(rating);
    var xhr = new XMLHttpRequest();
    
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById('rate_post').innerHTML = xhr.responseText;

        } else if (xhr.status != 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.open('POST', 'ChangeRate');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send("id="+id+"&username="+lusername+"&rating="+rating);
}

function addcomment(id){
    document.getElementById('my_comment').innerHTML=[
        `<textarea id="comment_text"></textarea>
         <button class=\"view_post\" onclick="addcomment2(`+id+`)">Add comment</button>`
    ].join("/n");
}
function addcomment2(id){
    //alert(id);
    var comment = document.getElementById('comment_text').value;
    alert(comment);
    var xhr = new XMLHttpRequest();
    
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById('my_comment').innerHTML = xhr.responseText;

        } else if (xhr.status != 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.open('POST', 'AddComment');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send("id="+id+"&username="+lusername+"&comment="+comment);
}
function getRegisterHtml() {
    return `<span id="esc_map" class="esc_map">ESCAPE</span>
	<div class="map" id="map"></div>
	<div class="container">
		<div id="register_form" action="#">
			<h1 lang="en">Warp Drive Register</h1>
			<span class="reversegeo" id="reversegeo">Find my location</span>
			<div class="input-item" id="username-item">
				<label for="username" lang="en">*Username:</label>
				<input type="text" id="username" name="username" pattern="[A-Za-z]{8,}" title="8 or more latin characters" onchange="userCheck('username')" required>
			</div>
		
			<div class="input-item" id="email-item">
				<label for="email" lang="en">*E-mail:</label>
				<input id="email" type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" name="email" title="'text'@'text'.'2-3 characters'" onchange="userCheck('email')" required>
			</div>
		
			<div class="input-item">
				<label for="pw">*Κωδικός χρήστη:</label>
				<input type="password" name="pw" id="pw" pattern="(?=.*[0-9])(?=.*[A-Za-z])(?=.*[^A-Za-z0-9]+)[-\\S]{8,10}" title="From 8 to 10 characters and please include at least 1 latin character, 1 number and 1 symbol!" required>
			</div>
		
			<div class="input-item verify">
				<label for="pw-verify">*Επιβεβαίωση Κωδικού:</label>
				<input type="password" id="pw-verify" name="pw-verify" pattern="(?=.*[0-9])(?=.*[A-Za-z])(?=.*[^A-Za-z0-9]+)[-\\S]{8,10}" title="From 8 to 10 characters and please include at least 1 latin character, 1 number and 1 symbol!" required>
				<span class="verification_error" id="pass_mismatch"></span>
			</div>
		
			<div class="input-item">
				<label for="name">*Όνομα:</label>
				<input type="text" name="name" id="name" pattern="[A-Za-z]{3,15}" title="3 - 15 latin characters" required>
			</div>
		
			<div class="input-item">
				<label for="lastname">*Επώνυμο:</label>
				<input type="text" id="lastname" name="surname" pattern="[A-Za-z]{3,15}" title="3 - 15 latin characters" required>
			</div>
		
			<div class="input-item">
				<label for="birth">*Ημερομηνία γέννησης:</label>
				<input id="birth" type="text" name="date" pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}$" title="ΗΗ/ΜΜ/ΧΧΧΧ" required>
			</div>
		
			<div class="input-item">
				<fieldset>
				    <legend>Φύλο:</legend>

				    <label for="gender_boy">Αγόρι<input id="gender_boy" name="gender" type="radio" value="male"></label>

				    <label for="gender_girl">Κορίτσι<input id="gender_girl" name="gender" type="radio" value="female"></label>

				    <label for="gender_what">Μη Εφαρμόσιμο<input id="gender_what" name="gender" type="radio" value="other"></label>
				</fieldset>
			</div>
	    
	    	<div class="input-item">
		    	<label for="address">Διεύθυνση:</label>
		    	<input type="text" id="address" name="address" pattern=".{2,20}" required>
			</div>

			<div class="input-item">
		    	<label for="country">*Χώρα:</label>
		    	<select id="country">
					<option value="AF">Afghanistan</option>
					<option value="AX">Åland Islands</option>
					<option value="AL">Albania</option>
					<option value="DZ">Algeria</option>
					<option value="AS">American Samoa</option>
					<option value="AD">Andorra</option>
					<option value="AO">Angola</option>
					<option value="AI">Anguilla</option>
					<option value="AQ">Antarctica</option>
					<option value="AG">Antigua and Barbuda</option>
					<option value="AR">Argentina</option>
					<option value="AM">Armenia</option>
					<option value="AW">Aruba</option>
					<option value="AU">Australia</option>
					<option value="AT">Austria</option>
					<option value="AZ">Azerbaijan</option>
					<option value="BS">Bahamas</option>
					<option value="BH">Bahrain</option>
					<option value="BD">Bangladesh</option>
					<option value="BB">Barbados</option>
					<option value="BY">Belarus</option>
					<option value="BE">Belgium</option>
					<option value="BZ">Belize</option>
					<option value="BJ">Benin</option>
					<option value="BM">Bermuda</option>
					<option value="BT">Bhutan</option>
					<option value="BO">Bolivia, Plurinational State of</option>
					<option value="BQ">Bonaire, Sint Eustatius and Saba</option>
					<option value="BA">Bosnia and Herzegovina</option>
					<option value="BW">Botswana</option>
					<option value="BV">Bouvet Island</option>
					<option value="BR">Brazil</option>
					<option value="IO">British Indian Ocean Territory</option>
					<option value="BN">Brunei Darussalam</option>
					<option value="BG">Bulgaria</option>
					<option value="BF">Burkina Faso</option>
					<option value="BI">Burundi</option>
					<option value="KH">Cambodia</option>
					<option value="CM">Cameroon</option>
					<option value="CA">Canada</option>
					<option value="CV">Cape Verde</option>
					<option value="KY">Cayman Islands</option>
					<option value="CF">Central African Republic</option>
					<option value="TD">Chad</option>
					<option value="CL">Chile</option>
					<option value="CN">China</option>
					<option value="CX">Christmas Island</option>
					<option value="CC">Cocos (Keeling) Islands</option>
					<option value="CO">Colombia</option>
					<option value="KM">Comoros</option>
					<option value="CG">Congo</option>
					<option value="CD">Congo, the Democratic Republic of the</option>
					<option value="CK">Cook Islands</option>
					<option value="CR">Costa Rica</option>
					<option value="CI">Côte d'Ivoire</option>
					<option value="HR">Croatia</option>
					<option value="CU">Cuba</option>
					<option value="CW">Curaçao</option>
					<option value="CY">Cyprus</option>
					<option value="CZ">Czech Republic</option>
					<option value="DK">Denmark</option>
					<option value="DJ">Djibouti</option>
					<option value="DM">Dominica</option>
					<option value="DO">Dominican Republic</option>
					<option value="EC">Ecuador</option>
					<option value="EG">Egypt</option>
					<option value="SV">El Salvador</option>
					<option value="GQ">Equatorial Guinea</option>
					<option value="ER">Eritrea</option>
					<option value="EE">Estonia</option>
					<option value="ET">Ethiopia</option>
					<option value="FK">Falkland Islands (Malvinas)</option>
					<option value="FO">Faroe Islands</option>
					<option value="FJ">Fiji</option>
					<option value="FI">Finland</option>
					<option value="FR">France</option>
					<option value="GF">French Guiana</option>
					<option value="PF">French Polynesia</option>
					<option value="TF">French Southern Territories</option>
					<option value="GA">Gabon</option>
					<option value="GM">Gambia</option>
					<option value="GE">Georgia</option>
					<option value="DE">Germany</option>
					<option value="GH">Ghana</option>
					<option value="GI">Gibraltar</option>
					<option selected="selected" value="GR">Greece</option>
					<option value="GL">Greenland</option>
					<option value="GD">Grenada</option>
					<option value="GP">Guadeloupe</option>
					<option value="GU">Guam</option>
					<option value="GT">Guatemala</option>
					<option value="GG">Guernsey</option>
					<option value="GN">Guinea</option>
					<option value="GW">Guinea-Bissau</option>
					<option value="GY">Guyana</option>
					<option value="HT">Haiti</option>
					<option value="HM">Heard Island and McDonald Islands</option>
					<option value="VA">Holy See (Vatican City State)</option>
					<option value="HN">Honduras</option>
					<option value="HK">Hong Kong</option>
					<option value="HU">Hungary</option>
					<option value="IS">Iceland</option>
					<option value="IN">India</option>
					<option value="ID">Indonesia</option>
					<option value="IR">Iran, Islamic Republic of</option>
					<option value="IQ">Iraq</option>
					<option value="IE">Ireland</option>
					<option value="IM">Isle of Man</option>
					<option value="IL">Israel</option>
					<option value="IT">Italy</option>
					<option value="JM">Jamaica</option>
					<option value="JP">Japan</option>
					<option value="JE">Jersey</option>
					<option value="JO">Jordan</option>
					<option value="KZ">Kazakhstan</option>
					<option value="KE">Kenya</option>
					<option value="KI">Kiribati</option>
					<option value="KP">Korea, Democratic People's Republic of</option>
					<option value="KR">Korea, Republic of</option>
					<option value="KW">Kuwait</option>
					<option value="KG">Kyrgyzstan</option>
					<option value="LA">Lao People's Democratic Republic</option>
					<option value="LV">Latvia</option>
					<option value="LB">Lebanon</option>
					<option value="LS">Lesotho</option>
					<option value="LR">Liberia</option>
					<option value="LY">Libya</option>
					<option value="LI">Liechtenstein</option>
					<option value="LT">Lithuania</option>
					<option value="LU">Luxembourg</option>
					<option value="MO">Macao</option>
					<option value="MK">Macedonia, the former Yugoslav Republic of</option>
					<option value="MG">Madagascar</option>
					<option value="MW">Malawi</option>
					<option value="MY">Malaysia</option>
					<option value="MV">Maldives</option>
					<option value="ML">Mali</option>
					<option value="MT">Malta</option>
					<option value="MH">Marshall Islands</option>
					<option value="MQ">Martinique</option>
					<option value="MR">Mauritania</option>
					<option value="MU">Mauritius</option>
					<option value="YT">Mayotte</option>
					<option value="MX">Mexico</option>
					<option value="FM">Micronesia, Federated States of</option>
					<option value="MD">Moldova, Republic of</option>
					<option value="MC">Monaco</option>
					<option value="MN">Mongolia</option>
					<option value="ME">Montenegro</option>
					<option value="MS">Montserrat</option>
					<option value="MA">Morocco</option>
					<option value="MZ">Mozambique</option>
					<option value="MM">Myanmar</option>
					<option value="NA">Namibia</option>
					<option value="NR">Nauru</option>
					<option value="NP">Nepal</option>
					<option value="NL">Netherlands</option>
					<option value="NC">New Caledonia</option>
					<option value="NZ">New Zealand</option>
					<option value="NI">Nicaragua</option>
					<option value="NE">Niger</option>
					<option value="NG">Nigeria</option>
					<option value="NU">Niue</option>
					<option value="NF">Norfolk Island</option>
					<option value="MP">Northern Mariana Islands</option>
					<option value="NO">Norway</option>
					<option value="OM">Oman</option>
					<option value="PK">Pakistan</option>
					<option value="PW">Palau</option>
					<option value="PS">Palestinian Territory, Occupied</option>
					<option value="PA">Panama</option>
					<option value="PG">Papua New Guinea</option>
					<option value="PY">Paraguay</option>
					<option value="PE">Peru</option>
					<option value="PH">Philippines</option>
					<option value="PN">Pitcairn</option>
					<option value="PL">Poland</option>
					<option value="PT">Portugal</option>
					<option value="PR">Puerto Rico</option>
					<option value="QA">Qatar</option>
					<option value="RE">Réunion</option>
					<option value="RO">Romania</option>
					<option value="RU">Russian Federation</option>
					<option value="RW">Rwanda</option>
					<option value="BL">Saint Barthélemy</option>
					<option value="SH">Saint Helena, Ascension and Tristan da Cunha</option>
					<option value="KN">Saint Kitts and Nevis</option>
					<option value="LC">Saint Lucia</option>
					<option value="MF">Saint Martin (French part)</option>
					<option value="PM">Saint Pierre and Miquelon</option>
					<option value="VC">Saint Vincent and the Grenadines</option>
					<option value="WS">Samoa</option>
					<option value="SM">San Marino</option>
					<option value="ST">Sao Tome and Principe</option>
					<option value="SA">Saudi Arabia</option>
					<option value="SN">Senegal</option>
					<option value="RS">Serbia</option>
					<option value="SC">Seychelles</option>
					<option value="SL">Sierra Leone</option>
					<option value="SG">Singapore</option>
					<option value="SX">Sint Maarten (Dutch part)</option>
					<option value="SK">Slovakia</option>
					<option value="SI">Slovenia</option>
					<option value="SB">Solomon Islands</option>
					<option value="SO">Somalia</option>
					<option value="ZA">South Africa</option>
					<option value="GS">South Georgia and the South Sandwich Islands</option>
					<option value="SS">South Sudan</option>
					<option value="ES">Spain</option>
					<option value="LK">Sri Lanka</option>
					<option value="SD">Sudan</option>
					<option value="SR">Suriname</option>
					<option value="SJ">Svalbard and Jan Mayen</option>
					<option value="SZ">Swaziland</option>
					<option value="SE">Sweden</option>
					<option value="CH">Switzerland</option>
					<option value="SY">Syrian Arab Republic</option>
					<option value="TW">Taiwan, Province of China</option>
					<option value="TJ">Tajikistan</option>
					<option value="TZ">Tanzania, United Republic of</option>
					<option value="TH">Thailand</option>
					<option value="TL">Timor-Leste</option>
					<option value="TG">Togo</option>
					<option value="TK">Tokelau</option>
					<option value="TO">Tonga</option>
					<option value="TT">Trinidad and Tobago</option>
					<option value="TN">Tunisia</option>
					<option value="TR">Turkey</option>
					<option value="TM">Turkmenistan</option>
					<option value="TC">Turks and Caicos Islands</option>
					<option value="TV">Tuvalu</option>
					<option value="UG">Uganda</option>
					<option value="UA">Ukraine</option>
					<option value="AE">United Arab Emirates</option>
					<option value="GB">United Kingdom</option>
					<option value="US">United States</option>
					<option value="UM">United States Minor Outlying Islands</option>
					<option value="UY">Uruguay</option>
					<option value="UZ">Uzbekistan</option>
					<option value="VU">Vanuatu</option>
					<option value="VE">Venezuela, Bolivarian Republic of</option>
					<option value="VN">Viet Nam</option>
					<option value="VG">Virgin Islands, British</option>
					<option value="VI">Virgin Islands, U.S.</option>
					<option value="WF">Wallis and Futuna</option>
					<option value="EH">Western Sahara</option>
					<option value="YE">Yemen</option>
					<option value="ZM">Zambia</option>
					<option value="ZW">Zimbabwe</option>
				</select>
			</div>
	    
			<div class="input-item">
		    	<label for="city">*Πόλη:</label>
		    	<input type="text" id="city" name="city" pattern=".{2,20}" required>
		    	<span id="address_error" class="verification_error"></span>
		    	<span class="map-btn" id="map-btn">Show place on map</span>
			</div>
	    
			<div class="input-item">
		    	<label for="profession">*Επάγγελμα:</label>
		    	<input type="text" id="profession" name="profession" pattern=".{3,15}" required>
			</div>
	    
			<div class="input-item">
		    	<label for="interests">Ενδιαφέροντα:</label>
		    	<textarea id="interests" name="interests" maxlength="100"></textarea>
			</div>
	    
			<div class="input-item">
		    	<label for="more_info">Γενικές Πληροφορίες:</label>
		    	<textarea id="more_info" name="more_info" maxlength="500"></textarea>
			</div>
			<button class="submit active" onclick="submitFunc();" type="submit">Εγγραφή</button>
	    </div>
	    <input type="radio" name="record_enable" id="open" onclick="record_handler()">Open
	    <input type="radio" id="close" name="record_enable" onclick="record_handler()">Close

		<div id="record" class="record">
		  <video id='video' width='640' height='480' autoplay></video>
		  <input type='button' id="snap" value ='Take Photo'>
		  <input type='button' id="upload" value ='Upload Image'>
		  <canvas id='canvas' width='640' height='480'></canvas>
		  <script>
		    faceRec.init();
		  </script>
		</div>
    </div>`;
}/*
function previewFile(){
            var c = document.getElementById("canvas");
       var preview = document.getElementById('preview'); //selects the query named img
       var file    = document.querySelector('input[type=file]').files[0]; //sames as here
       var reader  = new FileReader();

       reader.onloadend = function () {
           preview.src = reader.result;
           dataurl = reader.result;
            var ctx = c.getContext("2d");
            var img = document.getElementById("preview");
            ctx.drawImage(img, 10, 10);
       }

       if (file) {
           reader.readAsDataURL(file); //reads the data as a URL
       } else {
           preview.src = "";
       }
       alert("asdf"+c.toDataURL('image/jpeg', 1.0));
       $("#record").html("<img src='"+ c.toDataURL('image/png', 1.0) +"' >");
  }*/

