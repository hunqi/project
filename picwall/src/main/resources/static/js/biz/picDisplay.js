window.onbeforeunload=function(){
    document.getElementById('pageNumber').value = 1;
};

function show(prevIdx, nextIdx, thisBtn){
    var pageSize = document.getElementById('pageSize').value,
        pageButtons = document.getElementsByClassName('pageBtn'),
        pageNumberVal = Number.parseInt(document.getElementById('pageNumber').value);

    //for prev button
    if(prevIdx == 0 && pageNumberVal > 1){
        debugger
        document.getElementById('pageNumber').value = pageNumberVal - 1;
        Ajax.get('/pager?size='+ pageSize +'&number='+ (pageNumberVal - 1) +'', function(){
            if(xmlHttp && xmlHttp.response){
                var pics = JSON.parse(xmlHttp.response);
                if(!pics.length){
                    document.getElementById('nextBtn').hidden=true;
                }else{
                    var pagesInnerHtml='';

                    for(var i=0, len=pics.length; i<len; i++){
                        var tempPageBtn = '<button onclick="show(null, null, this)" seq="' + i + '" class="pageBtn">'+(i+1)+'</button>';
                        var tempHiddenVal = '<input type="hidden" value="'+ pics[i].content +'"/>';
                        var tempSpan = '<span>'+ tempPageBtn + tempHiddenVal +'</span>';
                        pagesInnerHtml += tempSpan;
                    }

                    document.getElementById('pages').innerHTML=pagesInnerHtml;
                    document.getElementsByClassName('pageBtn')[0].click();
                }
            }else{
                console.log('no result found');
            }
        });
        return;
    }

    if(nextIdx == pageSize){
        document.getElementById('pageNumber').value = pageNumberVal + 1;

        Ajax.get('/pager?size='+ pageSize +'&number='+ (pageNumberVal + 1), function(){
            if(xmlHttp && xmlHttp.response){
                var pics = JSON.parse(xmlHttp.response);
                if(!pics.length){
                    document.getElementById('nextBtn').hidden=true;
                }else{
                    var pagesInnerHtml='';

                    for(var i=0, len=pics.length; i<len; i++){
                        var tempPageBtn = '<button onclick="show(null, null, this)" seq="' + i + '" class="pageBtn">'+(i+1)+'</button>';
                        var tempHiddenVal = '<input type="hidden" value="'+ pics[i].content +'"/>';
                        var tempSpan = '<span>'+ tempPageBtn + tempHiddenVal +'</span>';
                        pagesInnerHtml += tempSpan;
                    }

                    document.getElementById('pages').innerHTML=pagesInnerHtml;
                    document.getElementsByClassName('pageBtn')[0].click();
                }
            }else{
                console.log('no result found');
            }
        });
        return;
    }

    var currentItem = document.getElementsByClassName('selected')[0];
    if(currentItem){
        currentItem.className= currentItem.className.replace(' selected', '');
    }

    var toClickedButton;
    if(thisBtn)
        toClickedButton = thisBtn;
    else{
        if(prevIdx !== null)
            toClickedButton = pageButtons[prevIdx - 1];
        else
            toClickedButton = pageButtons[nextIdx];
    }

    toClickedButton.className=toClickedButton.className + ' selected';
    document.getElementById('currentImage').src = toClickedButton.nextElementSibling.value;

    var seqNo = Number.parseInt(toClickedButton.attributes['seq'].value);

    // prev button
    if(seqNo == 0 && pageNumberVal == 1)
        document.getElementById('prevBtn').hidden=true;
    else
        document.getElementById('prevBtn').hidden=false;

    //next button
    var pageSize = document.getElementById('pageSize').value;
    if(pageButtons.length < pageSize && (seqNo + 1) == pageButtons.length){
        document.getElementById('nextBtn').hidden=true;
    } else {
        document.getElementById('nextBtn').hidden=false;
    }
}

function next(){
    var currentItem = document.getElementsByClassName('selected')[0],
        pageButtons = document.getElementsByClassName('pageBtn'),
        pageSize = document.getElementById('pageSize').value;

    var seqNo = 0;
    if(currentItem){
        seqNo = Number.parseInt(currentItem.attributes['seq'].value) + 1;
    }

    show(null, seqNo);
}

function prev(){
    var currentItem = document.getElementsByClassName('selected')[0],
        seqNo = Number.parseInt(currentItem.attributes['seq'].value);

    show(seqNo, null);
}