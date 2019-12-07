var detail = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
        $('#btn-delete').on('click', function(){
           _this.delete();
        });
    },
    save : function () {
        var no = $('#id').val();
        var data = {
            id: $('#id').val(),
            title: $('#title').val(),
            writer: $('#writer').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'PUT',
            url: '/board/update/'+no,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            location.reload();
            //location.replace('/list');
        }).fail(function (error) {
            alert("에러에러에러!!!");
        });
    },

    delete : function(){
        var no = $('#id').val();

        $.ajax({
            type: 'delete',
            url: '/board/delete/'+no,
            dataType: 'text',
            contentType:'application/json; charset=utf-8',
        }).done(function(msg){
            if(msg == "ok"){
                alert('글이 삭제되었습니다.');
                location.replace('/list');
            }
        }).fail(function (error) {
            alert("에러발생!!!");
        });
    }

};

detail.init();