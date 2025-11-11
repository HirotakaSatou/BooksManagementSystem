-o- users
-- password = "qwerty"
INSERT INTO users(last_name, first_name, email, password, role, created_at, updated_at) VALUES('管理', '太郎', 'admin@example.com', '$2a$10$ZXmhkSlqNhfNmvqA5pkxDuzU9IU1JwlcsJy6kkCYbcse9VWv6KKyi', 1, '2022-12-19', '2022-12-19');
INSERT INTO users(last_name, first_name, email, passwrd, role, created_at, updated_at) VALUES('一般', '太郎', 'general@example.com', '$2a$10$ZXmhkSlqNhfNmvqA5pkxDuzU9IU1JwlcsJy6kkCYbcse9VWv6KKyi', 2, '2022-12-19', '2022-12-19');

-- book_names
INSERT INTO book_names(title, author, detail, publisher, genre, img, active, created_at, updated_at) VALUES('Java1年生 体験してわかる! 会話でまなべる! プログラミングのしくみ', '森 巧尚', 'Javaは企業向けの大きなシステムから、Androidアプリなどの小さなシステムまで、' || CHR(13) || 'さまざまな分野で非常に多く利用されている人気の言語です。' || CHR(13) || '開発会社などでは、新人研修の際に、Javaを学習するケースも多いです。' || CHR(13) || '本書はJava初心者の方に向けて、' || CHR(13) || '簡単なサンプルを作りながら、' || CHR(13) || '対話形式でプログラミングのしくみを学ぶ書籍です。', '翔泳社', 1, 'img_001_01.jpg', 1, '2022-12-19', '2022-12-19');
INSERT INTO book_names(title, author, detail, publisher, genre, img, active, created_at, updated_at) VALUES('オラクル認定資格教科書 Javaプログラマ Silver SE11（試験番号1Z0-815）', '山本 道子', '日本オラクルが主催する「Javaプログラマ試験」の中で一番人気の' || CHR(13) || '「Silver」の最新バージョン「SE11」に対応したテキスト＆問題集。' || CHR(13) || '大人気講師によるわかりやすい解説と、豊富な練習問題（模擬試験2回分含む）が' || CHR(13) || '収録された決定版！', '翔泳社', 4, 'img_002_01.jpg', 1, '2022-12-19', '2022-12-19');
INSERT INTO book_names(title, author, detail, publisher, genre, img, active, created_at, updated_at) VALUES('銀河鉄道の夜', '宮沢 賢治', 'どこまでもどこまでも一緒に行こう。' || CHR(13) || '――永久の未完成これ完成である――。自らの言葉を体現するかのように、賢治の死の直前まで変化発展し続けた、最大にして最高の傑作「銀河鉄道の夜」。そして、いのちを持つものすべての胸に響く名作「よだかの星」のほか、「ひかりの素足」「双子の星」「貝の火」などの代表作を収める。', '角川文庫', 2, null, 1, '2022-12-19', '2022-12-19');
INSERT INTO book_names(title, author, detail, publisher, genre, img, active, created_at, updated_at) VALUES('コーディングを支える技術 ~成り立ちから学ぶプログラミング作法 (WEB+DB PRESS plus)', '西尾 泰和', '本書は、プログラミング言語が持つ各種概念が「なぜ」存在するのかを解説する書籍です。' || CHR(13) || '世の中にはたくさんのプログラミング言語があります。そしてプログラミングに関する概念も、関数、型、スコープ、クラス、継承など、さまざまなものがあります。多くの言語で共通して使われる概念もあれば、一部の言語でしか使われない概念もあります。これらの概念は、なぜ生まれたのでしょうか。本書のテーマは、その「なぜ」を理解することです。', '技術評論社', 3, 'img_004_01.jpg', 1, '2022-12-19', '2022-12-19');
INSERT INTO book_names(title, author, detail, publisher, genre, img, active, created_at, updated_at) VALUES('Spring Framework超入門 ~やさしくわかるWebアプリ開発', '樹下 雅章', 'Spring Framework はJava開発におけるオープンソースのWebアプリケーションフレームワークです。' || CHR(13) || 'Spring Frameworkの登場で現代的なWebアプリケーション開発環境が整い,近年再び注目されています。' || CHR(13) || '本書は,Spring Frameworkの最新のバージョン5.3に対応し,' || CHR(13) || '1章~8章までで「Webアプリケーション開発で必要なWebの知識」「データ操作の方法」「MVCモデルの開発方法」など基本的なことが学べます。' || CHR(13) || 'また,9章~12章で実際にアプリを作成する方法を解説しているので,' || CHR(13) || 'Webアプリケーション開発の一連の流れが学べます。' || CHR(13) || '近年需要が高まっているセミナーや研修でも利用できます。', '株式会社フルネス', 1, 'img_005_01.jpg', 1, '2023-01-04', '2023-01-04');
INSERT INTO book_names(title, author, detail, publisher, genre, img, active, created_at, updated_at) VALUES('オラクル認定資格教科書 Javaプログラマ Gold SE11(試験番号1Z0-816)', '山本 道子', '日本オラクルが主催する「Javaプログラマ試験」の中で最上位資格として人気の' || CHR(13) || '「Gold」の最新試験に対応したテキスト&問題集。' || CHR(13) || '大人気講師によるわかりやすい解説と、豊富な練習問題(模擬試験含む)が' || CHR(13) || '収録された決定版!', '翔泳社', 4, 'img_006_01.jpg', 1, '2023-01-04', '2023-01-04');

