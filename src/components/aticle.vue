<template>
  <v-row style="margin-top: 56px" justify="center" class="serif no-gutters">
    <v-col
      v-intersect="onIntersect"
      style="background-color: white"
      lg="6"
      cols="12"
      ><v-img
        class="white--text align-end"
        height="400px"
        src="../assets/73718346_p0.png"
      >
        <v-card-title
          style="text-shadow: 2px 2px 10px #000"
          class="py-10 font-weight-thin"
        >
          <h1>オレンジ</h1>
        </v-card-title>
        <v-card-subtitle class="white--text"
          ><h2>
             <v-icon small color="white">mdi-clock</v-icon> 2020-7-31 
          </h2></v-card-subtitle
        ></v-img
      >
      <v-card class="px-6 py-6">
        <h1
          class="md-end-block md-heading"
          style="
            box-sizing: border-box;
            break-after: avoid-page;
            break-inside: avoid;
            orphans: 4;
            font-size: 2.25em;
            margin-top: 1rem;
            margin-bottom: 1rem;
            position: relative;
            line-height: 1.2;
            cursor: text;
            border-bottom: 1px solid #eeeeee;
            white-space: pre-wrap;
            color: #333333;
            font-family: 'Open Sans', 'Clear Sans', 'Helvetica Neue', Helvetica,
              Arial, sans-serif;
          "
        >
          <span class="md-plain" style="box-sizing: border-box">2021-7-14</span>
        </h1>
        <h2
          class="md-end-block md-heading"
          style="
            box-sizing: border-box;
            break-after: avoid-page;
            break-inside: avoid;
            orphans: 4;
            font-size: 1.75em;
            margin-top: 1rem;
            margin-bottom: 1rem;
            position: relative;
            line-height: 1.225;
            cursor: text;
            border-bottom: 1px solid #eeeeee;
            white-space: pre-wrap;
            color: #333333;
            font-family: 'Open Sans', 'Clear Sans', 'Helvetica Neue', Helvetica,
              Arial, sans-serif;
          "
        >
          <span class="md-plain" style="box-sizing: border-box">算法</span>
        </h2>
        <p
          class="md-end-block md-p"
          style="
            box-sizing: border-box;
            line-height: inherit;
            orphans: 4;
            margin: 0.8em 0px;
            white-space: pre-wrap;
            position: relative;
            color: #333333;
            font-family: 'Open Sans', 'Clear Sans', 'Helvetica Neue', Helvetica,
              Arial, sans-serif;
            font-size: 16px;
          "
        >
          <span class="md-pair-s" style="box-sizing: border-box"
            ><strong style="box-sizing: border-box"
              ><span
                class="md-link md-pair-s"
                style="box-sizing: border-box; word-break: break-all"
                spellcheck="false"
                ><a
                  style="
                    box-sizing: border-box;
                    cursor: pointer;
                    color: #4183c4;
                    -webkit-user-drag: none;
                  "
                  href="https://leetcode-cn.com/problems/3sum/"
                  >https://leetcode-cn.com/problems/3sum/</a
                ></span
              ></strong
            ></span
          >
        </p>
        <pre class="language-javascript"><code>var threeSum = function (nums) {
        nums.sort((a, b) =&gt; a - b)
        const res = [], size = nums.length
        for (let b = 0; b &lt; size - 2; ++b) {
            if (nums[b] === nums[b - 1]) continue
            let i = b + 1, j = size - 1
            while (i &lt; j) {
                let sum = nums[b] + nums[i] + nums[j]
                if (sum &lt; 0) { 
                    while (i &lt; j &amp;&amp; nums[i] === nums[++i]) { }
                } else if (sum &gt; 0) { 
                    while (i &lt; j &amp;&amp; nums[j] === nums[--j]) { }
                } else {
                    res.push([nums[b], nums[i], nums[j]])
                    while (i &lt; j &amp;&amp; nums[i] === nums[++i]) { }
                    while (i &lt; j &amp;&amp; nums[j] === nums[--j]) { }
                }
            }
        }
        return res
    };</code></pre>
        <p
          class="md-end-block md-p"
          style="
            box-sizing: border-box;
            line-height: inherit;
            orphans: 4;
            margin: 0.8em 0px;
            white-space: pre-wrap;
            position: relative;
            color: #333333;
            font-family: 'Open Sans', 'Clear Sans', 'Helvetica Neue', Helvetica,
              Arial, sans-serif;
            font-size: 16px;
          "
        >
          <span class="md-pair-s" style="box-sizing: border-box"
            ><strong style="box-sizing: border-box"
              ><span
                class="md-link md-pair-s"
                style="box-sizing: border-box; word-break: break-all"
                spellcheck="false"
                ><a
                  style="
                    box-sizing: border-box;
                    cursor: pointer;
                    color: #4183c4;
                    -webkit-user-drag: none;
                  "
                  href="https://leetcode-cn.com/problems/reverse-words-in-a-string/"
                  >https://leetcode-cn.com/problems/reverse-words-in-a-string/</a
                ></span
              ></strong
            ></span
          >
        </p>
        <pre class="language-javascript"><code>var reverseWords = function(s) {
      return  s.trim().split(/\s+/).reverse().join(' ');
    };</code></pre>
        <p
          class="md-end-block md-p"
          style="
            box-sizing: border-box;
            line-height: inherit;
            orphans: 4;
            margin: 0.8em 0px;
            white-space: pre-wrap;
            position: relative;
            color: #333333;
            font-family: 'Open Sans', 'Clear Sans', 'Helvetica Neue', Helvetica,
              Arial, sans-serif;
            font-size: 16px;
          "
        >
          &nbsp;
        </p>
        <h2
          class="md-end-block md-heading"
          style="
            box-sizing: border-box;
            break-after: avoid-page;
            break-inside: avoid;
            orphans: 4;
            font-size: 1.75em;
            margin-top: 1rem;
            margin-bottom: 1rem;
            position: relative;
            line-height: 1.225;
            cursor: text;
            border-bottom: 1px solid #eeeeee;
            white-space: pre-wrap;
            color: #333333;
            font-family: 'Open Sans', 'Clear Sans', 'Helvetica Neue', Helvetica,
              Arial, sans-serif;
          "
        >
          <span class="md-plain" style="box-sizing: border-box">java</span>
        </h2>
        <p
          class="md-end-block md-p"
          style="
            box-sizing: border-box;
            line-height: inherit;
            orphans: 4;
            margin: 0.8em 0px;
            white-space: pre-wrap;
            position: relative;
            color: #333333;
            font-family: 'Open Sans', 'Clear Sans', 'Helvetica Neue', Helvetica,
              Arial, sans-serif;
            font-size: 16px;
          "
        >
          <span class="md-pair-s" style="box-sizing: border-box"
            ><strong style="box-sizing: border-box"
              >说一下 synchronized 底层实现原理？</strong
            ></span
          >
        </p>
        <p
          class="md-end-block md-p"
          style="
            box-sizing: border-box;
            line-height: inherit;
            orphans: 4;
            margin: 0.8em 0px;
            white-space: pre-wrap;
            position: relative;
            color: #333333;
            font-family: 'Open Sans', 'Clear Sans', 'Helvetica Neue', Helvetica,
              Arial, sans-serif;
            font-size: 16px;
          "
        >
          <span class="md-plain" style="box-sizing: border-box"
            >Java中每一个对象都可以作为锁，这是synchronized实现同步的基础：</span
          >
        </p>
        <p
          class="md-end-block md-p"
          style="
            box-sizing: border-box;
            line-height: inherit;
            orphans: 4;
            margin: 0.8em 0px;
            white-space: pre-wrap;
            position: relative;
            color: #333333;
            font-family: 'Open Sans', 'Clear Sans', 'Helvetica Neue', Helvetica,
              Arial, sans-serif;
            font-size: 16px;
          "
        >
          <span class="md-plain" style="box-sizing: border-box"
            >普通同步方法，锁是当前实例对象</span
          >
        </p>
        <p
          class="md-end-block md-p"
          style="
            box-sizing: border-box;
            line-height: inherit;
            orphans: 4;
            margin: 0.8em 0px;
            white-space: pre-wrap;
            position: relative;
            color: #333333;
            font-family: 'Open Sans', 'Clear Sans', 'Helvetica Neue', Helvetica,
              Arial, sans-serif;
            font-size: 16px;
          "
        >
          <span class="md-plain" style="box-sizing: border-box"
            >静态同步方法，锁是当前类的class对象</span
          >
        </p>
        <p
          class="md-end-block md-p"
          style="
            box-sizing: border-box;
            line-height: inherit;
            orphans: 4;
            margin: 0.8em 0px;
            white-space: pre-wrap;
            position: relative;
            color: #333333;
            font-family: 'Open Sans', 'Clear Sans', 'Helvetica Neue', Helvetica,
              Arial, sans-serif;
            font-size: 16px;
          "
        >
          <span class="md-plain" style="box-sizing: border-box"
            >同步方法块，锁是括号里面的对象</span
          >
        </p>
        <p
          class="md-end-block md-p"
          style="
            box-sizing: border-box;
            line-height: inherit;
            orphans: 4;
            margin: 0.8em 0px;
            white-space: pre-wrap;
            position: relative;
            color: #333333;
            font-family: 'Open Sans', 'Clear Sans', 'Helvetica Neue', Helvetica,
              Arial, sans-serif;
            font-size: 16px;
          "
        >
          <span class="md-pair-s" style="box-sizing: border-box"
            ><strong style="box-sizing: border-box"
              >synchronized 和 volatile 的区别是什么？</strong
            ></span
          >
        </p>
        <p
          class="md-end-block md-p"
          style="
            box-sizing: border-box;
            line-height: inherit;
            orphans: 4;
            margin: 0.8em 0px;
            white-space: pre-wrap;
            position: relative;
            color: #333333;
            font-family: 'Open Sans', 'Clear Sans', 'Helvetica Neue', Helvetica,
              Arial, sans-serif;
            font-size: 16px;
          "
        >
          <span class="md-plain" style="box-sizing: border-box"
            >volatile本质是在告诉jvm当前变量在寄存器（工作内存）中的值是不确定的，需要从主存中读取；
            synchronized则是锁定当前变量，只有当前线程可以访问该变量，其他线程被阻塞住。</span
          ><span class="md-softbreak" style="box-sizing: border-box"> </span
          ><span class="md-plain" style="box-sizing: border-box"
            >volatile仅能使用在变量级别；synchronized则可以使用在变量、方法、和类级别的</span
          ><span class="md-softbreak" style="box-sizing: border-box"> </span
          ><span class="md-plain" style="box-sizing: border-box"
            >volatile仅能实现变量的修改可见性，不能保证原子性；而synchronized则可以保证变量的修改可见性和原子性</span
          ><span class="md-softbreak" style="box-sizing: border-box"> </span
          ><span class="md-plain" style="box-sizing: border-box"
            >volatile不会造成线程的阻塞；synchronized可能会造成线程的阻塞。</span
          ><span class="md-softbreak" style="box-sizing: border-box"> </span
          ><span class="md-plain" style="box-sizing: border-box"
            >volatile标记的变量不会被编译器优化；synchronized标记的变量可以被编译器优化</span
          >
        </p>
        <p
          class="md-end-block md-p"
          style="
            box-sizing: border-box;
            line-height: inherit;
            orphans: 4;
            margin: 0.8em 0px;
            white-space: pre-wrap;
            position: relative;
            color: #333333;
            font-family: 'Open Sans', 'Clear Sans', 'Helvetica Neue', Helvetica,
              Arial, sans-serif;
            font-size: 16px;
          "
        >
          <span class="md-pair-s" style="box-sizing: border-box"
            ><strong style="box-sizing: border-box"
              >synchronized 和 Lock 有什么区别？</strong
            ></span
          >
        </p>
        <figure
          class="md-table-fig"
          style="
            box-sizing: border-box;
            margin: 1.2em 0px;
            overflow-x: auto;
            max-width: calc(100% + 16px);
            padding: 0px;
            cursor: default;
            color: #333333;
            font-family: 'Open Sans', 'Clear Sans', 'Helvetica Neue', Helvetica,
              Arial, sans-serif;
            font-size: 16px;
          "
        >
          <table
            class="md-table"
            style="
              border-spacing: 0px;
              width: 766px;
              overflow: auto;
              break-inside: auto;
              cursor: text;
              margin: 0px;
              padding: 0px;
              word-break: initial;
              white-space: pre-wrap;
            "
          >
            <thead style="box-sizing: border-box; background-color: #f8f8f8">
              <tr
                class="md-end-block"
                style="
                  box-sizing: border-box;
                  break-inside: avoid;
                  break-after: auto;
                  border: 1px solid #dfe2e5;
                  margin: 0px;
                  padding: 0px;
                "
              >
                <th
                  style="
                    box-sizing: border-box;
                    padding: 6px 13px;
                    border-width: 1px 1px 0px;
                    border-image: initial;
                    margin: 0px;
                    text-align: center;
                    border-color: #dfe2e5 #dfe2e5 initial #dfe2e5;
                    border-style: solid solid initial solid;
                  "
                >
                  <span
                    class="td-span"
                    style="
                      box-sizing: border-box;
                      display: inline-block;
                      min-width: 1ch;
                      width: 18px;
                      min-height: 10px;
                    "
                    ><span class="md-plain" style="box-sizing: border-box"
                      >类别</span
                    ></span
                  >
                </th>
                <th
                  style="
                    box-sizing: border-box;
                    padding: 6px 13px;
                    border-width: 1px 1px 0px;
                    border-image: initial;
                    margin: 0px;
                    text-align: center;
                    border-color: #dfe2e5 #dfe2e5 initial #dfe2e5;
                    border-style: solid solid initial solid;
                  "
                >
                  <span
                    class="td-span"
                    style="
                      box-sizing: border-box;
                      display: inline-block;
                      min-width: 1ch;
                      width: 296px;
                      min-height: 10px;
                    "
                    ><span class="md-plain" style="box-sizing: border-box"
                      >synchronized</span
                    ></span
                  >
                </th>
                <th
                  style="
                    box-sizing: border-box;
                    padding: 6px 13px;
                    border-width: 1px 1px 0px;
                    border-image: initial;
                    margin: 0px;
                    text-align: center;
                    border-color: #dfe2e5 #dfe2e5 initial #dfe2e5;
                    border-style: solid solid initial solid;
                  "
                >
                  <span
                    class="td-span"
                    style="
                      box-sizing: border-box;
                      display: inline-block;
                      min-width: 1ch;
                      width: 370px;
                      min-height: 10px;
                    "
                    ><span class="md-plain" style="box-sizing: border-box"
                      >Lock</span
                    ></span
                  >
                </th>
              </tr>
            </thead>
            <tbody style="box-sizing: border-box">
              <tr
                class="md-end-block"
                style="
                  box-sizing: border-box;
                  break-inside: avoid;
                  break-after: auto;
                  border: 1px solid #dfe2e5;
                  margin: 0px;
                  padding: 0px;
                "
              >
                <td
                  style="
                    box-sizing: border-box;
                    padding: 6px 13px;
                    border: 1px solid #dfe2e5;
                    margin: 0px;
                    min-width: 32px;
                    text-align: center;
                  "
                >
                  <span
                    class="td-span"
                    style="
                      box-sizing: border-box;
                      display: inline-block;
                      min-width: 1ch;
                      width: 18px;
                      min-height: 10px;
                    "
                    ><span class="md-plain" style="box-sizing: border-box"
                      >存在层次</span
                    ></span
                  >
                </td>
                <td
                  style="
                    box-sizing: border-box;
                    padding: 6px 13px;
                    border: 1px solid #dfe2e5;
                    margin: 0px;
                    min-width: 32px;
                    text-align: center;
                  "
                >
                  <span
                    class="td-span"
                    style="
                      box-sizing: border-box;
                      display: inline-block;
                      min-width: 1ch;
                      width: 296px;
                      min-height: 10px;
                    "
                    ><span class="md-plain" style="box-sizing: border-box"
                      >java关键字，在jvm层面上</span
                    ></span
                  >
                </td>
                <td
                  style="
                    box-sizing: border-box;
                    padding: 6px 13px;
                    border: 1px solid #dfe2e5;
                    margin: 0px;
                    min-width: 32px;
                    text-align: center;
                  "
                >
                  <span
                    class="td-span"
                    style="
                      box-sizing: border-box;
                      display: inline-block;
                      min-width: 1ch;
                      width: 370px;
                      min-height: 10px;
                    "
                    ><span class="md-plain" style="box-sizing: border-box"
                      >是一个类</span
                    ></span
                  >
                </td>
              </tr>
              <tr
                class="md-end-block"
                style="
                  box-sizing: border-box;
                  break-inside: avoid;
                  break-after: auto;
                  border: 1px solid #dfe2e5;
                  margin: 0px;
                  padding: 0px;
                  background-color: #f8f8f8;
                "
              >
                <td
                  style="
                    box-sizing: border-box;
                    padding: 6px 13px;
                    border: 1px solid #dfe2e5;
                    margin: 0px;
                    min-width: 32px;
                    text-align: center;
                  "
                >
                  <span
                    class="td-span"
                    style="
                      box-sizing: border-box;
                      display: inline-block;
                      min-width: 1ch;
                      width: 18px;
                      min-height: 10px;
                    "
                    ><span class="md-plain" style="box-sizing: border-box"
                      >锁的释放</span
                    ></span
                  >
                </td>
                <td
                  style="
                    box-sizing: border-box;
                    padding: 6px 13px;
                    border: 1px solid #dfe2e5;
                    margin: 0px;
                    min-width: 32px;
                    text-align: center;
                  "
                >
                  <span
                    class="td-span"
                    style="
                      box-sizing: border-box;
                      display: inline-block;
                      min-width: 1ch;
                      width: 296px;
                      min-height: 10px;
                    "
                    ><span class="md-plain" style="box-sizing: border-box"
                      >1、以获取锁的线程执行完同步代码，释放锁2、线程执行发生异常，jvm会让线程释放锁</span
                    ></span
                  >
                </td>
                <td
                  style="
                    box-sizing: border-box;
                    padding: 6px 13px;
                    border: 1px solid #dfe2e5;
                    margin: 0px;
                    min-width: 32px;
                    text-align: center;
                  "
                >
                  <span
                    class="td-span"
                    style="
                      box-sizing: border-box;
                      display: inline-block;
                      min-width: 1ch;
                      width: 370px;
                      min-height: 10px;
                    "
                    ><span class="md-plain" style="box-sizing: border-box"
                      >在finally中必须释放锁，不然容易造成线程死锁</span
                    ></span
                  >
                </td>
              </tr>
              <tr
                class="md-end-block"
                style="
                  box-sizing: border-box;
                  break-inside: avoid;
                  break-after: auto;
                  border: 1px solid #dfe2e5;
                  margin: 0px;
                  padding: 0px;
                "
              >
                <td
                  style="
                    box-sizing: border-box;
                    padding: 6px 13px;
                    border: 1px solid #dfe2e5;
                    margin: 0px;
                    min-width: 32px;
                    text-align: center;
                  "
                >
                  <span
                    class="td-span"
                    style="
                      box-sizing: border-box;
                      display: inline-block;
                      min-width: 1ch;
                      width: 18px;
                      min-height: 10px;
                    "
                    ><span class="md-plain" style="box-sizing: border-box"
                      >锁的获取</span
                    ></span
                  >
                </td>
                <td
                  style="
                    box-sizing: border-box;
                    padding: 6px 13px;
                    border: 1px solid #dfe2e5;
                    margin: 0px;
                    min-width: 32px;
                    text-align: center;
                  "
                >
                  <span
                    class="td-span"
                    style="
                      box-sizing: border-box;
                      display: inline-block;
                      min-width: 1ch;
                      width: 296px;
                      min-height: 10px;
                    "
                    ><span class="md-plain" style="box-sizing: border-box"
                      >假设A线程获得锁，B线程等待。如果A线程阻塞，B线程会一直等待</span
                    ></span
                  >
                </td>
                <td
                  style="
                    box-sizing: border-box;
                    padding: 6px 13px;
                    border: 1px solid #dfe2e5;
                    margin: 0px;
                    min-width: 32px;
                    text-align: center;
                  "
                >
                  <span
                    class="td-span"
                    style="
                      box-sizing: border-box;
                      display: inline-block;
                      min-width: 1ch;
                      width: 370px;
                      min-height: 10px;
                    "
                    ><span class="md-plain" style="box-sizing: border-box"
                      >分情况而定，Lock有多个锁获取的方式，具体下面会说道，大致就是可以尝试获得锁，线程可以不用一直等待</span
                    ></span
                  >
                </td>
              </tr>
              <tr
                class="md-end-block"
                style="
                  box-sizing: border-box;
                  break-inside: avoid;
                  break-after: auto;
                  border: 1px solid #dfe2e5;
                  margin: 0px;
                  padding: 0px;
                  background-color: #f8f8f8;
                "
              >
                <td
                  style="
                    box-sizing: border-box;
                    padding: 6px 13px;
                    border: 1px solid #dfe2e5;
                    margin: 0px;
                    min-width: 32px;
                    text-align: center;
                  "
                >
                  <span
                    class="td-span"
                    style="
                      box-sizing: border-box;
                      display: inline-block;
                      min-width: 1ch;
                      width: 18px;
                      min-height: 10px;
                    "
                    ><span class="md-plain" style="box-sizing: border-box"
                      >锁状态</span
                    ></span
                  >
                </td>
                <td
                  style="
                    box-sizing: border-box;
                    padding: 6px 13px;
                    border: 1px solid #dfe2e5;
                    margin: 0px;
                    min-width: 32px;
                    text-align: center;
                  "
                >
                  <span
                    class="td-span"
                    style="
                      box-sizing: border-box;
                      display: inline-block;
                      min-width: 1ch;
                      width: 296px;
                      min-height: 10px;
                    "
                    ><span class="md-plain" style="box-sizing: border-box"
                      >无法判断</span
                    ></span
                  >
                </td>
                <td
                  style="
                    box-sizing: border-box;
                    padding: 6px 13px;
                    border: 1px solid #dfe2e5;
                    margin: 0px;
                    min-width: 32px;
                    text-align: center;
                  "
                >
                  <span
                    class="td-span"
                    style="
                      box-sizing: border-box;
                      display: inline-block;
                      min-width: 1ch;
                      width: 370px;
                      min-height: 10px;
                    "
                    ><span class="md-plain" style="box-sizing: border-box"
                      >可以判断</span
                    ></span
                  >
                </td>
              </tr>
              <tr
                class="md-end-block"
                style="
                  box-sizing: border-box;
                  break-inside: avoid;
                  break-after: auto;
                  border: 1px solid #dfe2e5;
                  margin: 0px;
                  padding: 0px;
                "
              >
                <td
                  style="
                    box-sizing: border-box;
                    padding: 6px 13px;
                    border: 1px solid #dfe2e5;
                    margin: 0px;
                    min-width: 32px;
                    text-align: center;
                  "
                >
                  <span
                    class="td-span"
                    style="
                      box-sizing: border-box;
                      display: inline-block;
                      min-width: 1ch;
                      width: 18px;
                      min-height: 10px;
                    "
                    ><span class="md-plain" style="box-sizing: border-box"
                      >锁类型</span
                    ></span
                  >
                </td>
                <td
                  style="
                    box-sizing: border-box;
                    padding: 6px 13px;
                    border: 1px solid #dfe2e5;
                    margin: 0px;
                    min-width: 32px;
                    text-align: center;
                  "
                >
                  <span
                    class="td-span"
                    style="
                      box-sizing: border-box;
                      display: inline-block;
                      min-width: 1ch;
                      width: 296px;
                      min-height: 10px;
                    "
                    ><span class="md-plain" style="box-sizing: border-box"
                      >可重入 不可中断 非公平</span
                    ></span
                  >
                </td>
                <td
                  style="
                    box-sizing: border-box;
                    padding: 6px 13px;
                    border: 1px solid #dfe2e5;
                    margin: 0px;
                    min-width: 32px;
                    text-align: center;
                  "
                >
                  <span
                    class="td-span"
                    style="
                      box-sizing: border-box;
                      display: inline-block;
                      min-width: 1ch;
                      width: 370px;
                      min-height: 10px;
                    "
                    ><span class="md-plain" style="box-sizing: border-box"
                      >可重入 可判断 可公平（两者皆可)</span
                    ></span
                  >
                </td>
              </tr>
              <tr
                class="md-end-block"
                style="
                  box-sizing: border-box;
                  break-inside: avoid;
                  break-after: auto;
                  border: 1px solid #dfe2e5;
                  margin: 0px;
                  padding: 0px;
                  background-color: #f8f8f8;
                "
              >
                <td
                  style="
                    box-sizing: border-box;
                    padding: 6px 13px;
                    border: 1px solid #dfe2e5;
                    margin: 0px;
                    min-width: 32px;
                    text-align: center;
                  "
                >
                  <span
                    class="td-span"
                    style="
                      box-sizing: border-box;
                      display: inline-block;
                      min-width: 1ch;
                      width: 18px;
                      min-height: 10px;
                    "
                    ><span class="md-plain" style="box-sizing: border-box"
                      >性能</span
                    ></span
                  >
                </td>
                <td
                  style="
                    box-sizing: border-box;
                    padding: 6px 13px;
                    border: 1px solid #dfe2e5;
                    margin: 0px;
                    min-width: 32px;
                    text-align: center;
                  "
                >
                  <span
                    class="td-span"
                    style="
                      box-sizing: border-box;
                      display: inline-block;
                      min-width: 1ch;
                      width: 296px;
                      min-height: 10px;
                    "
                    ><span class="md-plain" style="box-sizing: border-box"
                      >少量同步</span
                    ></span
                  >
                </td>
                <td
                  style="
                    box-sizing: border-box;
                    padding: 6px 13px;
                    border: 1px solid #dfe2e5;
                    margin: 0px;
                    min-width: 32px;
                    text-align: center;
                  "
                >
                  <span
                    class="td-span"
                    style="
                      box-sizing: border-box;
                      display: inline-block;
                      min-width: 1ch;
                      width: 370px;
                      min-height: 10px;
                    "
                    ><span class="md-plain" style="box-sizing: border-box"
                      >大量同步
                    </span></span
                  >
                </td>
              </tr>
            </tbody>
          </table>
        </figure>
      </v-card>
    </v-col>
  </v-row>
</template>
<script>
import Prism from "prismjs";
export default {
  data: () => ({}),
  methods: {
    onIntersect(entries) {
      this.$emit("onIntersect", entries);
    },
  },
  mounted() {
    Prism.highlightAll();
  },
};
</script>
<style>
.serif {
  font-family: "Noto Serif SC", "Source Han Serif SC", "Source Han Serif",
    source-han-serif-sc, "PT Serif", "SongTi SC", "MicroSoft Yahei", Georgia,
    serif !important;
}
</style>