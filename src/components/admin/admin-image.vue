<template>
  <div>
    <v-dialog v-model="overlayFlag"
      max-width="70vw"
      absolute>
      <v-card max-width="70vw">
        <v-img sizes="vw"
          contain
          :src="onCheck.url" />
      </v-card>
    </v-dialog>
    <v-card width="1000px"
      class='pa-2'>
      <v-card-title>
        图片管理
      </v-card-title>
      <v-divider></v-divider>
      <v-row class='pa-2'>
        <v-col cols="9"
          class="img_scroll">
          <v-row align="end">
            <v-col cols="4"
              v-for="element,index in imageList"
              :key="index">

              <v-menu v-model="showMenu"
                absolute
                offset-y
                style="max-width: 600px">
                <template v-slot:activator="{ on, attrs }">
                  <v-card
                    v-bind="attrs"
                    v-on="on"
                    @dblclick.stop="overlayFlag=true">
                    <v-img :src="element.url"
                      :alt="element.describe"
                      height="150"></v-img>
                  </v-card>
                </template>
                <v-list>
                  <v-list-item  @click="imgChange(element)">
                    <v-list-item-title>查看详细</v-list-item-title>
                  </v-list-item>
                  <v-list-item>
                    <v-list-item-title>删除</v-list-item-title>
                  </v-list-item>
                </v-list>
              </v-menu>

            </v-col>
            <v-col cols=4>
              <v-card outlined
                class="dropbox d-flex justify-center align-center "
                height="150">
                <input v-show="false"
                  type="file"
                  id="imgFile"
                  multiple
                  @change="getFiles($event)">
                <span @click="openFileSelector()"
                  class="img_upload pa-2 font-weight-bold">拖拽图片上传</span>
              </v-card>
            </v-col>
          </v-row>
        </v-col>
        <v-divider vertical
          inset></v-divider>
        <v-col cols="3">

          <v-img absolute
            :src="onCheck.url"></v-img>
          <p>路径:
            {{onCheck.path}}</p>
          <p>大小:{{onCheck.size}}</p>
          <p>格式:{{onCheck.type}}</p>
          <p>上传日期:{{onCheck.date}}</p>
          <p>更新日期:{{onCheck.modified}}</p>
          <p>描述：</p>
          <v-textarea filled
            no-resize
            v-model="onCheck.describe"
            placeholder="空空如也" />

        </v-col>
      </v-row>
    </v-card>
  </div>

</template>

<script>
export default {
  data: () => ({
    as: "123",
    //图片列表
    imageList: [
      {
        id: 2,
        path: "D:/vue/image/164710667903117.jpg",
        url: "http://senkaryouran.top/img/6.4237b083.jpg",
        type: "image/jpeg",
        size: 902178,
        date: "2022-03-13 01:03:31",
        modified: "2022-03-13 01:03:31",
        except: "",
      },
      {
        id: 2,
        path: "D:/vue/image/164710667903117.jpg",
        url: "http://senkaryouran.top/img/6.4237b083.jpg",
        type: "image/jpeg",
        size: 902178,
        date: "2022-03-13 01:03:31",
        modified: "2022-03-13 01:03:31",
        except: "",
      },
      {
        id: 2,
        path: "D:/vue/image/164710667903117.jpg",
        url: "http://senkaryouran.top/img/6.4237b083.jpg",
        type: "image/jpeg",
        size: 902178,
        date: "2022-03-13 01:03:31",
        modified: "2022-03-13 01:03:31",
        except: "",
      },
    ],
    //被选中的图片列表项
    onCheck: {
      id: 2,
      path: "D:/vue/image/164710667903117.jpg",
      url: "http://senkaryouran.top/img/6.4237b083.jpg",
      type: "image/jpeg",
      size: 902178,
      date: "2022-03-13 01:03:31",
      modified: "2022-03-13 01:03:31",
      except: "",
    },
    //照片放大遮罩开关
    overlayFlag: false,
    //上传图片数据
    imgFile: "",
  }),
  methods: {
    openFileSelector() {
      document.querySelector("#imgFile").click();
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
      let data = new FormData();
      data.append("file", file);
      data.append("Content-Type", "multipart/form-data");
      console.log(data);
      this.$http
        .post("/api/common/imageupload", data)
        .then((res) => {
          if (res.data.code == 10000) this.imageList.push(res.data.data);
        })
        .catch((e) => {
          console.log(e);
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
  },
  mounted() {
    let dropbox = document.querySelector(".dropbox");
    dropbox.addEventListener("dragenter", this.onDrag, false);
    dropbox.addEventListener("dragover", this.onDrag, false);
    dropbox.addEventListener("drop", this.onDrop, false);
  },
};
</script>

<style>
.img_scroll {
  max-height: 100%;
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