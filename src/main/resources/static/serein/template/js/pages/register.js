const saveUrl = contextPath + 'allowed/save';
const errorMessage = 'Ocorreu um erro ao tentar salvar o registro.';
const confirmButton = 'Entrar no Sistema';
const cancelButton = 'Cadastrar Outro Usuário';

$(document).ready(function () {
    validate()
    typeHead()
    onChangeCepField()
    mask()
});


function mask() {
    $('.phone_with_ddd').mask('(00) 0000-00000');
    $('.cep').mask('00000-000');
}

function save() {
    var data = JSON.stringify($(formId).serializeObject());
    var obj = saveFireSw('<h1>Finalizar Registro</h1>', 'Clique abaixo para continuar.', saveUrl, data);
}

function onChangeCepField() {
    $("#cep").change(function () {
        getCepData()
    })
}

function typeHead() {
    cidades = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.obj.whitespace,
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        remote: {
            url: contextPath + 'allowed/findbycidadenome#%QUERY',
            wildcard: '%QUERY',
            transport: function (opts, onSuccess, onError) {
                var url = opts.url.split("#")[0];
                var query = opts.url.split("#")[1];
                $.ajax({
                    url: url,
                    data: "cidadeNome=" + query,
                    type: "POST",
                    success: onSuccess,
                    error: onError,
                })
            },
            filter: function (data) {
                return data;
            }
        }
    })
    cidades.initialize();
    $('.typeahead-basic').typeahead(null, {
        name: 'Cidades',
        display: function (data) {
            return data.cidadeNome + ' - ' + data.estadoCodigo
        },
        limit: Infinity,
        templates: {
            empty: [
                '<div class="empty-message">',
                'Nem um registro encontrado',
                '</div>'
            ].join('\n'),
            suggestion: function (data) {
                return "<div class='omniboxresult'><div class='caseName'>" + data.cidadeNome + " - " + data.estadoNome + "</div></div>";
            }
        },
        source: cidades.ttAdapter()
    }).on('typeahead:select', function (ev, obj) {
        $('#cidadeId').val(obj.cidadeId);
    });
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
                    successAlert(obj, title, text, resolve, confirmButton, cancelButton)
                } else {
                    errorSwRegister(obj.error.error, text, obj)
                }
            }
        })

    }).then(function (r) {
        if (r) {
            login()
            debugger
            console.log(contextPath + "app/dashboard")
            window.location.href = contextPath + "app/dashboard";
        } else {
            window.location.href = contextPath + "allowed/register";
        }
    })
}

function login() {
    $.ajax({
        url: contextPath + "allowed/manuallogin",
        type: "post",
        data: {
            email: $("#email").val(),
            senha: $("#senha").val()
        },
        success: () => {

        }
    });
}

function getCepData() {
    var cep = StringUtils.removeNonNumbers($("#cep").val());
    if(cep.length == 8) {
        try {
            $.ajax({
                type : "GET",
                url : "https://viacep.com.br/ws/" +cep+ "/json/",
                success : function(addr) {
                    findByCidadeNomeAndUf(addr)
                    $("#endereco").val(addr.logradouro)
                },
                error: function (request, status, error) {
                    console.log(request)
                }
            })
        }
        catch(err) {
            console.log(err)
            return 0;
        }
        return 0;
    }
}

function findByCidadeNomeAndUf(addr) {
    $.ajax({
        type: "POST",
        url :  contextPath + 'allowed/findbycidadenomeanduf',
        data: {
            cidadeNome: addr.localidade,
            uf: addr.uf
        },
        success : function(obj) {
            if (obj !== undefined && obj !== '' && obj !== null) {
                $("#cidadeId").val(obj.cidadeId)
                var cidade = obj.cidadeNome+ " - "+obj.estadoCodigo;
                $("#cidades").val(cidade)
                $('.typeahead-basic').typeahead('val', cidade);
            }
        }
    })
}

function addrFields(obj) {
    $("#bairro").val(obj.bairro)
    $("#numero").val(obj.numero)
    $("#rua").val(obj.logradouro)
    $("#complemento").val(obj.complemento)
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
