class Popup {
  constructor() {
    const popup = document.querySelector('.dev-popup');
    const closePopups = [].slice.call(document.querySelectorAll('.dev-popup__close'));

    if (popup) {
      popup.addEventListener('click', (e) => {
        if (popup.classList.contains('dev-popup--open')) {
          const popupContentWrap = popup.querySelector('.dev-popup__content-wrap--open');
          const popupContent = popup.querySelector('.dev-popup__content-wrap--open .dev-popup__content');
          const target = e.target || e.srcElement;
          if (target !== popupContent && !this.isChildOf(target, popupContent)) {
            popup.classList.remove('dev-popup--open');
            if (popupContentWrap) popupContentWrap.classList.remove('dev-popup__content-wrap--open');
            document.body.style.overflow = '';
          }
        }
      });

      closePopups.forEach((item) => {
        item.addEventListener('click', (event) => {
          event.preventDefault();

          popup.classList.remove('dev-popup--open');
          item.parentNode.parentNode.classList.remove('dev-popup__content-wrap--open');
          document.body.style.overflow = '';
        });
      });
    }
  }

  isChildOf(child, parent) {
    let result = false;
    if (child.parentNode === parent) {
      result = true;
    } else if (child.parentNode === null) {
      result = false;
    } else {
      result = this.isChildOf(child.parentNode, parent);
    }
    return result;
  }
}
export default Popup;
