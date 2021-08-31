console.log("****** member.js ******");

var memberService =(function(){

        function get(member_id, callback, error){

            $.get("/admin/"+ member_id, function(result){
                        if(callback){
                            callback(result);
                        }

                    }).fail(function(xhr, status, err){
                        if(error){
                            error();
                        }
                    });
        }

        function update(member, callback, error){

            console.log("MNO:" + member.member_no);

            $.ajax({
                type : 'PUT',
                url : '/admin/' + member.member_no,
                data : JSON.stringify(member),
                contentType : "application/json; charset=utf-8",
                success : function(result, status, xhr){
                    if(callback){
                        callback(result);
                    }
                },
                error : function(xhr, status, er){
                    if(error){
                        error(er);
                    }
                }
            });
        }


        function remove(member_no, callback, error){
            console.log(member_no+"번이 삭제 되었습니다.");

            $.ajax({
                 type : 'DELETE',
                 url : '/admin/delete/'+ member_no,
                 success : function(deleteResult, status, xhr){
                    if(callback){
                        callback(deleteResult);
                    }
                 },
                 error : function(xhr, status, er){
                    if(error){
                        error(er);
                    }
                 }
            });

        }

 return {
    get : get,
    update : update,
    remove : remove
 };

})();
