const end = 16
    , start = 1;
const randomImg = {
    RandomImg: (() => {
        return require(`../assets/bg/${Math.floor(Math.random() * end) + start}.jpg`);
    }),
    cdnRandomImg: ((indexd) => {
        let random = Math.floor(Math.random() * end) + start
        while (random === indexd) {
            random = Math.floor(Math.random() * end) + start
            if (random)
                break;
        }
        return `https://cdn.jsdelivr.net/gh/Sakura404/myblog/src/assets/bg/${random}.jpg`
    })
}
export default randomImg
