const saveUrl = contextPath + 'app/servico';
const errorMessage = 'Ocorreu um erro ao tentar salvar o registro.';
const confirmButton = 'Listar';
const cancelButton = 'Cadastrar Outro Serviço';

const postSaveTitle = 'Sucesso!';
const postSaveText = 'Registro Salvo Com sucesso';

$(document).ready(function () {
    wizzard();
    datePicker();
    timePicker();
    startSelect2()
});

function startSelect2() {
    $('.tipo-servico').select2();
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
    $("#tipo-servico-form").val( $('.tipo-servico').select2('data')[0].text)
    $("#local-form").val($(formId).serializeObject().aonde)
    $("#data-hora-form").val( $(formId).serializeObject().date + "/" + $(formId).serializeObject().time )
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
