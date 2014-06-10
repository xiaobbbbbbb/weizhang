$(function(){

    //所有的ajax form提交,由于大多业务逻辑都是一样的，故统一处理
    var ajaxForm_list = $('form.J_ajaxForm');
    if (ajaxForm_list.length) {
        Wind.use('ajaxForm', 'artDialog','toJSON', function () {
            if ($.browser.msie) {
                //ie8及以下，表单中只有一个可见的input:text时，会整个页面会跳转提交
                ajaxForm_list.on('submit', function (e) {
                    //表单中只有一个可见的input:text时，enter提交无效
                    e.preventDefault();
                });
            }

            $('button.J_ajax_submit_btn').on('click', function (e) {
                e.preventDefault();
                var btn = $(this),
                    form = btn.parents('form.J_ajaxForm');

                //批量操作 判断选项
                if (btn.data('subcheck')) {
                    btn.parent().find('span').remove();
                    if (form.find('input.J_check:checked').length) {
                        var msg = btn.data('msg');
                        if (msg) {
                            art.dialog({
                                id: 'warning',
                                icon: 'warning',
                                content: btn.data('msg'),
                                cancelVal: '关闭',
                                cancel: function () {
                                    //btn.data('subcheck', false);
                                    //btn.click();
                                },
                                ok: function () {
                                	 btn.data('subcheck', false);
                                	 btn.click();
                                }
                            });
                        } else {
                            btn.data('subcheck', false);
                            btn.click();
                        }

                    } else {
                        $('<span class="tips_error">请至少选择一项</span>').appendTo(btn.parent()).fadeIn('fast');
                    }
                    return false;
                }

                var actionUrl = btn.data('action') ? btn.data('action') : form.attr('action'); //按钮上是否自定义提交地址(多按钮情况)
                form.ajaxSubmit({
                    url: actionUrl,
                    dataType: 'json',
                    beforeSubmit: function (arr, $form, options) {
                        var text = btn.text();
                        //按钮文案、状态修改
                        btn.text(text + '中...').prop('disabled', true).addClass('disabled');
                    },
                    success: function (data, statusText, xhr, $form) {
                        var text = btn.text();

                        //按钮文案、状态修改
                        btn.removeClass('disabled').text(text.replace('中...', '')).parent().find('span').remove();
                        if (data.success) {
                            $('<span class="tips_success">' + data.msg + '</span>').appendTo(btn.parent()).fadeIn('slow').delay(1000).fadeOut(function () {
                            	var pos = actionUrl.indexOf("saveUpdate");
                            	if (pos > 0)
                            		data.referer=actionUrl.substr(0, pos);

                            	if (data.referer) {
                                    //返回带跳转地址
                            		window.location.href = data.referer;
                                } else {
                                    if(window.parent.art){
                                        reloadPage(window.parent);
                                    }else{
                                        //刷新当前页
                                        reloadPage(window);
                                    }
                                }
                            });
                        } else if (!data.success) {
                            $('<span class="tips_error">' + data.msg + '</span>').appendTo(btn.parent()).fadeIn('fast');
                            btn.removeProp('disabled').removeClass('disabled');
                        }
                    }
                });
            });

        });
    } 
    
    //所有的删除操作，删除数据后刷新页面
    if ($('a.J_ajax_del').length) {
        Wind.use('artDialog','iframeTools', function () {
            $('.J_ajax_del').on('click', function (e) {
                e.preventDefault();
                var $_this = this,
                    $this = $($_this),
                    href = $this.prop('href'),
                    msg = $this.data('msg');
                var throughBox = art.dialog.through;
                throughBox({
                    icon: 'question',
                    content: '确定要删除吗？',
                    close: function () {
                        $_this.focus();; //关闭时让触发弹窗的元素获取焦点
                        return true;
                    },
                    ok: function () {
                        $.getJSON(href).done(function (data) {
                            if (data.success) {
                                if (data.referer) {
                                    location.href = data.referer;
                                } else {
                                    reloadPage(window);
                                }
                            } else{
                                art.dialog.alert(data.msg);
                            }
                        });
                    },
                    cancel: true
                });
            });

        });
    }
    
    if ($('.datepicker').length) {
    	Wind.use('datePicker', function () {
    		$('.datepicker').datetimepicker();
    	});
    }
   
});

// 重新刷新页面，使用location.reload()有可能导致重新提交
function reloadPage(win) {
    var location = win.location;
    location.href = location.pathname + location.search;
}

// 页面跳转
function redirect(url) {
    location.href = url;
}

