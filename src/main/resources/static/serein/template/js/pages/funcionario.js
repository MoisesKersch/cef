const saveUrl = contextPath + 'app/funcionario';
const isCpfUrl = contextPath + 'app/funcionario/iscpf';
const listUrl = contextPath + 'app/funcionario/list';
const errorMessage = 'Ocorreu um erro ao tentar salvar o registro.';
const confirmButton = 'Listar';
const cancelButton = 'Cadastrar Outro Funcionário';

const postSaveTitle = 'Sucesso!';
const postSaveText  =  'Registro Salvo Com sucesso';

$(document).ready(function () {
    validate()
    datePicker();
    mask()
});

function mask() {
    $('.cpf').mask('000.000.000-00', {reverse: true});
}

function datePicker() {
    if ($("#data-admissao").length) {
        $('#data-admissao').datepicker({
            enableOnReadonly: true,
            todayHighlight: true,
            format: 'dd/mm/yyyy'
        } );
    }
    $("#data-admissao").datepicker("update", new Date());
}

function save() {
    var data = $(formId).serializeObject();
    data.dataAdmissao = new Date( moment(data.dataAdmissao, "DD-MM-YYYY"));
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
            return savePromise(url, data)
        }
    }).then(function (obj) {
        return new Promise(resolve => {
            if (obj) {
                successAlert(obj, postSaveTitle, postSaveText, resolve, confirmButton, cancelButton)
            } else {
                errorSwRegister(obj, '', obj)
            }
        })

    }).then(function (r) {
        if (r) {
            window.location.href = listUrl;
        } else {
            location.reload();
        }
    })
}

function savePromise(url, data) {
    return new Promise(function(resolve, reject) {
        $.ajax({
            url: saveUrl,
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
        submitHandler: function (form) {
            save()
        },
        rules: {
            cpf : {
                required: true,
                cpf: true,
                remote : {
                    url : isCpfUrl,
                    type : "GET",
                    data : {
                        cpf : function() {
                            return $("#cpf").val()
                        },
                        id : function() {
                            return $("#id").val()
                        }
                    },
                    dataFilter : function(response) {
                        var response = jQuery.parseJSON(response);
                        return !response;
                    }
                }
            },
            dataAdmissao: {
                validDate: true
            }
        },
        messages: {
            cpf: {
                remote: "CPF já está cadastrado no sistema"
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
