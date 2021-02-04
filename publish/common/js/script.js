// 배너 슬라이드 영역
$(document).ready(function(){
    var wd=$("#slider").width();
    var len=$(".img_box li").length;
    var intv = setInterval(function(){
        nextAni();
    }, 2950);
    function nextAni(){
        $(".img_box").not(":animated").animate({
            "margin-left":-wd+"px"
        }, 700, function(){ 
            $(".img_box li").eq(0).appendTo($(".img_box"));
            $(".img_box").css("margin-left", "0px");
            $(".btn_box li").eq(0).appendTo($(".btn_box"));
            $(".btn_box li .btn").removeClass("on");
            $(".btn_box li .btn").eq(0).addClass("on");
        });
    }
    function numAni(num){
        for(var x=0;x<num-1;x++){
            $(".img_box li").eq(0).appendTo($(".img_box"));
            $(".btn_box li").eq(0).appendTo($(".btn_box"));
        }
        nextAni();
    }
    $(".img_btn li .btn").click(function(){
        var btnNum = $(this).parents("li").index();
        clearInterval(intv);
        numAni(btnNum);
        intv = setInterval(function(){  nextAni(); }, 2950);
    });
});