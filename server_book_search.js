app.get('/book', function (req, res) {

  var headers = {
      'Authorization': 'KakaoAK --REST API KEY--
  };

  var dataString = "query=ø°¿Ã∆Æ]";

  var options = {
    url: 'https://dapi.kakao.com/v3/search/book?',
    method: 'GET',
    headers: headers,
    form: {
      query: dataString
    }
  };

  function callback(error, response, body) {
    if (!error && response.statusCode == 200) {
      console.log(body);
    }
    console.log(response.statusCode);
  }

  request(options, callback);

  res.send('Hello World!');
});