module.exports = {
    presets: [
        '@vue/cli-plugin-babel/preset'
    ],
    "plugins": [
        ["prismjs", {
            "languages": ["java", "javascript", "css", "markup"],
            "plugins": ["line-numbers"],
            "theme": "tomorrow",
            "css": true
        }]
    ]
}
