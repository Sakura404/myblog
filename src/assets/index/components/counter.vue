<template>
  <div
    id="counter"
    class="mdui-hidden-xs mdui-container mdui-color-blue-50"
  >
    <div
      id="output"
      class="mdui-container mdui-color-amber"
    >
      <div id="formula">
        <p class="textright">
          {{ formula }}
        </p>
      </div>
      <div id="result">
        <p class="textright">
          {{ result }}
        </p>
      </div>
    </div>
    <div
      v-for="(buttonli,liindex) in butarr"
      :key="liindex"
      class="mdui-row-xs-4"
    >
      <div
        v-for="(buttonkey,keyindex) in buttonli"
        :key="keyindex"
        class="mdui-col"
      >
        <button
          class="mdui-ripple mdui-btn mdui-color-orange-100"
          @click="addformula(buttonkey)"
        >
          {{ buttonkey }}
        </button>
      </div>
    </div>
  </div>
</template>

<script>
	const execMathExpress=require('exec-mathexpress');
	export default {
		name: 'Counter',
		data() {
			return {
				result: 0,
				formula: '',
				butarr: [
					['7', '8', '9', '+'],
					['4', '5', '6', '-'],
					['1', '2', '3', '*'],
					['0', '.', '=', '/'],
				]
			}
		},
		methods: {
			addformula(newnum) {
				var symbols = '/.*-+='
				if (this.formula != null) {
					if (!symbols.includes(this.formula.split('')[this.formula.length - 1]) || !symbols.includes(newnum))
						if (newnum != '=')
							this.formula += newnum;
						else{
							this.result=execMathExpress(this.formula)['num'];
						}
				} else {
					this.formula = newnum;
				}
			}
		}
	}
</script>

<style>
	#counter {
		padding: 20px;
		max-width: 500px;
	}

	#counter button {
		width: 70%;
		margin: 10px;
		height: 60;
	}

	#output {
		height: 60px;
		border: 4px inset;
	}

	.textright {
		text-align: right;
	}

	#formula {
		font-size: 6px;
	}
</style>
