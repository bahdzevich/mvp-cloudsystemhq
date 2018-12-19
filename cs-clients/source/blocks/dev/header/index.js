class Header {
  constructor() {
    this.header = document.querySelector('.dev-header');
    this.headerBtn = document.querySelector('.dev-header__btn-menu');

    if (this.header && window.innerWidth >= 1024) {
      this.scrolledPage();
    }

    if (this.header) {
      this.headerBtn.addEventListener('click', (event) => {
        event.preventDefault();
        if (!this.header.classList.contains('dev-header--open')) {
          this.header.classList.add('dev-header--open');
          this.headerBtn.classList.add('dev-header__btn-menu--open');
          document.body.style.overflow = 'hidden';
        } else {
          this.header.classList.remove('dev-header--open');
          this.headerBtn.classList.remove('dev-header__btn-menu--open');
          document.body.style.overflow = '';
        }
      });
    }
  }

  scrolledPage() {
    if (this.header.classList.contains('dev-header--main')) {
      this.checkScroll();

      document.addEventListener('scroll', () => {
        this.checkScroll();
      });
    }
  }

  checkScroll() {
    const scroll = window.pageYOffset;

    if (scroll < 50) {
      this.header.classList.add('dev-header--transparent');
    } else {
      this.header.classList.remove('dev-header--transparent');
    }
  }
}

export default Header;
