<template>
	<div id="headinfo">
		<div id="ones">
			{{sentence}}
		</div>
	</div>
</template>

<script>
	export default {
		name: 'headinfo',
		data() {
			return {
				sentence: 123
			}
		},
		mounted: function() {
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
	}
</style>
