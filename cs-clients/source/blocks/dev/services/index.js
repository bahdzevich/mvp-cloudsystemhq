import PerfectScrollbar from 'perfect-scrollbar';

class Services {
  constructor() {
    this.listItems = [].slice.call(document.querySelectorAll('.dev-services__item'));
    this.popups = [].slice.call(document.querySelectorAll('.dev-services__popup'));

    this.listItems.forEach((item) => {
      const listWrap = item.querySelector('.dev-services__popup');
      const list = item.querySelector('.dev-services__popup-list');
      const btn = item.querySelector('.dev-services__more');

      const apiScroll = new PerfectScrollbar(list, {
        wheelSpeed: 1,
        suppressScrollX: true,
        wheelPropagation: true,
        minScrollbarLength: 20,
        maxScrollbarLength: 200,
      });

      if (btn) {
        btn.addEventListener('click', (event) => {
          console.log('click', listWrap.classList.contains('dev-services__popup--open'));
          event.preventDefault();
          if (listWrap.classList.contains('dev-services__popup--open')) {
            listWrap.classList.remove('dev-services__popup--open');
          } else {
            listWrap.classList.add('dev-services__popup--open');
            apiScroll.update();
            // console.log('add', listWrap.classList.contains('dev-services__popup--open'));
          }
        });
      }
    });

    if (this.listItems.length > 0) {
      const body = document.querySelector('body');
      body.addEventListener('click', (e) => {
        const target = e.target || e.srcElement;
        if (!this.isChildOf(target, 'dev-services__popup', body) &&
            !target.parentNode.classList.contains('dev-services__more')) {
          this.closePopups();
        }
      });
    }
  }

  closePopups() {
    this.popups.forEach((item) => {
      item.classList.remove('dev-services__popup--open');
    });
  }

  isChildOf(child, className, parent) {
    let result = false;

    if (child.classList.contains(className)) {
      result = child;
    } else if (child.parentNode === parent) {
      result = false;
    } else if (child.parentNode === null) {
      result = false;
    } else {
      result = this.isChildOf(child.parentNode, className, parent);
    }
    return result;
  }
}

export default Services;
