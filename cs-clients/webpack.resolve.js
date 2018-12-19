const path = require('path');

module.exports = function () {
  return {
    resolve: {
      extensions: ['.js', '.json', '.sass'],
      modules: [
        path.resolve(__dirname), path.resolve(__dirname, 'node_modules'),
      ],
      alias: {
        app: path.resolve('./source'),
        static: path.resolve('./source/static'),
        assets: path.resolve('./source/assets'),
        TweenMax: path.resolve('node_modules', './gsap/src/uncompressed/TweenMax.js'),
        TimelineMax: path.resolve('node_modules', './gsap/src/uncompressed/TimelineMax.js'),
        ScrollMagic: path.resolve('node_modules', './scrollmagic/scrollmagic/uncompressed/ScrollMagic.js'),
        scrollGsap: path.resolve('node_modules', './scrollmagic/scrollmagic/uncompressed/plugins/animation.gsap.js'),
      },
    },
  };
};
