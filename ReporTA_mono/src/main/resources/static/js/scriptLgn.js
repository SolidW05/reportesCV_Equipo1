document.addEventListener("DOMContentLoaded", () => {
  const container = document.querySelector(".container");
  const btnSignIn = document.getElementById("btn-sign-in");
  const btnSignUp = document.getElementById("btn-sign-up");

  const form = document.getElementById("formRegistro");
  const mensajeExito = document.getElementById("registro-exitoso");

  // Alternar formularios (desktop y móvil)
  function checkMobile() {
    return window.innerWidth <= 768;
  }

  function toggleForms(event) {
    if (checkMobile()) {
      container.classList.toggle("toggle");
    } else {
      if (event.target === btnSignIn) {
        container.classList.remove("toggle");
      } else if (event.target === btnSignUp) {
        container.classList.add("toggle");
      }
    }
  }

  btnSignIn.addEventListener("click", (e) => {
    e.preventDefault();
    toggleForms(e);
  });

  btnSignUp.addEventListener("click", (e) => {
    e.preventDefault();
    toggleForms(e);
  });

  // Registro al backend
  form.addEventListener("submit", function (e) {
    e.preventDefault();

    const datos = {
      nombre: document.getElementById("nombre").value,
      email: document.getElementById("email").value,
      password: document.getElementById("passwordC").value,
      tipoUsuario: form.tipoUsuario.value
    };

    fetch("/api/usuarios/registro", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(datos)
    })
      .then(async (response) => {
        const data = await response.json();

        if (response.ok) {
          mensajeExito.style.display = "block";
          mensajeExito.textContent = "✅ Registro exitoso";
          form.reset();

          setTimeout(() => {
            mensajeExito.style.display = "none";
          }, 3000);
        } else {
          if (data && data.error) {
            alert("❌ " + data.error);
          } else {
            alert("❌ Error al registrar.");
          }
        }
      })
      .catch((error) => {
        console.error("Error:", error);
        alert("❌ Ocurrió un error al registrar.");
      });
  });

  // Reacción al redimensionar ventana (opcional si quieres ajustar visualmente)
  window.addEventListener("resize", () => {
    if (checkMobile()) {
      // Puedes hacer ajustes adicionales aquí si deseas
    }
  });
});
