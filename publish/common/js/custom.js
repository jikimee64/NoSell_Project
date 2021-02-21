// 카테고리 클릭 시 드롭다운 영역
$(document).ready(function(){
    $(".gnb").hide();
    $(".sub_bottom").hide();
    $(".cate_btn").click(function(){
        $(".gnb").stop().slideDown();
        $(this).css("background-color","#f3f3f3");
    });
    $(".gnb").mouseleave(function(){
        $(".gnb").stop().slideUp();
        $("#cate_btn").css("background-color","transparent");
    });
    $(".sub li").click(function(){
        $(".sub_bottom").removeClass("on");
        $(this).children("ul").addClass("on").stop().slideDown();
        $(this).css("color","#f3f3f3");
    });

    // 모바일 카테고리 토글
    $(".m_menu .m_cate").click(function(){
        $(".gnb").slideToggle();
    });

});

// 배너 슬라이드 영역
$(document).ready(function(){
    var wd=$("#slider").width();
    var len=$(".img_box li").length;
    var cnt=0;
    var intv = setInterval(function(){
        nextAni();
    }, 2950);
    function nextAni(){
        $(".img_box li").removeClass("on");
        $(".btn_box li .btn").removeClass("on");
        cnt++;
        if(cnt>=len){
            cnt = 0;
        }
        $(".img_box li").eq(cnt).addClass("on");
        $(".btn_box li .btn").eq(cnt).addClass("on");   
    }
    function numAni(num){
        for(cnt=0;cnt<num-1;cnt++){
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


// 더보기 로딩영역
    // $(function () {
    //     $(".main_row>li").slice(0, 20).show();
    //     $(".more_load").on('click', function (e) {
    //         e.preventDefault();
    //         $(".main_row>li:hidden").slice(0, 20).slideDown();
    //         if ($(".main_row>li:hidden").length == 0) {
    //             $("#load").fadeOut('slow');
    //         }
    //         $('html,body').animate({
    //             scrollTop: $(this).offset().top
    //         }, 1500);
    //     });
    // });

// 상단으로 이동영역
    $('a[href=#top]').click(function () {
        $('body,html').animate({
            scrollTop: 0
        }, 600);
        return false;
    });
});

// include
window.addEventListener('load', function() {
    var allElements = document.getElementsByTagName('*');
    Array.prototype.forEach.call(allElements, function(el) {
        var includePath = el.dataset.includePath;
        if (includePath) {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    el.innerHTML = this.responseText;
                }
            };
            xhttp.open('GET', includePath, true);
            xhttp.send();
        }
    });  
});