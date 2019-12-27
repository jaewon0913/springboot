function checkid(){
    //  아이디 공백 확인
    if($("#userid").val() == ""){
        alert("아이디를 입력해주세요.");
        $("#userid").focus();
    } else if ($("#userid").val().includes("admin") || $("#userid").val().includes("ADMIN")) {
        alert("admin이 들어간 ID는 사용 하실 수 없습니다.");
        $("#userid").val("");
        $("#userid").focus();
    } else {
        var checkid = $('#userid').val();

        $.ajax({
            type: 'get',
            url: '/user/checkid/' + checkid,
            dataType: 'text',
            contentType: 'application/json; charset=utf-8',
            success: function (msg) {

                if(msg == "ok"){
                    $("#useridCheck").html("사용 가능합니다.");
                    $("#email").focus();
                } else if(msg == "no"){
                    alert("이미 아이디가 존재합니다. 다시입력해주세요.");
                    $("#userid").val("");
                    $("#userid").focus();
                }},
            error: function (error) {
                alert(error);
            }
        });
    }

}

var user = {
    init : function () {
        var _this = this;
        var emailCheck = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

        $('#email').blur(function (){
            if ($('#email').val() == "" || emailCheck.test($('#email').val())){
                $('#password').focus();
            } else {
                alert("이메일 형식이 아닙니다. 다시 입력해주세요.");
                $('#email').focus();
                $('#email').val("");
            }
        });

        //  비밀번호가 일치한지 확인
        $("#alert-success").hide();
        $("#alert-danger").hide();

        $('#password_confirmation').keyup(function (){
            var password = $('#password').val();
            var password_conf = $('#password_confirmation').val();
            if(password == password_conf){
                $("#alert-success").show();
                $("#alert-danger").hide();
                $('#user-save').removeAttr('disabled');
            } else {
                $("#alert-success").hide();
                $("#alert-danger").show();
                $('#user-save').attr('disabled','disabled');
            }
        })

        // $('#useridCheck').on('click',function() {
        //     _this.checkId();
        // })

        $('#user-save').on('click', function () {
            if($('#useridCheck').html() == "ID check"){
                alert("아이디 중복체크를 해주세요.");
            } else {
                if($('#name').val() != "" && $('#userid').val() != ""
                    && $('#email').val() != "" && $('#password').val() != ""){
                    _this.insert();
                } else {
                    alert("모든 칸을 입력해주세요.");
                }
            }
        });
    },
    insert : function () {
        var data = {
            name: $('#name').val(),
            userid: $('#userid').val(),
            password: $('#password').val(),
            email: $('#email').val()
        };

        $.ajax({
            type: 'POST',
            url: '/user/insert',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('회원가입되었습니다. 환영합니다.');
            location.replace('/');
        }).fail(function (error) {
            alert("회원가입에 실패하였습니다.");
        });
    },
    checkId : function (){
        alert("idcheck 눌림");

        //  아이디 공백 확인
        if($("#userid").val() == ""){
            alert("아이디를 입력해주세요.");
            $("#userid").focus();
        } else {
            var checkid = $('#userid').val();

            $.ajax({
                type: 'get',
                url: '/user/checkid/'+checkid,
                dataType: 'text',
                contentType:'application/json; charset=utf-8',
                success: function(msg){
                    alert(msg);
                    alert("통신 성공");
                },
                error: function(error){
                    alert(error);
                },
            }).done(function(msg){
                alert(msg);

                if(msg == "ok"){
                    alert("아이디를 사용 하실 수 있습니다.");
                    $("#email").focus();
                } else if(msg == "no"){
                    alert("이미 아이디가 존재합니다. 다시입력해주세요.");
                    $("#userid").val("");
                    $("#userid").focus();
                }
            }).fail(function(error) {
                alert("에러 발생!!!");
            });
        }
    }
};

user.init();