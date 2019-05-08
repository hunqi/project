
function goIntoFolder(currentFolder){
    console.log('enter folder : ' + currentFolder.text);
    location.href='/pic?folder=' + currentFolder.text;
}