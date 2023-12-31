CREATE TABLE books(
	id INT PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(200),
	author VARCHAR(200),
	price DOUBLE,
	sales INT,
	stock INT,
	img_path VARCHAR(100)
);
INSERT INTO books (title, author ,price, sales , stock , img_path) VALUES('解忧杂货店','东野圭吾',27.20,100,100,'/static/uploads/jieyouzahuodian.jpg');
INSERT INTO books (title, author ,price, sales , stock , img_path) VALUES('边城','沈从文',23.00,100,100,'/static/uploads/biancheng.jpg');
INSERT INTO books (title, author ,price, sales , stock , img_path) VALUES('中国哲学史','冯友兰',44.5,100,100,'/static/uploads/zhongguozhexueshi.jpg');
INSERT INTO books (title, author ,price, sales , stock , img_path) VALUES('忽然七日',' 劳伦',19.33,100,100,'/static/uploads/huranqiri.jpg');
INSERT INTO books (title, author ,price, sales , stock , img_path) VALUES('苏东坡传','林语堂',19.30,100,100,'/static/uploads/sudongpozhuan.jpg');
INSERT INTO books (title, author ,price, sales , stock , img_path) VALUES('百年孤独','马尔克斯',29.50,100,100,'/static/uploads/bainiangudu.jpg');
INSERT INTO books (title, author ,price, sales , stock , img_path) VALUES('扶桑','严歌苓',19.8,100,100,'/static/uploads/fusang.jpg');
INSERT INTO books (title, author ,price, sales , stock , img_path) VALUES('给孩子的诗','北岛',22.20,100,100,'/static/uploads/geihaizideshi.jpg');
INSERT INTO books (title, author ,price, sales , stock , img_path) VALUES('为奴十二年','所罗门',16.5,100,100,'/static/uploads/weinushiernian.jpg');
INSERT INTO books (title, author ,price, sales , stock , img_path) VALUES('平凡的世界','路遥',55.00,100,100,'/static/uploads/pingfandeshijie.jpg');
INSERT INTO books (title, author ,price, sales , stock , img_path) VALUES('悟空传','今何在',14.00,100,100,'/static/uploads/wukongzhuan.jpg');
INSERT INTO books (title, author ,price, sales , stock , img_path) VALUES('硬派健身','斌卡',31.20,100,100,'/static/uploads/yingpaijianshen.jpg');
INSERT INTO books (title, author ,price, sales , stock , img_path) VALUES('从晚清到民国','唐德刚',39.90,100,100,'/static/uploads/congwanqingdaominguo.jpg');
INSERT INTO books (title, author ,price, sales , stock , img_path) VALUES('三体','刘慈欣',56.5,100,100,'/static/uploads/santi.jpg');
INSERT INTO books (title, author ,price, sales , stock , img_path) VALUES('看见','柴静',19.50,100,100,'/static/uploads/kanjian.jpg');
INSERT INTO books (title, author ,price, sales , stock , img_path) VALUES('活着','余华',11.00,100,100,'/static/uploads/huozhe.jpg');
INSERT INTO books (title, author ,price, sales , stock , img_path) VALUES('小王子','安托万',19.20,100,100,'/static/uploads/xiaowangzi.jpg');
INSERT INTO books (title, author ,price, sales , stock , img_path) VALUES('我们仨','杨绛',11.30,100,100,'/static/uploads/womensa.jpg');
INSERT INTO books (title, author ,price, sales , stock , img_path) VALUES('生命不息,折腾不止','罗永浩',25.20,100,100,'/static/uploads/shengmingbuxi.jpg');
INSERT INTO books (title, author ,price, sales , stock , img_path) VALUES('皮囊','蔡崇达',23.90,100,100,'/static/uploads/pinang.jpg');
INSERT INTO books (title, author ,price, sales , stock , img_path) VALUES('恰到好处的幸福','毕淑敏',16.40,100,100,'/static/uploads/qiadaohaochudexingfu.jpg');
INSERT INTO books (title, author ,price, sales , stock , img_path) VALUES('大数据预测','埃里克',37.20,100,100,'/static/uploads/dashujuyuce.jpg');
INSERT INTO books (title, author ,price, sales , stock , img_path) VALUES('人月神话','布鲁克斯',55.90,100,100,'/static/uploads/renyueshenhua.jpg');
INSERT INTO books (title, author ,price, sales , stock , img_path) VALUES('C语言入门经典','霍尔顿',45.00,100,100,'/static/uploads/cyuyanrumenjingdian.jpg');
INSERT INTO books (title, author ,price, sales , stock , img_path) VALUES('数学之美','吴军',29.90,100,100,'/static/uploads/shuxuezhimei.jpg');
INSERT INTO books (title, author ,price, sales , stock , img_path) VALUES('Java编程思想','埃史尔',70.50,100,100,'/static/uploads/Javabianchengsixiang.jpg');
INSERT INTO books (title, author ,price, sales , stock , img_path) VALUES('设计模式之禅','秦小波',20.20,100,100,'/static/uploads/shejimoshizhichan.jpg');
INSERT INTO books (title, author ,price, sales , stock , img_path) VALUES('图解机器学习','杉山将',33.80,100,100,'/static/uploads/tujiejiqixuexi.jpg');
INSERT INTO books (title, author ,price, sales , stock , img_path) VALUES('艾伦图灵传','安德鲁',47.20,100,100,'/static/uploads/ailuntulingzhuan.jpg');
INSERT INTO books (title, author ,price, sales , stock , img_path) VALUES('教父','马里奥普佐',29.00,100,100,'/static/uploads/jiaofu.jpg');

-- 创建用户表
CREATE TABLE users(
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100),
    PASSWORD VARCHAR(100),
    email VARCHAR(100)
);
INSERT INTO users VALUES(NULL,'张三1','abc','12345@qq.com');
INSERT INTO users VALUES(NULL,'张三2','abc','12341@qq.com');
INSERT INTO users VALUES(NULL,'张三3','abc','12322@qq.com');
INSERT INTO users VALUES(NULL,'张三4','abc','12343@qq.com');
INSERT INTO users VALUES(NULL,'张三5','abc','12344@qq.com');
INSERT INTO users VALUES(NULL,'张三6','abc','12346@qq.com');
INSERT INTO users VALUES(NULL,'张三7','abc','12347@qq.com');

-- 订单表
CREATE TABLE t_order(
	order_id INT PRIMARY KEY AUTO_INCREMENT,
	order_sequence VARCHAR(200),
	create_time VARCHAR(100),
	total_count INT,
	total_amount DOUBLE,
	order_status INT,
	user_id INT,
	foreign key (user_id) references users(id)
);
-- 订单详情表
CREATE TABLE t_order_item(
	item_id INT PRIMARY KEY AUTO_INCREMENT,
	book_name VARCHAR(20),
	price DOUBLE,
	img_path VARCHAR(50),
	item_count INT,
	item_amount DOUBLE,
	order_id VARCHAR(20),
	foreign key (order_id) references t_order(order_id)
);

SELECT * FROM books;
SELECT * FROM t_order;
SELECT * FROM t_order_item;
