const path = require('path'),
  ExtractTextPlugin = require('extract-text-webpack-plugin'),
  HtmlWebpackPlugin = require('html-webpack-plugin'),
  CopyWebpackPlugin = require('copy-webpack-plugin'),
  glob = require("glob"),
  webpack = require('webpack'),
  StyleLintPlugin = require('stylelint-webpack-plugin'),
  pugIncludeGlob = require('pug-include-glob');

const UglifyJsPlugin = require('uglifyjs-webpack-plugin');
const SpritePlugin = require('svg-sprite-loader/plugin');
const resolveLoader = require('./webpack.resolve');

module.exports = function (env) {

  const pluginsOptions = [
    new SpritePlugin(),
    new StyleLintPlugin(),
    new webpack.NamedModulesPlugin(),
    new webpack.HotModuleReplacementPlugin(),
    new ExtractTextPlugin({
      filename: 'assets/styles/style.css',
      disable: env.production === 'true' ? false : true
    }),
    new CopyWebpackPlugin([
      {
        from: './static',
        to: './',
        ignore: ['*.md']
      }
    ]),
    // new webpack.optimize.ModuleConcatenationPlugin(),
    // new webpack.NoEmitOnErrorsPlugin()
  ];
  if (env.production === 'true') {
    pluginsOptions.push(new UglifyJsPlugin({
      extractComments:true
    }));
  }

  let pages = glob.sync(__dirname + '/source/pages/*.pug');
  pages.forEach(function (file) {
    let base = path.basename(file, '.pug');
    pluginsOptions.push(
      new HtmlWebpackPlugin({
        filename: './' + base + '.html',
        template: './pages/' + base + '.pug',
        inject: true
      })
    )
  });

  const config = {
    entry: "./autoload.js",
    context: __dirname + '/source/',
    output: {
      path: path.resolve(__dirname, "dist"),
      filename: 'assets/scripts/[name]' + '.bundle' + '.js',
      chunkFilename: "[id].js",
      //publicPath: "../assets/"
    },
    module: {
      rules: [
        {
          enforce: "pre",
          test: /\.js$/,
          exclude: /node_modules/,
          loader: "eslint-loader",
        },
        {
          test: /\.js$/,
          loader: 'babel-loader',
          exclude: /node_modules\/(?!(dom7|ssr-window|gsap|swiper)\/).*/,
          query: {
            presets: ['env']
          }
        }, {
          test: /\.(sass|scss|css)$/,
          use: ExtractTextPlugin.extract({
            fallback: [
              {
                loader: 'style-loader'
              }
            ],
            use: [{
              loader: 'css-loader',
              options: {
                importLoaders: 1
              }
            }, {
              loader: 'group-css-media-queries-loader'
            }, {
              loader: 'postcss-loader',
              options: {
                plugins: function () {
                  return [
                    require('autoprefixer')({browsers: "last 5 versions"})
                  ];
                }
              }
            }, {
              loader: 'sass-loader'
            }, {
              loader: 'import-glob-loader'
            }],
            publicPath: (env.production === 'true') ? '/' : __dirname + '/source'
          })
        },
        {
          enforce: 'pre',
          test: /\.pug$/,
          exclude: /node_modules/,
          loader: 'pug-lint-loader',
          options: require('./.pug-lintrc.js'),
        },
        {
          test: /\.pug$/,
          loader: 'pug-loader',
          options: {
            plugins:[pugIncludeGlob()]
          }
        }, {
          test: /\.(woff|woff2|ttf|otf|eot?)(\?.+)?$/,
          loader: 'url-loader',
          options: {
            name: 'assets/fonts/[name].[ext]',
            limit: 10000
          }
        },
        {
          test: /\.svg$/,
          loader: 'svg-sprite-loader',
          options: {
            extract: true,
            spriteFilename: 'assets/sprite.svg'
          }
        },
        {
          test: /\.(png|jpg?)(\?.+)?$/,
          loader: 'url-loader',
          options: {
            name: 'assets/images/[name].[ext]',
            limit: 10000
          }
        }, {
          test: /\.(webm|mp4)$/,
          loader: 'file-loader',
          options: {
            name: 'assets/videos/[name].[hash:7].[ext]'
          }
        }
      ]
    },
    devtool: "source-map",
    target: "web",
    plugins: pluginsOptions,

    watch: (env.production === 'true') ? false : true,

    watchOptions: {
      aggregateTimeout: 300,
      poll: true
    },
    devServer: {
      contentBase: path.resolve(__dirname, './source/pages/'),
      watchContentBase: true,
      port: 9005,
      open: true,
      disableHostCheck: true,
      noInfo: true,
      compress: true,
      hot: (env.production === 'true') ? false : true,
      stats: 'minimal',
      publicPath: "/"
    }
  }
  if (env.production === 'true') {
    config.performance = {
      maxEntrypointSize: 1000000,
      maxAssetSize: 300000,
      hints: 'warning'
    };
  }
  config.resolve = resolveLoader().resolve;
  return config;
}
