
 document.addEventListener("DOMContentLoaded", () => {
 window.map = L.map("map2").setView([20.6597, -103.3496], 10); // Vista inicial sobre Jalisco

  // Capa base de OpenStreetMap
  L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
    attribution: '&copy; OpenStreetMap contributors',
  }).addTo(window.map);});

var idReporte;
const seccion = document.getElementById('actualizacion');
const tblUsr = document.getElementById('descripcion');
function setIdReporte(id) {
    idReporte = id;
}

$('#tabla-usuario').on('click', '.btn-editar', function() {
    const id = $(this).data('id');
    seccion.style.display = 'block'; // Mostrar la sección de actualización
    seccion.scrollIntoView({ behavior: 'smooth' }); // Desplazarse suavemente a la sección de actualización
    setIdReporte(id); 

    const url = `http://localhost:7512/api/reporte/${id}`;

    fetch(url, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    })
    .then(res => res.json())
    .then(data => {
      llenarFormulario(data);
    })
    .catch(console.error);
});

function llenarFormulario(data) {
    document.getElementById("servicio-act").value = data.servicio;
    document.getElementById("municipio-act").value = data.idMunicipio

    // Activar evento
    document.getElementById("municipio-act").dispatchEvent(new Event('change'));
    document.getElementById("servicio-act").dispatchEvent(new Event('change'));


    document.getElementById("fecha-act").value = data.fechaReporte;
    document.getElementById("colonia-act").value = data.colonia;
    document.getElementById("calle-act").value = data.calle;
    document.getElementById("numero-act").value = data.numero;
    document.getElementById("codigoPostal-act").value = data.codigoPostal;
    document.getElementById("descripcion-act").value = data.descripcion;


    setTimeout(() => {
        const selectAutoridad = document.getElementById("autoridad-act");
        selectAutoridad.value = data.idAutoridad; // Asignar el valor de la autoridad
    }, 300); // Esperar 1 segundo antes de asignar el valor
    const marcador = L.marker([data.latitud, data.longitud]).addTo(window.map);
                  marcador.bindPopup(`<strong>Reporte #${data.id}</strong><br>${data.descripcion}`);
               window.map.setView([data.latitud, data.longitud], 15);
                document.getElementById("latitud-act").value = data.latitud;
                document.getElementById("longitud-act").value = data.longitud;


}

document.getElementById("formulario-actualizacion").addEventListener("submit", function(event) {
    event.preventDefault();

    const actualizacion = {
        idUsuario: document.body.dataset.id,
        idAutoridad: document.getElementById("autoridad-act").value,
        municipio: document.getElementById("municipio-act").value,
        colonia: document.getElementById("colonia-act").value,
        calle: document.getElementById("calle-act").value,
        numero: document.getElementById("numero-act").value,
        codigoPostal: document.getElementById("codigoPostal-act").value,
        fecha: document.getElementById("fecha-act").value,
        descripcion: document.getElementById("descripcion-act").value,
        latitud: document.getElementById("latitud-act").value,
        longitud: document.getElementById("longitud-act").value

    };
    console.log(actualizacion)
    fetch(`http://localhost:7512/api/reporte/${idReporte}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(actualizacion)
    })
    .then(response => response.json())
    .then(data => {
        this.reset(); // Limpiar el formulario después de enviar
        showPopupACT(data); // Mostrar el popup con el id del reporte creado
        document.getElementById("municipio-act").dispatchEvent(new Event('change'));
        document.getElementById("servicio-act").dispatchEvent(new Event('change'));
        tblUsr.scrollIntoView({ behavior: 'smooth' });
        setTimeout(() => {
            seccion.style.display = 'none'; // Ocultar la sección de actualización
        }, 1000); // Esperar 1 segundo antes de limpiar el valor
    })
    .catch(error => console.error("Error:", error));
});

function showPopupACT(id) {
    
    const popup = document.getElementById("popup-actualizar");

    const message = popup.querySelector(".popup-message");

    if (id == null) {
        message.textContent = "Error al actualizar el reporte"; // Mensaje personalizado si lo necesitas
    }
    else{
        message.textContent = `El reporte con id: ${id}, se ha actualizado correctamente`;
    }
    popup.showModal(); // Mostrar el popup
    setInterval(() => {
        popup.close(); // Cerrar el popup después de 3 segundos
    }, 5000); // Cambia el tiempo según tus necesidades
}