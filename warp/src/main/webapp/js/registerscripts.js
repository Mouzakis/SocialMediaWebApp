'use strict';
$(document).ready(function(){
    $("body").on('click', '#register', function(){
        if (document.getElementById("pw")){
        document.getElementById("pw-verify").addEventListener("change", password_check);
        document.getElementById("pw").addEventListener("change", password_check);
        function password_check() {
                if ((document.getElementById("pw").value != "") && (document.getElementById("pw-verify").value != "")) {
                        if (!(document.getElementById("pw").value === document.getElementById("pw-verify").value)) {
                                document.getElementById("pass_mismatch").innerHTML = "Passwords do not match";
                                document.getElementById("pass_mismatch").style.margin = "5px 0 10px";
                        }
                        else {
                                if (document.getElementById("pass_mismatch").innerHTML !== ""){
                                        document.getElementById("pass_mismatch").innerHTML = "";
                                        document.getElementById("pass_mismatch").style.margin = "0";
                                }
                        }
                }
                else if (document.getElementById("pass_mismatch").innerHTML !== ""){
                        document.getElementById("pass_mismatch").innerHTML = "";
                        document.getElementById("pass_mismatch").style.margin = "0";
                }
        };
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
            $("#address").change(location_check);
            $("#city").change(location_check);
            $("#country").change(location_check);
            function location_check() {
                    if (($("#address").val()) && ($("#city").val())) {
                            var nomreq = new XMLHttpRequest();
                            var nomurl = "https://nominatim.openstreetmap.org/?format=json&addressdetails=1&q="+document.getElementById("address").value+"+"+document.getElementById("city").value+"+"+
                                    document.getElementById("country").value+"&format=json&limit=1";
                            nomreq.open("GET", nomurl, true);
                            nomreq.onreadystatechange = function() {
                                    if (nomreq.readyState == 4 && nomreq.status == 200) {
                                            var response = JSON.parse(nomreq.responseText);
                                            console.log(response[0]);
                                            if (response[0] == undefined) {
                                                    document.getElementById("address_error").innerHTML = "Address not found";
                                                    document.getElementById("address_error").style.margin = "5px 0 10px";
                                                    if (document.getElementById("map-btn").style.display === "block"){
                                                        document.getElementById("map-btn").style.display = "none";
                                                    }
                                            }
                                            else {
                                                    if (document.getElementById("address_error").innerHTML !== ""){
                                                            document.getElementById("address_error").innerHTML = "";
                                                            document.getElementById("address_error").style.margin = "0";
                                                    }
                                                    var long = parseFloat(response[0].lon);
                                                    var lati = parseFloat(response[0].lat);
                                                    map.getView().setCenter(ol.proj.transform([long, lati], 'EPSG:4326', 'EPSG:900913'));
                                                    add_map_point(lati, long);
                                                document.getElementById("map-btn").style.display = "block";
                                            }
                                    }
                                    else if (nomreq.status == 400) {
                            console.log('There was an error 400');
                       }
                            };
                            nomreq.send();
                    }
                    else {
                            if (document.getElementById("address_error").innerHTML !== "") {
                                    document.getElementById("address_error").innerHTML = "";
                                    document.getElementById("address_error").style.margin = "0";
                            }
                            document.getElementById("map-btn").style.display = "none";
                    }
            };
            $("#map-btn").click(function(){
                    $("#map").css("z-index", "10");
                    $("#map").animate({
                            opacity: 1
                    }, 1000, function(){

                    });
                    $("#map").addClass("active");
                    $("#esc_map").css("display", "inline-block");
                    $("body").css({
                            "text-align": "center",
                            "overflow": "hidden"
                    });
            });
            $("#esc_map").click(function(){
                    $("#map").css("z-index", "0");
                    $("#map").animate({
                            opacity: 0
                    }, 1000, function(){

                    });
                    $("#map").removeClass("active");
                    $("#esc_map").css("display", "none");
                    $("body").css({
                            "text-align": "left",
                            "overflow": "visible"
                    });
            });
            if (navigator.geolocation) {
                    $("#reversegeo").css("display","block");
                    $("#reversegeo").click(function(){
                                    navigator.geolocation.getCurrentPosition(function(position) {
                                      var nreq = new XMLHttpRequest();
                                            var nurl = "https://nominatim.openstreetmap.org/reverse?format=json&lat="+position.coords.latitude+"&lon="+position.coords.longitude+"&addressdetails=1&zoom=18";
                                            nreq.open("GET", nurl, true);
                                            nreq.onreadystatechange = function() {
                                                    if (nreq.readyState == 4 && nreq.status == 200) {
                                                            var response = JSON.parse(nreq.responseText);
                                                            if (response == undefined) {
                                                                    alert("did not find location!");
                                                                    $("#address_error").html("Address not found");
                                                                    $("#address_error").css('margin', "5px 0 10px");
                                                            }
                                                            else if ((response.address.pedestrian||response.address.road) && response.address.city && response.address.country_code){
                                                                    if (response.address.pedestrian) {
                                                                            $("#address").val(response.address.pedestrian);
                                                                    }
                                                                    else if (response.address.road) {
                                                                            $("#address").val(response.address.road);
                                                                    }
                                                                    $("#city").val(response.address.city);
                                                                    $("#country").val(response.address.country_code.toUpperCase());
                                                                    location_check();
                                                            }
                                                    }
                                            };
                                            nreq.send();
                                    });
                    });
            }
            else {
                    //geo not supported
            }
        }
    });
});
function userCheck(str) {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'UserCheck');
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            if (str === "username") {
                document.getElementById('username-item').innerHTML = xhr.responseText;
                if ($("#username").val() === "") { alert("Username in use") }
            } else {
                document.getElementById('email-item').innerHTML = xhr.responseText;
                if ($("#email").val() === "") { alert("Email in use") }
            }
        } else if (xhr.status != 200) {
            alert('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send("mode="+str+"&username="+$("#username").val()+"&email="+$("#email").val());
}
function submitFunc() {
    var val = true;
    var i;
    for (i = 0; i < 13; i++) {
        if (!$("input")[i].checkValidity()) {
            val = false;
            alert($("input:eq("+i+")").attr("name") + " : " + $("input:eq("+i+")").attr("value") + " is not valid!");
        }
    }
    if (!($("textarea")[0].checkValidity() || $("textarea")[1].checkValidity()) || 
            !($("#pw").val() === $("#pw-verify").val()) ||
            !($("#map-btn").css("display") === "block")) {
        val = false;
    }
    if (val) {
        var gender = $("input[name=gender]:checked").val();
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'Registration');
        xhr.onload = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.getElementById('ajaxContent').innerHTML = xhr.responseText;

            } else if (xhr.status != 200) {
                alert('Request failed. Returned status of '+ xhr.status);
            }
        };
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.send("user_name="+document.getElementById("username").value+"&email="+document.getElementById("email").value
        +"&password="+document.getElementById("pw").value+"&first_name="+document.getElementById("name").value
        +"&last_name="+document.getElementById("lastname").value+"&birth_date="+document.getElementById("birth").value
        +"&gender="+gender+"&country="+document.getElementById("country").value
        +"&town="+document.getElementById("city").value+"&address="+document.getElementById("address").value
        +"&occupation="+document.getElementById("profession").value+"&interests="+document.getElementById("interests").value
        +"&info="+document.getElementById("more_info").value);
    }
}
