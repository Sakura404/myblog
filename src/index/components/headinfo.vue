<template>
	<div id="headinfo" :style="headinfoheight">
		<div id="ones">
			{{sentence}}
		</div>
		<div @click="downbtn" id="downbtn"><i class="mdui-icon material-icons">keyboard_arrow_down</i></div>
	</div>
</template>

<script>
	function scrollAnimation(currentY, targetY) {
		// 获取当前位置方法
		// const currentY = document.documentElement.scrollTop || document.body.scrollTop

		// 计算需要移动的距离
		let needScrollTop = targetY - currentY
		let _currentY = currentY
		setTimeout(() => {
			// 一次调用滑动帧数，每次调用会不一样
			const dist = Math.ceil(needScrollTop / 10)
			_currentY += dist
			window.scrollTo(_currentY, currentY)
			// 如果移动幅度小于十个像素，直接移动，否则递归调用，实现动画效果
			if (needScrollTop > 10 || needScrollTop < -10) {
				scrollAnimation(_currentY, targetY)
			} else {
				window.scrollTo(_currentY, targetY)
			}
		}, 1)
	}
	export default {
		name: 'headinfo',
		data() {
			return {
				sentence: 123,
				headinfoheight: "height:" + document.documentElement.clientHeight + "px"
			}
		},
		methods: {
			downbtn() {
				scrollAnimation(document.documentElement.scrollTop, document.documentElement.clientHeight -document.getElementsByClassName("mdui-appbar")[0].offsetHeight);
			}
		},
		mounted: function() {
			const that = this
			window.onresize = () => {
				that.headinfoheight = "height:" + document.documentElement.clientHeight + "px";
			}
			this.$http
				.get("http://api.tianapi.com/txapi/one/index?key=0b5ca2dd448bb6b4b8eb0da46a27d0d9")
				.then((
					response) => {
					this.sentence = response.data.newslist[0].word;
				})

		}

	}
</script>

<style>
	#ones {
		border-radius: 10px;
		margin: 0 auto;
		max-width: 700px;
		background-color: rgba(0, 0, 0, 0.5);
		color: white;
		text-align: center;
		font-size: 16px;
		line-height: 30px;
		padding: 15px;
		position: relative;
		top: 50%;
		font-weight: 700;
	}

	#headinfo {
		background-image: url(../assets/dcbg.jpg);
		background-position: top center;
		background-repeat: no-repeat;
		background-attachment: fixed;
		background-size: cover;
		height: 1000px;
		transition: 0.2s;
	}

	#downbtn {
		width: 100%;
		height: 40px;
		font-size: 10px;
		position: absolute;
		text-align: center;
		bottom: 0px;
		transition: 0.5s;
		user-select: none;
		cursor: pointer;
		padding-top: 20px;
	}

	#downbtn .mdui-icon {
		font-size: 40px;
	}

	#downbtn:hover {
		background-color: rgba(0, 0, 0, .3);
	}
</style>
