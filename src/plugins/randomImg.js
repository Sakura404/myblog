const end = 16
   , start = 1;
const randomImg = {
   cdnRandomImg: (() => {

      return new URL(`../assets/docsmall/${Math.floor(Math.random() * end) + start}.jpg`, import.meta.url).href
   }),
   RandomImg: ((indexd) => {
      let random = Math.floor(Math.random() * end) + start
      while (random === indexd) {
         random = Math.floor(Math.random() * end) + start
         if (random)
            break;
      }
      return `https://cdn.jsdelivr.net/gh/Sakura404/myblog/src/assets/docsmall/${random}.jpg`
   })
}
export default randomImg
