(function($){
		$.fn.tmDragSort = function(options) {
			return this.each(function() {
				var opts = $.extend({},$.fn.tmDragSort.defaults, options);
				$(this).data("tmDragSort", {options : opts});
			tmDragSortInit($(this));
		});
	};
	function tmDragSortInit($item){
		var opts = $item.data("tmDragSort").options;
		var $this = $item;
		$item.mousedown(function(e){
			/*鼠标左键激活*/
			var switchDragSort = true;
			if(e.which!=1)return;
			/*与拖动选择互斥*/
			if(isNotEmpty(opts.to)){
				$this = $item.parent("."+opts.to);
			}
			$this.siblings().removeClass(opts.colorClass);
			$this.css({"cursor":"move"}).addClass(opts.colorClass);
			var $position = $this.position();
			var $clone = $this.clone(true);
			var $proxy = opts.proxyItem($this);
			var selfHeight = $this.height();
			var selfWidth = $this.width();
			/*高度*/
			var containerHeight = $(document).innerHeight();
			var containerWidth = $(document).innerWidth();
			var fixX = containerWidth - selfWidth;
			var fixY = containerHeight - selfHeight;
			var x = tm_posXY(e).x - $position.left;
			var y = tm_posXY(e).y - $position.top;
			$clone.addClass("tm-drag-sortHelper").css({top:$position.top,left:$position.left,width:selfWidth,height:selfHeight});
			$this.before($clone).before($proxy).hide();
			opts.before($this);
			$(document).on("mousemove",function(e){
				if(!switchDragSort)return;
				var _left = tm_posXY(e).x - x;
				var _top = tm_posXY(e).y - y;
				if(_left<=0)_left=1;
				if(_top<=0)_top=1;
				if(_left >=fixX)_left = fixX;
				if(_top >=fixY)_top = fixY;
				if(isNotEmpty(opts.arrow)){
					/*水平位置移动*/
					if(opts.arrow=='left'){
						_top = $position.top;
					}
					/*垂直方向移动*/
					if(opts.arrow=='top'){
						_left = $position.left;
					}
				}
				$clone.css({left:_left,top:_top});
				var $items = opts.parent.find(" > *").filter(':visible').filter(':not(.tm-drag-sortPlaceholder, .tm-drag-sortHelper)');
				$items.each(function(){
					var $pos = $(this).position();
					var width = $(this).width();
					var height = $(this).height();
					var posLeft = $pos.left-5;
					var posTop = $pos.top-5;
					var $lastPos = $items.last().position();
					if(_left<=width/2 && _top<=height/2){
						$items.eq(0).before($proxy);
						return false;
					}else if(_left >=$lastPos.left && _top >=$lastPos.top){
						$items.last().after($proxy);
						return false;
					}else{
						if(_left > posLeft && _left < posLeft+width && _top> posTop && _top <posTop+height/2){
							if($(this)!=undefined){
								$proxy.insertBefore($(this));
							}
						}else if(_left > posLeft && _left < posLeft + width && _top > posTop + height/2 && _top < posTop + height){
							if($(this)!=undefined){
								$proxy.insertAfter($(this));
							}
						}
					}
				});
			}).on("mouseup",function(){
				$this.insertBefore($proxy).show();
				$proxy.remove();
				$clone.remove();
				$(document).off("mousedown").off("mouseup");
				switchDragSort = false;/*还原拖选*/
				opts.callback($this);
			});
		});
	}

	$.fn.tmDragSort.defaults = {
		parent:"",/*父类容器*/
		arrow:"",/*允许的方向，top垂直方向 left水平方向*/
		proxyItem:function($item){
			return $('<'+$item[0].nodeName+' class="tm-drag-sortPlaceholder"/>').css({
				width:$item.innerWidth()+'px',
				border:"1px dashed green",
				height:$item.innerHeight()+'px',
				marginTop:$item.css('marginTop'),
				marginRight:$item.css('marginRight'),
				marginBottom:$item.css('marginBottom'),
				marginLeft:$item.css('marginLeft')
			});
		},
		to:"",
		before:function(obj){},
		callback:function(obj){},
		colorClass: "tm-items-on"
	};
	
})(jQuery)

function isEmpty(val) {
	val = $.trim(val);
	if (val == null) return true;
	if (val == undefined || val == 'undefined') return true;
	if (val == "") return true;
	if (val.length == 0) return true;
	if (!/[^(^\s*)|(\s*$)]/.test(val)) return true;
	return false;
}

function isNotEmpty(val) {
	return ! isEmpty(val);
}

function tm_posXY(event) {
	event = event || window.event;
	var posX = event.pageX || (event.clientX + (document.documentElement.scrollLeft || document.body.scrollLeft));
	var posY = event.pageY || (event.clientY + (document.documentElement.scrollTop || document.body.scrollTop));
	return {
		x: posX,
		y: posY
	};
}

function tm_forbiddenSelect() {
	$(document).bind("selectstart",
	function() {
		return false;
	});
	document.onselectstart = new Function("event.returnValue=false;");
	$("*").css({
		"-moz-user-select": "none"
	});
}