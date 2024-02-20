// Express 기본 모듈 불러오기
var express = require('express')
  , http = require('http')
  , path = require('path');
// Express의 미들웨어 불러오기
var bodyParser = require('body-parser')
  , static = require('serve-static')
  , fs = require('fs');
var ejs = require('ejs'); // ejs 모듈 추가
// 익스프레스 객체 생성
var app = express();
app.use(bodyParser.urlencoded({ extended: true }));

app.set('view engine', 'ejs');
app.set('views', path.join(__dirname, 'public')); // 경로 수정

// 기본 속성 설정
app.set('port', process.env.PORT || 3000);

app.use(bodyParser.json());

app.use('/public', static(path.join(__dirname, 'public')));

app.use(express.static("public"));
// 이거 public 한방에 몽땅읽는거임 .


// '/receive_data' 엔드포인트 수정
app.post('/receive_data', (req, res) => {
    const predictedClassCNN = req.body.predicted_class_cnn;
    const mid = req.body.mid;
    console.log('Received Predicted Class from Flask Server:', predictedClassCNN);
    console.log('Received mid:', mid);

    if (predictedClassCNN == 'industrial') {
      res.render('industrial_detail',{mid});
  } else if (predictedClassCNN == 'french_provance') {
      res.render('detail_success',{mid ,predictedClassCNN});
  } else if (predictedClassCNN == 'lovely_romantic') {
      res.render('detail_success',{mid ,predictedClassCNN});
  } else if (predictedClassCNN == 'modern') {
      res.render('detail_success',{mid ,predictedClassCNN});
  } else if (predictedClassCNN == 'unique') {
      res.render('detail_success',{mid ,predictedClassCNN});
  } else if (predictedClassCNN == 'vintage') { 
      res.render('detail_success',{mid ,predictedClassCNN});}
    });


// Express 서버 시작
http.createServer(app).listen(app.get('port'), function(){
    console.log('Express server listening on port ' + app.get('port'));
});

//<p>URL: 가구구매</p>
//<a href="http://192.168.0.8:8088/finalproject/funiture.h?mid=<%= mid%>">가구구매</a>