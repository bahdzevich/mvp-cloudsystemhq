import 'svgxuse';
import 'custom-event-polyfill';

import Anchors from './base/scripts/anchors';
import Images from './base/scripts/images';

import Header from './blocks/dev/header';
// import Banner from './blocks/dev/banner';

import Clients from './blocks/dev/index/clients';
import Reviews from './blocks/dev/index/reviews';
import News from './blocks/dev/news';

import Popup from './blocks/dev/popup';

require('./autoload.scss');

new Anchors();
new Images();

new Popup();

new Header();
new Clients();
new Reviews();
const news = new News();

window.initSliderNews = news.fillSlider.bind(news);
// new Banner();
