import 'package:flutter/material.dart';
import 'package:flutter_1/cupetino_page.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Flutter Demo',
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: TabPage());
  }
}

class TabPage extends StatefulWidget {
  @override
  _TabPageState createState() => _TabPageState();
}

class _TabPageState extends State<TabPage> {
  int _selected = 0;
  List _pages = [Text('page1'), Text('page2'), Text('page3')];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      bottomNavigationBar: BottomNavigationBar(
          onTap: _onItemTapped,
          currentIndex: _selected,
          items: const <BottomNavigationBarItem>[
            BottomNavigationBarItem(
                icon: Icon(Icons.home), title: Text('Home')),
            BottomNavigationBarItem(
                icon: Icon(Icons.search), title: Text('Search')),
            BottomNavigationBarItem(
                icon: Icon(Icons.account_circle), title: Text('Account')),
          ]),
      body: Center(
        child: _pages[_selected],
      ),
    );
  }

  void _onItemTapped(int value) {
    setState(() {
      _selected = value;
    });
  }
}

class HelloPage extends StatefulWidget {
  final String title;

  HelloPage(this.title);

  @override
  @override
  _HelloPageState createState() => _HelloPageState();
}

class _HelloPageState extends State<HelloPage> {
  String _message = 'hello world';
  int _count = 0;
  bool _switch = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        floatingActionButton: FloatingActionButton(
          child: Icon(Icons.add),
          onPressed: () => _changeMessage(),
        ),
        appBar: AppBar(
          title: Text(widget.title),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Text(_message, style: TextStyle(fontSize: 40)),
              Text('$_count', style: TextStyle(fontSize: 40)),
              RaisedButton(
                child: Text('하이'),
                onPressed: () {
                  setState(() {
                    Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => CupertinoPage()));
                  });
                },
              ),
              Switch(
                  value: _switch,
                  onChanged: (bool value) {
                    setState(() {
                      _switch = value;
                    });
                  })
            ],
          ),
        ));
  }

  _changeMessage() {
    setState(() {
      _message = 'zzz';
      _count++;
    });
  }
}
