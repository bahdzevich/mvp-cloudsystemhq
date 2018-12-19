import Swiper from 'swiper';

class Clients {
  constructor() {
    new Swiper('.dev-index-clients__slider', {
      pagination: {
        el: '.dev-index-clients__pag',
        type: 'bullets',
        clickable: true,
      },
      autoplay: {
        delay: 5000,
        disableOnInteraction: true,
      },
      slidesPerView: 6,
      spead: 700,
      loop: false,
      breakpoints: {
        1023: {
          slidesPerView: 4,
        },
        767: {
          slidesPerView: 2,
        },
      },
    });
  }
}

export default Clients;
