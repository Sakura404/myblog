<template>
  <div>
    <v-dialog v-model="zoomFlag" internal-activator max-width="70vw" absolute>
      <v-card
        class="full-screen"
        style="overflow: hidden"
        max-width="70vw"
        height="70vh"
      >
        <v-img
          id="full-img"
          height="100%"
          @mousedown="dragImg"
          @mousewheel="ImgWheel"
          style=""
          :style="`transform: scale(${fullScreenScale});;left: ${fullPosition.x}px;top:  ${fullPosition.y}px;`"
          position="center center"
          contain
          :src="menuElement.url"
        />
      </v-card>
    </v-dialog>
    <v-dialog max-width="50vw" v-model="deleteFlag" absolute>
      <v-card>
        <v-card-title>警告</v-card-title>
        <v-card-text>确定要删除该图片么</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn @click="deleteCancel()" depressed>取消</v-btn>
          <v-btn @click="deleteSumbit()" depressed>确认</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-card class="pa-2">
      <v-card-title>
        图片管理
        <v-spacer></v-spacer>
        <v-icon color="red" large @click="$emit('close')">mdi-close</v-icon>
      </v-card-title>
      <v-divider class="mb-2"></v-divider>
      <v-row no-gutters class="pa-2">
        <v-col order="2" cols="12" order-lg="1" lg="9">
          <v-card class="pa-4" outlined>
            <v-row align="end" class="img_scroll">
              <v-col
                cols="6"
                lg="4"
                v-for="(element, index) in imageList"
                :key="index"
              >
                <v-card
                  @contextmenu="show($event, element)"
                  @dblclick="show($event, element)"
                  @click="onCheck = element"
                >
                  <v-img
                    :src="element.url"
                    :alt="element.describe"
                    height="150"
                  ></v-img>
                </v-card>
              </v-col>
              <v-menu
                v-model="showMenu"
                absolute
                :position-x="menuLocation.x"
                :position-y="menuLocation.y"
                offset-y
                style="max-width: 600px"
              >
                <v-list>
                  <v-list-item
                    @click.stop="
                      zoomFlag = true;
                      fullPosition = { x: 0, y: 0 };
                      fullScreenScale = 1;
                    "
                  >
                    <v-list-item-title>
                      <v-icon color="blue">mdi-magnify-plus-outline</v-icon>
                      放大
                    </v-list-item-title>
                  </v-list-item>
                  <v-list-item @click.stop="deleteFlag = true">
                    <v-list-item-title>
                      <v-icon color="red">mdi-delete</v-icon> 删除
                    </v-list-item-title>
                  </v-list-item>
                </v-list>
              </v-menu>
              <v-col lg="4" cols="6">
                <v-card
                  outlined
                  class="dropbox d-flex justify-center align-center"
                  height="150"
                >
                  <input
                    v-show="false"
                    type="file"
                    accept="image/*"
                    id="imgFile"
                    multiple
                    @change="getFiles($event)"
                  />
                  <span
                    @click="openFileSelector()"
                    class="img_upload pa-2 font-weight-bold"
                    >拖拽图片上传</span
                  >
                </v-card>
              </v-col>
            </v-row>
          </v-card>
        </v-col>

        <v-col order="1" order-lg="2" cols="12" lg="3" class="pl-4">
          <v-img absolute min-height="150" :src="onCheck.url"></v-img>
          <p>实际路径: {{ onCheck.path }}</p>
          <p>使用链接: {{ onCheck.url }}</p>
          <p>大小:{{ (onCheck.size / (1024 * 1024)).toFixed(2) }}MB</p>
          <p>格式:{{ onCheck.type }}</p>
          <p>上传日期:{{ onCheck.date }}</p>
          <p>更新日期:{{ onCheck.modified }}</p>
          <p>描述：</p>

          <v-textarea
            filled
            id="img-describe"
            no-resize
            dense
            auto-grow
            row-height="16"
            loader-height="1"
            v-model="onCheck.excerpt"
            @change="exceptChange"
            placeholder="空空如也"
          />
        </v-col>
      </v-row>

      <v-divider class="mt-2"></v-divider>
      <v-progress-linear
        :buffer-value="uploadProgress"
        color="success"
        :active="uploadProgress > 0"
        stream
      ></v-progress-linear>
      <v-btn block text @click="submit" color="blue">确定</v-btn>
    </v-card>
  </div>
</template>