// 读取cookie
function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1, c.length);
        }
        if (c.indexOf(nameEQ) == 0) {
            return c.substring(nameEQ.length, c.length);
        }
    };

    return null;
}

// 设置cookie
function setCookie(name, value, days) {
    var argc = setCookie.arguments.length;
    var argv = setCookie.arguments;
    var secure = (argc > 5) ? argv[5] : false;
    var expire = new Date();
    if(days==null || days==0) days=1;
    expire.setTime(expire.getTime() + 3600000*24*days);
    document.cookie = name + "=" + escape(value) + ("; path=/") + ((secure == true) ? "; secure" : "") + ";expires="+expire.toGMTString();
}

// 浮出提示_居中
function resultTip(options) {

    var cls = (options.error ? 'warning' : 'success');
    var pop = $('<div style="left:50%;top:30%;" class="pop_showmsg_wrap"><span class="pop_showmsg"><span class="' + cls + '">' + options.msg + '</span></span></div>');

    pop.appendTo($('body')).fadeIn(function () {
        pop.css({
            marginLeft: -pop.innerWidth() / 2
        }); // 水平居中
    }).delay(1500).fadeOut(function () {
        pop.remove();

        // 回调
        if (options.callback) {
            options.callback();
        }
    });

}

// 弹窗居中定位 非ie6 fixed定位
function popPos(wrap) {
    var ie6 = false,
        pos = 'fixed',
        top,
        win_height = $(window).height(),
        wrap_height = wrap.outerHeight();

    if ($.browser.msie && $.browser.version < 7) {
        ie6 = true;
        pos = 'absolute';
    }

    if (win_height < wrap_height) {
        top = 0;
    } else {
        top = ($(window).height() - wrap.outerHeight()) / 2;
    }

    wrap.css({
        position: pos,
        top: top + (ie6 ? $(document).scrollTop() : 0),
        left: ($(window).width() - wrap.innerWidth()) / 2
    }).show();
}


/*
 * 头像的错误处理
 */
function avatarError(avatars) {
    avatars.each(function () {
        this.onerror = function () {
            this.onerror = null;
            this.src = GV.URL.IMAGE_RES + '/face/face_' + $(this).data('type') + '.jpg'; // 替代头像
            this.setAttribute('alt', '默认头像');

            // 隐藏恢复默认头像
            $('#J_set_def').hide();
        }
        this.src = this.src;
    });
}

// 新窗口打开
function openWinx(url,name,w,h) {
    if(!w) w=screen.width;
    if(!h) h=screen.height;
    // window.open(url,name,"top=100,left=400,width=" + w + ",height=" + h +
	// ",toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no");
    window.open(url,name);
}

// 询问
function confirmUrl(url, message) {
    Wind.use("artDialog", "iframeTools", function () {
        art.dialog.confirm(message, function () {
            location.href = url;
        }, function () {
            art.dialog.tips('你取消了操作');
        });
    });
}

// 选择弹出层
// obj 当前的对象
// title 选择器的标题
// url 定位的url地址
// toObj 如果有对应的对象,此对象的名称
// multipleChoice 多选
function input_popup(obj, title, url, toObj, multipleChoice) {
	url = GV.HOST + url;
	if (toObj != null) {
		var $toObj = $("#"+toObj);
		url += "?" + $toObj.attr("name") + "=" + $toObj.val();
	}
	Wind.use("artDialog", "iframeTools", function() {
		art.dialog.open(url, {
			width : 680,
			height : 420,
			title : '选择' + title,
			ok : function() {
				var form = $(this.iframe.contentWindow.document);
				var arrayId = new Array();
				var arrayVal = new Array();
				// 多选
				if (multipleChoice) {
					// 多选
					var checks = form.find("input[name='pk_id']:checkbox");
					checks.each(function() {
						// 判断是否选中
						if ($(this).attr("checked")) {
							arrayId.push($(this).val()); // 将选中的值添加到array中
							arrayVal.push($(this).next().val());
						}
					});
				} else {
					// 单选
					var radio = form.find("input[name='pk_id']:checked");
					if (radio.size() > 0) {
						arrayId.push(radio.val()); // 将选中的值添加到array中
						arrayVal.push(radio.next().val());
					}
				}

				if (arrayId.length > 0) {
					$("#" + obj).val(arrayId.join(","));
					$("#_" + obj).val(arrayVal.join(","));
				} else {
					art.dialog.alert("请先选中记录!");
					return false;
				}
			},
			cancel : true
		});
	});
}