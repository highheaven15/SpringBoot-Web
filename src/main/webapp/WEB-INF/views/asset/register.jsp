<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>

<style>

.btn.btn-primary {
     width: 350px;
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

            <form action="/asset/register" method="post" id="form" ">
                <div class="itr" id="asset">
                        <label>주식명</label> <input class="required" name='stock_name' id="stock_name" class="form-control rounded-left">
                        <label>1주당 가격</label> <input class="required" name='stock_price' id="stock_price" class="form-control rounded-left">
                        <label>주식 수량</label> <input class="required" name='stock_count' id="stock_count" class="form-control rounded-left">
                       <input type="hidden" name= "member_id" value = "${member.id}">
                </div>
                </br>
                <div class="itr" id="asset">
                        <label>주식명</label> <input class="required" name='stock_name' id="stock_name">
                        <label>1주당 가격</label> <input class="required" name='stock_price' id="stock_price">
                        <label>주식 수량</label> <input class="required" name='stock_count' id="stock_count">
                        <input type="hidden" name = "member_id" value = "${member.id}">
                </div>
                </br>
                <div class="itr" id="asset">
                        <label>주식명</label> <input class="required" name='stock_name' id="stock_name">
                        <label>1주당 가격</label> <input class="required" name='stock_price' id="stock_price">
                        <label>주식 수량</label> <input class="required" name='stock_count' id="stock_count">
                        <input type="hidden" name = "member_id" value = "${member.id}">
                        <input type="button" name = "plus" id="plus" value="+">
                </div>
                </br>
                <div id="add" id="asset">
                </div>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="submit" value="등록하기" class="form-control btn btn-primary rounded submit px-3" width="300px">
            </form>
        </div>
    </div>
</section>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
    $(document).ready(function(){

                 var maxAppend=0;

               $("#plus").on("click", function(){

                       if(maxAppend <= 1) {

                            var newDiv = document.getElementById("add");

                            var output ="";

                            output += "<label>주식명</label> <input class='required' name='stock_name' id='stock_name'>&nbsp;";
                            output += "<label>1주당 가격</label> <input class='required' name='stock_price'  id='stock_price'>&nbsp;";
                            output += "<label>주식 수량</label> <input class='required' name='stock_count'  id='stock_count'>&nbsp;";
                            output += "<input type='hidden' name = 'member_id' value = '${member.id}'>";
                            output += "</br></br>"


                            var childDiv = document.createElement("div");
                             childDiv.innerHTML =output;
                             newDiv.appendChild(childDiv);


                        }
                        else if(maxAppend > 1){

                             alert("최대 5개까지만 생성 가능합니다.");
                             return false;
                       }
                       maxAppend++;
                       console.log(maxAppend);
             });



    });


    $("#form").submit(function(e){
        e.preventDefault();

        var form = document.getElementById("form");

        var nameChk = /^[가-힣a-zA-Z0-9]{2,15}$/;
        var priceChk = /^[0-9]{4,7}$/;
        var countChk = /^[0-9]{1,4}$/;

        var stock_name =  $('input[name="stock_name"]')
        var stock_price =  $('input[name="stock_price"]')
        var stock_count =  $('input[name="stock_count"]')

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
    });






</script>
</html>



