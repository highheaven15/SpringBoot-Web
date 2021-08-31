<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>

<body>
<section class="hero-wrap hero-wrap-2" style="background-image: url('/traders/images/bg_wave.jpg');" data-stellar-background-ratio="0.5">
    <div class="overlay"></div>
    <div class="container">
        <div class="row no-gutters slider-text align-items-center justify-content-center">
            <div class="col-md-9 ftco-animate text-center">
                <p class="mb-2 bread" style="font-size:45px;">My Account</p>
                <p class="breadcrumbs"><span class="mr-2"><a href="/main">Home <i class="ion-ios-arrow-forward"></i></a></span> <span>My account <i class="ion-ios-arrow-forward"></i></span></p>
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
            <div class="col-md-7 col-lg-5">
                <div class="login-wrap p-4 p-md-5">

                    <form action="/account/transfer" method="post" id="transferForm">

                        <label>나의 계좌 잔고</label><input type="text" id="balance" name="balance" class="form-control rounded-left" value='<c:out value="${account.balance}"/>' readonly>
                        <br>
                        <label>받는사람의 계좌번호를 입력하세요</label><input type="text" name="account_number" id="account_number" class="form-control rounded-left">
                        <br>
                        <label>이체할 금액을 입력하세요</label><input type="text" name="money" id="money" class="form-control rounded-left">
                        <br>
                        <input type="hidden" name="member_id" value="${member.id}">

                        <button type="submit" class="form-control btn btn-primary rounded submit px-3">이체하기</button>
                        <br></br>
                        <a href="/account/get" class="form-control btn btn-primary rounded submit px-3">뒤로가기</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
</body>




<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>

    var alert_message = `${alert}`;
    console.log(alert_message);

    if(alert_message != ""){
      alert(alert_message);
    }

    $("#transferForm").submit(function(e){
        e.preventDefault();

                var moneyChk = /^[0-9]/g;

                var money =  $('input[name="money"]').val();
                console.log(money);

                var result = money.replace(moneyChk, "");

                if(!moneyChk.test(money)){
                    alert("정확한 숫자를 입력해 주세요.");
                    return false;
                }

        var account_number = $("#account_number").val();
        console.log(account_number);
        var isFalse = false;

        $.ajax({
            url : '/account/existenceChk',
            type : 'POST',
            data : account_number,
            contentType : "application/json; charset=UTF-8",

            success : function(result){
                console.log(result);
                if(result == '1'){
                    $("#transferForm").unbind().submit();
                    alert("이체 완료되었습니다.");
                    //alert("계좌번호가 존재합니다");
                    return false; //alert을 취소시킨다.

                }else if(result == '0'){
                     isFalse = true;
                     alert("받는 사람의 계좌번호가 존재하지 않습니다");
                     return false;
                }
           },
        });


    });

</script>