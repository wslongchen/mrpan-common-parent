/*!
 * Marquee - JS for Debug
 * @licence Marquee - v1.0 (2015-01-20)
 * http://56hm.com/ | Licence: MIT
 */
/*
 * @name pluginName
 * @Rely jQuery v1.7+
 * @License MIT
 *
 * github resource repository:
 *   https://github.com/repar
 *
 * usage as:
 * m1. $.fn.pluginName({...}); 
 * m2. $(...).pluginName({...});
 *
 * author: repar
 * website: http://www.56hm.com
 * email: 47558328@qq.com,  yy47558328@sina.com
 * qq: 47558328
 */
;(function($, window, document, undefined){

    // Create the defaults once
    var pluginName = "marquee",

    defaults = {
       enable : true,  //plug-in is enabled
       direction: 'vertical',   //运动方向.  vertical : horizontal
       itemSelecter : 'li',  //子节点选择器
       delay: 3000,  //动画渲染延迟时间
       speed: 1,  //动画渲染距离.
       timing: 1, //动画渲染速率.
       mouse: true //鼠标移入停止动画

    };


    function Widget(element, options) {
        this.element = element;
        this.settings = $.extend({}, defaults, options);
        this._defaults = defaults;
        this._name = pluginName;
        this.version = 'v1.0';

        
        this.$element = $(this.element);
        this.$wrapper = this.$element.parent();
        this.$items = this.$element.children(this.settings.itemSelecter);


        this.next = 0;
        this.timeoutHandle;
        this.intervalHandle

        if(!this.settings.enable)return; //检测插件是否开启.
        this.init();
    }


    Widget.prototype = {

       init:function(){

            var that = this;

           //子节点占用总高度.
            var totalSize = 0;

            $.each(this.$items, function(index, element){

                totalSize += that.isHorizontal() 
                            ? parseInt($(element).outerWidth())
                            : parseInt($(element).outerHeight());

            }); 
            
            //父节点实际高度
            var elmentTotalSize = this.isHorizontal()
               ? this.$element.outerWidth
               : this.$element.outerHeight;

            //判断子节点总高度是否大于父节点高度, 否则插件停止运行.
            if(totalSize < elmentTotalSize)return;

            //设置动画渲染所需的CSS样式.
            this.$wrapper.css({
                 
                position : 'relative',
                overflow : 'hidden'

            });

            this.$element.css({

                 position : 'absolute',
                 top : 0,
                 left: 0

            });

            this.$element.css(this.isHorizontal() ? 'width' : 'height', '1000%');


            //克隆子节点.
            this.cloneAllItems();

            //鼠标监听
            if(this.settings.mouse)
                     this.addHoverEvent(this);

            this.timer(this);

            
       },

       /**
         * 计时器.
         */
        timer : function(that){

            this.timeoutHandle = setTimeout(function(){that.play(that)}, this.settings.delay);

        },


        /**
         * 播放.
         */
        play : function(that){


           this.clearTimeout();

            var target = 0;

            for(var i = 0; i <= this.next; i++){
                 
                 target -= this.isHorizontal()
                    ? parseInt($(this.$items.get(this.next)).outerWidth())
                    : parseInt($(this.$items.get(this.next)).outerHeight());
                    

            }

            this.intervalHandle = setInterval(function(){that.animate(target)},this.settings.timing);
        },


        /**
         * 动画渲染.
         */
        animate : function(target){

            var mark = this.isHorizontal() ? 'left' : 'top';

            var present =  parseInt(this.$element.css(mark));

  
            if(present > target)
            {
                if(present - this.settings.speed <= target)
                {
                     this.$element.css(mark, target);
                
                }else

                     this.$element.css(mark, present - this.settings.speed);

            }else{


                this.clearInterval();

                if(this.next + 1 < this.$items.length){
                     
                     this.next++;
                    
                }else{

                    this.next = 0;
                    this.$element.css(mark,0);
                    
                }
                this.timer(this);
            }

        },


        isHorizontal : function(){

            return this.settings.direction == 'horizontal';
        },

        /**
         * 克隆子节点
         */
        cloneAllItems: function(){

            this.$element.append(this.$items.clone());
        },



        /**
         * 取消时钟队列.
         */
        clearTimeout : function(){
            
            clearTimeout(this.timeoutHandle);
        },

        /**
         * 取消定时器队列.
         */
        clearInterval : function(){
            
            clearInterval(this.intervalHandle);
        },
        
        /**
         * 暂停动画渲染.
         * @return {[type]} [description]
         */
        addHoverEvent : function(that){

            this.$wrapper
              .mouseenter(function(){
                   
                   that.clearInterval()
                   that.clearTimeout();

              })
              .mouseleave(function(){

                   that.play(that);

              });
        }



    }//prototype
    

    $.fn[pluginName] = function(options) {

        // chain jQuery functions
        return this.each(function() {
            if (!$.data(this, "plugin_" + pluginName)) {
                $.data(this, "plugin_" + pluginName, new Widget(this, options));
            }
        });

    };

})(jQuery, window, document);

