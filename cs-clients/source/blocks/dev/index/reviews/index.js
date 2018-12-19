import Swiper from 'swiper';

class Reviews {
  constructor() {
    new Swiper('.dev-index-reviews__slider', {
      pagination: {
        el: '.dev-index-reviews__pag',
        type: 'bullets',
        clickable: true,
      },
      // autoplay: {
      //   delay: 5000,
      //   disableOnInteraction: true,
      // },
      slidesPerView: 1,
      spead: 700,
      loop: false,
    });
  }
}

export default Reviews;
