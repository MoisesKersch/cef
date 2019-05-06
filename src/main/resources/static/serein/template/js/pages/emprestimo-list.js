var table;
var getListUrl  = contextPath + "app/emprestimo/list";
var addUrl = contextPath + "app/emprestimo";
var baixaUrl = contextPath + "app/emprestimo/baixa";

$(document).ready(function () {
    table()
    datePicker()
    onClickPesquisar()
})

function onClickPesquisar() {
    $("#pesquisar").click(() => {
        if ($(formId).valid()) {
            table.ajax.reload();
        }
    })
}

function onClickBaixar(id) {
    promtpRemove(id)
}

function datePicker() {
    if ($("#data-inicial").length) {
        $('#data-inicial').datepicker({
            enableOnReadonly: true,
            todayHighlight: true,
            format: 'dd/mm/yyyy'
        } );
    }

    $("#data-inicial").datepicker("update", new Date());

    if ($("#data-final").length) {
        $('#data-final').datepicker({
            enableOnReadonly: true,
            todayHighlight: true,
            format: 'dd/mm/yyyy'
        } );
    }

    $("#data-final").datepicker("update",  new Date (moment(new Date()).add(360, 'days')) );
}

function table() {
    table = $(tableId).DataTable({
        ajax: {
            type: "POST",
            url: getListUrl,
            data: function() {
                return getData();
            },
            dataSrc:""
        },
        language: {
            url: contextPath + "resources/serein/template/vendors/js/datatables/translation/Portuguese-Brasil.json"
        },
        columns: getColumns(),
        columnDefs: getColumnDefs(),
        dom: 'Bfrtip',
        select: true,
        order: [
            [1, 'asc']
        ]
    });
}

function getData() {
    var data = $(formId).serializeObject();
    data.dataInicial = new Date( moment(data.dataInicial, "DD-MM-YYYY"));
    data.dataFinal = new Date( moment(data.dataFinal, "DD-MM-YYYY"));
    return data;
}

function getButtons() {
    return  [ {
        text: ' <i class="mdi mdi-account-plus btn-icon-prepend"></i> Adicionar',
        className: 'btn-sm btn btn-primary round  ',
        action: function(e, dt, node, config) {
            window.location.href =  addUrl;
        },
        enabled: true
    }, {
            text: ' <i class="mdi mdi-tooltip-edit btn-icon-prepend"></i> Editar',
            className: 'btn-sm btn btn-dark round  box-shadow-2 px-2',
            action: function(e, dt, node, config) {
                var obj = dt.row( { selected: true } ).data();
                window.location.href = addUrl + "?id="+obj.id;
            },
            enabled: false
    }, {
            text: ' <i class="mdi mdi-account-remove btn-icon-prepend"></i> Remover',
            className: 'btn-sm btn btn-danger round  box-shadow-2 px-2',
            action: function(e, dt, node, config) {
                var obj = dt.row( { selected: true } ).data();
                promtpRemove(obj.id)
            },
            enabled: false
    }];
}

function promtpRemove(id) {
        Swal.fire({
            type: 'warning',
            html: HtmlSw("Confirmação", "Tem Certeza que deseja dar baixar nessa parcela?"),
            showCloseButton: true,
            showCancelButton: true,
            focusConfirm: true,
            confirmButtonText: "Sim",
            cancelButtonText:  "Não",
            confirmButtonAriaLabel: 'Thumbs up, great!',
        }).then((result) => {
            if (result.dismiss === undefined)
                baixaUpdate(id)
        })
}

function baixaUpdate(id) {
    $.ajax({
        type : "POST",
        url : baixaUrl,
        data: {
            id: id
        },
        success: function (obj) {
            if (obj === true) {
                table.ajax.reload();
                Swal.fire({
                    type: 'success',
                    html: HtmlSw("Sucesso!", "Registro baixado com sucesso."),
                    focusConfirm: true,
                    confirmButtonAriaLabel: 'Thumbs up, great!',
                })
            } else {
                Swal.fire({
                    type: 'error',
                    html: HtmlSw("Error!", "Desculpe, mas não foi possível dar baixa nesse registro!"),
                    focusConfirm: true,
                    confirmButtonAriaLabel: 'Thumbs up, great!',
                })
            }
        }
    })
}

function getColumns() {
    return [{
        title: "id",
        data: "id"
    }, {
        title: "Funcionário",
        data: "funcionario",
        render: (val) => {
            return val.nome;
        }
    }, {
        title: "Vencimento",
        data: "vencimento",
        render: (val) => {
            return formatDate(new Date(val));
        }
    }, {
        title: "Valor Parcela",
        data: "valor",
        render: (val) => {
            return val == undefined ? "R$ 0,00" : floatToBrl(val);
        }
    }, {
        title: "Valor Pago",
        data: "valorPago",
        render: (val) => {
            return val == undefined ? "R$ 0,00" : floatToBrl(val);
        }
    }, {
        title: "Opções",
        render: (data, type, row)  => {
            return baixa(row.id);
        }
    }];
}
function floatToBrl(value) {
    return value.toLocaleString("pt-BR", { style: "currency" , currency:"BRL"});
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

function getColumnDefs() {
    return [{
        targets: [0],
        visible: false
    }, {
        className: "text-capitalize text-truncate",
        targets: "_all"
    }];
}

function baixa(id) {
    return  `<button class="btn btn-outline-primary" onclick="onClickBaixar(${id})">
                <i class="mdi mdi-briefcase-download text-primary"></i>Baixar
             </button>`;
}

function validate() {
    $(formId).validate({
        rules: {
        },
        messages: {
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
