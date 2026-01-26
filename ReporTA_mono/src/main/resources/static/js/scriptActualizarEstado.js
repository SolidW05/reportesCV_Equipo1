var idReporte;

export function setIdReporte(id) {
    idReporte = id;
}
 

document.getElementById("formulario-reporte")
.addEventListener("submit", function(event) {
    event.preventDefault();

    const reporteAct = {
        idReporte: idReporte,
        estatus: document.getElementById("Estatus").value,
        observaciones: document.getElementById("observaciones").value
    };

    let url = `http://localhost:7512/api/reporte/actualizar/estatus` 
    console.log(url)
    fetch(url, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(reporteAct)
    })
    .then(response => response.json())
    .then(data => {
        this.reset(); // Limpiar el formulario después de enviar
        showPopupACT(data); // Mostrar el popup de éxito
        const reporteSeccion = document.getElementById("contact");
        document.getElementById("tabla-usuario-reportes").scrollIntoView({
          behavior: "smooth"
        });

        setTimeout(() => {
            reporteSeccion.style.display = 'none'; 
        }, 1000); 
    })
    .catch(error => console.error("Error:", error));
});

function showPopupACT(id) {
    
    const popup = document.getElementById("popup");

    const message = popup.querySelector(".popup-message");

    if (id == null) {
        message.textContent = "Error al actualizar el estatus del reporte"; // Mensaje personalizado si lo necesitas
    }
    else{
        message.textContent = `El reporte con id: ${id}, se ha actualizado su estatus correctamente`;
    }
    popup.showModal(); // Mostrar el popup
    setInterval(() => {
        popup.close(); // Cerrar el popup después de 3 segundos
    }, 5000); // Cambia el tiempo según tus necesidades
}
