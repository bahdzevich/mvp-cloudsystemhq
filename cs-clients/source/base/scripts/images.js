import Blazy from 'blazy';

class Images {
  constructor() {
    new Blazy({
      selector: '.b-lazy',
      src: 'data-src',
    });
  }
}
export default Images;
