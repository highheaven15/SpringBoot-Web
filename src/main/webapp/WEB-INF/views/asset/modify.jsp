<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>

<style>

#kill{
    background: #007bff;
    border: 1px solid #007bff;
    color: #fff;
    width: 150px;
    height: 50px;
}

.btn.btn-primary {
    background: #007bff;
    border: 1px solid #007bff;
    color: #fff;
    width: 250px;
    height: 50px;
}

</style>

<body>

<section class="hero-wrap hero-wrap-2" style="background-image: url('/traders/images/bg_wave.jpg');" data-stellar-background-ratio="0.5">
    <div class="overlay"></div>
    <div class="container">
        <div class="row no-gutters slider-text align-items-center justify-content-center">
            <div class="col-md-9 ftco-animate text-center">
                <p class="mb-2 bread" style="font-size:45px;">My Asset</p>
                <p class="breadcrumbs"><span class="mr-2"><a href="/main">Home <i class="ion-ios-arrow-forward"></i></a></span> <span>Asset <i class="ion-ios-arrow-forward"></i></span></p>
            </div>
        </div>
    </div>
</section>

    <!-- loader -->
    <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


    <script src="/traders/js/jquery.min.js"></script>
    <script src="/traders/js/jquery-migrate-3.0.1.min.js"></script>
    <script src="/traders/js/popper.min.js"></script>
    <script src="/traders/js/bootstrap.	min.js"></script>
    <script src="/traders/js/jquery.easing.1.3.js"></script>
    <script src="/traders/js/jquery.waypoints.min.js"></script>
    <script src="/traders/js/jquery.stellar.min.js"></script>
    <script src="/traders/js/owl.carousel.min.js"></script>
    <script src="/traders/js/jquery.magnific-popup.min.js"></script>
    <script src="/traders/js/aos.js"></script>
    <script src="/traders/js/jquery.animateNumber.min.js"></script>
    <script src="/traders/js/bootstrap-datepicker.js"></script>
    <script src="/traders/js/scrollax.min.js"></script>
    <script src="/traders/js/main.js"></script>

<section class="ftco-section">
    <div class="container">
        <div class="row justify-content-center">
            <form action="/asset/modify" method="post" id="form">
                <c:forEach items="${asset}" var="asset">
                  <div class="form-group row" >
                    <div class="modify">
                        <div class="col-xs-4">
                            <label>주식명</label> <input class="form-control" name='stock_name' value='<c:out value="${asset.stock_name}"/>' >
                        </div>

                        <div class="col-xs-4">
                            <label>1주당 가격</label> <input class="form-control" name='stock_price' value='<c:out value="${asset.stock_price}"/>' >
                        </div>

                        <div class="col-xs-4">
                            <label>주식 수량</label> <input class="form-control" name='stock_count' value='<c:out value="${asset.stock_count}"/>' >
                        </div>
                            &nbsp;&nbsp;
                            <button class='<c:out value="${asset.asset_no}"/>' type="submit" data-oper='remove' name="button" id="kill"> 삭제하기 </button>
                            <input type="hidden" name='asset_no', id='asset_no' value='<c:out value="${asset.asset_no}"/>'>
                    </div>
                    </div>
                    <br>
                </c:forEach>
                          <div id="add" class="modify">

                          </div>
            </form>

            <button type="submit" data-oper='modify' name="button" class="form-control btn btn-primary rounded submit px-3">수정하기</button>
            <br></br>&nbsp;&nbsp;&nbsp;
            <input type="button" name = "plus" id="plus" value="내 주식 추가하기" class="form-control btn btn-primary rounded submit px-3">
            <br></br>&nbsp;&nbsp;&nbsp;
            <button type="submit" data-oper='back' id="back" name="button" class="form-control btn btn-primary rounded submit px-3">뒤로가기</button>

        </div>
    </div>
</section>


</body>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>

      var totalCnt = 5;
      var curCnt = $('input[name="stock_name"]').length;
       console.log(curCnt);


        $("#plus").on("click", function(){

                if(curCnt < totalCnt) {

                         var newDiv = document.getElementById("add");

                         var output ="";

                         output += "<label>주식명</label> <input class='required' name='stock_name2' id='stock_name'>&nbsp;";
                         output += "<label>1주당 가격</label> <input class='required' name='stock_price2'  id='stock_price'>&nbsp;";
                         output += "<label>주식 수량</label> <input class='required' name='stock_count2'  id='stock_count'>&nbsp;";
                         output += "<input type='hidden' name = 'member_id' value = '${member.id}'>";
                         output += "<input type='button' name='cancel' id='kill' value='삭제하기'>";
                         output += "  </br></br>"

                         var childDiv = document.createElement("div");

                         childDiv.setAttribute("id", curCnt + 1);

                         childDiv.innerHTML =output;
                         newDiv.appendChild(childDiv);

                        curCnt = curCnt + 1;
                        console.log("curCnt" + curCnt);


                 } else if(curCnt >= totalCnt){

                      alert("최대 5개까지만 생성 가능합니다.");
                      return false;
                }

      });



        $(document).on("click", 'input[name="cancel"]', function(){
            console.log("onclick작동");

            var addDiv2 = $(this).closest("div");
            var addDiv2Name = $(this).closest("div").attr('id');
            console.log(addDiv2Name);

            console.log("삭제될 번호: " + addDiv2Name);

            addDiv2.remove();
            curCnt = curCnt - 1;
            console.log(curCnt);


      });



    var form = $("form");

    $('button[name="button"]').on("click" , function(e){
            e.preventDefault();

            var oper = $(this).data("oper");
            var asset_no = $(this).attr('class');

            var nameChk = /^[가-힣a-zA-Z0-9]{2,15}$/;
            var priceChk = /^[0-9]{4,7}$/;
            var countChk = /^[0-9]{1,4}$/;

            var stock_name =  $('input[name="stock_name"]')
            var stock_price =  $('input[name="stock_price"]')
            var stock_count =  $('input[name="stock_count"]')

            if(oper === 'modify'){

                for(i=0; i < stock_name.length; i++){

                     if(!nameChk.test(stock_name[i].value)){
                        alert("정확한 주식명을 입력해 주세요.");
                        return false;
                    }

                          for(j=i+1; j < stock_name.length; j++){
                                     if(stock_name[i].value == stock_name[j].value){
                                        console.log([i]);
                                        console.log([j]);

                                        alert("동일한 주식명이 존재합니다.");
                                        return false;
                                    }
                          }


                    if(!priceChk.test(stock_price[i].value)){
                        alert("정확한 주식 가격을 입력해 주세요.");
                        return false;
                    }
                    if(!countChk.test(stock_count[i].value)){
                        alert("정확한 주식 수량을 입력해 주세요.");
                        return false;
                    }


                 }

                 form.submit();


        } else if(oper === 'back'){

                      form.attr("action", "/asset/get").attr("method", "get");
                      form.submit();


         }else if(oper === 'remove'){

                     var asset_no = $(this).attr('class');
                     var newForm = $('<form></form>');

                     newForm.attr("method","post");
                     newForm.attr("action","/asset/remove");

                    newForm.append($('<input/>', {type: 'hidden', name: 'asset_no', value:asset_no }));
                    newForm.appendTo('body');
                    newForm.submit();

        }

    });




</script>

</html>
