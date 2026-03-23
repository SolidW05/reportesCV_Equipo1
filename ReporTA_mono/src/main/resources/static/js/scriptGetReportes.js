import { aplicarDataLabels } from "./scripTablas.js";

// DECLARACIÓN GLOBAL
let modalEliminar = document.getElementById("popup-eliminar");
let tablaTodosDT = null;
let tablaUsuarioDT = null;
let idEliminar = null;
let filaAEliminar = null;

function setIdEliminar(id) {
    idEliminar = id;
}
function setFilaAEliminar(fila) {
    filaAEliminar = fila;
}


function aplicarFiltros(tabla, sufijo) {
    $(`#filtro-estado-${sufijo}`).off('change').on('change', function () {
        tabla.column(sufijo === "usuario"?3 : 2).search(this.value).draw();
    });

    $(`#filtro-municipio-${sufijo}`).off('keyup change').on('keyup change', function () {
        tabla.column(sufijo === "usuario"? 7 : 6).search(this.value).draw();
    });

    $(`#filtro-servicio-${sufijo}`).off('keyup change').on('keyup change', function () {
        tabla.column(sufijo === "usuario"? 10 : 9).search(this.value).draw();
    });
}

function obtenerTodosReportes(tablaID) {
    let url = "http://localhost:7512/api/reporte";
    fetch(url, {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        const formattedData = data.map(element => ([
            element.idReporte,
            element.fechaReporte,
            element.estado,
            element.calle,
            element.numero,
            element.colonia,
            element.municipio,
            element.codigoPostal,
            element.autoridad,
            element.servicio,
            element.nombre,
            element.telefono
        ]));

        if (!tablaTodosDT) {
            tablaTodosDT = $(`#${tablaID}`).DataTable({
                data: formattedData,
                columns: [
                    { title: "ID Reporte" },
                    { title: "Fecha" },
                    { title: "Estado" },
                    { title: "Calle" },
                    { title: "Número" },
                    { title: "Colonia" },
                    { title: "Municipio" },
                    { title: "C.P." },
                    { title: "Autoridad" },
                    { title: "Servicio" },
                    { title: "Encargado" },
                    { title: "Teléfono" }
                ]
            });
            aplicarFiltros(tablaTodosDT, "todos");
        } else {
            tablaTodosDT.clear().rows.add(formattedData).draw();
        }

        aplicarDataLabels(tablaID);
    })
    .catch(error => {
        console.error('Fetch error:', error);
    });
}

function obtenerTodosReportesPorUsr(tablaID) {
    const idUsr = document.body.dataset.id;
    let url = `http://localhost:7512/api/reporte/usuarios/${idUsr}`;
    fetch(url, {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        const formattedData = data.map(element => ([
            `
            <button class="btn-editar" data-id="${element.idReporte}">Editar</button>
            <button class="btn-eliminar" data-id="${element.idReporte}">Eliminar</button>
            `,
            element.idReporte,
            element.fechaReporte,
            element.estado,
            element.calle,
            element.numero,
            element.colonia,
            element.municipio,
            element.codigoPostal,
            element.autoridad,
            element.servicio,
            element.nombre,
            element.telefono
        ]));

        if (!tablaUsuarioDT) {
            tablaUsuarioDT = $(`#${tablaID}`).DataTable({
                data: formattedData,
                columns: [
                    { title: "Acciones" },
                    { title: "ID Reporte" },
                    { title: "Fecha" },
                    { title: "Estado" },
                    { title: "Calle" },
                    { title: "Número" },
                    { title: "Colonia" },
                    { title: "Municipio" },
                    { title: "C.P." },
                    { title: "Autoridad" },
                    { title: "Servicio" },
                    { title: "Encargado" },
                    { title: "Teléfono" },
                ]
            });
            aplicarFiltros(tablaUsuarioDT, "usuario");
        } else {
            tablaUsuarioDT.clear().rows.add(formattedData).draw();
        }

        aplicarDataLabels(tablaID);
    })
    .catch(error => {
        console.error('Fetch error:', error);
    });

    accionesBotones(tablaID);
}

function accionesBotones(tablaID) {
    
    $('#' + tablaID).on('click', '.btn-eliminar', function() {
        const id = $(this).data('id');

        setIdEliminar(id);  
        setFilaAEliminar($(this).closest('tr')); // Guardar la fila a eliminar

        console.log('Eliminar reporte:', idEliminar);
        console.log('Fila a eliminar:', filaAEliminar); // Verificar la fila a eliminar
        document.getElementById("popup-message").textContent = `¿Está seguro de que desea eliminar el reporte con ID ${id}?`;
        modalEliminar.showModal();  
    });
}

document.getElementById("eliminar-reporte").addEventListener("click", function() {
    eliminarFila(); // Llamar a la función para eliminar la fila
    modalEliminar.close(); // Cerrar el modal después de eliminar
});

function eliminarFila() {
    fetch(`http://localhost:7512/api/reporte/${idEliminar}`, {
        method: 'Delete',
        headers: { 'Content-Type': 'application/json' }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al eliminar el reporte'); // Captura errores como el 500
        }
        filaAEliminar.remove(); // Elimina la fila si la respuesta es exitosa
        return response.json();
    })
    .then(data => {
        console.log('Datos del reporte:', data);
        showPopupDEL(data); // Muestra el pop-up si la eliminación es exitosa
    })
    .catch(error => {
        console.error('Error en la solicitud:', error);
        showPopupDEL(null, error.message); // Muestra el error en el pop-up
    });
}

function showPopupDEL(id, errorMessage) {
    const popup = document.getElementById("popup-eliminar-respuesta");
    const message = popup.querySelector(".popup-message");

    if (errorMessage) {
        message.textContent = `Error: ${errorMessage}`; // Muestra el mensaje de error
    } else if (id == null) {
        message.textContent = "Error al eliminar el reporte"; // Mensaje personalizado si no hay id
    } else {
        message.textContent = `El reporte con id: ${id}, se ha eliminado correctamente`; // Mensaje de éxito
    }
    
    popup.showModal(); // Muestra el pop-up

    setInterval(() => {
        popup.close(); // Cierra el pop-up después de 5 segundos
    }, 5000); // Cambia el tiempo según tus necesidades
}


window.addEventListener('load', () => {
    obtenerTodosReportes("tabla-todos");
    obtenerTodosReportesPorUsr("tabla-usuario");

    setInterval(() => {
        obtenerTodosReportes("tabla-todos");
        obtenerTodosReportesPorUsr("tabla-usuario");
    }, 5000);
});

