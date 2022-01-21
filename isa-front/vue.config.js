module.exports = {
    devServer: {
        proxy: 'https://adventureland-isa.herokuapp.com'
    },
    chainWebpack: (config) => {
        config
            .plugin('provide')
            // eslint-disable-next-line global-require
            .use(require('webpack').ProvidePlugin, [{
                'window.Quill': 'quill/dist/quill.js',
                Quill: 'quill/dist/quill.js',
                'window.jQuery': 'jquery/src/jquery.js',
                jQuery: 'jquery/src/jquery.js',
            }]);
    },
}