$(function(){
	
	
	//hobYU()
function hobYU(){
//  setCookie('hob','hob',1);//存储标记
    $('.hongbao-wrapper').fadeIn();
    var h_con= 0,isPc;
    var HONGBAO = setInterval(function(){
        var _r=['50%','70%','90%','100%']
        $(window).width()>800?isPc=0:isPc=1;
        var HTML_i=$('<i style="background-size:'+_r[parseInt(Math.random()*4)]+';left:'+(parseInt(Math.random()*6)-isPc)*18+'%"></i>');
        $('.hongbao-wrapper').append(HTML_i);
        var _left = HTML_i.offset().left + 300
        HTML_i.animate({'top': '120%','left': _left}, 1200 , 'linear')
        $('.hob-cd').html(5-parseInt(h_con/20)+'s')
        if(h_con===100){
            clearTimeout(HONGBAO);
            var _x=$('.hob-inlet-02').offset().left - $(window).scrollLeft() +60,
                _y=$('.hob-inlet-02').offset().top  - $(window).scrollTop()  +50;
            $('.hongbao-wrapper').addClass('rotate').animate({
                'width'  : 0,
                'height' : 0,
                'top'    : _y,
                'left'   : _x,
                'opacity': 0
            }, 500);
            $('.hongbao-wrapper *').addClass('rotate').animate({
                'width'  : 0,
                'height' : 0,
                'top'    : _y,
                'left'   : _x,
                'opacity': 0
            }, 500,function(){
                $('.hongbao-wrapper').hide();
            }); 
        }
        else {
            h_con++;
        }
    },50);
}




	
	
		////红包雨
	var _hob = $('.hongbao-wrapper');
	var _forget = $('.for-get-now');
//  if(!GetUrlString('mmtoken') && getCookie('hob')!='hob'){
        setTimeout(function(){
                hobYU()
        },1000);
//  }
    
 
    $('.coupon').one('click',function coupon(event) {
//      if(!isLogin){
//          $('#hobCoupon').fadeOut();
//          modal.show('#login1');
//      }else {
            $.ajax({//红包雨优惠券领取
                url: portalDomain + "/api/cbc/user/promotion-coupon/v1/coupons/apply",
                data:JSON.stringify({"promotionId":'P1810102108468870LESYXJ0NJH5ZA',"customerId":jsonParamInit['UserAccount']}),
                dataType: "json",
                timeout: 10000,
                type: 'post',
                xhrFields: {
                    withCredentials: true
                },
                success: function(data) {
                    if(data.retCode==0 || data.retCode==508040112){
                        $('#hobCoupon').fadeOut();
                        $('#YLhob').fadeIn();
                        $('#hobCoupon .btn-s').addClass('disable').html('您已领取');
                    }
                    /*else if(data.retCode==508040112){
                        $('#hobCoupon').fadeOut();
                        $('#realName').fadeIn();
                    }*/
                    else {
                        console.log('红包雨条件不满足，状态 '+data.retCode);
                    }
                }
            });
//      }
        $('.coupon').one('click',coupon);
    });
    $('.hob-close').click(function(event) {
        var _x=$('.hob-inlet-02').offset().left - $(window).scrollLeft() +60,
            _y=$('.hob-inlet-02').offset().top  - $(window).scrollTop()  +50;
        $('.hongbao-wrapper').addClass('rotate').animate({
            'width'  : 0,
            'height' : 0,
            'top'    : _y,
            'left'   : _x,
            'opacity': 0
        }, 500);
        $('.hongbao-wrapper *').addClass('rotate').animate({
            'width'  : 0,
            'height' : 0,
            'top'    : _y,
            'left'   : _x,
            'opacity': 0
        }, 500,function(){
            $('.hongbao-wrapper').hide();
        }); 
        return false
    });
    _forget.click(function(event) {
        _hob.fadeOut();
        modal.show('#hobCoupon');
    });
    
    
    
});