<script>
export default {
  data: () => ({
    as: "123",
    //图片列表
    imageList: [
      //   {
      //     id: 2,
      //     path: "D:/vue/image/164710667903117.jpg",
      //     url: "http://senkaryouran.top/img/10.c0d78c8c.jpg",
      //     type: "image/jpeg",
      //     size: 3,
      //     date: "2022-03-13 01:03:31",
      //     modified: "2022-03-13 01:03:31",
      //     except: "",
      //   },
    ],
    //被选中的图片列表项
    onCheck: {
      //   id: 2,
      //   path: "D:/vue/image/164710667903117.jpg",
      //   url: "http://senkaryouran.top/img/6.4237b083.jpg",
      //   type: "image/jpeg",
      //   size: 902178,
      //   date: "2022-03-13 01:03:31",
      //   modified: "2022-03-13 01:03:31",
      //   excerpt: "",
    },
    //照片放大遮罩开关
    zoomFlag: false,
    //删除确认开关
    deleteFlag: false,
    //上传图片数据
    imgFile: "",
    // 图片管理选项
    showMenu: false,
    //被选中的选项
    menuElement: "",
    //选项菜单坐标
    menuLocation: { x: 0, y: 0 },
    //上传进度开关
    uploadProgress: 0,
    fullScreenScale: 1,
    fullPosition: { x: 0, y: 0 },
  }),
  methods: {
    exceptChange() {
      let Data = new FormData();
      Data.append("media", this.onCheck);
      this.$http.put("/api/medias/", this.onCheck).then((res) => {
        if (res.data.code == 10000)
          this.imageList[this.imageList.indexOf(this.onCheck)] = res.data.data;
      });
    },
    openFileSelector() {
      document.querySelector("#imgFile").click();
    },
    onCheckChange() {
      this.onCheck = this.menuElement;
    },
    ImgWheel(e) {
      if (this.time && Date.now() - this.time < 16) return;
      this.time = Date.now();
      if (e.deltaY > 0)
        this.fullScreenScale -= this.fullScreenScale - 0.2 > 0 ? 0.1 : 0;
      else this.fullScreenScale += this.fullScreenScale + 0.2 < 3.0 ? 0.1 : 0;
    },
    moveCancel(e) {
      e.target.onmousemove = null;
    },
    dragImg(e) {
      let box = e.target;
      let _this = this;
      let changeX;
      let changeY;
      document.onmousedown = function (e) {
        let down = false;
        if (e.target === box) {
          down = true;
          changeX = _this.fullPosition.x - e.clientX;
          changeY = _this.fullPosition.y - e.clientY;
        }
        document.onmousemove = function (e) {
          if (!down) return;
          if (this.time && Date.now() - this.time < 16) return;
          this.time = Date.now();
          _this.fullPosition.x = changeX + e.clientX;
          _this.fullPosition.y = changeY + e.clientY;
        };
        document.onmouseup = function () {
          document.onmouseup = document.onmousemove = null;
        };
      };
    },
    submit() {
      this.$emit("imgSelect", this.onCheck);
    },
    show(e, element) {
      e.preventDefault();
      this.menuElement = element;
      this.showMenu = false;
      this.menuLocation.x = e.clientX;
      this.menuLocation.y = e.clientY;
      this.$nextTick(() => {
        this.showMenu = true;
      });
    },
    imgChange(item) {
      this.onCheck = item;
    },
    getFiles(e) {
      var dt = e.target;
      for (var i = 0; i !== dt.files.length; i++) {
        this.uploadFile(dt.files[i]);
      }
    },
    uploadFile(file) {
      const cofig = {
        onUploadProgress: (progress) =>
          (this.uploadProgress = Math.floor(
            (progress.loaded / progress.total) * 100
          )),
      };
      let data = new FormData();
      data.append("file", file);
      data.append("Content-Type", "multipart/form-data");
      // console.log(data);
      this.$http
        .post("/api/medias/", data, cofig)
        .then((res) => {
          if (res.data.code == 10000) {
            this.imageList.push(res.data.data);
            this.$snackbar.success("图片上传成功");
          } else {
            throw new Error(res.data.message);
          }
        })
        .catch((e) => {
          this.$snackbar.error("图片上传失败,原因:" + e);
        });
    },
    onDrag(e) {
      e.stopPropagation();
      e.preventDefault();
    },
    onDrop(e) {
      e.stopPropagation();
      e.preventDefault();
      var dt = e.dataTransfer;
      for (var i = 0; i !== dt.files.length; i++) {
        this.uploadFile(dt.files[i]);
      }
    },
    deleteSumbit() {
      let deleteIndex = this.imageList.indexOf(this.menuElement);
      this.$http
        .delete("/api/medias/" + this.menuElement.id)
        .then((res) => {
          // console.log(res);
          if (res.data.code == 10000) {
            this.imageList.splice(deleteIndex, 1);
            this.deleteFlag = false;
            this.$snackbar.success("图片删除成功");
          }
        })
        .catch((err) => {
          this.$snackbar.error("图片失败成功," + err);
        });
      this.onCheck = {};
      this.menuElement = {};
    },
    deleteCancel() {
      this.deleteFlag = false;
    },
  },
  mounted() {
    let dropbox = document.querySelector(".dropbox");
    dropbox.addEventListener("dragenter", this.onDrag, false);
    dropbox.addEventListener("dragover", this.onDrag, false);
    dropbox.addEventListener("drop", this.onDrop, false);
    this.$http
      .get("/api/medias/")
      .then((res) => {
        if (res.data.code == 10000)
          res.data.data.forEach((e) => {
            this.imageList.push(e);
          });
        this.onCheck = res.data.data[0];
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>

<style>
.img_scroll {
  max-height: 750px;
  overflow: auto;
}
.img_data li {
  display: block;
}
.img_upload {
  border: 2px dashed grey;
  user-select: none;
}
</style>