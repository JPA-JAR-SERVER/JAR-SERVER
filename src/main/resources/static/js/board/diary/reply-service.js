let replyService = (function(){

    function save(reply, callback){
        $.ajax({
            url : '/diary/reply/save',
            type: "post",
            data: JSON.stringify(reply),
            contentType: "application/json;charset=utf-8",
            success: function () {
                if (callback) {
                    callback();
                }
            }
        });
    }

    function list(reply, callback){
        $.ajax({
            url : '/diary/reply/list',
            type: 'get',
            data: reply,
            success : function(replies){
                if(callback) {
                    callback(replies);
                }
            }
        });
    }

    function modify(reply, callback){
        $.ajax({
            url : '/diary/reply/update',
            type : 'patch',
            data : reply,
            success : function(){
                if(callback){
                    callback();
                }
            }
        });
    }

    function deleteReply(reply, callback){
        $.ajax({
            url : '/diary/reply/delete',
            type : 'delete',
            data : reply,
            success : function(){
                if(callback){
                    callback();
                }
            }
        });
    }

    return {
        save : save,
        list : list,
        modify : modify,
        deleteReply : deleteReply
    }
})();