function obtenerMunicipios() {
    let url = "http://localhost:7512/api/municipio";
    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        const select = document.querySelectorAll(".municipio");
        select.forEach(select => {
            data.forEach(element => {
                const option = document.createElement("option");
                option.value = element.idMunicipio;
                option.text = element.municipio;
                select.appendChild(option);
            });
        });

    })
    .catch(error => {
        console.error('There was a problem with the fetch operation:', error);
    });
}

window.addEventListener('load', obtenerMunicipios);