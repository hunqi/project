function setDownEventForEnterKey(callback){
	document.onkeydown = function (e) {
      if (!e) e = window.event;
      if ((e.keyCode || e.which) == 13) {
//    	  document.getElementById('btnGenerate').click();
    	  callback();
      }
    }
}

var Ajax = {
    get : function(url, callback, args) {
    	this.request(url, 'GET', callback, args);
    },

    post : function(url, callback, args) {
    	this.request(url, 'POST', callback, args);
    },

    del : function(url, callback, args) {
    	this.request(url + '?' + args, 'DELETE', callback);
    },

    request : function(url, requestType, callback, args) {
    	xmlHttp = createXMLHttpRequest();
    	xmlHttp.open(requestType, url, true);// 异步处理返回
    	xmlHttp.onreadystatechange = function() {
    		if (xmlHttp.readyState == 4 && xmlHttp.status == 200 && callback) {
    			callback();
    		}
    	};

    	xmlHttp.setRequestHeader('Content-Type', 'application/json;');

    	if(requestType==='POST' || requestType === 'DELETE'){
    		xmlHttp.setRequestHeader('X-CSRF-TOKEN', document.getElementsByName('_csrf')[0].content);
    	}

    	if(!args){
    		xmlHttp.send();
    	}else{
    	    xmlHttp.send(args);
    	}

    	function createXMLHttpRequest() {
            var xmlHttp;
            if (window.XMLHttpRequest) {
                xmlHttp = new XMLHttpRequest();
                if (xmlHttp.overrideMimeType)
                    xmlHttp.overrideMimeType('text/text');
            } else if (window.ActiveXObject) {
                try {
                    xmlHttp = new ActiveXObject('Msxml2.XMLHTTP');
                } catch (e) {
                    try {
                        xmlHttp = new ActiveXObject('Microsoft.XMLHTTP');
                    } catch (e) {
                    }
                }
            }
            return xmlHttp;
        }
    }
};

//function get(url, callback) {
//	request(url, 'GET', callback);
//}
//
//function post(url, callback, args) {
//	request(url, 'POST', callback, args);
//}
//
//function del(url, callback, args) {
//	request(url + '?' + args, 'DELETE', callback);
//}
//
//function request(url, requestType, callback, args) {
//	xmlHttp = createXMLHttpRequest();
//	xmlHttp.open(requestType, url, true);// 异步处理返回
//	xmlHttp.onreadystatechange = function() {
//		if (xmlHttp.readyState == 4 && xmlHttp.status == 200 && callback) {
//			callback();
//		}
//	};
//
//	xmlHttp.setRequestHeader('Content-Type',
//			'application/json;');
//
//	if(requestType==='POST'){
//		xmlHttp.setRequestHeader('X-CSRF-TOKEN', document.getElementsByName('_csrf')[0].content);
//	}
//
//	if(!args){
//		xmlHttp.send();
//	}else{
//	    xmlHttp.send(args);
//	}
//}
//
//function createXMLHttpRequest() {
//	var xmlHttp;
//	if (window.XMLHttpRequest) {
//		xmlHttp = new XMLHttpRequest();
//		if (xmlHttp.overrideMimeType)
//			xmlHttp.overrideMimeType('text/text');
//	} else if (window.ActiveXObject) {
//		try {
//			xmlHttp = new ActiveXObject('Msxml2.XMLHTTP');
//		} catch (e) {
//			try {
//				xmlHttp = new ActiveXObject('Microsoft.XMLHTTP');
//			} catch (e) {
//			}
//		}
//	}
//	return xmlHttp;
//}
