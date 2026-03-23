document.addEventListener("DOMContentLoaded", function () {


  window.marker = null;

  // Función para actualizar ubicación y campos del formulario
  async function actualizarUbicacion2(lat, lng) {
    if (window.marker) {
      window.marker.setLatLng([lat, lng]);
    } else {
      window.marker = L.marker([lat, lng]).addTo(window.map);
    }

    document.getElementById("latitud-act").value = lat;
    document.getElementById("longitud-act").value = lng;

    try {
      const response = await fetch(
        `https://nominatim.openstreetmap.org/reverse?format=json&lat=${lat}&lon=${lng}&accept-language=es`
      );
      const data = await response.json();

      document.getElementById("colonia-act").value =
        data.address.suburb ||
        data.address.neighbourhood ||
        data.address.city_district ||
        "No disponible";

      document.getElementById("calle-act").value =
        data.address.road ||
        data.address.street ||
        "No disponible";

      document.getElementById("codigoPostal-act").value =
        data.address.postcode || "No disponible";

      const municipioNombre =
        data.address.city ||
        data.address.town ||
        data.address.village ||
        data.address.county ||
        "No disponible";

      const selectMunicipio = document.getElementById("municipio-act");
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
window.map.on('click', function (e) {
    const lat = e.latlng.lat;
    const lng = e.latlng.lng;

    actualizarUbicacion2(lat, lng);
});


  // Control de búsqueda (geocoder)
  const mapaP =
  L.Control.geocoder({
    defaultMarkGeocode: false,
  })
    .on("markgeocode", function (e) {
      const latlng = e.geocode.center;
      window.map.setView(latlng, 16);
      actualizarUbicacion2(latlng.lat, latlng.lng);
    })
    .addTo(window.map);

  // Redirigir mapa según municipio seleccionado en el <select>
  document.getElementById("municipio-act").addEventListener("change", async function () {
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
        window.map.setView([lat, lng], 14);
        actualizarUbicacion2(lat, lng);
      } else {
        console.warn("No se encontró el municipio:", municipio);
      }
    } catch (error) {
      console.error("Error al buscar el municipio:", error);
    }
  });
});
