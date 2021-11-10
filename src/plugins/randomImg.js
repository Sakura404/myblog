const randomImg = {
    randomImg: (() => {
        return require(`../assets/bg/${Math.floor(Math.random() * 16) + 1}.jpg`);
    })
}
export default randomImg
