<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title>現場登録画面</title>
    </head>
    <body>

    <!--<h1>現場管理アプリ</h1>-->
    <h2>現場情報を入力してください。</h2>

        <form action="login.php" method = "post">
        <!-- <?php if($err_msg !== null && $err_msg !== ''){
            echo $err_msg;
            echo '<br />';
            } ?> -->
            現場名<input type = "text" name = "project_name" value = ""><br />
            所在地<input type ="text" name="address" value = ""><br />
            概要<input type = "text" name="overview" value = ""><br />

            <h2>図面情報を登録してください。</h2>
            <p>
			<label for="image_file">ファイルを選択してください</label><br>
			<input type="file" name="image_file" id="image_file">
		</p>

            <input type="submit" name ="login" value = "次へ">
        </form>
    </body>
</html>