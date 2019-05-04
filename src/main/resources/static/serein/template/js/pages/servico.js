const saveUrl = contextPath + 'app/servico';
const errorMessage = 'Ocorreu um erro ao tentar salvar o registro.';
const confirmButton = 'Listar';
const cancelButton = 'Cadastrar Outro Serviço';

const postSaveTitle = 'Sucesso!';
const postSaveText = 'Registro Salvo Com sucesso';

let lat = usuario.latitude;
let lon = usuario.longitude;

$(document).ready(function () {
    wizzard();
    datePicker();
    timePicker();
    startSelect2()
    initializeMap()
    setAddress(lat, lon);
    setLatLonInput(lat, lon);
});

async function setAddress(latitude, longitude) {
    let enderecoObj = await getAddressBasedOnCoordinates(latitude, longitude);
    let endereco = `${ !isEmpty(enderecoObj.staddress) ? enderecoObj.staddress : ''}, ${!isEmpty(enderecoObj.postal) ? enderecoObj.postal : ''}, ${!isEmpty(enderecoObj.city) ? enderecoObj.city : ''}, ${!isEmpty(enderecoObj.state) ? enderecoObj.state : ''}`;
    $("#endereco").text(endereco);
}

function isEmpty(obj) {
    return Object.keys(obj).length === 0;
}

function getAddressBasedOnCoordinates(latitude, longitude) {
    return new Promise(function(resolve, reject) {
        $.ajax({
            type : "GET",
            url : `https://geocode.xyz/${latitude},${longitude}?geoit=json`,
            contentType: 'application/json',
            dataType:'jsonp',
            responseType:'application/json',
            xhrFields: {
                withCredentials: false
            },
            headers: {
                'Access-Control-Allow-Credentials' : true,
                'Access-Control-Allow-Origin':'*',
                'Access-Control-Allow-Methods':'GET',
                'Access-Control-Allow-Headers':'application/json',
            },
            success : function(obj) {
                resolve(obj);
            },
            error: function (request, status, error) {
                resolve(obj);
            }
        })
    });
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

    map.on("click", function (event) {
        markerGroup.clearLayers()
        lat = event.latlng.lat;
        lon = event.latlng.lng;

        setAddress(lat, lon);
        setLatLonInput(lat, lon);
        L.marker(event.latlng).addTo(markerGroup);
    });
}
function startSelect2() {
    $('.tipo-servico').select2();
    $('.animal').select2();
}

function datePicker() {
    $('.datepicker').datepicker({
        orientation: "bottom",
        pickerPosition: "top-left",
        format: 'dd/mm/yyyy'
    });
}

function wizzard() {
    var form = $(formId);
    form.children("div").steps({
        headerTag: "h3",
        bodyTag: "section",
        transitionEffect: "slideLeft",
        onStepChanging: function(e, t, i) {
            setFinalForm()
            window.dispatchEvent(new Event('resize'));
            return (form.validate().settings.ignore = ":disabled,:hidden", form.valid());
        },
        onFinished: function (event, currentIndex) {
            save()
        },
        labels: {
            cancel: "Cancelar",
            current: "Próximo",
            pagination: "Paginação",
            finish: "Confirmar",
            next: "Próximo",
            previous: "Voltar",
            loading: "Carregando ..."
        }
    });
}
function setFinalForm() {
    $("#tipo-servico-form").text( $('.tipo-servico').select2('data')[0].text +"/"+$('.animal').select2('data')[0].text )
    $("#local-form").text( $(formId).serializeObject().aonde )
    $("#data-hora-form").text( $(formId).serializeObject().date + "/" + $(formId).serializeObject().time )

    if ($(formId).serializeObject().aonde != "Casa do Prestador de Serviço") {
        setLatLonInput(lat, lon);
        $("#endereco-form").text( $("#endereco").text() )
    } else {
        setLatLonInput("", "")
        $("#endereco-form").text(  "Casa do Prestador de Serviço" )
    }
}

function setLatLonInput(lat, lng) {
    $("#latitude").val(lat)
    $("#longitude").val(lng)
}

function timePicker() {
    $('input.timepicker').timepicker();
}

function save() {
    let date = $(formId).serializeObject().date + "   " + $(formId).serializeObject().time;
    $("#data").val(new Date(Date.parse(date)))
    var data = $(formId).serializeObject();
    var obj = saveFireSw('<h1>Tem certeza que deseja continuar?</h1>', '', saveUrl, data);
}

function saveFireSw(title, text, url, data) {
    Swal.fire({
        html: HtmlSw(title, text),
        showLoaderOnConfirm: true,
        confirmButtonClass: 'btn btn-primary btn-min-width btn-glow mr-1 mb-1',
        confirmButtonText: 'Enviar',
        buttonsStyling: false,
        preConfirm: function () {
            return savePromise(url, data);
        }
    }).then(function (obj) {
        return new Promise(resolve => {
            if (obj.value != undefined) {
                obj = obj.value;
                if (!obj.hasError) {
                    successAlert(obj, postSaveTitle, postSaveText, resolve, confirmButton, cancelButton)
                } else {
                    errorSwRegister(obj.error.error, '', obj)
                }
            }
        })

    }).then(function (r) {
        if (r) {
            window.location.href = contextPath + "app/servico/list";
        } else {
            location.reload();
        }
    })
}

function savePromise(url, data) {
    return new Promise(function(resolve, reject) {
        $.ajax({
            url: url,
            data: data,
            type: "POST",
            success: (obj) => {
                resolve(obj);
            }
        })
    });
}

function validate() {
    $(formId).validate({
        rules: {

        },
        messages: {

        },
        errorElement: 'div',
        success: function (label, element) {
            label.parent().removeClass('has-danger')
        },
        errorPlacement: function (label, element) {
            label.addClass('mt-2 text-danger');
            label.insertAfter(element);
        },
        highlight: function (element, errorClass) {
            $(element).parent().addClass('has-danger')
            $(element).addClass('form-control-danger')
        }
    });
}
