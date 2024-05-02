$(document).on("pageshow","#addnote_index",function () {
    var $header=$("#add"); //添加按钮
    var $rdotype=$("input[type='radio']"); //单选框
    var $hidtype=$("#hidtype"); //类型
    var $txtcontents=$("#txta-content"); //内容
    var $txttitle=$("#txta-title"); //标题

    $rdotype.on("change",function (){
        $hidtype.val(this.value);
    })
    $header.on("click",function (){

    })

})