s = document.querySelector("#Datagrid1 > tbody")
var total = 0
for (var i = 1; i < s.children.length; i++)
    total += parseFloat(s.children[i].children[8].innerText)
console.log(total)