// Script para aplicar data-labels a las tablas

export function aplicarDataLabels(tablaId) {
  const tabla = document.getElementById(tablaId);
  if (!tabla) return;

  const encabezados = Array.from(tabla.querySelectorAll("thead th")).map(
    (th) => th.innerText.trim()
    );

    tabla.querySelectorAll("tbody tr").forEach((fila) => {
    fila.querySelectorAll("td").forEach((celda, index) => {
      celda.setAttribute("data-label", encabezados[index]);
      });
    });

}


