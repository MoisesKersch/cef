const saveUrl = 'save';
const errorMessage = 'Ocorreu um erro ao tentar salvar o registro.';
const confirmButton = 'Entrar no Sistema';
const cancelButton = 'Cadastrar Outro Usuário';

$(document).ready(function() {

});

function save() {
    var data = JSON.stringify($(formId).serializeObject());
    var obj = saveFireSw('<h1>Finalizar Registro</h1>', 'Clique abaixo para continuar.', saveUrl, data);
}

function saveFireSw(title, text, url, data) {
    Swal.fire({
        html: HtmlSw(title, text),
        showLoaderOnConfirm: true,
        confirmButtonClass: 'btn btn-primary btn-min-width btn-glow mr-1 mb-1',
        confirmButtonText: 'Enviar',
        buttonsStyling: false,
        preConfirm: function() {
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

    }).then( function(obj) {
        return new Promise(resolve => {
            if (obj.value != undefined) {
                obj = obj.value;
                updateToken(obj.token)
                if (!obj.hasError) {
                    successAlert(obj, title, text, resolve, confirmButton, cancelButton)
                } else {
                    errorSwRegister(obj.error.error, text, obj)
                }
            }
        })

    }).then(function (r) {
        if (r) {
            window.location.href = contextPath + "dashboard";
        } else {
            window.location.href = contextPath + "freely/register";
        }
    })
}

function errorSwRegister(title, text, obj) {
    Swal.fire({
        type: 'error',
        html: HtmlSw(title, text),
        showCloseButton: true,
        showCancelButton: true,
        focusConfirm: false,
        confirmButtonText:
            '<i class="fa fa-thumbs-up"></i> Tentar Novamente!',
        confirmButtonAriaLabel: 'Thumbs up, great!',
        cancelButtonText:
            '<i class="fa fa-thumbs-down"></i> Sair para Tela de Login',
        cancelButtonAriaLabel: 'Thumbs down',
    }).then((result) => {
        if (result) {
            isReturning = true;
            if (title.indexOf('e-mail') != -1) {
                $(".steps-validation").steps("setStep", 0);
                $(formId).validate().showErrors({email: 'Email já esta sendo utilizado no sistema'})
            } else {
                $(formId).validate().showErrors({login: 'Login já esta sendo utilizado no sistema'})
            }
        } else {

        }
    })
}

function validate() {
    $(formId).validate({
        onsubmit: true,
        onkeyup: false,
        onclick: false,
        onfocusout: false,
        rules : {
            countryIsoCode : {
                valueNotEquals: true
            },
            passwordConfirm : {
                equalTo : "#password"
            },
            taxid: {
                required: false,
                cpfcnpjandbrazil: true
            },
        },
        messages: {
            email: {
                remote: "Email já está cadastrado no sistema"
            },
            login: {
                remote: "Login já está cadastrado no sistema"
            }
        },
        errorClass : 'help-block',
        errorElement : 'div',
        success : function(label, element) {
            label.parent().removeClass('error')
        },
        highlight : function(element, errorClass, validClass) {
            $(element).parent().addClass('error')
        },
        errorPlacement : function(error, element) {
            if (element.prop('id') === 'addressCountryIsoCode') {
                error.appendTo(element.parent());
            } else {
                error.insertAfter(element);
            }
        }
    });
}