//  오른쪽 방향

function awsmodal_r1(){
    $("#awsmodal1").hide();
    $("#awsmodal2").show();
    $('#portfolioModal1').animate({
        scrollTop: 0
        }, 400);
}

function awsmodal_r2(){
    $("#awsmodal2").hide();
    $("#awsmodal3").show();
    $('#portfolioModal1').animate({
        scrollTop: 0
    }, 400);
}

//  왼쪽 방향
function awsmodal_l2(){
    $("#awsmodal1").show();
    $("#awsmodal2").hide();
    $('#portfolioModal1').animate({
        scrollTop: 0
    }, 400);
}

function awsmodal_l3(){
    $("#awsmodal2").show();
    $("#awsmodal3").hide();
    $('#portfolioModal1').animate({
        scrollTop: 0
    }, 400);
}

//  닫을 때
function closeModal(){
    $("#awsmodal1").show();
    $("#awsmodal2").hide();
    $("#awsmodal3").hide();
}