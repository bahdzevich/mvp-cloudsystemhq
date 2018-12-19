import { TimelineMax } from 'gsap';
import scrollMagic from 'scrollmagic';

require('scrollmagic/scrollmagic/uncompressed/plugins/animation.gsap.js');

class Animation {
  constructor() {
    this.scrollController = new scrollMagic.Controller();
    this.animationsElement = [].slice.call(document.querySelectorAll('*[data-anim]'));
    this.fadeElem = undefined;
    this.scenes = [];

    if (window.innerWidth >= 1024) this.initAnimate();
  }

  initAnimate() {
    this.animationsElement.forEach((elem) => {
      const durationScene = 0.7;
      const tl = new TimelineMax();
      const type = elem.getAttribute('data-anim');
      let delayScene = 0.2;
      if (elem.getAttribute('data-delay')) delayScene = (+elem.getAttribute('data-delay') / 1000);
      tl.delay(delayScene);
      tl.set(elem, { willChange: 'transform' });

      this.fadeElem = new scrollMagic.Scene({
        triggerElement: elem,
        triggerHook: 'onEnter',
        reverse: false,
        tweenChanges: true,
      }).setTween(tl);

      if (type === 'fade') {
        tl.fromTo(elem, durationScene, { opacity: 0 }, { opacity: 1 });
      }
      if (type === 'fade-up') {
        tl.fromTo(elem, durationScene, { opacity: 0, y: 100 }, { opacity: 1, y: 0 });
      }
      if (type === 'fade-down') {
        tl.fromTo(elem, durationScene, { opacity: 0, y: -100 }, { opacity: 1, y: 0 });
      }
      if (type === 'fade-left') {
        tl.fromTo(elem, durationScene, { opacity: 0, x: 100 }, { opacity: 1, x: 0 });
      }
      if (type === 'fade-right') {
        tl.fromTo(elem, durationScene, { opacity: 0, x: -100 }, { opacity: 1, x: 0 });
      }
      if (type === 'fade-up-left') {
        tl.fromTo(elem, durationScene, { opacity: 0, y: 100, x: 100 }, { opacity: 1, y: 0, x: 0 });
      }
      if (type === 'fade-circle') {
        tl.fromTo(elem, durationScene, { opacity: 0, scale: 0 }, { opacity: 1, scale: 1 });
      }
      this.scenes.push(this.scrollController.addScene(this.fadeElem));
    });

    this.animationsElement.forEach((item) => {
      item.classList.remove('animate');
    });
  }
}

export default Animation;
