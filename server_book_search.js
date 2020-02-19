app.get('/book', function (req, res) {
  var qs = url.parse(req.url, true).query;

  var headers = {
      'Authorization': 'KakaoAK --REST API KEY--'
  };

  var dataString = qs.title;

  var options = {
    url: 'https://dapi.kakao.com/v3/search/book?target=title',
    method: 'GET',
    headers: headers,
    form: {
      query: dataString
    }
  };

  function callApi(callback) {
    
    request(options, function(error, response, body) {
      if (!error && response.statusCode == 200) {
        //console.log(JSON.parse(body));
      }
      console.log(response.statusCode);
  
      return callback(error, body, response.statusCode);
    });
  }

  callApi(function(error, result, statusCode){
    if(!error && statusCode == 200){
      res.send(JSON.parse(result));
    }
    else{
      res.send('400');
    }
  })
});