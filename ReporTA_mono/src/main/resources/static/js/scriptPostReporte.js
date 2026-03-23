document.getElementById("formulario-reporte").addEventListener("submit", function(event) {
    event.preventDefault();

    const usuario = {
        idUsuario: document.body.dataset.id,
        idAutoridad: document.getElementById("autoridad").value,
        municipio: document.getElementById("municipio").value,
        colonia: document.getElementById("colonia").value,
        calle: document.getElementById("calle").value,
        numero: document.getElementById("numero").value,
        codigoPostal: document.getElementById("codigoPostal").value,
        fecha: document.getElementById("fecha").value,
        descripcion: document.getElementById("descripcion").value,
        latitud: document.getElementById("latitud").value,
        longitud: document.getElementById("longitud").value

    };

    fetch(`http://localhost:7512/api/reporte`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(usuario)
    })
    .then(response => response.json())
    .then(data => {
        const autoridadSelect = document.getElementById("autoridad");
        autoridadSelect.disabled = true; // Desactivar el select de autoridad
        this.reset(); // Limpiar el formulario después de enviar
        showPopup(data); // Mostrar el popup con el id del reporte creado
    })
    .catch(error => console.error("Error:", error));
});

function showPopup(id) {
    
    const popup = document.getElementById("popup-crear");

    const message = popup.querySelector(".popup-message");

    if (id == null) {
        message.textContent = "Error al crear el reporte"; // Mensaje personalizado si lo necesitas
    }
    else{
    message.textContent = `El reporte se ha creado con el id:  ${id}`;
    }
    popup.showModal(); // Mostrar el popup
    setInterval(() => {
        popup.close(); // Cerrar el popup después de 3 segundos
    }, 5000); // Cambia el tiempo según tus necesidades
}



