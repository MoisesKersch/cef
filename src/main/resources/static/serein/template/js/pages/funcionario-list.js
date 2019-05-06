var table;
var getListUrl  = "app/funcionario/list";
var addUrl = contextPath + "app/funcionario";
var deleteUrl = contextPath + "app/funcionario/delete/";

$(document).ready(function () {
    table()
})

function table() {
    table = $(tableId).DataTable({
        ajax: {
            type: "POST",
            url: contextPath + getListUrl,
            dataSrc: ""
        },
        language: {
            url: contextPath + "resources/serein/template/vendors/js/datatables/translation/Portuguese-Brasil.json"
        },
        columns: getColumns(),
        buttons: getButtons(),
        columnDefs: getColumnDefs(),
        initComplete: function(settings, json) {
            selectTableConfig(this.DataTable());
        },
        dom: 'Bfrtip',
        select: true,
        order: [
            [1, 'asc']
        ]
    });
}

function selectTableConfig(table) {
    genericTableId = getTableId(table);
    table.on( 'select deselect', function () {
        selectDeselectTable(table)
    });
    $(genericTableId + ' tbody').on(
        'dblclick',
        'tr',
        function() {
            table.rows(this).select()
            var obj = table.row({
                selected : true
            }).data();
            window.location.href = contextPath
                + "/admin/socio/socio?id=" + obj.id;
        });
}

function getTableId(table) {
    return '#'+table.tables().nodes().to$().attr('id');
}

function selectDeselectTable(table) {
    var selectedRows = table.rows( { selected: true } ).count();
    table.buttons( [1, 2] ).enable( selectedRows > 0 && selectedRows < 2 );
}

function getButtons() {
    return  [ {
        text: ' <i class="mdi mdi-account-plus btn-icon-prepend"></i> Adicionar',
        className: 'btn-sm btn btn-primary round  ',
        action: function(e, dt, node, config) {
            window.location.href =  addUrl;
        },
        enabled: true
    },
        {
            text: ' <i class="mdi mdi-tooltip-edit btn-icon-prepend"></i> Editar',
            className: 'btn-sm btn btn-dark round  box-shadow-2 px-2',
            action: function(e, dt, node, config) {
                var obj = dt.row( { selected: true } ).data();
                window.location.href = addUrl + "?id="+obj.id;
            },
            enabled: false
        },
        {
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
            html: HtmlSw("Remover", "Tem Certeza que deseja remover esse registro?"),
            showCloseButton: true,
            showCancelButton: true,
            focusConfirm: true,
            confirmButtonText: "Sim",
            cancelButtonText:  "Não",
            confirmButtonAriaLabel: 'Thumbs up, great!',
        }).then((result) => {
            if (result.dismiss === undefined)
                remove(id)
        })
}

function remove(id) {
    $.ajax({
        type : "DELETE",
        url : deleteUrl + id,
        success: function (obj) {
            if (obj === true) {
                table.row({
                    selected: true
                }).remove();
                table.draw();
                Swal.fire({
                    type: 'success',
                    html: HtmlSw("Removido!", "Registro removido com sucesso."),
                    focusConfirm: true,
                    confirmButtonAriaLabel: 'Thumbs up, great!',
                })
            } else {
                Swal.fire({
                    type: 'error',
                    html: HtmlSw("Error!", "Desculpe, mas não foi possível remover esse registro!"),
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
        title: "Nome",
        data: "nome"
    }, {
        title: "CPF",
        data: "cpf"
    }, {
        title: "Setor",
        data: "setor"
    }, {
        title: "Data de Admissão",
        data: "dataAdmissao",
        render: (val) => {
            return formatDate(new Date(val));
        }
    }];
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

function getWatchAndRemove() {
    return  `<button class="btn btn-light">
                <i class="mdi mdi-eye text-primary"></i>Editar
             </button>
             <button class="btn btn-light">
                <i class="mdi mdi-close text-danger"></i>Remover
             </button>`;
}
