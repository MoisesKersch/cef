const saveUrl = contextPath + 'app/emprestimo';
const listUrl = contextPath + 'app/emprestimo/list';
const detalhesUrl = contextPath + 'app/emprestimo/detalhes';
const errorMessage = 'Ocorreu um erro ao tentar salvar o registro.';
const confirmButton = 'Listar';
const cancelButton = 'Cadastrar Outro Empréstimo';

const isFuncionarioUrl = contextPath + 'app/emprestimo/isfuncionario';


const postSaveTitle = 'Sucesso!';
const postSaveText  =  'Registro Salvo Com sucesso';

$(document).ready(function () {
    validate()
    datePicker();
    startSelect2()
    mask();
    table()
    onClickSaveEmprestimo()
    tableLengthChanged()
    onDetalhesClick()
});

function onDetalhesClick() {
    $("#detalhes").click(() => {
        if ( $(formId).valid() ) {
            table.ajax.reload();
        }
    })
}

function onClickSaveEmprestimo() {
    $("#save").click(() => {
        save()
    })
}

function tableLengthChanged() {
    $(tableId).on('draw.dt', () => {
        if (table.data().count() > 0) {
            $("#save").prop("disabled", false)
        } else {
            $("#save").prop("disabled", true)
        }
    } );
}

function floatToBrl(value) {
    return value.toLocaleString("pt-BR", { style: "currency" , currency:"BRL"});
}

function table() {
    table = $(tableId).DataTable({
        ajax: {
            type: "POST",
            url: detalhesUrl,
            data: function() {
                return getData();
            },
            dataSrc:""
        },
        language: {
            url: contextPath + "resources/serein/template/vendors/js/datatables/translation/Portuguese-Brasil.json"
        },
        columns: getColumns(),
        dom: 'Bfrtip',
        select: true,
        order: [
            [1, 'asc']
        ]
    });
}

function formatDate(date) {
    var dd = date.getDate();
    var mm = date.getMonth() + 1; // January is 0!

    var yyyy = date.getFullYear();
    if (dd < 10) {
        dd = '0' + dd;
    }
    if (mm < 10) {
        mm = '0' + mm;
    }
    return today = dd + '/' + mm + '/' + yyyy;
}

function getColumns() {
    return [ {
        title: "Número",
        data: "numero"
    }, {
        title: "Valor",
        data: "valor",
        render: (val) => {
            return floatToBrl(val);
        }
    }, {
        title: "Data de Vencimento",
        data: "vencimento",
        render: (val) => {
            return formatDate(new Date(val));
        }
    }];
}

function mask() {
    $('.brl').mask("#.##0,00", {reverse: true});
}

function getData() {
    var data = $(formId).serializeObject();
    data.valorEmprestimo = brlToFloat(data.valorEmprestimo);
    data.dataOperacao = new Date( moment(data.dataOperacao, "DD-MM-YYYY"));
    data.dataPrimeiraParcela = new Date( moment(data.dataPrimeiraParcela, "DD-MM-YYYY"));
    return data;
}

function startSelect2() {
    $('.funcionario').select2();
    $('.quantidadeParcela').select2();
}

function datePicker() {
    if ($("#data-operacao").length) {
        $('#data-operacao').datepicker({
            enableOnReadonly: true,
            todayHighlight: true,
            format: 'dd/mm/yyyy'
        } );
    }
    $("#data-operacao").datepicker("update", new Date());

    if ($("#data-primeira-parcela").length) {
        $('#data-primeira-parcela').datepicker({
            enableOnReadonly: true,
            todayHighlight: true,
            format: 'dd/mm/yyyy'
        } );
    }
    $("#data-primeira-parcela").datepicker("update",  new Date (moment(new Date()).add(1, 'days')) );
}

function save() {
    if ($(formId).valid()) {
        var data = $(formId).serializeObject();
        data.valorEmprestimo = brlToFloat(data.valorEmprestimo);
        data.dataOperacao = new Date( moment(data.dataOperacao, "DD-MM-YYYY"));
        data.dataPrimeiraParcela = new Date( moment(data.dataPrimeiraParcela, "DD-MM-YYYY"));
        var obj = saveFireSw('<h1>Tem certeza que deseja continuar?</h1>', '', saveUrl, data);
    }
}

function brlToFloat(value) {
    return isNaN(value) == false ? parseFloat(value) :   parseFloat(value.replace("R$","").replace(".","").replace(",","."));
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
            funcionario : {
                required: true,
                remote : {
                    url : isFuncionarioUrl,
                    type : "GET",
                    data : {
                        id : function() {
                            return $(".funcionario").select2("data")[0].id
                        }
                    },
                    dataFilter : function(response) {
                        var response = jQuery.parseJSON(response);
                        return !response;
                    }
                }
            },
            dataPrimeiraParcela: {
                validDate: true,
                isNotTodaysDate: true
            },
            dataOperacao: {
                validDate: true
            }
        },
        messages: {
            funcionario: {
                remote: "Esse funcionário ja possuí um empréstimo em aberto."
            }
        },
        errorElement : 'div',
        success : function(label, element) {
            label.parent().removeClass('has-danger')
        },
        errorPlacement: function(label, element) {
            label.addClass('mt-2 text-danger');
            if (element[0].nodeName === 'SELECT') {
                label.appendTo(element.parent());
            } else {
                label.insertAfter(element);
            }
        },
        highlight: function(element, errorClass) {
            $(element).parent().addClass('has-danger')
            $(element).addClass('form-control-danger')
        }
    });
}

