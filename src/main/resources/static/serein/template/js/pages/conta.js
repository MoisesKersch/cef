let lat = usuario.latitude;
let lon = usuario.longitude;

$(document).ready(function () {
    initializeMap()
    setLatLonInput(lat, lon);
});

function isEmpty(obj) {
    return Object.keys(obj).length === 0;
}

function initializeMap() {
    var map = L.map("map").setView([lat, lon], 14);
    L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
        attribution: 'Copyright © 2019 Fernando Moises And Master Taylor. Todos os Direitos Reservados.'
    }).addTo(map);

    var markerGroup = L.layerGroup().addTo(map);

    var latlng = {
        lat: lat,
        lng: lon
    };

    L.marker(latlng).addTo(markerGroup);
}






