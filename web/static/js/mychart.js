//昨日成交额柱状图
new Chart(document.getElementById("myChart1").getContext('2d'), {
    type: 'bar',
    data: {
        labels: ["17", "18", "19", "20", "21", "22"],
        datasets: [{
            label: '',
            type: 'bar',
            //柱状图高度
            data: [1211, 1598, 3665, 5475, 2096, 3348],
            backgroundColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderColor: [
                'rgba(255,99,132,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
  options: {
    legend: { display: false },
    title: {
      display: true,
      text: ''
    }
  }
});

//当月交易金额
new Chart(document.getElementById("myChart2"), {
  type: 'line',
  data: {
    labels: [8,9,10,11,12],
    datasets: [{ 
        data: [18236,19920,20120,23020,24780],
        label: '',
        borderColor: "#3e95cd",
        fill: false
        }
      // , { 
      //   data: [809,947,1402,3700,5267],
      //   label: '',
      //   borderColor: "#8e5ea2",
      //   fill: false
      // }, { 
      //   data: [276,408,547,675,734],
      //   label: '',
      //   borderColor: "#3cba9f",
      //   fill: false
      // }, { 
      //   data: [38,74,167,508,784],
      //   label: '',
      //   borderColor: "#e8c3b9",
      //   fill: false
      // }, { 
      //   data: [26,82,172,312,433],
      //   label: '',
      //   borderColor: "#c45850",
      //   fill: false
      // }
    ]
  },
  options: {
    legend: { display: false },
    title: {
      display: true,
      text: ''
    }
  }
});

//发往各地区的物流占比 饼状图
// 东北（黑龙江省、吉林省、辽宁省）；
// 华东（上海市、江苏省、浙江省、安徽省、福建省、江西省、山东省、台湾省）；
// 华北（北京市、天津市、山西省、河北省、内蒙古自治区）；
// 华中（河南省、湖北省、湖南省）；
// 华南（广东省、广西壮族自治区、海南省、香港特别行政区、澳门特别行政区）；
// 西南（重庆市、四川省、贵州省、云南省、西藏自治区）；
// 西北（陕西省、甘肃省、青海省、宁夏回族自治区、新疆维吾尔自治区）
new Chart(document.getElementById("myChart3").getContext('2d'), {
  type: 'polarArea',
  data: {
    labels: ["东北", "华东", "华北", "华中", "华南", "西南", "西北"],
    datasets: [{
      backgroundColor: [
        "#2ecc71",
        "#3498db",
        "#95a5a6",
        "#9b59b6",
        "#f1c40f",
        "#e74c3c",
        "#34495e"
      ],
      data: [12, 19, 13, 17, 18, 14, 7]
    }]
  },
  options: {
    legend: { display: false },
    title: {
      display: true,
      text: ''
     } 
  }
});
// new Chart(document.getElementById("myChart3"), {
//     type: 'bubble',
//     data: {
//       datasets: [
//         {
//           label: ["China"],
//           backgroundColor: "rgba(255,221,50,0.2)",
//           borderColor: "rgba(255,221,50,1)",
//           data: [{
//             x: 69017,
//             y: 5.245,
//             r: 15
//           }]
//         }, {
//           label: ["Denmark"],
//           backgroundColor: "rgba(60,186,159,0.2)",
//           borderColor: "rgba(60,186,159,1)",
//           data: [{
//             x: 258702,
//             y: 7.526,
//             r: 10
//           }]
//         }, {
//           label: ["Germany"],
//           backgroundColor: "rgba(0,0,0,0.2)",
//           borderColor: "#000",
//           data: [{
//             x: 759083,
//             y: 6.994,
//             r: 7
//           }]
//         }, {
//           label: ["Japan"],
//           backgroundColor: "rgba(193,46,12,0.2)",
//           borderColor: "rgba(193,46,12,1)",
//           data: [{
//             x: 891877,
//             y: 5.921,
//             r: 20
//           }]
//         }
//       ]
//     },
//     options: {
//       legend: { display: false },
//       title: {
//         display: true,
//         text: ''
//        } 
//     }
// });

//Mychart4
new Chart(document.getElementById("myChart4").getContext('2d'), {
  type: 'doughnut',
  data: {
    labels: ["东北", "华东", "华北", "华中", "华南", "西南", "西北"],
    datasets: [{
      backgroundColor: [
        "#2ecc71",
        "#3498db",
        "#95a5a6",
        "#9b59b6",
        "#f1c40f",
        "#e74c3c",
        "#34495e"
      ],
      data: [12, 19, 3, 17, 28, 24, 7]
    }]
  },
  options: {
      legend: { display: false },
      title: {
        display: true,
        text: ''
       } 
    }
});

// Polar chart
new Chart(document.getElementById("polar-chart").getContext('2d'), {
  type: 'polarArea',
  data: {
    labels: ["M", "T", "W", "T", "F", "S", "S"],
    datasets: [{
      backgroundColor: [
        "#2ecc71",
        "#3498db",
        "#95a5a6",
        "#9b59b6",
        "#f1c40f",
        "#e74c3c",
        "#34495e"
      ],
      data: [12, 19, 3, 17, 28, 24, 7]
    }]
  },
  options: {
    legend: { display: false },
    title: {
      display: true,
      text: ''
     } 
  }
});