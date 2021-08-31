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

</section>

<section class="ftco-section">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-7 col-lg-5">
                <div class="login-wrap p-4 p-md-5">

                    <form action="/account/register" method="post" id="form">

                        <label>사용할 계좌번호</label><input type="text" class="form-control rounded-left" name='account_number' value='' readonly>
                        <br>
                        <label>사용할 계좌 비밀번호 (4자리 입력)</label><input type="text" class="form-control rounded-left" name='account_pw'>
                        <br>
                        <label>주민번호 앞자리 (6자리 입력)</label><input type="text" class="form-control rounded-left" name='birth'>
                        <br>
                        <label>연락처 (숫자만 입력)</label><input type="text" class="form-control rounded-left" name='phone_number'>
                        <br>
                        <input type="hidden" name= "member_id" value = "${member.id}">

                        <input type="submit" class="form-control btn btn-primary rounded submit px-3" value="등록하기">

                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

</body>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
    $(document).ready(function(){

        var n=6;

        function generateRandomCode(n) {

              let str = '';

              for (let i = 0; i < n; i++) {
                str += String(Math.floor(Math.random() * 10))
              }

          //console.log(typeof(str));
           $('input[name="account_number"]').val(str);
        }

        generateRandomCode(n);

    });

    $("#form").submit(function(e){
        e.preventDefault();

        var form = document.getElementById("form");

        var pwChk = /^[0-9]{4,4}$/;
        var birthChk = /^[a-z0-9_-]{6,6}$/;
        var numberChk = /^[a-z0-9_-]{8,11}$/;

        var account_pw =  $('input[name="account_pw"]')

        //console.log( account_pw.val());
        var birth =  $('input[name="birth"]')
        var phone_number =  $('input[name="phone_number"]')

        if(!account_pw.val()){
            alert("비밀번호를 입력해 주세요");
        }

        if(!pwChk.test(account_pw.val())){
            alert("정확한 계좌 비밀번호을 입력해 주세요.");
            return false;
        }

        if(!birth.val()){
            alert("주민등록번호 앞자리를 입력해 주세요");
        }

        if(!birthChk.test(birth.val())){
            alert("주민등록번호 앞자리를 입력해 주세요.");
            return false;
        }

        if(!phone_number.val()){
             alert("연락처를 입력해 주세요");
        }

        if(!numberChk.test(phone_number.val())){
            alert("연락처를 입력해 주세요.");
            return false;
        }

       form.submit(); //정지

       alert("계좌 개설 완료되었습니다.");
   });



</script>