-- books
INSERT INTO books(book_name_id, active, lendable, created_at, updated_at) VALUES(1, 1, 1, '2022-12-19', '2022-12-19');
INSERT INTO books(book_name_id, active, lendable, created_at, updated_at) VALUES(2, 1, 1, '2022-12-19', '2022-12-19');
INSERT INTO books(book_name_id, active, lendable, created_at, updated_at) VALUES(3, 1, 1, '2022-12-19', '2022-12-19');
INSERT INTO books(book_name_id, active, lendable, created_at, updated_at) VALUES(1, 0, 0, '2022-12-26', '2022-12-26');
INSERT INTO books(book_name_id, active, lendable, created_at, updated_at) VALUES(1, 1, 0, '2023-01-09', '2023-01-23');
INSERT INTO books(book_name_id, active, lendable, created_at, updated_at) VALUES(1, 1, 1, '2023-01-09', '2023-01-23');
INSERT INTO books(book_name_id, active, lendable, created_at, updated_at) VALUES(4, 1, 0, '2023-01-09', '2023-01-23');
INSERT INTO books(book_name_id, active, lendable, created_at, updated_at) VALUES(5, 1, 1, '2023-01-23', '2023-01-23');
INSERT INTO books(book_name_id, active, lendable, created_at, updated_at) VALUES(5, 1, 1, '2023-01-23', '2023-01-23');
INSERT INTO books(book_name_id, active, lendable, created_at, updated_at) VALUES(6, 1, 1, '2023-01-23', '2023-01-23');

--lendings
INSERT INTO lendings(book_id, user_id, lend_date, return_due_date, return_date, created_at, updated_at) VALUES(1, 1, '2023-02-06', '2023-02-20', '2023-02-16', '2023-02-06', '2023-02-06');
INSERT INTO lendings(book_id, user_id, lend_date, return_due_date, return_date, created_at, updated_at) VALUES(9, 1, '2023-02-10', '2023-02-24', '2023-02-22', '2023-02-10', '2023-02-10');
INSERT INTO lendings(book_id, user_id, lend_date, return_due_date, return_date, created_at, updated_at) VALUES(2, 1, '2023-02-21', '2023-03-07', '2023-03-07', '2023-02-06', '2023-02-06');
INSERT INTO lendings(book_id, user_id, lend_date, return_due_date, return_date, created_at, updated_at) VALUES(5, 1, '2023-02-28', '2023-03-16', null, '2023-02-28', '2023-02-28');

--genre
INSERT INTO genre(name, created_at, updated_at) VALUES('技術書', '2022-12-19', '2022-12-19');
INSERT INTO genre(name, created_at, updated_at) VALUES('小説', '2022-12-19', '2022-12-19');
INSERT INTO genre(name, created_at, updated_at) VALUES('ビジネス書', '2022-12-19', '2022-12-19');
INSERT INTO genre(name, created_at, updated_at) VALUES('資格・検定', '2022-12-19', '2022-12-19');