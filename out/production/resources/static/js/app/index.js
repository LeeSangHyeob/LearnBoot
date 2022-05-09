var main = {
    init : function(){
        var _this = this;
        /*$('#btn-save').on('click', function(){
            _this.save();
        });

        $('#btn-update').on('click', function(){
            _this.update();
        });

        $('#btn-delete').on('click', function(){
            _this.delete();
        });*/

        var map = new naver.maps.Map('naverMapArea');
        var curtBtn = '<div style="display: inline-flex; align-items: center; position:relative; padding: 10px; background-color:#fff;"><img src="https://tistory4.daumcdn.net/tistory/3031640/skin/images/keypoint_red.svg"></div>';
        var lat = '';
        var lng = '';


        var path = "testt".split('*')[0];
        console.log(path);
        var marker = new naver.maps.Marker({
            position : new naver.maps.LatLng(37.5666805, 126.9784147),
            map : map,
            icon: {
                //content: '<div><img src="https://tistory4.daumcdn.net/tistory/3031640/skin/images/keypoint_red.svg"><span>'
                //        + "testtesttesttesttesttesttesttest" + '</span></div>'
                content: '<div style="display: inline-flex; align-items: center; position:fixed; padding: 5px; border: 2px solid #4f7cef; border-radius: 30px; background-color:#fff;">'
                              +'<span style="display: inline-block; width: 28px; hieght: 28px"><img src="https://tistory4.daumcdn.net/tistory/3031640/skin/images/keypoint_red.svg"></span>'
                              +'<span style="display: inline-block; margin: 0 5px; font-size: 12px; font-weight: 700; line-height: 18px; vertical-align: middle">줌할때 마커 깨짐'
                              +'<span style="display: block; position: absolute; left: 20px; bottom: -6px; width: 7px; height: 7px; border-bottom: 2px solid #4f7cef; border-right: 2px solid #4f7cef; transform: rotate(45deg); background-color:#fff;"></span></div>'
            }
        });

        marker.setMap(map);

        naver.maps.Event.once(map, 'init', function(){
            var csmCtrl = new naver.maps.CustomControl(curtBtn, {
                position: naver.maps.Position.LEFT_TOP
            });
            csmCtrl.setMap(map);

            naver.maps.Event.addDOMListener(csmCtrl.getElement(), 'click', function(){
                console.log("Test");
                if(lat == '' || lng == '') {
                    console.log("latlng");
                    lat = '37.5666805';
                    lng = '126.9784147';
                    test(map, lat, lng);
                } else {
                    map.setCenter(new naver.maps.LatLng(lat, lng));
                }
            });
        });

    },

    save : function(){
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('글이 등록되었습니다.');
            window.location.href = '/'; // 글 등록이 성공하면 메인페이지로 이동
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function(){
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (){
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function (){
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function (){
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    encode : function(){
        $.ajax({
            type: 'PUT',
            url: '/api/v1/map/'+key,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (){
            alert('인코딩');
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}

test = function(map, val1, val2) {
        lat = val1;
        lng = val2;
        map.setCenter(new naver.maps.LatLng(lat, lng));
};

main.init();