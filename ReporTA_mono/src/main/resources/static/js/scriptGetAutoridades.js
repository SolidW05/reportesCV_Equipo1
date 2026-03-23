// Buscar todos los formularios
const formularios = document.querySelectorAll(".formulario-servicio");

formularios.forEach(formulario => {
    const servicioSelect = formulario.querySelector(".servicio");
    const municipioSelect = formulario.querySelector(".municipio");
    const autoridadSelect = formulario.querySelector(".autoridad");

    function verificarSeleccionYActualizarAutoridades() {
        const servicio = servicioSelect.value;
        const municipio = municipioSelect.value;

        if (servicio && municipio) {
            autoridadSelect.disabled = false;
            const url = `http://localhost:7512/api/autoridad/municipio/${municipio}/servicio/${servicio}`;
            
            fetch(url)
                .then(response => response.json())
                .then(data => {
                    autoridadSelect.innerHTML = '<option value="">Seleccione la autoridad</option>';

                    data.forEach(autoridad => {
                        const option = document.createElement("option");
                        option.value = autoridad.id;
                        option.text = autoridad.autoridad;
                        autoridadSelect.appendChild(option);
                    });
                })
                .catch(error => {
                    console.error("Error al obtener autoridades:", error);
                    autoridadSelect.disabled = true;
                });
        } else {
            autoridadSelect.disabled = true;
            autoridadSelect.innerHTML = '<option value="">Seleccione la autoridad</option>';
        }
    }

    servicioSelect.addEventListener("change", verificarSeleccionYActualizarAutoridades);
    municipioSelect.addEventListener("change", verificarSeleccionYActualizarAutoridades);
});
