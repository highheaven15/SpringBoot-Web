<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/galleryHeader.jsp"%>

<body>

<section class="hero-wrap hero-wrap-2" style="background-image: url('/traders/images/bg_wave.jpg');" data-stellar-background-ratio="0.5">
    <div class="overlay"></div>
    <div class="container">
        <div class="row no-gutters slider-text align-items-center justify-content-center">
            <div class="col-md-9 ftco-animate text-center">
                 <p class="mb-2 bread" style="font-size:45px;">Gallery</p>
                <p class="breadcrumbs"><span class="mr-2"><a href="/main">Home <i class="ion-ios-arrow-forward"></i></a></span> <span>Gallery <i class="ion-ios-arrow-forward"></i></span></p>
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

<div class="row">
  <div class="col-lg-12">
  </div>
  <!-- /.col-lg-12 -->
</div>
<!-- /.row -->


<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">

      <!-- /.panel-heading -->
      <div class="panel-body">

        <form role="form" action="/gallery/register" method="post">
          <div class="form-group">
            <label>Title</label> <input class="form-control" name='title'>
          </div>

          <div class="form-group">
            <label>Text area</label>
            <textarea class="form-control" rows="3" name='content'></textarea>
          </div>

          <div class="form-group">
            <label>Writer</label>
            <input class="form-control" name='writer' value="${member.id}" readonly>
          </div>
          <button type="submit" class="btn btn-default">Submit Button</button>
          <button type="reset" class="btn btn-default">Reset Button</button>
        </form>

      </div>
      <!--  end panel-body -->

    </div>
    <!--  end panel-body -->
  </div>
  <!-- end panel -->
</div>
<!-- /.row -->


<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">

      <div class="panel-heading">File Attach</div>
      <!-- /.panel-heading -->
      <div class="panel-body">
        <div class="form-group uploadDiv">
            <input type="file" id="uploadFile" name="uploadFile" accept="image/*" >
            </br>
            </br>
        </div>
        
        <div class='uploadResult'>
             <ul id="uploadResult">
                 <img src="" id="result-image" class="" >
             </ul>
        </div>
        
        
      </div>
      <!--  end panel-body -->

    </div>
    <!--  end panel-body -->
  </div>
  <!-- end panel -->
</div>
<!-- /.row -->

<script>

$(document).ready(function(e){

      var formObj = $("form[role='form']");

      $("button[type='submit']").on("click", function(e){

        e.preventDefault();

        console.log("submit clicked");

        var fileName = $('#result-image').attr('class');
        var url = $('#result-image').attr('src');

        console.log(fileName);

        if(fileName != '' && url != ''){
            console.log("dto: "+ fileName);

            var str = "";


           //파일을 선택했을때 파일 업로드 처리   히든으로 첨부파일의 정보들이  보드 VO FielList에 수집됨
              str += "<input type='hidden' name='fileName' value=''>";
              str += "<input type='hidden' name='url' value=''>";

            formObj.append(str);
            $("input[name='fileName']").attr("value", fileName);
            $("input[name='url']").attr("value", url);


        }

        formObj.submit();

      });

      var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
      var maxSize = 5242880; //5MB

      function checkExtension(fileName, fileSize){

        if(fileSize >= maxSize){
              alert("파일 사이즈 초과");
              return false;
        }

        if(regex.test(fileName)){
          alert("해당 종류의 파일은 업로드할 수 없습니다.");
          return false;
        }
        return true;
      }

      $("input[type='file']").change(function(e){

            var file = $('#uploadFile')[0].files[0];
            var formData = new FormData();

            //console.log(file.name);
           //console.log(file.size);

        //확장자 체크
              if(!checkExtension(file.name, file.size) ){
                    return false;
              }

         //가상의 데이타
              formData.append('data', file);


            $.ajax({
                type: 'POST',
                url: '/gallery/upload',
                data: formData,
                processData: false,
                contentType: false
            }).done(function (data){
                console.log("data: " + data);
                showUploadResult(data);
            }).fail(function(error){
                console.log("s3 upload fail");
                alert(error);
            })

      });

      function showUploadResult(data){

            var file = $('#uploadFile')[0].files[0];
            var fileName = file.name;
            console.log(file.name);
            var uploadUL = $("#uploadResult");
            var s3URL = data;

            var extension = s3URL.slice(-3);
            console.log(extension);

            if(extension == 'jpg' || extension == 'png' ){
                 $('#result-image').attr("src", data);
                 $('#result-image').attr("class", fileName);
            } else {
                 $('#result-image').attr("src", '/resources/img/file.png');
                 $('#result-image').attr("class", fileName);
            }

      }

});

</script>

<%@include file="../includes/boardFooter.jsp"%>
