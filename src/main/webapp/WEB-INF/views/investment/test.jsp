<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>

<style>
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
                 <p class="mb-2 bread" style="font-size:45px;">Test</p>
                <p class="breadcrumbs"><span class="mr-2"><a href="/main">Home <i class="ion-ios-arrow-forward"></i></a></span> <span>Test <i class="ion-ios-arrow-forward"></i></span></p>
            </div>
        </div>
    </div>
</section>

<section class="ftco-section ftco-no-pt ftco-no-pb contact-section">
    </div>
    </div>


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


<form name="investTest" id="investTest" style="margin-top:50px; margin-left:300px;" width="350px">
	<h1 >투자성향 테스트</h1>
	<br>
	<h3>1. 당신의 연령대는 어떻게 됩니까?</h3>
	<div>
		<p><input class="test" type="radio" name="age" value="12.5"> 19세 이하</p>
		<p><input class="test" type="radio" name="age" value="12.5"> 20세 ~ 40세</p>
		<p><input class="test" type="radio" name="age" value="9.3"> 41세 ~ 50세</p>
		<p><input class="test" type="radio" name="age" value="6.2"> 51세 ~ 60세</p>
		<p><input class="test" type="radio" name="age" value="3.1"> 60세 이상 </p>
	</div>
	</br>
	<h3>2. 투자하고자 하는 자금의 투자가능 기간은 얼마나 됩니까?</h3>
    	<div>
    		<p><input class="test" type="radio" name="period" value="3.1"> 6개월 이내</p>
    		<p><input class="test" type="radio" name="period" value="6.2"> 6개월 이상 ~ 1년 이내</p>
    		<p><input class="test" type="radio" name="period" value="9.3"> 1년 이상 ~ 2년 이내</p>
    		<p><input class="test" type="radio" name="period" value="12.5"> 2년 ~ 3년 이내</p>
    		<p><input class="test" type="radio" name="period" value="15.6"> 3년 이상</p>
    	</div>
      </br>
        <h3>3. 다음 중 투자경험과 가장 가까운 것은 어느 것입니까? (중복가능)</h3>
        	<div>
        		<p><input class="test" type="radio" name="experience1" value="3.1"> 은행 예금/적금, 국채, 지방채, 보증채, MMF, CMA 등</p>
        		<p><input class="test" type="radio" name="experience2" value="6.2">
                	금융채, 신용도가 높은 회사채, 채권형펀드, 원금보장형 ELS 등</p>
        		<p><input class="test" type="radio" name="experience3" value="9.3"> 신용도 중간 등급의 회사채, 원금의 일부만 보장되는 ELS, 혼합형 펀드 등</p>
        		<p><input class="test" type="radio" name="experience4" value="12.5"> 신용도가 낮은 회사채, 주식, 원금이 보장되지 않는 ELS, 시장수익률 수준의 수익을 추구하는 주식형 펀드 등</p>
        		<p><input class="test" type="radio" name="experience5" value="15.6"> ELW, 선물옵션, 시장수익률 이상의 수익을 추구하는 주식형펀드, 파생상품에 투자하는 펀드, 주식 신용거래 등</p>
        	</div>
    </br>
    <h3>4. 금융상품 투자에 대한 본인의 수준은 어느 정도라고 생각하십니까?</h3>
    	<div>
    		<p><input class="test" type="radio" name="level" value="3.1"> [매우 낮은 수준] 금융투자상품에 투자해 본 경험이 없음</p>
    		<p><input class="test" type="radio" name="level" value="6.2">
            	 [낮은 수준] 널리 알려진 금융투자상품 (주식, 채권 및 펀드 등)의 구조 및 위험을 일정부분 이해하고 있는 정도</p>
    		<p><input class="test" type="radio" name=bo"level" value="9.3"> [높은 수준] 투자할 수 있는 대부분의 금융상품의 차이를 구별할 수 있는 정도</p>
    		<p><input class="test" type="radio" name="level" value="12.5"> [매우 높은 수준] 금융상품을 비롯하여 모든 투자대상 상품의 차이를 이해할 수 있는 정도</p>
    	</div>
    </br>
            <h3>5. 현재 투자하고자 하는 자금은 전체 금융자산(부동산 등을 제외) 중 어느 정도의 비중을 차지합니까?</h3>
            	<div>
            		<p><input class="test" type="radio" name="weight" value="15.6"> 10% 이내</p>
            		<p><input class="test" type="radio" name="weight" value="12.5"> 10% ~ 20% 이내</p>
            		<p><input class="test" type="radio" name="weight" value="9.3"> 20% ~ 30% 이내</p>
            		<p><input class="test" type="radio" name="weight" value="6.2"> 30% ~ 40% 이내</p>
            		<p><input class="test" type="radio" name="weight" value="3.1"> 40% 초과</p>
            	</div>
    </br>
            <h3>6. 다음 중 당신의 수입원을 가장 잘 나타내고 있는 것은 어느 것입니까?</h3>
            	<div>
            		<p><input class="test" type="radio" name="income" value="9.3"> 현재 일정한 수입이 발생하고 있으며, 향후 현재 수준을 유지하거나 증가할 것으로 예상</p>
            		<p><input class="test" type="radio" name="income" value="6.2"> 현재 일정한 수입이 발생하고 있으나, 향후 감소하거나 불안정할 것으로 예상</p>
            		<p><input class="test" type="radio" name="income" value="3.1"> 현재 일정한 수입원이 없으며, 연금이 주 수입원임</p>

            	</div>
    </br>
            <h3>7. 만약 투자원금에 손실이 발생할 경우 다음 중 감수할 수 있는 손실 수준은 어느 것입니까?</h3>
            	<div>
            		<p><input class="test" type="radio" name="loss" value="-6.2"> 무슨 일이 있어도 투자원금은 보전되어야 한다.</p>
            		<p><input class="test" type="radio" name="loss" value="6.2"> 10% 미만까지는 손실을 감수 할 수 있을 것 같다.</p>
            		<p><input class="test" type="radio" name="loss" value="12.5"> 20% 미만까지는 손실을 감수 할 수 있을 것 같다.</p>
            		<p><input class="test" type="radio" name="loss" value="18.7"> 기대수익이 높다면 위험이 높아도 상관하지 않겠다.</p>
            	</div>
    </br>
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       <button type="button"  class="form-control btn btn-primary rounded submit px-3" id="result" onclick="sumScore()"> 결과 확인하기 </button>
       <br>
    </br>

