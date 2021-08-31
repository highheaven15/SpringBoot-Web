window.onload = function() {


var member_id = sessionStorage.getItem("member_id");

console.log(member_id);


$.ajax({
            url: '/admin/adminChk',
            type: 'POST',
            data: member_id,
            contentType: "application/json; charset=UTF-8",
            success: function(check){
                console.log(check);
                if(check == '1'){
                    $("#admin").show();
                } else if(check == '0'){
                    $("#admin").remove();
                }
            },
            error: function(){
            console.log("fail");
            }
});


$.ajax({
            url : '/asset/assetChk',
            type: 'POST',
            data: member_id,
            contentType : "application/json; charset=UTF-8",
            success : function(result){
            console.log("result::" + result);

                if(result == '1'){
                     $("#asRegBtn").remove();

                } else if(result == '0'){
                     $("#asGetBtn").remove();
                }
            },
            error: function(){
            console.log("fail");
            }
        });



console.log("memberId::"+member_id);
$.ajax({
            url : '/account/accountChk',
            type: 'POST',
            data: member_id,
            contentType : "application/json; charset=UTF-8",
            success : function(result1){
            console.log("result1::" + result1);

                if(result1 == '1'){
                     $("#acRegBtn").remove();

                } else if(result1 == '0'){
                     $("#acGetBtn").remove();
                }
            },
            error: function(){
            console.log("fail");
            }
        });

    if(member_id == null ) {
         $("#logoutBtn").remove();
    }else if(member_id != null ) {
         $("#loginBtn").remove();
    }
}