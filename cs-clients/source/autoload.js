import 'svgxuse';
import 'custom-event-polyfill';

import Anchors from './base/scripts/anchors';

import Header from './blocks/dev/header';
// import Banner from './blocks/dev/banner';

import Clients from './blocks/dev/index/clients';
import Reviews from './blocks/dev/index/reviews';
import Services from './blocks/dev/services';
import News from './blocks/dev/news';

import Popup from './blocks/dev/popup';

require('./autoload.scss');

new Anchors();

new Popup();

new Header();
new Clients();
new Reviews();
new Services();
const news = new News();

window.initSliderNews = news.fillSlider.bind(news);
// new Banner();
