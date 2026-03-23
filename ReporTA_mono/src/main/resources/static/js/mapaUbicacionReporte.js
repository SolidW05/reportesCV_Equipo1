/*document.addEventListener("DOMContentLoaded", () => {
  const mapa = L.map("mapa-reporte").setView([20.6597, -103.3496], 12); // Guadalajara

  L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
    attribution: "Map data © OpenStreetMap contributors"
  }).addTo(mapa);

  fetch('/api/reporte')

    .then(response => response.json())
    .then(reportes => {
      reportes.forEach((reporte) => {
        const marcador = L.marker([reporte.latitud, reporte.longitud]).addTo(mapa);
        marcador.bindPopup(`<strong>Reporte #${reporte.id}</strong><br>${reporte.descripcion}`);
      });

      if (reportes.length > 0) {
        mapa.setView([reportes[0].latitud, reportes[0].longitud], 15);
      }
    })
    .catch(error => {
      console.error("Error al cargar los reportes:", error);
    });
});*/
