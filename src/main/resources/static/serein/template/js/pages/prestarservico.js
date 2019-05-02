const saveUrl = contextPath + 'app/animal';
const errorMessage = 'Ocorreu um erro ao tentar salvar o registro.';
const confirmButton = 'Listar';
const cancelButton = 'Cadastrar Outro Pet';

const postSaveTitle = 'Sucesso!';
const postSaveText  =  'Registro Salvo Com sucesso';

$(document).ready(function () {

});


function save() {
    var data = JSON.stringify($(formId).serializeObject());
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
            return fetch(url, {
                method: 'POST',
                headers: {
                    "Content-Type": "application/json",
                },
                body: data
            })
                .then(response => response.json())
                .catch((obj) => {
                    Swal.insertQueueStep({
                        type: 'error',
                        title: 'erro'
                    })
                    return false;
                })
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
            window.location.href = contextPath + "app/animal/list";
        } else {
            location.reload();
        }
    })
}

function validate() {
    $(formId).validate({
        submitHandler: function (form) {
            save()
        },
        rules: {
            senha2: {
                equalTo: "#senha"
            },
            email : {
                required: true,
                remote : {
                    url : contextPath + 'allowed/isemail',
                    type : "GET",
                    data : {
                        email : function() {
                            return $("#email").val()
                        }
                    },
                    dataFilter : function(response) {
                        if (!response)
                            return true;
                        return false;
                    }
                }
            },
        },
        messages: {
            email: {
                remote: "Email já está cadastrado no sistema"
            }
        },
        errorElement : 'div',
        success : function(label, element) {
            label.parent().removeClass('has-danger')
        },
        errorPlacement: function(label, element) {
            label.addClass('mt-2 text-danger');
            label.insertAfter(element);
        },
        highlight: function(element, errorClass) {
            $(element).parent().addClass('has-danger')
            $(element).addClass('form-control-danger')
        }
    });
}
