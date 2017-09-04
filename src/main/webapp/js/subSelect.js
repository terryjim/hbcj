var weekdayArr=['总经办','研发部'];
var dayArr=['10天','5天'];
var numArr=['1','2','3','4','5'];

//----------------------------------------------------------

//只有trigger 和 wheels 是必要参数  其他都是选填参数
var mobileSelect1 = new MobileSelect({
    trigger: '#trigger1',
    title: '部门',
    wheels: [
                {data: weekdayArr},
            ],
    position:[0]
});

var mobileSelect2 = new MobileSelect({
    trigger: '#trigger2', 
    title: '统计天数',  
    wheels: [
                {data: dayArr}
            ],
    position:[0] //初始化定位 打开时默认选中的哪个  如果不填默认为0
});

var mobileSelect3 = new MobileSelect({
    trigger: '#trigger3',
    title: '阀值',
    wheels: [
                {data: numArr},
            ],
    position:[0]
});
