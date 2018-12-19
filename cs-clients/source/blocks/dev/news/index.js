import Swiper from 'swiper';

class News {
  constructor() {
    this.newsBlock = document.querySelector('.dev-news');
    this.slider = document.querySelector('.dev-news__items');
    this.wrap = document.querySelector('.dev-news__content .swiper-wrapper');
    this.btnAddNews = document.querySelector('.dev-news__btn--add');
    this.optionsDate = {
      era: 'narrow',
      year: 'numeric',
      month: 'long',
      day: 'numeric',
      weekday: 'long',
      timezone: 'UTC',
      hour: 'numeric',
      minute: 'numeric',
      second: 'numeric',
    };
    this.data = [];
    this.length = 3;
    this.flagAll = false;
  }

  fillSlider(data) {
    this.data = data;
    if (this.wrap) {
      if (this.newsBlock.classList.contains('dev-news--all')) {
        this.length = 18;
        this.flagAll = true;
      }
      this.wrap.innerHTML = '';
      data.forEach((item, i) => {
        if (i < this.length) {
          this.wrap.appendChild(this.addNews(
            item.image,
            item.title,
            item.date,
            item.description,
            item.url,
          ));
        }
      });

      if (this.btnAddNews) {
        this.btnAddNews.addEventListener('click', (event) => {
          event.preventDefault();

          this.addMore();
          this.checkAddBtn();
        });
      }
    }

    this.initSlider();
  }

  addMore() {
    for (let i = this.length; i < this.length + 3; i += 1) {
      if (this.data[i]) {
        this.wrap.appendChild(this.addNews(
          this.data[i].image,
          this.data[i].title,
          this.data[i].date,
          this.data[i].description,
          this.data[i].url,
        ));
      }
    }

    this.length = this.length + 3;
  }

  checkAddBtn() {
    if (this.length >= this.data.length) {
      this.btnAddNews.style.display = 'none';
    }
  }

  addNews(image, title, date, description, url) {
    const news = document.createElement('div');
    const nDate = new Date(date).toLocaleString('en-US', this.optionsDate);
    news.classList.add('dev-news__item');
    news.classList.add('swiper-slide');
    news.innerHTML = `<div class="dev-news__img" style="background-image: url(${image});"></div>
                      <div class="dev-news__description">
                        <p class="text dev-news__date">${nDate}</p>
                        <div class="h4 dev-news__title">${this.constructor.reduceText(title)}</div>
                        <p class="text dev-news__text">${(this.flagAll) ? this.constructor.reduceText(description) : description}</p>
                        <a class="dev-news__link text" href="${url}">More</a>
                      </div>`;

    return news;
  }

  static reduceText(str, numLength) {
    if (str.length > numLength) {
      return `${str.substring(0, numLength)}...`;
    }

    return str;
  }

  initSlider() {
    let apiSlider;

    const enabledSwiper = () => {
      apiSlider = new Swiper(this.slider, {
        pagination: {
          el: '.dev-news__pag',
          type: 'bullets',
          dynamicBullets: false,
          clickable: true,
        },
        slidesPerView: 1,
        spaceBetween: 20,
        spead: 700,
        loopAdditionalSlides: 22,
        loop: false,
      });
    };

    if (window.innerWidth < 768) enabledSwiper();

    window.addEventListener('resize', () => {
      if (window.innerWidth >= 768) {
        if (apiSlider) apiSlider.destroy(false, true);
      } else if (!apiSlider) {
        enabledSwiper();
      } else if (window.innerWidth < 768 && apiSlider.destroyed === true) {
        enabledSwiper();
      }
    });
  }
}
export default News;
