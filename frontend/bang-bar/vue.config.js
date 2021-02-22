module.exports = {
  devServer: {
    port: 7300,
    overlay:false
  },
  configureWebpack: {
    module: {
      rules: [
        {
          test: /\.css$/,
          use: ["postcss-loader"],
        },
      ],
    },
  },
};
