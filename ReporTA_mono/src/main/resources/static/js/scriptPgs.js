document.addEventListener('DOMContentLoaded', () => {
  const navLinks = document.querySelectorAll(".nav-menu .nav-link");
  const menuOpenButton = document.querySelector("#menu-open-button");
  const menuCloseButton = document.querySelector("#menu-close-button");

  console.log("¿Botón encontrado?", menuOpenButton);

  if (menuOpenButton) {
    menuOpenButton.addEventListener('click', () => {
      document.body.classList.toggle("show-mobile-menu");
    });
  }

  if (menuCloseButton) {
    menuCloseButton.addEventListener('click', () => menuOpenButton.click());
  }

  navLinks.forEach(link => {
    link.addEventListener('click', () => menuOpenButton.click());
  });

  // Verificar que Swiper está disponible antes de usarlo
  if (typeof Swiper !== "undefined") {
    const swiper = new Swiper('.slider-wrapper', {
      loop: true,
      grabCursor: true,
      spaceBetween: 25,

      pagination: {
        el: '.swiper-pagination',
        clickable: true,
        dynamicBullets: true,
      },

      navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
      },

      breakpoints: {
        0: {
          slidesPerView: 1,
        },
        768: {
          slidesPerView: 2,
        },
        1024: {
          slidesPerView: 3,
        },
      }
    });
  } else {
    console.error("Swiper no está definido. Asegúrate de que has cargado swiper-bundle.min.js antes de este script.");
  }
});

