var table;
var getListUrl = "app/funcionario/list";

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

    // $(genericTableId).on( 'length.dt', function ( e, settings, newlen ) {
    //     len = newlen;
    //     table.ajax.reload();
    // } );
}

function getTableId(table) {
    return '#'+table.tables().nodes().to$().attr('id');
}

function selectDeselectTable(table) {
    var selectedRows = table.rows( { selected: true } ).count();
    table.button( 1 ).enable( selectedRows > 0 && selectedRows < 2 );
}

function getButtons() {
    return  [ {
        text: ' <i class="mdi mdi-file-check btn-icon-prepend"></i> Adicionar',
        className: 'btn-sm btn-primary btn-icon-text',
        action: function(e, dt, node, config) {
            window.location.href =  contextPath + getListUrl;
        },
        enabled: true
    },

        {
            text: '  Adicionar',
            className: 'btn-sm  btn-danger',
            action: function(e, dt, node, config) {
                window.location.href =  contextPath + getListUrl;
            },
            enabled: false
        },
        {
            text: ' <i class="mdi mdi-upload btn-icon-prepend"></i> Adicionar',
            className: 'btn-sm  btn-danger',
            action: function(e, dt, node, config) {
                window.location.href =  contextPath + getListUrl;
            },
            enabled: false
        }];
}

function promtpRemove(id) {
    swal({
        title: "Remover",
        text: "Tem certeza que deseja remover esse registro?",
        icon: "warning",
        buttons: {
            cancel: {
                text: "Não",
                value: null,
                visible: true,
                className: "",
                closeModal: true,
            },
            confirm: {
                text: "Sim",
                value: true,
                visible: true,
                className: "",
                closeModal: true
            }
        }
    })
        .then((isConfirm) => {
            if (isConfirm) {
                remove(id)
            } else {
                swal("Cancelado", "Registro não removido!", "error");
            }
        });
}

function remove(id) {
    $.ajax({
        type: "POST",
        data: {
            id: id
        },
        url: contextPath + "/admin/socio/post/remove",
        success: function (obj) {
            if (obj === true) {
                table.row({
                    selected: true
                }).remove();
                table.draw();
                swal("Removido!", "Registro removido com sucesso.", "success");
            } else {
                console.log(obj);
                swal("Erro ao Remover!", "Desculpe, mas não foi possível remover esse registro!", "error");
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
