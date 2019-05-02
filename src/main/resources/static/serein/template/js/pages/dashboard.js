const url = contextPath + 'app/updatelocation';

$(document).ready(function () {
    updateLocation()
});

function updateLocation() {
    navigator.geolocation.getCurrentPosition(function(position) {
        setLocation(position.coords.latitude, position.coords.longitude);
    });
}

function setLocation(latitude, longitude) {
    $.ajax({
        url: url,
        data: {
            "latitude": latitude,
            "longitude": longitude
        },
        type: "POST",
        success: (obj) => {
            console.log(obj)
        }
    })
}

