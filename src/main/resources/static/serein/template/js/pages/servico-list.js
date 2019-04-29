var table;
var getListUrl = "app/servico/list";

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
        // buttons: getButtons(),
        columnDefs: getColumnDefs(),
        dom: 'Bfrtip',
        select: true,
        order: [
            [1, 'asc']
        ]
    });
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
    }
    , {
        title: "Local",
        data: "aonde"
    }, {
        title: "Descriçao",
        data: "descricao"
    }, {
        title: "Data",
        data: "data",
        render: function(data, type, full) {
            return moment(data).format("DD/MM/YY hh:mm:ss");
        }
    }, {
        title: "Tipo Serviço",
        data: "tipoServico",
        render: function(data, type, full) {
            return data.nome;
        }
    }];
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
