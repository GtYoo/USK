USK [ Universal Studios Korea ] . Stand alone type
===================================================
2020年12月から2021年7月までのJAVA研修で初めて作ったチームプロジェクトです。  
1月21日から3月23日までの日程でした。
韓国にもユニバーサルスタジオができてほしいと願いの気持ちが込めてあります。  
3人のチームになって主に担当した部分はログイン、会員登録、管理者メニュー、UI、管理者データベースとの連結です。  
<br/>  
<br/>  
                      
概要
----
### なぜ作ったのか。
* チケット販売所の混雑状況を回避する。
* ホームページから沢山の情報や訪れた客の感想、レビューを見る。
* ホームページ  ->  情報提供  ->  予約・販売  ->  利益  
<br/>  
<br/>  

使用技術
--------
* Oracle SQL
* JAVA
* eclipse
* Tortoise SVN
* Photoshop
* Illustrator  
<br/>  
<br/>  

ERD / Class Diagram
-------------------

<br/>  
<br/>  

コードレビュー
--------------
* MVCパターンを学ぶ前、開発しましたのでClassの部品化を使いこなせませんでした。  
  今は現場で必ず使われてあるMVCをちゃんと学んでいます。  
<br/>
* ログイン機能  
会員情報をログインで利用するためのDTO Classを生成、ログイン Classで読み込む。

```java
public LoginPopup() {
  super((Frame)null , "" , true);
  //부모자리	 //타이틀  //젤먼저뜬창을 핸들링해야 다음창으로가능 //모달  
  HashMap<String , Object>hm = Common.getHm();
  this.loginDto = (LoginDTO)hm.get("LoginDTO");
}
```
  * データベースとの連結
```java
public void dbconnect() {
	try {
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, password);
	} catch(Exception e) {
		e.printStackTrace();
	}
}
```
<br/>  
<br/>  

SOURCE
------

<br/>  
<br/>  

