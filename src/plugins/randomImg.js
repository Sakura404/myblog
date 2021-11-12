const randomImg = {
    randomImg: (() => {
        return require(`../assets/bg/${Math.floor(Math.random() * 16) + 1}.jpg`);
    }),
    cdnRandomImg: (() => {
        return `https://cdn.jsdelivr.net/gh/Sakura404/myblog/src/assets/bg/${Math.floor(Math.random() * 16) + 1}.jpg`
    })
}
export default randomImg