</form>
</body>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>


 function sumScore(){
          var sum = 0;
          var radio = document.getElementsByClassName("test");
          var chkCnt = 0;

           for(i=0; i<radio.length; i++ ){
                if(radio[i].checked == true){
                    sum += parseInt(radio[i].value);
                    chkCnt++;
                }
           }

           if(chkCnt < 6){
                alert("모든 문항에 체크해야 합니다");
                return;
           }


        console.log(sum);
        resultChk(sum);

 }



    function resultChk(sum){

        var output="";


              if(sum <= 20){
                   output +="<img src='/resources/img/test_icon5.png' alt='안정형'/>";
                   output +="<h1>안정형</h1><hr>";
                   output += "<h3>당신은 안정형입니다.</h3></br>";
                   output +="<p>예금이나 적금 수준의 수익률을 기대하며, 투자원금에 손실을 발생하는 것을 원하지 않습니다.</br> 원금손실의 우려가 없는 상품에 투자하는 것이 바람직하며 CMA와 MMF가 좋습니다.</p></br>";
                   output +="<h3>추천 상품</h3>";
                   output +="<p>벤처투자X</p>";
                   output +="</br></br></br></br>";
                   document.getElementById("investTest").innerHTML =output;
                

              }else if(20 < sum && sum <= 40){
                       output +="<img src='/resources/img/test_icon4.png' alt='안정추구형'/>";
                       output +="<h1>안정추구형</h1><hr>";
                       output +="<h3>당신은 안정추구형입니다.</h3></br>";
                       output +="<p>투자원금의 손실위험은 최소화하고, 이자소득이나 배당소득 수준의 안정적인 투자를 목표로 합니다.</br>다만 수익을 위해 단기적인 손실을 수용할 수 있으며,</br>예금/적금보다 높은 수익을 위해 자산 중의 일부를 변동성 높은 상품에 투자할 의향이 있습니다.</br>채권형 펀드가 적당하며, 그 중에서도 장기회사채펀드 등이 좋습니다.</p></br>";
                       output +="<h3>추천 상품</h3>";
                       output +="<p>원칙:벤처투자X</p> <p>예외:소득공제+채권형</p>";
                        output +="</br></br></br></br>";
                       document.getElementById("investTest").innerHTML =output;

              }else if(40 < sum && sum <= 60){
                       output +="<img src='/resources/img/test_icon3.png' alt='위험중립형'/>";
                       output +="<h1>위험중립형</h1><hr>";
                       output +="<h3>당신은 위험중립형입니다.</h3></br>";
                       output +="<p>투자에는 그에 상응하는 투자위험이 있음을 충분히 인식하고 있으며,</br>예.적금보다 높은 수익을 기대할 수 있다면 일정수준의 손실위험을 감수할 수 있습니다.</br>적립식펀드나 주가연동상품처럼 중위험 펀드로 분류되는 상품을 선택하는 것이 좋습니다.</p></br>";
                       output +="<h3>추천 상품</h3>";
                       output +="<p>전환사채</p> <p>신주인수권부사채</p>";
                       output +="</br></br></br></br>";
                       document.getElementById("investTest").innerHTML =output;


              }else if(60 < sum && sum <= 80){
                       output +="<img src='/resources/img/test_icon2.png' alt='적극투자형'/>";
                       output +="<h1>적극투자형</h1><hr>";
                       output +="<h3>당신은 적극투자형입니다.</h3></br>";
                       output +="<p>투자원금의 보전보다는 위험을 감내하더라도 높은 수준의 투자수익을 추구합니다.</br>투자자금의 상당 부분을 주식, 주식형편드 또는 파생상품 등의 위험자산에 투자할 의향이 있습니다.</br>국내외 주식형펀드와 원금비보장형 주가연계증권(ELS) 등 고수익, 고위험 상품에 투자할 수 있습니다.</p></br>";
                       output +="<h3>추천 상품</h3>";
                       output +="<p>상환전환우선주</p>";
                       output +="</br></br></br></br>";
                      document.getElementById("investTest").innerHTML =output;


              }else{
                       output +="<img src='/resources/img/test_icon1.png' alt='공격투자형'/>";
                       output +="<h1>공격투자형</h1><hr>";
                       output +="<h3>당신은 공격투자형입니다.</h3></br>";
                       output +="<p>시장평균수익률을 훨씬 넘어서는 높은 수준의 투자수익을 추구하며</br>이를 위해 자산가치의 변동에 따른 손실위험을 적극 수용할 수 있습니다.</br>투자자금 대부분을 주식, 주식형펀드 또는 파생상품 등의 위험자산에 투자할 의향이 있습니다.</br>주식 비중이 70% 이상인 고위험 펀드가 적당하고, 자산의 10%정도는 직접투자(주식)도 고려해 볼만합니다.</p></br>";
                       output +="<h3>추천 상품</h3>";
                       output +="<p>보통주</p> <p>이익참가부사채</p>";
                        output +="</br></br></br></br>";
                       document.getElementById("investTest").innerHTML =output;

              }

}


</script>


</html>
