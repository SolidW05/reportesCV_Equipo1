document.addEventListener("DOMContentLoaded", function () {
  const map = L.map("map").setView([20.6597, -103.3496], 10); // Vista inicial sobre Jalisco

  // Capa base de OpenStreetMap
  L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
    attribution: '&copy; OpenStreetMap contributors',
  }).addTo(map);

  let marker;

  // Función para actualizar ubicación y campos del formulario
  async function actualizarUbicacion(lat, lng) {
    if (marker) {
      marker.setLatLng([lat, lng]);
    } else {
      marker = L.marker([lat, lng]).addTo(map);
    }

    document.getElementById("latitud").value = lat;
    document.getElementById("longitud").value = lng;

    try {
      const response = await fetch(
        `https://nominatim.openstreetmap.org/reverse?format=json&lat=${lat}&lon=${lng}&accept-language=es`
      );
      const data = await response.json();

      document.getElementById("colonia").value =
        data.address.suburb ||
        data.address.neighbourhood ||
        data.address.city_district ||
        "No disponible";

      document.getElementById("calle").value =
        data.address.road ||
        data.address.street ||
        "No disponible";

      document.getElementById("codigoPostal").value =
        data.address.postcode || "No disponible";

      const municipioNombre =
        data.address.city ||
        data.address.town ||
        data.address.village ||
        data.address.county ||
        "No disponible";

      const selectMunicipio = document.getElementById("municipio");
      let municipioEncontrado = false;

      for (let option of selectMunicipio.options) {
        if (
          option.text.trim().toLowerCase() === municipioNombre.trim().toLowerCase()
        ) {
          option.selected = true;
          municipioEncontrado = true;
          break;
        }
      }

      if (!municipioEncontrado) {
        console.log("Municipio no encontrado en el select");
      }

    } catch (error) {
      console.error("Error al obtener dirección:", error);
    }
  }

  // Evento: click sobre el mapa
map.on('click', function (e) {
    const lat = e.latlng.lat;
    const lng = e.latlng.lng;

    actualizarUbicacion(lat, lng);
});


  // Control de búsqueda (geocoder)
  L.Control.geocoder({
    defaultMarkGeocode: false,
  })
    .on("markgeocode", function (e) {
      const latlng = e.geocode.center;
      map.setView(latlng, 16);
      actualizarUbicacion(latlng.lat, latlng.lng);
    })
    .addTo(map);

  // Redirigir mapa según municipio seleccionado en el <select>
  document.getElementById("municipio").addEventListener("change", async function () {
    const municipio = this.options[this.selectedIndex].text;
    if (!municipio) return;

    try {
      const response = await fetch(
        `https://nominatim.openstreetmap.org/search?format=json&q=${municipio},Jalisco,México&limit=1`
      );
      const data = await response.json();

      if (data.length > 0) {
        const lat = parseFloat(data[0].lat);
        const lng = parseFloat(data[0].lon);
        map.setView([lat, lng], 14);
        actualizarUbicacion(lat, lng);
      } else {
        console.warn("No se encontró el municipio:", municipio);
      }
    } catch (error) {
      console.error("Error al buscar el municipio:", error);
    }
  });
});
