import TweenLite from 'gsap';

require('gsap/ScrollToPlugin');

class Anchors {
  constructor() {
    this.anchors = [].slice.call(document.querySelectorAll('.core-anchor'));
    this.header = document.querySelector('.mob-header');
    this.headerBtn = document.querySelector('.mob-header__btn-menu');

    this.anchors.forEach((item) => {
      const block = document.querySelector(item.getAttribute('href'));

      item.addEventListener('click', (event) => {
        event.preventDefault();
        if (this.header) this.header.classList.remove('mob-header--open');
        if (this.headerBtn) this.headerBtn.classList.remove('mob-header__btn-menu--open');
        document.body.style.overflow = '';

        if (block) {
          setTimeout(() => {
            TweenLite.killAll();
            this.constructor.initAnim(block);
          }, 100);
        }
      });
    });
  }

  static initAnim(block) {
    const scroll = window.pageYOffset;
    const point = (block.getBoundingClientRect().top + scroll) - 120;
    const pointMath = Math.round(point);

    TweenLite.to(window, 1.5, {
      ease: Power2.easeOut,
      scrollTo: {
        y: pointMath,
        autoKill: false,
      },
    });
  }
}
export default Anchors;
